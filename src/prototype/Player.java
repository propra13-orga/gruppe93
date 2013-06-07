package prototype;




import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class Player {
	
	private static Rectangle bounding;
	private float f_playposx;
	private float f_playposy;
	private short kartenPositionX;	//enstpricht dem Feld auf der Map. Zur ueberpruefung welche Felder auf Kollision geprueft werden
	private short kartenPositionY;
	private int	x_Tiles;
	private int	y_Tiles;
	
	private float speedX;
	private float speedY;
	private float speedGainRate=2000;
	private float speedReductionRate=1000;
	private float maximumSpeed=300;
	private boolean richtungWurdeGeaendert=false;
	
	private Map map;
	private BufferedImage bimg;
	private boolean isAlive = true;
	private boolean bCheck = true; // Aktiviert Kollisionsabfrage
	private List<Zauber>Zaubern;
	private List<Gegner>Enemys;		//hab das static rausgenommen um die Warnmeldung auszuschalten.
	private final float schussfrequenz = 0.5f ;
	private float ZeitSeitLetztemSchuss = 0;
	private int Zauberrichtung_x=1000;
	private int Zauberrichtung_y;
	private float mana=1000;
	private float manaregeneration=40;
	private float leben=1000;
	private int gegneranzahl;

	private long timeOfDeath;
	private long now;
	
	private boolean needPort = false; //siehe Teleporter
	private boolean goShop = false;
	private boolean resetMap = false;
	
//	Konstruktor
	public Player(int x, int y,  Map map, List<Zauber>Zaubern, List<Gegner>Enemys){
		try {
			bimg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/Rossi.png"));
		} catch (IOException e) {e.printStackTrace();}
		bounding = new Rectangle(x+10,y+10,bimg.getWidth()-20,bimg.getHeight()-20);
		f_playposx = x;
		f_playposy = y;

		this.map=map;
		this.Zaubern=Zaubern;
		this.Enemys=Enemys;
	
	}
	
	public void update(float frametime){
		now = System.currentTimeMillis();
		now -= timeOfDeath;
		if(!isAlive&&now>3000)respawn(); // Automatischer Respawn 
		if(!isAlive)return;	//wenn der spieler tot ist wird das update Uebersprungen
		ZeitSeitLetztemSchuss+=frametime;
		if (mana<1000) mana=mana+frametime*manaregeneration; //manaregeneration
			
		x_Tiles=map.getXTiles();
		y_Tiles=map.getYTiles();
				
		bewegen(frametime); //Komplettes Movement ausgelagert
		schussGen();// Schuesse werden hier generiert
		
		// Kolisionen
		if(bCheck){
			wandKollision();
			fallenPruefung();
			teleport();
			exit();
			gegnerKolision();	
		}

		//Taste erzeugt Gegner zum testen
		
		if(ZeitSeitLetztemSchuss>schussfrequenz&&Keyboard.isKeyDown(KeyEvent.VK_1)){
			ZeitSeitLetztemSchuss = 0;
			Enemys.add(new Gegner( 600, 600,1, Enemys));
		}
	
		if (leben<0) spielerTot();
		
	}//update Ende
		
	private void bewegen(float frametime)
	{
		if(Keyboard.isKeyDown(KeyEvent.VK_W)){speedY -= speedGainRate*frametime;}
		if(Keyboard.isKeyDown(KeyEvent.VK_S)){speedY += speedGainRate*frametime;}
		if(Keyboard.isKeyDown(KeyEvent.VK_A)){speedX -= speedGainRate*frametime;}
		if(Keyboard.isKeyDown(KeyEvent.VK_D)){speedX += speedGainRate*frametime;}
		
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
		
		
		
		kartenPositionX=(short)(f_playposx/Tile.getFeldGroesse());
		kartenPositionY=(short)(f_playposy/Tile.getFeldGroesse());
		bounding.x = ((int) f_playposx)+10;	//Aufgrund der Natur des Bilds machen diese einrueckungen Sinn
		bounding.y = ((int) f_playposy)+10;
	}
	
	private void wandKollision(){
		for(int tx = kartenPositionX; tx <= kartenPositionX + 1; tx++){//hier muss <= geprueft werden, damit an kartenposition+1 auch eine ueberpruefung stattfindet. an kartenpos -1 muss dafuer nix gemacht werden da wir die obere linke ecke sowieso als ausgangsbasis nehmen
			if(tx<0)tx=0;	//sorgt dafuer, daß beim ueberschreiten der levelgrenzen kein absturz auftritt
			if(tx>x_Tiles)break;
			for(int ty = kartenPositionY; ty<= kartenPositionY + 1; ty++){
				if(ty<0)ty=0;
				if(ty>y_Tiles)break;
				if(map.getTile(tx, ty).getBlockiert()&&!richtungWurdeGeaendert&&bounding.intersects(map.getTile(tx, ty).getBounding())){//wenn hier abprallen gebrueft werden muss und die richtung nicht schon geaendert wurde
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
		for(int tx = kartenPositionX; tx <= kartenPositionX + 1; tx++){//hier muss <= geprueft werden, damit an kartenposition+1 auch eine Ueberpruefung stattfindet. an kartenpos -1 muss dafuer nix gemacht werden da wir die obere linke ecke sowieso als ausgangsbasis nehmen
			if(tx<0)tx=0;	//sorgt dafuer, daß beim ueberschreiten der levelgrenzen kein absturz auftritt
			if(tx>x_Tiles)break;
			for(int ty = kartenPositionY; ty <= kartenPositionY + 1; ty++){
				if(ty<0)ty=0;
				if(ty>y_Tiles)break;
				if(map.getTile(tx, ty).getKillYou()&&bounding.intersects(map.getTile(tx, ty).getBounding())){
				
					spielerTot();
				}
			}
		}
	}
	
	
	
	private void teleport(){
		for(int tilex = kartenPositionX; tilex <= kartenPositionX +1; tilex++){
			if(tilex<0)continue;
			if(tilex>x_Tiles)break;
			for(int tiley = kartenPositionY; tiley <= kartenPositionY +1; tiley++){
				if(tiley<0)continue;
				if(tiley>y_Tiles)break;
				if(map.getTile(tilex, tiley).getIsTeleporter()&&bounding.intersects(map.getTile(tilex, tiley).getBounding())){

					needPort = true; //Maploader Workaround
				}
				if(map.getTile(tilex, tiley).getIsShop()&&bounding.intersects(map.getTile(tilex, tiley).getBounding())&&(System.currentTimeMillis()-Shop.getNextPort())>0){
					goShop = true;
				}
			}
		}
	}
	//Hilfe fuer den Workaround
	public boolean getNeedPort(){
		return needPort;
	}
	
	public void setNeedPort(){
		needPort = false;
	}
	
	public boolean getGoShop(){
		return goShop;
	}
	
	public void setGoShop(){
		goShop = false;
	}
	
	private void exit(){
		for(int tilex = kartenPositionX; tilex <= kartenPositionX +1; tilex++){
			for( int tiley = kartenPositionY; tiley <= kartenPositionY+1; tiley++){
					if(map.getTile(tilex, tiley).getIsExit()&&bounding.intersects(map.getTile(tilex, tiley).getBounding())){
					map.setWin();
				

				}
			}
		}
	}
	
	private void gegnerKolision()
	{
		//Kollision fuer Spieler-Gegner und Zauber-Gegner
		
		for(int i = 0; i<Enemys.size(); i++){
			Gegner e = Enemys.get(i);
			
			if(bounding.intersects(e.getBounding())){
				leben=leben-(float)10;
			}
		}
		
		
		for(int i = 0; i<Enemys.size(); i++){
			Gegner e = Enemys.get(i);
			for(int a=0; a<Zaubern.size(); a++){
				Zauber f = Zaubern.get(a);
			
			
				
			
		    if(e.getBounding().intersects(f.getBounding())){
		    	if (f.getid()==1){
				Zaubern.remove(a);
				e.setLeben(60);
		    	}
		    	else
		    	if (f.getid()==2){
					e.setLeben(2);
			    	}
				//Schussschaden an Gegner
				
				}
			}
		}
	}
	
	private void schussGen()
	{
		//Zauber generierung jetzt ueber Pfeiltasten und in eigener Methode
		
		if(Keyboard.isKeyDown(KeyEvent.VK_UP)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>130)
		{
			ZeitSeitLetztemSchuss = 0;
			Zauberrichtung_x=0;
			Zauberrichtung_y=-1000;
			Zaubern.add(new Zauber(f_playposx, f_playposy, Zauberrichtung_x, Zauberrichtung_y,1,  Zaubern));
			mana -= 30;
		}
		
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>130)
		{
			ZeitSeitLetztemSchuss = 0;
			Zauberrichtung_x=0;
			Zauberrichtung_y=1000;
			Zaubern.add(new Zauber(f_playposx, f_playposy, Zauberrichtung_x, Zauberrichtung_y,1,  Zaubern));
			mana -= 30;
		}
		
		if(Keyboard.isKeyDown(KeyEvent.VK_LEFT)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>130)
		{
			ZeitSeitLetztemSchuss = 0;
			Zauberrichtung_x=-1000;
			Zauberrichtung_y=0;
			Zaubern.add(new Zauber(f_playposx, f_playposy, Zauberrichtung_x, Zauberrichtung_y,1,  Zaubern));
			mana -= 30;
		}
		
		if(Keyboard.isKeyDown(KeyEvent.VK_RIGHT)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>130)
		{
			ZeitSeitLetztemSchuss = 0;
			Zauberrichtung_x=1000;
			Zauberrichtung_y=0;
			Zaubern.add(new Zauber(f_playposx, f_playposy, Zauberrichtung_x, Zauberrichtung_y,1, Zaubern));
			mana -= 30;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_SPACE)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>800)
		{
			ZeitSeitLetztemSchuss = 0;
			Zauberrichtung_x=0;
			Zauberrichtung_y=0;
			Zaubern.add(new Zauber(f_playposx-100, f_playposy-100, Zauberrichtung_x, Zauberrichtung_y,2, Zaubern));
			mana -= 800;
		}	
	}
	
	private void spielerTot()
	{
		isAlive = false;					
		map.setSpielerTod(true);
		speedX=0;
		speedY=0;
		timeOfDeath = System.currentTimeMillis();
		gegneranzahl=Enemys.size();// Festellung der Gegnerzahl
		for(int i = 0; i<gegneranzahl; i++){
		Enemys.remove(0);}
					
	}
	
	public static Rectangle getBounding(){
		return bounding;
	}
	
	public BufferedImage getBimg(){
		return bimg;
	}
	
	public void respawn(){
		f_playposx = 120;
		f_playposy = 550;
		speedX = 0;
		speedY = 0;
		isAlive = true;
		map.setSpielerTod(false);
		leben=1000;
		mana=1000;
		resetMap = true;
	    }
	
	//DEBUG Methoden bzgl der Kollisions und Sterbepruefung
	public void bCheckOn(){
		bCheck = true;
	}
	
	public void bCheckOff(){
		bCheck = false;
	}
	
//	Rueckgabe Spielerposition and Frame
	public int getX(){
		return (int)f_playposx;
	}
	
	public int getY(){
		return (int)f_playposy;
	}
	
	public float getmana()
	{
		return mana;
	}
	
	public float getleben()
	{
		return leben;
	}
	
	public void setPosition(float f_posx, float f_posy) {
		f_playposx = f_posx;
		f_playposy = f_posy;
		return;
	}
	
	public float posX(){
		return f_playposx;
	}
	
	public float posY(){
		return f_playposy;
	}
	
	public boolean getResetMap(){
		return resetMap;
	}
	
	public void setResetMap(){
		resetMap = false;
	}
	
	public void stop(){
		speedX = 0;
		speedY = 0;
	}
	
}
