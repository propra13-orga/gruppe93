package prototype;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapLoader {
	
	Map map;	
	Scanner s;
	
	MapLoader(File f,Map map){
		this.map = map;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			map.errMap();
			e.printStackTrace();
		}
		
	}
	
	public void lesen(){

		if(s.hasNext()){
			
			for(int x= 0; x<32;x++ ){
				for(int y = 0; y<18;y++){
					int tt;
					tt = s.nextInt();
					switch(tt){
					case 1:
						map.getTile(x, y).setFloor();
						break;
					case 2:
						map.getTile(x, y).setWall();
						break;
					case 3:
						map.getTile(x, y).setTrap();
						break;
					case 4:
						map.getTile(x, y).setTeleporter();
						break;
					default:
						map.getTile(x, y).setErr();
						break;
					}
				}
			}
		}
	}
	
	public void schliesen(){
		s.close();
	}

}
