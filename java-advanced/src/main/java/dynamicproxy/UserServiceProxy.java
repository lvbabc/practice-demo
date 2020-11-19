package dynamicproxy;

public class UserServiceProxy implements UserService {
    private UserServiceImpl userService;

    public UserServiceProxy(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void login(String username, String password) {
        long startTimestamp = System.currentTimeMillis();

        userService.login(username, password);

        long endTimeStamp = System.currentTimeMillis();

        long responseTime = endTimeStamp - startTimestamp;

        System.out.printf("method:%s, startTime:%s, responseTime:%s", "login", startTimestamp, responseTime);
        System.out.println();
    }

}
