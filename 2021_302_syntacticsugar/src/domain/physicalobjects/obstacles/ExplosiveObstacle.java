package domain.physicalobjects.obstacles;

import domain.Constants;
import domain.physicalobjects.Vector;
import domain.physicalobjects.behaviors.collision.ExplosiveObstacleCollisionBehavior;
import domain.physicalobjects.behaviors.movement.StationaryMovementBehavior;
import domain.physicalobjects.boundingbox.SphereBoundingBox;
import domain.services.Service;

import java.util.List;

public class ExplosiveObstacle extends Obstacle{

    public ExplosiveObstacle(Vector location, List<Service> services) {
        super(location,   Constants.OBSTACLE_WIDTH, Constants.OBSTACLE_HEIGHT,
                new StationaryMovementBehavior(),
                new ExplosiveObstacleCollisionBehavior(), 1, services,
                new SphereBoundingBox(location.add(new Vector(20,20)),20));
    }


    @Override
    public String toString(){
        return "ExplosiveObstacle";
    }
}
