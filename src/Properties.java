package eg.edu.alexu.csd.paint2D;

import javafx.scene.paint.Paint;
public class Properties {
	private String Type ;
	private double oldX , oldY , currX ,currY ; // my line
	private double corrnerX , corrnery , width , height ; //rect , sqr ,ellipse
	private double centerX , centerY ,radius; // circle
	private double px1, py1 ,px2 , py2 ,px3 ,py3 ; //poly
	private Paint color;
	private Paint colorStrock;
	public Paint getColorStrock() {
		return colorStrock;
	}
	public void setColorStrock(Paint colorStrock) {
		this.colorStrock = colorStrock;
	}
	public Paint getColor() {
		return color;
	}
	public void setColor(Paint color) {
		this.color = color;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public double getOldX() {
		return oldX;
	}
	public void setOldX(double oldX) {
		this.oldX = oldX;
	}
	public double getOldY() {
		return oldY;
	}
	public void setOldY(double oldY) {
		this.oldY = oldY;
	}
	public double getCurrX() {
		return currX;
	}
	public void setCurrX(double currX) {
		this.currX = currX;
	}
	public double getCurrY() {
		return currY;
	}
	public void setCurrY(double currY) {
		this.currY = currY;
	}
	public double getCorrnerX() {
		return corrnerX;
	}
	public void setCorrnerX(double corrnerX) {
		this.corrnerX = corrnerX;
	}
	public double getCorrnery() {
		return corrnery;
	}
	public void setCorrnery(double corrnery) {
		this.corrnery = corrnery;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getCenterX() {
		return centerX;
	}
	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}
	public double getCenterY() {
		return centerY;
	}
	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getPx1() {
		return px1;
	}
	public void setPx1(double px1) {
		this.px1 = px1;
	}
	public double getPy1() {
		return py1;
	}
	public void setPy1(double py1) {
		this.py1 = py1;
	}
	public double getPx2() {
		return px2;
	}
	public void setPx2(double px2) {
		this.px2 = px2;
	}
	public double getPy2() {
		return py2;
	}
	public void setPy2(double py2) {
		this.py2 = py2;
	}
	public double getPx3() {
		return px3;
	}
	public void setPx3(double px3) {
		this.px3 = px3;
	}
	public double getPy3() {
		return py3;
	}
	public void setPy3(double py3) {
		this.py3 = py3;
	}
}