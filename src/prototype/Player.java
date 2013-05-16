package prototype;



import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	
	private Rectangle bounding;
	private float f_playposx;
	private float f_playposy;
	private short kartenPositionX;	//enstpricht dem Feld auf der Map. Zur �berpr�fung welche Felder auf Kollision gepr�ft werden
	private short kartenPositionY;
	
	private float speedX;
	private float speedY;
	private float speedGainRate=2000;
	private float speedReductionRate=100;
	private float maximumSpeed=300;
	private boolean richtungWurdeGeaendert=false;
	
	private int worldsize_x;
	private int worldsize_y;
	private Map map;
	private BufferedImage bimg;
	private boolean isAlive = true;
	private boolean bCheck = true;  // Aktiviert Kollisionsabfrage
	
	
	public Player(int x, int y, int worldsize_x, int worldsize_y, Map map){
		try {
			bimg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/Rossi.png"));
		} catch (IOException e) {e.printStackTrace();}
		bounding = new Rectangle(x+10,y+10,bimg.getWidth()-20,bimg.getHeight()-20);
		f_playposx = x;
		f_playposy = y;
		this.worldsize_x=worldsize_x;
		this.worldsize_y=worldsize_y;
		this.map=map;
	}
	
	public void update(float frametime){
		
		if(!isAlive)return;	//wenn der spieler tot ist wird das update �bersprungen
		
		if(Keyboard.isKeyDown(KeyEvent.VK_W))speedY -= speedGainRate*frametime;
		if(Keyboard.isKeyDown(KeyEvent.VK_S))speedY += speedGainRate*frametime;
		if(Keyboard.isKeyDown(KeyEvent.VK_A))speedX -= speedGainRate*frametime;
		if(Keyboard.isKeyDown(KeyEvent.VK_D))speedX += speedGainRate*frametime;
		
		f_playposy+=speedY*frametime;
		f_playposx+=speedX*frametime;
		
		if(Math.abs(speedY)<speedReductionRate/100&&!Keyboard.isKeyDown(KeyEvent.VK_W)&&!Keyboard.isKeyDown(KeyEvent.VK_S)){speedY=0;}else	//Speed geht mit der Zeit wieder runter und unter 1 geht er direkt auf 0
		if(speedY>maximumSpeed){speedY=maximumSpeed;}else
		if(speedY<-maximumSpeed){speedY=-maximumSpeed;}else
		if(speedY>speedReductionRate/1000){speedY-=speedReductionRate*frametime;}else
		if(speedY<speedReductionRate/1000){speedY+=speedReductionRate*frametime;}
		if(Math.abs(speedX)<speedReductionRate/100&&!Keyboard.isKeyDown(KeyEvent.VK_A)&&!Keyboard.isKeyDown(KeyEvent.VK_D)){speedX=0;}else
		if(speedX>maximumSpeed){speedX=maximumSpeed;}else
		if(speedX<-maximumSpeed){speedX=-maximumSpeed;}else
		if(speedX>speedReductionRate/1000){speedX-=speedReductionRate*frametime;}else
		if(speedX<speedReductionRate/1000){speedX+=speedReductionRate*frametime;}
		
		
		if(f_playposx<0){f_playposx=0;speedX=-speedX;}else
		if(f_playposy<0){f_playposy=0;speedY=-speedY;}else
		if(f_playposx>worldsize_x - bounding.width){f_playposx=worldsize_x - bounding.width;speedX=-speedX;}else
		if(f_playposy>worldsize_y - bounding.height){f_playposy=worldsize_y - bounding.height;speedY=-speedY;}
		
		
		
		kartenPositionX=(short)(f_playposx/Tile.getFeldGroesse());
		kartenPositionY=(short)(f_playposy/Tile.getFeldGroesse());
		bounding.x = ((int) f_playposx)+10;	//Aufgrund der Natur des Bilds machen diese einr�ckungen Sinn
		bounding.y = ((int) f_playposy)+10;
		
		
		//Schalter Kollision
		
		
	
		if(bCheck){
			wandKollision();
			fallenPruefung();
		}
	}//update Ende
	
	
	
	private void wandKollision(){
		for(int tx = kartenPositionX; tx <= kartenPositionX + 2; tx++){//hier muss <= gepr�ft werden, damit an kartenposition+2 auch eine �berpr�fung stattfindet. an kartenpos -1 muss daf�r nix gemacht werden da wir die obere linke ecke sowieso als ausgangsbasis nehmen
			if(tx<0)tx=0;	//sorgt daf�r, da� beim �berschreiten der levelgrenzen kein absturz auftritt
			if(tx>31)break;
			for(int ty = kartenPositionY; ty<= kartenPositionY + 2; ty++){
				if(ty<0)ty=0;
				if(ty>17)break;
				if(map.getTile(tx, ty).getBlockiert()&&!richtungWurdeGeaendert&&bounding.intersects(map.getTile(tx, ty).getBounding())){//wenn hier abprallen gebr�ft werden muss und die richtung nicht schon ge�ndert wurde
					Rectangle inter =  bounding.intersection(map.getTile(tx, ty).getBounding());
					richtungWurdeGeaendert=true; //wichtig, damit pro vorgang nicht doppelt die richtung umgedreht wird
					if(inter.getWidth()>inter.getHeight()){
						speedY=-speedY;
						if(inter.y>map.getTile(tx, ty).getBounding().y){
							f_playposy+=inter.getHeight();
						}else if(inter.y==map.getTile(tx, ty).getBounding().y){
							f_playposy-=inter.getHeight();
						}
					}else if(inter.getWidth()<inter.getHeight()){
						speedX=-speedX;
						System.out.println(inter.x+"   "+map.getTile(tx, ty).getBounding().x);
						if(inter.x>map.getTile(tx, ty).getBounding().x){
							f_playposx+=inter.getWidth();
						}else if(inter.x==map.getTile(tx, ty).getBounding().x){
							f_playposx-=inter.getWidth();
						}
					}
				}
			}
		}
		richtungWurdeGeaendert=false;
	}//Ende wandKollision
	
	
	
	private void fallenPruefung(){
		for(int tx = kartenPositionX; tx <= kartenPositionX + 1; tx++){//hier muss <= gepr�ft werden, damit an kartenposition+1 auch eine �berpr�fung stattfindet. an kartenpos -1 muss daf�r nix gemacht werden da wir die obere linke ecke sowieso als ausgangsbasis nehmen
			if(tx<0)tx=0;	//sorgt daf�r, da� beim �berschreiten der levelgrenzen kein absturz auftritt
			if(tx>31)break;
			for(int ty = kartenPositionY; ty <= kartenPositionY + 1; ty++){
				if(ty<0)ty=0;
				if(ty>17)break;
				if(map.getTile(tx, ty).getKillYou()&&bounding.intersects(map.getTile(tx, ty).getBounding())){
					isAlive = false;					
					map.setSpielerTod(true);
					speedX=0;
					speedY=0;
				}
			}
		}
	}
	
	
	
	public Rectangle getBounding(){
		return bounding;
	}
	
	
	
	public BufferedImage getBimg(){
		return bimg;
	}
	
	
	
	public void respawn(){
		f_playposx = 500;
		f_playposy = 500;	
		isAlive = true;
		map.setSpielerTod(false);
		map.erstelleTestMap();
	}
	
	
	//DEBUG Methoden bzgl der Kollisions und Sterbepr�fung
	public void bCheckOn(){
		bCheck = true;
	}
	
	
	
	public void bCheckOff(){
		bCheck = false;
	}
	
	public int getX(){
		return (int)f_playposx;
	}
	
	public int getY(){
		return (int)f_playposy;
	}
}
