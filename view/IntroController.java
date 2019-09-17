package view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.Tsum;

public class IntroController {
	@FXML
	private Pane tsumPane;
	@FXML 
	private Button ready;	
	private Tsum tsum;
	private Main main;
	
	@FXML
	private void initialize() {
		createTsum();
	}
	public void setMainApp(Main main) {
		this.main = main;
	}
	public void createTsum() {
		tsum = new Tsum();
		tsumPane.getChildren().add(tsum);
	}
	@FXML
	private void handleReady() {
		main.showTsumDialog();
	}
}
