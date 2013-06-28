package Leveleditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import prototype.Map;
import typen.Boden;
import typen.Wand;

public class EditorMaus implements MouseListener{
	//DEKLARATION
	private Map map;
	private Kamera kamera;
	private EditorFrame frame;
	//Ende DEKLARATION
	
	
	
	//KONSTRUKTOR
	public EditorMaus(){
		
	}
	//Ende KONSTRUKTOR
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//Die 40 in der Methode steht hier fest, weil es mir zu unübersichtlich war die exakte größe der einzelnen Feldblocks aus der map bzw dem tile zu laden
		//Falls der Klick nicht auf der map ist.
		if(0>(int)(e.getX()+kamera.getX())/40||map.getXTiles()<(int)(e.getX()+kamera.getX())/40||0>(int)(e.getY()+kamera.getY())/40||map.getYTiles()<(int)(e.getY()+kamera.getY())/40){
			return;
		}
		//wird direkt retrunt und kein Fehler erzeugt
		if(EditorFrame.feldart==1){
			System.out.println("Boden gesetzt");
			map.getTile((int)(e.getX()+kamera.getX())/40, (int)(e.getY()+kamera.getY())/40).setTileTyp(new Boden());
		}
		if(EditorFrame.feldart==2){
			System.out.println("Wand gesetzt");
			map.getTile((int)(e.getX()+kamera.getX())/40, (int)(e.getY()+kamera.getY())/40).setTileTyp(new Wand());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public void setMap(Map map){
		this.map=map;
	}
	
	public void setKamera(Kamera kamera){
		this.kamera=kamera;
	}
	
	public void setFenster(EditorFrame frame){
		this.frame=frame;
	}
}
