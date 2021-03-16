/**
 * The display of the lottery machine. Receives a new ball and prints it. Is synchronized with the BallSelector class.
 */
public class Display implements Runnable {

    //members

    private final Ball ball;
    private int printedSoFar; //Used to enumerate balls printed


    /**
     * Constructor.
     *
     * @param ball is a lottery ball object containing a number.
     */
    public Display(Ball ball) {
        printedSoFar = 0;
        this.ball = ball;
    }

    /**
     * The method which runs the thread of the display.
     */

    @Override

    public void run() {
        for (int receivedNumber = ball.draw();
             receivedNumber != BallSelector.getEndToken();
             receivedNumber = ball.draw()) {
            System.out.println("Ball number #" + (printedSoFar + 1) + " is " + receivedNumber);
            printedSoFar++;
        }
    }
}