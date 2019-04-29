package java8.charpter8.template;

import java.util.function.Consumer;

/**
 * @Author matthew_wu
 * @Description 模版方法
 * @Date 21:57 2019-03-26
 * @Param 
 * @return 
 **/
public class OnlineBanking {

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

}
