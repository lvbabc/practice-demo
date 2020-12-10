package aqsdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semp = new Semaphore(5);
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            Runnable run = () -> {
                try {
                    semp.acquire();
                    System.out.println("Accessing: " + NO);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semp.release();
                }
            };
            exec.execute(run);
        }
        exec.shutdown();
    }
}
