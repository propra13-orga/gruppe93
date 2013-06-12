package player;

import java.awt.event.KeyEvent;
import java.util.List;

import prototype.Keyboard;
import prototype.Zauber;

class Schiessen {
	
	private static final float schussfrequenz= 0.5f;
	private static float ZeitSeitLetztemSchuss=1;
	private static float abklingzeitZauber5;
	
	static void schussGen(float frametime)
	{
		List<Zauber>Zaubern=Player.getZaubern();
		float mana = Player.getF_mana();
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
		
		Player.setF_mana(mana);
		ZeitSeitLetztemSchuss += frametime;
		abklingzeitZauber5 += frametime;
		
	}
	public static float getZeitSeitLetztemSchuss() {
		return ZeitSeitLetztemSchuss;
	}
	public static float abklingzeitZauber5() {
		return abklingzeitZauber5;
	}

}
