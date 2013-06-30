package player;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.util.List;

import prototype.Keyboard;
import prototype.Zauber;

class Schiessen {
	
	private static final float schussfrequenz= 0.5f;
	private static float ZeitSeitLetztemSchuss=1;
	private static float abklingzeitZauber5;
	private static float abklingzeittrank;
	private static int manatrank=3; //Anzahl an Traenken
	private static int lebenstrank=3;
	
	
	static void schussGen(float frametime)
	{
		 PointerInfo info = MouseInfo.getPointerInfo(); //Für spätere Steuerung mit der Maus
		    
		    Point location = info.getLocation();
		    
		    
//		    System.out.println("x="+location.x+ " y="+location.y); 
		
		List<Zauber>Zaubern=Player.getZaubern();
		float mana = Player.getF_mana();
		float leben = Player.getF_leben();
		float f_playposx = Player.getF_PlayerPositionX();
		float f_playposy = Player.getF_PlayerPositionY();
	
		
		//Zauber generierung jetzt ueber Pfeiltasten und in eigener Methode
		
		if(Keyboard.isKeyDown(KeyEvent.VK_UP)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>30)
		{
			ZeitSeitLetztemSchuss = 0;
			float Zauberrichtung_x=0;
			float Zauberrichtung_y=-1000;
			Zaubern.add(new Zauber(f_playposx, f_playposy, Zauberrichtung_x, Zauberrichtung_y,1,  Zaubern));
			mana -= 30;
		}
		
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>30)
		{
			ZeitSeitLetztemSchuss = 0;
			float Zauberrichtung_x=0;
			float Zauberrichtung_y=1000;
			Zaubern.add(new Zauber(f_playposx, f_playposy, Zauberrichtung_x, Zauberrichtung_y,1,  Zaubern));
			mana -= 30;
		}
		
		if(Keyboard.isKeyDown(KeyEvent.VK_LEFT)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>30)
		{
			ZeitSeitLetztemSchuss = 0;
			float Zauberrichtung_x=-1000;
			float Zauberrichtung_y=0;
			Zaubern.add(new Zauber(f_playposx, f_playposy, Zauberrichtung_x, Zauberrichtung_y,1,  Zaubern));
			mana -= 30;
		}
		
		if(Keyboard.isKeyDown(KeyEvent.VK_RIGHT)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>30)
		{
			ZeitSeitLetztemSchuss = 0;
			float Zauberrichtung_x=1000;
			float Zauberrichtung_y=0;
			Zaubern.add(new Zauber(f_playposx, f_playposy, Zauberrichtung_x, Zauberrichtung_y,1, Zaubern));
			mana -= 30;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_1)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>500)
		{
			ZeitSeitLetztemSchuss = 0;
			float Zauberrichtung_x=0;
			float Zauberrichtung_y=0;
			Zaubern.add(new Zauber(f_playposx-100, f_playposy-100, Zauberrichtung_x, Zauberrichtung_y,2, Zaubern));
			mana -= 500;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_2)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>500)
		{
			ZeitSeitLetztemSchuss = 0;
			float Zauberrichtung_x=0;
			float Zauberrichtung_y=0;
			Zaubern.add(new Zauber(f_playposx-100, f_playposy-100, Zauberrichtung_x, Zauberrichtung_y,4, Zaubern));
			mana -= 500;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_3)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>500&&abklingzeitZauber5>20)
		{
			ZeitSeitLetztemSchuss = 0;
			abklingzeitZauber5 = 0;
			float Zauberrichtung_x=0;
			float Zauberrichtung_y=0;
			Zaubern.add(new Zauber(f_playposx, f_playposy, Zauberrichtung_x, Zauberrichtung_y,5, Zaubern));
			mana -=500;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_Q)&&lebenstrank>0&&abklingzeittrank>10)
		{
			abklingzeittrank = 0;
			lebenstrank=lebenstrank-1;
			leben+=300;
			
			
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_E)&&manatrank>0&&abklingzeittrank>10)
		{
			abklingzeittrank = 0;
		    manatrank=manatrank-1;
		    mana+=300;
		    
		    
		}
		
		Player.setF_mana(mana);
		Player.setF_leben(leben);
		ZeitSeitLetztemSchuss += frametime;
		abklingzeitZauber5 += frametime;
		abklingzeittrank += frametime;
		
	}
	public static float getZeitSeitLetztemSchuss() {
		return ZeitSeitLetztemSchuss;
	}
	public static float abklingzeitZauber5() {
		return abklingzeitZauber5;
	}
	public static float abklingzeittrank() {
		return abklingzeittrank;
	}
	public static int Lebenstrank() {
		return lebenstrank;
	}
	public static int Manatrank() {
		return manatrank;
	}
	public static void setLebenstrank(int x) {
		lebenstrank+=1;
	}
	public static void setManatrank(int x) {
		manatrank+=1;
	}


}
