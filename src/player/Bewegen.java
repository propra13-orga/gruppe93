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
		
		if(Keyboard.isKeyDown(KeyEvent.VK_W)){speedY -= beschleunigung*frametime;}
		if(Keyboard.isKeyDown(KeyEvent.VK_S)){speedY += beschleunigung*frametime;}
		if(Keyboard.isKeyDown(KeyEvent.VK_A)){speedX -= beschleunigung*frametime;}
		if(Keyboard.isKeyDown(KeyEvent.VK_D)){speedX += beschleunigung*frametime;}
		
		f_PlayerPositionY+=speedY*frametime;
		f_PlayerPositionX+=speedX*frametime;
		
		Player.setF_PlayerPositionX(f_PlayerPositionX);
		Player.setF_PlayerPositionY(f_PlayerPositionY);
		
		if(Math.abs(speedY)<verlangsamung/100&&!Keyboard.isKeyDown(KeyEvent.VK_W)&&!Keyboard.isKeyDown(KeyEvent.VK_S)){speedY=0;}else	//Speed geht mit der Zeit wieder runter und unter 1 geht er direkt auf 0
		if(speedY>maxSpeed){speedY=maxSpeed;}else
		if(speedY<-maxSpeed){speedY=-maxSpeed;}else
		if(speedY>verlangsamung/1000){speedY-=verlangsamung*frametime;}else
		if(speedY<verlangsamung/1000){speedY+=verlangsamung*frametime;}
		if(Math.abs(speedX)<verlangsamung/100&&!Keyboard.isKeyDown(KeyEvent.VK_A)&&!Keyboard.isKeyDown(KeyEvent.VK_D)){speedX=0;}else
		if(speedX>maxSpeed){speedX=maxSpeed;}else
		if(speedX<-maxSpeed){speedX=-maxSpeed;}else
		if(speedX>verlangsamung/1000){speedX-=verlangsamung*frametime;}else
		if(speedX<verlangsamung/1000){speedX+=verlangsamung*frametime;}
		
		Rectangle bounding = Player.getBounding();
		bounding.x = ((int) f_PlayerPositionX)+10;	//Aufgrund der Natur des Bilds machen diese einrueckungen Sinn
		bounding.y = ((int) f_PlayerPositionY)+10;
		Player.setBounding(bounding);
		
	}

}
