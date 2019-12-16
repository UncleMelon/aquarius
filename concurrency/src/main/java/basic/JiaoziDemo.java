package basic;

/**
 * @author matthew_wu
 * @since 2019-12-16 11:49
 */
public class JiaoziDemo {

    // 创建个共享对象做监视器用
    private static Object obj = new Object();

    // 大木盘子，一盘最多可盛10份饺子，厨师做满10份，服务员就可以端出去了。
    private static Integer platter = 0;

    //卖出的饺子总量，卖够100份就打烊收工
    private static Integer count = 0;

    static class Cook implements Runnable {

        @Override
        public void run() {
            while (count < 100 ) {
                synchronized (obj) {
                    while (platter < 10) {
                        platter ++;
                    }
                    //通知服务员饺子好了，可以端走了
                    obj.notify();
                    System.out.println(Thread.currentThread().getName() + "--饺子好啦，厨师休息会儿");
                }
                try {
                    //线程睡一会儿, 帮助服务员抢到对象锁
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"--打烊收工，厨师回家");
        }
    }

    static class Waiter implements Runnable {

        @Override
        public void run() {
            while (count < 100) {
                synchronized (obj) {
                    // 厨师做够10份了，就可以端出去了
                    while (platter < 10) {
                        try {
                            System.out.println(Thread.currentThread().getName()+"--饺子还没好，等待厨师通知...");
                            obj.wait();
                            System.out.println(Thread.currentThread().getName()+"被唤醒");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //饺子端给客人了，盘子清空
                    platter -= 10;
                    //又卖出去10份。
                    count += 10;
                    System.out.println(Thread.currentThread().getName()+"--服务员把饺子端给客人了");
                }
            }
            System.out.println(Thread.currentThread().getName()+"--打烊收工，服务员回家");
        }
    }

    public static void main(String[] args) {
        Thread cookThread = new Thread(new Cook(), "cookThread");
        Thread waiterThread = new Thread(new Waiter(), "waiterThread");
        cookThread.start();
        waiterThread.start();
    }


}
