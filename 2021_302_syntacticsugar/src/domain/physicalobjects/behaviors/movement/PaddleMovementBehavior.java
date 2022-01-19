package domain.physicalobjects.behaviors.movement;

import domain.Constants;
import domain.Game;
import domain.physicalobjects.Paddle;
import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.Vector;
import domain.physicalobjects.boundingbox.PolygonBoundingBox;

import java.util.Arrays;

public class PaddleMovementBehavior extends MovementBehavior{

    private double rotationSpeed;
    private double rotation;

    public PaddleMovementBehavior(){
        this(0);
    }

    public PaddleMovementBehavior(double speed){
        this(new Vector(speed, 0));
    }

    public PaddleMovementBehavior(Vector speed){
        super(speed);
        this.rotationSpeed = 0;
        this.rotation = 0;
    }

    @Override
    public void move(Object o){
        PhysicalObject object = (PhysicalObject) o;

        Vector newLocation = object.getLocation().add(getSpeed());

        object.setLocation(newLocation);
        object.getBoundingBox().shift(getSpeed());
        setSpeed(new Vector(90*getSpeed().getX()/100, 0));
        if (Math.abs(getSpeed().getX()) < 0.00001) setSpeed(new Vector(0, 0));
        //System.out.println("Rotation: "+rotation + " Speed: "+rotationSpeed + "Location: "  + paddle.getLocation().getX() + " " + paddle.getLocation().getY());
        /*Rotation mechanism */
        if (!Game.getInstance().isBallStickToPaddle()){
            if(rotationSpeed != 0){
                if(rotation < -0.5 || rotation > 0.5 )
                    rotationSpeed = rotationSpeed/2;
                else{
                    rotation += rotationSpeed;
                    object.getBoundingBox().rotate(rotationSpeed);
                    rotationSpeed = rotationSpeed/2;
                }
            }else if(rotation > 0){
                object.getBoundingBox().rotate(-Constants.PADDLE_ROTATION_SPEED);
                rotation -= Constants.PADDLE_ROTATION_SPEED;
            }else if(rotation < 0){
                object.getBoundingBox().rotate(Constants.PADDLE_ROTATION_SPEED);
                rotation += Constants.PADDLE_ROTATION_SPEED;
            }
            if(Math.abs(rotationSpeed)<0.00001)
                rotationSpeed = 0;
            if(Math.abs(rotation) < Constants.PADDLE_ROTATION_SPEED){
                object.getBoundingBox().rotate(-rotation);
                rotation = 0;
            }
        }
    }

    public void setSpeed(double speed){
        setSpeed(new Vector(speed, 0));
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public double getRotation() {
        return rotation;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }
}
