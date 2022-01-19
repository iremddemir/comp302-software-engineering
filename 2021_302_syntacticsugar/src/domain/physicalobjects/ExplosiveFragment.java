package domain.physicalobjects;

import domain.physicalobjects.behaviors.collision.ExplosiveFragmentCollisionBehavior;
import domain.physicalobjects.behaviors.movement.ExplosiveFragmentMovementBehavior;

import domain.services.GameBoardService;
import domain.services.Service;

import java.util.List;

public class ExplosiveFragment extends PhysicalObject{
    public ExplosiveFragment(Vector location, List<Service> services) {
        super(location, 30, 30, new ExplosiveFragmentMovementBehavior(), new ExplosiveFragmentCollisionBehavior(), services);
    }
}
