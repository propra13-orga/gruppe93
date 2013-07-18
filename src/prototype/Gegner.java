package prototype;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import player.PlayerIO;

public class Gegner {
	private static BufferedImage Gengar;
	private static BufferedImage mewtwo;
	private static BufferedImage glumanda;
	private static BufferedImage camaub;
	private static BufferedImage[] Dragoran= new BufferedImage[16];
	private static BufferedImage[] Dragoranfly= new BufferedImage[16];
	private float f_Gegnerposy_x;
	private float f_Gegnerposy_y;
	private Rectangle bounding;
	private float existiertseit;
	private float entfernung; //entfernung zwischen Gegner und Spieler
	private float gegnergeschwindigkeit;
	private float zufallszahl; //fuer zufallsbasierte Bewegung 
	private float zufallszahl2;//fuer  zufallsbasierte Geschosse
	private float zufallszahl3;//fuer  zufallsbasierten Gegnerspawn (mewtwo)
	private float reichweite=700; //legt fast ab welcher Entfernung zum Spieler die Gegner angreifen
	float leben; 
	private List<Gegner> Enemys;
	private List<Zauber>Zaubern;
	private static float zeitBisZurNaechstenAnimation = (float) -0.5; //Zaehlt mit frametime hoch um zu entscheiden wann welche Animation kommt
	private final static float Animationsdauer = 0.5f; //Wie lange ein Animationsdurchgang dauert d.h 4 Bilder in x Sekunden
	private int animationsrichtung=1; // Animationsrichtungen 1-4 fuer 4 Richtungen
	private int gegnerid; //Entscheidet welcher Gegner spawnt
	private float xadd; //x Abstand zum Spieler /Abstand von Spieler
	private float yadd; //y Abstand zum Spieler /Abstand von SpielerM
	private int phase=20; //Wechsel zwischen Flug- und Bodenphase in 20 Sekunden Rhythmus
	private float phasecounter=0;
	private float winkel=90; //steuert Streuung der Feuerbaelle von Dragoran in Grad
	private float Dragorangeschossgeschwindigkeit=500;
	private float glumandageschossfrequenz=1;
	private float glumandageschossfrequenzzaehler=1;
	private float speedchange=1;
	private float gegnerspawnfrequenz; //fuer Mewtwo
	private double spawnspeedup=0; //spawns werden immer schnell bei Mewtwo
	private boolean visible = true;
	static {
		try {
			Gengar = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/gengar.png"));
			mewtwo = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/mewtwo.png"));
			camaub = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/camaub.png"));
			glumanda= ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/glumanda.png"));
			Dragoran[0]  = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/1.png"));
			Dragoran[1] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/2.png"));
			Dragoran[2]= ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/3.png"));
			Dragoran[3] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/4.png"));
			Dragoran[4]  = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/11.png"));
			Dragoran[5] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/12.png"));
			Dragoran[6]= ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/13.png"));
			Dragoran[7] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/14.png"));
			Dragoran[8]  = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/21.png"));
			Dragoran[9] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/22.png"));
			Dragoran[10]= ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/23.png"));
			Dragoran[11] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/24.png"));
			Dragoran[12]  = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/31.png"));
			Dragoran[13] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/32.png"));
			Dragoran[14]= ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/33.png"));
			Dragoran[15] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/34.png"));
			Dragoranfly[0]  = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/41.png"));
			Dragoranfly[1] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/42.png"));
			Dragoranfly[2]= ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/43.png"));
			Dragoranfly[3] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/44.png"));
			Dragoranfly[4]  = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/51.png"));
			Dragoranfly[5] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/52.png"));
			Dragoranfly[6]= ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/53.png"));
			Dragoranfly[7] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/54.png"));
			Dragoranfly[8]  = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/61.png"));
			Dragoranfly[9] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/62.png"));
			Dragoranfly[10]= ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/63.png"));
			Dragoranfly[11] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/63.png"));
			Dragoranfly[12]  = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/71.png"));
			Dragoranfly[13] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/72.png"));
			Dragoranfly[14]= ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/73.png"));
			Dragoranfly[15] = ImageIO.read(Gegner.class.getClassLoader().getResourceAsStream("gfx/Dragoran/74.png"));
			
			} catch (IOException e) {e.printStackTrace();}
}
	
	public Gegner( float Gegnerx, float Gegnery,int Gegnerid, List<Gegner> Enemys,List<Zauber>Zaubern){
        this.Zaubern=Zaubern;
		this.gegnerid=Gegnerid;
		this.f_Gegnerposy_x = Gegnerx;
		this.f_Gegnerposy_y = Gegnery;
		if (gegnerid==1){   //Attribute fuer Gegner 1 (Gengar)
		     bounding = new Rectangle((int)Gegnerx, (int)Gegnery, Gengar.getWidth(), Gengar.getHeight());
		     leben=200;
		     gegnergeschwindigkeit=300;
		    }
		if (gegnerid==2){ //Attribute fuer Gegner 2 (Dragoran)
			bounding = new Rectangle((int)Gegnerx+10, (int)Gegnery+5, Dragoran[0].getWidth()-20, Dragoran[0].getHeight()-10);
			leben=6000;
			gegnergeschwindigkeit=40;
			}
		if (gegnerid==3){ //Attribute fuer Gegner 3 (glumanda)
		     bounding = new Rectangle((int)Gegnerx, (int)Gegnery, Gengar.getWidth(), Gengar.getHeight());
			leben=400;
			gegnergeschwindigkeit=200;
			}
		if (gegnerid==4){ //Attribute fuer Gegner 4 (mewtwo)
		     bounding = new Rectangle((int)Gegnerx, (int)Gegnery, mewtwo.getWidth(), mewtwo.getHeight());
			leben=4000;
			gegnergeschwindigkeit=0;
			}
		this.Enemys=Enemys;
	}
	
	public void update(float timeSinceLastFrame){
		existiertseit+=timeSinceLastFrame;
		if(existiertseit>=4){
			zufallszahl=(float)(Math.random()-0.5);
			existiertseit=0;
		}
		entfernung=(float) Math.sqrt((PlayerIO.getBounding().x-f_Gegnerposy_x)*(PlayerIO.getBounding().x-f_Gegnerposy_x)+(PlayerIO.getBounding().y-f_Gegnerposy_y)*(PlayerIO.getBounding().y-f_Gegnerposy_y));

		xadd=(PlayerIO.getBounding().x-f_Gegnerposy_x)/entfernung;
	    yadd=(PlayerIO.getBounding().y-f_Gegnerposy_y)/entfernung;
	    
	    
	    if (gegnerid==4){ //Mwetwo
			gegnerspawnfrequenz+=timeSinceLastFrame;
			if (gegnerspawnfrequenz>(4.6-spawnspeedup)){
				zufallszahl3=(float)(Math.random());
				if (zufallszahl3>0.5){
					Enemys.add(new Gegner((float) (Math.abs(zufallszahl3-0.5))*40*60+40, (float) (Math.abs(zufallszahl3-0.5))*40*46+40,1, Enemys,Zaubern));
					
				}else{
					Enemys.add(new Gegner((float) (Math.abs(zufallszahl3-0.5))*40*60+40, (float) (Math.abs(zufallszahl3-0.5))*40+40,3, Enemys,Zaubern));

				}
				gegnerspawnfrequenz=0;
				spawnspeedup=spawnspeedup+0.1;

				
			}

	        
		}
		
		
		if (gegnerid==1){ //Bewegung Gengar
			
	          if(entfernung<reichweite){
		            f_Gegnerposy_x= (f_Gegnerposy_x+xadd*timeSinceLastFrame*gegnergeschwindigkeit*speedchange+zufallszahl*(-((existiertseit-2)*(existiertseit-2))+4)); //-(x-2)^2+4
		            f_Gegnerposy_y=f_Gegnerposy_y+yadd*timeSinceLastFrame*gegnergeschwindigkeit*speedchange+zufallszahl*(-((existiertseit-2)*(existiertseit-2))+4);
		            bounding.x = (int)f_Gegnerposy_x;
		            bounding.y = (int)f_Gegnerposy_y;}
		}
		if (gegnerid==3){ //Bewegung glumanda
	          if(entfernung<reichweite){
	        	  glumandageschossfrequenzzaehler=glumandageschossfrequenzzaehler+timeSinceLastFrame;
	        	  
		            f_Gegnerposy_x= (f_Gegnerposy_x+xadd*timeSinceLastFrame*gegnergeschwindigkeit*speedchange+zufallszahl*(-((existiertseit-2)*(existiertseit-2))+4)); //-(x-2)^2+4
		            f_Gegnerposy_y=f_Gegnerposy_y+yadd*timeSinceLastFrame*gegnergeschwindigkeit*speedchange+zufallszahl*(-((existiertseit-2)*(existiertseit-2))+4);
		            bounding.x = (int)f_Gegnerposy_x;
		            bounding.y = (int)f_Gegnerposy_y;
	          if (glumandageschossfrequenzzaehler>glumandageschossfrequenz){
		     		 Zaubern.add(new Zauber(f_Gegnerposy_x, f_Gegnerposy_y, xadd*800, yadd*800, 3, Zaubern));
		     		glumandageschossfrequenzzaehler=0;
              	}
	          }
		}
		 //Animationsrichtung
		 if(yadd<0&&xadd>0)animationsrichtung=2;
         if(yadd<0&&xadd<0)animationsrichtung=3;
         if(yadd>0&&xadd<0)animationsrichtung=0;
         if(yadd>0&&xadd>0)animationsrichtung=1;
		if (gegnerid==2){ //Bewegung Dragoran
				
					f_Gegnerposy_x=f_Gegnerposy_x+xadd*gegnergeschwindigkeit*speedchange*timeSinceLastFrame;
					f_Gegnerposy_y=f_Gegnerposy_y+yadd*gegnergeschwindigkeit*speedchange*timeSinceLastFrame;
					bounding.x = (int)f_Gegnerposy_x;
		            bounding.y = (int)f_Gegnerposy_y;
		            //Animationsrichtung
//		            if(yadd<0&&xadd>0)animationsrichtung=2;
//		            if(yadd<0&&xadd<0)animationsrichtung=3;
//		            if(yadd>0&&xadd<0)animationsrichtung=0;
//		            if(yadd>0&&xadd>0)animationsrichtung=1;
		            //zufallsbasierte Geschossrichtung
		            if (zeitBisZurNaechstenAnimation>0.15){
		            	zufallszahl2=(float)(Math.random()*winkel-winkel/2);
		            	if(phasecounter<10){
		            		gegnergeschwindigkeit=40;	
		            	    xadd = (float) ((this.xadd * Math.cos(Math.toRadians(zufallszahl2))) - (this.yadd * Math.sin(Math.toRadians(zufallszahl2))));
		            	    yadd = (float) ((this.xadd * Math.sin(Math.toRadians(zufallszahl2))) + (this.yadd * Math.cos(Math.toRadians(zufallszahl2))));
		       
	            	if (animationsrichtung==0 || animationsrichtung==3){
			     		 Zaubern.add(new Zauber(f_Gegnerposy_x, f_Gegnerposy_y, xadd*Dragorangeschossgeschwindigkeit, yadd*Dragorangeschossgeschwindigkeit, 3, Zaubern));
	                 	}
		            	if(animationsrichtung==1 || animationsrichtung==2){
		           		 Zaubern.add(new Zauber(f_Gegnerposy_x+50, f_Gegnerposy_y, xadd*Dragorangeschossgeschwindigkeit, yadd*Dragorangeschossgeschwindigkeit, 3, Zaubern));
		            	}
		            	}else gegnergeschwindigkeit=200; 
		            
				  
			  }
		           
			 
		}
		
		
		
	    
		  //wenn Gegner stirbt 
	    if(leben<0){
	    	PlayerIO.setgeld(1);
	    	visible = false;
			}
	    
	    //Animationszaehler
		zeitBisZurNaechstenAnimation=zeitBisZurNaechstenAnimation+timeSinceLastFrame;
		if(zeitBisZurNaechstenAnimation>Animationsdauer)zeitBisZurNaechstenAnimation = (float) -0.5;
		phasecounter=phasecounter+timeSinceLastFrame;
		if(phasecounter>phase)  phasecounter=0;
		
		 speedchange=1;

	}
	
	public Rectangle getBounding(){
		return bounding;
	}
	
	public BufferedImage getLook(){
		if (gegnerid==3){
			return glumanda;
		}
		if (gegnerid==4){
			return mewtwo;
		}
		if (gegnerid==2){
			if(phasecounter<10){
		if(Dragoran.length==0)return null;
		for(int i = 0; i<4; i++){
			if(Math.abs(zeitBisZurNaechstenAnimation)<(float)(Animationsdauer/4*(i+1))){
				return Dragoran[i+4*animationsrichtung];}

			
		
		}
		return Dragoran[3+4*animationsrichtung];
			}
			for(int i = 0; i<4; i++){
				if(Math.abs(zeitBisZurNaechstenAnimation)<(float)(Animationsdauer/4*(i+1))){
					return Dragoranfly[i+4*animationsrichtung];}

				
			
			}
			return Dragoranfly[3+4*animationsrichtung];
				
			
			

	}	
	
	return Gengar;
	}
	
	public int getX(){
		return (int)f_Gegnerposy_x;
	}
	
	public int getY(){
		return (int)f_Gegnerposy_y;
	}
	public void setLeben(int x){
		leben=leben-x; //Schussschaden
	}
	public int getid(){
		return gegnerid;
	}

	public void setSpeed(float d) {
		speedchange=d;
		
	}
	public boolean visible(){
		return visible;
	}
	public void remove(){
		Enemys.remove(this);
	}
}