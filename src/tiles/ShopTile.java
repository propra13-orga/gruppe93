package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ShopTile extends TileType{

	public ShopTile(int positionX, int positionY) {
		super(positionX, positionY);
		try {
			bimg = ImageIO.read(ShopTile.class.getClassLoader().getResourceAsStream("gfx/Tiles/shop.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
