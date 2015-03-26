import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Infout{

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
		
		//
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 30));
		int width = getWidth();
		int height = getHeight(); 
			
		int div = width / 10;
		int divH = height / 10;
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){		
				if( (x < 3 && y < 3) || (y == 6 && x == 5) )
					g2.setColor(Color.red);
				else
					g2.setColor(Color.blue);
			// Rectangles are left margin, top margin, width, height
				Rectangle rect1 = new Rectangle(div * x, divH * y, div, divH);
				g2.draw(rect1);
				g2.fill(rect1);
				
			}
		}
		ArrayList <Double>ptlist = new ArrayList<Double>();
		double srcX, srcY, destX, destY;
		for (int node = 0; node < 10; node++){
			srcX = Math.random() * width;
			srcY = Math.random() * height;
			ptlist.add(srcX);
			ptlist.add(srcY);
			for(int count = 0; count < 30; count++){
				int differential = 200;
				destX = srcX - differential / 2 + Math.random() * differential;
				destY = srcY - differential / 2 + Math.random() * differential;
				Line2D.Double vertical = new Line2D.Double(srcX,srcY,destX, destY);
				float dash1[] = {10.0f};
				/*BasicStroke dashed = new BasicStroke(1.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);*/
                 g2.setStroke(new BasicStroke(2));
				//g2.setStroke(dashed);
				g2.setColor(Color.white);
				g2.draw(vertical);
			}
		}
		
		Iterator <Double>itr = ptlist.iterator();
		srcX = itr.next();
		srcY = itr.next();
		while(itr.hasNext()){
			destX = itr.next();
			destY = itr.next();
			Line2D.Double vertical = new Line2D.Double(srcX,srcY,destX, destY);
			float dash1[] = {10.0f};
			/*BasicStroke dashed = new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, dash1, 0.0f);*/
             g2.setStroke(new BasicStroke(2));
			//g2.setStroke(dashed);
			g2.setColor(Color.white);
			g2.draw(vertical);
			srcX = destX;
			srcY = destY;
		}
/*
		g2.setColor(Color.blue);
		Ellipse2D.Double ellipse;
		ellipse = new Ellipse2D.Double(5, 
		   height / 2 + 10, 70, 40);
		g2.draw(ellipse);

		Color randomColor = new Color(
		   (float) Math.random(), (float) Math.random(),
		   (float) Math.random());
		g2.setColor(Color.BLACK);
		Arc2D.Double vertical;
		vertical = new Arc2D.Double(0,0,width / 2 + 5, 10,
		    5, height / 2 + 10, 0);
		g2.draw(vertical);
		g2.setColor(Color.blue);
		

		int xPoly[] = {150,250,325,375,450,275,100};
        int yPoly[] = {150,100,125,225,250,375,300};

        Polygon poly = new Polygon(xPoly, yPoly, xPoly.length);
         g2.setColor(Color.BLUE);
        g2.drawPolygon(poly);
        
        int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;
		int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);
        
        
        
		        // Create a Polygon object
		//int xPoly2[] = {306,250,236,340,165,182,146};
        //int yPoly2[] = {341,277,180,267,214,34,182};

        Polygon deer = new Polygon();
        deer.addPoint(305,436);
        deer.addPoint(305,345);
        deer.addPoint(264,323);
        deer.addPoint(252, 374);
        deer.addPoint(305,436);
        deer.addPoint(305,345);
        deer.addPoint(347,322);
        deer.addPoint(357,374);
        g2.setColor(Color.BLUE);
        g2.drawPolygon(deer);
		//Polygon polygon = new Polygon();
		//polygon.addPoint(305,340);
		/*Polygon polygon = new Polygon();
		// Add points to the polygon
		polygon.addPoint(xCenter + radius, yCenter);
		polygon.addPoint((int)(xCenter + radius *
		Math.cos(2 * Math.PI / 6)), (int)(yCenter - radius *
		Math.sin(2 * Math.PI / 6)));
		polygon.addPoint((int)(xCenter + radius *
		Math.cos(2 * 2 * Math.PI / 6)), (int)(yCenter - radius *
		Math.sin(2 * 2 * Math.PI / 6)));
		polygon.addPoint((int)(xCenter + radius *
		Math.cos(3 * 2 * Math.PI / 6)), (int)(yCenter - radius *
		Math.sin(3 * 2 * Math.PI / 6)));
		polygon.addPoint((int)(xCenter + radius *
		Math.cos(4 * 2 * Math.PI / 6)), (int)(yCenter - radius *
		Math.sin(4 * 2 * Math.PI / 6)));
		polygon.addPoint((int)(xCenter + radius * Math.cos(7 * 2 * Math.PI / 6)), (int)(yCenter - radius * Math.sin(7 * 2 * Math.PI / 6)));
		
		polygon.addPoint((int)(xCenter + radius * Math.cos(8 * 2 * Math.PI / 6)), (int)(yCenter - radius * Math.sin(7 * 2 * Math.PI / 6)));
		
		polygon.addPoint((int)(xCenter + radius * Math.cos(9 * 2 * Math.PI / 6)), (int)(yCenter - radius * Math.sin(7 * 2 * Math.PI / 6)));
		*/
		
		// Draw the polygon
		//g2.drawPolygon(polygon);
	//	g2.fill(polygon);
		
		g2.setColor(Color.BLACK);
		//g2.drawString("NETWORKONIA", xCenter -  xCenter * 5 / 10, yCenter);
		
		
		
	}       
	
}
