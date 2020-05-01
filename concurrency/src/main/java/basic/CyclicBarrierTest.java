package basic;


public class CyclicBarrierTest {

    public static void main(String[] args) {
        final Sum sum = new Sum();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (sum) {
                        System.out.println("thread3 get lock");
                        sum.sum();
                        //此时唤醒没有作用，没有线程等待
                        sum.notify();
                        Thread.sleep(2000);
                        System.out.println("thread3 really release lock");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (sum) {
                        System.out.println("thread1 get lock");
                        sum.wait();
                        System.out.println(sum.total);
                        System.out.println("thread1 release lock");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (sum) {
                        System.out.println("thread2 get lock");
                        //释放sum的对象锁，等待其他对象唤醒（其他对象释放sum锁）
                        sum.wait();
                        System.out.println(sum.total);
                        System.out.println("thread2 release lock");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}

class Sum {
    public Integer total = 0;

    public void sum() throws Exception {
        total = 100;
        Thread.sleep(5000);
    }




}

/**
 例子1（错误使用导致线程阻塞）：
 三个线程，线程3先拥有sum对象的锁，然后通过sum.notify()方法通知等待sum锁的线程去获得锁，
 但是这个时候线程1,2并没有处于wait()导致的阻塞状态，而是在synchronized方法块处阻塞了，
 所以，这次notify()根本没有通知到线程1,2。然后线程3正常结束，释放掉sum锁，
 这个时候，线程1就立刻获得了sum对象的锁（通过synchronized获得），然后调用sum.wait()方法释放掉sum的锁，
 线程2随后获得了sum对象的线程锁（通过synchronized获得），这个时候线程1,2都处于阻塞状态，
 但是悲催的是，这之后再也没有线程主动调用sum.notify()或者notifyAll()方法显示唤醒这两个线程，所以程序阻塞

 例子2：还是上面程序，顺序不同，把线程3放到最下面。最后线程1,2都因为没有再次获得线程导致线程阻塞
 运行过程：
 线程1先运行获得sum对象锁（通过synchronized），但是随后执行了sum.wait()方法，主动释放掉了sum对象锁，
 然后线程2获得了sum对象锁（通过synchronized）,也通过sum.wait()失去sum的对象锁，
 最后线程3获得了sum对象锁（通过synchronized），主动通过sum.notify()通知了线程1或者2，
 假设是1，线程1重新通过notify()/notifyAll()的方式获得了锁，然后执行完毕，随后线程释放锁，
 然后这个时候线程2成功获得锁，执行完毕。
**/