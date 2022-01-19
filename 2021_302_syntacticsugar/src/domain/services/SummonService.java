package domain.services;

import domain.GameBoard;
import domain.physicalobjects.PhysicalObject;

public class SummonService extends GameBoardService {
    public SummonService(GameBoard gameBoard) {
        super(ServiceType.SUMMON, gameBoard);
    }

    @Override
    public Object performSpecification(Object o) {
        PhysicalObject physicalObject = (PhysicalObject) o;
        getGameBoard().addPhysicalObject(physicalObject);

        return null;
    }
}
