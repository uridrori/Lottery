/**
 * A class representing a lottery ball.
 */
public class Ball {


    //The value of the ball.
    private int number;

    // True if Display should wait
    // False if Selector should wait
    private boolean transfer = true;

    /**
     * Lets the other modules (threads) that the ball is ready to be displayed.
     * @param number The value to be set for the ball transmitted.
     */
    public synchronized void display(int number) {
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

    /**
     * Lets the other modules (threads) that the ball is ready to be drawn.
     * @return The number of the ball drawn.
     */
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
