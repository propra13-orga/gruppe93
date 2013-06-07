package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import prototype.Tile;

public class ShopTile extends TileType{

	public ShopTile(int positionX, int positionY) {
		super(positionX, positionY);
		try {
			bimg = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/shop.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
