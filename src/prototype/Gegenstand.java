package prototype;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import player.PlayerIO;


public class Gegenstand {
	private int id;
	private float f_Gegenstandposy_x;
	private float f_Gegenstandposy_y;
	private Rectangle bounding;
	private static List<Gegenstand> gegenstaende;
	private static BufferedImage Lebenstrank; 
	private static BufferedImage Manatrank; 
	private static BufferedImage Waffe;
	private static BufferedImage Ruestung;
	
	
	static {
		try {
			
			Lebenstrank = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/Lebenstrank.png"));
			Manatrank = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/Manatrank.png"));
			Waffe = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/Waffe.png"));
			Ruestung = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/Ruestung.png"));



			} catch (IOException e) {e.printStackTrace();}
        }


	public Gegenstand(float x, float y, int id, List<Gegenstand> gegenstaende) {
		this.id=id;
		Gegenstand.gegenstaende=gegenstaende;
		this.f_Gegenstandposy_x = x;
		this.f_Gegenstandposy_y = y;
	    bounding = new Rectangle((int)x, (int)y, Lebenstrank.getWidth(), Lebenstrank.getHeight());

	}
	


	public static void update(float timeSinceLastFrame) {
		for(int i = 0; i<gegenstaende.size(); i++){
			Gegenstand e = gegenstaende.get(i);
			
			if(PlayerIO.getBounding().intersects(e.getBounding())){
				if (e.getid()==1&&PlayerIO.getgeld()>4){
					PlayerIO.setlebenstrank(1);
					PlayerIO.setgeld(-5);

					gegenstaende.remove(i);
					
				}
				if (e.getid()==2&&PlayerIO.getgeld()>4){
					PlayerIO.setmanatrank(1);
					PlayerIO.setgeld(-5);

					gegenstaende.remove(i);
					
				}
				if (e.getid()==3&&PlayerIO.getgeld()>19){
					PlayerIO.setgeld(-20);

					gegenstaende.remove(i);
					
				}
				
				if (e.getid()==41&&PlayerIO.getgeld()>19){
					PlayerIO.setgeld(-20);

					gegenstaende.remove(i);
					
				}
				
				
				
			}
		}
		
		

	}

	public Rectangle getBounding() {
		return bounding;
	}
	public static  BufferedImage getLook() {
	
			return Lebenstrank;
    }
	public static BufferedImage getLook2() {
		
			return Manatrank;
		
	

	}
	public static BufferedImage getLook3() {
		
		return Waffe;
	


}
	public static BufferedImage getLook4() {
		
		return Ruestung;
	


}


	public int getX() {

		return (int) f_Gegenstandposy_x;
	}

	public int getY() {
		return (int) f_Gegenstandposy_y;
	}

	public int getid() {
		return (int) id;
	}



}