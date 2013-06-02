package prototype;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Zauber {
	private static BufferedImage bimg;
	private float f_Zaubergeschwindigkeitx;
	private float f_Zaubergeschwindigkeity;
	private static Rectangle bounding;
	private List<Zauber> Zaubern;
	private float f_playposx;
	private float f_playposy;
	private float existiertseit;
	private final float darfexistieren = 10;
	
	public Zauber(float x, float y, float speedx, float speedy, List<Zauber> Zaubern){
		try {
			bimg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/Zauber.png"));
		} catch (IOException e) {e.printStackTrace();}
		
		this.f_playposx = x;
		this.f_playposy = y;
		this.f_Zaubergeschwindigkeitx = speedx;
		this.f_Zaubergeschwindigkeity = speedy;
		bounding = new Rectangle((int)x, (int)y, bimg.getWidth(), bimg.getHeight());
		this.Zaubern = Zaubern;
	}
	
	public void update(float timeSinceLastFrame){
		existiertseit+=timeSinceLastFrame;
		if(existiertseit>darfexistieren){
			Zaubern.remove(this);
		}
		f_playposx+=f_Zaubergeschwindigkeitx*timeSinceLastFrame;
		f_playposy+=f_Zaubergeschwindigkeity*timeSinceLastFrame;
		bounding.x = (int)f_playposx;
		bounding.y = (int)f_playposy;
	}
	
	public Rectangle getBounding(){
		return bounding;
	}
	
	public static BufferedImage getLook(){
		return bimg;
	}
	public int getX(){
		return (int)f_playposx;
	}
	
	public int getY(){
		return (int)f_playposy;
	}
}