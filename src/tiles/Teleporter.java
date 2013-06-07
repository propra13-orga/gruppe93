package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import prototype.Tile;

public class Teleporter extends TileType{

	public Teleporter(int positionX, int positionY) {
		super(positionX, positionY);
		try {
			bimg = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/teleporter.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
