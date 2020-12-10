package threaddemo;

public class DeadThreadDemo {
    public static void main(String[] args) {
        Account a = new Account(100);
        Account b = new Account(100);

        Thread t1 = new Thread(() -> {
            a.transfer(b, 10);
        });

        Thread t2 = new Thread(() -> {
            b.transfer(a, 10);
        });

        t1.start();
        t2.start();
    }
}
