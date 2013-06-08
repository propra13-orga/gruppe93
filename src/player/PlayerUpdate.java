package player;

public class PlayerUpdate {
	
	static void update(float frametime){
		Bewegen.bewegen(frametime);
		Kollision.kollision();
		Schiessen.schussGen(frametime);
	}

}
