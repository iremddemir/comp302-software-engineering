package domain.services;

import domain.GameBoard;

public abstract class GameBoardService extends Service {
    private GameBoard gameBoard;

    public GameBoardService(ServiceType serviceType, GameBoard gameBoard){
        super(serviceType);
        this.gameBoard = gameBoard;
    }

    protected GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    abstract Object performSpecification(Object o);
}
