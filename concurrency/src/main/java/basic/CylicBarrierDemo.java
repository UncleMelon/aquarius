package basic;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏
 * 栅栏和倒计时器很像，就是拦住一堆线程，等到线程数达到某个设定值之后同时把它们放出去。但是不同的是，
 * 它可以每次设定值达成时候运行定制线程中的run方法。就像是每次一个栏，够数就放。
 *
 * 例中CyclicBarrier有两个参数，前一个就是提到的设定值，后一个就是定制线程了。每当到达设定值的时候会触发定制线程。
 *
 * 每个阶段完成都会调用一下定制线程。
 */
public class CylicBarrierDemo {

    public static class Soldier implements Runnable {
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                doWork();
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(soldier + " done!");
        }

        public static class BarrierRun implements Runnable {
            boolean flag;
            int n;

            public BarrierRun(boolean flag, int n) {
                this.flag = flag;
                this.n = n;
            }

            @Override
            public void run() {
                if (flag) {
                    System.out.println("士兵:" + n + "个 done!");
                } else {
                    System.out.println("士兵: " + n + "个 集合完毕!");
                    flag = true;
                }
            }
        }

        public static void main(String[] args) {
            final int n = 10;
            Thread[] allSoldier = new Thread[n];
            boolean flag = false;
            CyclicBarrier cyclic = new CyclicBarrier(n, new BarrierRun(flag, n));
            System.out.println("集合");
            for (int i = 0; i < n; i++) {
                System.out.println("士兵 " + i + " 报道");
                allSoldier[i] = new Thread(new Soldier("士兵"+i, cyclic));
                allSoldier[i].start();
            }
        }
    }

}
