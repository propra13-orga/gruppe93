package prototype;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Licht {
	private static BufferedImage buffImage=new BufferedImage(4000, 4000,
            BufferedImage.TYPE_INT_ARGB);
	private static int radius;
	private static int x;
	private static int y;
	public static boolean newmap=true;
	 

	
	
	
	
	
	public Licht( int X, int Y,int Radius){
		radius=Radius;
		x=X;
		y=Y;
       
       
		Graphics2D gbi3 = buffImage.createGraphics();
        if(newmap){
        gbi3.setColor(new Color(0,0,0,255));
        gbi3.fillRect(0,0,4000,4000);
	    newmap=false;
        }

	    Point2D center = new Point2D.Float(x, y);
	    Point2D focus = new Point2D.Float(x, y);
	    float[] dist = {0f, 1f};
	    Color[] colors = {new Color(0,0, 0,255), new Color(0, 0, 0, 0)};
	    RadialGradientPaint rgp = new RadialGradientPaint(center, radius, focus, dist, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE);
	    gbi3.setPaint(rgp);
	    gbi3.setComposite(AlphaComposite.DstOut);
	    gbi3.fillRect(0,0, 2000, 2000);
	    
	    
//	    Point2D center1 = new Point2D.Float(x-400, y-400);
//	    Point2D focus1 = new Point2D.Float(x-400, y-400);
//	    float[] dist1 = {0f, 1f};
//	    Color[] colors1 = {new Color(0,0, 0,255), new Color(0, 0, 0, 0)};
//	    RadialGradientPaint rgp1 = new RadialGradientPaint(center1, radius, focus1, dist1, colors1, MultipleGradientPaint.CycleMethod.NO_CYCLE);
//	    gbi3.setPaint(rgp1);
//	    gbi3.setComposite(AlphaComposite.DstOut);
//	    gbi3.fillRect(0,0, 2000, 2000);
	    
	    
	 
	
	}
	public static BufferedImage getLook() {

		return buffImage;

	}
	
	

}
