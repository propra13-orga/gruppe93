package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Boden extends TileType{

	public Boden (int positionX, int positionY) {
		super(positionX, positionY);
		try {
			bimg = ImageIO.read(Boden.class.getClassLoader().getResourceAsStream("gfx/Tiles/floor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
