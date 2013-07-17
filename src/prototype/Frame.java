package prototype;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import multitools.AntiRossi;


import player.PlayerIO;
//import javax.swing.JLabel;
import player.Schiessen;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;	//noetig, damit kein Warning angezeigt wird
	//DEKLARATION
	private BufferStrategy buff;
	private Map map;
	private List<Zauber>Zaubern;
	private List<Gegner>Enemys;
	private List<NPC>npcs;
	private List<Gegenstand> gegenstaende;
	private static BufferedImage interface1; //unsklaliertes original interface.png
	private static BufferedImage interface2; //Zwischenspeicher fuer skaliertes Interface
	private static BufferedImage icon;
	private static BufferedImage icon12;
	private static BufferedImage icon1; 
	private static BufferedImage icon2;
	private static BufferedImage icon3; 
	private static BufferedImage NPCDIALOG;
	private static BufferedImage gold; 
	private static BufferedImage status;
	private int kugelgroesse=152;
	public static int fensterbreite=0;
	public static int fensterhoehe=0;
	private int xVerschiebung=0;	//fuer die Verschiebungen, der alle Objekte ausser dem Spieler unterworfen sind
	private int yVerschiebung=0;
	private int altefensterbreite; //Interface wird erst neu skaliert wenn (fensterbreite!=altefensterbreite||fensterhoehe!=altefensterhoehe) wegen der Performance
	private int altefensterhoehe; //
	Color myColour = new Color(0, 0, 0, 180);
	Color myColour2 = new Color(0, 0, 100, 180);
	
Color statusColor = new Color(171, 25, 84, 180);
	
	int level = 1;
	int ep = 0;
	boolean up1 = false;
	boolean up2 = false;
	Font f = new Font("Arial", Font.BOLD, 17);
	
	static {
		try {
			interface1 = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/interface.png"));
			icon = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/icon.png"));
			icon12 = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/icon12.png"));

			icon1 = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/icon1.png"));
			icon2 = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/icon2.png"));
			icon3 = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/icon3.png"));
			gold = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/gold.png"));
			NPCDIALOG = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/NPCDIALOG.png"));

			status = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/status.png"));

			} catch (IOException e) {e.printStackTrace();}
        }

	
	

	
	
	public Frame(String name,Map map, List<Zauber>Zaubern,List<Gegner>Enemys, List<Gegenstand> gegenstaende, List<NPC> npcs){
		super(name);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.Zaubern=Zaubern;
		this.npcs=npcs;
		this.gegenstaende=gegenstaende;
		this.Enemys=Enemys;
		
		createBufferStrategy(2);	//Buffer mit 2 Bildern wird erzeugt
		buff= getBufferStrategy();	//buff wird die Bufferstrat
		
		setSize(800, 600);			//Damit man, wenn man vom maximierten Modus umschaltet immernoch ne Fenstergroeﬂe hat
		setExtendedState(MAXIMIZED_BOTH);	//Frame ueber den ges. Bilfdschirm
		
//		screen = new Screen();
//		screen.setBounds(0, 0, worldsizex, worldsizey);
//		add(screen);
		
		
		addKeyListener(new Keyboard());
        addMouseListener(new Schiessen());

		this.map=map;
		

	}
	
	
	//METHODEN
	public void nextFrame(){
		fensterbreite=getWidth();
		fensterhoehe=getHeight();
		xVerschiebung=(-PlayerIO.getPlayerPositionX()+fensterbreite/2-PlayerIO.getBimg().getWidth()/2);	//diese Werte liefern unter Beruecksichtigung der Faktoren wie Spielerposition, Insets, Fenstergroeﬂe, Spielergroeﬂe und Statusleistendicke die noetigen Verschiebungen aller Objekte
		yVerschiebung=(-PlayerIO.getPlayerPositionY()+(fensterhoehe-kugelgroesse)/2-PlayerIO.getBimg().getHeight()/2+getInsets().top-getInsets().bottom);
		Graphics g=buff.getDrawGraphics();//uebergibt ein malobjekt aus der bufferstrat
		g.setColor(Color.BLACK);
		g.fillRect(0, 0 , getWidth(), getHeight());

		for(int x = 0; x <= map.getXTiles() ; x++){
			for(int y = 0 ; y<=map.getYTiles() ; y++){
			
				g.drawImage(map.getTile(x, y).getBimg(), map.getTile(x, y).getBounding().x+xVerschiebung, map.getTile(x, y).getBounding().y+yVerschiebung, null);
				
			}
		}
		
		for(int i = 0; i<Zaubern.size(); i++){
			Zauber b = Zaubern.get(i);
			if ((b.getid()==2 && level >= 2)|| b.getid()==4){
			g.drawImage(b.getLook(), b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
			
			}
			else{
				b.setvisible();
			}
		}
		for(int i = 0; i<gegenstaende.size(); i++){
			Gegenstand b = gegenstaende.get(i);
			if (b.getid()==1){
			g.drawImage(Gegenstand.getLook(), b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
			}
			if (b.getid()==2){
				g.drawImage(Gegenstand.getLook2(), b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
			}
			if (b.getid()==3){
				g.drawImage(Gegenstand.getLook3(), b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
			}
			if (b.getid()==4){
				g.drawImage(Gegenstand.getLook4(), b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
			}
		}
		
		if(AntiRossi.getExistiert()){	//gegenspieler wird nur gemalt wenn er existiert
			g.drawImage(AntiRossi.getBimg(),AntiRossi.getX()+xVerschiebung,AntiRossi.getY()+yVerschiebung,null);
		}
		g.drawImage(PlayerIO.getBimg(), (fensterbreite-PlayerIO.getBimg().getWidth())/2, (fensterhoehe-kugelgroesse-PlayerIO.getBimg().getHeight())/2+getInsets().top-getInsets().bottom, null);	//Player in der Mitte des Bildes
		
			
			
	
		for(int i = 0; i<Enemys.size(); i++){
			Gegner c = Enemys.get(i);
			if(c.visible() == true){
				g.drawImage(c.getLook(), c.getX()+xVerschiebung, c.getY()+yVerschiebung, null);
				}
				if(c.visible() == false ){
					ep = ep + 180*level;
					c.remove();
				}
	    }
		
//		Effekte(g);
		for(int i = 0; i<Zaubern.size(); i++){
			Zauber b = Zaubern.get(i);
			if (b.getid()==1 || b.getid()==3 || b.getid()==6){
			g.drawImage(b.getLook(), b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
			}
			if(b.getid()==5 && level >= 3 ){
				g.drawImage(b.getLook(), (fensterbreite-PlayerIO.getBimg().getWidth())/2-10, (fensterhoehe-kugelgroesse-PlayerIO.getBimg().getHeight())/2+getInsets().top-getInsets().bottom-15, null);	
			}
			else{
				b.setbesiegbar();
			}
			
		}
		for(int i = 0; i<npcs.size(); i++){
			NPC b = npcs.get(i);
			g.drawImage(b.getLook(), b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
			if (b.dialogAn){g.drawImage(NPCDIALOG, b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
			}
		}
		
        
			
		g.drawImage(Licht.getLook(),xVerschiebung,yVerschiebung, null);

		//am Ende, damit da nix druebermalt
		dieKugelnUndLeiste(g);//wegen der Komplexitaet ist die Statusleiste in ihrer eigenen Funktion
		Interface(g);

		g.dispose();	//gibt den zeichner wieder frei
		buff.show();	//zeigt dann den aktuellen buffer
	}
	
	
	
	private void dieKugelnUndLeiste(Graphics g){
//		g.setColor(new Color(70, 67, 123));
//		g.fillRect(0, getHeight()-100-getInsets().bottom, getWidth(), 100);	//ist jetzt an der unteren Bildschirmkante fixiert und so bei jeder Aufloesung an der richtigen Stelle
		
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.8f);

        BufferedImage buffImg = new BufferedImage(200, 200,
                                    BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbi = buffImg.createGraphics();
        
        gbi.setPaint(Color.black);         //Manakugel
        gbi.fillOval(0, 0, kugelgroesse*fensterbreite/1920,kugelgroesse*fensterhoehe/1080);
        gbi.setComposite(ac);

        gbi.setPaint(Color.blue);
        gbi.fillRect(0, kugelgroesse*fensterhoehe/1080-((int)PlayerIO.getF_Mana()*kugelgroesse*fensterhoehe/1080/1000), kugelgroesse, kugelgroesse); 
    
        g.drawImage(buffImg, getWidth()-453*fensterbreite/1920-kugelgroesse*fensterbreite/1920, getHeight()-kugelgroesse*fensterhoehe/1080-getInsets().bottom-10*fensterhoehe/1080, null);  
			


        BufferedImage buffImg2 = new BufferedImage(200, 200,
                                    BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbi2 = buffImg2.createGraphics();
        
        gbi2.setPaint(Color.black);         //Lebenskugel
        gbi2.fillOval(0, 0, kugelgroesse*fensterbreite/1920,kugelgroesse*fensterhoehe/1080);
        gbi2.setComposite(ac);

        gbi2.setPaint(Color.red);
        gbi2.fillRect(0, kugelgroesse*fensterhoehe/1080-((int)PlayerIO.getF_Leben()*kugelgroesse*fensterhoehe/1080/1000), kugelgroesse, kugelgroesse); 
    
        g.drawImage(buffImg2, 478*fensterbreite/1920, getHeight()-kugelgroesse*fensterhoehe/1080-getInsets().bottom-10*fensterhoehe/1080, null);
	}
	private void Interface(Graphics g){
		Graphics2D g2d = (Graphics2D)g;


		if (fensterbreite != altefensterbreite
				|| fensterhoehe != altefensterhoehe) { //Neues Interface wird erst sklaliert wenn Fensterhoehe und Breite sich unterscheiden und dann in interface2 gespeichtert
			BufferedImage ergebnis = new BufferedImage(fensterbreite,fensterhoehe, BufferedImage.TYPE_INT_ARGB);
			ergebnis.createGraphics().drawImage(interface1, 0, 0,fensterbreite, fensterhoehe, null);
			ergebnis.createGraphics().drawImage(icon,982 * fensterbreite / 1920,fensterhoehe - 72 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);
			ergebnis.createGraphics().drawImage(icon12,1049 * fensterbreite / 1920,fensterhoehe - 72 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);

			ergebnis.createGraphics().drawImage(icon1,643 * fensterbreite / 1920,fensterhoehe - 72 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);
			ergebnis.createGraphics().drawImage(icon2,709 * fensterbreite / 1920,fensterhoehe - 72 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);
			ergebnis.createGraphics().drawImage(icon3,774 * fensterbreite / 1920,fensterhoehe - 72 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);
			ergebnis.createGraphics().drawImage(Gegenstand.getLook(),1125 * fensterbreite / 1920,fensterhoehe - 76 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);
			ergebnis.createGraphics().drawImage(Gegenstand.getLook2(),1190* fensterbreite / 1920,fensterhoehe - 76 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);
			ergebnis.createGraphics().drawImage(gold,1260* fensterbreite / 1920,fensterhoehe - 70 * fensterhoehe / 1080,50* fensterbreite / 1920, 50 * fensterhoehe / 1080, null);

			interface2 = ergebnis;
			altefensterbreite = fensterbreite;
			altefensterhoehe = fensterhoehe;
		}
		g.drawImage(interface2, -fensterbreite + getWidth(), getHeight()- fensterhoehe - getInsets().bottom, null); //Skaliertes Interface wird gezeichnet
		
		g2d.setPaint(Color.white); //Anzahl Manatr‰nke/lebenstr‰nke
		g.setFont(new Font("TimesRoman", Font.BOLD, 30* fensterbreite / 1920));
		g.drawString(""+PlayerIO.lebenstrank(), 1165* fensterbreite / 1920,fensterhoehe - 15* fensterhoehe / 1080);
		g.drawString(""+PlayerIO.manatrank(), 1230* fensterbreite / 1920,fensterhoehe - 15 * fensterhoehe / 1080);
		g2d.setPaint(Color.yellow); 
		g.drawString(""+PlayerIO.getgeld(), 1280* fensterbreite / 1920,fensterhoehe - 15 * fensterhoehe / 1080);

		

		if (PlayerIO.getZeitSeitLetztemSchuss() < 0.4) { //Graunhinterlegung fuer Schussfrequenz fuer Zauber ohne Abklingzeit
			g2d.setPaint(myColour);

			g.fillRect(643 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) ((int) (64 * fensterhoehe / 1080) * (1 - 2.5 * PlayerIO.getZeitSeitLetztemSchuss())));
			g.fillRect(709 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) ((int) (64 * fensterhoehe / 1080) * (1 - 2.5 * PlayerIO.getZeitSeitLetztemSchuss())));
			g.fillRect(982 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) ((int) (64 * fensterhoehe / 1080)* (1 - 2.5 * PlayerIO.getZeitSeitLetztemSchuss()))) ;
			g.fillRect(1049 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) ((int) (64 * fensterhoehe / 1080)* (1 - 2.5 * PlayerIO.getZeitSeitLetztemSchuss()))) ;

		}
		if (PlayerIO.getF_Mana()<200){ //Zauber werden grau hinterlegt wenn Manakosten>Manapool
			g2d.setPaint(myColour);

			g.fillRect(643 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080)) ;
			g.fillRect(709 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080)) ;
		}
		if (PlayerIO.getF_Mana()<100){ //Zauber werden grau hinterlegt wenn Manakosten>Manapool
			g2d.setPaint(myColour);
			g.fillRect(774 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080)) ;

		}
		
		if (PlayerIO.getF_Mana()<30){ //Zauber werden grau hinterlegt wenn Manakosten>Manapool (fuer jeden Zauber gesondert mit unterschiedlichen Manakosten)
			g2d.setPaint(myColour);

			g.fillRect(982 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080)) ;

		}
		if (PlayerIO.abklingzeitZauber5() < 20) { //Zauber werden grau hinterlegt wenn die Zeit seit der Zauber genutzt wurde, die Abklingzeit des Zaubers nicht ueberschreitet
			g2d.setPaint(myColour);
			g.fillRect(774 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080 * (1 - PlayerIO.abklingzeitZauber5() / 20)));

		} else {
			if (PlayerIO.getZeitSeitLetztemSchuss() < 0.4) { // Grauhinterlegung fuer Schussfrequenz bei Zaubern mit Abklingzeit in einer extra IF-Abfrage da vorher noch die Abklingzeit geprueft werden muss
				g.fillRect(774 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080 * (1 - 2.5 * PlayerIO.getZeitSeitLetztemSchuss())));
			}
		}
		if (PlayerIO.abklingzeittrank() < 10) { //Zauber werden grau hinterlegt wenn die Zeit seit der Zauber genutzt wurde, die Abklingzeit des Zaubers nicht ueberschreitet
			g2d.setPaint(myColour);
			g.fillRect(1130 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,125 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080 * (1 - PlayerIO.abklingzeittrank() / 10)));
		}
		
		

		//Status der Erfahrungspunkte
		g2d.setFont(f);
		g2d.drawImage(status,5,30, null);
		g2d.setPaint(statusColor);
		g2d.drawString("Name: Rossana", 130,60);
		g2d.drawString("Level: " + level, 130, 85);
		if(ep > 1000*level){
			level++;
			ep = ep % 1000;
		}
		g2d.drawString("EXP: " + ep, 130, 110);
		
	}
	//	private void Effekte(Graphics g){
//			Graphics2D g2d = (Graphics2D)g;
//			
//			for(int i = 0; i<Enemys.size(); i++){
//				Gegner c = Enemys.get(i);
//			double points[][] = { 
//			        { c.getBounding().x+xVerschiebung+15 , c.getBounding().y+yVerschiebung+c.getBounding().height }, { c.getBounding().x+xVerschiebung-PlayerIO.getBounding().x, c.getBounding().y+yVerschiebung-PlayerIO.getBounding().y+c.getBounding().height  }, { c.getBounding().x+xVerschiebung+c.getBounding().width-PlayerIO.getBounding().x, c.getBounding().y+yVerschiebung-PlayerIO.getBounding().y  }, {  c.getBounding().x+xVerschiebung+c.getBounding().width-15, c.getBounding().y+yVerschiebung  }, 
//			        { c.getBounding().x+xVerschiebung+15, c.getBounding().y+yVerschiebung+c.getBounding().height}};
//			
//     
//
//        g2d.translate(0, 0);
//
//        GeneralPath shadow = new GeneralPath();
//
//        shadow.moveTo(points[0][0], points[0][1]);
//
//        for (int k = 1; k < points.length; k++)
//          shadow.lineTo(points[k][0], points[k][1]);
//
//       shadow.closePath();
//       g2d.setPaint(myColour);
//       g2d.fill(shadow);        
//          
//			}

		


//	}

}
