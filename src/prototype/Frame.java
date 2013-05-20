package prototype;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
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
	public void makeBuff(){		//kann das auch in den konstruktor...test später
		createBufferStrategy(2);
		buff= getBufferStrategy();
	}
	
	public void setSizeRight(int x,int y){		//kann nicht im Konstruktor gemacht werden, wegen zunächst falscher Insets
		setSize(x+getInsets().left+getInsets().right, y+getInsets().top+getInsets().bottom);	//Größe + Randeinrückungen, damit der Sichtbare bereich genau die eingegebene Größe hat
	}
	
	
	
	public void nextFrame(){
		Graphics g=buff.getDrawGraphics();//übergibt ein malobjekt aus der bufferstrat
			
		for(int x = 0; x < 32 ; x++){
			for(int y = 0 ; y< 18 ; y++){
				map.getTile(x, y);
				g.drawImage(Tile.getLook(map.getTile(x, y).getTex()), map.getTile(x, y).getBounding().x+getInsets().left, map.getTile(x, y).getBounding().y+getInsets().top, null);	//man sollte nicht an denboundings malen. habs beim player gefixt. das kann zu bugs führen falls die boundins mal kleiner sind als das eigentliche bild
			}
		}
		
		g.drawImage(player.getBimg(), player.getX()+getInsets().left, player.getY()+getInsets().top, null);
		for(int i = 0; i<Zaubern.size(); i++){
			Zauber b = Zaubern.get(i);
			g.drawImage(Zauber.getLook(), b.getBounding().x, b.getBounding().y, null);
		}
		
		g.dispose();	//gibt den zeichner wieder frei
		buff.show();	//zeigt dann den aktuellen buffer
	}
}
