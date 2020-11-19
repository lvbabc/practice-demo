package dynamicproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DynamicInterceptor implements MethodInterceptor {

    /**
     * @param o 表示原始类
     * @param method 表示被拦截的方法
     * @param objects 数组表示参数列表，基本数据类型需要传入其包装类型，如int-->Integer、long-Long、double-->Double
     * @param methodProxy 表示对方法的代理，invokeSuper方法表示对原始对象方法的调用
     * @return 执行结果
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long startTimestamp = System.currentTimeMillis();

        // 注意这里是调用 invokeSuper 而不是 invoke，否则死循环，
        // methodProxy.invokeSuper执行的是原始类的方法，
        // method.invoke执行的是子类的方法
        Object result = methodProxy.invokeSuper(o, objects);

        long endTimeStamp = System.currentTimeMillis();

        long responseTime = endTimeStamp - startTimestamp;

        System.out.printf("method:%s, startTime:%s, responseTime:%s", method.getName(), startTimestamp, responseTime);

        return result;
    }

}
