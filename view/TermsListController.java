package view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import model.Term;

public class TermsListController {
	@FXML
	private TableView<Term> termTable;
	@FXML
	private TableColumn<Term, String> termColumn;
	@FXML
	private TableColumn<Term, String> definitionColumn;

	private Main main;

	@FXML
	private void initialize() {
		termColumn.setCellValueFactory(cellData -> cellData.getValue().getWord());
		definitionColumn.setCellValueFactory(cellData -> cellData.getValue().getDefinition());
	}
	public void setMainApp(Main mainApp) {
		this.main = mainApp;

		// Add observable list data to the table
		termTable.setItems(mainApp.getTermsData());
	}
	@FXML
	private void handleDelete() {
		int selectedIndex = termTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			termTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No term Selected");
			alert.setContentText("Please select a term in the table.");
			alert.showAndWait();
		}
	}
	@FXML
	private void handleEdit() {
		Term selectedTerm = termTable.getSelectionModel().getSelectedItem();
		if (selectedTerm != null) {
			boolean okClicked = main.showTermsEditDialog(selectedTerm);
			if (okClicked) {
				termTable.setItems(null);
				termTable.layout();
				termTable.setItems(main.getTermsData());
			}
			
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Term Selected");
			alert.setContentText("Please select a term in the table.");

			alert.showAndWait();
		}
	}
	@FXML
	private void handleNew() {
		Term t = new Term();
//		main.getTermsData().add(t);
		boolean okClicked = main.showTermsEditDialog(t);
		if (okClicked) {
			main.getTermsData().add(t);
		}
	}
	@FXML
	private void handleProceed() {
		if(main.getTermsData().size() <= 1) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("Invalid Terms");
			alert.setHeaderText("Invalid terms list.");
			alert.setContentText("We know you can edit this later, but can you please enter at least two terms? Much appreciated.");
			alert.showAndWait();
		}
		else {
			main.showQuizMenu();
		}
	}
}
