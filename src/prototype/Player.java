 package prototype;

import java.awt.Rectangle;
import java.util.List;

import javax.imageio.ImageIO;
@Deprecated
public class Player {
	
	private static Rectangle bounding;
	private boolean isAlive = true;
	private boolean bCheck = true; // Aktiviert Kollisionsabfrage
	private List<Zauber>Zaubern;
	private List<Gegner>Enemys;		//hab das static rausgenommen um die Warnmeldung auszuschalten.


	private int gegneranzahl;

	public void update(float frametime){

		if(bCheck){
			gegnerKolision();	
		}

		//Taste erzeugt Gegner zum testen
	}//update Ende

		
	private void gegnerKolision()
	{
		//Kollision fuer Spieler-Gegner und Zauber-Gegner und Gegnerzauber-Spieler
		
		for(int i = 0; i<Enemys.size(); i++){
			Gegner e = Enemys.get(i);
			
			if(bounding.intersects(e.getBounding())){
				leben=leben-(float)10;
			}
		}
		for(int a=0; a<Zaubern.size(); a++){
			Zauber f = Zaubern.get(a);
			if(bounding.intersects(f.getBounding())){
				if (f.getid()==3){
					leben=leben-50;
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
				//Schussschaden an Gegner
				
				}
			}
		}
	}

	//DEBUG Methoden bzgl der Kollisions und Sterbepruefung
	public void bCheckOn(){
		bCheck = true;
	}
	
	public void bCheckOff(){
		bCheck = false;
	}

