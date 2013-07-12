package Leveleditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import prototype.Map;

public class EditorFrame extends JFrame{
	private static final long serialVersionUID = 1L;	//noetig, damit kein Warning angezeigt wird
	
	
	
	//DEKLARATION
	private JPanel panel;	//dasjPanel erkennt im Unterschied zur Buffstrat, daß es unter die jmenubar gehört malt aber trotzdem fröhlich über die menüs drüber wenn die aufpoppen
	
	public static int feldart=2;
	public int KartenBreite=0;
	public int KartenHoehe=0;
	public boolean newMap=false;
	public boolean save=false;
	private Map map;
	private Kamera kamera;
	public EditorMaus maus=new EditorMaus();	//die Maus des Editor muss andere Anforderungen erfüllen, als die des Games
	
	
	public EditorFrame(String name, Map map,Kamera kamera){
		super(name);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		panel=(JPanel) this.getContentPane();
		panel.setBackground(Color.BLACK);
		initUI();
		setSize(800, 600);			//Damit man, wenn man vom maximierten Modus umschaltet immernoch ne Fenstergroeße hat
//		setExtendedState(MAXIMIZED_BOTH);	//Frame ueber den ges. Bilfdschirm
		this.map=map;
		this.kamera=kamera;
		addKeyListener(new Keyboard());
//		addMouseListener(maus);
		panel.addMouseListener(maus);	//viel besser als im fenster, weil keine insets mehr berechnet werden müsen. man bin ich schlau
		
	}
	
	
	
	//METHODEN
	public void nextFrame(){
		Graphics2D g=(Graphics2D) panel.getGraphics();
		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, getWidth(), getHeight());	//auskommentier wegen flackern, da buffstrat ja nicht geht -.-
		if(map!=null){
			for(int x = 0; x <= map.getXTiles() ; x++){
				for(int y = 0 ; y<=map.getYTiles() ; y++){
					g.drawImage(map.getTile(x, y).getBimg(), (int)(map.getTile(x, y).getBounding().x-kamera.getX()), (int)(map.getTile(x, y).getBounding().y-kamera.getY()), null);
					
				}
			}
		}

		g.dispose();	//gibt den zeichner wieder frei
		
	}
	
	public void changeMap(Map m){
		map=m;
	}

	public void initUI(){
		JMenuBar menueleiste=new JMenuBar();
		menueleiste.setDoubleBuffered(true);	//warscheinlich unnötig
		
		JMenu data=new JMenu("Datei");
		JMenu feldtyp=new JMenu("Feldtyp");
		data.getPopupMenu().setLightWeightPopupEnabled(false);
		feldtyp.getPopupMenu().setLightWeightPopupEnabled(false);
		
		JMenuItem New=new JMenuItem("Auf ein Neues");
		JMenuItem Save=new JMenuItem("Osama bin Speichern");
		JMenuItem Load=new JMenuItem("Osama bin  Laden");
		JMenuItem Exit=new JMenuItem("Weg Hier!");
		
		data.add(New);
		New.setToolTipText("Neue Leveldatei erstellen.");
		New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NeueKarte ext=new NeueKarte();	//ruft das fenster für die neuen Kartenparameter auf
			}
		});
		
		data.add(Save);
		Save.setToolTipText("Ruft das Speichermenü auf.");
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save=true;
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
		JMenuItem checkpoint=new JMenuItem("Checkpoint");
		JMenuItem exit=new JMenuItem("Ausgang");
		JMenuItem trap=new JMenuItem("Falle");
		JMenuItem teleporter=new JMenuItem("Tür");
		
		feldtyp.add(wall1);
		wall1.setToolTipText("Baut Objekte vom Typ Boden1.");
		wall1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				feldart=2;
			}
		});
		
		feldtyp.add(floor1);
		floor1.setToolTipText("Baut Objekte vom Typ Wand1.");
		floor1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				feldart=1;
			}
		});
		
		feldtyp.add(checkpoint);
		checkpoint.setToolTipText("Baut Objekte vom Typ Checkpoint.");
		checkpoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				feldart=3;
			}
		});
		
		feldtyp.add(exit);
		exit.setToolTipText("Baut Objekte vom Typ Ausgang.");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				feldart=4;
			}
		});
		
		feldtyp.add(trap);
		trap.setToolTipText("Baut Objekte vom Typ Falle.");
		trap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				feldart=5;
			}
		});
		
		feldtyp.add(teleporter);
		teleporter.setToolTipText("Baut Objekte vom Typ Tür.");
		teleporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				feldart=6;
			}
		});
		
		menueleiste.add(data);
		menueleiste.add(feldtyp);
		menueleiste.setVisible(true);
		setJMenuBar(menueleiste);
	}
	
	
	
	//KLASSEN
	public class NeueKarte extends JFrame{
		
		public NeueKarte(){
			final JTextField Breite=new JTextField("Hier Breite eingeben.");
			add(Breite);
			Breite.setVisible(true);
			Breite.setBounds(40, 20, 200, 20);
			
			final JTextField Hoehe=new JTextField("Hier Höhe eingeben.");
			add(Hoehe);
			Hoehe.setVisible(true);
			Hoehe.setBounds(40, 60, 200, 20);
			
			JButton generieren=new JButton("Karte generieren");
			add(generieren);
			generieren.setVisible(true);
			generieren.setBounds(40,100,200,20);
			generieren.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					newMap=true;
					KartenBreite=Integer.parseInt(Breite.getText());
					KartenHoehe=Integer.parseInt(Hoehe.getText());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {e.printStackTrace();}
					dispose();
				}
			});
			
			setLayout(null);
			setSize(280, 180);
			setResizable(false);
			setTitle("Kartenparamter");	
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			
			setLocationRelativeTo(null);	
			setVisible(true);	
		}
	}
}
