package domain.physicalobjects.behaviors.collision;

import domain.physicalobjects.*;
import domain.physicalobjects.obstacles.Obstacle;

public class UnstoppableBallCollisionBehavior extends CollisionBehavior{
    Vector newSpeed;

    @Override
    public void collide(Collision collision) {
        Ball ball = (Ball) collision.getO1();
        PhysicalObject o2 = collision.getO2();

        Vector ballSpeed = ball.getSpeed();
        Vector normal = collision.getNormal();

        if(		o2 instanceof Wall){
            Vector newSpeed = ballSpeed.subtract(normal.scale(normal.dot(ballSpeed)*2));
            ball.setSpeed(newSpeed);
        }		else if(o2 instanceof Paddle){

            if(ballSpeed.getY()==0) {
                newSpeed =  perpendicularColl(ballSpeed);
            }
            else if (((int)((Paddle) o2).getSpeed().getX() ^ (int) ball.getSpeed().getX()) < 0) {
                ball.setSpeed(new Vector(-ball.getSpeed().getX(), -ball.getSpeed().getY()));
            } else{
                newSpeed = ballSpeed.subtract(normal.scale(normal.dot(ballSpeed)*2));
                ball.setSpeed(newSpeed);
            }
        }
    }

    public Vector perpendicularColl(Vector speed) {
        double v = -speed.getX();
        double vScaled = Math.sqrt(2)*v/2;
        Vector newSpeed = new Vector(vScaled,vScaled);
        return newSpeed;
    }
}
