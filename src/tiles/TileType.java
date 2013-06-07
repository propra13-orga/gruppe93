package tiles;

import java.awt.image.BufferedImage;

abstract class TileType {
	
	protected int positionX;
	protected int positionY;
	protected static BufferedImage bimg;
	protected boolean isBlockiert=false;
	protected boolean isTrap=false;
	protected boolean isTeleporter=false;
	protected boolean isExit=false;
	protected boolean isShop=false;
	
	public TileType(int positionX, int positionY)
	{
		this.positionX=positionX;
		this.positionY=positionY;
	}

	/**
	 * @return the positionX
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}
	
	/**
	 * @return the Textur
	 */
	public static BufferedImage getBimg() {
		return bimg;
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
	
	

}
