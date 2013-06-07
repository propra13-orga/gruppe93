package tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ShopTile extends TileType{
	protected static BufferedImage bimg;

	public ShopTile(int positionX, int positionY) {
		super(positionX, positionY);
		isShop=true;
		try {
			bimg = ImageIO.read(ShopTile.class.getClassLoader().getResourceAsStream("gfx/Tiles/shop.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getBimg() {
		return bimg;
	}
}
