package player;

/*
 * Bewegt den Spieler
 */

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import prototype.Keyboard;

class Bewegen {
	
	private static float speedX;						// Vertikale Geschwindigkeit
	private static float speedY;						// Horizontale GEschwindigkeit
	private static final float beschleunigung = 2000;	// Beschleunigungs Wert
	private static final float verlangsamung = 1000;	// Bremswert
	private static final float maxSpeed = 300;			// Hoechst Geschwindigkeit
	
	static void bewegen(float frametime){
		
		// Abfragen der aktuellen Spielerposition
		
		float f_PlayerPositionX= Player.getF_PlayerPositionX();
		float f_PlayerPositionY= Player.getF_PlayerPositionY();
		
		// Beschleunigen des Spielers auf Tastendruck
		
		if(Keyboard.isKeyDown(KeyEvent.VK_W)){setSpeedY(getSpeedY() - beschleunigung*frametime);}
		if(Keyboard.isKeyDown(KeyEvent.VK_S)){setSpeedY(getSpeedY() + beschleunigung*frametime);}
		if(Keyboard.isKeyDown(KeyEvent.VK_A)){setSpeedX(getSpeedX() - beschleunigung*frametime);}
		if(Keyboard.isKeyDown(KeyEvent.VK_D)){setSpeedX(getSpeedX() + beschleunigung*frametime);}
		
		// Verschieben des Spielers
		
		f_PlayerPositionY+=getSpeedY()*frametime;
		f_PlayerPositionX+=getSpeedX()*frametime;
		
		Player.setF_PlayerPositionX(f_PlayerPositionX);
		Player.setF_PlayerPositionY(f_PlayerPositionY);
		
		// BeschleunigungsKontrolle und Bremsen
		
		if(Math.abs(getSpeedY())<verlangsamung/100&&!Keyboard.isKeyDown(KeyEvent.VK_W)&&!Keyboard.isKeyDown(KeyEvent.VK_S)){setSpeedY(0);}else	//Speed geht mit der Zeit wieder runter und unter 1 geht er direkt auf 0
		if(getSpeedY()>maxSpeed){setSpeedY(maxSpeed);}else
		if(getSpeedY()<-maxSpeed){setSpeedY(-maxSpeed);}else
		if(getSpeedY()>verlangsamung/1000){setSpeedY(getSpeedY() - verlangsamung*frametime);}else
		if(getSpeedY()<verlangsamung/1000){setSpeedY(getSpeedY() + verlangsamung*frametime);}
		if(Math.abs(getSpeedX())<verlangsamung/100&&!Keyboard.isKeyDown(KeyEvent.VK_A)&&!Keyboard.isKeyDown(KeyEvent.VK_D)){setSpeedX(0);}else
		if(getSpeedX()>maxSpeed){setSpeedX(maxSpeed);}else
		if(getSpeedX()<-maxSpeed){setSpeedX(-maxSpeed);}else
		if(getSpeedX()>verlangsamung/1000){setSpeedX(getSpeedX() - verlangsamung*frametime);}else
		if(getSpeedX()<verlangsamung/1000){setSpeedX(getSpeedX() + verlangsamung*frametime);}
		
		Rectangle bounding = Player.getBounding();
		bounding.x = ((int) f_PlayerPositionX+10);	//Aufgrund der Natur des Bilds machen diese einrueckungen Sinn
		bounding.y = ((int) f_PlayerPositionY+10); 
		Player.setBounding(bounding);
		
	}
	
	//Setter und Getter fuer externe Zugriffe.

	static float getSpeedX() {
		return speedX;
	}

	static void setSpeedX(float speedX) {
		Bewegen.speedX = speedX;
	}

	static float getSpeedY() {
		return speedY;
	}

	static void setSpeedY(float speedY) {
		Bewegen.speedY = speedY;
	}
	
	

}
