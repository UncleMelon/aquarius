package basic;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport提供线程挂起操作的支持类
 * 正如Condition使得原有的Object监视器封装成了新类，LockSupport提供使线程park和unpark之类的操作。
 *
 * 它在park时候线程会变成wait状态，而不是runnable。
 */
public class LockSupportDemo {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
                System.out.println("unpack" + getName());
            }
        }

        public static void main(String[] args) throws Exception {
            t1.start();
            Thread.sleep(100);
            t2.start();
            LockSupport.unpark(t1);
            LockSupport.unpark(t2);
            t1.join();
            t2.join();
        }
    }
}
