package tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Teleporter extends TileType{
	protected static BufferedImage bimg;

	public Teleporter(int positionX, int positionY) {
		super(positionX, positionY);
		isTeleporter=true;
		try {
			bimg = ImageIO.read(Teleporter.class.getClassLoader().getResourceAsStream("gfx/Tiles/teleporter.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getBimg() {
		return bimg;
	}

}
