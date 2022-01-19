package domain;

import domain.abilities.AbilityType;
import domain.abilities.UsefulAbilityType;
import domain.loadsave.LoadGame;
import domain.physicalobjects.obstacles.ObstacleType;
import domain.loadsave.SaveGame;
import domain.physicalobjects.Vector;
import domain.services.GameBoardServiceFactory;
import domain.services.ServiceType;

import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {
    private static Game instance = null;


    private GameStatus status;
    private GameBoard gameBoard;
    private SaveGame saveGame;
    private LoadGame loadGame;
    private String username;

    private Game() {
       status = GameStatus.RESUMED;
    }

    public void createGameBoard(int width, int height){
        gameBoard = new GameBoard(new Vector(width, height));
        GameBoardServiceFactory.getInstance().setGameBoard(gameBoard);
        gameBoard.addService(GameBoardServiceFactory.getInstance().create(ServiceType.GAME_WON));
        gameBoard.addService(GameBoardServiceFactory.getInstance().create(ServiceType.GAME_LOST));

        gameBoard.getPlayer().setUsername(username);
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();

        return instance;
    }

    public void movePaddle(Direction direction){ if(status == GameStatus.RESUMED) gameBoard.movePaddle(direction); }
    public void rotatePaddle(Direction direction){ if(status == GameStatus.RESUMED) gameBoard.rotatePaddle(direction); }
    public void addObstacle(ObstacleType type, Vector location) {gameBoard.addObstacle(type, location); }

    public void useAbility(AbilityType type){
        gameBoard.useAbility(type);
    }
    public List<AbilityType> getAvailableAbilities(){return gameBoard.getAvailableAbilities();}
    public void shootMagicalHex(){gameBoard.shootMagicalHex();}


    public void loadGame(String gameName){
        loadGame.loadGame(gameName);
    }

    public ArrayList<String> getSavedGames(String username) {
        loadGame = new LoadGame(username);
        return loadGame.getSavedGameList();
    }

    public void saveGame(int slot){
        saveGame = new SaveGame(getGameBoard());
        saveGame.saveGame();
    }


    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public GameStatus getStatus() { return status; }

    public void switchPaused() {
        if (status == GameStatus.PAUSED) {
            status = GameStatus.RESUMED;
        } else {
            status = GameStatus.PAUSED;
        }
    }

    public void infiniteVoid() {
        gameBoard.infiniteVoid();
    }

    public void doubleAccel() {
        gameBoard.doubleAccel();
    }

    public void hollowPurple() {
        gameBoard.hollowPurple();
    }

    public void run(){

        System.out.println("MyClass running");
        while(true){
            try {
                Thread.sleep(10);

                if(gameBoard != null && status == GameStatus.RESUMED)
                    gameBoard.doTickActions();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPlayerName(String username) {
        this.username = username;
    }

    public boolean isUsernameNull(){
        return username == null;
    }

    public void setStickToPaddle(Boolean value) {
        gameBoard.setStickToPaddle(value);
    }

    public boolean isBallStickToPaddle() {
        return gameBoard.isBallStickToPaddle();
    }

    public void shootBall() {
        gameBoard.shootBall();
    }

    public Vector getSize() {
        return gameBoard.getSize();
    }

    public void loseChance() {
        gameBoard.loseChance();
    }

    public boolean isValidInventory() {
    	return gameBoard.isValidInventory();
    }

    public void randomGame() {
    	gameBoard.randomGame();
    }

    public Double getScore() {
        return gameBoard.getScore();
    }

    public int getTime(){
        return gameBoard.getTime();
    }

    public int getLives() {
    	return gameBoard.getLives();

    }

    public String getUserName() {
    	return gameBoard.getPlayer().getUsername();
    }

}


