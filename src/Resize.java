package eg.edu.alexu.csd.paint2D;

import java.util.Stack;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Resize {

	public double centerX;
	public double centerY;
	Circle circle;
	Ellipse ellipse ;
	Rectangle rectangle ;
	String squareOrRectangle;
	Line line ;
	Polygon tripoly;
	int counter = 0 ;
	public void makeResizable(Node node, Pane root,Stack undoStack) {
	
		node.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (node instanceof Rectangle)
				{	
				rectangle = (Rectangle) node;
					if(rectangle.getWidth()==rectangle.getHeight())
					{
					squareOrRectangle = "Square";
					}else{
					squareOrRectangle = "Rectangle";
					}
				
				centerX=rectangle.getX()+(rectangle.getWidth()/2);	
				centerY=rectangle.getY()+(rectangle.getHeight()/2);	
					
				}
				int index;
				for( index = 0 ; index < root.getChildren().size(); index++)
				  {
					  if(node.getLayoutX() == root.getChildren().get(index).getLayoutX())
					  {break;}
				  }  
				    
				 final Undo movedObject = new Undo (node,"Resized",index ,node.getLayoutX(),node.getLayoutY());
				  
				 undoStack.add(movedObject);
			}
		
		});

		node.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				counter++;
				if (node instanceof Circle)
				{	
					circle = (Circle) node;
					circle.setRadius(new Point2D(circle.getCenterX(),circle.getCenterY()).distance(event.getX(),event.getY()));
					circle.setStyle("-fx-stroke-width: 3; -fx-stroke:  yellow;"+"-fx-stroke-dash-array: 2 12 12 2; ");
					circle.setCursor(Cursor.E_RESIZE);
				} else if(node instanceof Ellipse)
				{
					ellipse= (Ellipse) node;
					ellipse.setRadiusX(Math.abs(event.getX()-ellipse.getCenterX()));
					ellipse.setRadiusY(Math.abs(event.getY()-ellipse.getCenterY()));
					ellipse.setStyle("-fx-stroke-width: 3; -fx-stroke: yellow;"+"-fx-stroke-dash-array: 2 12 12 2; ");
					ellipse.setCursor(Cursor.E_RESIZE);
				} else if(node instanceof Rectangle)
				{
				double width  = 2*Math.abs(centerX - event.getX()) ,
						height = 2* Math.abs(centerY - event.getY()) ;
				if(squareOrRectangle == "Square")
				{double maxLength = Math.max( width , height);
					rectangle.setX(centerX-maxLength/2);
					rectangle.setY(centerY-maxLength/2);
					rectangle.setWidth(maxLength);
					rectangle.setHeight(maxLength);
				} else
				{
					rectangle.setX(centerX-width/2);
					rectangle.setY(centerY-height/2);
					rectangle.setWidth(width);
					rectangle.setHeight(height);
				}
					rectangle.setStyle("-fx-stroke-width: 3; -fx-stroke: yellow;"+"-fx-stroke-dash-array: 2 12 12 2; ");
					rectangle.setCursor(Cursor.E_RESIZE);

				} else if(node instanceof Line)
				{
					line = (Line) node;
					line.setEndX(event.getSceneX());
					line.setEndY(event.getSceneY());	
					line.setStyle("-fx-stroke-width: 3; -fx-stroke: yellow;"+"-fx-stroke-dash-array: 2 12 12 2; ");
					line.setCursor(Cursor.E_RESIZE);
					
				} else if(node instanceof Polygon)
				{
					tripoly = (Polygon) node ;
					tripoly.getPoints().set(4, event.getX());
					tripoly.getPoints().set(5, event.getY());
					tripoly.setStyle("-fx-stroke-width: 3; -fx-stroke: yellow;"+"-fx-stroke-dash-array: 2 12 12 2; ");
					tripoly.setCursor(Cursor.E_RESIZE);
				}
				}
		});
		node.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(counter > 0){
				if (node instanceof Circle)
				{	
					circle.setStyle("-fx-stroke-width: 1; -fx-stroke: #000000;");
					circle.setCursor(Cursor.DEFAULT);
				} else if (node instanceof Ellipse)
				{	
					ellipse.setStyle("-fx-stroke-width: 1; -fx-stroke: #000000;");
					ellipse.setCursor(Cursor.DEFAULT);
				} else if (node instanceof Rectangle)
				{	
					rectangle.setStyle("-fx-stroke-width: 1; -fx-stroke: #000000;");
					rectangle.setCursor(Cursor.DEFAULT);
				} else if(node instanceof Line)
				{
					line.setStyle("-fx-stroke-width: 1; -fx-stroke: #000000;");
					line.setCursor(Cursor.DEFAULT);
				}else if(node instanceof Polygon)
				{
					tripoly.setStyle("-fx-stroke-width: 1; -fx-stroke: #000000;");
					tripoly.setCursor(Cursor.DEFAULT);
				}
				
					
			}
			}
		});


	
	
	}
		
}
