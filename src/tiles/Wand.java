package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Wand extends TileType{

	public Wand(int positionX, int positionY) {
		super(positionX, positionY);
		isBlockiert = false;
		try {
			bimg = ImageIO.read(Wand.class.getClassLoader().getResourceAsStream("gfx/Tiles/wall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
