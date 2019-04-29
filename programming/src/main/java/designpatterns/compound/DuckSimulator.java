package designpatterns.compound;

public class DuckSimulator {
    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        CountingDuckFactory countingDuckFactory = new CountingDuckFactory();

        simulator.simulate(countingDuckFactory);
    }

    public void simulate(AbstractDuckFactory duckFactory) {

        Quackable redheadDuck = duckFactory.createRedheadDuck();
        Quackable duckCall = duckFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();
        Quackable gooseDuck = new GooseAdapter(new Goose());
        System.out.println("\nDuck Simulator: With Composite - Flocks");

        Flock flockOfDucks = new Flock();

        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(duckCall);
        flockOfDucks.add(rubberDuck);
        flockOfDucks.add(gooseDuck);

        Quackable mallardOne = duckFactory.createMallardDuck();
        Quackable mallardTwo = duckFactory.createMallardDuck();
        Quackable mallardThree = duckFactory.createMallardDuck();
        Quackable mallardFour = duckFactory.createMallardDuck();

        flockOfDucks.add(mallardOne);
        flockOfDucks.add(mallardTwo);
        flockOfDucks.add(mallardThree);
        flockOfDucks.add(mallardFour);

        System.out.println("\nDuck Simulator: With Observer");
        Quackologist quackologist = new Quackologist();
        flockOfDucks.registerObserver(quackologist);
        simulate(flockOfDucks);

        System.out.println("\nThe ducks quacked " + QuackCounter.getCount() + " times");
    }

    public void simulate(Quackable duck) {
        duck.quack();
    }
}
