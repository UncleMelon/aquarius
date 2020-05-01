package basic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时器CountDownLatch
 * 多线程中，需要知道这批线程的最大完成任务时间，也就是从第一个任务开始到最后返回这段时间的时长，
 * 那么倒计时器是必不可少的。就像各项资源准备完毕才进行下一步操作的模型一样，
 * CountDownLatch就是这样的多线程模型。等到所有任务调用了计数器，并且计数器总数到达某个数量时候，
 * 它才会将阻塞代码放开，让主线程往下走。
 *
 * await方法是阻塞倒计时器所在线程的方法，等到线程池service中调用countDown方法到达一定的数量（此处是10）之后，
 * 主线程的await方法才会过去。
 */
public class CountDownLatchDemo implements Runnable {
    static CountDownLatch end = new CountDownLatch(10);
    static CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println(Thread.currentThread().getId() + " check complete! ");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            service.submit(demo);
        }
        end.await();
        System.out.println("fire");
        service.shutdown();
    }
}
