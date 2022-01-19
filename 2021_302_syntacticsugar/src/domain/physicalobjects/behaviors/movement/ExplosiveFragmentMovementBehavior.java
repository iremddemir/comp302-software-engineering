package domain.physicalobjects.behaviors.movement;

import domain.physicalobjects.ExplosiveFragment;
import domain.physicalobjects.Vector;

public class ExplosiveFragmentMovementBehavior extends MovementBehavior {


    public ExplosiveFragmentMovementBehavior(){
        super(new Vector(0, 2));
    }

    @Override
    public void move(Object o){
    	ExplosiveFragment explosiveFragment = (ExplosiveFragment) o;

        Vector newLocation = explosiveFragment.getLocation().add(getSpeed());

        explosiveFragment.setLocation(newLocation);
        explosiveFragment.getBoundingBox().shift(getSpeed());
    }
}
