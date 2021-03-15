import java.util.HashSet;
import java.util.Random;

public class BallSelector extends Thread{
    private static final int BALL_NUM_UPPER_BOUND = 50;
    private final HashSet<Integer> numbersSelected;
    private final Random random;

    public BallSelector(){
        numbersSelected = new HashSet<>();
        random = new Random();
    }

    public int getNewBall(){
        int num = 0;
        while (numbersSelected.contains(num) || num == 0){
            num = random.nextInt(BALL_NUM_UPPER_BOUND);
        }
        numbersSelected.add(num);
        return num;
    }
}
