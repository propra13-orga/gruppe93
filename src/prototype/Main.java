package prototype;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {	
		//Start Einstellungen 
		int worldsizex = 1280;
		int worldsizey = 720;
		int startx = 500;
		int starty = 500;
		List<Zauber> Zaubern= new LinkedList<Zauber>();
		
		
		
		
//		Initialisierung
		Map map=new Map();		
		map.erstelleTestMap();
		Player player = new Player(startx,starty,worldsizex,worldsizey,map, Zaubern);
		
		
		
		//Spielfenster
		Frame spielFenster = new Frame("Gruppe93",player, map, Zaubern);
		spielFenster.makeBuff();		//ist f�r die BufferStrategy zwingend erforderlich
		spielFenster.setSizeRight(worldsizex,worldsizey);	//Gr��e kann erst hier gesetzt werden, weil im Konstruktor die Insets des Fenster noch falsch sind
		spielFenster.setLocationRelativeTo(null);
		
		
		
		//Frameratelimiter Variabeln
		float timeSinceLastFrame =0;
		long lastFrame=0;
		long currentFrame = 0;
		long nachBerechnungsZeit=0;
		long berechnungsZeit=0;
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
			map.update(timeSinceLastFrame);
			for(int i = 0; i<Zaubern.size(); i++){
				Zaubern.get(i).update(timeSinceLastFrame);}
			
			
			//Spiel beenden
			if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE))System.exit(0);
			
			// Debuging hilfen sp�ter entfernen
			if(Keyboard.isKeyDown(KeyEvent.VK_R))player.respawn();
			if(Keyboard.isKeyDown(KeyEvent.VK_K))player.bCheckOn();
			if(Keyboard.isKeyDown(KeyEvent.VK_L))player.bCheckOff();


			
			
		
		//MAploader Basisfunktion Wartet auf das Menu

		//if(Keyboard.isKeyDown(KeyEvent.VK_M)){
		//	try{
		//		File f = new File("Maps/test.map");
		//		MapLoader ml = new MapLoader(f, map);
		//		ml.lesen();
		//		ml.schliesen();
		//	}catch(Exception e){
		//		map.errMap();
		//	}


			
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
