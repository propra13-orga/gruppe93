package prototype;

public class Shop {
	private static boolean inShop;
	private static float f_playPosx;
	private static float f_playPosy;
	private static String lastMap;
	private static long lastPort;
	
	public static boolean isInShop() {
		return inShop;
	}
	
	public static void setInShop(boolean inShop) {
		Shop.inShop = inShop;
	}
	
	public static float getF_playPosx() {
		return f_playPosx;
	}
	
	public static void setF_playPosx(float f_playPosx) {
		Shop.f_playPosx = f_playPosx;
	}
	
	public static float getF_playPosy() {
		return f_playPosy;
	}
	
	public static void setF_playPosy(float f_playPosy) {
		Shop.f_playPosy = f_playPosy;
	}
	
	public static String getLastMap() {
		return lastMap;
	}
	
	public static void setLastMap(String lastMap) {
		Shop.lastMap = lastMap;
	}
	
	public static long getLastPort() {
		return lastPort;
	}
	
	public static void setLastPort(long lastPort) {
		Shop.lastPort = lastPort;
	}
	
	

}
