package dynamicproxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {
    public static void main(String[] args) {
//        staticProxy();
//        dynamicProxy();
        cglibProxy();
    }

    private static void staticProxy() {
        UserServiceImpl userService = new UserServiceImpl();
        UserServiceProxy userServiceProxy = new UserServiceProxy(userService);
        userServiceProxy.login("rex", "123");
    }

    private static void dynamicProxy() {
        // 设置变量可以保存动态代理类，默认名称以 $Proxy0 格式命名
//        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        UserServiceImpl userServiceImpl = new UserServiceImpl();

        UserService proxyInstance = (UserService) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(),
                userServiceImpl.getClass().getInterfaces(), new DynamicProxyHandler(userServiceImpl));

        proxyInstance.login("jack", "456");
    }

    private static void cglibProxy() {
        DynamicInterceptor interceptor = new DynamicInterceptor();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceWithoutImpl.class);  // 设置超类，cglib是通过继承来实现的
        enhancer.setCallback(interceptor);

        UserServiceWithoutImpl userService = (UserServiceWithoutImpl)enhancer.create();
        userService.login("mary", "789");
    }
}
