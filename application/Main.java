package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Term;
import model.Tsum;
import model.Tsum.Mood;
import view.IntroController;
import view.QuizController;
import view.TermsDialogController;
import view.TermsListController;
import view.TsumDialogController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	private AnchorPane rootLayout;
	private Stage primaryStage;
	private Color tsumColor;
	private String tsumName;
	private Mood tsumMood;
	private int tsumHealth;
	private ObservableList<Term> termsData = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Please Don't Kill Me");
		showWelcome();
	}
	public ObservableList<Term> getTermsData() {
		return termsData;
	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public void showWelcome() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/WelcomeScreen.fxml"));
			AnchorPane welcomeScreen = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout = welcomeScreen;
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Give the controller access to the main app.
			IntroController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showTsumDialog() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/TsumDialogScreen.fxml"));
			AnchorPane tsumScreen = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout = tsumScreen;
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Give the controller access to the main app.
			TsumDialogController controller = loader.getController();
			controller.setMainApp(this);
			controller.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showTermsList() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/TermsList.fxml"));
			AnchorPane termsScreen = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout = termsScreen;
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Give the controller access to the main app.
			TermsListController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean showTermsEditDialog(Term t) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("/view/TermEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Term");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the term into the controller.
	        TermsDialogController controller = loader.getController();
	        controller.setMainApp(this);
	        controller.setDialogStage(dialogStage);
	        controller.setFields(t);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();
	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public void showQuizMenu() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/QuizMenu.fxml"));
			AnchorPane menuScreen = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout = menuScreen;
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Give the controller access to the main app.
			QuizController controller = loader.getController();
			controller.setMainApp(this);
			controller.setTsumProperties(tsumColor, tsumName, tsumMood, tsumHealth);
			controller.addTsum();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showQuizlet() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/QuizletScreen.fxml"));
			AnchorPane quizScreen = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout = quizScreen;
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Give the controller access to the main app.
			QuizController controller = loader.getController();
			controller.setMainApp(this);
			controller.setTsumProperties(tsumColor, tsumName, tsumMood, tsumHealth);
			controller.initQuiz();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setTsum(String name, Color color) {
		tsumName = name;
		tsumColor = color;
	}
	public void setTsumMood(Mood mood) {
		tsumMood = mood;
	}
	public void setTsumHealth(int health) {
		tsumHealth = health;
	}
	public Stage getStage() {
		return primaryStage;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
