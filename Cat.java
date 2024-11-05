import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Cat {
    public int x, y, catSize, health, speed;
    private int upperLane = 360;
    private int lowerLane = 480;
    private BufferedImage[] images;
    private int currentImageIndex = 0;
    private int frameCounter = 0;
    private final int FRAME_DELAY = 10;

    public Cat(int x, int y, int catSize, int health, int speed) {
        this.x = x;
        this.y = y;
        this.catSize = catSize;
        this.health = health;
        this.speed = speed;

        images = new BufferedImage[4];
        try {
            images[0] = ImageIO.read(new File("cat0.png"));
            images[1] = ImageIO.read(new File("cat1.png"));
            images[2] = ImageIO.read(new File("cat2.png"));
            images[3] = ImageIO.read(new File("cat3.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void move() {
        x += speed;
    }

    public BufferedImage getImage() {
        frameCounter++;
        if (frameCounter >= FRAME_DELAY) {
            currentImageIndex = (currentImageIndex + 1) % images.length; // วนภาพ
            frameCounter = 0;
        }
        return images[currentImageIndex];
    }

    public void upperLane() {
        this.y = upperLane;
    }

    public void lowerLane() {
        this.y = lowerLane;
    }
}
