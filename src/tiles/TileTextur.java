package tiles;

/*
 * Texturenspeicher fuer Felder
 */

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

class TileTextur {

	private static BufferedImage err;
	private static BufferedImage boden;
	private static BufferedImage wand;
	private static BufferedImage trap;
	private static BufferedImage exit;
	private static BufferedImage tot;
	private static BufferedImage tot2;
	private static BufferedImage teleporter;
	private static BufferedImage pokal;
	private static BufferedImage shop;
	
	static{
		try{
			err = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/err.png"));
			boden = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/floor.png"));
			wand = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/wall.png"));
			trap = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/trap.png"));
			exit = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/exit.png"));
			tot = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/tot.png"));
			tot2 = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/tot2.png"));
			teleporter = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/teleporter.png"));
			pokal = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/pokal.png"));
			shop = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/shop.png"));

			}catch (IOException e) {e.printStackTrace();}

	}
	
	//Rueckgabe der Texturen and Frame
	
	static BufferedImage getTextur(int TileType){
		switch(TileType){
			case 1:
				return boden;
			case 2:
				return wand;
			case 3:
				return trap;
			case 4:
				return teleporter;
			case 5:
				return shop;
			case 6:
				return exit;
			case 7:
				return pokal;
				
			case 101:
				return tot;
			case 102:
				return tot2;
				
			default:
				return err;
		}
		
	}

}
