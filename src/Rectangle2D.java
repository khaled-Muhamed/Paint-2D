package eg.edu.alexu.csd.paint2D;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Stack;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
public class Rectangle2D implements Shapes {

	private Rectangle rectangle;
	private Pane root;
	private boolean isDone;
	private boolean isStarted;
	private double centerx;
	private double centery;
	private String type;
	
	

	public Rectangle2D() {
		
		rectangle = new Rectangle();
		isDone = false;
		isStarted = false;
	}
	public Rectangle2D(Pane root, String type) {
		this.root = root;
		this.type = type;
	}
	public Rectangle2D draw( ) {
		Rectangle2D rect = new Rectangle2D();
		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (rect.isStarted == false) {
					root.getChildren().add(rect.rectangle);
					rect.isStarted = true;
				} else {
					rect.isDone = true;
				}
				if (rect.isDone == false) {
					centerx = (event.getX());
					centery = (event.getY());
				}
			}
		});

		root.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (!rect.isDone && rect.isStarted) {
					double width  = 2*Math.abs(centerx - event.getX()) ,
						   height = 2* Math.abs(centery - event.getY()) ;
					
					if(type == "Rectangle")
					{	rect.rectangle.setX(centerx-width/2);
						rect.rectangle.setY(centery-height/2);
						rect.rectangle.setWidth(width);
						rect.rectangle.setHeight(height);
						rect.rectangle.setFill(Color.TRANSPARENT);
						rect.rectangle.setStroke(Color.BLACK);
	
					}
					else{
						double maxLength = Math.max(width, height);
						rect.rectangle.setX(centerx-maxLength/2);
						rect.rectangle.setY(centery-maxLength/2);
						rect.rectangle.setWidth(maxLength);
						rect.rectangle.setHeight(maxLength);
						rect.rectangle.setFill(Color.TRANSPARENT);
						rect.rectangle.setStroke(Color.BLACK);
						}
								}
			}
		});
		return rect;
	}
}
