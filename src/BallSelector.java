import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A class representing a random ball selector.
 */
public class BallSelector implements Runnable {
    //constants
    private static final int BALL_NUM_UPPER_BOUND = 49;
    private static final int BALLS_PER_LOTTERY = 7;
    private static final int END_OF_BALLS = -1;
    private static final int MAX_WAIT_TIME = 3000;
    public static final int MIN_WAIT_TIME = 1000;

    //members
    private final Ball ball;


    //methods

    /**
     * Simple constructor.
     *
     * @param ball a Ball object.
     */
    public BallSelector(Ball ball) {
        this.ball = ball;
    }


    /**
     * The method which performs the business logic of the selector thread.
     */
    @Override
    public void run() {
        HashSet<Integer> numbers = new HashSet<>();
        for (int i = 0; i < BALLS_PER_LOTTERY; i++) {
            int number = ThreadLocalRandom.current().nextInt(BALL_NUM_UPPER_BOUND) + 1;

            //verify that the number is unique
            while (numbers.contains(number)) {
                number = ThreadLocalRandom.current().nextInt(BALL_NUM_UPPER_BOUND) + 1;
            }

            numbers.add(number);
            ball.display(number);
            waitForDisplay();
        }
        //let the display know that it shouldn't wait to receive balls anymore.
        ball.display(END_OF_BALLS);
        waitForDisplay();
    }

    /**
     * Lets other modules know what is the constant saved to indicate that all balls were sent.
     *
     * @return The constant saved.
     */

    public static int getEndToken() {
        return END_OF_BALLS;
    }

    /**
     * Waits a random time between set max and min values before drawing the next ball.
     */
    private void waitForDisplay() {
        try {
            int waitTime = ThreadLocalRandom
                    .current()
                    .nextInt(MAX_WAIT_TIME + 1 - MIN_WAIT_TIME) + MIN_WAIT_TIME;
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}