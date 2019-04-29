package designpatterns.state;

public class NoQuarterState extends State {
    private GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    void insertQuarter() {
        System.out.println("You inserted a quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    void ejectQuarter() {
        System.out.println("You haven't inserted a quarter");
    }

    @Override
    void turnCrank() {
        System.out.println("You turned, but there's no quarter");
    }

    @Override
    void dispense() {
        System.out.println("You need to pay first");
    }
}
