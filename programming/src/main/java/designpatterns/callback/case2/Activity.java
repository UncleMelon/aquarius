package designpatterns.callback.case2;

/**
 * @author matthew_wu
 * @since 2019-08-19 17:00
 */
public abstract class Activity {
    protected void onCreate() {
        System.out.println("创建准备~~~~~~");
    }

    protected void onDestroy() {
        System.out.println("销毁准备~~~~~~");
    }
}
