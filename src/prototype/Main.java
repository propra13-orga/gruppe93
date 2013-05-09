package prototype;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;




public class Main {
	

	
	public static void main(String[] args) {
		
		//initial settings
		int worldsizex = 1280;
		int worldsizey = 720;
		int startx = 500;
		int starty = 500;
		//Windows 7 offsets
		int offx = 6;
		int offy = 28;
		
		
		Player player = new Player(startx,starty,worldsizex,worldsizey);
		
		Frame f = new Frame(worldsizex,worldsizey,player);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(worldsizex+offx,worldsizey+offy);
		f.setVisible(true);
		f.setResizable(false);
		
		long lastFrame=System.currentTimeMillis();

		while(true){
			long currentFrame = System.currentTimeMillis();
			float frameTime = ((float)(currentFrame - lastFrame)/1000f);
			lastFrame = currentFrame;
			player.update(frameTime);
			
			
			f.repaint();
			
			try {
				Thread.sleep(14);
			} catch (InterruptedException e) {e.printStackTrace();}
			if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE))System.exit(0);
		}
		


	}

}
