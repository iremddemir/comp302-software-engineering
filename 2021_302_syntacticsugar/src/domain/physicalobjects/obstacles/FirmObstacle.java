package domain.physicalobjects.obstacles;

import domain.Constants;
import domain.physicalobjects.Vector;
import domain.physicalobjects.behaviors.collision.MovingObstacleCollisionBehavior;
import domain.physicalobjects.behaviors.collision.ObstacleCollisionBehavior;
import domain.physicalobjects.behaviors.movement.MovingObstacleMovementBehavior;
import domain.physicalobjects.behaviors.movement.StationaryMovementBehavior;
import domain.services.Service;

import java.util.List;
import java.util.Random;

public class FirmObstacle extends Obstacle{
    public FirmObstacle(Vector location, List<Service> services) {
        super(location,
                Constants.OBSTACLE_WIDTH, Constants.OBSTACLE_HEIGHT,
                new StationaryMovementBehavior(),
                new ObstacleCollisionBehavior(), 3, services);

        if( new Random().nextInt(10) < 2 ){
            setMovementBehavior(new MovingObstacleMovementBehavior());
            setCollisionBehavior(new MovingObstacleCollisionBehavior());
        }
    }


    @Override
    public String toString(){
        return "FirmObstacle";
    }

}
