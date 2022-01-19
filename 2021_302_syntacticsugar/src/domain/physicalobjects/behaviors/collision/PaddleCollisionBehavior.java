package domain.physicalobjects.behaviors.collision;

import domain.physicalobjects.*;

public class PaddleCollisionBehavior extends CollisionBehavior{

    @Override
    public void collide(Collision collision) {
        Paddle paddle = (Paddle) collision.getO1();
        PhysicalObject o2 = collision.getO2();

        if(o2 instanceof Wall ||
            o2 instanceof Ball){

            //If paddle is still trying to move pass the wall or the ball, stop it
            //Else, it can move

            if(paddle.getBoundingBox()
                    .deepCopy()
                    .shift(paddle.getSpeed())
                    .getCollisionWith(o2.getBoundingBox()) != null)
                   paddle.setSpeed(new Vector(0,0));
        }
    }
}
