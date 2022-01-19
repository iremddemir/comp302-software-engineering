package domain.physicalobjects.obstacles;

import domain.Constants;
import domain.physicalobjects.Vector;
import domain.physicalobjects.behaviors.collision.CollisionBehavior;
import domain.physicalobjects.behaviors.collision.GiftObstacleCollisionBehavior;
import domain.physicalobjects.behaviors.collision.ObstacleCollisionBehavior;
import domain.physicalobjects.behaviors.movement.MovementBehavior;
import domain.physicalobjects.behaviors.movement.StationaryMovementBehavior;
import domain.services.GameBoardService;
import domain.services.Service;

import java.util.List;

public class GiftObstacle extends Obstacle{
    public GiftObstacle(Vector location, List<Service> services) {
        super(location,
                Constants.OBSTACLE_WIDTH, Constants.OBSTACLE_HEIGHT,
                new StationaryMovementBehavior(),
                new GiftObstacleCollisionBehavior(), 1, services);
    }



    @Override
    public String toString(){
        return "GiftObstacle";
    }
}
