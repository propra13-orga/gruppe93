package tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wand extends TileType{
	protected static BufferedImage bimg;
	

	public Wand(int positionX, int positionY) {
		super(positionX, positionY);
		isBlockiert = true;
		try {
			bimg = ImageIO.read(Wand.class.getClassLoader().getResourceAsStream("gfx/Tiles/wall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getBimg() {
		return bimg;
	}
}
