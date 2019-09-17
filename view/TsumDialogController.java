package view;

import java.util.ArrayList;

import application.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Tsum;
import model.Tsum.Mood;

public class TsumDialogController {
	@FXML
	private Pane tsumPane;	
	@FXML
	private Pane c1;
	@FXML
	private Pane c2;
	@FXML
	private Pane c3;
	@FXML
	private Pane c4;
	@FXML
	private Pane c5;
	@FXML
	private Pane c6;
	@FXML
	private Pane c7;
	@FXML
	private Pane c8;
	@FXML
	private Pane c9;
	@FXML
	private Pane c10;
	@FXML
	private Pane c11;
	@FXML
	private Pane c12;
	@FXML
	private Pane c13;
	@FXML
	private Pane c14;
	@FXML
	private Pane c15;
	@FXML
	private Pane c16;
	@FXML
	private Button ok;
	@FXML
	private TextField nameField;
	
	private Tsum tsum;
	private Main main;
	private ArrayList<Color> colors = new ArrayList<Color>();
	private ArrayList<Pane> panes = new ArrayList<Pane>();
	
	public void show() {
		createTsum();
		initPanes();
	}
	public void setMainApp(Main main) {
		this.main = main;
	}
	private void createTsum() {
		tsum = new Tsum();
		tsumPane.getChildren().add(tsum);
	}
	private void initPanes() {
		makeArrays();
		for(int i = 0; i < 16; i++) {
			Pane p = panes.get(i);
			p.setOnMouseClicked(new EventHandler<MouseEvent>()  {
				@Override
				public void handle(MouseEvent event) {
					tsum.setColor(colors.get(Integer.parseInt(p.getChildren().get(0).getId())));
					tsum.drawTsum();
				}
			});
			Rectangle r = new Rectangle();
			r.setId(Integer.toString(i));
			r.setWidth(p.getWidth());
			r.setHeight(p.getHeight());
			r.setFill(colors.get(i));
			p.getChildren().add(r);
		}
	}
	private void makeArrays() {
		colors.add(Color.web("#ffff99"));
		colors.add(Color.web("#3958D0"));
		colors.add(Color.web("#7FD3C8"));
		colors.add(Color.web("#fddef7"));
		colors.add(Color.web("#e6e5f4"));
		colors.add(Color.web("#ff3b63"));
		colors.add(Color.web("#ffb62c"));
		colors.add(Color.web("#ffffff"));
		colors.add(Color.web("#f0f8ff"));
		colors.add(Color.web("#fff1f1"));
		colors.add(Color.web("#00fddc"));
		colors.add(Color.web("#818181"));
		colors.add(Color.web("#ab721a"));
		colors.add(Color.web("#dcffc2"));
		colors.add(Color.web("#9d89ce"));
		colors.add(Color.web("#799eda"));		
		panes.add(c1);
		panes.add(c2);
		panes.add(c3);
		panes.add(c4);
		panes.add(c5);
		panes.add(c6);
		panes.add(c7);
		panes.add(c8);
		panes.add(c9);
		panes.add(c10);
		panes.add(c11);
		panes.add(c12);
		panes.add(c13);
		panes.add(c14);
		panes.add(c15);
		panes.add(c16);	
	}
	@FXML
	private void handleOk() {
		if(isNameValid()) {
			main.setTsum(nameField.getText(),tsum.getColor());
			main.setTsumMood(Mood.HAPPY);
			main.setTsumHealth(8);
			main.showTermsList();
		}
	}
	private boolean isNameValid() {
		if(nameField.getText().length() == 0 || nameField.getText() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(main.getStage());
			alert.setTitle("Invalid name.");
			alert.setHeaderText("Facing identity crisis...");
			alert.setContentText("Please name your Tsum!");

			alert.showAndWait();
			
			return false;
		}
		else {
			return true;
		}
	}
}
