package designpatterns.factory;

import designpatterns.factory.simple.ClamPizza;
import designpatterns.factory.simple.PepperoniPizza;
import designpatterns.factory.simple.VeggiePizza;

public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new NYStyleCheesePizza();
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza();
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        } else if (type.equals("clam")) {
            pizza = new ClamPizza();
        }
        return pizza;
    }
}
