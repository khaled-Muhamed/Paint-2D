package eg.edu.alexu.csd.paint2D;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
 
public class DrawArrayLoad {
   
    public void drawArray (Pane root , ArrayList <Properties> arr){
        for (int i=0;i<arr.size();i++){
          if (arr.get(i).getType().equals("Circle")){
            Circle c =new Circle(arr.get(i).getCenterX(), arr.get(i).getCenterY(), arr.get(i).getRadius());
            c.setFill(arr.get(i).getColor());
            c.setStroke(arr.get(i).getColorStrock());
            root.getChildren().add(c);
          } else if (arr.get(i).getType().equals("Rectangle")){
              Rectangle r =new Rectangle(arr.get(i).getCorrnerX(), arr.get(i).getCorrnery(),
                      arr.get(i).getWidth(), arr.get(i).getHeight());
                r.setFill(arr.get(i).getColor());
                r.setStroke(arr.get(i).getColorStrock());
                root.getChildren().add(r);
          }
          else if (arr.get(i).getType().equals("Line")){
              Line l=new Line(arr.get(i).getOldX(), arr.get(i).getOldY(),
                      arr.get(i).getCurrX(), arr.get(i).getCurrY());
                l.setFill(arr.get(i).getColor());
                l.setStroke(arr.get(i).getColorStrock());
                root.getChildren().add(l);   
          }  else if (arr.get(i).getType().equals("Ellipse")){
              Ellipse e =new Ellipse(arr.get(i).getCenterX(), arr.get(i).getCenterY(),
                      arr.get(i).getWidth(), arr.get(i).getHeight());
                e.setFill(arr.get(i).getColor());
                e.setStroke(arr.get(i).getColorStrock());
                root.getChildren().add(e); 
          } else if (arr.get(i).getType().equals("Polygon")){
              Polygon p =new Polygon();
              p.getPoints().addAll(new Double[]{
                        arr.get(i).getPx1(),  arr.get(i).getPy1(),
                        arr.get(i).getPx2(),  arr.get(i).getPy2(),
                        arr.get(i).getPx3(),  arr.get(i).getPy3() });
                p.setFill(arr.get(i).getColor());
                p.setStroke(arr.get(i).getColorStrock());
                root.getChildren().add(p); 
 
          }
 
             
           
           
        }
    }
 
}

