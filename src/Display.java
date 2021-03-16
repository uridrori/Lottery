import java.util.concurrent.ThreadLocalRandom;

public class Display implements Runnable {
    private final Ball ball;
    private int printedSoFar;

    // standard constructors

    public Display(Ball ball) {
        printedSoFar = 0;
        this.ball = ball;
    }

    public void run() {
        for (int receivedNumber = ball.draw();
             receivedNumber != BallSelector.getEndToken();
             receivedNumber = ball.draw()) {
            System.out.println("Ball number #" + (printedSoFar + 1) + " is " + receivedNumber);
            printedSoFar++;
            waitForSelector();
        }
    }

    private void waitForSelector() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}