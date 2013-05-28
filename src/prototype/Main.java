package prototype;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {	
		//Start Einstellungen 
		int worldsizex = 1280;
		int worldsizey = 820;
		int startx = 120;
		int starty = 550;
		List<Zauber> Zaubern= new LinkedList<Zauber>();
		
		
		
		
//		Initialisierung
		Map map=new Map();		
		map.erstelleTestMap();
		//map.raumEins();
		Player player = new Player(startx,starty,worldsizex,worldsizey,map, Zaubern);
		
		
		
		//Spielfenster
		Frame spielFenster = new Frame("Gruppe93",player, map, Zaubern);
		spielFenster.makeBuff();		//ist für die BufferStrategy zwingend erforderlich
		spielFenster.setSizeRight(worldsizex,worldsizey);	//Größe kann erst hier gesetzt werden, weil im Konstruktor die Insets des Fenster noch falsch sind
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
//			System.out.println(timeSinceLastFrame);		//evtl für debugging benötigt
//			System.out.println(berechnungsZeit);
//			System.out.println(player.getBounding().x+"    "+player.getBounding().y);
//			System.out.println(spielFenster.getInsets());
//			System.out.println(spielFenster.getSize());
			
			
			
			player.update(timeSinceLastFrame);
			spielFenster.nextFrame();			//nächster frame
			map.update(timeSinceLastFrame);
			for(int i = 0; i<Zaubern.size(); i++){
				Zaubern.get(i).update(timeSinceLastFrame);}
			
			
			//Spiel beenden
			if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE))System.exit(0);
			
			// Debuging hilfen später entfernen
			if(Keyboard.isKeyDown(KeyEvent.VK_R))player.respawn();
			if(Keyboard.isKeyDown(KeyEvent.VK_K))player.bCheckOn();
			if(Keyboard.isKeyDown(KeyEvent.VK_L))player.bCheckOff();
			if(Keyboard.isKeyDown(KeyEvent.VK_T))map.erstelleTestMap();


			if(map.getTile(1, 1).getTex()==8){
				try {
					Desktop.getDesktop().browse(new URI("http://www.youtube.com/watch?v=DLTZctTG6cE"));
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
				
			}
			
			
		
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
