package threaddemo;

import org.openjdk.jol.info.ClassLayout;

public class AccountingSync implements Runnable {
    int i = 0;
    static AccountingSync instance = new AccountingSync();

    public synchronized void increase() {
        i++;
    }


    @Override
    public void run() {
        synchronized (instance) {
            System.out.println(ClassLayout.parseInstance(instance).toPrintable());
            for (int j = 0; j < 1000000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //new新实例
//        AccountingSync instance = new AccountingSync();

        Thread t1 = new Thread(new AccountingSync());
        Thread t2 = new Thread(new AccountingSync());

        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println();
    }
}
