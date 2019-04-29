package designpatterns.compound;

/**
 * @Author matthew_wu
 * @Description 被观察的对象
 **/
public interface QuackObservable {
    void registerObserver(Observer observer);

    void notifyObservers();
}
