package task4;

public class Task4 extends Thread {
    private int n;

    public Task4(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Current time: " + System.currentTimeMillis());
                Thread.sleep(n * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}