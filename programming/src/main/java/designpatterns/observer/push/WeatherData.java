package designpatterns.observer.push;

import java.util.ArrayList;

/**
 * 在对象之间定义一对多的依赖，这样一来，当一个对象改
 * 变状态，依赖它的对象都会收到通知，并自动更新。
 **/
public class WeatherData implements Subject {
    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size() ; i++) {
            Observer observer = observers.get(i);
            observer.update(temperature, humidity, pressure);
        }

    }

    public void measurementsChanged() {
        notifyObserver();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public void getTemperature() {

    }

    public void getHumodoty() {

    }

    public void getPressure() {

    }
}
