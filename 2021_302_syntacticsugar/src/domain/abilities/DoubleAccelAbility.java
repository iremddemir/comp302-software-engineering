package domain.abilities;

import java.util.ArrayList;

import domain.Constants;
import domain.physicalobjects.Ball;
import domain.physicalobjects.PhysicalObject;

public class DoubleAccelAbility extends Ability {

	public DoubleAccelAbility(PhysicalObject appliesTo) {
		super(appliesTo);
	}

	@Override
	public void perform() {
		Ball ball = (Ball) getAppliesTo();
		ball.setSpeed(ball.getSpeed().scale(1.0/2));
	}

	@Override
	public void revert() {
		Ball ball = (Ball) getAppliesTo();
		ball.setSpeed(ball.getSpeed().scale(2));
	}
}
