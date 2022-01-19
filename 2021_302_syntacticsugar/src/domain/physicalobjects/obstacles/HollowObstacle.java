package domain.physicalobjects.obstacles;

import domain.physicalobjects.Vector;
import domain.physicalobjects.behaviors.collision.ExplosiveObstacleCollisionBehavior;
import domain.physicalobjects.behaviors.collision.GiftObstacleCollisionBehavior;
import domain.physicalobjects.behaviors.collision.ObstacleCollisionBehavior;
import domain.physicalobjects.behaviors.movement.StationaryMovementBehavior;
import domain.physicalobjects.boundingbox.SphereBoundingBox;
import domain.services.Service;
import domain.Constants;
import java.util.List;

public class HollowObstacle extends Obstacle{
    public HollowObstacle(Vector location, List<Service> services) {
        super(location, Constants.OBSTACLE_WIDTH, Constants.OBSTACLE_HEIGHT,
                new StationaryMovementBehavior(),
                new ObstacleCollisionBehavior(), 1, services,
                new SphereBoundingBox(location.add(new Vector(Constants.OBSTACLE_WIDTH/2,Constants.OBSTACLE_HEIGHT/2)),Constants.OBSTACLE_WIDTH/2));
    }

}
