package prototype;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame{
	
	private Screen screen;
	
	final Player player;
	Tiles[][] map = new Tiles[32][18];
	
	public Frame(int worldsizex, int worldsizey,Player player, Tiles[][] map){
		super("Gruppe 93");
		screen = new Screen();
		screen.setBounds(0, 0, worldsizex, worldsizey);
		add(screen);
		addKeyListener(new Keyboard());
		this.player = player;
		this.map =  map;
		
		
	}

	private class Screen extends JLabel{
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			
			for(int a = 0; a < 32 ; a++){
				for(int b = 0 ; b< 18 ; b++){
					Tiles c = map[a][b];
					g.drawImage(c.getLook(), c.getBounding().x, c.getBounding().y, null);
				}
			}
			
		
			g.drawImage(player.getplay(), player.getBounding().x, player.getBounding().y, null);
			
			
		}
		
	}

}
