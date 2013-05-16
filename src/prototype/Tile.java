package prototype;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	
	private int posx;
	private int posy;
	private static short feldGroesse=40;
	private int textur;
	private Rectangle bounding;
	private boolean blockiert;	//in blockiert umbenannt zur besseren verständlichkeit. vorher war walkover= true wenn man nicht drüber walken konnte
	private boolean killYou = false;
	private boolean isTeleporter = false;
	
	
	
	private static BufferedImage err;
	private static BufferedImage floor;
	private static BufferedImage wall;
	private static BufferedImage trap;
	private static BufferedImage exit;
	private static BufferedImage tot;
	private static BufferedImage tot2;
	private static BufferedImage teleporter;
	
	
	// Texturen laden
	static{
		try{
			err = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglyerr.png"));
			floor = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglyfloor.png"));
			wall = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglywall.png"));
			trap = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglytrap.png"));
			exit = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglyexit.png"));
			tot = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/tot.png"));
			tot2 = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/tot2.png"));
			teleporter = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/uglyteleporter.png"));
			
			
			}catch (IOException e) {e.printStackTrace();}
		
	}
	
	
	//KONSTRUKTOR
	public Tile(int x,int y, boolean blockiert, int textur){
		this.posx=x;
		this.posy=y;
		this.blockiert=blockiert;
		this.textur=textur;
			
		bounding = new Rectangle(posx, posy, feldGroesse, feldGroesse);
		
	}
	
//	Tiletypechanger
	
	public void setFloor(){
		blockiert = false;
		killYou = false;
		isTeleporter = false;
		textur = 1;
	}
	
	public void setTrap(){
		killYou = true;
		blockiert = false;
		isTeleporter = false;
		textur = 3;		
	}
	
	public void setTeleporter(){
		isTeleporter = true;
		killYou = false;
		blockiert = false;
		textur = 7; 
	}
	
	public void setWall(){
		isTeleporter = false;
		killYou = false;
		blockiert = true;
		textur = 2;
	}
	
	public void setErr(){
		isTeleporter = false;
		killYou = false;
		blockiert = false;
		textur = 0;
	}
	
	//Getter und Setter
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
		case 6:
			return tot2;
		case 7:
			return teleporter;
		default:
			return err;			
		}
	}
	
	public void setTex(int tex){
		textur = tex;
	}
	
	public int getTex(){
		return textur;
	}
	
	public void setKillYou(boolean killYou){
		this.killYou=killYou;
	}
	
	public boolean getKillYou(){
		return killYou;
	}
	
	public void setBlockiert(boolean blockiert){
		this.blockiert=blockiert;
	}
	
	public boolean getBlockiert(){
		return blockiert;
	}
	
	public void setIsTeleporter(boolean isTeleporter){
		this.isTeleporter = isTeleporter;
	}
	
	public boolean getIsTeleporter(){
		return isTeleporter;
	}
		
	public Rectangle getBounding(){
		return bounding;
	}
	
	public static int getFeldGroesse(){
		return feldGroesse;
	}
}
