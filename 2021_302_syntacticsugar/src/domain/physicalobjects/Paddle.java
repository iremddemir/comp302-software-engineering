package domain.physicalobjects;

import domain.Constants;
import domain.Direction;
import domain.physicalobjects.behaviors.collision.PaddleCollisionBehavior;
import domain.physicalobjects.behaviors.movement.PaddleMovementBehavior;
import domain.services.Service;

import java.util.List;

public class Paddle extends PhysicalObject{

    private boolean magicalHexEnabled;

    public Paddle(Vector location, double width, double height, List<Service> services){
        super(location, width, height, new PaddleMovementBehavior(), new PaddleCollisionBehavior(), services);
        magicalHexEnabled = false;
    }

    public void rotate(Direction direction){
        double speedDirection = direction == Direction.LEFT ? -1 : 1;
        ((PaddleMovementBehavior)getMovementBehavior()).setRotationSpeed(speedDirection*Constants.PADDLE_ROTATION_SPEED);
    }

    public void setSpeed(Vector speed){
        getMovementBehavior().setSpeed(speed);
    }
    public Vector getSpeed(){ return  ((PaddleMovementBehavior) getMovementBehavior()).getSpeed();}

    public void shootMagicalHex() {
    	double rotation =  ((PaddleMovementBehavior)getMovementBehavior()).getRotation();
    	
        if(magicalHexEnabled){
            getService(0).perform(new MagicalHexAmmo(getLocation(), getServices(), rotation));
            getService(0).perform(new MagicalHexAmmo(getLocation().add(new Vector(this.getWidth(), 0)), getServices(),rotation));
        }
    	
    }
    public void setMagicalHexEnabled(boolean magicalHexEnabled) {
        this.magicalHexEnabled = magicalHexEnabled;
    }
}
