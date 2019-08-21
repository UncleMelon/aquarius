package designpatterns.callback.case2;

/**
 * @author matthew_wu
 * @since 2019-08-19 17:03
 */
public class Client {
    public static void main(String[] args) {
        Activity activity =new ConcreteActivity();
        activity.onCreate();
        activity.onDestroy();
    }
}
