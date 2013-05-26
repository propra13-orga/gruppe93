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
	
	
	public Frame(String name,Player player, Map map, List<Zauber>Zaubern){
		super(name);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		this.Zaubern=Zaubern;
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
		setSize(x+getInsets().left+getInsets().right, y+getInsets().top+getInsets().bottom);	//Gr��e + Randeinr�ckungen, damit der Sichtbare bereich genau die eingegebene Gr��e hat
	}
	
	
	
	public void nextFrame(){
		Graphics g=buff.getDrawGraphics();//�bergibt ein malobjekt aus der bufferstrat
        Graphics2D g2d = (Graphics2D) g;
			
		for(int x = 0; x < 32 ; x++){
			for(int y = 0 ; y< 18 ; y++){
				g.drawImage(Tile.getLook(map.getTile(x, y).getTex()), map.getTile(x, y).getBounding().x+getInsets().left, map.getTile(x, y).getBounding().y+getInsets().top, null);	//man sollte nicht an denboundings malen. habs beim player gefixt. das kann zu bugs f�hren falls die boundins mal kleiner sind als das eigentliche bild
			}
		}
		 g2d.setColor(new Color(70, 67, 123));
			g.fillRect(0, 740, 1480, 200);
	
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.8f);

        BufferedImage buffImg = new BufferedImage(200, 200,
                                    BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbi = buffImg.createGraphics();

        gbi.setPaint(Color.gray);
        gbi.fillOval(0, 0, 160,160);
        gbi.setComposite(ac);

        gbi.setPaint(Color.blue);
        gbi.fillRect(0, 160-(int)player.getmana(), 160, 160); 
        
        
     
        g2d.drawImage(buffImg, 100, 680, null);  
        


		
		
		
		g.drawImage(player.getBimg(), player.getX()+getInsets().left, player.getY()+getInsets().top, null);
		for(int i = 0; i<Zaubern.size(); i++){
			Zauber b = Zaubern.get(i);
			g.drawImage(Zauber.getLook(), b.getBounding().x, b.getBounding().y, null);
		}
		
		g.dispose();	//gibt den zeichner wieder frei
		buff.show();	//zeigt dann den aktuellen buffer
	}
}
