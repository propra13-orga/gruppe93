package player;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import prototype.Map;
import prototype.Shop;
import tiles.Tile;

class Kollision {
	
	static void kollision(){
		
		int kartenPositionX = (int) (Player.getF_PlayerPositionX()/Tile.getFeldGroesse());
		int kartenPositionY = (int) (Player.getF_PlayerPositionY()/Tile.getFeldGroesse());
		Map map = Player.getMap();
		int x_Tiles = map.getXTiles();
		int y_Tiles = map.getYTiles();
		Rectangle bounding = Player.getBounding();
		boolean richtungWurdeGeaendert = false;
		float f_PlayerPositionX = Player.getF_PlayerPositionX();
		float f_PlayerPositionY = Player.getF_PlayerPositionY();

		
		
		for(int tilex = kartenPositionX; tilex <= kartenPositionX + 1; tilex++){//hier muss <= geprueft werden, damit an kartenposition+1 auch eine ueberpruefung stattfindet. an kartenpos -1 muss dafuer nix gemacht werden da wir die obere linke ecke sowieso als Ausgangsbasis nehmen
			if(tilex<0)tilex=0;	//sorgt dafuer, da� beim ueberschreiten der levelgrenzen kein absturz auftritt
			if(tilex>x_Tiles)break;
			for(int tiley = kartenPositionY; tiley<= kartenPositionY + 1; tiley++){
				if(tiley<0)tiley=0;
				if(tiley>y_Tiles)break;
				if(map.getTile(tilex, tiley).getBlockiert()&&!richtungWurdeGeaendert&&bounding.intersects(map.getTile(tilex, tiley).getBounding()))//wenn hier abprallen gebrueft werden muss und die richtung nicht schon geaendert wurde
				{
					Rectangle inter =  bounding.intersection(map.getTile(tilex, tiley).getBounding());
					richtungWurdeGeaendert=true; //wichtig, damit pro vorgang nicht doppelt die richtung umgedreht wird
					if(inter.getWidth()>inter.getHeight())
					{
						Bewegen.setSpeedY(-Bewegen.getSpeedY());
						if(inter.y>map.getTile(tilex, tiley).getBounding().y){
							f_PlayerPositionY+=inter.getHeight();
						}else if(inter.y==map.getTile(tilex, tiley).getBounding().y){
							f_PlayerPositionY-=inter.getHeight();
						}
					}else if(inter.getWidth()<inter.getHeight())
					{
						Bewegen.setSpeedX(-Bewegen.getSpeedX());
						if(inter.x>map.getTile(tilex, tiley).getBounding().x){
							f_PlayerPositionX+=inter.getWidth();
						}else if(inter.x==map.getTile(tilex, tiley).getBounding().x){
							f_PlayerPositionX-=inter.getWidth();
						}
					}
					Player.setF_PlayerPositionX(f_PlayerPositionX);
					Player.setF_PlayerPositionY(f_PlayerPositionY);
				}
				
				if(map.getTile(tilex, tiley).getTrap()&&bounding.intersects(map.getTile(tilex, tiley).getBounding()))
				{
					Player.setF_leben(0);
					break;
				}
				// Teleporterpruefung
				
				if(map.getTile(tilex, tiley).getTranporter()&&bounding.intersects(map.getTile(tilex, tiley).getBounding())&&Player.getEnemys().size()<1){
					Map.needPort = true; 
				}
				//Shoppruefung
				
				if(map.getTile(tilex, tiley).getShop()&&bounding.intersects(map.getTile(tilex, tiley).getBounding())&&(System.currentTimeMillis()-Shop.getNextPort())>0){
					Map.goShop = true;
				}
				//Exitpruefung
				if(map.getTile(tilex, tiley).getExit()&&bounding.intersects(map.getTile(tilex, tiley).getBounding())){
					map.setWin();
					try {
						Desktop.getDesktop().browse(new URI("http://www.youtube.com/watch?v=DLTZctTG6cE")); //Ruft Youtube auf siehe Java API
					} catch (IOException | URISyntaxException e) {}
					try {
						Thread.sleep(8000);
					} catch (InterruptedException e) {}
					System.exit(0);
				}
				
				// Checkpointsetzen
				if(map.getTile(tilex, tiley).getCheckpoint()){
					tiles.Checkpoint.setCheckpoint(map.getTile(tilex, tiley).getPositionX(), map.getTile(tilex, tiley).getPositionY());
				}
				
				
			}
		}
	}	

}
