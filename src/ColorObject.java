package eg.edu.alexu.csd.paint2D;

import java.util.Stack;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ColorObject {
	
	public void setColor(Node node , Pane root ,Stack undoStack )
	{
		node.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				int index =  0;
				for(int i =0 ;i<root.getChildren().size()-1;i++ )
				{
					if(root.getChildren().get(i)==node)
					{	index = i;
						break;
						
					}
					
				}
				Undo x = new Undo(node,"Color",index,node.getLayoutX(),node.getLayoutY());
				undoStack.add(x);
				node.setStyle("-fx-fill: yellow; ");
			}
		});
		
		node.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
					node.setStyle("-fx-stroke-width: 1; -fx-stroke: #000000;"+"-fx-fill : yellow; ");	
				}
		});

	
	}
}
