package domain;

import domain.physicalobjects.Vector;

public class Constants {
    public static final Vector GIFT_FRAGMENT_SPEED = new Vector(0,4);

    public static int OBSTACLE_WIDTH = 40;
    public static int OBSTACLE_HEIGHT = 40;
    public static int HOLLOW_OBSTACLE_NUMBER = 8;
    public static int PADDLE_LENGTH = 250;
    public static int ABILITY_TIME = 500;

    public static Vector BALL_SPEED = new Vector(3, -4);
    public static int BALL_ATTACK_DAMAGE = 1;

    public static Vector MAGICAL_HEX_AMMO_SPEED = new Vector(0,-4);
    public static final int MAGICAL_HEX_ATTACK_DAMAGE = 1;

    public static int PLAYER_INITIAL_LIVES = 3;
    public static double PADDLE_ROTATION_SPEED = 0.02;

    public static double MOVING_OBSTACLE_SPEED = 0.2;
}


