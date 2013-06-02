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
	private static final long serialVersionUID = 1L;	//n�tig, damit kein Warning angezeigt wird
	//DEKLARATION
	private BufferStrategy buff;
	final Player player;
	private Map map;
	private List<Zauber>Zaubern;
	private List<Gegner>Enemys;
	private int kugelgroesse=100;
	
	
	public Frame(String name,Player player, Map map, List<Zauber>Zaubern,List<Gegner>Enemys){
		super(name);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
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
	public void makeBuff(){		//kann das auch in den konstruktor...test sp�ter
		createBufferStrategy(2);
		buff= getBufferStrategy();
	}
	
	
	
	public void setSizeRight(int x,int y){		//kann nicht im Konstruktor gemacht werden, wegen zun�chst falscher Insets
//		setSize(x+getInsets().left+getInsets().right, y+getInsets().top+getInsets().bottom);	//Gr��e + Randeinr�ckungen, damit der Sichtbare bereich genau die eingegebene Gr��e hat
		setExtendedState(MAXIMIZED_BOTH);
		//resizable, weil es nach dem einf�hren der Kamera keinen Unterschied mehr macht
//		setResizable(false);	//kann erst hier hin, weil sonst beim Maximieren die Windows Taskleiste unsichtbar wird
	}
	
	
	
	public void nextFrame(){
		Graphics g=buff.getDrawGraphics();//�bergibt ein malobjekt aus der bufferstrat
		
		for(int x = 0; x < 32 ; x++){
			for(int y = 0 ; y< 18 ; y++){
				g.drawImage(Tile.getLook(map.getTile(x, y).getTex()), map.getTile(x, y).getBounding().x+getInsets().left, map.getTile(x, y).getBounding().y+getInsets().top, null);	//man sollte nicht an denboundings malen. habs beim player gefixt. das kann zu bugs f�hren falls die boundins mal kleiner sind als das eigentliche bild
			}
		}
		
		dieKugelnUndLeiste(g);//wegen der Komplexit�t ist die Statusleiste in ihrer eigenen Funktion
		
		g.drawImage(player.getBimg(), player.getX()+getInsets().left, player.getY()+getInsets().top, null);
		
		for(int i = 0; i<Enemys.size(); i++){
			Gegner c = Enemys.get(i);
			g.drawImage(Gegner.getLook(), c.getX(), c.getY(), null);
	    }
		for(int i = 0; i<Zaubern.size(); i++){
			Zauber b = Zaubern.get(i);
			g.drawImage(Zauber.getLook(), b.getX(), b.getY(), null);
		}	
	
		
		g.dispose();	//gibt den zeichner wieder frei
		buff.show();	//zeigt dann den aktuellen buffer
	}
	
	
	
	private void dieKugelnUndLeiste(Graphics g){
		g.setColor(new Color(70, 67, 123));
		g.fillRect(0, getHeight()-100-getInsets().bottom, getWidth(), 100);	//ist jetzt an der unteren Bildschirmkante fixiert und so bei jeder Aufl�sung an der richtigen Stelle
		
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
