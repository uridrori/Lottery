/**
 * A class which represents a lottery machine. The machine holds a ball selector and a display.
 */

public class LotteryMachine {

    //members
    private final BallSelector ballSelector;
    private final Display display;

    //methods

    /**
     * Constructor
     */
    public LotteryMachine() {
        Ball currentBall = new Ball();
        ballSelector = new BallSelector(currentBall);
        display = new Display(currentBall);
    }

    /**
     * A driver method which initializes the two components, creates a thread for each and starts them.
     */

    public void start() {
        Thread ballSelectorThread = new Thread(ballSelector);
        Thread displayThread = new Thread(display);

        ballSelectorThread.start();
        displayThread.start();
    }
}
