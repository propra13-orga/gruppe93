package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import prototype.Tile;

public class Trap extends TileType{

	public Trap(int positionX, int positionY) {
		super(positionX, positionY);
		try {
			bimg = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/err.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
