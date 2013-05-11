package prototype;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
//import javax.swing.JLabel;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;	//nötig, damit kein Warning angezeigt wird
	//DEKLARATION
	private BufferStrategy buff;
	final Player player;
	private Tiles[][] map;
	
	
	public Frame(String name,Player player, Tiles[][] map){
		super(name);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
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
	
	public void setSizeRight(int x,int y){		//kann nicht im Konstruktor gemacht werden, wegen falscher Insets
		setSize(x+getInsets().left+getInsets().right, y+getInsets().top+getInsets().bottom);	//Größe + Randeinrückungen, damit der Sichtbare bereich genau die eingegebene Größe hat
	}
	
	
	
	public void nextFrame(){
		Graphics g=buff.getDrawGraphics();//übergibt ein malobjekt aus der bufferstrat
			
		for(int a = 0; a < 32 ; a++){
			for(int b = 0 ; b< 18 ; b++){
				Tiles c = map[a][b];
				g.drawImage(c.getLook(), c.getBounding().x+getInsets().left, c.getBounding().y+getInsets().top, null);
			}
		}
		
		g.drawImage(player.getPlay(), player.getBounding().x+getInsets().left, player.getBounding().y+getInsets().top, null);
		
		g.dispose();	//gibt den zeichner wieder frei
		buff.show();	//zeigt dann den aktuellen buffer
	}
}
