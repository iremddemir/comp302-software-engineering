package domain.physicalobjects.behaviors.collision;

import domain.physicalobjects.ExplosiveFragment;
import domain.physicalobjects.obstacles.ExplosiveObstacle;


public class ExplosiveObstacleCollisionBehavior extends ObstacleCollisionBehavior {

    @Override
    public void collide(Collision collision) {
        super.collide(collision);

        ExplosiveObstacle obstacle = (ExplosiveObstacle) collision.getO1();

        if(obstacle.getHealth() == 0) {
            obstacle.getService(1).perform(new ExplosiveFragment(collision.getLocation(), obstacle.getServices()));
        }
    }
}
