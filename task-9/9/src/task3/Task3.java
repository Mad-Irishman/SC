package task3;

import java.util.LinkedList;
import java.util.Queue;

class Producer extends Thread {
    private final Queue<Integer> queue;
    private final int maxSize;

    public Producer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                produce();
            }
        } catch (InterruptedException e) {
        }
    }

    private void produce() throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == maxSize) {
                System.out.println("Queue is full, producer is waiting...");
                queue.wait();
            }

            int value = (int) (Math.random() * 100);
            queue.add(value);
            System.out.println("Produced: " + value);

            queue.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> sharedQueue = new LinkedList<>();
        int maxSize = 5;

        Producer producer = new Producer(sharedQueue, maxSize);
        Consumer consumer = new Consumer(sharedQueue);

        producer.start();
        consumer.start();

        Thread.sleep(10000);

        producer.interrupt();
        consumer.interrupt();

        producer.join();
        consumer.join();
    }
}

class Consumer extends Thread {
    private final Queue<Integer> queue;

    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                consume();
            }
        } catch (InterruptedException e) {}
    }

    private void consume() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                System.out.println("Queue is empty, consumer is waiting...");
                queue.wait();
            }

            int value = queue.poll();
            System.out.println("Consumed: " + value);

            queue.notifyAll();
        }
    }
}
