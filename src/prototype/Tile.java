package prototype;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	
	private int posx;
	private int posy;
	private static short feldGröße=40;
	private int textur;
	private Rectangle bounding;
	private boolean calcBounding;  //z.Zt ohne Funktion, soll ünnötige Kolisionsabfragen verhindern
	
	
	
	
	private static BufferedImage floor;
	private static BufferedImage wall;
	private static BufferedImage err;
	
	
	
	// Texturen laden
	static{
		try{
			floor = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglyfloor.png"));
			wall = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglywall.png"));
			err = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglyerr.png"));
			}catch (IOException e) {e.printStackTrace();}
		
	}
	
	public Tile(int x,int y, boolean calcBounding, int textur){
		this.posx=x;
		this.posy=y;
		this.calcBounding=calcBounding;
		this.textur=textur;
			
		
		bounding = new Rectangle(posx, posy, feldGröße, feldGröße); // 40 bei derzeitiger Texturauflösung
		
		}
	

	
	public static BufferedImage getLook(int tex){
		switch(tex){
		case 1:
			return floor;
		case 2:
			return wall;
		default:
			return err;
		
		
				
		}
	}
	
	public int getTex(){
		return textur;
	}
	
	public Rectangle getBounding(){
		return bounding;
	}
	
	public static int getFeldGröße(){
		return feldGröße;
	}
	

}
