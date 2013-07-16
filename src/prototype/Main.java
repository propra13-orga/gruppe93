package prototype;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JInternalFrame;

import multitools.AntiRossi;
import multitools.MPlayServer;

import player.PlayerIO;
import player.Player;



public class Main {
	public static Point point; //wird gebraucht für Mausposition


	public static void main(String[] args) {	
		
		Menue m = new Menue();
		
		
		while(true){
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {	e1.printStackTrace();}
						
			if(m.play||m.multiplay){
				//Start Einstellungen 
				int startx = 0;
				int starty = 0;
				List<Zauber> Zaubern= new LinkedList<Zauber>();
				List<Gegner> Enemys= new LinkedList<Gegner>();
				List<Gegenstand> Gegenstaende= new LinkedList<Gegenstand>();
				List<NPC> npcs= new LinkedList<NPC>();


//				Initialisierung
				Map map=new Map(1,1,null);	//da hier nie ne defaultmap zum einsatz kommt mach ich mal 1 und 1 draus..verbessert die übersicht	
				Player player = new Player(startx,starty,map, Zaubern, Enemys);
				
				//Multigedöns
				Socket socket = null;
				PrintWriter out = null;
				BufferedReader in = null;
				String input="";
				if(m.multiplay){
					AntiRossi anti=new AntiRossi();
					//Jetzt kommt die Magie...
					try
					  {
					   socket = new Socket("25.199.201.255", 5000);
					   out = new PrintWriter(socket.getOutputStream(), true);
					   in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					  }
					  catch(UnknownHostException e)
					  {
					   System.out.println("Unknown or unreachable host 25.199.201.255 on port 5000");
					   System.exit(1);
					  }
					  catch(IOException e)
					  {
					   System.out.println("I/O error");
					   System.exit(1);
					  }
				}

				MapLoader ml = new MapLoader(map, Enemys, Zaubern,Gegenstaende,npcs);
				if(m.play){
					ml.lesen("maps/Map1.txt");
				}else if(m.multiplay){
					ml.lesen("maps/AktuelleMap.txt");
				}
				

				//Sound
				boolean playMusic = false;

				//Spielfenster
				Frame spielFenster = new Frame("Gruppe93", map, Zaubern,Enemys,Gegenstaende,npcs);
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
//					System.out.println(timeSinceLastFrame);		//evtl fuer debugging benoetigt
//					System.out.println(berechnungsZeit);
//					System.out.println(player.getBounding().x+"    "+player.getBounding().y);
//					System.out.println(spielFenster.getInsets());
//					System.out.println(spielFenster.getSize());



						//Updates der Objekte und Akteure
					PlayerIO.playerUpdate(timeSinceLastFrame);
					if(m.multiplay){
						out.println(PlayerIO.getPlayerPositionX()+" "+PlayerIO.getPlayerPositionY());
						try {
							if(in.ready())input=in.readLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(input.contains("nepop")){
							Scanner s=new Scanner(input);
							if(s.hasNext()){
//								System.out.println(s.next());
								s.next();	//um den sessid bestandteil des string zu überspringen
								AntiRossi.setX(s.nextInt());
								AntiRossi.setY(s.nextInt());
								s.close();
							}
						}
					}
					map.spielerTodAnimation(timeSinceLastFrame);
					for(int i = 0; i<Zaubern.size(); i++){
						Zaubern.get(i).update(timeSinceLastFrame);}
					for(int i = 0; i<Enemys.size(); i++){
						Enemys.get(i).update(timeSinceLastFrame);}
					for(int i = 0; i<npcs.size(); i++){
						npcs.get(i).update(timeSinceLastFrame);}
					if (Gegenstaende.size()>0)Gegenstand.update(timeSinceLastFrame);
					
					spielFenster.nextFrame();			//naechster frame


                    //Mausposition für Zauber
					point = spielFenster.getLocationOnScreen();

					//Spiel beenden
					if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE))System.exit(0);

					// Debugging-Hilfen spaeter entfernen
					if(Keyboard.isKeyDown(KeyEvent.VK_R))PlayerIO.respawn();
					if(Keyboard.isKeyDown(KeyEvent.VK_K))PlayerIO.setBCheck(true);
					if(Keyboard.isKeyDown(KeyEvent.VK_L))PlayerIO.setBCheck(false);

			// 		Hintergrund Musik wird abgespielt
					if(playMusic==false){
						HintergrundMusik so = new HintergrundMusik();
						Thread x = new Thread(so);
						x.start();
						playMusic = true;
					}
					

			// Beispiel fuer das Abspielen von Soundeffekten
			// Im Anwendungsfall Verzoegerung einbauen

					if(Keyboard.isKeyDown(KeyEvent.VK_B))
					{
						SoundFX eins = new SoundFX("sound/boing.mp3");
						Thread y = new Thread(eins);
						y.start();
					}

					if(Map.needPort){
						ml.lesen(map.getNextMap());
						Map.needPort = false;
					}

					if(Map.goShop){
						ml.lesen("maps/shop.txt", true);
						Map.goShop = false;
					}
					if(Map.resetMap){
						ml.lesen("maps/test.txt");
						Map.resetMap =false;
					}



			//Schlafen
					nachBerechnungsZeit=System.currentTimeMillis();
					berechnungsZeit=nachBerechnungsZeit-currentFrame;
					if(berechnungsZeit<15){
						try {Thread.sleep(15-berechnungsZeit);} 
						catch (InterruptedException e) {e.printStackTrace();}
					}
				}//WHILE ENDE
			}//if play
		}
		
	}//main Ende
	
	
}