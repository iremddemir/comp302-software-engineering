package domain;

import domain.abilities.*;
import domain.physicalobjects.*;
import domain.physicalobjects.behaviors.collision.BallCollisionBehavior;
import domain.physicalobjects.engines.AbilityEngine;
import domain.physicalobjects.engines.CollisionEngine;
import domain.physicalobjects.engines.PhysicsEngine;
import domain.physicalobjects.obstacles.*;
import domain.services.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GameBoard extends ServiceAttachable {
    private ArrayList<PhysicalObject> physicalObjects;
    private HashMap<ObstacleType, Integer> obstacleInventory = new HashMap <>();
    private Ball ball;
    private Paddle paddle;
    private Vector size;
    private Player player;
    private final int simpleObstacleMin = 75;
    private final int firmObstacleMin = 10;
    private final int explosiveObstacleMin = 5;
    private final int giftObstacleMin = 10;
    private static HashMap<ArrayList<Integer>, Integer> gameGrid = new HashMap<>();
    private List<Service> services = new ArrayList<>();
    private int time_milliseconds;

    public GameBoard(Vector size){
        super(null);

        this.size = size;
        physicalObjects = new ArrayList<>();

        List<Service> basicServices = new ArrayList<>();
        basicServices.add(new SummonService(this));
        basicServices.add(new DestroyService(this));

        paddle = new Paddle(new Vector(size.getX()/2 - 100,size.getY()-100), Constants.PADDLE_LENGTH, 20, basicServices);
        ball = new Ball(new Vector(size.getX()/2 - 12.5,size.getY()-126), 25, 25, paddle.getMovementBehavior(), new BallCollisionBehavior());
        ball.setSpeed(new Vector(0,0));
        player = new Player();
        physicalObjects.add(ball);
        physicalObjects.add(paddle);
        physicalObjects.add(new Wall(new Vector(0,size.getY()/6-21), size.getX(), 20));
        physicalObjects.add(new Wall(new Vector(-21,20), 20, size.getY()));
        physicalObjects.add(new Wall(new Vector(size.getX()+1, 0), 20, size.getY()));
        physicalObjects.add(new Wall(new Vector(0, size.getY()+1), size.getX(), 20));

    }

    public void addPhysicalObject(PhysicalObject physicalObject){
        physicalObjects.add(physicalObject);
    }

    public List<PhysicalObject> getPhysicalObjects(){
        return this.physicalObjects;
    }

    public Obstacle addObstacle(ObstacleType type, Vector location){
        //MODIFIES: physicalObjects list
        //EFFECTS: adds a new obstacle to physicalObjects list on given location and in given type.

        Obstacle obstacle = ObstacleFactory.getInstance().create(type, location, GameBoardServiceFactory.getInstance().setGameBoard(this));
        physicalObjects.add(obstacle);

        return obstacle;
    }

    public void movePaddle(Direction direction){ paddle.setSpeed((direction == Direction.LEFT) ? new Vector(-9, 0): new Vector(9, 0)); }
    public void rotatePaddle(Direction direction){ paddle.rotate(direction); }

    public void doTickActions() {
        time_milliseconds += 10;
        CollisionEngine.getInstance().handleCollisions(physicalObjects);
        PhysicsEngine.getInstance().moveObjects(physicalObjects);
        AbilityEngine.getInstance().calculate(physicalObjects);
        isGameWon();
    }

    public void isGameWon(){
        for (PhysicalObject o: physicalObjects){
            if (o instanceof Obstacle)
                return;
        }
        services.get(0).perform(null);
    }

    public Vector getSize() {
        return size;
    }

    public Ball getBall(){
        return ball;
    }

    public void shootMagicalHex() {
        paddle.shootMagicalHex();
    }

    public List<AbilityType> getAvailableAbilities() {
       return player.getAbilities();
    }

    public Player getPlayer() {
        return player;
    }
    public List<Obstacle> getObstacles() {
        List<Obstacle> obstacles = new ArrayList<>();
        for (PhysicalObject object : physicalObjects) {
            if (object.getClass().getSuperclass().getSimpleName().equals("Obstacle")){
                obstacles.add((Obstacle) object);
            }
        }
        return obstacles;
    }

    public void increaseScore(double score){
        player.setScore(player.getScore() + score);
    }

    public void useAbility(AbilityType type) {
        if(player.removeAbility(type)){
            Ability ability = AbilityFactory.getInstance().create(type, physicalObjects);
            AbilityEngine.getInstance().activateAbility(ability);
        }
    }

    public void infiniteVoid() {
        Ability ability = AbilityFactory.getInstance().create(AbilityType.InfiniteVoidAbility, physicalObjects);
        AbilityEngine.getInstance().activateAbility(ability);
    }

    public void doubleAccel() {
        Ability ability = AbilityFactory.getInstance().create(AbilityType.DoubleAccelAbility, physicalObjects);
        AbilityEngine.getInstance().activateAbility(ability);
    }

    public void hollowPurple() {
        Ability ability = AbilityFactory.getInstance().create(AbilityType.HollowPurpleAbility, physicalObjects);
        AbilityEngine.getInstance().activateAbility(ability);
    }

    public void setStickToPaddle(Boolean value) {
        ball.setStickToPaddle(value);
    }

    public void shootBall() {
        if (isBallStickToPaddle()) {
            ball.shoot();
            setStickToPaddle(false);
        }
    }

    public Boolean isBallStickToPaddle() {
        return ball.getStickToPaddle();
    }

    public void loseChance() {
        int chance = player.getLives();
        player.setLives(--chance);
        if (chance <= 0) {
            this.getService(1).perform(null);
        } else {
            paddle.getBoundingBox().shift(new Vector(size.getX()/2 - (paddle.getWidth()/2),size.getY()-100).subtract(paddle.getLocation()));
            paddle.setLocation(new Vector(size.getX()/2 - (paddle.getWidth()/2),size.getY()-100));
            ball.getBoundingBox().shift(new Vector(size.getX()/2 - 12.5,size.getY()-126).subtract(ball.getLocation()));
            ball.setLocation(new Vector(size.getX()/2 - 12.5,size.getY()-126));
            ball.setMovementBehavior(paddle.getMovementBehavior());
            ball.setStickToPaddle(true);
        }
    }

    public void increaseChance() {
        int chance = player.getLives();
        player.setLives(++chance);
    }

    public int getTime(){
        return (int) (time_milliseconds * 0.0016);
    }

    public boolean isValidInventory() {
    	obstacleInventory.put(ObstacleType.SimpleObstacle,0);
    	obstacleInventory.put(ObstacleType.FirmObstacle,0);
    	obstacleInventory.put(ObstacleType.ExplosiveObstacle,0);
    	obstacleInventory.put(ObstacleType.GiftObstacle,0);
    	for (PhysicalObject object : physicalObjects) {
    		if (object instanceof SimpleObstacle) {
    	    	obstacleInventory.put(ObstacleType.SimpleObstacle,
    	    			obstacleInventory.getOrDefault(ObstacleType.SimpleObstacle, 0) + 1);
    		}
    		if (object instanceof FirmObstacle) {
    			obstacleInventory.put(ObstacleType.FirmObstacle,
    	    			obstacleInventory.getOrDefault(ObstacleType.FirmObstacle, 0) + 1);

    		}
    		if (object instanceof ExplosiveObstacle) {
    			obstacleInventory.put(ObstacleType.ExplosiveObstacle,
    	    			obstacleInventory.getOrDefault(ObstacleType.ExplosiveObstacle, 0) + 1);

    		}
    		if (object instanceof GiftObstacle) {
    			obstacleInventory.put(ObstacleType.GiftObstacle,
    	    			obstacleInventory.getOrDefault(ObstacleType.GiftObstacle, 0) + 1);
    		}
    	}
    	/*REQUIREMENTS:
    	 * Simple >= 75
    	 * FIRM >= 10
    	 * EXPLOSIVE >= 5
    	 * GIFT >= 10
    	 */
    	if (obstacleInventory.get(ObstacleType.SimpleObstacle) >= 75 &&
    			obstacleInventory.get(ObstacleType.FirmObstacle) >= 10 &&
    			obstacleInventory.get(ObstacleType.ExplosiveObstacle) >= 5 &&
    			obstacleInventory.get(ObstacleType.GiftObstacle) >= 10) {
    		return true;
    	}

    	return false;
    }

    public void randomGame() {
    	double X = this.getSize().getX();
    	double Y = this.getSize().getY();
    	int MAX_X =(int) (50*(int)(X/50)- (int)(50/2));
    	int MAX_Y = /*(int) (50*(int)(Y/50)- (int)(Y/5));*/(int) paddle.getLocation().getY()-50;
    	int random_x, random_y;
    	int simpleCounter=0, firmCounter=0, explosiveCounter=0, giftCounter = 0;

    	while (simpleCounter< simpleObstacleMin){
    		random_x = (int) (50*(int)(((Math.random() * (MAX_X - 20)) + 20)/50)- (int)(40/20));
        	random_y = (int) (50*(int)(((Math.random() * (MAX_Y - (Y/6))) + (Y/6))/50))- (int) (40/20);
        	ArrayList<Integer> coord = new ArrayList<Integer>();
    		coord.add(random_x); coord.add(random_y);

    		if (gameGrid.get(coord) == null) {
    			gameGrid.put(coord, 1);
    			addObstacle(ObstacleType.SimpleObstacle, new Vector(random_x, random_y));
    			simpleCounter ++;
    		}

    	}
    	while (firmCounter< firmObstacleMin){
    		random_x = (int) (50*(int)(((Math.random() * (MAX_X - 20)) + 20)/50)- (int)(40/20));
        	random_y = (int) (50*(int)(((Math.random() * (MAX_Y - (Y/6))) + (Y/6))/50)- (int)(40/20));
        	ArrayList<Integer> coord = new ArrayList<Integer>();
    		coord.add(random_x); coord.add(random_y);

    		if (gameGrid.get(coord) == null) {
    			gameGrid.put(coord, 1);
    			addObstacle(ObstacleType.FirmObstacle, new Vector(random_x, random_y));
    			firmCounter ++;
    		}

    	}
    	while (explosiveCounter< explosiveObstacleMin){
    		random_x = (int) (50*(int)(((Math.random() * (MAX_X - 20)) + 20)/50)- (int)(40/20));
        	random_y = (int) (50*(int)(((Math.random() * (MAX_Y - (Y/6))) + (Y/6))/50)- (int)(40/20));
        	ArrayList<Integer> coord = new ArrayList<Integer>();
    		coord.add(random_x); coord.add(random_y);

    		if (gameGrid.get(coord) == null) {
    			gameGrid.put(coord, 1);
    			addObstacle(ObstacleType.ExplosiveObstacle, new Vector(random_x, random_y));
    			explosiveCounter ++;
    		}

    	}
    	while (giftCounter< giftObstacleMin){
    		random_x = (int) (50*(int)(((Math.random() * (MAX_X - 20)) + 20)/50)- (int)(40/20));
        	random_y = (int) (50*(int)(((Math.random() * (MAX_Y - (Y/6))) + (Y/6))/50)- (int)(40/20));
        	ArrayList<Integer> coord = new ArrayList<Integer>();
    		coord.add(random_x); coord.add(random_y);

    		if (gameGrid.get(coord) == null) {
    			gameGrid.put(coord, 1);
    			addObstacle(ObstacleType.GiftObstacle, new Vector(random_x, random_y));
    			giftCounter ++;
    		}

    	}



    }



    public int getLives() {
    	return player.getLives();

    }

    public double getScore() {
    	return player.getScore();

    }


    public void setTime(int time) {
        this.time_milliseconds = time;
    }
}
