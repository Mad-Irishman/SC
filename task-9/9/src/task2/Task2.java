package task2;

class Task2 implements Runnable {
    private String name;

    public Task2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread " + name + " is running.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Task 2 ===");
        Thread thread1 = new Thread(new Task2("Thread 1"));
        Thread thread2 = new Thread(new Task2("Thread 2"));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
