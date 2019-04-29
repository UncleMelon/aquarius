package designpatterns.state;

public abstract class State {

    abstract void insertQuarter();

    abstract void ejectQuarter();

    abstract void turnCrank();

    abstract void dispense();
}
