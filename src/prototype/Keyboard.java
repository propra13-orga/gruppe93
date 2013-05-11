package prototype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	private static boolean[] keys = new boolean[512];
	
	
	// Übergabe der Tastenstatus 
	
	public static boolean isKeyDown(int keyCode){
		if (keyCode>=0&&keyCode<keys.length) return keys[keyCode];
		else return false;
		
	}
	/*
	 *   Gedrückte Tasten werden in static Array registriert.
	 *   
	 */

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode(); //Maybe switch to extended
		if(keyCode>=0&&keyCode<keys.length) keys[keyCode]= true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode(); 
		if(keyCode>=0&&keyCode<keys.length) keys[keyCode]= false;
		
	}

	
	
	
	
	
	
	// Ohne Funktion
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
