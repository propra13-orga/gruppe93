package prototype;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

	
	private Map map;
	private String filename;
	private InputStream files;
	private Player player;
	
	
	MapLoader(String filename, Map map, Player player)
	{
		this.map = map;
		this.filename = filename;
		this.player = player;
	}


	
	public void lesen()
	{
		
		files = MapLoader.class.getClassLoader().getResourceAsStream(filename);
		Scanner s = new Scanner(files);

		if(s.hasNext())
		{
			int sizeX = s.nextInt();
			int sizeY = s.nextInt();
			int startX = s.nextInt();
			int startY = s.nextInt();
			
			player.setPosition((float)startX ,(float)startY);
			
			
			map = new Map(sizeX,sizeY);
			
			
			for(int y= 1; y<=sizeY;y++)
			{
				for(int x = 1; x<=sizeX;x++)
				{
					int tt;
					tt = s.nextInt();
					System.out.print(tt);
					switch(tt)
					{
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
					case 5:
						map.getTile(x, y).setExit();
						break;
					case 6:
						map.getTile(x, y).setWintile();
						break;
					default:
						map.getTile(x, y).setErr();
						break;
					}
				}
			}
		}
		s.close();
	}
}
