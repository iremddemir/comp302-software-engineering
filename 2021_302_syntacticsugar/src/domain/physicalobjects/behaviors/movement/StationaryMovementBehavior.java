package domain.physicalobjects.behaviors.movement;

import domain.physicalobjects.Vector;

public class StationaryMovementBehavior extends MovementBehavior{
    public StationaryMovementBehavior(){
        super(new Vector(0,0));
    }

    public void move(Object o){
    }
}
