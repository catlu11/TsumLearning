package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import model.Tsum.Mood;

public class HealthBar extends Canvas {
	int width = 213;
	int height = 40;
	private GraphicsContext gc;
	private int health;
	
	public HealthBar(int health) {
		super(213,40);
		gc = this.getGraphicsContext2D();
		this.health = health;
		drawHealthBar();
	}
	public void drawHealthBar() {
		gc.clearRect(0, 0, width, height);
		gc.setFill(Color.LAWNGREEN);
		for(int i = 1; i <= health; i++) {
			if(i == 1) {
				gc.fillArc(0, 0, width / 4, height, 90, 180, ArcType.ROUND);
			}
			else if(i == 8) {
				gc.fillArc(width - (width / 4) - 6, 0, width / 4, height, 270, 180, ArcType.ROUND);
			}
			else {
				gc.fillRect((i - 1) * (width / 8) - 1, 0, width / 8, height);
			}
		}
	}
	public void updateHealthBar(boolean isCorrect) {
		if(!isCorrect) {
			health--;
			drawHealthBar();
		}
	}
	public int getHealth() {
		return health;
	}
	public Mood getMood() {
		if(health >= 6) 
			return Mood.HAPPY;
		else if(health >= 4)
			return Mood.SAD;
		else if(health > 0)
			return Mood.CRYING;
		else
			return Mood.DEAD;
	}
}
