package domain.physicalobjects;

import domain.physicalobjects.behaviors.collision.NoCollisionBehavior;
import domain.physicalobjects.behaviors.movement.StationaryMovementBehavior;

public class Wall extends PhysicalObject{

    public Wall(Vector location, double width, double height) {
        super(location, width, height, new StationaryMovementBehavior(), new NoCollisionBehavior());
    }
}
