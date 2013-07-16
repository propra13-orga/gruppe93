package multitools;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AntiRossi {
	private static int xPos=100,yPos=100;
	private static BufferedImage bimg;
	private static boolean existiert=true;	//zum testen der malfunktion
	
	public AntiRossi(){
		try {
			bimg= ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/AntiRossi.png"));
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void update(float frametime){
		
	}
	
	public static BufferedImage getBimg(){
		return bimg;
	}
	
	public static int getX(){
		return xPos;
	}
	
	public static int getY(){
		return yPos;
	}
	
	public static boolean getExistiert(){
		return existiert;
	}
}
