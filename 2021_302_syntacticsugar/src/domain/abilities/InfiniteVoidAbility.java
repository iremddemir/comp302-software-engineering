package domain.abilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import domain.physicalobjects.Paddle;
import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.obstacles.Obstacle;

import static java.util.stream.Collectors.toCollection;

public class InfiniteVoidAbility extends Ability {
	int OBSTACLE_COUNT_CONSTANT = 8;
	private List<PhysicalObject> obstacleList;

	public InfiniteVoidAbility(ArrayList<PhysicalObject> physicalObjectsList) {
		super(physicalObjectsList);
	}

	public void perform() {
		obstacleList = getPhysicalObjectsList().stream().filter(physicalObject -> physicalObject instanceof Obstacle)
																 .collect(Collectors.toList());

		Collections.shuffle(obstacleList);

		for (int i=0; i< Math.min(OBSTACLE_COUNT_CONSTANT, obstacleList.size()); i++){
			((Obstacle) obstacleList.get(i)).setInvincible(true);
		}
	}

	@Override
	public void revert() {
		for (int i=0; i< Math.min(OBSTACLE_COUNT_CONSTANT, obstacleList.size()); i++){
			((Obstacle) obstacleList.get(i)).setInvincible(false);
		}
	}

}
