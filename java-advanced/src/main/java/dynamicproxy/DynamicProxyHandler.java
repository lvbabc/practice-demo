package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {
    // 原始类
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTimestamp = System.currentTimeMillis();

        Object obj = method.invoke(proxied, args);

        long endTimeStamp = System.currentTimeMillis();

        long responseTime = endTimeStamp - startTimestamp;

        System.out.printf("method:%s, startTime:%s, responseTime:%s", method.getName(), startTimestamp, responseTime);

        return obj;
    }
}
