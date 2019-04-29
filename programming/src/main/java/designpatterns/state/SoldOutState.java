package designpatterns.state;

public class SoldOutState extends State {
    private GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    void insertQuarter() {
        System.out.println("You can't insert a quarter, the machine is sold out");
    }

    @Override
    void ejectQuarter() {
        System.out.println("You can't eject, you haven't insert a quarter yet");
    }

    @Override
    void turnCrank() {
        System.out.println("You turned, but there are not gumballs");
    }

    @Override
    void dispense() {
        System.out.println("No gumball dispensed");
    }
}
