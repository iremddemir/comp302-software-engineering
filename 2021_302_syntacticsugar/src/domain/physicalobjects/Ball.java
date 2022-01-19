package domain.physicalobjects;
import domain.Constants;
import domain.physicalobjects.behaviors.collision.Collision;
import domain.physicalobjects.behaviors.collision.CollisionBehavior;
import domain.physicalobjects.behaviors.movement.MovementBehavior;
import domain.physicalobjects.boundingbox.SphereBoundingBox;
import domain.physicalobjects.behaviors.collision.BallCollisionBehavior;
import domain.physicalobjects.behaviors.movement.BallMovementBehavior;


public class Ball extends PhysicalObject{

	private int attackDamage;
	private Boolean stickToPaddle;

    public Ball(Vector location, double width, double height ){
        super(location, width, height,
				new SphereBoundingBox(location.add(new Vector(width/2, width/2)), width/2 )
				, new BallMovementBehavior(), new BallCollisionBehavior(), null);

		attackDamage = Constants.BALL_ATTACK_DAMAGE;
		stickToPaddle = true;
    }

	public Ball(Vector location, double width, double height, MovementBehavior movementBehavior, CollisionBehavior collisionBehavior){
		super(location, width, height,
				new SphereBoundingBox(location.add(new Vector(width/2, width/2)), width/2 )
				, movementBehavior, collisionBehavior, null);

		attackDamage = Constants.BALL_ATTACK_DAMAGE;
		stickToPaddle = true;
	}

	public Vector getSpeed() {
		return  getMovementBehavior().getSpeed();
	}
	public void setSpeed(Vector speed) {
		getMovementBehavior().setSpeed(speed);
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getAttackDamage(){
		return this.attackDamage;
	}
	public Boolean getStickToPaddle() {
		return this.stickToPaddle;
	}

	public void shoot(){
		this.setMovementBehavior(new BallMovementBehavior());
		this.setCollisionBehavior(new BallCollisionBehavior());
		this.setSpeed(Constants.BALL_SPEED);
		this.setStickToPaddle(false);
	}

	public void setStickToPaddle(Boolean value) {
		this.stickToPaddle = value;
	}
}
