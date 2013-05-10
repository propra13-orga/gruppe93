package prototype;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;




public class Main {
	

	
	public static void main(String[] args) {
		
		//Start Einstellungen 
		int worldsizex = 1280;
		int worldsizey = 720;
		int startx = 500;
		int starty = 500;
		//Windows 7 Anpassungen 
		int offx = 6;
		int offy = 28;

		
		Map.setMap();
		Player player = new Player(startx,starty,worldsizex,worldsizey);
		
		Frame f = new Frame(worldsizex,worldsizey,player, Map.getMap());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(worldsizex+offx,worldsizey+offy);
		f.setVisible(true);
		f.setResizable(false);
		
		long lastFrame=System.currentTimeMillis();
		
		
		// Haupschleife mit FPS Limiter (ca 60 FPS
		while(true){
			long currentFrame = System.currentTimeMillis();
			float frameTime = ((float)(currentFrame - lastFrame)/1000f);
			lastFrame = currentFrame;
			player.update(frameTime);
			f.repaint();
			long currentFrame2 = System.currentTimeMillis();
			if(currentFrame2-lastFrame<=14){
			try {
				Thread.sleep(14-(currentFrame2-lastFrame));
			} catch (InterruptedException e) {e.printStackTrace();}
			}
			if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE))System.exit(0);
			lastFrame = currentFrame;
		}
		


	}

}
