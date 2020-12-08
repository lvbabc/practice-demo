package threaddemo;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {
    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable {
        public void run() {
            for (int k = 0; k < 10000; k++)
                //执行数组中元素自增操作,参数为index,即数组下标
                atomicIntegerArray.getAndIncrement(k % atomicIntegerArray.length());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts=new Thread[10];
        //创建10条线程
        for(int k=0;k<10;k++){
            ts[k]=new Thread(new AddThread());
        }
        //启动10条线程
        for(int k=0;k<10;k++){ts[k].start();}
        for(int k=0;k<10;k++){ts[k].join();}
        //执行结果
        //[10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000]
        System.out.println(atomicIntegerArray);
    }

}
