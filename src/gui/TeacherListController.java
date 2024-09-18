package gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Teacher;

public class TeacherListController implements Initializable {

	@FXML
	private TableView<Teacher> tableViewTeacher;
	
	@FXML
	private TableColumn<Teacher, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Teacher, String> tableColumnName;
	
	@FXML
	private TableColumn<Teacher, String> tableColumnCpf;
	
	@FXML
	private TableColumn<Teacher, String> tableColumnPhone;
	
	
	@FXML
	private TableColumn<Teacher, Date> tableColumnAdmisionDate;
	
	@FXML
	private TableColumn<Teacher, Double> tableColumnSalary;
	
	@FXML
	private TableColumn<Teacher, String> tableColumnChief;
	
	@FXML
	private TableColumn<Teacher, String> tableColumnCoordinator;
	
	@FXML
	private Button btNew;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private  void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("admissi1onDate"));
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("salary"));
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("chief"));
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("coordinator"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewTeacher.prefHeightProperty().bind(stage.heightProperty());
	}
	
}
