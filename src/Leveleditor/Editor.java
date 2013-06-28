package Leveleditor;

import java.awt.Event;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

import player.Player;
import prototype.Map;
import prototype.MapLoader;
import typen.Wand;

public class Editor {
	public static void main(String[] args) {
		
		Map map=new Map(10,10,null);
		Kamera kamera=new Kamera();
		EditorFrame fenster=new EditorFrame("Editor",map,kamera);
		fenster.maus.setMap(map);
		fenster.maus.setKamera(kamera);
		fenster.maus.setFenster(fenster);

		
		
		while(true){
			fenster.nextFrame();
			kamera.update(0.01f);
			
			
			//Speichern?
			if(fenster.save){
				speichern(map);
				fenster.save=false;
			}
			
			//neue Karte?
			if(fenster.newMap){
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
		
	}//main
	
	
	
	//METHODEN
	public static void speichern(Map map){
		String kartenString=new String("");
		
		kartenString+=map.getXTiles()+" ";
		kartenString+=map.getYTiles()+" ";
		kartenString+="Hier Spielerstarpunkt dieser map eintragen (x y) ";
		kartenString+="Hier Pfad der nächsten map eintragen ";
		
		for(int y= 1; y<=map.getXTiles();y++)
		{
			for(int x = 1; x<=map.getYTiles();x++)
			{
				if(map.getTile(x, y).getTileTyp().getName()=="Wand"){
					kartenString+="2 ";
				}else
				if(map.getTile(x, y).getTileTyp().getName()=="Boden"){
					kartenString+="1 ";
				}
			}
		}
		
		System.out.println(kartenString);
		File Speicher=new File("C://JavaMaps//AktuelleMap.txt");
		try {
			Formatter x=new Formatter(Speicher);
			x.format("%s", kartenString);
			x.close();	//sonst wird die Datei nicht geschlossen und nix passiert
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Gespeichert!");
	}
}
