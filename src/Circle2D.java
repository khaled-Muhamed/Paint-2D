package eg.edu.alexu.csd.paint2D;


import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
//import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/**
 * 
 * @author khaled
 *
 */
public class Circle2D implements Shapes {
/**
 * an object of Circle to draw it.
 */
	private Circle circle;
	/**
	 * an Object of panel to draw on.
	 */
	private Pane root;
	/**
	 * boolean to detect finish.
	 */
	private boolean isDone;
	/**
	 * boolean to detect finish.
	 */
	private boolean isStarted;
/**
 * @return circle.
 */
	public final Circle2D draw() {
		Circle2D  circ = new Circle2D();
		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(final MouseEvent event) {
				if (circ.isStarted == false) {
					 
				
					root.getChildren().add(circ.circle);
					

					circ.isStarted = true;
				} else {
					circ.isDone = true;
				}
				if(circ.isDone == false) {
					circ.circle.setCenterX(event.getX());
					circ.circle.setCenterY(event.getY());
				}	
			}
		});

		root.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent event) {
				if (!circ.isDone && circ.isStarted){
				circ.circle.setRadius(new Point2D(circ.circle.getCenterX() , circ.circle.getCenterY()).distance(event.getX() , event.getY()));
				circ.circle.setFill(Color.TRANSPARENT);
				circ.circle.setStroke(Color.BLACK); 
				}
			}
		});
		return circ;
	}
/**
 * constructor.
 */
	public Circle2D(Pane root)
	{
		this.root=root;
	}
	public Circle2D() {
		circle = new Circle();
		isDone = false;
		isStarted = false;
	}

	/**
	 *@return radius.
	 * getter to get radius of circle.
	 */
	public final double getRadius() {
		return circle.getRadius();
	}
/**
 * set radius of circle.
 *  @param radius.
 */
	public final void setRadius(final double radius) {
		circle.setRadius(radius);
	}
}