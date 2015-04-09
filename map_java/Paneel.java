import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;

public class Paneel{

    public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		final int FRAME_WIDTH = 500;
		final int FRAME_HEIGHT = 300;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		frame.setLocation(5, 5);
		frame.setTitle("Graphics Examples");
		// Draw shapes
		GraphicsExampleComponent example;
		example = new GraphicsExampleComponent();
		frame.add(example);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setFont(new Font("Impact",Font.BOLD,16));
		frame.setVisible( true );
    }
}

class GraphicsExampleComponent extends JComponent{
	public void paintComponent(Graphics g){
		try{
			Graphics2D g2 = (Graphics2D) g;
			g2.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 30));
			int width = getWidth();
			int height = getHeight(); 
			
			String imageSource = "World100.gif";
			Image img = ImageIO.read(new File(imageSource));
	    	g2.drawImage(img, 0, 0, width, height, null);
	    	ArrayList<Double> washingtondc = new ArrayList<Double>();
	    	double lat = 38.900002;
	    	double lng = -77.000000;
	    	drawNode( g2, lat, lng, getWidth(), getHeight() );
		}
		catch(Exception e){
			System.out.println( e.getMessage() );
		}
	}       
	
	public static int toX(double lng, int width){
		lng += 180; //scaled to 0 to 360
		lng /= 360; //ratio 0 to 1
		int out = (int) lng * width;
		return out;
	}
	public static int toY(double lat, int height){
		lat += 90; //scaled to 0 to 360
		lat /= 180; //ratio 0 to 1
		lat = 1 - lat;
		int out = (int) lat * height;
		return out;
	}
	public static int lngToX(double lng, double width){
		double x = (lng + 170) * width / 360;
		return (int) x;
	}
	public static int latToY(double lat, double height){
		double y = height - (lat + 90) / 180 * height;
		return (int) y;
	}
	//fourCoords is formatted srcX, srcY, destX, destY
	public static void drawNode( Graphics2D g2, double lat, double lng, int width, int height ){
		g2.setColor(Color.blue);
		// Rectangles are left margin, top margin, width, height
		Rectangle rect1 = new Rectangle( lngToX(lng, width), latToY(lat, height), 20, 20);
		g2.draw(rect1);
		g2.fill(rect1);
	}
	
}
