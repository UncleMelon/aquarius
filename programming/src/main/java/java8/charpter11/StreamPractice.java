package java8.charpter11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPractice {

    List<Shop> shops = Arrays.asList(new Shop("BestPricec"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    private final Executor executor = Executors.newFixedThreadPool(Math.min(
            shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    private ExchangeService exchangeService = new ExchangeService();

    public static void main(String[] args) {
        long start = System.nanoTime();
        CompletableFuture[] futures = new StreamPractice().findPricesStream("myPhone")
                .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " +
                        ((System.nanoTime() - start) / 1_000_000) + " msecs")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in "
                        + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    public void test(Shop shop, String product) {
        CompletableFuture<String> futurePriceInUSD = CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                .thenCombine(
                        CompletableFuture.supplyAsync(() -> exchangeService.getRate(
                                ExchangeService.Money.EUR, ExchangeService.Money.UDS)),
                        (price, rate) -> price + rate
                );
    }


    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream().map(shop -> CompletableFuture.supplyAsync(
                () -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor)));
    }
    public List<String> findPricesAsync(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream().map(shop -> CompletableFuture.supplyAsync(
                () -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }


}
