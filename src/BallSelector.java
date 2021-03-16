import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


//TODO: aesthetics
//TODO: print here also?
public class BallSelector implements Runnable {
    private static final int BALL_NUM_UPPER_BOUND = 49;
    private Ball ball;
    private static final int BALLS_PER_LOTTERY = 7;
    private static final int END_OF_BALLS = -1;
    private static final int MAX_WAIT_TIME = 3000;
    public static final int MIN_WAIT_TIME = 1000;

    public BallSelector(Ball ball) {
        this.ball = ball;
    }

    public void run() {
        ArrayList<Integer> numbers = new ArrayList<>();
        while (numbers.size() < BALLS_PER_LOTTERY) {
            int newNum = ThreadLocalRandom.current().nextInt(BALL_NUM_UPPER_BOUND)+1;
            if (!numbers.contains(newNum)) {
                numbers.add(newNum);
            }
        }
        numbers.add(END_OF_BALLS);
        for (int number : numbers) {
            ball.send(number);
            try {
                int waitTime = ThreadLocalRandom.current().nextInt(MAX_WAIT_TIME + 1 - MIN_WAIT_TIME) + MIN_WAIT_TIME;
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    public static int getEndToken() {
        return END_OF_BALLS;
    }
}