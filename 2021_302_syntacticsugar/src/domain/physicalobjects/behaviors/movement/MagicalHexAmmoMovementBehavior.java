package domain.physicalobjects.behaviors.movement;

import domain.Constants;
import domain.physicalobjects.MagicalHexAmmo;
import domain.physicalobjects.Vector;

public class MagicalHexAmmoMovementBehavior extends MovementBehavior{
    public MagicalHexAmmoMovementBehavior(double rotation) {
        super(Constants.MAGICAL_HEX_AMMO_SPEED.rotate(rotation));
    }

    @Override
    public void move(Object o) {
        MagicalHexAmmo ammo = (MagicalHexAmmo) o;
        Vector newLocation = ammo.getLocation().add(getSpeed());

        ammo.setLocation(newLocation);
        ammo.getBoundingBox().shift(getSpeed());
    }
}
