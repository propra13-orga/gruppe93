package prototype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Menue extends JFrame{
	
	JButton start, beenden;
	boolean play;
	
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
		
		play = false;
		
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
		}
	}
	
	
}