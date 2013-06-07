package tiles;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public interface TileMethod{
	
	
	public int getPositionX();

	public int getPositionY();
	
	public BufferedImage getBimg();
	
	public boolean isBlockiert();

	public boolean isTrap();

	public boolean isTeleporter(); 

	public boolean isExit(); 

	public boolean isShop(); 
	
	public Rectangle getBounding();
	
	public short getfeldGroesse();

}
