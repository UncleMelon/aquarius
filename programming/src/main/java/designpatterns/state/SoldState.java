package designpatterns.state;

public class SoldState extends State {
    private GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    void insertQuarter() {
        System.out.println("Please wait, we're already giving you a gumball");
    }

    @Override
    void ejectQuarter() {
        System.out.println("Sorry, you already turned the crank");
    }

    @Override
    void turnCrank() {
        System.out.println("Turning twice doesn't giving you another gumball");
    }

    @Override
    void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            System.out.println("Oops, out of gumballs!");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}
