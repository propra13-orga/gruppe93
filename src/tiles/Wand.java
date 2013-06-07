package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import prototype.Tile;

public class Wand extends TileType{

	public Wand(int positionX, int positionY) {
		super(positionX, positionY);
		isBlockiert = false;
		try {
			bimg = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/err.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
