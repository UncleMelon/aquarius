package java8.charpter11.case1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class StreamPractice {

    List<Shop> shops = Arrays.asList(new Shop("BestPricec"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    /**
     * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors各个方法的弊端：
     * 1）newFixedThreadPool和newSingleThreadExecutor:
     *   主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
     * 2）newCachedThreadPool和newScheduledThreadPool:
     *   主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
     *
     * Positive example 1：
     *     //org.apache.commons.lang3.concurrent.BasicThreadFactory
     *     ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
     *         new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
     *
     *
     *
     * Positive example 2：
     *     ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
     *         .setNameFormat("demo-pool-%d").build();
     *
     *     //Common Thread Pool
     *     ExecutorService pool = new ThreadPoolExecutor(5, 200,
     *         0L, TimeUnit.MILLISECONDS,
     *         new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
     *
     *     pool.execute(()-> System.out.println(Thread.currentThread().getName()));
     *     pool.shutdown();//gracefully shutdown
     *
     *
     *
     * Positive example 3：
     *     <bean id="userThreadPool"
     *         class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
     *         <property name="corePoolSize" value="10" />
     *         <property name="maxPoolSize" value="100" />
     *         <property name="queueCapacity" value="2000" />
     *
     *     <property name="threadFactory" value= threadFactory />
     *         <property name="rejectedExecutionHandler">
     *             <ref local="rejectedExecutionHandler" />
     *         </property>
     *     </bean>
     *     //in code
     *     userThreadPool.execute(thread);
     *
     **/
    private final Executor executor = Executors.newFixedThreadPool(Math.min(
            shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(new StreamPractice().findPrices("ttt"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public void test(String product) {
        shops.stream().map(shop -> CompletableFuture.supplyAsync(
                () -> shop.getName() + " price is " + shop.getPrice(product),
                executor));
    }

    //这种方式是一串行的方式执行
    public List<String> findPricesAsync1(String product) {
        return shops.stream().map(shop -> CompletableFuture.supplyAsync(
                () -> shop.getName() + " price is " + shop.getPrice(product)
        )).map(CompletableFuture::join).collect(Collectors.toList());
    }

    //异步查询
    public List<String> findPricesAsync(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream().map(shop -> CompletableFuture.supplyAsync(
                () -> shop.getName() + " price is " + shop.getPrice(product)
        )).collect(Collectors.toList());

        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    //顺序查询
    public List<String> findPrices(String product) {
        return shops.stream().map(shop ->
                String.format("%s price is %.2f", shop.getName(),
                        shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    //使用并行流查询
    public List<String> findPricesByParallel(String product) {
        return shops.parallelStream().map(shop ->
                String.format("%s price is %.2f", shop.getName(),
                        shop.getPrice(product)))
                .collect(Collectors.toList());
    }


}
