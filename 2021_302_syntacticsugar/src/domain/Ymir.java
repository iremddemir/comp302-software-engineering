package domain;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ymir {
    private ScheduledExecutorService executorService;
    private Random random;

    public Ymir() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        random = new Random();
    }

    public void start() {
        executorService.scheduleAtFixedRate(this::action, 2, 30, TimeUnit.SECONDS);
    }

    private void action() {
        System.out.println("Activated");
        if (!Game.getInstance().isBallStickToPaddle()){
            int coinFlip = random.nextInt(2);

            if (coinFlip == 0) {
                int ability = random.nextInt(3);
                System.out.println(ability);
                switch (ability) {
                    case 2: //Infinite void
                        Game.getInstance().infiniteVoid();
                        break;
                    case 1: //Double accel
                        Game.getInstance().doubleAccel();
                        break;
                    case 0: //Hollow purple
                        Game.getInstance().hollowPurple();
                        break;
                }
            }

        }

    }
}