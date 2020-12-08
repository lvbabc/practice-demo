package threaddemo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo {
    public static class Candidate {
        int id;
        volatile int score;
    }

    public static class Game {
        int id;
        volatile String name;

        public Game(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Game{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static AtomicIntegerFieldUpdater<Candidate> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater
            .newUpdater(Candidate.class, "score");

    static AtomicReferenceFieldUpdater<Game, String> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater
            .newUpdater(Game.class, String.class, "name");

    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu = new Candidate();

        Thread[] t = new Thread[10000];
        //开启10000个线程
        for (int i = 0; i < 10000; i++) {
            t[i] = new Thread(() -> {
                if (Math.random() > 0.4) {
                    atomicIntegerFieldUpdater.incrementAndGet(stu);
                    allScore.incrementAndGet();
                }
            });
            t[i].start();
        }

        for (int i = 0; i < 10000; i++) {
            t[i].join();
        }
        System.out.println("最终分数score=" + stu.score);
        System.out.println("校验分数allScore=" + allScore);

        //AtomicReferenceFieldUpdater 简单的使用
        Game game = new Game(2, "zh");
        atomicReferenceFieldUpdater.compareAndSet(game, game.name, "JAVA-HHH");
        System.out.println(game.toString());

    }
}
