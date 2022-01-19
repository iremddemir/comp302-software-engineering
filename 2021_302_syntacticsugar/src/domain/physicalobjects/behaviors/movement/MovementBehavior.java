package domain.physicalobjects.behaviors.movement;

import domain.physicalobjects.Vector;

public abstract class MovementBehavior {
    private Vector speed;

    public MovementBehavior(Vector speed){
        this.speed = speed;
    }

    public void setSpeed(Vector speed){
        this.speed = speed;
    }

    public Vector getSpeed(){
        return this.speed;
    }
    public abstract void move(Object o);
}
