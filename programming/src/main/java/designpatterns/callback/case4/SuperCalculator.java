package designpatterns.callback.case4;

/**
 * @author matthew_wu
 * @since 2019-08-19 17:51
 */
public class SuperCalculator {

    public void add(int a, int b, doJob customer) {
        int result = a + b;
        customer.fillBlank(a, b, result);
    }
}
