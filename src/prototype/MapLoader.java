package prototype;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

	
	private Map map;
	private String filename;
	private InputStream files;
	
	
	
	MapLoader(String filename, Map map)
	{
		this.map = map;
		this.filename = filename;
	}


	
	public void lesen()
	{
		
		files = MapLoader.class.getClassLoader().getResourceAsStream(filename);
		Scanner s = new Scanner(files);

		if(s.hasNext())
		{
			
			for(int y= 0; y<18;y++)
			{
				for(int x = 0; x<32;x++)
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
