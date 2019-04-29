package designpatterns.compound;

public class QuackCounter implements  Quackable {
    private Quackable duck;
    private static int count;

    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }

    @Override
    public void quack() {
        duck.quack();
        count++;
    }

    @Override
    public void registerObserver(Observer observer) {
        duck.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        duck.notifyObservers();
    }

    public static int getCount() {
        return count;
    }
}
