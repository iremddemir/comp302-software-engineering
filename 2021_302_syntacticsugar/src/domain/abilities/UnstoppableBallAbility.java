package domain.abilities;

import domain.Constants;
import domain.physicalobjects.Ball;
import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.behaviors.collision.BallCollisionBehavior;
import domain.physicalobjects.behaviors.collision.UnstoppableBallCollisionBehavior;

public class UnstoppableBallAbility extends Ability {

    public UnstoppableBallAbility(PhysicalObject appliesTo) {
        super(appliesTo);
    }

    @Override
    public void perform() {
        Ball ball = (Ball) getAppliesTo();
        ball.setCollisionBehavior(new UnstoppableBallCollisionBehavior());
        ball.setAttackDamage(Constants.BALL_ATTACK_DAMAGE*100);
    }

    @Override
    public void revert() {
        Ball ball = (Ball) getAppliesTo();
        ball.setCollisionBehavior(new BallCollisionBehavior());
        ball.setAttackDamage(Constants.BALL_ATTACK_DAMAGE);
    }
}
