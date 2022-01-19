package domain.physicalobjects.behaviors.collision;

import domain.physicalobjects.Ball;
import domain.physicalobjects.MagicalHexAmmo;
import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.Wall;
import domain.physicalobjects.behaviors.movement.MovementBehavior;
import domain.physicalobjects.obstacles.Obstacle;

public class MovingObstacleCollisionBehavior extends CollisionBehavior{
    @Override
    public void collide(Collision collision) {
        PhysicalObject o1 = collision.getO1();
        PhysicalObject o2 = collision.getO2();
        Obstacle obstacle = ((Obstacle) o1);

        if(collision.getO2() instanceof Obstacle ||
        collision.getO2() instanceof Wall) {
            MovementBehavior mb = collision.getO1().getMovementBehavior();
            mb.setSpeed(mb.getSpeed().scale(-1));
        }  else      if (o2 instanceof Ball){
            Ball ball = (Ball) o2;
            obstacle.decreaseHealth(ball.getAttackDamage());
        }else if(o2 instanceof MagicalHexAmmo){
            MagicalHexAmmo ammo = (MagicalHexAmmo) o2;
            obstacle.decreaseHealth(ammo.getAttackDamage());
        }

        if(obstacle.getHealth() == 0){
            obstacle.getService(0).perform(o1);
        }
    }
}
