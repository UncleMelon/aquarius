package sync

import java.io._

import org.apache.commons.codec.digest.DigestUtils
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.slf4j.LoggerFactory

/**
  *
  * @author matthew_wu
  * @since 2019-08-22 22:40
  */
object PathSyncer {

  lazy val logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    val conf: Configuration = new Configuration()
    conf.set("fs.defaultFS", "hdfs://192.168.0.22:8020")
    conf.setBoolean("fs.hdfs.impl.disable.cache", true)
    implicit val fs = FileSystem.get(conf)
    //    val in = new Path("/user/spark/matthew/jars/xmlenc.xmlenc-0.52.jar")
    //    val status = fs.getFileStatus(in)
    //    val instant = Instant.ofEpochMilli(status.getModificationTime)
    //    val zone = ZoneId.systemDefault()
    //    println(LocalDateTime.ofInstant(instant, zone))
    sync(new File("/Users/matthew_wu/Documents/IdeaProjects/aquarius/spark/target/universal/stage/lib"), new Path("/user/spark/matthew/jars"))
  }

  @throws(classOf[IOException])
  def sync(file: File, path: Path, fileFilterOps: Option[FileFilter] = None)(implicit fileSystem: FileSystem): Unit = {
    if (fileFilterOps.isEmpty) {
      if (!initPath(file, path)) {
        syncChildren(file, path)
      }
    } else {
      if (!initPath(file, path, fileFilterOps.get)) {
        syncChildren(file, path, fileFilterOps)
      }
    }
  }

  private def initPath(file: File, path: Path)(implicit fileSystem: FileSystem): Boolean = {
    if (!fileSystem.exists(path)) {
      logger.debug("{} not exists ,copy from local", path.toString)
      fileSystem.copyFromLocalFile(false, true, new Path(file.toURI), path)
      initTime(file, path, None)
      true
    } else false
  }

  private def initPath(file: File, path: Path, fileFilter: FileFilter)
                      (implicit fileSystem: FileSystem): Boolean = {
    if (!fileSystem.exists(path)) {
      logger.debug("{} not exists ,copy from local", path.toString)
      val filterLocal = file.listFiles(fileFilter)
      filterLocal.foreach(local => fileSystem.copyFromLocalFile(false, true, new Path(local.toURI),
        new Path(path, local.getName)))
      initTime(file, path, Some(fileFilter))
      true
    } else false
  }

  private def syncChildren(file: File, path: Path, fileFilterOps: Option[FileFilter] = None)
                          (implicit fileSystem: FileSystem): Unit = {
    (file.isFile, fileSystem.isFile(path)) match {
      case (true, true) ⇒ syncFile(file, path, Map[String, Long]().empty)
      case (true, false) ⇒ throw new IllegalArgumentException("")
      case (false, true) ⇒ throw new IllegalArgumentException("")
      case (false, false) ⇒ if (fileFilterOps.isEmpty) {
        syncDir(file, path)
      } else {
        syncDir(file, path, fileFilterOps.get)
      }
    }
  }

  @throws(classOf[IOException])
  private def syncDir(file: File, path: Path)(implicit fileSystem: FileSystem): Unit = {
    //sync file step 1 delete diff dir in hdfs
    val needDeleteList = needDelete(file, path, None)
    needDeleteList.foreach(child ⇒ fileSystem.delete(new Path(path, child), true))
    var hdfsMap = Map[String, Long]().empty
    val hdfsFile = fileSystem.listStatus(path).filter(_.isFile)
    hdfsFile.foreach(hdfs => hdfsMap += (hdfs.getPath.getName -> hdfs.getModificationTime))
    //sync file step2 local sync this dir's file to hdfs
    val (listDirLocal, listFileLocal) = file.listFiles().partition(_.isDirectory)
    listFileLocal.foreach(localFile => syncFile(localFile, new Path(path, localFile.getName), hdfsMap))
    listDirLocal.foreach(localDir => syncChildren(localDir, new Path(path, localDir.getName)))
  }

  private def syncDir(file: File, path: Path, fileFilter: FileFilter)
                     (implicit fileSystem: FileSystem): Unit = {
    val localFilter = file.listFiles(fileFilter)
    val needDeleteList = needDelete(file, path, Some(fileFilter))
    needDeleteList.foreach(child ⇒ fileSystem.delete(new Path(path, child), true))
    val (localDir, localFile) = localFilter.partition(_.isDirectory)

    var hdfsMap = Map[String, Long]().empty
    val hdfsFile = fileSystem.listStatus(path).filter(_.isFile)
    hdfsFile.foreach(hdfs => hdfsMap += (hdfs.getPath.getName -> hdfs.getModificationTime))
    localFile.foreach(childFile => syncFile(childFile, new Path(path, childFile.getName), hdfsMap))
    localDir.foreach(childDir => syncChildren(childDir, new Path(path, childDir.getName)))
  }

  private def needDelete(localFile: File, hdfsPath: Path, fileFilterOps: Option[FileFilter])(implicit fileSystem: FileSystem) = {
    require(fileSystem.isDirectory(hdfsPath) && localFile.isDirectory)
    if (!fileSystem.exists(hdfsPath)) {
      fileSystem.mkdirs(hdfsPath)
    }
    if (fileFilterOps.nonEmpty) {
      fileSystem.listStatus(hdfsPath)
        .map(_.getPath.getName)
        .diff(localFile.listFiles(fileFilterOps.get).map(_.getName)).toList
    } else {
      fileSystem.listStatus(hdfsPath)
        .map(_.getPath.getName)
        .diff(localFile.listFiles().map(_.getName)).toList
    }
  }

  private def syncFile(file: File, path: Path, map: Map[String, Long])(implicit fileSystem: FileSystem): Unit = {
    if (!sameFile(file, path, map)) {
      fileSystem.copyFromLocalFile(false, true, new Path(file.toURI), path)
      logger.info(s"upload file ${file.getName}")
      syncFileTime(file, path)
    }
  }

  private def sameFile(file: File, path: Path, map: Map[String, Long])(implicit fileSystem: FileSystem): Boolean = {
    if (fileSystem.exists(path) &&
      file.isFile && map.contains(path.getName)) {
      if (file.lastModified() == map.getOrElse(path.getName, 0)) {
        true
      } else {
        val isEq = getHdfsFileMd5(path) == getLocalFileMd5(file)
        if (isEq) syncFileTime(file, path)
        isEq
      }
    } else false
  }

  private def initTime(file: File, path: Path, fileFilterOps: Option[FileFilter])(implicit fileSystem: FileSystem): Unit = {
    if (fileSystem.isFile(path)) {
      syncFileTime(file, path)
    } else {
      syncDirTime(file, path, fileFilterOps)
    }
  }

  private def syncFileTime(file: File, path: Path)(implicit fileSystem: FileSystem): Unit = {
    val localModifyTime = file.lastModified()
    fileSystem.setTimes(path, localModifyTime, -1)
  }

  private def syncDirTime(file: File, path: Path, fileFilterOps: Option[FileFilter] = None)
                         (implicit fileSystem: FileSystem): Unit = {
    require(file.isDirectory && fileSystem.isDirectory(path))
    val (localChildrenFile, localChildrenDir) = fileFilterOps match {
      case Some(filter) ⇒ file.listFiles(filter).partition(_.isFile)
      case None ⇒ file.listFiles().partition(_.isFile)
    }
    localChildrenFile.foreach(childFile => syncFileTime(childFile, new Path(path, childFile.getName)))
    localChildrenDir.foreach(childDir => syncDirTime(childDir, new Path(path, childDir.getName)))
  }

  @throws(classOf[IOException])
  private[sync] def getHdfsFileMd5(path: Path)(implicit dfs: FileSystem): String = {
    val in = dfs.open(path)
    try {
      val md5 = DigestUtils.md5Hex(new BufferedInputStream(in))
      md5
    } finally {
      in.close()
    }
  }

  @throws(classOf[IOException])
  private[sync] def getLocalFileMd5(file: File): String = {
    val in = new FileInputStream(file)
    try {
      val md5 = DigestUtils.md5Hex(new BufferedInputStream(in))
      md5
    } finally {
      in.close()
    }
  }

}
