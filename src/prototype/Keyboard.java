package prototype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	private static boolean[] keys = new boolean[512];
	
	
/*
 * Tastatureingabe Ueberwachung:
 * 
 * Bei Tastendruck wird dies registriert und im Keys Array eingetragen.
 * So das in andern Klassen nur noch die Taste im Array abgefragt werden muss.
 */
	
	
//	Rückgabe des Tastenstatus
	
	public static boolean isKeyDown(int keyCode){
		if (keyCode>=0&&keyCode<keys.length) return keys[keyCode];
		else return false;
		
	}

	
//	Registrierung der Tastendrücke.

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode(); 
		if(keyCode>=0&&keyCode<keys.length) keys[keyCode]= true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode(); 
		if(keyCode>=0&&keyCode<keys.length) keys[keyCode]= false;
		
	}

	
	
	
	
	
	
	// Ohne Funktion
	@Override
	public void keyTyped(KeyEvent e) {}

}
