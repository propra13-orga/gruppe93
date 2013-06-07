package prototype;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;
//import javax.swing.JLabel;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;	//noetig, damit kein Warning angezeigt wird
	//DEKLARATION
	private BufferStrategy buff;
	final Player player;
	private Map map;
	private List<Zauber>Zaubern;
	private List<Gegner>Enemys;
	private int kugelgroesse=100;
	private int fensterbreite=0;
	private int fensterhoehe=0;
	private int xVerschiebung=0;	//fuer die Verschiebungen, der alle Objekte ausser dem Spieler unterworfen sind
	private int yVerschiebung=0;
	

	
	
	public Frame(String name,Player player, Map map, List<Zauber>Zaubern,List<Gegner>Enemys){
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
		this.player = player;
		this.map=map;
		

	}
	
	
	//METHODEN
	public void nextFrame(){
		fensterbreite=getWidth();
		fensterhoehe=getHeight();
		xVerschiebung=(-player.getX()+fensterbreite/2-player.getBimg().getWidth()/2);	//diese Werte liefern unter Beruecksichtigung der Faktoren wie Spielerposition, Insets, Fenstergroeße, Spielergroeße und Statusleistendicke die noetigen Verschiebungen aller Objekte
		yVerschiebung=(-player.getY()+(fensterhoehe-kugelgroesse)/2-player.getBimg().getHeight()/2+getInsets().top-getInsets().bottom);
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
			if (b.getid()==2){
			g.drawImage(b.getLook2(), b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
			
			}
		}
		g.drawImage(player.getBimg(), (fensterbreite-player.getBimg().getWidth())/2, (fensterhoehe-kugelgroesse-player.getBimg().getHeight())/2+getInsets().top-getInsets().bottom, null);	//Player in der Mitte des Bildes
		
		for(int i = 0; i<Enemys.size(); i++){
			Gegner c = Enemys.get(i);
			g.drawImage(c.getLook(), c.getX()+xVerschiebung, c.getY()+yVerschiebung, null);
	    }
		for(int i = 0; i<Zaubern.size(); i++){
			Zauber b = Zaubern.get(i);
			if (b.getid()==1){
			g.drawImage(b.getLook(), b.getX()+xVerschiebung, b.getY()+yVerschiebung, null);
		}	}
		
		//am Ende, damit da nix drübermalt
		dieKugelnUndLeiste(g);//wegen der Komplexitaet ist die Statusleiste in ihrer eigenen Funktion
		
		g.dispose();	//gibt den zeichner wieder frei
		buff.show();	//zeigt dann den aktuellen buffer
	}
	
	
	
	private void dieKugelnUndLeiste(Graphics g){
		g.setColor(new Color(70, 67, 123));
		g.fillRect(0, getHeight()-100-getInsets().bottom, getWidth(), 100);	//ist jetzt an der unteren Bildschirmkante fixiert und so bei jeder Aufloesung an der richtigen Stelle
		
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.8f);

        BufferedImage buffImg = new BufferedImage(200, 200,
                                    BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbi = buffImg.createGraphics();
        
        gbi.setPaint(Color.gray);         //Manakugel
        gbi.fillOval(0, 0, kugelgroesse,kugelgroesse);
        gbi.setComposite(ac);

        gbi.setPaint(Color.blue);
        gbi.fillRect(0, kugelgroesse-((int)player.getmana()*kugelgroesse/1000), kugelgroesse, kugelgroesse); 
    
        g.drawImage(buffImg, getWidth()-120-kugelgroesse, getHeight()-kugelgroesse-getInsets().bottom, null);  
			
        
        
       

        BufferedImage buffImg2 = new BufferedImage(200, 200,
                                    BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbi2 = buffImg2.createGraphics();
        
        gbi2.setPaint(Color.gray);         //Lebenskugel
        gbi2.fillOval(0, 0, kugelgroesse,kugelgroesse);
        gbi2.setComposite(ac);

        gbi2.setPaint(Color.red);
        gbi2.fillRect(0, kugelgroesse-((int)player.getleben()*kugelgroesse/1000), kugelgroesse, kugelgroesse); 
    
        g.drawImage(buffImg2, 120, getHeight()-kugelgroesse-getInsets().bottom, null);
	}
	
}
