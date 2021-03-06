package Leveleditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import prototype.Map;
import typen.Boden;
import typen.CheckpointTile;
import typen.Err;
import typen.Exit;
import typen.Teleporter;
import typen.Trap;
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
		//Die 40 in der Methode steht hier fest, weil es mir zu un�bersichtlich war die exakte gr��e der einzelnen Feldblocks aus der map bzw dem tile zu laden
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
		if(EditorFrame.feldart==3){
			System.out.println("Checkpoint gesetzt");
			map.getTile((int)(e.getX()+kamera.getX())/40, (int)(e.getY()+kamera.getY())/40).setTileTyp(new CheckpointTile());
		}
		if(EditorFrame.feldart==4){
			System.out.println("Ausgang gesetzt");
			map.getTile((int)(e.getX()+kamera.getX())/40, (int)(e.getY()+kamera.getY())/40).setTileTyp(new Exit());
		}
		if(EditorFrame.feldart==5){
			System.out.println("Falle gesetzt");
			map.getTile((int)(e.getX()+kamera.getX())/40, (int)(e.getY()+kamera.getY())/40).setTileTyp(new Trap());
		}
		if(EditorFrame.feldart==6){
			System.out.println("T�r gesetzt");
			map.getTile((int)(e.getX()+kamera.getX())/40, (int)(e.getY()+kamera.getY())/40).setTileTyp(new Teleporter());
		}
		if(EditorFrame.feldart==7){
			System.out.println("Error gesetzt");
			map.getTile((int)(e.getX()+kamera.getX())/40, (int)(e.getY()+kamera.getY())/40).setTileTyp(new Err());
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
