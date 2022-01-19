package domain.physicalobjects.behaviors.collision;

import domain.physicalobjects.ExplosiveFragment;
import domain.physicalobjects.GiftFragment;
import domain.physicalobjects.obstacles.ExplosiveObstacle;
import domain.physicalobjects.obstacles.GiftObstacle;


public class GiftObstacleCollisionBehavior extends ObstacleCollisionBehavior {

    @Override
    public void collide(Collision collision) {
        super.collide(collision);

        GiftObstacle obstacle = (GiftObstacle) collision.getO1();

        if(obstacle.getHealth() == 0) {
            obstacle.getService(1).perform(new GiftFragment(collision.getLocation(), obstacle.getServices()));
        }
    }
}
