package java8.charpter11;

import java.util.Random;

public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    private static final Random random = new Random();

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public double calculatePrice(String product) {
        randomDelay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
