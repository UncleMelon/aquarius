package java8.charpter2;

public class Examples {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        });

        Thread t1 = new Thread(() -> System.out.println("Hello World"));

    }
}
