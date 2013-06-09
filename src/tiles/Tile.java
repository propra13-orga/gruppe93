package tiles;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile {
	
	//Basisdaten
	
	private int tileTyp;					// Legt Textur fest
	private int positionX;					// Position auf X
	private int positionY;					// Position auf Y
	private Rectangle bounding;				// Aussenwand fuer Kollisionen
	private static short feldGroesse=40;	// Seitenllänge
	
	//Modifikatoren
	private boolean blockiert = false;		// Legt fest ob Feld passierbar ist
	private boolean trap = false; 			// Legt fest ob Feld eine Falle ist
	private boolean tranporter = false;		// Legt fest ob Feld ein Teleporter ist
	private boolean exit = false;			// Legt fest ob Feld ein Ende ist
	private boolean shop = false;			// Legt fest ob FEld ein Shop eingang ist
	
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
	public void setTileTyp(int tileTyp) {
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
	
	/**
	 * @return feldGroesse
	 */
	public static short getFeldGroesse(){
		return feldGroesse;
	}
	

}
