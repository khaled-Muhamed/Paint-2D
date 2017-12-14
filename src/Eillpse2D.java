
package eg.edu.alexu.csd.paint2D;


import java.util.ArrayList;
import java.util.Stack;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Eillpse2D implements Shapes {
	private Ellipse ellipse;
	private Pane root;
	private boolean isDone;
	private boolean isStarted;
	private double cornerx;
	private double cornery;
	
	public Eillpse2D()  {
		ellipse = new Ellipse();
		isDone = false;
		isStarted = false;
	}
	public Eillpse2D(Pane root) {
		this.root = root;
	}
	public Eillpse2D draw( ) {
		Eillpse2D ellipse2D = new Eillpse2D();
		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (ellipse2D.isStarted == false) {
					root.getChildren().add(ellipse2D.ellipse);
					LoopGetChildren save = new LoopGetChildren();
					
					ellipse2D.isStarted = true;
				} else {
					ellipse2D.isDone = true;
				}
				if (ellipse2D.isDone == false) {
					cornerx = (event.getX());
					cornery = (event.getY());
				}
			}
		});

		root.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (!ellipse2D.isDone && ellipse2D.isStarted) {
					ellipse2D.ellipse.setCenterX(
							Math.min(cornerx, event.getX()));
					ellipse2D.ellipse.setCenterY(
							Math.min(cornery, event.getY()));
					ellipse2D.ellipse.setRadiusX(
							Math.abs(cornerx - event.getX()));
					ellipse2D.ellipse.setRadiusY(
							Math.abs(cornerx - event.getY()));

					ellipse2D.ellipse.setFill(Color.TRANSPARENT);
					ellipse2D.ellipse.setStroke(Color.BLACK);
					
				}
			}
		});
		return ellipse2D;
	}
}