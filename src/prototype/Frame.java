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
//import javax.swing.JLabel;

import player.PlayerIO;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;	//noetig, damit kein Warning angezeigt wird
	//DEKLARATION
	private BufferStrategy buff;
	private Map map;
	private List<Zauber>Zaubern;
	private List<Gegner>Enemys;
	private static BufferedImage interface1; //unsklaliertes original interface.png
	private static BufferedImage interface2; //Zwischenspeicher fuer skaliertes Interface
	private static BufferedImage icon;
	private static BufferedImage icon1; 
	private static BufferedImage icon2;
	private static BufferedImage icon3; 
	private static BufferedImage Lebenstrank; 
	private static BufferedImage Manatrank; 
	private static BufferedImage gold; 
	private int kugelgroesse=152;
	private int fensterbreite=0;
	private int fensterhoehe=0;
	private int xVerschiebung=0;	//fuer die Verschiebungen, der alle Objekte ausser dem Spieler unterworfen sind
	private int yVerschiebung=0;
	private int altefensterbreite; //Interface wird erst neu skaliert wenn (fensterbreite!=altefensterbreite||fensterhoehe!=altefensterhoehe) wegen der Performance
	private int altefensterhoehe; //
	static {
		try {
			interface1 = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/interface.png"));
			icon = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/icon.png"));

			icon1 = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/icon1.png"));
			icon2 = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/icon2.png"));
			icon3 = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/icon3.png"));
			Lebenstrank = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/Lebenstrank.png"));
			Manatrank = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/Manatrank.png"));
			gold = ImageIO.read(Zauber.class.getClassLoader().getResourceAsStream("gfx/gold.png"));


			} catch (IOException e) {e.printStackTrace();}
        }

	
	

	
	
	public Frame(String name,Map map, List<Zauber>Zaubern,List<Gegner>Enemys){
		super(name);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.Zaubern=Zaubern;
		this.Enemys=Enemys;
		
		createBufferStrategy(2);	//Buffer mit 2 Bildern wird erzeugt
		buff= getBufferStrategy();	//buff wird die Bufferstrat
		
		setSize(800, 600);			//Damit man, wenn man vom maximierten Modus umschaltet immernoch ne Fenstergroeﬂe hat
		setExtendedState(MAXIMIZED_BOTH);	//Frame ueber den ges. Bilfdschirm
		
//		screen = new Screen();
//		screen.setBounds(0, 0, worldsizex, worldsizey);
//		add(screen);
		
		
		
		
		addKeyListener(new Keyboard());
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
			if (b.getid()==2|| b.getid()==4){
			g.drawImage(b.getLook(), b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
			
			}
		}
		g.drawImage(PlayerIO.getBimg(), (fensterbreite-PlayerIO.getBimg().getWidth())/2, (fensterhoehe-kugelgroesse-PlayerIO.getBimg().getHeight())/2+getInsets().top-getInsets().bottom, null);	//Player in der Mitte des Bildes
		
		for(int i = 0; i<Enemys.size(); i++){
			Gegner c = Enemys.get(i);
			g.drawImage(c.getLook(), c.getX()+xVerschiebung, c.getY()+yVerschiebung, null);
	    }
		for(int i = 0; i<Zaubern.size(); i++){
			Zauber b = Zaubern.get(i);
			if (b.getid()==1 || b.getid()==3 ){
			g.drawImage(b.getLook(), b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
			}
			if(b.getid()==5 ){
				g.drawImage(b.getLook(), (fensterbreite-PlayerIO.getBimg().getWidth())/2-10, (fensterhoehe-kugelgroesse-PlayerIO.getBimg().getHeight())/2+getInsets().top-getInsets().bottom-15, null);	
			}
			
		}

			
			
		
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
		Color myColour = new Color(0, 0, 0, 180);


		if (fensterbreite != altefensterbreite
				|| fensterhoehe != altefensterhoehe) { //Neues Interface wird erst sklaliert wenn Fensterhoehe und Breite sich unterscheiden und dann in interface2 gespeichtert
			BufferedImage ergebnis = new BufferedImage(fensterbreite,fensterhoehe, BufferedImage.TYPE_INT_ARGB);
			ergebnis.createGraphics().drawImage(interface1, 0, 0,fensterbreite, fensterhoehe, null);
			ergebnis.createGraphics().drawImage(icon,982 * fensterbreite / 1920,fensterhoehe - 72 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);
			ergebnis.createGraphics().drawImage(icon1,643 * fensterbreite / 1920,fensterhoehe - 72 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);
			ergebnis.createGraphics().drawImage(icon2,709 * fensterbreite / 1920,fensterhoehe - 72 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);
			ergebnis.createGraphics().drawImage(icon3,774 * fensterbreite / 1920,fensterhoehe - 72 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);
			ergebnis.createGraphics().drawImage(Lebenstrank,1125 * fensterbreite / 1920,fensterhoehe - 76 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);
			ergebnis.createGraphics().drawImage(Manatrank,1190* fensterbreite / 1920,fensterhoehe - 76 * fensterhoehe / 1080,64 * fensterbreite / 1920, 64 * fensterhoehe / 1080, null);
			ergebnis.createGraphics().drawImage(gold,1260* fensterbreite / 1920,fensterhoehe - 70 * fensterhoehe / 1080,50* fensterbreite / 1920, 50 * fensterhoehe / 1080, null);

			
			interface2 = ergebnis;
			altefensterbreite = fensterbreite;
			altefensterhoehe = fensterhoehe;
		}
		g.drawImage(interface2, -fensterbreite + getWidth(), getHeight()- fensterhoehe - getInsets().bottom, null); //Skaliertes Interface wird gezeichnet
		
		((Graphics2D) g).setPaint(Color.white); //Anzahl Manatr‰nke/lebenstr‰nke
		g.setFont(new Font("TimesRoman", Font.BOLD, 30* fensterbreite / 1920));
		g.drawString(""+PlayerIO.lebenstrank(), 1165* fensterbreite / 1920,fensterhoehe - 15* fensterhoehe / 1080);
		g.drawString(""+PlayerIO.manatrank(), 1230* fensterbreite / 1920,fensterhoehe - 15 * fensterhoehe / 1080);
		((Graphics2D) g).setPaint(Color.yellow); 
		g.drawString(""+PlayerIO.getgeld(), 1280* fensterbreite / 1920,fensterhoehe - 15 * fensterhoehe / 1080);

		

		if (PlayerIO.getZeitSeitLetztemSchuss() < 0.5) { //Graunhinterlegung fuer Schussfrequenz fuer Zauber ohne Abklingzeit
			((Graphics2D) g).setPaint(myColour);

			g.fillRect(643 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) ((int) (64 * fensterhoehe / 1080) * (1 - 2 * PlayerIO.getZeitSeitLetztemSchuss())));
			g.fillRect(709 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) ((int) (64 * fensterhoehe / 1080) * (1 - 2 * PlayerIO.getZeitSeitLetztemSchuss())));
			g.fillRect(982 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) ((int) (64 * fensterhoehe / 1080)* (1 - 2 * PlayerIO.getZeitSeitLetztemSchuss()))) ;

		}
		if (PlayerIO.getF_Mana()<500){ //Zauber werden grau hinterlegt wenn Manakosten>Manapool
			((Graphics2D) g).setPaint(myColour);

			g.fillRect(643 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080)) ;
			g.fillRect(709 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080)) ;
			g.fillRect(774 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080)) ;

		}
		if (PlayerIO.getF_Mana()<30){ //Zauber werden grau hinterlegt wenn Manakosten>Manapool (fuer jeden Zauber gesondert mit unterschiedlichen Manakosten)
			((Graphics2D) g).setPaint(myColour);

			g.fillRect(982 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080)) ;

		}
		if (PlayerIO.abklingzeitZauber5() < 20) { //Zauber werden grau hinterlegt wenn die Zeit seit der Zauber genutzt wurde, die Abklingzeit des Zaubers nicht ueberschreitet
			((Graphics2D) g).setPaint(myColour);
			g.fillRect(774 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080 * (1 - PlayerIO.abklingzeitZauber5() / 20)));

		} else {
			if (PlayerIO.getZeitSeitLetztemSchuss() < 0.5) { // Grauhinterlegung fuer Schussfrequenz bei Zaubern mit Abklingzeit in einer extra IF-Abfrage da vorher noch die Abklingzeit geprueft werden muss
				g.fillRect(774 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,64 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080 * (1 - 2 * PlayerIO.getZeitSeitLetztemSchuss())));
			}
		}
		if (PlayerIO.abklingzeittrank() < 10) { //Zauber werden grau hinterlegt wenn die Zeit seit der Zauber genutzt wurde, die Abklingzeit des Zaubers nicht ueberschreitet
			((Graphics2D) g).setPaint(myColour);
			g.fillRect(1130 * fensterbreite / 1920, fensterhoehe - 72* fensterhoehe / 1080 - getInsets().bottom,125 * fensterbreite / 1920,(int) (64 * fensterhoehe / 1080 * (1 - PlayerIO.abklingzeittrank() / 10)));
		}

	}

}
