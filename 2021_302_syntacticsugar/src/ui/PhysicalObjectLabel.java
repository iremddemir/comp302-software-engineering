package ui;

import domain.physicalobjects.*;
import domain.physicalobjects.obstacles.ExplosiveObstacle;
import domain.physicalobjects.obstacles.FirmObstacle;
import domain.physicalobjects.obstacles.GiftObstacle;
import domain.physicalobjects.obstacles.HollowObstacle;
import domain.physicalobjects.obstacles.SimpleObstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import java.io.IOException;
import java.util.Objects;

public class PhysicalObjectLabel{
    private PhysicalObject physicalObject;
    private BufferedImage image;

    public PhysicalObjectLabel(PhysicalObject physicalObject){
        this.physicalObject = physicalObject;

        String resource;
        if(physicalObject instanceof SimpleObstacle)
            resource = PATHS.SIMPLE_OBSTACLE_IMG_PATH;
        else if(physicalObject instanceof FirmObstacle)
            resource = PATHS.FIRM_OBSTACLE_3_IMG_PATH;
        else if(physicalObject instanceof GiftObstacle)
            resource = PATHS.GIFT_OBSTACLE_IMG_PATH;
        else if(physicalObject instanceof ExplosiveObstacle)
            resource = PATHS.EXPLOSIVE_OBSTACLE_IMG_PATH;
        else if(physicalObject instanceof Ball)
            resource = PATHS.BALL_IMG_PATH;
        else if(physicalObject instanceof ExplosiveFragment)
            resource = PATHS.EXPLOSIVE_FRAGMENT_IMG_PATH;
        else if(physicalObject instanceof HollowObstacle)
            resource = PATHS.HOLLOW_IMG_PATH;
        else
            resource = PATHS.BALL_IMG_PATH;
        try{
            image = ImageIO.read(this.getClass().getResource(resource));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public PhysicalObject getPhysicalObject() {
        return physicalObject;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void paint(Graphics g) {
        //Very bad practice, but we didn't have any time
        if(physicalObject instanceof FirmObstacle){
            int health = ((FirmObstacle) physicalObject).getHealth();
            String resource = "";
            switch (health){
                case 3:
                    resource = PATHS.FIRM_OBSTACLE_3_IMG_PATH;
                    break;
                case 2:
                    resource = PATHS.FIRM_OBSTACLE_2_IMG_PATH;
                    break;
                case 1:
                    resource = PATHS.FIRM_OBSTACLE_1_IMG_PATH;
                    break;
                default:
                    resource = PATHS.FIRM_OBSTACLE_1_IMG_PATH;
                    break;
            }
            try {
                image = ImageIO.read(this.getClass().getResource(resource));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Vector location = physicalObject.getLocation();
        int x = (int) location.getX();
        int y = (int) location.getY();

        g.drawImage(image
                ,x, y, (int) physicalObject.getWidth(), (int) physicalObject.getHeight(), new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
        });
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhysicalObjectLabel that = (PhysicalObjectLabel) o;
        return Objects.equals(physicalObject, that.physicalObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(physicalObject);
    }
}
