package tiles;

public class Checkpoint {
	
	private static int positionX;
	private static int positionY;

	
	
	public static void setCheckpoint(int positionX, int positionY){
		if(positionX!=Checkpoint.positionX&&positionY!=Checkpoint.positionY){
			Checkpoint.positionX=positionX;
			Checkpoint.positionY=positionY;
		}
	}

}
