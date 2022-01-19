package domain.abilities;

import domain.Constants;
import domain.physicalobjects.Paddle;
import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.boundingbox.PolygonBoundingBox;

public class PaddleExpansionAbility extends Ability {

	public PaddleExpansionAbility(PhysicalObject appliesTo) {
		super(appliesTo);
	}

	@Override
	public void perform() {
		Paddle paddle = (Paddle) getAppliesTo();

		paddle.setWidth(Constants.PADDLE_LENGTH *2);
		paddle.setBoundingBox(PolygonBoundingBox.createRectangleBoundingBox(paddle.getLocation(), paddle.getWidth(), paddle.getHeight()));
	}

	@Override
	public void revert() {
		Paddle paddle = (Paddle) getAppliesTo();

		paddle.setWidth(Constants.PADDLE_LENGTH);
		paddle.setBoundingBox(PolygonBoundingBox.createRectangleBoundingBox(paddle.getLocation(), paddle.getWidth(), paddle.getHeight()));
	}


}
