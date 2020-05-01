package basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 对象监视器Condition
 * 在JDK实现了Lock来简化synchronized之后，Condition作为简化监视器而存在。
 * Condition的await方法和signal方法对应对象的wait和signal。
 */
public class ConditionAndLock implements Runnable {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("thread is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        ConditionAndLock c = new ConditionAndLock();
        Thread t = new Thread(c);
        t.start();
        // 防止线程start的比较慢，暂停1s
        Thread.sleep(1000);
        lock.lock();
        System.out.println("signal all");
        condition.signalAll();
        lock.unlock();
    }
}
