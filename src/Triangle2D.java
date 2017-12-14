package eg.edu.alexu.csd.paint2D;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
 
public class Triangle2D implements Shapes {
    // private double secondPointX, SecondPointY;
    private Polygon poly;
    private Pane root;
    private boolean isDone;
    private boolean isStarted;
    private int corners;
    private double startPointX, startPointY;
    private static final int FirstPoint = 0,SecondPoint = 1,ThirdPoint = 2,
            FourthPoint = 3,FifthPoint = 4,LastPoint=5;
    public void setRoot(Pane root) {
        this.root = root;
    }
 
    public Triangle2D() {
        poly = new Polygon();
        isDone = false;
        isStarted = false;
        corners = 0;
    }
 
    public Triangle2D(Pane root) {
        this.root = root;
    }
 
    @Override
    public Shapes draw() {
        Triangle2D tri = new Triangle2D();
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (tri.corners == 0) {
                    root.getChildren().add(tri.poly);
                    tri.isStarted = true;
                    for (int i = 0; i < FourthPoint; i++) {
                        tri.poly.getPoints().addAll(event.getX(),
                                event.getY());
                    }
                    tri.corners++;
                } else if (tri.corners == 1) {
                    tri.poly.getPoints().set(ThirdPoint, event.getX());
                    tri.poly.getPoints().set(FourthPoint, event.getY());
                    tri.corners++;
                } else if (tri.corners == 2) {
                    tri.poly.getPoints().set(FifthPoint, event.getX());
                    tri.poly.getPoints().set(5, event.getY());
                    tri.corners++;
                }
            }
        });
        root.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (tri.corners == 1) {
                    tri.poly.getPoints().set(ThirdPoint, event.getX());
                    tri.poly.getPoints().set(FourthPoint, event.getY());
                } else if (tri.corners == ThirdPoint) {
                    tri.poly.getPoints().set(FifthPoint, event.getX());
                    tri.poly.getPoints().set(LastPoint, event.getY());
                    tri.poly.setFill(Color.TRANSPARENT);
                    tri.poly.setStroke(Color.BLACK);
                }
            }
        });
        return tri;
    }
 
 
}