package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Teleporter extends TileType{

	public Teleporter(int positionX, int positionY) {
		super(positionX, positionY);
		try {
			bimg = ImageIO.read(Teleporter.class.getClassLoader().getResourceAsStream("gfx/Tiles/teleporter.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
