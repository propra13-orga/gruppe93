package prototype;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

import player.Player;
import player.PlayerIO;
import tiles.Tile;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Zauber {
	private static BufferedImage bimg;
	private static BufferedImage bimg2;
	private static BufferedImage bimg3;
	private static BufferedImage bimg4;
	private static BufferedImage bimg5;
	private float f_Zaubergeschwindigkeitx;
	private float f_Zaubergeschwindigkeity;
	private Rectangle bounding;
	private static List<Zauber> Zaubern;
	private float f_playposx;
	private float f_playposy;
	private float existiertseit = 0;
	private float darfexistieren;
	private int zauberid;
	private int kartenPositionX;
	private int kartenPositionY;
	private int x_Tiles;
	private int y_Tiles;
	private static boolean besiegbar = true;
	private Map map;
	static {
		try {
			bimg = ImageIO.read(Zauber.class.getClassLoader()
					.getResourceAsStream("gfx/Zauber.png"));
			bimg2 = ImageIO.read(Zauber.class.getClassLoader()
					.getResourceAsStream("gfx/circle.png"));
			bimg3 = ImageIO.read(Zauber.class.getClassLoader()
					.getResourceAsStream("gfx/Dragoran/Dragoranspell3.png"));
			bimg4 = ImageIO.read(Zauber.class.getClassLoader()
					.getResourceAsStream("gfx/circle2.png"));
			bimg5 = ImageIO.read(Zauber.class.getClassLoader()
					.getResourceAsStream("gfx/circle3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Zauber(float x, float y, float speedx, float speedy, int Zauberid,
			List<Zauber> Zaubern) {
		this.zauberid = Zauberid;
		if (zauberid == 1) {
			darfexistieren = (float) 0.5;
			bounding = new Rectangle((int) x, (int) y, bimg.getWidth(),
					bimg.getHeight());
		}
		if (zauberid == 2) {
			darfexistieren = (float) 3;
			bounding = new Rectangle((int) x, (int) y, bimg2.getWidth(),
					bimg2.getHeight());
		}
		if (zauberid == 3) {
			darfexistieren = (float) 3;
			bounding = new Rectangle((int) x - 5, (int) y - 5,
					bimg3.getWidth() - 10, bimg3.getHeight() - 10);
		}
		if (zauberid == 4) {
			darfexistieren = (float) 8;
			bounding = new Rectangle((int) x - 5, (int) y - 5,
					bimg2.getWidth() - 10, bimg2.getHeight() - 10);
		}
		if (zauberid == 5) {
			darfexistieren = (float) 3;
			besiegbar = false;
			bounding = new Rectangle((int) x - 5, (int) y - 5,
					bimg.getWidth() - 10, bimg.getHeight() - 10);

		}
		this.f_playposx = x;
		this.f_playposy = y;
		this.f_Zaubergeschwindigkeitx = speedx;
		this.f_Zaubergeschwindigkeity = speedy;
		this.setZaubern(Zaubern);
	}

	public void update(float timeSinceLastFrame) {
		if (zauberid==1 || zauberid==3)
		{
		
		
		map = Player.getMap();

		kartenPositionX = (int) (f_playposx/Tile.getFeldGroesse());
		kartenPositionY = (int) (f_playposy/Tile.getFeldGroesse());
		x_Tiles = map.getXTiles();
		y_Tiles = map.getYTiles();
		
	
		for(int tilex = kartenPositionX; tilex <= kartenPositionX + 1; tilex++){//hier muss <= geprueft werden, damit an kartenposition+1 auch eine ueberpruefung stattfindet. an kartenpos -1 muss dafuer nix gemacht werden da wir die obere linke ecke sowieso als Ausgangsbasis nehmen
			if(tilex<0)tilex=0;	//sorgt dafuer, daß beim ueberschreiten der levelgrenzen kein absturz auftritt
			if(tilex>x_Tiles)break;
			for(int tiley = kartenPositionY; tiley<= kartenPositionY + 1; tiley++){
				if(tiley<0)tiley=0;
				if(tiley>y_Tiles)break;
				if(map.getTile(tilex, tiley).getBlockiert()&&bounding.intersects(map.getTile(tilex, tiley).getBounding()))//wenn hier abprallen gebrueft werden muss und die richtung nicht schon geaendert wurde
				{
					getZaubern().remove(this);

				}
			}
		}
		}
		

					
					
					
					
		existiertseit += timeSinceLastFrame;
		if (existiertseit > darfexistieren) {
			getZaubern().remove(this);
			if (zauberid == 5) {
				besiegbar = true;

			}
		}

		if (zauberid != 5) {

			f_playposx += f_Zaubergeschwindigkeitx * timeSinceLastFrame;
			f_playposy += f_Zaubergeschwindigkeity * timeSinceLastFrame;
			bounding.x = (int) f_playposx;
			bounding.y = (int) f_playposy;

		}

	}

	public Rectangle getBounding() {
		return bounding;
	}

	public BufferedImage getLook() {
		if (zauberid == 3) {
			return bimg3;
		}
		if (zauberid == 1) {
			return bimg;
		}
		if (zauberid == 4) {
			return bimg4;
		}
		if (zauberid == 5) {
			return bimg5;
		}
		return bimg2;

	}

	public int getX() {

		return (int) f_playposx;
	}

	public int getY() {
		return (int) f_playposy;
	}

	public int getid() {
		return (int) zauberid;
	}

	public List<Zauber> getZaubern() {
		return Zaubern;
	}

	public void setZaubern(List<Zauber> zaubern) {
		Zaubern = zaubern;
	}

	public static boolean getbesiegbar() {
		return besiegbar;
	}

}