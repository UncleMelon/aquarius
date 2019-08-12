package myakkastream

import com.mongodb.{MongoClient, MongoClientURI}
import com.mongodb.client.model.{Filters, Projections}
import org.bson.types.ObjectId

import scala.annotation.tailrec
import scala.collection.JavaConverters._

object MongoService {

  val mongoClient = new MongoClient(new MongoClientURI("mongodb://root:root@192.168.0.21:27017/?authSource=admin"))
  val database          = mongoClient.getDatabase("teambition")
  val membersCollection = database.getCollection("members")
  val teamsCollection   = database.getCollection("teams")


  def main(args: Array[String]): Unit = {
    println(findUserTeams("5be24d5c530c55000172cb78"))
    //    println(findUserTeams("5bf4bf6017ef0b00011e4c63"))
    //    println(findUserTeams("5900759b395022e0a338e4ff"))
  }

  def findUserTeams(userId: String): Seq[String] =
    iterateTeam(findTeamsByUserId(userId), Seq.empty, 0).map(_.id.toString)

  @tailrec
  def iterateTeam(teamIds: Set[ObjectId], seq: Seq[Team], i: Int): Seq[Team] = {
    val ts         = findTeams(teamIds)
    val sourceTeams = seq ++ ts
    val sourceIds = sourceTeams.map(_.id).filter(_ != null)
    val pIds       = ts.filter(t => !(t.parentId == null && t.teamType == "default"))
                      .map(_.parentId).filter(_ != null)
                      .filter(!sourceIds.contains(_)).toSet
    if (!pIds.isEmpty && i < 30)
      iterateTeam(pIds, sourceTeams, i + 1)
    else sourceTeams
  }

  def findTeamsByUserId(userId: String): Set[ObjectId] =
    membersCollection
        .find(Filters.and(Filters.eq("_userId", new ObjectId(userId)), Filters.eq("boundToObjectType", "team")))
      .projection(Projections.fields(Projections.include("_boundToObjectId")))
      .asScala
      .map(_.get("_boundToObjectId"))
      .filter(v => v.isInstanceOf[ObjectId])
      .map(v => v.asInstanceOf[ObjectId])
      .toSet

  def findTeamById(id: ObjectId): Team = {
    val team = teamsCollection.find(Filters.eq("_id", id)).first()
    Team(team.getObjectId("_id"), team.getObjectId("_parentId"), team.getString("name"), team.getString("type"))
  }

  def findTeams(ids: Set[ObjectId]): Seq[Team] = {
    teamsCollection
      .find(Filters.in("_id", ids.toList:_*))
      .asScala
      .map(team => Team(team.getObjectId("_id"), team.getObjectId("_parentId"), team.getString("name"), team.getString("type")))
      .toSeq
  }

  case class Team(id: ObjectId, parentId: ObjectId, name: String, teamType: String)
}
