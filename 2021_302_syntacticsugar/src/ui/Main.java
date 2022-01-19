package ui;

import domain.Game;
import domain.physicalobjects.Vector;
import domain.physicalobjects.boundingbox.PolygonBoundingBox;
import domain.physicalobjects.obstacles.FirmObstacle;
import domain.physicalobjects.obstacles.Obstacle;

import java.awt.*;
import java.util.Arrays;

public class Main{

    public static void main(String[] args) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dim.getWidth();
        int height = (int) dim.getHeight();
        TitleScreen titleScreen = new TitleScreen(width, height);

        Game game = Game.getInstance();
    }
}
