package designpatterns.callback.case2;

/**
 * @author matthew_wu
 * @since 2019-08-19 17:02
 */
public class ConcreteActivity extends Activity {

    @Override
    protected void onCreate() {
        super.onCreate();
        System.out.println("创建中!!!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("销毁中!!!");
    }
}
