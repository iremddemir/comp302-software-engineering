package domain.abilities;

import java.util.ArrayList;
import java.util.Random;

import domain.Constants;
import domain.Game;
import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.Vector;
import domain.physicalobjects.obstacles.Obstacle;
import domain.physicalobjects.obstacles.ObstacleFactory;
import domain.physicalobjects.obstacles.ObstacleType;
import domain.services.GameBoardServiceFactory;

public class HollowPurpleAbility extends Ability {

	public HollowPurpleAbility(ArrayList<PhysicalObject> physicalObjectsList) {
		super(physicalObjectsList);
	}

	@Override
	public void perform() {
		for (int i = 0; i< Constants.HOLLOW_OBSTACLE_NUMBER; i++){

			Vector size = Game.getInstance().getGameBoard().getSize();
			double X = size.getX();
	    	double Y = size.getY();
	    	int MAX_X =(int) (50*(int)(X/50)- (int)(50/2));
	    	int MAX_Y = /*(int) (50*(int)(Y/50)- (int)(Y/5));*/(int) Y-200;
	    	int random_x = (int) (50*(int)(((Math.random() * (MAX_X - 20)) + 20)/50)- (int)(40/20));
        	int random_y = (int) (50*(int)(((Math.random() * (MAX_Y - 200)) + 200)/50));
			Vector location = new Vector(random_x, random_y);

			Obstacle obstacle = Game.getInstance().getGameBoard().addObstacle(ObstacleType.HollowObstacle, location);

			Game.getInstance().getGameBoard().getPhysicalObjects().add(obstacle);
			obstacle.getService(1).perform(obstacle);
		}

	}

	@Override
	public void revert() {

	}
}
