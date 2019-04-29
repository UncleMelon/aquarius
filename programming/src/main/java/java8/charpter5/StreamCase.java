package java8.charpter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamCase {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));

        //case 1
        transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue));
        //case 2  distinct 还是 collect toSet
        transactions.stream().map(Transaction::getTrader)
                .map(Trader::getCity).distinct();
        //case 3
        transactions.stream().map(Transaction::getTrader).distinct()
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName));
        //case 4
        transactions.stream().map(Transaction::getTrader).distinct()
                .map(Trader::getName).sorted()
                .reduce("", (n1, n2) -> n1 + n2);
                //collect(joining())
        //case 5
        transactions.stream().anyMatch(transaction ->
                "Milan".equals(transaction.getTrader().getCity()));
        //case 6
        transactions.stream().filter(transaction ->
                "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        //case 7
        transactions.stream().map(Transaction::getValue)
                .reduce(0, Integer::max);
        //case 8
        transactions.stream().
                sorted(Comparator.comparing(Transaction::getValue))
                .findFirst();
        transactions.stream().reduce(
                (t1, t2) -> t1.getValue() < t2.getValue()? t1 : t2);
        transactions.stream().min(Comparator.comparing(Transaction::getValue));
    }
}
