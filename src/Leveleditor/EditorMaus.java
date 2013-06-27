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
	//Ende DEKLARATION
	
	
	
	//KONSTRUKTOR
	public EditorMaus(){
		
	}
	//Ende KONSTRUKTOR
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(EditorFrame.feldart==1){
			System.out.println("Boden gesetzt");
			map.getTile((int)(e.getX() - 8+kamera.getX())/40, (int)(e.getY()-45+kamera.getY())/40).setTileTyp(new Boden());
		}
		if(EditorFrame.feldart==2){
			System.out.println("Wand gesetzt");
			map.getTile((int)(e.getX() - 8+kamera.getX())/40, (int)(e.getY()-45+kamera.getY())/40).setTileTyp(new Wand());
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
}
