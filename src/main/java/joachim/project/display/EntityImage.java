package joachim.project.display;

import java.awt.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This class represents an image that is to be drawn on the screen. It holds
 * an Image class that is needed to be painted by the Screen/Canvas and also the
 * details of the picture, like the coordinates it is to be painted and its dimensions.
 */
public class EntityImage{
    private String id;
    private Image image;
    private int x;
    private int y;
    private int width;
    private int height;
    Toolkit t = Toolkit.getDefaultToolkit();
    public EntityImage(String filename, int start_x, int start_y, int width, int height, String id){
        image = t.getImage(filename);
        x = start_x;
        y = start_y;
        this.width = width;
        this.height = height;
        this.id =id;

        // Load and resize the image
        Image originalImage = image;

        this.image = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getId() {
        return id;
    }

    /**
     * This method is to be called whenever the image is to be changed. It takes the filename of the new image
     * and uses the toolkit to load it in and resize it to the dimensions of the old image.
     * @param filename
     */
    public void change_image(String filename){
        image = t.getImage(filename);
        Image originalImage = image;
        this.image = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public void setId(String id) {
        this.id = id;
    }
}