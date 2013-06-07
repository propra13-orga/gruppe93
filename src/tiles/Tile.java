package tiles;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile {
	
	private int tileTyp;
	private int positionX;
	private int positionY;
	private Rectangle bounding;
	private static short feldGroesse=40;
	
	//Modificatoren
	private boolean blockiert = false;
	private boolean trap = false; 
	private boolean tranporter = false;
	private boolean exit = false;
	private boolean shop = false;
	
	Tile(int tileTyp, int positionX, int positionY){
		this.setTileTyp(tileTyp);
		this.setPositionX(positionX);
		this.setPositionY(positionY);
		bounding = new Rectangle(positionX, positionY, feldGroesse, feldGroesse);
	}

	/**
	 * @return the tileTyp
	 */
	public int getTileTyp() {
		return tileTyp;
	}

	/**
	 * @param tileTyp the tileTyp to set
	 */
	void setTileTyp(int tileTyp) {
		this.tileTyp = tileTyp;
	}

	/**
	 * @return the positionX
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * @param positionX the positionX to set
	 */
	void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	/**
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * @param positionY the positionY to set
	 */
	void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	/**
	 * @return the bouning
	 */
	public Rectangle getBounding() {
		return bounding;
	}
	
	/**
	 * @return the blockiert
	 */
	public boolean isBlockiert() {
		return blockiert;
	}

	/**
	 * @param blockiert the blockiert to set
	 */
	void setBlockiert(boolean blockiert) {
		this.blockiert = blockiert;
	}

	/**
	 * @return the trap
	 */
	public boolean isTrap() {
		return trap;
	}

	/**
	 * @param trap the trap to set
	 */
	void setTrap(boolean trap) {
		this.trap = trap;
	}

	/**
	 * @return the exit
	 */
	public boolean isExit() {
		return exit;
	}

	/**
	 * @param exit the exit to set
	 */
	void setExit(boolean exit) {
		this.exit = exit;
	}

	/**
	 * @return the tranporter
	 */
	public boolean isTranporter() {
		return tranporter;
	}

	/**
	 * @param tranporter the tranporter to set
	 */
	void setTranporter(boolean tranporter) {
		this.tranporter = tranporter;
	}

	/**
	 * @return the shop
	 */
	public boolean isShop() {
		return shop;
	}

	/**
	 * @param shop the shop to set
	 */
	void setShop(boolean shop) {
		this.shop = shop;
	}
	
	/**
	 * @return textur
	 */
	public BufferedImage getBimg(){
		return TileTextur.getTextur(this.tileTyp);
	}
	

}
