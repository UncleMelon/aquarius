package designpatterns.compound;

public class Quackologist implements Observer {
    @Override
    public void update(QuackObservable duck) {
        switch (duck.getClass().getSimpleName()) {
            case "MallardDuck":
            case "RedheadDuck":
            case "DuckCall":
            case "RubberDuck":
                System.out.println(duck.getClass().getSimpleName() + " just quacked.");
                break;
            case "GooseAdapter":
                System.out.println("Goose pretending to be a Duck just quacked");
        }
    }
}
