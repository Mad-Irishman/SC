package task1;

class Task1 extends Thread {
    private final Object lock = new Object();

    @Override
    public void run() {
        try {
            System.out.println("State (RUNNABLE): " + getState());
            Thread.sleep(100);

            synchronized (lock) {
                System.out.println("State (RUNNABLE): " + getState());
                System.out.println("State (WAITING): " + getState());
                lock.wait();
            }

        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Task 1 ===");

        Task1 task1 = new Task1();
        System.out.println("Before start (NEW): " + task1.getState());

        task1.start();

        Thread.sleep(50);
        System.out.println("After start (RUNNABLE): " + task1.getState());

        synchronized (task1.lock) {
            Thread.sleep(50);
            System.out.println("While holding lock (BLOCKED): " + task1.getState());
        }

        Thread.sleep(200);
        System.out.println("In waiting state (WAITING): " + task1.getState());

        synchronized (task1.lock) {
            task1.lock.notify();
        }

        task1.join();
        System.out.println("After completion (TERMINATED): " + task1.getState());
    }
}
