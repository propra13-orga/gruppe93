package prototype;



import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	
	private Rectangle bounding;
	private float f_playposx;
	private float f_playposy;
	private int worldsize_x;
	private int worldsize_y;
	private BufferedImage play;
	
	
	public Player(int x, int y, int worldsize_x, int worldsize_y){
		try {
			play = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/ugly.png"));
		} catch (IOException e) {e.printStackTrace();}
		bounding = new Rectangle(x,y,play.getWidth(),play.getHeight());
		f_playposx = x;
		f_playposy = y;
		this.worldsize_x=worldsize_x;
		this.worldsize_y=worldsize_y;
		
	}
	
	public void update(float frametime){
		if(Keyboard.isKeyDown(KeyEvent.VK_W))f_playposy -= 250*frametime;
		if(Keyboard.isKeyDown(KeyEvent.VK_S))f_playposy += 250*frametime;
		if(Keyboard.isKeyDown(KeyEvent.VK_A))f_playposx -= 250*frametime;
		if(Keyboard.isKeyDown(KeyEvent.VK_D))f_playposx += 250*frametime;
		
		if(f_playposx<0)f_playposx=0;
		if(f_playposy<0)f_playposy=0;
		if(f_playposx>worldsize_x - bounding.width)f_playposx=worldsize_x - bounding.width;
		if(f_playposy>worldsize_y - bounding.height)f_playposy=worldsize_y - bounding.height;
		
		bounding.x = (int) f_playposx;
		bounding.y = (int) f_playposy;
		
		
		
	}
	
	public Rectangle getBounding(){
		return bounding;
	}
	
	public BufferedImage getplay(){
		return play;
	}

}
