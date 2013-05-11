package prototype;

public class Map {
	
	private Tile[][] tiles;
	
	//KONSTRUKTOR
	public Map(){
		tiles = new Tile[32][18];
	}
	
	

	//METHODEN
	public Tile getTile(int x,int y){
		return tiles[x][y];
	}
	
	
	
	public void  erstelleTestMap(){
		for(int a = 1; a<31; a++){
			for(int b = 1; b<17; b++){
				tiles[a][b] = new Tile(a*40,b*40,false,1);
			}
		}
		for(int a = 0; a<1; a++){
			for(int b = 0; b<17; b++){
				tiles[a][b] = new Tile(a*40,b*40,true,2);
			}
		}
		
		for(int a = 31; a<32; a++){
			for(int b = 0; b<17; b++){
				tiles[a][b] = new Tile(a*40,b*40,true,2);
			}
		}
		for(int a = 0; a<32; a++){
			for(int b = 0; b<1; b++){
				tiles[a][b] = new Tile(a*40,b*40,true,2);
			}
		}
		for(int a = 0; a<32; a++){
			for(int b = 17; b<18; b++){
				tiles[a][b] = new Tile(a*40,b*40,true,2);
			}
		}	
	}
		
	

}      
