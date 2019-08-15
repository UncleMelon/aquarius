package designpatterns.proxy.case1;

/**
 * @author matthew_wu
 * @since 2019-08-14 18:05
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("userDao save execute!");
    }

    @Override
    public void find() {
        System.out.println("userDao find execute!");
    }
}
