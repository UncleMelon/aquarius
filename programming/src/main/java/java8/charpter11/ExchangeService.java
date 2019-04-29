package java8.charpter11;

public class ExchangeService {

    public enum Money {
        EUR, UDS
    }

    public double getRate(Money money1, Money money2) {
        return 0.2;
    }
}
