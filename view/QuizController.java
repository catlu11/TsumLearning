package view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.HealthBar;
import model.Term;
import model.Tsum;
import model.Tsum.Mood;

public class QuizController {
	@FXML
	private Pane tsumPane;
	@FXML
	private Pane tsumPane2;
	@FXML
	private Pane healthPane;
	@FXML
	private Text definition;
	@FXML
	private TextField answer;
	@FXML
	private Text ouchie;
	@FXML
	private Button submit;
	@FXML
	private Button giveUp;
	@FXML 
	private Button exit;
	@FXML
	private Button monstrosity;
	@FXML
	private Text warning;
	@FXML
	private Text nameLabel;
	
	private Main main;
	private HealthBar hb;
	private int currentTermIndex;
	private int previousTermIndex;
	private Color tsumColor;
	private String tsumName;
	private Mood tsumMood;
	private int tsumHealth;
	
	public void setMainApp(Main main) {
		this.main = main;
	}
	public void setTsumProperties(Color color, String name, Mood mood, int health) {
		tsumColor = color;
		tsumName = name;
		tsumMood = mood;
		tsumHealth = health;
	}
	public void addTsum() {
		Tsum t = new Tsum(tsumColor, tsumName, tsumMood);
		tsumPane.getChildren().add(t);
	}
	public void initSideBar() {
		Tsum t = new Tsum(tsumColor, tsumName, tsumMood);
		tsumPane2.getChildren().add(t);
		System.out.println(tsumHealth);
		hb = new HealthBar(tsumHealth);
		healthPane.getChildren().add(hb);
		ouchie.setVisible(false);
		monstrosity.setVisible(false);
		warning.setText("The more answers you get wrong, the more you hurt "
				+ tsumName + ". " + "Don't let " + tsumName +  " die!");
		nameLabel.setText(tsumName + " the Tsum");
	}
	// Menu handlers
	@FXML
	private void handleStudy() {
		main.showQuizlet();
	}
	@FXML
	private void handleEditTerms() {
		main.showTermsList();
	}
	@FXML
	private void handleQuit() {
		main.getPrimaryStage().close();
	}
	public void initQuiz() {
		getRandomTerm();
		definition.setText(main.getTermsData().get(currentTermIndex).getDefinition().getValue());
		answer.clear();
		ouchie.setVisible(false);
		initSideBar();
	}
	// Quiz handlers
	@FXML
	private void handleSubmit() {
		ouchie.setVisible(true);
		boolean isCorrect = isAnswerCorrect();
		System.out.println(isCorrect);
		hb.updateHealthBar(isCorrect);
		if(isCorrect) {
			System.out.println(tsumHealth);
			previousTermIndex = currentTermIndex;
			getRandomTerm();
			definition.setText(main.getTermsData().get(currentTermIndex).getDefinition().getValue());
			answer.clear();
			ouchie.setFill(Color.GREEN);
			if(tsumHealth >= 6) {
				int rng = (int) (Math.random() * 3 + 1);
				switch(rng) {
				case 1: ouchie.setText("Yay!");
				break;
				case 2: ouchie.setText("Good job!");
				break;
				case 3: ouchie.setText("I'm proud of you!");
				}
				
			}
			else if(tsumHealth >= 4) {
				int rng = (int) (Math.random() * 3 + 1);
				switch(rng) {
				case 1: ouchie.setText("Thank goodness.");
				break;
				case 2: ouchie.setText("You got that one right?");
				break;
				case 3: ouchie.setText("That one looked hard!");
				}
			}
			else {
				ouchie.setFill(Color.GREEN);
				ouchie.setText("...");
			}
		}
		else {
			ouchie.setFill(Color.RED);
			tsumHealth--;
			if(tsumHealth == 1) {
				ouchie.setText("I've seen things you people wouldn't \n"
						+ "believe. Attack ships on fire off the shoulder \n"
						+ "of Orion. I watched C-Beams glitter in the \n"
						+ "dark near the Tannhauser Gate. All those \n"
						+ "moments will be lost in time, like tears \n"
						+ "in rain. Time to die.");
			}
			else if(tsumHealth >= 6) {
				ouchie.setText("Owie.");
			}
			else if(tsumHealth >= 4) {
				ouchie.setText("Why are you doing this?");
			}
			else {
				ouchie.setText("Plz don't hurt me.");
			}
		}
		updateScreen();
	}
	@FXML
	private void handleGiveUp() {
		answer.setText(main.getTermsData().get(currentTermIndex).getWord().getValue());
		hb.updateHealthBar(false);
		ouchie.setFill(Color.RED);
		ouchie.setText(":(");
		updateScreen();
	}
	@FXML
	private void handleExit() {
		main.setTsumHealth(tsumHealth);
		main.setTsumMood(tsumMood);
		main.showQuizMenu();
	}
	@FXML
	private void handleMonstrosity() {
		main.getPrimaryStage().close();
	}
	private void updateScreen() {
		tsumMood = hb.getMood();
		if(tsumMood == Mood.DEAD) {
			showDeathScreen();
		}
		updateTsumStatus();
	}
	private void updateTsumStatus() {
		tsumPane2.getChildren().set(0, new Tsum(tsumColor, tsumName, tsumMood));
	}
	private boolean isAnswerCorrect() {
		if(answer.getText().equals(main.getTermsData().get(currentTermIndex).getWord().getValue())) {
			return true;
		}
		return false;
	}
	private void getRandomTerm() {
		int rng = (int) (Math.random() * main.getTermsData().size());
		while(rng == previousTermIndex) {
			rng = (int) (Math.random() * main.getTermsData().size());
		}
		currentTermIndex = rng;
	}
	private void showDeathScreen() {
		ouchie.setVisible(false);
		definition.setText("You monster.");
		monstrosity.setVisible(true);
		warning.setText("You had one job.");
		submit.setDisable(true);
		giveUp.setDisable(true);
		exit.setDisable(true);
	}
}
