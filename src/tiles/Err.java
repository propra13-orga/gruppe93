package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Err extends TileType{

	public Err(int positionX, int positionY) {
		super(positionX, positionY);
		try {
			bimg = ImageIO.read(Err.class.getClassLoader().getResourceAsStream("gfx/Tiles/err.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
