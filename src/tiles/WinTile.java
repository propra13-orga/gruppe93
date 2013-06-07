package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import prototype.Tile;

public class WinTile extends TileType{

	public WinTile(int positionX, int positionY) {
		super(positionX, positionY);
		try {
			bimg = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/pokal.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
