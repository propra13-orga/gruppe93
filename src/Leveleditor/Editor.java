package Leveleditor;

import java.awt.Event;
import java.awt.event.MouseEvent;

import player.Player;
import prototype.Map;
import prototype.MapLoader;

public class Editor {
	public static void main(String[] args) {
		
		Map map=new Map(1,1,null);		
		EditorFrame fenster=new EditorFrame("Editor",map);
		
		while(true){
			fenster.nextFrame();
			
//			if (fenster.maus.mouseClicked(e)){
//				
//			}
			
			//neue Karte?
			if(fenster.newMap=true){
				map=new Map(fenster.KartenBreite,fenster.KartenHoehe,null);
				fenster.changeMap(map);
				fenster.newMap=false;
			}
			
			//Schlafen
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {e.printStackTrace();}
			//Schlafen Ende
		}
		
	}
}
