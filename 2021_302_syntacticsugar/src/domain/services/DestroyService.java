package domain.services;

import domain.GameBoard;
import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.obstacles.ExplosiveObstacle;
import domain.physicalobjects.obstacles.FirmObstacle;
import domain.physicalobjects.obstacles.GiftObstacle;
import domain.physicalobjects.obstacles.SimpleObstacle;

public class DestroyService extends GameBoardService {
    public DestroyService(GameBoard gameBoard) {
        super(ServiceType.DESTROY, gameBoard);
    }

    @Override
    public Object performSpecification(Object o) {
        PhysicalObject physicalObject = (PhysicalObject) o;
        getGameBoard().getPhysicalObjects().remove(physicalObject);

        if (o instanceof FirmObstacle
                || o instanceof GiftObstacle
                || o instanceof ExplosiveObstacle
                || o instanceof SimpleObstacle){
            int time = getGameBoard().getTime();
            if (time == 0) time =1; //to handle zero division
            getGameBoard().increaseScore(300.0/ time);
        }


        return null;
    }
}
