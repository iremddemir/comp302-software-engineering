package domain.physicalobjects.behaviors.movement;

import domain.Constants;
import domain.physicalobjects.ExplosiveFragment;
import domain.physicalobjects.GiftFragment;
import domain.physicalobjects.Vector;

public class GiftFragmentMovementBehavior extends MovementBehavior{

    public GiftFragmentMovementBehavior() {
        super(Constants.GIFT_FRAGMENT_SPEED);
    }

    @Override
    public void move(Object o) {
        GiftFragment giftFragment = (GiftFragment) o;

        Vector newLocation = giftFragment.getLocation().add(getSpeed());

        giftFragment.setLocation(newLocation);
        giftFragment.getBoundingBox().shift(getSpeed());
    }
}
