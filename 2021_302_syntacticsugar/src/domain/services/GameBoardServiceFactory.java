package domain.services;

import domain.GameBoard;

public class GameBoardServiceFactory {
    private static GameBoardServiceFactory instance;
    private GameBoard gameBoard;
    private GameBoardServiceFactory(){}

    public static GameBoardServiceFactory getInstance(){
        if(instance == null)
            instance = new GameBoardServiceFactory();
        return instance;
    }

    public GameBoardServiceFactory setGameBoard(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        return this;
    }

    public Service create(ServiceType serviceType){
        if(gameBoard == null)
            throw new RuntimeException("GameBoard not found.");

        switch (serviceType){
            case SUMMON :
                return new SummonService(gameBoard);
            case DESTROY:
                return new DestroyService(gameBoard);
            case EQUIP_ABILITY:
                return new EquipAbilityService(gameBoard);
            case GAME_WON:
                return new GameWonService();
            case GAME_LOST:
                return new GameLostService();
        }
        return null;
    }
}
