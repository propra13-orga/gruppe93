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
	private boolean walkover;
	private boolean killYou = false;
	
	
	
	private static BufferedImage err;
	private static BufferedImage floor;
	private static BufferedImage wall;
	private static BufferedImage trap;
	private static BufferedImage exit;
	private static BufferedImage tot;
	
	
	// Texturen laden
	static{
		try{
			err = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglyerr.png"));
			floor = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglyfloor.png"));
			wall = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglywall.png"));
			trap = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglytrap.png"));
			exit = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglyexit.png"));
			tot = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/tot.png"));
			
			
			}catch (IOException e) {e.printStackTrace();}
		
	}
	
	public Tile(int x,int y, boolean calcBounding, int textur){
		this.posx=x;
		this.posy=y;
		this.walkover=calcBounding;
		this.textur=textur;
			
		bounding = new Rectangle(posx, posy, feldGröße, feldGröße);
		
		}
	
	public static BufferedImage getLook(int tex){
		switch(tex){
		case 1:
			return floor;
		case 2:
			return wall;
		case 3:
			return trap;
		case 4:
			return exit;
		case 5:
			return tot;
		default:
			return err;
		
		
				
		}
	}
	
	public void setTex(int tex){
		textur = tex;
	}
	
	public void setTrap(){
		killYou = true;
		textur = 3;		
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
	
	public boolean getWalkOver(){
		return walkover;		
	}
	
	public boolean getKillYou(){
		return killYou;
	}
	
}
