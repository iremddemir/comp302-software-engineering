package domain.abilities;

import domain.physicalobjects.Paddle;
import domain.physicalobjects.PhysicalObject;

public class MagicalHexAbility extends Ability {

    public MagicalHexAbility(PhysicalObject appliesTo) {
        super(appliesTo);
    }

    @Override
    public void perform() {
        ((Paddle) getAppliesTo()).setMagicalHexEnabled(true);
    }

    @Override
    public void revert() {
        ((Paddle) getAppliesTo()).setMagicalHexEnabled(false);
    }
}
