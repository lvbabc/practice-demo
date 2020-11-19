package dynamicproxy;

public class UserServiceWithoutImpl {

    public void login(String username, String password) {
        System.out.println("欢迎" + username + "登录！");
    }

}
