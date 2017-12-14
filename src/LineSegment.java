package eg.edu.alexu.csd.paint2D;
import java.util.ArrayList;
import java.util.Stack;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.ArrayList;

public  class LineSegment implements Shapes {
	private Line line;
	private Pane root;
	private boolean isDone;
	private boolean isStarted;
	private int count=0;
	

	public LineSegment() {
		line = new Line();
		isDone = false;
		isStarted = false;
	}

	public LineSegment(Pane root) {
		setRoot(root);
	}

	public void setRoot(Pane root) {
		this.root = root;
	}

	
	public LineSegment draw( ) {
		LineSegment lineSegmant = new LineSegment();
		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (lineSegmant.isStarted == false) {
					root.getChildren().add(lineSegmant.line);
					lineSegmant.isStarted = true;
				} else {
					lineSegmant.isDone = true;
				}
				if (lineSegmant.isDone == false) {
					lineSegmant.line.setStartX(event.getX());
					lineSegmant.line.setStartY(event.getY());
					lineSegmant.line.setEndX(event.getX());
					lineSegmant.line.setEndY(event.getY());
				}
			}
		});
		root.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (!lineSegmant.isDone && lineSegmant.isStarted){
			    lineSegmant.line.setEndX(event.getX());
				lineSegmant.line.setEndY(event.getY());
                                }
			}
		});

		return lineSegmant;
	}
	public void setEndX(double x) {
		line.setEndX(x);
	}
	public void setEndY(double y) {
		line.setEndY(y);
	}


}