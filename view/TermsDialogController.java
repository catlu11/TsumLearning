package view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Term;

public class TermsDialogController {
	@FXML
	private TextField termField;
	@FXML
	private TextArea definitionField;
	
	private Term term;
	private Stage dialogStage;
	private boolean okClicked = false;
	private Main main;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public void setMainApp(Main main) {
		this.main = main;
	}
	public void setFields(Term t) {
		term = t;
		if(t.getWord() != null || t.getDefinition() != null) {
			termField.setText(t.getWord().getValue());
			definitionField.setText(t.getDefinition().getValue());
		}
	}
	@FXML
	private void handleOk() {
		if(isValidInput()) {
			term.setTerm(termField.getText(), definitionField.getText());
			okClicked = true;
			dialogStage.close();
		}
	}
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	private boolean isValidInput() {
		String errors = "";
		if(termField.getText() == null || termField.getText().length() == 0) {
			errors += "Term";
		}
		if(definitionField.getText() == null || definitionField.getText().length() == 0) {
			errors += "\nDefinition";
		}
		if(errors.length() == 0) {
			return true;
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errors);

			alert.showAndWait();
			
			return false;
		}
	}
	public boolean isOkClicked() {
		return okClicked;
	}
}
