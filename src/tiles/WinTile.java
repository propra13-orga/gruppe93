package tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WinTile extends TileType{
	protected static BufferedImage bimg;

	public WinTile(int positionX, int positionY) {
		super(positionX, positionY);
		try {
			bimg = ImageIO.read(WinTile.class.getClassLoader().getResourceAsStream("gfx/Tiles/pokal.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getBimg() {
		return bimg;
	}

}
