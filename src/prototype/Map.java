package prototype;

public class Map {
	private boolean spielertot=false;
	private float sekundenTakt=0;
	private static Tile[][] tiles;
	
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
	
	public void errMap(){
		for(int x = 0; x<32;x++){
			for(int y = 0;y<18;y++){
				tiles[x][y].setErr();
			}
		}
	}
	
	public void setWin(){
		for(int x = 0; x<32;x++){
			for(int y = 0;y<18;y++){
				tiles[x][y].setWintile();
			}
		}
	}
	
	public void raumEins(){
		for(int x = 0; x<32;x++){
			for(int y = 0;y<18;y++){
				tiles[x][y].setWall();
			}
		}
		{
			int minx = 2;
			int maxx = 4;
			int miny = 2;
			int maxy = 15;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setFloor();
				}
			}
		}
		{
			int minx = 5;
			int maxx = 15;
			int miny = 2;
			int maxy = 4;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setFloor();
				}
			}
		}
		{
			int minx = 13;
			int maxx = 15;
			int miny = 5;
			int maxy = 15;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setFloor();
				}
			}
		}
		{
			int minx = 16;
			int maxx = 29;
			int miny = 13;
			int maxy = 15;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setFloor();
				}
			}
		}
		{
			int minx = 27;
			int maxx = 29;
			int miny = 2;
			int maxy = 14;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setFloor();
				}
			}
		}
		{
			int minx = 20;
			int maxx = 27;
			int miny = 2;
			int maxy = 4;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setFloor();
				}
			}
		}
		{
			int minx = 20;
			int maxx = 22;
			int miny = 5;
			int maxy = 9;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setFloor();
				}
			}
		}
		tiles[21][8].setTeleporter();

		
	}
	
	public void raumZwei(){
		for(int x = 0; x<32;x++){
			for(int y = 0;y<18;y++){
				tiles[x][y].setWall();
			}
		}
		{
			int minx = 1;
			int maxx = 30;
			int miny = 6;
			int maxy = 8;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setFloor();
				}
			}
		}
		{
			int minx = 9;
			int maxx = 11;
			int miny = 9;
			int maxy = 11;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setFloor();
				}
			}
		}
		{
			int minx = 19;
			int maxx = 21;
			int miny = 9;
			int maxy = 11;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setFloor();
				}
			}
		}
		{
			int minx = 9;
			int maxx = 21;
			int miny = 12;
			int maxy = 14;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setFloor();
				}
			}
		}
		tiles[15][6].setTrap();
		tiles[15][7].setTrap();
		tiles[15][8].setTrap();
		tiles[29][7].setTeleporter();
		
	}
	
	public void raumDrei(){
		for(int x = 0; x<32;x++){
			for(int y = 0;y<18;y++){
				tiles[x][y].setWall();
			}
		}
		for(int x = 1; x<31;x++){
			for(int y = 1;y<17;y++){
				tiles[x][y].setFloor();
			}
		}
		tiles[29][15].setExit();
		{
			int minx = 6;
			int maxx = 6;
			int miny = 1;
			int maxy = 3;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setWall();
				}
			}
		}
		{
			int minx = 1;
			int maxx = 3;
			int miny = 6;
			int maxy = 6;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setWall();
				}
			}
		}
		{
			int minx = 25;
			int maxx = 25;
			int miny = 14;
			int maxy = 16;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setTrap();
				}
			}
		}
		{
			int minx = 25;
			int maxx = 30;
			int miny = 11;
			int maxy = 11;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setTrap();
				}
			}
		}
		{
			int minx = 22;
			int maxx = 22;
			int miny = 8;
			int maxy = 16;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setTrap();
				}
			}
		}
		{
			int minx = 23;
			int maxx = 28;
			int miny = 8;
			int maxy = 8;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setTrap();
				}
			}
		}
		{
			int minx = 28;
			int maxx = 28;
			int miny = 3;
			int maxy = 7;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setTrap();
				}
			}
		}
		{
			int minx = 22;
			int maxx = 22;
			int miny = 3;
			int maxy = 7;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setTrap();
				}
			}
		}
		{
			int minx = 25;
			int maxx = 25;
			int miny = 1;
			int maxy = 5;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setTrap();
				}
			}
		}
		{
			int minx = 12;
			int maxx = 12;
			int miny = 1;
			int maxy = 7;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setWall();
				}
			}
		}
		{
			int minx = 15;
			int maxx = 21;
			int miny = 10;
			int maxy = 10;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setWall();
				}
			}
		}
		tiles[19][7].setTrap();
		tiles[18][6].setTrap();
		tiles[17][5].setTrap();
		tiles[16][4].setTrap();
		tiles[15][5].setTrap();
		tiles[11][5].setTrap();
		tiles[10][6].setTrap();
		tiles[9][7].setTrap();
		tiles[8][8].setTrap();
		tiles[7][9].setTrap();
		tiles[6][10].setTrap();
		tiles[5][11].setTrap();
		tiles[4][12].setTrap();
		tiles[5][13].setTrap();
		tiles[6][14].setTrap();
		{
			int minx = 7;
			int maxx = 15;
			int miny = 14;
			int maxy = 14;
			for(int x = minx; x <= maxx; x++){
				for(int y = miny; y <= maxy; y++){
					tiles[x][y].setTrap();
				}
			}
		}
		
	}
	

}      