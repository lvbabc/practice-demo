package threaddemo;

import java.util.concurrent.TimeUnit;

public class InterruptSleepThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        TimeUnit.SECONDS.sleep(20);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted When Sleep");
                    boolean interrupt = this.isInterrupted();
                    //中断状态被复位
                    System.out.println("interrupt:"+interrupt);
                }
            }
        };

        t1.start();
        TimeUnit.SECONDS.sleep(2);

        System.out.println(t1.getState());
        System.out.println(t1.isInterrupted());

        //中断处于阻塞状态的线程
        t1.interrupt();
    }
}
