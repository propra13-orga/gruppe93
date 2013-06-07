package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import prototype.Tile;

public class Boden extends TileType{

	public Boden(int positionX, int positionY) {
		super(positionX, positionY);
		try {
			bimg = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/floor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
