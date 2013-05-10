package prototype;

public class Map {
	
	public static Tiles [][] map = new Tiles[32][18];
	
	//Gibt Map Array zurück
	public static Tiles[][] getMap() {
		return map;
	}
	
	
	// Lädt Testmap1
	public static void  setMap(){
		for(int a = 1; a<31; a++){
			for(int b = 1; b<17; b++){
				map[a][b] = new Tiles(a*40,b*40,false,1);
			}
		}
		for(int a = 0; a<1; a++){
			for(int b = 0; b<17; b++){
				map[a][b] = new Tiles(a*40,b*40,true,2);
			}
		}
		
		for(int a = 31; a<32; a++){
			for(int b = 0; b<17; b++){
				map[a][b] = new Tiles(a*40,b*40,true,2);
			}
		}
		for(int a = 0; a<32; a++){
			for(int b = 0; b<1; b++){
				map[a][b] = new Tiles(a*40,b*40,true,2);
			}
		}
		for(int a = 0; a<32; a++){
			for(int b = 17; b<18; b++){
				map[a][b] = new Tiles(a*40,b*40,true,2);
			}
		}	
	}
		
	

}      
