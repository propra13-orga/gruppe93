package player;

import prototype.Map;

public class PlayerUpdate {
	
	private static boolean isAlive = true;
	private static final float manaRegenaration = 40;
	private static long timeOfDeath = 0;
	
	static void update(float frametime){
		
		if(!isAlive&&(System.currentTimeMillis()-timeOfDeath)>3000)respawn();
		if(!isAlive)return;
		
		Bewegen.bewegen(frametime);
		Kollision.kollision();
		Schiessen.schussGen(frametime);
		
		if(Player.getF_mana()<1000)Player.setF_mana(Player.getF_mana()+manaRegenaration);
		if(Player.getF_leben()<=0)spielerTot();
		
	}
	
	private static void spielerTot()
	{
		int gegneranzahl;
		
		isAlive = false;					
		Player.getMap().setSpielerTod(true);
		timeOfDeath = System.currentTimeMillis();
		gegneranzahl=Player.getEnemys().size();// Festellung der Gegnerzahl
		for(int i = 0; i<gegneranzahl; i++){
		Player.getEnemys().remove(0);}
					
	}
	
	public static void respawn(){
		isAlive = true;
		Player.getMap().setSpielerTod(false);
		Player.setF_leben(1000);
		Player.setF_mana(1000);
		Map.resetMap = true;
	    }

}
