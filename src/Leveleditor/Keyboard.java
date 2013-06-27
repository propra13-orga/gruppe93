package Leveleditor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	private static boolean keys[]=new boolean[1024];
	
	public static boolean isKeyDown(int KeyCode){
		if(KeyCode<keys.length&&-1<KeyCode){
			return keys[KeyCode];
		} else return false;	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode=e.getKeyCode();
		if(keyCode<keys.length){keys[keyCode]=true;}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode=e.getKeyCode();
		if(keyCode<keys.length){keys[keyCode]=false;}
	}
	
	
	
//UNNÖTIG
	@Override
	public void keyTyped(KeyEvent arg0) {}

}
