/**
 * date: Feb. 17th, 2014
 * @author cpatterson
 */
 
import java.awt.*;

public class Circle {
    //data members
    private double dblRadius;
    final public double PI = 3.142;
    private Color penColor;
    private Color fillColor;
    private int xCoord, yCoord;
    //constructor
    public Circle(){
        this(0);
    }
    
    public Circle(double radius){
        dblRadius = radius;
        penColor = Color.BLACK;
        fillColor = Color.WHITE;
        xCoord = 0;
        yCoord = 0;
    }
    //other methods
    public double getArea(){
        return PI * dblRadius * dblRadius;
        //return PI * Math.pow(dblRadius, 2);
    }
    
    public double getCircumference(){
        return 2 * PI * dblRadius;
    }
    
    public void setRadius(double r){
        if (r > 0 ){
            dblRadius = r;
        }else{
            dblRadius = 0;
        }
    }
    
    public void setXCoord(int x){
        if (x > 0){
            xCoord = x;
        }else {
            xCoord = 0;
        }
    }
    
    public void setYCoord(int y){
        if (y > 0){
            yCoord = y;
        }else {
            yCoord = 0;
        }
    }
    
    public void setPenColor(Color pen){
        penColor = pen;
    }
    
    public void setFillColor(Color fill){
        fillColor = fill;
    }
    
    public void draw(Graphics gContext){
        int width = (int)dblRadius *2;
        int height = (int)dblRadius * 2;
        gContext.setColor(fillColor);
        gContext.fillOval(xCoord, yCoord, width, height);
        gContext.setColor(penColor);
        gContext.drawOval(xCoord, yCoord, width, height);
        gContext.dispose();
    }
}
