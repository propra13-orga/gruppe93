package Leveleditor;

import java.awt.Event;
import java.awt.event.MouseEvent;

import player.Player;
import prototype.Map;
import prototype.MapLoader;

public class Editor {
	public static void main(String[] args) {
		
		Map map=new Map(10,10,null);
		Kamera kamera=new Kamera();
		EditorFrame fenster=new EditorFrame("Editor",map,kamera);
		fenster.maus.setMap(map);
		fenster.maus.setKamera(kamera);
		
		
		
		while(true){
			fenster.nextFrame();
			kamera.update(0.01f);
			
			
			
			
			//neue Karte?
			if(fenster.newMap==true){
				map=new Map(fenster.KartenBreite,fenster.KartenHoehe,null);
				fenster.changeMap(map);
				fenster.maus.setMap(map);
				fenster.newMap=false;
			}
			
			//Schlafen
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {e.printStackTrace();}
			//Schlafen Ende
		}
		
	}
}
