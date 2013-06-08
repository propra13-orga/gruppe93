package player;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import prototype.Keyboard;

class Bewegen {
	
	private static float speedX;
	private static float speedY;
	private static final float beschleunigung = 2000;
	private static final float verlangsamung = 1000;
	private static final float maxSpeed = 300;
	
	static void bewegen(float frametime){
		float f_PlayerPositionX= Player.getF_PlayerPositionX();
		float f_PlayerPositionY= Player.getF_PlayerPositionY();
		
		if(Keyboard.isKeyDown(KeyEvent.VK_W)){setSpeedY(getSpeedY() - beschleunigung*frametime);}
		if(Keyboard.isKeyDown(KeyEvent.VK_S)){setSpeedY(getSpeedY() + beschleunigung*frametime);}
		if(Keyboard.isKeyDown(KeyEvent.VK_A)){setSpeedX(getSpeedX() - beschleunigung*frametime);}
		if(Keyboard.isKeyDown(KeyEvent.VK_D)){setSpeedX(getSpeedX() + beschleunigung*frametime);}
		
		f_PlayerPositionY+=getSpeedY()*frametime;
		f_PlayerPositionX+=getSpeedX()*frametime;
		
		Player.setF_PlayerPositionX(f_PlayerPositionX);
		Player.setF_PlayerPositionY(f_PlayerPositionY);
		
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
		bounding.x = ((int) f_PlayerPositionX)+10;	//Aufgrund der Natur des Bilds machen diese einrueckungen Sinn
		bounding.y = ((int) f_PlayerPositionY)+10;
		Player.setBounding(bounding);
		
	}

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
