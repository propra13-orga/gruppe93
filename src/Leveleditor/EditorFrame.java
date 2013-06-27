package Leveleditor;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.tools.JavaFileManager;

public class EditorFrame extends JFrame{
	private static final long serialVersionUID = 1L;	//noetig, damit kein Warning angezeigt wird
	//DEKLARATION
	private BufferStrategy buff;
	
	public int feldart=0;
	
	public EditorFrame(String name){
		super(name);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		createBufferStrategy(2);	//Buffer mit 2 Bildern wird erzeugt
		buff= getBufferStrategy();	//buff wird die Bufferstrat
		setSize(800, 600);			//Damit man, wenn man vom maximierten Modus umschaltet immernoch ne Fenstergroeße hat
		setExtendedState(MAXIMIZED_BOTH);	//Frame ueber den ges. Bilfdschirm
		initUI();
	}
	
	
	
	//METHODEN
	public void nextFrame(){
		Graphics g=buff.getDrawGraphics();//uebergibt ein malobjekt aus der bufferstrat
		
		g.dispose();	//gibt den zeichner wieder frei
		buff.show();	//zeigt dann den aktuellen buffer
	}

	private void initUI(){
		JMenuBar menueleiste=new JMenuBar();
		
		JMenu data=new JMenu("Datei");
		JMenu feldtyp=new JMenu("Feldtyp");
		
		JMenuItem Exit=new JMenuItem("Weg Hier!");
		JMenuItem Save=new JMenuItem("Osama bin Speichern");
		JMenuItem Load=new JMenuItem("Osama bin  Laden");
		
		data.add(Save);
		Save.setToolTipText("Ruft das Speichermenü auf.");
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		data.add(Load);
		Load.setToolTipText("Ruft das Lademenü auf.");
		Load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		data.add(Exit);
		Exit.setToolTipText("Verlässt das Programm.");
		Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
		
		JMenuItem wall1=new JMenuItem("Wand1");
		JMenuItem floor1=new JMenuItem("Boden1");
		feldtyp.add(wall1);
		wall1.setToolTipText("Baut Objekte vom Typ Boden1.");
		wall1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				feldart=1;
			}
		});
		
		feldtyp.add(floor1);
		floor1.setToolTipText("Baut Objekte vom Typ Wand1.");
		floor1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				feldart=2;
			}
		});
		
		menueleiste.add(data);
		menueleiste.add(feldtyp);
		
		setJMenuBar(menueleiste);
	}
}
