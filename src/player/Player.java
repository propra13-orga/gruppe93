package player;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import prototype.Map;
import prototype.Gegner;
import prototype.Zauber;

public class Player {
	
	private static float f_PlayerPositionX;
	private static float f_PlayerPositionY;
	private static float f_leben;
	private static float f_mana;
	private static float f_geld;
	private static Rectangle bounding;
	private static BufferedImage bimg;
	
	private static Map map;
	private static List<Zauber>Zaubern;
	private static List<Gegner>Enemys;
	
	public Player(int PositionX, int PositionY, Map map, List<Zauber> Zaubern, List<Gegner> Enemys) {
		
		try {
			bimg= ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/Rossi.png"));
		} catch (IOException e) {e.printStackTrace();}
		
		Player.f_PlayerPositionX = PositionX;
		Player.f_PlayerPositionY = PositionY;
		Player.bounding = new Rectangle(PositionX+10,PositionY+10,bimg.getWidth()-20,bimg.getHeight()-20);
		
		Player.map = map;
		Player.Enemys = Enemys;
		Player.Zaubern = Zaubern;
		

	}
		
	/**
	 * @return the f_PlayerPositionX
	 */
	static float getF_PlayerPositionX() {
		return f_PlayerPositionX;
	}
	/**
	 * @param f_PlayerPositionX the f_PlayerPositionX to set
	 */
	static void setF_PlayerPositionX(float f_PlayerPositionX) {
		Player.f_PlayerPositionX = f_PlayerPositionX;
	}
	/**
	 * @return the f_PlayerPositionY
	 */
	static float getF_PlayerPositionY() {
		return f_PlayerPositionY;
	}
	/**
	 * @param f_PlayerPositionY the f_PlayerPositionY to set
	 */
	static void setF_PlayerPositionY(float f_PlayerPositionY) {
		Player.f_PlayerPositionY = f_PlayerPositionY;
	}
	/**
	 * @return the f_leben
	 */
	static float getF_leben() {
		return f_leben;
	}
	/**
	 * @param f_leben the f_leben to set
	 */
	static void setF_leben(float f_leben) {
		Player.f_leben = f_leben;
	}
	/**
	 * @return the f_mana
	 */
	static float getF_mana() {
		return f_mana;
	}
	/**
	 * @param f_mana the f_mana to set
	 */
	static void setF_mana(float f_mana) {
		Player.f_mana = f_mana;
	}
	/**
	 * @return the f_geld
	 */
	static float getF_geld() {
		return f_geld;
	}
	/**
	 * @param f_geld the f_geld to set
	 */
	static void setF_geld(float f_geld) {
		Player.f_geld = f_geld;
	}
	/**
	 * @return the bounding
	 */
	static Rectangle getBounding() {
		return bounding;
	}
	/**
	 * @param bounding the bounding to set
	 */
	static void setBounding(Rectangle bounding) {
		Player.bounding = bounding;
	}
	/**
	 * @return the zaubern
	 */
	static List<Zauber> getZaubern() {
		return Zaubern;
	}
	/**
	 * @param zaubern the zaubern to set
	 */
	static void setZaubern(List<Zauber> zaubern) {
		Zaubern = zaubern;
	}
	/**
	 * @return the map
	 */
	static Map getMap() {
		return map;
	}

	static BufferedImage getBimg() {
		return bimg;
	}

	

	
	
}
