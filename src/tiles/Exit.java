package tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Exit extends TileType{

	public Exit(int positionX, int positionY) {
		super(positionX, positionY);
		isExit=true;
		try {
			bimg = ImageIO.read(Exit.class.getClassLoader().getResourceAsStream("gfx/Tiles/exit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
