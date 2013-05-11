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
	private short kartenPositionX;	//enstpricht dem Feld auf der Map. Zur �berpr�fung welche Felder auf Kollision gepr�ft werden
	private short kartenPositionY;
	private float speedX;
	private float speedY;
	private float speedReductionRate=10;
	private int worldsize_x;
	private int worldsize_y;
	private Map map;
	private BufferedImage bimg;
	
	
	public Player(int x, int y, int worldsize_x, int worldsize_y, Map map){
		try {
			bimg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/Rossi.png"));
		} catch (IOException e) {e.printStackTrace();}
		bounding = new Rectangle(x+10,y+10,bimg.getWidth()-20,bimg.getHeight()-20);
		f_playposx = x;
		f_playposy = y;
		this.worldsize_x=worldsize_x;
		this.worldsize_y=worldsize_y;
		this.map=map;
	}
	
	public void update(float frametime){
		if(Keyboard.isKeyDown(KeyEvent.VK_W))speedY -= 50*frametime;
		if(Keyboard.isKeyDown(KeyEvent.VK_S))speedY += 50*frametime;
		if(Keyboard.isKeyDown(KeyEvent.VK_A))speedX -= 50*frametime;
		if(Keyboard.isKeyDown(KeyEvent.VK_D))speedX += 50*frametime;
		
		f_playposy+=speedY;
		f_playposx+=speedX;
		
		if(Math.abs(speedY)<1&&!Keyboard.isKeyDown(KeyEvent.VK_W)&&!Keyboard.isKeyDown(KeyEvent.VK_S)){speedY=0;}else	//Speed geht mit der Zeit wieder runter und unter 1 geht er direkt auf 0
		if(speedY>0){speedY-=speedReductionRate*frametime;}else
		if(speedY<0){speedY+=speedReductionRate*frametime;}
		if(Math.abs(speedX)<1&&!Keyboard.isKeyDown(KeyEvent.VK_A)&&!Keyboard.isKeyDown(KeyEvent.VK_D)){speedX=0;}else
		if(speedX>0){speedX-=speedReductionRate*frametime;}else
		if(speedX<0){speedX+=speedReductionRate*frametime;}
		
		
		if(f_playposx<0){f_playposx=0;speedX=-speedX;}else
		if(f_playposy<0){f_playposy=0;speedY=-speedY;}else
		if(f_playposx>worldsize_x - bounding.width){f_playposx=worldsize_x - bounding.width;speedX=-speedX;}else
		if(f_playposy>worldsize_y - bounding.height){f_playposy=worldsize_y - bounding.height;speedY=-speedY;}
		
		
		
		kartenPositionX=(short)(f_playposx/Tile.getFeldGr��e());
		kartenPositionY=(short)(f_playposy/Tile.getFeldGr��e());
		bounding.x = ((int) f_playposx)-10;	//Aufgrund der Natur des Bilds machen diese einr�ckungen Sinn
		bounding.y = ((int) f_playposy)-10;
	}
	
	public Rectangle getBounding(){
		return bounding;
	}
	
	public BufferedImage getBimg(){
		return bimg;
	}

}
