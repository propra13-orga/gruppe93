package prototype;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import tiles.*;

public class MapLoader {

	
	private Map map;
	private InputStream files;
	private Player player;
	private List<Gegner> Enemys;
	private List<Zauber> Zaubern;
	private boolean comeback = false;
	
	MapLoader(Map map, Player player,List<Gegner> Enemys,List<Zauber> Zaubern)
	{
		this.map = map;
		this.player = player;
		this.Enemys = Enemys;
		this.Zaubern = Zaubern;
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
				Shop.setF_playPosx(player.posX());
				Shop.setF_playPosy(player.getY());
				Shop.setLastMap(Map.getCurrentMap());
				Shop.setNextPort(3000+System.currentTimeMillis());
			}
		}
		
		// Loescht Gegner und Zauber
		
	
		int gegneranzahl=Enemys.size();
		for(int i = 0; i<gegneranzahl; i++){
		Enemys.remove(0);}
				
		int zauberzahl=Zaubern.size();
		for(int i = 0; i < zauberzahl; i++){
			Zaubern.remove(0);}
		
	
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
				
				//Neuaufbau
				
				map = new Map(sizeX,sizeY,nextMap);		//Map Laden
				player.setPosition(startX ,startY);	//Spieler Spawnen lassen
				player.stop();
				Map.setCurrentMap(filename);
				
				
				//Tiles füllen
				
				int tileType;
				for(int y= 1; y<=sizeY;y++)
				{
					for(int x = 1; x<=sizeX;x++)
					{
						if(s.hasNextInt()){tileType = s.nextInt();}else{tileType = 0;}
						
						switch(tileType)
						{
						case 1:
							Map.tiles[x][y]=TileSpawner.Boden(x*40, y*40);
							break;
						case 2:
							Map.tiles[x][y]=TileSpawner.Wand(x*40, y*40);
							break;
						case 3:
							Map.tiles[x][y]=TileSpawner.Trap(x*40, y*40);
							break;
						case 4:
							Map.tiles[x][y]=TileSpawner.Teleporter(x*40, y*40);
							break;
						case 5:
							Map.tiles[x][y]=TileSpawner.Shop(x*40, y*40);
							break;
						case 6:
							Map.tiles[x][y]=TileSpawner.Exit(x*40, y*40);
							break;
						case 7:
							Map.tiles[x][y]=TileSpawner.WinTile(x*40, y*40);
							break;
						
							// Spawnt Gegner
						case 51:
							Map.tiles[x][y]=TileSpawner.Boden(x*40, y*40);
							Enemys.add(new Gegner(x*40+10, y*40+10,1, Enemys));
							break;
						case 52:
							Map.tiles[x][y]=TileSpawner.Boden(x*40, y*40);
							Enemys.add(new Gegner(x*40+10, y*40+10,2, Enemys));
							break;
					
						default:
							Map.tiles[x][y]=TileSpawner.Err(x*40, y*40);
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
