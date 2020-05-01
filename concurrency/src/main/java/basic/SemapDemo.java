package basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量这个东西就比较玄乎了，有点像准入许可，拿到信号准入的时候才往下执行。就像是有一批人拿号，
 * 只有号码区间在某个范围的人能进去办事，然后办完事就会让资源释放，号码区间往后移。
 * 然而在信号量中应该算是复用类型的，归还了key值，将key值返回给下一个申请者。
 *
 * 在acquire获得key之后，操作读写，之后release。
 */
public class SemapDemo implements Runnable {
    final Semaphore semp = new Semaphore(5);

    @Override
    public void run() {
        try {
            semp.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + " done!");
            semp.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        SemapDemo semapDemo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            executorService.submit(semapDemo);
        }
        executorService.shutdown();
    }
}
