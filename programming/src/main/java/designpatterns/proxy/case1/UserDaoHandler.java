package designpatterns.proxy.case1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author matthew_wu
 * @since 2019-08-14 18:09
 */
public class UserDaoHandler implements InvocationHandler {
    private Object obj;

    public UserDaoHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(obj, args);
        return result;
    }
}
