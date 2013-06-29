package player;

import java.util.List;

import prototype.Gegner;
import prototype.Zauber;

class GegnerUpdate {
	
	static void gegnerKolision()
	{
		
		List<Gegner> Enemys = Player.getEnemys();
		List<Zauber> Zaubern = Player.getZaubern();
		
	
		//Kollision fuer Spieler-Gegner und Zauber-Gegner und Gegnerzauber-Spieler 
        if (Zauber.getbesiegbar()==true){
		for(int i = 0; i<Enemys.size(); i++){
			Gegner e = Enemys.get(i);
			
			if(Player.getBounding().intersects(e.getBounding())){
			
				Player.setF_leben(Player.getF_leben()-10f);
				
			}
		}
		for(int a=0; a<Zaubern.size(); a++){
			Zauber f = Zaubern.get(a);
			if(Player.getBounding().intersects(f.getBounding())){
				if (f.getid()==3){
			
					Player.setF_leben(Player.getF_leben()-50f);
					
					
				}
			}
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
		    	else
		    	if (f.getid()==4){
					e.setSpeed((float) 0.4);
			    	}
				//Schussschaden an Gegner
				
				}
			}
		}
	}

}
