package Leveleditor;

import java.awt.event.KeyEvent;

public class Kamera {
	//DEKLARATIONEN
	private float x;
	private float y;
	//DEKLARATIONEN Ende
	
	
	
	//KONSTRUKTOR
	public Kamera(){
		x=0;
		y=0;
	}
	//KONSTRUKTOR Ende
	
	
	
	//METHODEN
	public void update(float timeSinceLastFrame){
		if(Keyboard.isKeyDown(KeyEvent.VK_UP))y-=500*timeSinceLastFrame;
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN))y+=500*timeSinceLastFrame;
		if(Keyboard.isKeyDown(KeyEvent.VK_LEFT))x-=500*timeSinceLastFrame;
		if(Keyboard.isKeyDown(KeyEvent.VK_RIGHT))x+=500*timeSinceLastFrame;
		
//		System.out.println("x: "+x);
		
	}
	
	
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
}
