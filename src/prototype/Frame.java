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
	private static final long serialVersionUID = 1L;	//nötig, damit kein Warning angezeigt wird
	//DEKLARATION
	private BufferStrategy buff;
	final Player player;
	private Map map;
	private List<Zauber>Zaubern;
	private List<Gegner>Enemys;
	private int kugelgroesse=100;
	private int fensterbreite=0;
	private int fensterhoehe=0;
	private int xVerschiebung=0;	//für die Verschiebungen, der alle Objekte ausser dem Spieler unterworfen sind
	private int yVerschiebung=0;

	
	
	public Frame(String name,Player player, Map map, List<Zauber>Zaubern,List<Gegner>Enemys){
		super(name);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		this.Zaubern=Zaubern;
		this.Enemys=Enemys;
//		screen = new Screen();
//		screen.setBounds(0, 0, worldsizex, worldsizey);
//		add(screen);
		
		
		
		
		addKeyListener(new Keyboard());
		this.player = player;
		this.map=map;
	}
	
	
	//METHODEN
	public void makeBuff(){		//kann das auch in den konstruktor...test später
		createBufferStrategy(2);
		buff= getBufferStrategy();
	}
	
	
	
	public void setSizeRight(int x,int y){		//kann nicht im Konstruktor gemacht werden, wegen zunächst falscher Insets
//		setSize(x+getInsets().left+getInsets().right, y+getInsets().top+getInsets().bottom);	//Größe + Randeinrückungen, damit der Sichtbare bereich genau die eingegebene Größe hat
		setExtendedState(MAXIMIZED_BOTH);
	}
	
	
	
	public void nextFrame(){
		fensterbreite=getWidth();
		fensterhoehe=getHeight();
		xVerschiebung=(-player.getX()+fensterbreite/2-player.getBimg().getWidth()/2);	//diese Werte liefern unter Berücksichtigung der Faktoren wie Spielerposition, Insets, Fenstergröße, Spielergröße und Statusleistendicke die nötigen Verschiebungen aller Objekte
		yVerschiebung=(-player.getY()+(fensterhoehe-kugelgroesse)/2-player.getBimg().getHeight()/2+getInsets().top-getInsets().bottom);
		Graphics g=buff.getDrawGraphics();//übergibt ein malobjekt aus der bufferstrat
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0 , getWidth(), getHeight());
		
		for(int x = 0; x < 32 ; x++){
			for(int y = 0 ; y< 18 ; y++){
				g.drawImage(Tile.getLook(map.getTile(x, y).getTex()), map.getTile(x, y).getBounding().x+xVerschiebung, map.getTile(x, y).getBounding().y+yVerschiebung, null);
			}
		}
		
		dieKugelnUndLeiste(g);//wegen der Komplexität ist die Statusleiste in ihrer eigenen Funktion
		
		g.drawImage(player.getBimg(), (fensterbreite-player.getBimg().getWidth())/2, (fensterhoehe-kugelgroesse-player.getBimg().getHeight())/2+getInsets().top-getInsets().bottom, null);	//Player in der Mitte des Bildes
		
		for(int i = 0; i<Enemys.size(); i++){
			Gegner c = Enemys.get(i);
			g.drawImage(Gegner.getLook(), c.getBounding().x+xVerschiebung, c.getBounding().y+yVerschiebung, null);
	    }
		for(int i = 0; i<Zaubern.size(); i++){
			Zauber b = Zaubern.get(i);
			g.drawImage(Zauber.getLook(), b.getBounding().x+xVerschiebung, b.getBounding().y+yVerschiebung, null);
		}	
	
		
		g.dispose();	//gibt den zeichner wieder frei
		buff.show();	//zeigt dann den aktuellen buffer
	}
	
	
	
	private void dieKugelnUndLeiste(Graphics g){
		g.setColor(new Color(70, 67, 123));
		g.fillRect(0, 740, 1480, 200);
		
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.8f);

        BufferedImage buffImg = new BufferedImage(200, 200,
                                    BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbi = buffImg.createGraphics();
        
        gbi.setPaint(Color.gray);         //Manakugel
        gbi.fillOval(0, 0, kugelgroesse,kugelgroesse);
        gbi.setComposite(ac);

        gbi.setPaint(Color.blue);
        gbi.fillRect(0, kugelgroesse-((int)player.getmana()*kugelgroesse/1000), kugelgroesse, kugelgroesse); 
    
        g.drawImage(buffImg, 1000, 840-kugelgroesse, null);  
			
        
        
       

        BufferedImage buffImg2 = new BufferedImage(200, 200,
                                    BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbi2 = buffImg2.createGraphics();
        
        gbi2.setPaint(Color.gray);         //Lebenskugel
        gbi2.fillOval(0, 0, kugelgroesse,kugelgroesse);
        gbi2.setComposite(ac);

        gbi2.setPaint(Color.red);
        gbi2.fillRect(0, kugelgroesse-((int)player.getleben()*kugelgroesse/1000), kugelgroesse, kugelgroesse); 
    
        g.drawImage(buffImg2, 120, 840-kugelgroesse, null);
	}
	
}
