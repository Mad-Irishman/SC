package task4;

class Task4 extends Thread {
    private int n;

    public Task4(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                System.out.println("Current time: " + System.currentTimeMillis());
                Thread.sleep(n * 1000);
            }
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Task 4 ===");
        Task4 task4 = new Task4(2);
        task4.start();

        Thread.sleep(10000);
        task4.interrupt();
        task4.join();
    }
}
