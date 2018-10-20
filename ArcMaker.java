import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class ShapeTest
{
  public static void main(String[] args)
  {
    JFrame frame = new ShapeTestFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}

class ShapeTestFrame()
{
  public ShapeTestFrame()
  {
    setTitle("ShapeTest");
    setSize(DEFAULT_WIDTH , DEFAULT_HEIGHT);
    final ShapePanel panel = new ShapePanel();
    add(panel, BoarderLayout.CENTER);
    final JComboBox comboBox = new JComboBox();
    comboBox.addItem(new LineMaker());
    comboBox.addItem(new RectangleMaker());
    comboBox.addItem(new RoundRectangleMaker());
    comboBox.addItem(new EllipseMaker());
    comboBox.addItem(new ArcMaker());
    comboBox.addItem(new PolygonMaker());
    comboBox.addItem(new QuadCurveMaker());
    comboBox.addItem(new CubicCurveMaker());
    comboBox.addActionListener(new
        ActionListener(){
          public void actionPerformed (ActionEvent event )
          {
            ShapeMaker shapeMaker = (ShapeMaker) comboBox.getSelectedItem();
            panel.setShapeMaker(shapeMaker);
            
          }
        });
    add(comboBox ,BorderLayout.NORTH);
    panel.setShapeMaker((ShapeMaker) comboBox.getItemAt(0));
  }
  
 private static final int DEFAULT_WIDTH = 300 ;
 private static final int DEFAULT_HEIGHT = 300;
 
  
}

class ShapePanel extends JPanel
{
  public ShapePanel()
  {
    addMouseListener(new
    MouseAdapter(){
      public void mousePressed(MouseEvent event)
      {
       Point p = event.getPoint();
       for(int i = 0 ; i< points.length; i++)
       {
         double x = points[i].getX()- SIZE/2;
         double y = points[i].getY() -SIZE/2 ;
         Rectangle2D r = new Rectangle2D.Double(x,y,SIZE,SIZE);
         if(r.contains(p))
         {
          current = i ; 
          return ; 
          
         }
       }
      }
      public void mouseReleased(MouseEvent event)
      {
      current= - 1 ;
      
      }
    });
    addMouseMotionListener(new
    MouseMotionAdapter()
    {
     public void mouseDragged(MouseEvent event)
     {
      if(current == -1 ) return;
      points[current] = event.getPoint();
      repaint();
      
     }
    });
    current= - 1; 
  }
  
  public void setShapeMaker(ShapeMaker aShapeMaker)
  {
    shapeMaker = aShapeMaker ; 
    int n = shapeMaker.getPointCount();
    points = new Point2D[n];
    for(int i= 0 ; i< n ; i++){
      double x = generator.nextDouble()*getWidth();
      double y = generator.nextDouble()*getHeight();
      points[i] = new Point2D.Double(x,y);
      
    }
    repaint();
  }
  
  public void paintComponent(Graphics g){
  super.paintComponent(g);
  if(points == null) return ; 
  Graphics2D g2 = (Graphics2D ) g ;
  for(int i = 0 ; i<points.length ; i++)
  {
   double x = points[i].getX() -SIZE/2 ;
   double y = points[i].getY() - SIZE/2;
   g2.fill(new Rectangle2D.Double(x,y,SIZE,SIZE));
  }
  g2.draw(shapeMaker.makeShape(points));
  
  }
  
  private Point2D[] points;
  private static Random generator  = new Random();
  private static int SIZE = 10 ; 
  private int current ; 
  private ShapeMaker shapeMaker ;
  
}

abstract class ShapeMaker
{
  public ShapeMaker(int aPointCount){
    pointCount = aPointCount
  }
  
  public int getPointCount()
  {
    return pointCount;
  }
  public abstract Shape makeShape(Point2D[] p);
  public String toString()
  {
    return getClass().getName();
    
  }
  private int pointCount;
}

class ArcMaker extends ShapeMaker
{
  public ArcMaker(){super(4);}

public Shape makeShape(Point2D[] p)
{
  double centerX = (p[0].getX() + p[1].getX())/2;
  double centerY = (p[0].getY() + p[1].getY())/2;
  double width = Math.abs(p[1].getX() - p[0].getX());
  double height = Math.abs(p[1].getY() - p[0].getY());
  
  double distortedStartAngle = Math.toDegrees(Math.atan2(-(p[2].getY() - centerY)*width , (p[2].getX()-centerX)*height));
  double distortedEndAngle = Math.toDegrees(Math.atan2(-(p[3].getY() - centerY)*width , (p[3].getX()-centerX)*height));
  double distortedAngleDifference = distortedEndAngle - distortedStartAngle;
  if(distortedStartAngle < 0) distortedStartAngle+= 360 ;
  if(distrotedAngleDifference<0) distortedangledifference +=360;
  Arc2D s = new Arc2D.Double(0,0,0,0,distortedStartAngle,distortedAngleDifference  ,Arc2D.OPEN);
  s.setFrameFromDiagonal(p[0],p[1]);
  GeneralPath g = new GeneralPath();
  g.append(s, false );
  Rectangle2D r = new Rectangle2D.Double();
  r.setFrameFromDiagonal (p[0],p[1]);
  g.append(new Line2D.Double ( center, p[2] ),false);
  g.append(new Line2D.Double(center , p[3] ),false );
  return g ;
}

}

