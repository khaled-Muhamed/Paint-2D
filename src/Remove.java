package eg.edu.alexu.csd.paint2D;

import java.util.Stack;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Remove {

	
	
	public void RemoveObject(Node node, Pane root, Stack<Undo> undoStack) {
		

		node.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
					int index ;
					for( index = 0 ; index < root.getChildren().size(); index++)
					  {
						  if(node.getLayoutX() == root.getChildren().get(index).getLayoutX())
						  {break;}
					  }  				
					Undo x = new Undo(node,"Remove",index,node.getLayoutX(),node.getLayoutY());
					undoStack.add(x);
					root.getChildren().remove(node);
					
				
			}
		});
	}
}
