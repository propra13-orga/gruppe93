package prototype;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tiles {
	
	private int posx;
	private int posy;
	private int textur;
	private Rectangle bounding;
	private boolean calcBounding;  //z.Zt ohne Funktion, soll ünnötige Kolisionsabfragen verhindern
	private BufferedImage look;
	
	
	
	private static BufferedImage floor;
	private static BufferedImage wall;
	
	
	
	// Texturen laden
	static{
		try{
			floor = ImageIO.read(Tiles.class.getClassLoader().getResourceAsStream("gfx/uglyfloor.png"));
			wall = ImageIO.read(Tiles.class.getClassLoader().getResourceAsStream("gfx/uglywall.png"));
			}catch (IOException e) {e.printStackTrace();}
		
	}
	
	public Tiles(int x,int y, boolean calcBounding, int textur){
		this.posx=x;
		this.posy=y;
		this.calcBounding=calcBounding;
		switch(textur){
		case 1:
			look = floor;
			break;
		case 2:
			look = wall;
			break;
			// Bei bedarf neue cases hinzufügen.	
			
		}
		bounding = new Rectangle(posx, posy, look.getWidth(), look.getHeight());
		
		}
	

	
	public BufferedImage getLook(){
		return look;
	}
	
	public Rectangle getBounding(){
		return bounding;
	}
	

}
