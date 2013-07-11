package tiles;

import prototype.Map;

public class Checkpoint {
	
	private static int positionX;
	private static int positionY;
	private static String mapname;
	private static boolean checkpointset = false;

	
	
	public static void setCheckpoint(int positionX, int positionY){
//		if(positionX!=Checkpoint.positionX&&positionY!=Checkpoint.positionY){
			Checkpoint.positionX=positionX;
			Checkpoint.positionY=positionY;
			Checkpoint.checkpointset = true;
			
			Checkpoint.mapname= Map.getCurrentMap();
//		}
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
	
	public static boolean isSet(){
		return checkpointset;
	}

}
