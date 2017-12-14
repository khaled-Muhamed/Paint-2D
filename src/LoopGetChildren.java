package eg.edu.alexu.csd.paint2D;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
 
public class LoopGetChildren {
    public void check(Pane root, ArrayList<Properties> arrWrite) {
        for (int i = 0; i < root.getChildren().size(); i++) {
            if ((root.getChildren().get(i)) instanceof Circle) {
                Circle c = new Circle();
                c = (Circle) root.getChildren().get(i);
                Properties x = new Properties();
                x.setType("Circle");
                x.setColorStrock((c.getStroke()));
                x.setColor(c.getFill());
                x.setCenterX(c.getCenterX());
                x.setCenterY(c.getCenterY());
                x.setRadius(c.getRadius());
                arrWrite.add(x);
            } else if ((root.getChildren()
                    .get(i)) instanceof Rectangle) {
                Rectangle rec = new Rectangle();
                rec = (Rectangle) root.getChildren().get(i);
                Properties x = new Properties();
                x.setType("Rectangle");
                x.setColorStrock(rec.getStroke());
                x.setColor(rec.getFill());
                x.setCorrnerX(rec.getX());
                x.setCorrnery(rec.getY());
                x.setHeight(rec.getHeight());
                x.setWidth(rec.getWidth());
                arrWrite.add(x);
            } else if ((root.getChildren().get(i)) instanceof Line) {
                Line l = new Line();
                Properties x = new Properties();
                l = (Line) root.getChildren().get(i);
                x.setType("Line");
                x.setColorStrock(l.getStroke());
                x.setColor(l.getFill());
                x.setOldX(l.getStartX());
                x.setOldY(l.getStartY());
                x.setCurrX(l.getEndX());
                x.setCurrY(l.getEndY());
                arrWrite.add(x);
            } else if ((root.getChildren()
                    .get(i)) instanceof Ellipse) {
                Ellipse e = new Ellipse();
                e = (Ellipse) root.getChildren().get(i);
                Properties x = new Properties();
                x.setType("Ellipse");
                x.setColorStrock(e.getStroke());
                x.setColor(e.getFill());
                x.setCenterX(e.getCenterX());
                x.setCenterY(e.getCenterY());
                x.setHeight(e.getRadiusY());
                x.setWidth(e.getRadiusX());
                arrWrite.add(x);
            } else if ((root.getChildren()
                    .get(i)) instanceof Polygon) {
                Polygon p = new Polygon();
                p = (Polygon) root.getChildren().get(i);
                Properties x = new Properties();
                Object[] arr = new String[6];
                arr = p.getPoints().toArray();
                x.setType("Polygon");
                x.setColorStrock(p.getStroke());
                x.setColor(p.getFill());
                x.setPx1(Double.parseDouble(arr[0] + ""));
                x.setPy1(Double.parseDouble(arr[1] + ""));
                x.setPx2(Double.parseDouble(arr[2] + ""));
                x.setPy2(Double.parseDouble(arr[3] + ""));
                x.setPx3(Double.parseDouble(arr[4] + ""));
                x.setPy3(Double.parseDouble(arr[5] + ""));
                arrWrite.add(x);
 
            }
 
        }
 
    }
 
}