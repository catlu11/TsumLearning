package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Tsum extends Canvas {
	public enum Mood {HAPPY, SAD, CRYING, DEAD};
	private Mood currentMood;
	private GraphicsContext gc;
	private Color color;
	private String name;
	
	public Tsum() {
		super(213,225);
		gc = this.getGraphicsContext2D();
		color = Color.WHITE;
		currentMood = Mood.HAPPY;
		drawTsum();
	}
	public Tsum(Color color, String name, Mood mood) {
		super(213,225);
		gc = this.getGraphicsContext2D();
		this.color = color;
		this.name = name;
		currentMood = mood;
		drawTsum();
	}
	// redraws Tsum
	public void drawTsum() {
		gc.clearRect(0, 0,213, 225);
		// shadow
		gc.setFill(Color.DARKGREY);
		gc.fillOval(27, 190, 160, 30);
		// body
		gc.setFill(color);
		gc.fillOval(0,0,213,205);
		// blush
		gc.setFill(Color.web("#fdc0f4"));
		gc.fillOval(32, 90, 30, 14);
		gc.fillOval(156, 90, 30, 14);
		// eyes
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		if(currentMood == Mood.DEAD) {
			gc.setLineWidth(5);
			gc.strokeLine(50, 65, 70, 85);
			gc.strokeLine(70,65,50,85);
			gc.strokeLine(145,65,165,85);
			gc.strokeLine(165,65,145,85);
		}
		else if (currentMood == Mood.CRYING){
			gc.setLineWidth(6);
			gc.strokeLine(50,75,76,75);
			gc.strokeLine(140,75,166,75);
		}
		else {
			gc.fillOval(56, 65, 14, 23);
			gc.fillOval(146, 65, 14, 23);
		}
		drawAccessories();
	}
	private void drawAccessories() {
		if(currentMood != Mood.HAPPY) {
			int x = 140;
			int y = 86;
			gc.setFill(Color.AQUA);
			gc.fillPolygon(new double[]{20 + x,10 + x,30 + x}, new double[]{0 + y,20 + y,20 + y}, 3);
			gc.fillArc(10 + x, 10 + y, 20, 20, 180, 180, ArcType.ROUND);
		}
	}
	// calls drawTsum() after 
	public void setMood(int health) {
		if(health >= 6) 
			currentMood = Mood.HAPPY;
		else if(health >= 4)
			currentMood = Mood.SAD;
		else if(health > 0)
			currentMood = Mood.CRYING;
		else
			currentMood = Mood.DEAD;
		drawTsum();
	}
	
	// @param the selected color from TsumDialogController
	public void setColor(Color color) {
		this.color = color;
		drawTsum();
	}
	public Color getColor() {
		return color;
	}
	public void setName(String name) {
		this.name = name;
	}
}
