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
	
	public Frame(int worldsizex, int worldsizey,Player player){
		super("Gruppe 93");
		screen = new Screen();
		screen.setBounds(0, 0, worldsizex, worldsizey);
		add(screen);
		addKeyListener(new Keyboard());
		this.player = player;
		
		
	}

	private class Screen extends JLabel{
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.drawImage(player.getplay(), player.getBounding().x, player.getBounding().y, null);
			
			
		}
		
	}

}
