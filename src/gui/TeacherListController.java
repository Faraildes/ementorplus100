package gui;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Teacher;
import model.services.TeacherService;

public class TeacherListController implements Initializable {

	private TeacherService service;
	
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
	private TableColumn<Teacher, Date> tableColumnAdmissionDate;
	
	@FXML
	private TableColumn<Teacher, Double> tableColumnSalary;
	
	@FXML
	private TableColumn<Teacher, String> tableColumnChief;
	
	@FXML
	private TableColumn<Teacher, String> tableColumnCoordinator;
	
	@FXML
	private Button btNew;
	
	private ObservableList<Teacher> obsList;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}
	
	public void setTeacherService(TeacherService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tableColumnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tableColumnAdmissionDate.setCellValueFactory(new PropertyValueFactory<>("admissionDate"));
		tableColumnSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
		tableColumnChief.setCellValueFactory(new PropertyValueFactory<>("chief"));
		tableColumnCoordinator.setCellValueFactory(new PropertyValueFactory<>("coordinator"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewTeacher.prefHeightProperty().bind(stage.heightProperty());
		tableViewTeacher.prefHeightProperty().bind(stage.widthProperty());
	}
	
	public void updateTableView() {
		if (service == null)
			throw new IllegalStateException("Service was null!");
		List<Teacher> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewTeacher.setItems(obsList);
	}
}
