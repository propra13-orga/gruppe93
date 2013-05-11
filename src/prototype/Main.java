package prototype;

import java.awt.event.KeyEvent;

public class Main {
	
	public static void main(String[] args) {	
		//Start Einstellungen 
		int worldsizex = 1280;
		int worldsizey = 720;
		int startx = 500;
		int starty = 500;

		
		//Framerater
		float timeSinceLastFrame =0;
		long lastFrame=0;
		long currentFrame = 0;
		long nachBerechnungsZeit=0;
		long berechnungsZeit=0;

		
		Map.setMap();
		Player player = new Player(startx,starty,worldsizex,worldsizey);
		
		//Spielfenster
		Frame spielFenster = new Frame("Gruppe93",player, Map.getMap());
		spielFenster.makeBuff();		//ist f�r die BufferStrategy zwingend erforderlich
		spielFenster.setSizeRight(worldsizex,worldsizey);	//Gr��e kann erst hier gesetzt werden, weil im Konstruktor die Insets des Fenster noch falsch sind
		spielFenster.setLocationRelativeTo(null);

		
		
		lastFrame=System.currentTimeMillis();
		// Haupschleife mit FPS Limiter (ca 60 FPS
		while(true){
			currentFrame = System.currentTimeMillis();
			timeSinceLastFrame = ((float)(currentFrame - lastFrame)/1000f);
			lastFrame = currentFrame;
//			System.out.println(timeSinceLastFrame);		//evtl f�r debugging ben�tigt
//			System.out.println(berechnungsZeit);
//			System.out.println(player.getBounding().x+"    "+player.getBounding().y);
//			System.out.println(spielFenster.getInsets());
//			System.out.println(spielFenster.getSize());
			
			player.update(timeSinceLastFrame);
			spielFenster.nextFrame();			//n�chster frame
			if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE))System.exit(0);
			
			
			//Schlafen
			nachBerechnungsZeit=System.currentTimeMillis();
			berechnungsZeit=nachBerechnungsZeit-currentFrame;
			if(berechnungsZeit<15){
				try {Thread.sleep(15-berechnungsZeit);} 
				catch (InterruptedException e) {e.printStackTrace();}
			}	
		}
	}//main Ende
}