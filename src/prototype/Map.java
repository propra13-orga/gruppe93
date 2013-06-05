package prototype;

import java.util.List;

public class Map 
{
	private boolean spielertot=false;
	private float sekundenTakt=0;
	private static Tile[][] tiles;
	private List<Gegner> Enemys;
	private static int x_Tiles;
	private static int y_Tiles;
	private String nextmap;
	
	//KONSTRUKTOR
	public Map(int x_Tiles, int y_Tiles, String nextmap){
		this.nextmap=nextmap;
		Map.x_Tiles = x_Tiles+1;
		Map.y_Tiles = y_Tiles+1;
		tiles = new Tile[x_Tiles+2][y_Tiles+2];
		for(int x = 1; x <= x_Tiles; x++)
		{
			for(int y = 0; y <= y_Tiles; y++){
				tiles[x][y] = new  Tile(x*40, y*40, false, 1); //Initialisiert jedes Feld der nutzbaren Map
			}
		}
		
		// Zwangsramen
		
		for(int x = 0; x <= x_Tiles+1;x++){tiles[x][0]= new Tile(x*40, 0*40, true, 2);}
		for(int x = 0; x <= x_Tiles+1;x++){tiles[x][y_Tiles+1]= new Tile(x*40, (y_Tiles+1)*40, true, 2);}
		for(int y = 0; y <= y_Tiles+1;y++){tiles[0][y]= new Tile(0*40, y*40, true, 2);}
		for(int y = 0; y <= y_Tiles+1;y++){tiles[x_Tiles+1][y]= new Tile((x_Tiles+1)*40, y*40, true, 2);}
	}

	//METHODEN
	public Tile getTile(int x,int y)
	{
		return tiles[x][y];
	}
	
	public int getXTiles(){
		return x_Tiles;
	}
	
	public int getYTiles() {
		return y_Tiles;
	}
	
	public void setNextMap(String nextmap)
	{
		this.nextmap = nextmap;
	}
	
	public String getNextMap(){
		return nextmap;
	}
	
	public void setSpielerTod(boolean tot)
	{
		spielertot=tot;
		sekundenTakt=0;//damit das Blinken immer auf schwarz beginnt und gleichmaeßig startet
	}
	
	public void spielerTodAnimation(float frametime)//Blinken fuer Spielertod
	{
		sekundenTakt+=frametime;
		if(sekundenTakt>=2)sekundenTakt=0;
		if(spielertot&&sekundenTakt<1)
		{
			for(int x = 0; x <=x_Tiles; x++)
			{
				for(int y = 0; y <= y_Tiles; y++)
				{
					tiles[x][y].setTex(5);
				}
			}
		}else if(spielertot)//sekundentakt zwischen 1und 2 ist hier klar und muss nicht nochmal geprueft werden
		{
			for(int x = 0; x <=x_Tiles; x++)
			{
				for(int y = 0; y <=y_Tiles; y++)
				{
					tiles[x][y].setTex(6);
				}
			}
		}
	}
	
	public void errMap()//Errstellt map die auf Fehler hinweisen soll 
	{
		for(int x = 0; x<32;x++)
		{
			for(int y = 0;y<18;y++)
			{
				tiles[x][y].setErr();
			}
		}
	}
	
	public void setWin(){ 		//Pokale Pokale Pokale
		for(int x = 0; x<=x_Tiles;x++)
		{
			for(int y = 0;y<y_Tiles;y++)
			{
				tiles[x][y].setWintile();
			}
		}
	}
	
	//Maps
	public void  erstelleTestMap(List<Gegner> Enemys){
		this.Enemys=Enemys;
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
		//Enemys.add(new Gegner( 500, 400, Enemys)); //spawnen in Raum 1 und Testmap
		//Enemys.add(new Gegner(1000, 500, Enemys));
		//Enemys.add(new Gegner( 1000, 600, Enemys));
		//Enemys.add(new Gegner(1000, 400, Enemys));
		//Enemys.add(new Gegner(1000, 200, Enemys));
	}
	
	//TODO Sichern oder Löschen
	
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
		Enemys.add(new Gegner( 500, 400, Enemys));
		Enemys.add(new Gegner(7000, 500, Enemys));
		Enemys.add(new Gegner( 700, 600, Enemys));
		Enemys.add(new Gegner(700, 400, Enemys));
		Enemys.add(new Gegner(700, 200, Enemys));
		
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