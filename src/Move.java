package eg.edu.alexu.csd.paint2D;

import java.util.Stack;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class Move {

	double oldLayoutX ,oldLayoutY;
	public static class DragDelta {
		double x, y;
		
	};

	GUI temp = new GUI();
	public void MakeDraggable(Node node,Pane root,Stack undoStack) {

		final DragDelta dragDelta = new DragDelta();

		node.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				dragDelta.x = node.getLayoutX() - event.getScreenX();
				dragDelta.y = node.getLayoutY() - event.getScreenY();
				oldLayoutX = node.getLayoutX();
				oldLayoutY = node.getLayoutY();
				node.setCursor(Cursor.MOVE);
				
				
			}
				});
		node.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				int index ;
				for( index = 0 ; index < root.getChildren().size(); index++)
				  {
					  if(node.getLayoutX() == root.getChildren().get(index).getLayoutX())
					  {break;}
				  }  
				    
				 final Undo movedObject = new Undo (node,"Moved",index ,oldLayoutX,oldLayoutY);
				  
				 undoStack.add(movedObject);
				 
				 node.setStyle("-fx-stroke: #000000;-fx-stroke-width: 1;");
				 
				 node.setCursor(Cursor.DEFAULT);
			}		
		});
		node.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
					node.setLayoutX(mouseEvent.getScreenX() + dragDelta.x);
					node.setLayoutY(mouseEvent.getScreenY() + dragDelta.y);
					node.setStyle("-fx-stroke-width: 4; -fx-stroke: #000000;"+"-fx-stroke-dash-array: 2 12 12 2; ");

			}

		});

	}
}
