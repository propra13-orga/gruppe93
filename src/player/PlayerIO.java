package player;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PlayerIO {
	
	public static void playerUpdate(float frametime){
		PlayerUpdate.update(frametime);
	}
	
	public static BufferedImage getBimg() {
		return Player.getBimg();		
	}
	
	public static float getF_PlayerPostionX() {
		return Player.getF_PlayerPositionX();	
	}
	
	public static float getF_PlayerPostionY() {
		return Player.getF_PlayerPositionY();	
	}
	
	public static int getPlayerPositionX() {
		return (int) Player.getF_PlayerPositionX();		
	}
	
	public static int getPlayerPositionY() {
		return (int) Player.getF_PlayerPositionY();		
	}
	
	public static void playerTeleport(float PositionX, float PositionY){
		Player.setF_PlayerPositionX(PositionX);
		Player.setF_PlayerPositionY(PositionX);
		Bewegen.setSpeedX(0);
		Bewegen.setSpeedY(0);
	}
	
	public static float getF_Leben() {
		return Player.getF_leben();
	}
	
	public static float getF_Mana() {
		return Player.getF_mana();
	}
	
	public static Rectangle getBounding() {
		return Player.getBounding();
		
	}
	
	
	
}
