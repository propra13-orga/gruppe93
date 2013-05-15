package prototype;

public class Map {
	private boolean spielertot=false;
	private float sekundenTakt=0;
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
		tiles[5][5].setTrap();
	}
	
	public void setSpielerTod(boolean tot){
		spielertot=tot;
		sekundenTakt=0;//damit das Blinken immer auf schwarz beginnt und gleichmäßig startet
	}
	
	public void update(float frametime){//damit kann man alles mögliche auf der map ändern
		sekundenTakt+=frametime;
		if(sekundenTakt>=2)sekundenTakt=0;
		if(spielertot&&sekundenTakt<1){
			for(int x = 0; x <32; x++){
				for(int y = 0; y < 18; y++){
					tiles[x][y].setTex(5);
				}
			}
		}else if(spielertot){//sekundentakt zwischen 1und 2 ist hier klar und muss nicht nochmal geprüft werden
			for(int x = 0; x <32; x++){
				for(int y = 0; y < 18; y++){
					tiles[x][y].setTex(6);
				}
			}
		}
		
	}
}      
