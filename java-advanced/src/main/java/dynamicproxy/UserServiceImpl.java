package dynamicproxy;

public class UserServiceImpl implements UserService {

    @Override
    public void login(String username, String password) {
        System.out.println("欢迎" + username + "登录！");
    }

}
