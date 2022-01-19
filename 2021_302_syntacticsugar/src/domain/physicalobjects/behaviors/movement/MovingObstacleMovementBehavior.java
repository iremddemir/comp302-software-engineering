package domain.physicalobjects.behaviors.movement;

import domain.Constants;
import domain.physicalobjects.Ball;
import domain.physicalobjects.Vector;
import domain.physicalobjects.obstacles.Obstacle;

public class MovingObstacleMovementBehavior extends MovementBehavior{
    public MovingObstacleMovementBehavior() {
        super(new Vector(Constants.MOVING_OBSTACLE_SPEED, 0));
    }

    @Override
    public void move(Object o) {
        Obstacle obstacle = (Obstacle) o;

        obstacle.setLocation(obstacle.getLocation().add(getSpeed()));
        obstacle.getBoundingBox().shift(getSpeed());
    }
}
