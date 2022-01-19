package domain.physicalobjects.behaviors.collision;

import domain.abilities.AbilityType;
import domain.abilities.UsefulAbilityType;
import domain.physicalobjects.GiftFragment;
import domain.physicalobjects.Paddle;

import java.util.Random;

public class GiftFragmentCollisionBehavior extends CollisionBehavior{
    @Override
    public void collide(Collision collision) {
        if(collision.getO2() instanceof Paddle){
            GiftFragment fragment = (GiftFragment) collision.getO1();
            fragment.getService(0).perform(fragment);

            AbilityType type = AbilityType.values()[new Random().nextInt(UsefulAbilityType.values().length)];
            fragment.getService(2).perform(type);
        }
    }
}
