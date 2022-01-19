package domain.physicalobjects.boundingbox;

import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.Vector;
import domain.physicalobjects.behaviors.collision.Collision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolygonBoundingBox extends BoundingBox{

    private Vector[] points;
    private Vector[] edges;
    private int numEdges;
    private Vector center;

    private final int fragmentation = 30;

    //IMPORTANT!
    //While creating this class give vectors in clockwise order.
    public PolygonBoundingBox(Vector... points){
        this.numEdges = points.length;

        if(numEdges < 3)
            throw new RuntimeException("Not enough edges");

        this.points = Arrays.copyOf(points, numEdges);

        this.edges = new Vector[numEdges];

        for(int i=0; i<numEdges-1; i++)
            edges[i] = points[i+1].subtract(points[i]);

        edges[numEdges-1] = points[0].subtract(points[numEdges-1]);

        center = new Vector(0,0);
        for(Vector point: points){
            center = center.add(point);
        }
        center = center.scale(1.0/points.length);
    }

    public static BoundingBox createRectangleBoundingBox(Vector location, double width, double height) {
        return new PolygonBoundingBox(location, location.add(new Vector(width, 0)), location.add(new Vector(width, height)), location.add(new Vector(0, height)));
    }

    public Collision getPointCollision(Vector v){
        double smallestDist = Double.MAX_VALUE;
        Vector closestEdge = null;

        for(int i =0; i< points.length; i++){
            double crossValue = edges[i].crossForBoundingBox(v.subtract(points[i]));
            if(crossValue < 0){
                return null;
            }else{
                if(crossValue < smallestDist) {
                    smallestDist = crossValue;
                    closestEdge = edges[i];
                }
            }
        }
        return new Collision(v, closestEdge.rotate(Math.PI/2).reverse().norm());
    }

    @Override
    public Collision getCollisionWith(BoundingBox b) {
        //List<Collision> collisions = new ArrayList<>();
        for(int i =0; i<numEdges; i++){
            for(double j=1; j<fragmentation+1; j++){
                Vector p = points[i].add(edges[i].scale(j/fragmentation));
                Collision col = b.getPointCollision(p);
                if(col != null){
                    return col;
                    //collisions.add(col);
                }
            }
        }

        return null;
/*
        PhysicalObject o1 = collisions.get(0).getO1();
        PhysicalObject o2 = collisions.get(0).getO2();
        Vector location = new Vector(0,0);
        Vector normal = new Vector(0,0);

        for(Collision col: collisions){
            location = location.add(col.getLocation());
            normal = normal.add(col.getNormal());
        }
        location = location.scale(1.0/ collisions.size());
        normal = normal.norm();

        Collision result = new Collision(location, normal);
        result.setO1(o1);
        result.setO2(o2);
        return result;
        */
    }

    @Override
    public BoundingBox shift(Vector v) {
        for(int i =0; i<numEdges; i++){
            points[i] = points[i].add(v);
        }
       center = center.add(v);
        return this;
    }

    @Override
    public PolygonBoundingBox deepCopy(){
        return new PolygonBoundingBox(this.points);
    }

    @Override
    public List<Vector> getFragmentation() {
        ArrayList<Vector> fragmentationList = new ArrayList<>();

        for(int i =0; i<numEdges; i++){
            for(double j=1; j<fragmentation+1; j++){
               fragmentationList.add(points[i].add(edges[i].scale(j/fragmentation)));
            }
        }

        return fragmentationList;
    }

    @Override
    public void rotate(double rad) {


        for(int i=0; i<points.length; i++){
            points[i] = points[i].rotate(rad, center);
        }

        for(int i=0; i<numEdges-1; i++)
            edges[i] = points[i+1].subtract(points[i]);

        edges[numEdges-1] = points[0].subtract(points[numEdges-1]);

    }

    public Vector[] getPoints() {
        return points;
    }
}
