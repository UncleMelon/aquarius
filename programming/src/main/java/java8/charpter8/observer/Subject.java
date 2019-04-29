package java8.charpter8.observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
