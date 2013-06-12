package tiles;

import java.awt.image.BufferedImage;

public abstract class Geländetyp {
	//METHODEN
	abstract public BufferedImage getBimg();

	abstract public boolean getBlockiert();
	
	abstract public boolean getTrap();
	
	abstract public boolean getTransporter();
	
	abstract public boolean getExit();
	
	abstract public boolean getShop();
	
	abstract public boolean getCheckpoint();
	//Die static Methoden stehen hier nicht drin
}
