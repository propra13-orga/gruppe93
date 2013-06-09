package tiles;

/*
 *  Sammlung von Konstructorenaufrufen fuer verschiedene Felder
 */

public class TileSpawner{
	
	public static Tile Err(int positionX, int positionY)
	{
		Tile Err = new Tile(0,positionX*40, positionY*40);
		return Err;
	}	
	
	public static Tile Boden(int positionX, int positionY)
	{
		Tile Boden = new Tile(1,positionX*40, positionY*40);
		return Boden;
	}
	
	public static Tile Wand(int positionX, int positionY)
	{
		Tile Wand = new Tile(2,positionX*40, positionY*40);
		Wand.setBlockiert(true);
		return Wand;
	}	
	
	public static Tile Trap(int positionX, int positionY)
	{
		Tile Trap = new Tile(3,positionX*40, positionY*40);
		Trap.setTrap(true);
		return Trap;
	}
	
	public static Tile Teleporter(int positionX, int positionY)
	{
		Tile Teleporter = new Tile(4,positionX*40, positionY*40);
		Teleporter.setTranporter(true);
		return Teleporter;
	}
	
	public static Tile Shop(int positionX, int positionY)
	{
		Tile Shop = new Tile(5,positionX*40, positionY*40);
		Shop.setShop(true);
		return Shop;
	}	
	
	public static Tile Exit(int positionX, int positionY)
	{
		Tile Exit = new Tile(6,positionX*40, positionY*40);
		Exit.setExit(true);
		return Exit;
	}	
	
	public static Tile WinTile(int positionX, int positionY)
	{
		Tile Win = new Tile(7,positionX*40, positionY*40);
		return Win;
	}	
	
}
