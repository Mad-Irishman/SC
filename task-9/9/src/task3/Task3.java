package task3;

import java.util.LinkedList;
import java.util.Random;

public class Task3 {
    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;

    public void produce() throws InterruptedException {
        Random random = new Random();
        synchronized (this) {
            while (true) {
                while (list.size() == LIMIT) {
                    wait();
                }
                int value = random.nextInt(100);
                list.add(value);
                System.out.println("Produced: " + value);
                notify();
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            while (true) {
                while (list.isEmpty()) {
                    wait();
                }
                int value = list.removeFirst();
                System.out.println("Consumed: " + value);
                notify();
                Thread.sleep(500);
            }
        }
    }
}