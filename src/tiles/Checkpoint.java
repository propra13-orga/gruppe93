package tiles;

import prototype.Map;

public class Checkpoint {
	
	private static int positionX;
	private static int positionY;
	private static String mapname;

	
	
	public static void setCheckpoint(int positionX, int positionY){
		if(positionX!=Checkpoint.positionX&&positionY!=Checkpoint.positionY){
			Checkpoint.positionX=positionX;
			Checkpoint.positionY=positionY;
			System.out.println(positionX);
			System.out.println(positionY);
			
			Checkpoint.mapname= Map.getCurrentMap();
		}
	}



	public static int getPositionX() {
		return positionX;
	}


	public static int getPositionY() {
		return positionY;
	}

	public static String getMapname() {
		return mapname;
	}

}
