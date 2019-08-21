package designpatterns.hook.case1;

/**
 * @author matthew_wu
 * @since 2019-08-19 15:50
 */
public class AchieveClass extends AbstractClass {
    @Override
    public boolean isOpen() {
        return true;
    }

    public static void main(String[] args) {
        AchieveClass ac = new AchieveClass();
        ac.operating();
    }
}
