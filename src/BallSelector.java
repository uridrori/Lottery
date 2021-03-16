import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;


//TODO: aesthetics
//TODO: print here also?
public class BallSelector implements Runnable {
    private static final int BALL_NUM_UPPER_BOUND = 49;
    private final Ball ball;
    private static final int BALLS_PER_LOTTERY = 7;
    private static final int END_OF_BALLS = -1;
    private static final int MAX_WAIT_TIME = 3000;
    public static final int MIN_WAIT_TIME = 1000;

    public BallSelector(Ball ball) {
        this.ball = ball;
    }

    public void run() {
        HashSet<Integer> numbers = new HashSet<>();
        for (int i = 0; i < BALLS_PER_LOTTERY; i++) {
            int number = ThreadLocalRandom.current().nextInt(BALL_NUM_UPPER_BOUND) + 1;;
            while (numbers.contains(number)){
                number = ThreadLocalRandom.current().nextInt(BALL_NUM_UPPER_BOUND) + 1;
            }
            numbers.add(number);
            ball.display(number);
            waitForDisplay();
        }
        ball.display(END_OF_BALLS);
        waitForDisplay();
    }

    public static int getEndToken() {
        return END_OF_BALLS;
    }

    private void waitForDisplay(){
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