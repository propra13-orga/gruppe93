package player;

/*
 * Kommunikationsklasse: Fuer die Kommunikation des Player Packages mit anderen Packages.
 */

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
		Player.setF_PlayerPositionY(PositionY);
		Bewegen.setSpeedX(0);
		Bewegen.setSpeedY(0);
	}
	
	public static float getF_Leben() {
		return Player.getF_leben();
	}
	public static int getgeld() {
		return Player.getF_geld();
	}
	public static void setgeld(int x) {
		Player.setF_geld(x);
	}
	public static float getZeitSeitLetztemSchuss() {
		return Schiessen.getZeitSeitLetztemSchuss();
	}
	public static float abklingzeitZauber5() {
		return Schiessen.abklingzeitZauber5();
	}
	public static float abklingzeittrank() {
		return Schiessen.abklingzeittrank();
	}
	public static int lebenstrank() {
		return Schiessen.Lebenstrank();
	}
	public static int manatrank() {
		return Schiessen.Manatrank();
	}
	
	public static float getF_Mana() {
		return Player.getF_mana();
	}
	
	public static Rectangle getBounding() {
		return Player.getBounding();
		
	}
	
	public static void setBCheck(boolean bCheck) {
		PlayerUpdate.setbCheck(bCheck);		
	}
	
	public static void respawn() {
		PlayerUpdate.respawn();
	}
	
	
}
