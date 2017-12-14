package eg.edu.alexu.csd.paint2D;

import javafx.scene.Node;
import javafx.scene.paint.Paint;

public class Undo {

	private Node object;

	private String indicator ;
	
	private int index ;

	private double oldLayoutX ,oldLayoutY;
	
	public double getOldLayoutX() {
		return oldLayoutX;
	}

	public double getOldLayoutY() {
		return oldLayoutY;
	}
	
	public Undo(Node object, String indicator, int index , double oldLayoutX , double oldLayoutY) {
		this.object = object;
		this.indicator = indicator;
		this.index = index;
		this.oldLayoutX = oldLayoutX;
		this.oldLayoutY = oldLayoutY;
	}
	
	public Node getObject() {
		return object;
	}

	public String getIndicator() {
		return indicator;
	}


	public int getIndex() {
		return index;
	}


}
