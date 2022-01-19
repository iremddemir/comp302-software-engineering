package domain.physicalobjects;

import java.util.Objects;

public class Vector {
    private double x;
    private double y;

    public Vector(){
        this(0,0);
    }

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public Vector setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Vector setY(double y) {
        this.y = y;
        return this;
    }

    public Vector add(Vector shiftVector){
        Vector newVector = new Vector();

        newVector.setX(shiftVector.getX() + this.x);
        newVector.setY(shiftVector.getY() + this.y);

        return newVector;
    }

    public Vector subtract(Vector shiftVector){
        Vector newVector = new Vector();

        newVector.setX(-shiftVector.getX() + this.x);
        newVector.setY(-shiftVector.getY() + this.y);

        return newVector;
    }

    public double distance(Vector v){
        return Math.sqrt(
                Math.pow(this.x-v.getX(),2) +
                Math.pow(this.y-v.getY(),2)
        );
    }

    public Vector scale(double c){
        return new Vector(this.x *c ,  this.y*c);
    }

    public Vector norm(){
        double length = Math.sqrt(x*x + y*y);

        return new Vector(x/length, y/length);
    }
    public double crossForBoundingBox(Vector v){
        return this.x*v.getY()-this.y*v.getX();
    }
    public double dot(Vector v){
        return x*v.getX() + y*v.getY();}

    public Vector rotate(double rad, Vector p){
        Vector shifted = this.subtract(p);

        return shifted.rotate(rad).add(p);
    }

    public Vector rotate(double rad){
        if(rad == 0 || rad == 2*Math.PI)
            return new Vector(this.x, this.y);
        else if(rad == Math.PI/2)
            return new Vector(-this.y, this.x);

        double sin = Math.sin(rad);
        double cos = Math.cos(rad);

        return new Vector(this.x * cos  - this.y * sin,
                this.x * sin + this.y * cos);

    }

    public Vector reverse(){
        return this.scale(-1);
    }

    public String toString(){
        return "<<Vector: (" + x + ", " + y +")>>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Double.compare(vector.x, x) == 0 && Double.compare(vector.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
