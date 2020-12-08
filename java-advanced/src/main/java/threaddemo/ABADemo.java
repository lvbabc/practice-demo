package threaddemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    static AtomicInteger atomicInteger = new AtomicInteger(100);

    static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<>(100, 0);

    static Thread t1 = new Thread(() -> {
        atomicInteger.compareAndSet(100, 101);
        atomicInteger.compareAndSet(101, 100);
    });

    static Thread t2 = new Thread(() -> {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean flag = atomicInteger.compareAndSet(100, 120);
        System.out.println("flag:" + flag + ",newValue:" + atomicInteger);
    });

    static Thread t3 = new Thread(() -> {
        int time = atomicStampedRef.getStamp();
        //更新为200
        atomicStampedRef.compareAndSet(100, 101, time, time + 1);
        //更新为100
        int time2 = atomicStampedRef.getStamp();
        atomicStampedRef.compareAndSet(101, 100, time2, time2 + 1);
    });


    static Thread t4 = new Thread(() -> {
        int time = atomicStampedRef.getStamp();
        System.out.println("sleep 前 t4 time:" + time);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean flag = atomicStampedRef.compareAndSet(100, 120, time, time + 1);
        System.out.println("flag:" + flag + ",newValue:" + atomicStampedRef.getReference());
    });

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        t3.start();
        t4.start();
    }
}
