package basic;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 传统的同步锁就是独占式锁，当线程使用资源时候保持独占，无论读写。
 * 当人们发现请求队列（假设）中相邻请求为读-读的时候，阻塞是一种浪费资源的操作。比如公告板，
 * 所有路过的人（请求）都是读操作，并没有因为你和他在读的时候对内容造成了改变，所以在模型中，
 * 读与读操作不需要阻塞。而读写相邻则需要进行独占式操作了，因为写未完成的时候，信息是不完整的，
 * 此时读出来的信息有可能是错误的，所以写必然要保持独占式操作。而在应用程序中，读的频率是写的好几倍，
 * 也就是说如果读-读是不阻塞的，那么对性能来说是毋庸置疑的提升。
 *
 * Java中存在一种锁，名曰：ReentrantReadWriteLock。他可以实现内存中对资源操作的读写锁，读与读是不阻塞的。
 *
 * 此demo使用了重入锁和读写锁的对比，在主程序中分别新建18个读写操作，如果使用了读操作，则打印的读操作是连续的；
 * 如果使用了重入锁，则可能的情况是读写相邻打印，并且都是阻塞的，读者可以自行测试体会。
 */
public class ReadWriteLockDemo {
    private static Lock relock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws Exception {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws Exception {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readThread = new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("read:" + demo.handleRead(readLock));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeThread = new Runnable() {

            @Override
            public void run() {
                try {
                    demo.handleWrite(relock, new Random().nextInt());
//                    demo.handleWrite(writeLock, new Random().nextInt());
                    System.out.println("id:" + Thread.currentThread().getId() + " done!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 18; i++) {
            new Thread(readThread).start();
        }

        for (int i = 0; i < 18; i++) {
            new Thread(writeThread).start();
        }
    }



}
