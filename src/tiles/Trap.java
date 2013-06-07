package tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Trap extends TileType{
	protected static BufferedImage bimg;

	public Trap(int positionX, int positionY) {
		super(positionX, positionY);
		isTrap=true;
		try {
			bimg = ImageIO.read(Trap.class.getClassLoader().getResourceAsStream("gfx/Tiles/trap.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getBimg() {
		return bimg;
	}
	

}
