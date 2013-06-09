package prototype;

import java.awt.AlphaComposite;
import java.awt.Color;
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
	private static BufferedImage interface2; //Zwischenspeicher für skaliertes Interface
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
		
		setSize(800, 600);			//Damit man, wenn man vom maximierten Modus umschaltet immernoch ne Fenstergröße hat
		setExtendedState(MAXIMIZED_BOTH);	//Frame über den ges. Bilfdschirm
		
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
		xVerschiebung=(-PlayerIO.getPlayerPositionX()+fensterbreite/2-PlayerIO.getBimg().getWidth()/2);	//diese Werte liefern unter Beruecksichtigung der Faktoren wie Spielerposition, Insets, Fenstergroeße, Spielergroeße und Statusleistendicke die noetigen Verschiebungen aller Objekte
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
			if (b.getid()==1 || b.getid()==3 || b.getid()==5 ){
			g.drawImage(b.getLook(), b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
		}	}
		
		//am Ende, damit da nix drübermalt
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

    if (fensterbreite!=altefensterbreite||fensterhoehe!=altefensterhoehe){
        BufferedImage ergebnis = new BufferedImage(fensterbreite, fensterhoehe , BufferedImage.TYPE_INT_ARGB); 
        ergebnis.createGraphics().drawImage(interface1, 0,0, fensterbreite, fensterhoehe, null); 
        interface2=ergebnis;
        altefensterbreite=fensterbreite;
        altefensterhoehe=fensterhoehe;
	   }
		g.drawImage(interface2, -fensterbreite+getWidth(),getHeight()-fensterhoehe-getInsets().bottom, null);

	}

	
}
