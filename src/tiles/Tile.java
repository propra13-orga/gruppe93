package tiles;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import typen.Boden;

public class Tile {
	
	//Basisdaten
	
	private int positionX;					// Position auf X
	private int positionY;					// Position auf Y
	private Rectangle bounding;				// Aussenwand fuer Kollisionen
	private static short feldGroesse=40;	// Seitenllaenge
	
	//Modifikatoren
	private Gelaendetyp typ;
	
	public Tile(int positionX, int positionY){
		this.setPositionX(positionX*40);
		this.setPositionY(positionY*40);
		this.setTileTyp(new Boden());	//nicht unbedingt erforderlich, aber so kann ein tile schonmal nicht ohne typ sein
		bounding = new Rectangle(positionX, positionY, feldGroesse, feldGroesse);
	}

	/**
	 * @return the tileTyp
	 */
	public Gelaendetyp getTileTyp() {
		return typ;
	}

	/**
	 * @param tileTyp the tileTyp to set
	 */
	public void setTileTyp(Gelaendetyp typ) {
		this.typ = typ;
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
	public boolean getBlockiert() {
		return typ.getBlockiert();
	}


	/**
	 * @return the trap
	 */
	public boolean getTrap() {
		return typ.getTrap();
	}

	/**
	 * @return the exit
	 */
	public boolean getExit() {
		return typ.getExit();
	}

	/**
	 * @return the tranporter
	 */
	public boolean getTranporter() {
		return typ.getTransporter();
	}

	/**
	 * @return the shop
	 */
	public boolean getShop() {
		return typ.getShop();
	}
	
	/**
	 * @return textur
	 */
	public BufferedImage getBimg(){
		return typ.getBimg();
	}
	
	/**
	 * @return feldGroesse
	 */
	public static short getFeldGroesse(){
		return feldGroesse;
	}

	public boolean getCheckpoint() {
		return typ.getCheckpoint();
	}
}
