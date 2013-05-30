package prototype;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class Gegner {
	private static BufferedImage bimg;
	private float f_Gegnerposy_x;
	private float f_Gegnerposy_y;
	private Rectangle bounding;
	private List<Gegner> Enemys;
	private float existiertseit;
	private final float darfexistieren = 60;;
	private float entfernung; //entfernung zwischen Gegner und Spieler
	private int gegnergeschwindigkeit=300;
	
	
	public Gegner( float Gegnerx, float Gegnery, List<Gegner> Enemys){
		try {
			bimg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/gengar.png"));
		} catch (IOException e) {e.printStackTrace();}
		
		this.f_Gegnerposy_x = Gegnerx;
		this.f_Gegnerposy_y = Gegnery;
		bounding = new Rectangle((int)Gegnerx, (int)Gegnery, bimg.getWidth(), bimg.getHeight());
		this.Enemys = Enemys;
	}
	
	public void update(float timeSinceLastFrame){
		existiertseit+=timeSinceLastFrame;
		if(existiertseit>darfexistieren){
			Enemys.remove(0);
		}
		entfernung=(float) Math.sqrt((Player.getBounding().x-f_Gegnerposy_x)*(Player.getBounding().x-f_Gegnerposy_x)+(Player.getBounding().y-f_Gegnerposy_y)*(Player.getBounding().y-f_Gegnerposy_y));
	   
	    
		f_Gegnerposy_x=f_Gegnerposy_x+(Player.getBounding().x-f_Gegnerposy_x)/entfernung*timeSinceLastFrame*gegnergeschwindigkeit;
		f_Gegnerposy_y=f_Gegnerposy_y+(Player.getBounding().y-f_Gegnerposy_y)/entfernung*timeSinceLastFrame*gegnergeschwindigkeit;
		bounding.x = (int)f_Gegnerposy_x;
		bounding.y = (int)f_Gegnerposy_y;
		
		
	}
	
	public Rectangle getBounding(){
		return bounding;
	}
	
	public static BufferedImage getLook(){
		return bimg;
	}
}