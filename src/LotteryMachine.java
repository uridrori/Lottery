public class LotteryMachine {
    private final BallSelector ballSelector;
    private final Display display;

    public LotteryMachine() {
        Ball currentBall = new Ball();
        ballSelector = new BallSelector(currentBall);
        display = new Display(currentBall);
    }

    public void start() {
        Thread ballSelectorThread = new Thread(ballSelector);
        Thread displayThread = new Thread(display);

        ballSelectorThread.start();
        displayThread.start();
    }
}
