package prototype;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Zauber {
	private BufferedImage bimg;
	private float f_Zaubergeschwindigkeitx;
	private float f_Zaubergeschwindigkeity;
	private Rectangle bounding;
	private List<Zauber> Zaubern;
	private float f_playposx;
	private float f_playposy;
	private float existiertseit;
	private float darfexistieren;
	private int zauberid;
	
	public Zauber(float x, float y, float speedx, float speedy,int Zauberid, List<Zauber> Zaubern){
		this.zauberid=Zauberid;
		if (zauberid==1){
		try {
			bimg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/Zauber.png"));
		} catch (IOException e) {e.printStackTrace();}
		darfexistieren=(float)0.5;
		}
		if (zauberid==2){
		try {
			bimg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/circle.png"));
		} catch (IOException e) {e.printStackTrace();}
		darfexistieren=(float)8;
		}
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
	
	public BufferedImage getLook(){
		return bimg;
	}
	public int getX(){
		return (int)f_playposx;
	}
	
	public int getY(){
		return (int)f_playposy;
	}
	public int getid(){
		return (int)zauberid;
	}
}