package eg.edu.alexu.csd.paint2D;

import java.util.ArrayList;
import java.util.Stack;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
/**
 *
 * @author khaled
 */
public class GUI extends Application {
	Line line;
	double oldLayoutX ;
	double oldLayoutY ;
	int counter = 0;
	Color color ;
	int moveCounter = 0;
	Stack<Undo> undoStack;
	Stack<Undo> redoStack;
	boolean check=false;
	FileChooser fileChooser=new FileChooser();
    ClassLoading ob ;
    ArrayList<Properties> arrWrite;
    ArrayList<Properties> arrRead;
	int undoRedoCounter = 1;
	ArrayList <ArrayList<Properties>> undoRedo ;
	ArrayList <Properties> undoRedoCell ;
	LoopGetChildren save = new LoopGetChildren();

	double px, py;

	String type;

	Pane BackgroundPane = new Pane();

	Pane root = new Pane();

	Polygon polygon;

	@Override
	public void start(Stage primaryStage) {
		undoRedo = new ArrayList<ArrayList<Properties>>();
		undoRedoCell = new ArrayList<Properties>();
		undoStack = new Stack<Undo>();
		fileChooser.setInitialFileName("Untitled");
		redoStack = new Stack<Undo>();	
		arrWrite = new ArrayList<>();

		root.setPrefHeight(600);
		root.setMaxHeight(700);
		root.setPrefWidth(700);
		root.setMaxWidth(700);
		root.relocate(0,60);

		root.setStyle("-fx-background-color:#ffffff;");
		
		root.setClip(new Rectangle(0,0,610,600));

		Scene scene = new Scene(BackgroundPane, 600, 600);

		line = new Line();

		Circle2D temp = new Circle2D(root);
		LineSegment lineSegmant =new LineSegment(root);
		Eillpse2D ellipse =new Eillpse2D(root);
		Rectangle2D rectangle2d= new Rectangle2D(root, "Rectangle");
		Rectangle2D sqr =new Rectangle2D(root,"Square");
		Triangle2D triangle = new Triangle2D(root);
		polygon = new Polygon();
		
		ColorPicker cp = new ColorPicker(Color.BLACK);
        cp.relocate(450, 520);
        cp.valueProperty().addListener(new ChangeListener<Color>() {
            @Override
            public void changed(
                    ObservableValue<? extends Color> observable,
                    Color oldValue, Color newValue) {
   
            	color =newValue ;   
            	}
        });
		MenuBar menuBar = new MenuBar();
        menuBar.setStyle(" -fx-background-color: #007fa8;-Fx-base:#00FF00; -fx-font: 20 arial;");
        menuBar.setPadding(new Insets(8));
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        fileMenu.setStyle(" -fx-background-color: #007fa8;");
        editMenu.setStyle(" -fx-background-color: #007fa8;");
        MenuItem undo = new MenuItem("Undo");
        MenuItem redo = new MenuItem("Redo");
        MenuItem remove = new MenuItem("Remove");
        MenuItem clear = new MenuItem("Clear");
        MenuItem resize = new MenuItem("Resize");
        MenuItem drag = new MenuItem("Drag");
        MenuItem colorr = new MenuItem("Color");
        MenuItem classLoading = new MenuItem("ClassLoading");
        clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
		public void handle(ActionEvent arg0) {
	
		for(int i = root.getChildren().size() -1 ; i >-1; i--)
		{	
			root.getChildren().remove(root.getChildren().get(i));		
			undoStack.clear();
			redoStack.clear();
		
		}
			}
			});

  		redo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {				
				try{
					if((redoStack.size() != 0) && (redoStack.get(redoStack.size()
				 - 1).getIndicator().equals("Moved")))
				 {
				 for (int i = 0 ; i < root.getChildren().size() ; i++) {
				
				 if (i == redoStack.get(redoStack.size() - 1).getIndex())
				 {
				 Undo x = new
				 Undo(root.getChildren().get(i),"Moved",i,root.getChildren().get(i).getLayoutX(),root.getChildren().get(i).getLayoutY());
				 undoStack.add(x);
				 root.getChildren().get(i).setLayoutX(redoStack.get(redoStack.size()-1).getOldLayoutX());
				 root.getChildren().get(i).setLayoutY(redoStack.pop().getOldLayoutY());
				 break;
				 }
				 }
				 } else if ((redoStack.size() != 0) &&
				 (redoStack.get(redoStack.size() -
				 1).getIndicator().equals("natural")))
				 {
				 root.getChildren().add(redoStack.pop().getObject());
				 } else if ((redoStack.size() != 0) &&
				 (redoStack.get(redoStack.size() -
				 1).getIndicator().equals("Remove")))
				 {
				 for (int i = 0 ; i < root.getChildren().size() ; i++)
				 {
				 if (root.getChildren().get(i)==
				 redoStack.get(redoStack.size()-1).getObject())
				 {
				 undoStack.add(redoStack.pop());
				 root.getChildren().remove(i);
				 break;
				 }
				
				 }
							}
				}catch(Exception e){
					new Alert(Alert.AlertType.ERROR, "invalid no more Undo",ButtonType.OK).show();                     
					
				}		
				}
		});

		undo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
		try{
			 if ((undoStack.size() != 0) && (( undoStack.get(undoStack.size() - 1)).getIndicator().equals("Moved"))) 
				{
					
					for (int i = 0 ; i < root.getChildren().size() ; i++) {
						if (i == undoStack.get(undoStack.size() - 1).getIndex()) 
						{
						Undo x= new Undo(root.getChildren().get(i),"Moved",undoStack.get(undoStack.size() - 1).getIndex(), root.getChildren().get(i).getLayoutX(),root.getChildren().get(i).getLayoutY());				
						redoStack.add(x);
						root.getChildren().get(i).setLayoutX(undoStack.get(undoStack.size()-1).getOldLayoutX());	
						root.getChildren().get(i).setLayoutY(undoStack.pop().getOldLayoutY());
						break;
						}
					}

				} else if((undoStack.size() != 0) && (undoStack.get(undoStack.size() - 1).getIndicator().equals("natural")))
				{
					root.getChildren().add(undoStack.pop().getObject());
			
				} else if((undoStack.size() != 0) && (undoStack.get(undoStack.size() - 1).getIndicator().equals("Remove")))
				{
					redoStack.add(undoStack.get(undoStack.size() - 1));
					root.getChildren().add(undoStack.pop().getObject());
			
				}
				 else if((undoStack.size() != 0) && (undoStack.get(undoStack.size() - 1).getIndicator().equals("Color")))
					
				 {
					
					 for(int i =0 ; i< root.getChildren().size();i++)
					 {
						 if (root.getChildren().get(i) == undoStack.get(undoStack.size() - 1).getObject())
						 { 	
							 Undo x = new Undo(root.getChildren().get(i),"Color",i,0,0);
							 redoStack.add(x);
							 root.getChildren().get(i).setStyle(undoStack.pop().getObject().getStyle());
							 break;
						 }
					 }
					 
				
					}

				else {
					Undo undoObject = new Undo(root.getChildren().get(root.getChildren().size() - 1),"natural", root.getChildren().size() - 1, 0, 0);
					
					redoStack.add(undoObject);

					root.getChildren().remove(root.getChildren().size() - 1);
				}
				}	
		catch( Exception e)
			{
			new Alert(Alert.AlertType.ERROR, "invalid no more Undo",ButtonType.OK).show();  
			}	
		}	
		});
		remove.setOnAction(new EventHandler<ActionEvent>() {
			Remove x = new Remove();
			@Override
			public void handle(ActionEvent arg0) {
				for (int i = 0; i < root.getChildren().size(); i++) {		
					x.RemoveObject(root.getChildren().get(i), root, undoStack);
				}
			}

		});
			drag.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Move x = new Move();
				for (int i = 0; i < root.getChildren().size(); i++) {
					 {
						x.MakeDraggable(root.getChildren().get(i), root,undoStack);
					}
				}
			}
		});
			resize.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Resize x = new Resize();
					
					for(int i =0 ; i<root.getChildren().size();i++)
						x.makeResizable(root.getChildren().get(i),root,undoStack);

				}
			});
			classLoading.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	check=  true;
	                ob =new ClassLoading(fileChooser.showOpenDialog(primaryStage).getPath());
	           
	            }
	        });

			colorr.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					ColorObject x = new ColorObject();
					for (int i = 0; i < root.getChildren().size(); i++) {
						 
							x.setColor(root.getChildren().get(i), root,undoStack);
					}
				}
			});

        editMenu.getItems().addAll(undo,redo,remove,clear,resize,drag,classLoading,colorr);
        Menu shapes = new Menu("Shapes");
        MenuItem circle = new MenuItem("Circle");
        MenuItem rect = new MenuItem("Rectangle");
        MenuItem sqare = new MenuItem("Sqaure");
        MenuItem linee = new MenuItem("Line");
        MenuItem tri = new MenuItem("Triangle");
        MenuItem ell = new MenuItem("Ellipse");
      
        linee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				lineSegmant.draw(); 

			}
		});
        
        circle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			//	temp.draw(); 
				if(check == true)
					ob.getObject(root).draw();
				else{
			new Alert(Alert.AlertType.ERROR, "You have to load class circle first",
				      ButtonType.OK).show();
				}
				
			}
		});
        ell.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ellipse.draw(); 
			}
		});
		rect.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				rectangle2d.draw(); 
			}
		});
		sqare.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sqr.draw(); 
			}
		});
		tri.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				triangle.draw(); 
			}
		});
        
        shapes.getItems().addAll(circle,rect,sqare,linee,tri,ell);

        Menu saveMenuItem = new Menu("Save");
        saveMenuItem.setStyle(" -fx-fill: #00FF00;");
 
       
        MenuItem savexml = new MenuItem("XML");
        MenuItem savejs = new MenuItem("JS");
        savexml.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  LoopGetChildren x=new LoopGetChildren();
                     x.check(root, arrWrite);
                    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML File","*.xml"));    
                    XStream stream=new XStream(new StaxDriver());
                    try {
                        File selectedFile = fileChooser.showSaveDialog(primaryStage);
                        ObjectOutputStream objectOuputStream = stream.createObjectOutputStream(new FileOutputStream(selectedFile.getPath()));
                        objectOuputStream.writeObject(arrWrite);
                        objectOuputStream.close();
                    } catch (Exception e) {
                    	new Alert(Alert.AlertType.ERROR, "ERROR",
                                ButtonType.OK).show(); 
 
                    }              
               
            }
        });
        savejs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoopGetChildren x=new LoopGetChildren();
                x.check(root, arrWrite);
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JS File","*.js"));    
                 try {
                        File selectedFile = fileChooser.showSaveDialog(primaryStage);
                        XStream stream = new XStream(new JettisonMappedXmlDriver());
                        ObjectOutputStream objectOutputStream = stream.createObjectOutputStream(new FileOutputStream(selectedFile));
                        objectOutputStream.writeObject(arrWrite);
                        objectOutputStream.close();
                    } catch (Exception e) {
                    	new Alert(Alert.AlertType.ERROR, "ERROR",
                                ButtonType.OK).show(); 
 
                    }          
               
            }
        });
        saveMenuItem.getItems().addAll(savexml,savejs);
          fileMenu.getItems().add(saveMenuItem);
            Menu loadMenuItem = new Menu("Load");
            MenuItem loadxml = new MenuItem("XML");
            MenuItem loadjs = new MenuItem("JS");
            loadxml.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	undoStack.clear();
                	redoStack.clear();
            		for(int i = root.getChildren().size() -1 ; i >-1; i--)
            		{	
            			root.getChildren().remove(root.getChildren().get(i));			
            		}
                	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML File","*.xml"));    
                    try {
                        XStream stream = new XStream(new StaxDriver());    
                        File selectedFile = fileChooser.showOpenDialog(primaryStage);
                        ObjectInputStream objectInputStream = stream.createObjectInputStream(new FileInputStream(selectedFile));                   
                        arrRead = (ArrayList<Properties>) objectInputStream.readObject();
                        DrawArrayLoad d =new DrawArrayLoad();
                        d.drawArray(root, arrRead);
                        objectInputStream.close();
                    } catch (Exception e) {
                    	new Alert(Alert.AlertType.ERROR, "ERROR",
                                ButtonType.OK).show();                     }          
                }
            });
            loadjs.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	undoStack.clear();
                	redoStack.clear();
            		for(int i = root.getChildren().size() -1 ; i >-1; i--)
            		{	
            			root.getChildren().remove(root.getChildren().get(i));		
            		
            		}
                	
                    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JS File","*.js"));    
                    try {
                        XStream stream=new XStream(new JettisonMappedXmlDriver());
                        File selectedFile = fileChooser.showOpenDialog(primaryStage);
                        ObjectInputStream objectInputStream = stream.createObjectInputStream(new FileInputStream(selectedFile));                   
                        arrRead = (ArrayList<Properties>) objectInputStream.readObject();
                        DrawArrayLoad d =new DrawArrayLoad();
                          d.drawArray(root, arrRead);
                        objectInputStream.close();
                    } catch (Exception e) {
                    	new Alert(Alert.AlertType.ERROR, "ERROR",
                                ButtonType.OK).show();                     }
                   
                }
            });
            loadMenuItem.getItems().addAll(loadxml,loadjs);
              fileMenu.getItems().add(loadMenuItem);
             MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().addAll(new SeparatorMenuItem(), exitMenuItem);
        menuBar.getMenus().addAll(fileMenu,editMenu,shapes);


		BackgroundPane.getChildren().addAll(menuBar,root);

		primaryStage.setResizable(false);

		primaryStage.setTitle("Paint2D");

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}


//	private void ColorObject(Node node) {
//		node.setOnMousePressed(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//
//				if (type == "Color") {
//
//				}
//
//			}
//		});
//	}



}
