package domain.physicalobjects.behaviors.collision;

import domain.physicalobjects.MagicalHexAmmo;
import domain.physicalobjects.Wall;
import domain.physicalobjects.obstacles.Obstacle;

public class MagicalHexAmmoCollisionBehavior extends CollisionBehavior{
    @Override
    public void collide(Collision collision) {
        MagicalHexAmmo ammo = (MagicalHexAmmo) collision.getO1();

        if(collision.getO2() instanceof Obstacle||
            collision.getO2() instanceof Wall)
            ammo.getService(1).perform(ammo);
    }
}
