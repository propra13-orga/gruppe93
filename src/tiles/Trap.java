package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Trap extends TileType{

	public Trap(int positionX, int positionY) {
		super(positionX, positionY);
		isTrap=true;
		try {
			bimg = ImageIO.read(Trap.class.getClassLoader().getResourceAsStream("gfx/Tiles/trap.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
