public class LotteryMachine {
    public static void main(String[] args) {
        Ball ball = new Ball();
        Thread sender = new Thread(new BallSelector(ball));
        Thread receiver = new Thread(new Display(ball));
        sender.start();
        receiver.start();
    }
}
