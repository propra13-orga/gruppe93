package typen;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tiles.Geländetyp;
import tiles.Tile;

public class Err extends Geländetyp{
	private static BufferedImage bimg;
	private boolean blockiert = false;		// Legt fest ob Feld passierbar ist
	private boolean trap = false; 			// Legt fest ob Feld eine Falle ist
	private boolean tranporter = false;		// Legt fest ob Feld ein Teleporter ist
	private boolean exit = false;			// Legt fest ob Feld ein Ende ist
	private boolean shop = false;			// Legt fest ob Feld ein Shop eingang ist
	private boolean checkpoint = false;
	
	static{
		try{
			bimg = ImageIO.read(Tile.class.getClassLoader().getResourceAsStream("gfx/Tiles/err.png"));
		}catch (IOException e) {e.printStackTrace();}
	}

	@Override
	public BufferedImage getBimg() {
		// TODO Auto-generated method stub
		return bimg;
	}

	@Override
	public boolean getBlockiert() {
		// TODO Auto-generated method stub
		return blockiert;
	}

	@Override
	public boolean getTrap() {
		// TODO Auto-generated method stub
		return trap;
	}

	@Override
	public boolean getTransporter() {
		// TODO Auto-generated method stub
		return tranporter;
	}

	@Override
	public boolean getExit() {
		// TODO Auto-generated method stub
		return exit;
	}

	@Override
	public boolean getShop() {
		// TODO Auto-generated method stub
		return shop;
	}

	@Override
	public boolean getCheckpoint() {
		// TODO Auto-generated method stub
		return checkpoint;
	}


}
