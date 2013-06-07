package prototype;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Zauber {
	private static BufferedImage bimg;
	private static BufferedImage bimg2;
	private static BufferedImage bimg3;
	private float f_Zaubergeschwindigkeitx;
	private float f_Zaubergeschwindigkeity;
	private Rectangle bounding;
	private List<Zauber> Zaubern;
	private float f_playposx;
	private float f_playposy;
	private float existiertseit;
	private float darfexistieren;
	private int zauberid;
	static {
		try {
			bimg = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/Zauber.png"));
			bimg2 = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/circle.png"));
			bimg3 = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/Dragoran/Dragoranspell3.png"));
			} catch (IOException e) {e.printStackTrace();}
        }
	

	
	public Zauber(float x, float y, float speedx, float speedy,int Zauberid, List<Zauber> Zaubern){
		this.zauberid=Zauberid;
		if (zauberid==1){
		darfexistieren=(float)0.5;
		bounding = new Rectangle((int)x, (int)y, bimg.getWidth(), bimg.getHeight());
		}
		if (zauberid==2){
		darfexistieren=(float)8;
		bounding = new Rectangle((int)x, (int)y, bimg2.getWidth(), bimg2.getHeight());
		}
		if (zauberid==3){
		darfexistieren=(float)2;
		bounding = new Rectangle((int)x-5, (int)y-5, bimg3.getWidth()-10, bimg3.getHeight()-10);
		}
		this.f_playposx = x;
		this.f_playposy = y;
		this.f_Zaubergeschwindigkeitx = speedx;
		this.f_Zaubergeschwindigkeity = speedy;
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
		if (zauberid==3){
			return bimg3;
		}
		if (zauberid==1){
		return bimg;
		}
	
	    return bimg2;

	
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