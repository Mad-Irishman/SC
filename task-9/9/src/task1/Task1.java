package task1;

public class Task1 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("State: NEW");
            Thread.sleep(100);

            System.out.println("State: RUNNABLE");
            synchronized (this) {
                wait(200);
                System.out.println("State: TIMED_WAITING");
            }

            synchronized (this) {
                System.out.println("State: BLOCKED");
            }

            Thread.sleep(100);
            System.out.println("State: WAITING");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("State: TERMINATED");
        }
    }
}