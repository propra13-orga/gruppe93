package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import prototype.Tile;

public class Exit extends TileType{

	public Exit(int positionX, int positionY) {
		super(positionX, positionY);
		try {
			bimg = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/Exit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
