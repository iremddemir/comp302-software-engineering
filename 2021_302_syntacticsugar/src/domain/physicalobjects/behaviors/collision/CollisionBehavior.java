package domain.physicalobjects.behaviors.collision;


public abstract class CollisionBehavior {
    /*
            Collision Algorithm for o1 when o2 hits it.
         */
    public abstract void collide(Collision collision);
}
