package prototype;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import player.PlayerIO;
import tiles.*;
import typen.Boden;
import typen.CheckpointTile;
import typen.Err;
import typen.Exit;
import typen.Teleporter;
import typen.Trap;
import typen.Wand;
import typen.WinTile;

public class MapLoader {

	
	private InputStream files;
	private List<Gegner> Enemys;
	private List<Zauber> Zaubern;
	private List<NPC> npcs;
	private List<Gegenstand> gegenstaende;
	private boolean comeback = false;
   
	
	MapLoader(Map map,List<Gegner> Enemys,List<Zauber> Zaubern, List<Gegenstand> gegenstaende,List<NPC> npcs)
	{
		this.Enemys = Enemys;
		this.Zaubern = Zaubern;
		this.gegenstaende=gegenstaende;
		this.npcs=npcs;

	}
	
	public void lesen(String filename)
	{
		lesen(filename,false);
	}

	public void lesen(String filename, boolean intern)
	{
		int sizeX;
		int sizeY;
		float startX;
		float startY;
		String nextMap;
		
		//intern Store
		if(intern)
		{
			if(Shop.isInShop()){
				filename = Shop.getLastMap();
				Shop.setInShop(false);
				Shop.setNextPort(3000+System.currentTimeMillis());
				comeback = true;
			}else{
				Shop.setInShop(true);
				Shop.setF_playPosx(PlayerIO.getF_PlayerPostionX());
				Shop.setF_playPosy(PlayerIO.getF_PlayerPostionY());
				Shop.setLastMap(Map.getCurrentMap());
				Shop.setNextPort(3000+System.currentTimeMillis());
			}
		}
		
		//Map reset
		
		if(Map.resetMap&&Checkpoint.isSet()){filename = Checkpoint.getMapname();}
		
	
		// Loescht Gegner und Zauber und Gegenstaende und Lichter
		int gegneranzahl=Enemys.size();
		for(int i = 0; i<gegneranzahl; i++){
		Enemys.remove(0);}
				
		int zauberzahl=Zaubern.size();
		for(int i = 0; i < zauberzahl; i++){
			Zaubern.remove(0);}
		
		int gegenstandzahl=gegenstaende.size();
		for(int i = 0; i < gegenstandzahl; i++){
			gegenstaende.remove(0);}
		int NPCzahl=npcs.size();
		for(int i = 0; i < NPCzahl; i++){
			npcs.remove(0);}
		Licht.newmap=true;
		
	
		try{
			files = MapLoader.class.getClassLoader().getResourceAsStream(filename);
			Scanner s = new Scanner(files);
			
			if(s.hasNext())
			{	
				//Header Lesen und verarbeiten 
				sizeX = s.nextInt();   	//Hoehe in Tiles
				sizeY = s.nextInt();	//Breite in Tiles
				startX = s.nextInt();	//Spawnposition 
				startY = s.nextInt();	// s.o.
				nextMap = s.next();//  Ort der naechsten Karte
				
				
				//Modifikatoren fuer internen Rueckport
				if(comeback){
					startX = Shop.getF_playPosx();
					startY = Shop.getF_playPosy();
					comeback = false;
				}
				
				if(Map.resetMap&&Checkpoint.isSet()){
					startX = (float)Checkpoint.getPositionX()/40;
					startY = (float)Checkpoint.getPositionY()/40;
			    
			        
				}
				
				
				//Neuaufbau
				new Map(sizeX,sizeY,nextMap);
				PlayerIO.playerTeleport(startX, startY);	//Spieler Spawnen lassen
				Map.setCurrentMap(filename);
				
				
				
				//Tiles fuellen
				int tileType;
				for(int y= 1; y<=sizeY;y++)
				{
					for(int x = 1; x<=sizeX;x++)
					{
						if(s.hasNextInt()){tileType = s.nextInt();}else{tileType = 0;}
						Map.tiles[x][y]=new Tile(x*40, y*40);
						switch(tileType)
						{
						case 1:
							Map.tiles[x][y].setTileTyp(new Boden());
							break;
						case 2:
							Map.tiles[x][y].setTileTyp(new Wand());
							break;
						case 3:
							Map.tiles[x][y].setTileTyp(new Trap());
							break;
						case 4:
							Map.tiles[x][y].setTileTyp(new Teleporter());
							break;
						case 5:
							Map.tiles[x][y].setTileTyp(new typen.Shop());
							break;
						case 6:
							Map.tiles[x][y].setTileTyp(new Exit());
							break;
						case 7:
							Map.tiles[x][y].setTileTyp(new WinTile());
							break;
						case 8:
							Map.tiles[x][y].setTileTyp(new CheckpointTile());
							break;
							
						
							// Spawnt Gegner
						case 51:
							Map.tiles[x][y].setTileTyp(new Boden());
							Enemys.add(new Gegner(x*40+10, y*40+10,1, Enemys,Zaubern));
							break;
						case 52:
							Map.tiles[x][y].setTileTyp(new Boden());
							Enemys.add(new Gegner(x*40+10, y*40+10,2, Enemys,Zaubern));
							break;
						case 53:
							Map.tiles[x][y].setTileTyp(new Boden());
							Enemys.add(new Gegner(x*40+10, y*40+10,3, Enemys,Zaubern));
							break;
						case 54:
							Map.tiles[x][y].setTileTyp(new Boden());
							Enemys.add(new Gegner(x*40+10, y*40+10,4, Enemys,Zaubern));
							break;
						case 61:
							Map.tiles[x][y].setTileTyp(new Boden());
							gegenstaende.add(new Gegenstand(x*40+10, y*40+10,1,gegenstaende));
							break;
						case 62:
							Map.tiles[x][y].setTileTyp(new Boden());
							gegenstaende.add(new Gegenstand(x*40+10, y*40+10,2,gegenstaende));
							break;
						case 63:
							Map.tiles[x][y].setTileTyp(new Boden());
							gegenstaende.add(new Gegenstand(x*40+10, y*40+10,3,gegenstaende));
							break;
						case 64:
							Map.tiles[x][y].setTileTyp(new Boden());
							gegenstaende.add(new Gegenstand(x*40+10, y*40+10,4,gegenstaende));
							break;
						case 65:
							Map.tiles[x][y].setTileTyp(new Boden());
							gegenstaende.add(new Gegenstand(x*40+10, y*40+10,5,gegenstaende));
							break;
						case 71:
							Map.tiles[x][y].setTileTyp(new Wand());
							Licht licht=new Licht(x*40+10,y*40+10,300);
							break;
						case 72:
							Map.tiles[x][y].setTileTyp(new Wand());
							Licht licht2=new Licht(x*40+10,y*40+10,400);
							break;
						case 73:
							Map.tiles[x][y].setTileTyp(new Wand());
							Licht licht3=new Licht(x*40+10,y*40+10,600);
							break;
						case 81:
							Map.tiles[x][y].setTileTyp(new Boden());
							npcs.add(new NPC(x*40+10, y*40+10,1,npcs));
							break;
						case 82:
							Map.tiles[x][y].setTileTyp(new Boden());
							npcs.add(new NPC(x*40+10, y*40+10,2,npcs));
							break;
					
					
						default:
							Map.tiles[x][y].setTileTyp(new Err());
							break;
							
					
						}
					}
				}
			}
			s.close();
		}catch (Exception e){//map.errMap();
		}
	}
}
