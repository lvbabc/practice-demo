package aqsdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceByCondition {
    private String name;
    private int count = 1;
    private boolean flag = false;

    //创建一个锁对象。
    Lock lock = new ReentrantLock();

    //通过已有的锁获取两组监视器，一组监视生产者，一组监视消费者。
    Condition producerCon = lock.newCondition();
    Condition consumerCon = lock.newCondition();

    /**
     * 生产
     *
     * @param name
     */
    public void product(String name) {
        lock.lock();

        try {
            while (flag) {
                try {
                    producerCon.await();
                } catch (InterruptedException e) {

                }
            }

            this.name = name + count;
            count++;
            System.out.println(Thread.currentThread().getName() + "...生产者5.0..." + this.name);
            flag = true;
            consumerCon.signal();//直接唤醒消费线程
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费
     */
    public void consume() {
        lock.lock();
        try {
            while (!flag) {
                try {
                    consumerCon.await();
                } catch (InterruptedException e) {

                }
            }
            System.out.println(Thread.currentThread().getName() + "...消费者.5.0......." + this.name);//消费烤鸭1
            flag = false;
            producerCon.signal();//直接唤醒生产线程
        } finally {
            lock.unlock();
        }
    }


}
