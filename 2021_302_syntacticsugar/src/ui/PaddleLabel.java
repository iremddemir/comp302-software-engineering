package ui;

import domain.physicalobjects.*;
import domain.physicalobjects.behaviors.movement.PaddleMovementBehavior;
import domain.physicalobjects.boundingbox.PolygonBoundingBox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import java.util.Arrays;
import java.util.Objects;

public class PaddleLabel extends PhysicalObjectLabel{

    public PaddleLabel(Paddle paddle){
        super(paddle);

        try{
            setImage(ImageIO.read(this.getClass().getResource(PATHS.PADDLE_IMG_PATH)));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void paint(Graphics g) {
        //Very bad practice, but we didn't have any time
        Paddle paddle = (Paddle) getPhysicalObject();
        Vector location = paddle.getLocation();

        BufferedImage image = getImage();

        double rads = ((PaddleMovementBehavior)paddle.getMovementBehavior()).getRotation();
        double sin = Math.abs(Math.sin(rads));
        double cos = Math.abs(Math.cos(rads));

        int w = (int) Math.floor(paddle.getWidth() * cos + paddle.getHeight() * sin);
        int h = (int) Math.floor(paddle.getHeight() * cos + paddle.getWidth() * sin);

        BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        AffineTransform at = new AffineTransform();

        at.rotate(rads,w/2, h/2);
        at.scale(paddle.getWidth()/image.getWidth(), paddle.getHeight()/image.getHeight());
        //at.translate(-paddle.getWidth() , -paddle.getHeight() );

        AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedImage);

        g.drawImage(rotatedImage
                ,(int) paddle.getLocation().getX(), (int) paddle.getLocation().getY() , w, h, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });

        int[] xPoints = Arrays.stream(((PolygonBoundingBox) paddle.getBoundingBox()).getPoints()).mapToInt(value -> (int) value.getX()).toArray();
        int[] yPoints = Arrays.stream(((PolygonBoundingBox) paddle.getBoundingBox()).getPoints()).mapToInt(value -> (int) value.getY()).toArray();
        g.setColor(Color.CYAN);
        g.drawPolygon(xPoints, yPoints, xPoints.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhysicalObjectLabel that = (PhysicalObjectLabel) o;
        return Objects.equals(getPhysicalObject(), that.getPhysicalObject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhysicalObject());
    }
}
