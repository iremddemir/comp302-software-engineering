package domain.services;

import domain.GameBoard;
import domain.physicalobjects.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GetObstaclesService extends  GameBoardService{
    public GetObstaclesService(GameBoard gameBoard) {
        super(ServiceType.GET_OBSTACLES, gameBoard);
    }

    @Override
    public Object performSpecification(Object o) {
        return getGameBoard().getPhysicalObjects()
                    .stream()
                    .filter(object -> object instanceof Obstacle)
                    .collect(Collectors
                            .toCollection(ArrayList::new));
    }
}
