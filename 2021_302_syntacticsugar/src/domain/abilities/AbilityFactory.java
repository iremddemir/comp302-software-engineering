package domain.abilities;

import domain.physicalobjects.Ball;
import domain.physicalobjects.Paddle;
import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.Vector;

import domain.services.GameBoardServiceFactory;
import domain.services.ServiceType;
import domain.services.Service;

import java.util.ArrayList;
import java.util.List;

public class AbilityFactory {
    private static AbilityFactory instance;

    private AbilityFactory() {
    }

    public static AbilityFactory getInstance() {
        if (instance == null) {
            instance = new AbilityFactory();
        }
        return instance;
    }

    public Ability create(AbilityType type, ArrayList<PhysicalObject> physicalObjects) {
        switch (type) {
            case ChanceGivingAbility:
                return new ChanceGivingAbility(null);
            case MagicalHexAbility:
                return new MagicalHexAbility(physicalObjects.stream().filter(physicalObject -> physicalObject instanceof Paddle).findFirst().get());
            case PaddleExpansionAbility:
                return new PaddleExpansionAbility(physicalObjects.stream().filter(physicalObject -> physicalObject instanceof Paddle).findFirst().get());
            case UnstoppableBallAbility:
                return new UnstoppableBallAbility(physicalObjects.stream().filter(physicalObject -> physicalObject instanceof Ball).findFirst().get());
            case InfiniteVoidAbility:
                return new InfiniteVoidAbility(physicalObjects);
            case DoubleAccelAbility:
                return new DoubleAccelAbility(physicalObjects.stream().filter(physicalObject -> physicalObject instanceof Ball).findFirst().get());
            case HollowPurpleAbility:
                return new HollowPurpleAbility(physicalObjects);
        }
        return null;
    }
}
