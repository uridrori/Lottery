
public class Ball {
    private int number;

    // True if Display should wait
    // False if Selector should wait
    private boolean transfer = true;

    public synchronized void send(int number) {
        while (!transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        transfer = false;
        this.number = number;
        notifyAll();
    }

    public synchronized int draw() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        transfer = true;
        notifyAll();
        return number;
    }
}
