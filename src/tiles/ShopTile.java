package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import prototype.Tile;

public class Shop extends TileType{

	public Shop(int positionX, int positionY) {
		super(positionX, positionY);
		try {
			bimg = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/shop.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
