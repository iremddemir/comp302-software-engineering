package domain.physicalobjects.behaviors.collision;

import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.Vector;

public class Collision {
    private Vector normal;
    private Vector location;

    private PhysicalObject o1;
    private PhysicalObject o2;

    public Collision(Vector location, Vector normal){
       this(location,normal,null, null);
    }

    public Collision(Vector location, Vector normal, PhysicalObject o1, PhysicalObject o2){
        this.location = location;
        this.normal = normal;
        this.o1 = o1;
        this.o2 = o2;
    }
    public PhysicalObject getO1(){ return o1;}
    public PhysicalObject getO2(){ return o2;}

    public void setO1(PhysicalObject o1){  this.o1 = o1;}
    public void setO2(PhysicalObject o2){ this.o2=o2;}

    public Vector getNormal() {
        return normal;
    }
    public Vector getLocation() {
        return location;
    }
    public Collision reverse(){return new Collision(location, normal.reverse(), o2, o1);}
}
