package tiles;

import java.awt.Rectangle;

abstract class TileType implements TileMethod{
	
	protected int positionX;
	protected int positionY;
	protected boolean isBlockiert=false;
	protected boolean isTrap=false;
	protected boolean isTeleporter=false;
	protected boolean isExit=false;
	protected boolean isShop=false;
	protected Rectangle bounding;
	protected final short feldGroesse=40;
	
	public TileType(int positionX, int positionY)
	{
		this.positionX=positionX;
		this.positionY=positionY;
		bounding = new Rectangle(positionX, positionY, feldGroesse, feldGroesse);
	}
	
	public int getPositionX(){
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}
	
	public boolean isBlockiert() {
		return isBlockiert;
	}

	public boolean isTrap() {
		return isTrap;
	}

	public boolean isTeleporter() {
		return isTeleporter;
	} 

	public boolean isExit() {
		return isExit;
	} 

	public boolean isShop() {
		return isShop;
	} 
	
	public Rectangle getBounding() {
		return bounding;
	}
	
	public short getfeldGroesse(){
		return feldGroesse;
	}
	
}
