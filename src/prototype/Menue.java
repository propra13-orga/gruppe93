package prototype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import multitools.MPlayServer;

public class Menue extends JFrame{
	
	JButton start, beenden, multiplayer;
	boolean play;
	boolean multiplay;
	
	
	public Menue(){
		
		setLayout(null);
		setSize(280, 180);
		
		setResizable(false);	
		setTitle("Gruppe93");	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		setLocationRelativeTo(null);	
		setVisible(true);	
		
		start = new JButton("Start");
		start.setBounds(30, 60, 100, 30);
		start.addActionListener(new ButtonListener());
		add(start);
		
		beenden = new JButton("Beenden");
		beenden.setBounds(150, 60, 100, 30);
		beenden.addActionListener(new ButtonListener());
		add(beenden);
		
		multiplayer = new JButton("Multiplayer");
		multiplayer.setBounds(30, 100, 100, 30);
		multiplayer.addActionListener(new ButtonListener());
		add(multiplayer);
		
		play = false;
		multiplay=false;
		
	}

	
	
	class ButtonListener implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == start){
				play = true;
				setVisible(false);	
			}
			if(e.getSource() == beenden){
				System.exit(0);
				
			}
			if(e.getSource()==multiplayer){
				//hier kommt der Multiplayerstarter rein
				multiplay=true;
				dispose();
			}
		}
	}
	
	
}