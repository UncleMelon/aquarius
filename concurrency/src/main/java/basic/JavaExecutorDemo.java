package basic;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

public class JavaExecutorDemo {

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void run() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //这个方法有个问题，就是没有办法获知task的执行结果。如果我们想获得task的执行结果，我们可以传入一个Callable的实例
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Asynchronous task");
            }
        });

        //如果任务执行完成，future.get()方法会返回一个null。注意，future.get()方法会产生阻塞
        Future<?> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Asynchronous task");
            }
        });
        future.get();   //returns null if the task has finished correctly.

        //submit(Callable)和submit(Runnable)类似，也会返回一个Future对象，但是除此之外，
        // submit(Callable)接收的是一个Callable的实现，Callable接口中的call()方法有一个返回值，
        // 可以返回任务的执行结果，而Runnable接口中的run()方法是void的，没有返回值。
        // 如果任务执行完成，future.get()方法会返回Callable任务的执行结果。注意，future.get()方法会产生阻塞
        executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });
        System.out.println("future.get() = " + future.get());


        HashSet<Callable<String>> callables = new HashSet<>();
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 3";
            }
        });

        //invokeAny(...)方法接收的是一个Callable的集合，执行这个方法不会返回Future，
        // 但是会返回所有Callable任务中其中一个任务的执行结果。这个方法也无法保证返回的是哪个任务的执行结果，
        // 反正是其中的某一个。
        // 大家可以尝试执行上面代码，每次执行都会返回一个结果，并且返回的结果是变化的，可能会返回“Task2”也可是“Task1”或者其它
        String s = executorService.invokeAny(callables);
        System.out.println("result = " + s);



        //invokeAll(...)与 invokeAny(...)类似也是接收一个Callable集合，但是前者执行之后会返回一个Future的List，
        // 其中对应着每个Callable任务执行后的Future对象
        List<Future<String>> futures = executorService.invokeAll(callables);
        for(Future<String> f : futures){
            System.out.println("future.get = " + f.get());
        }

        executorService.shutdown();

    }

    /**
     * 使用newSingleThreadExecutor()方法创建单一线程池
     * 此方法可以创建单一线程池，线程池里只有一个线程，单一线程池可以实现以队列的方式来执行任务。
     * 由执行结果的线程名字可以看出，线程池中只有一个线程
     */
    private static void createExecutorPool3() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable(" " + (i+1)));
        }
    }

    /**
     * 使用newFixedThreadPool(int) 方法创建有界线程池
     * 此方法创建的是有界线程池，也就是池中的线程的个数可以指定最大值
     * 通过执行结果可以看出，线程池中的线程最大数量为3
     */
    private static void createExecutorPool2() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable(" " + (i+1)));
        }
    }


    /**
     * 使用newCachedThreadPool(ThreadFactory)定制线程工厂
     * 　　　　　　构造函数ThreadFactory是实现定制Thread的作用，具体可以看下面的例子。
     */
    private static void createExecutorPool1() {
        MyThreadFactory factory = new MyThreadFactory();
        ExecutorService executorService = Executors.newCachedThreadPool(factory);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程的自定义名称为: " + Thread.currentThread().getName());
            }
        });
    }

    /**
     * 使用newCachedThreadPool()方法创建无界线程池
     * newCachedThreadPool()方法创建的是无界线程池，可以进行线程自动回收，此类线程池中存放线程个数理论值为Integer.MAX_VALUE最大值
     * 通过线程的名字，可以看出来线程是从池中取出来的，是可以复用的。
     */
    private static void createExecutorPool() throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
               pool.execute(new MyRunnable(" " + (i + 1)));
        }
        Thread.sleep(3000);
        System.out.println();
        System.out.println();
        for (int i = 0; i < 5; i++) {
            pool.execute(new MyRunnable(" " + (i + 1)));
        }
        pool.shutdown();
    }

    /**
     * 在方法一中，每个子线程要执行10s ,  pool.shutdown 会在for结束后立刻执行（毫秒级的执行速度）
     * 执行之后ExecutorService 处于shutdown状态，此时ExecutorService不在接受任务任务，
     * 而子线程并未结束。下面的 while 循环中 pool.isTerminated() 为真的时候，
     * 所有线程执行结束，主线程继续往下走
     */
    private static void getExecutorPoolStatus1() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(340);
        for (int i = 0; i < 340 ; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("method1");
                }
            });
        }
        pool.shutdown();
        // 因为pool已经被shutdown了， 下面这个执行会抛 RejectedExecutionException
//        pool.execute(() -> System.out.println("ttt"));
        boolean isClose = true;
        while (isClose) {
            Thread.sleep(1000);
            if (pool.isTerminated()) {
                System.out.println("--------已经结束--------");
                isClose = false;
            } else {
                System.out.println("--------还未结束--------");
            }
        }
    }

    /**
     * 方法二同样能检测到所有任务是否完成
     */
    private static void getExecutorPoolStatus2() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(340);
        for (int i = 0; i < 340 ; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("method2");
                }
            });
        }
        pool.shutdown();
        boolean status = pool.awaitTermination(1, TimeUnit.SECONDS);
        while (!status) {
            status = pool.awaitTermination(1, TimeUnit.SECONDS);
            Thread.sleep(1000);
            System.out.println("-----其实这里输出ExecutorService并非shutdown状态,而是Terminated状态,子线程未结束---------");
        }
        System.out.println("--------------所有任务已经完成----------------");
    }

}


class MyRunnable implements Runnable {

    private String username;

    public MyRunnable(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " username=" + username +
                " begin " + System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName() + " username=" + username +
                " end " + System.currentTimeMillis());
    }
}

class MyThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("自定义名称: " + new Date());
        return thread;
    }
}