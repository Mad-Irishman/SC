package task1;

class Task1 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("State: " + getState());
            Thread.sleep(100);

            synchronized (this) {
                System.out.println("State: " + getState());
                wait(200);
            }

            System.out.println("State: " + getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Task 1 ===");
        Task1 task1 = new Task1();
        System.out.println("Before start: " + task1.getState());
        task1.start();

        Thread.sleep(50);
        System.out.println("After start: " + task1.getState());

        task1.join();
        System.out.println("After completion: " + task1.getState());
    }
}
