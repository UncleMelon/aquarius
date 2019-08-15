package designpatterns.proxy.case1;

/**
 * @author matthew_wu
 * @since 2019-08-14 18:03
 */
public class UserDaoProxy implements IUserDao {
    //代理对象，需要维护一个目标对象
    private IUserDao target = new UserDao();
    @Override
    public void save() {
        System.out.println("代理操作: 开启事务...");
        target.save();
        System.out.println("代理操作: 提交事务...");
    }

    @Override
    public void find() {
        target.find();
    }
}
