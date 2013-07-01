package player;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import prototype.Frame;
import prototype.Keyboard;
import prototype.Zauber;

public class  Schiessen implements MouseListener {
	
	private static final float schussfrequenz= 0.5f;
	private static float ZeitSeitLetztemSchuss=1;
	private static float abklingzeitZauber5;
	private static float abklingzeittrank;
	private static int manatrank=1; //Anzahl an Traenken
	private static int lebenstrank=1;
	private static float f_playposx;
	private static float f_playposy;
	private static float mana;
	private static float leben;
	private static List<Zauber>Zaubern;
	
	
	 static void schussGen (float frametime)
	{

		
		Zaubern=Player.getZaubern();
		mana = Player.getF_mana();
		leben = Player.getF_leben();
		f_playposx = Player.getF_PlayerPositionX();
		f_playposy = Player.getF_PlayerPositionY();
	
		
		//Zauber generierung jetzt ueber Pfeiltasten und in eigener Methode
		
	
		if(Keyboard.isKeyDown(KeyEvent.VK_1)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>200)
		{
			ZeitSeitLetztemSchuss = 0;
			float Zauberrichtung_x=0;
			float Zauberrichtung_y=0;
			Zaubern.add(new Zauber(f_playposx-100, f_playposy-100, Zauberrichtung_x, Zauberrichtung_y,2, Zaubern));
			mana -= 200;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_2)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>200)
		{
			ZeitSeitLetztemSchuss = 0;
			float Zauberrichtung_x=0;
			float Zauberrichtung_y=0;
			Zaubern.add(new Zauber(f_playposx-100, f_playposy-100, Zauberrichtung_x, Zauberrichtung_y,4, Zaubern));
			mana -= 200;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_3)&&ZeitSeitLetztemSchuss>schussfrequenz&&mana>200&&abklingzeitZauber5>20)
		{
			ZeitSeitLetztemSchuss = 0;
			abklingzeitZauber5 = 0;
			float Zauberrichtung_x=0;
			float Zauberrichtung_y=0;
			Zaubern.add(new Zauber(f_playposx, f_playposy, Zauberrichtung_x, Zauberrichtung_y,5, Zaubern));
			mana -=200;
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
//		while(ZeitSeitLetztemSchuss>schussfrequenz&&mana>20){

		 PointerInfo info = MouseInfo.getPointerInfo(); //Für spätere Steuerung mit der Maus
		    
		    Point location = info.getLocation();
		    
		    
		   
			float entfernung=(float) Math.sqrt((location.x-Frame.fensterbreite/2)*(location.x-Frame.fensterbreite/2)+(location.y-Frame.fensterhoehe/2)*(location.y-Frame.fensterhoehe/2));

			Zaubern.add(new Zauber(f_playposx, f_playposy, (location.x-Frame.fensterbreite/2)/entfernung*1000 ,(location.y-Frame.fensterhoehe/2)/entfernung*1000,1,  Zaubern));
			mana -= 10;
			Player.setF_mana(mana);
//		}


	}
		
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
