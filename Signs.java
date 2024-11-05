import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Signs{
    public int x, y, width, height;
    private BufferedImage image;

    public Signs(int x, int y, int width,int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        try {
			this.image = ImageIO.read(new File("Sign.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public BufferedImage getImage() {		
		return image;
	}
}
