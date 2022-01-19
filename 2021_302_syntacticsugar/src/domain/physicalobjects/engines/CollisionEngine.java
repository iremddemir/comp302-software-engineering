package domain.physicalobjects.engines;

import domain.listeners.EventListener;
import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.behaviors.collision.Collision;
import domain.physicalobjects.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class CollisionEngine {
    private static CollisionEngine instance = null;
    private static final List<EventListener> listeners = new ArrayList<>();

    private CollisionEngine() {}

    public static CollisionEngine getInstance() {
        if (instance == null)
            instance = new CollisionEngine();
        return instance;
    }

    public void handleCollisions(List<PhysicalObject> physicalObjects){
        physicalObjects = new ArrayList<>(physicalObjects);

        int size = physicalObjects.size();
        for(int i=0; i<size-1; i++){
            for(int j=i+1; j < size; j++){
             /*   if(physicalObjects.get(i).getClass() ==
                        physicalObjects.get(j).getClass())
                    continue;
*/
                Collision collision =
                            physicalObjects.get(i).getBoundingBox().
                                    getCollisionWith(physicalObjects.get(j).getBoundingBox());
                if(collision != null){
                   collision.setO1(physicalObjects.get(i));
                   collision.setO2(physicalObjects.get(j));

                   physicalObjects.get(i).getCollisionBehavior().collide(collision);
                   physicalObjects.get(j).getCollisionBehavior().collide(collision.reverse());
                   for(EventListener listener: listeners){
                       listener.onEventOccured(collision);
                   }
                }
            }
        }
    }

    public void addEventListener(EventListener listener){
        listeners.add(listener);
    }
}
