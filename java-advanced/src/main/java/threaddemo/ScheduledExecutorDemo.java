package threaddemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {
    private static final ScheduledExecutorService executor = new
            ScheduledThreadPoolExecutor(1, Executors.defaultThreadFactory());

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy--MM-dd HH:mm:ss");


    public static void main(String[] args) {
        executor.scheduleWithFixedDelay(() -> {
            if (haveMsgAtCurrentTime()) {
                System.out.println(df.format(new Date()));
                System.out.println("大家注意了，我要发消息了");
            }
        }, 1, 1, TimeUnit.SECONDS);
    }


    private static boolean haveMsgAtCurrentTime() {
        return true;
    }
}
