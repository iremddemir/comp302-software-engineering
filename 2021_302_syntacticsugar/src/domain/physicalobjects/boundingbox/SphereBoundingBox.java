package domain.physicalobjects.boundingbox;

import domain.physicalobjects.Vector;
import domain.physicalobjects.behaviors.collision.Collision;

import java.util.ArrayList;
import java.util.List;

public class SphereBoundingBox extends BoundingBox{

    private Vector center;
    private double radius;
    private final int fragmentation = 30;

    public SphereBoundingBox(Vector center, double radius){
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Collision getPointCollision(Vector v) {
        if(center.distance(v) <= radius)
            return new Collision(v, v.subtract(center).norm());
        else
            return null;
    }

    @Override
    public Collision getCollisionWith(BoundingBox b) {

        for(int j=0; j<fragmentation; j++){
            Vector p = center.add(new Vector(radius, 0).rotate(Math.PI*2/fragmentation*j));
            Collision col = b.getPointCollision(p);
            if(col != null)
                return col;
        }
        return null;
    }

    @Override
    public BoundingBox shift(Vector v) {
        center = center.add(v);
        return this;
    }

    @Override
    public SphereBoundingBox deepCopy(){
        return new SphereBoundingBox(this.center, radius);
    }

    @Override
    public List<Vector> getFragmentation() {
        ArrayList<Vector> fragmentationList = new ArrayList<>();

        for(int j=0; j<fragmentation; j++){
            fragmentationList.add(center.add(new Vector(radius, 0).rotate(Math.PI*2/fragmentation*j)));
        }
        return fragmentationList;
    }

    @Override
    public void rotate(double rad) {
        /*Do nothing. Spheres do not need to rotate :) */
    }
}
