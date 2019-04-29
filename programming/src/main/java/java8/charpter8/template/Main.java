package java8.charpter8.template;

public class Main {
    public static void main(String[] args) {
        new OnlineBanking().processCustomer(1337, customer ->
                System.out.println("Hello " + customer.toString()));
    }
}
