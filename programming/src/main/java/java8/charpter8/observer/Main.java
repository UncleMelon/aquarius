package java8.charpter8.observer;

public class Main {
    public static void main(String[] args) {
        Feed f = new Feed();

        f.registerObserver(tweet -> {
            if(tweet != null && tweet.contains("money")) {
                System.out.println("Break news in NY! " + tweet);
            }
        });

        f.registerObserver(tweet -> {
            if(tweet != null && tweet.contains("queen")) {
                System.out.println("Yet another news in London... " + tweet);
            }
        });
    }
}
