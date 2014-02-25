/*ShapeMaker class
  Purpose:  to allow the user to choose a shape and colors to be drawn
  Author:   Cyndy Patterson
  Date:     2/18/2014
*/

import javax.swing.*;
import java.awt.*;

public class ShapeMaker
{
    //global object declarations
    private JFrame mainWindow;
    private Container contentPane;
    
    //main method - starting point for the program
    public static void main(String [] args)
    {
      ShapeMaker shapeMaker = new ShapeMaker();
      shapeMaker.start();
    }
    
    //constructor
    public ShapeMaker()
    {
      mainWindow = new JFrame("Shape Maker");
      mainWindow.setSize(500,400);
      mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainWindow.setVisible(true);
      contentPane = mainWindow.getContentPane();
      contentPane.setBackground(Color.white);
    }
    
    public void start()
    {
      String menu = "Enter a shape number: \n"
                     + "1 - Circle \n"
                     + "2 - Rectangle \n"
                     + "3 - Triangle \n";
      int shape = 0;
      
      shape = Integer.parseInt(JOptionPane.showInputDialog(mainWindow,
               menu));
               
      switch (shape)     
      {
         case 1:
            Circle circle = new Circle();
            getCircleInfo(circle);
            drawCircle(circle);
            break;   
         case 2:
            //do rectangle stuff
            break;
         case 3:
            //do triangle stuff
            break;
         default:
            JOptionPane.showMessageDialog(mainWindow, 
                  "Error: invalid shape number! \n"
                  + "Click OK to terminate application.");
            
      }
    }
    
    public void getCircleInfo(Circle c)
    {
      int x = 0, y = 0;
      double radius = Double.parseDouble(JOptionPane.showInputDialog(mainWindow,
                        "Enter the radius of the circle."));
      c.setRadius(radius);
      
      x = Integer.parseInt(JOptionPane.showInputDialog(mainWindow,
                           "Enter the x coordinate of the upper-left corner."));
      y = Integer.parseInt(JOptionPane.showInputDialog(mainWindow,
                           "Enter the y coordinate of the upper-left corner."));
      c.setXCoord(x);
      c.setYCoord(y);
      
      //color choice code
      
      c.setPenColor(Color.black);
      c.setFillColor(Color.pink);
    }
    
    public void drawCircle(Circle circle)
    {
      Graphics graphicsContext = contentPane.getGraphics();
      circle.draw(graphicsContext);
    }
    
}
