import task1.Task1;
import task2.Task2;
import task3.Task3;
import task4.Task4;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== Task 1 ===");
        Task1 task1 = new Task1();
        task1.start();
        task1.join();

        System.out.println("\n=== Task 2 ===");
        Thread thread1 = new Thread(new Task2("Thread 1"));
        Thread thread2 = new Thread(new Task2("Thread 2"));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("\n=== Task 3 ===");
        Task3 buffer = new Task3();
        Thread producerThread = new Thread(() -> {
            try {
                buffer.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                buffer.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();


        System.out.println("\n=== Task 4 ===");
        Task4 task4 = new Task4(2);
        task4.start();
        Thread.sleep(10000);
        task4.interrupt();
    }
}
