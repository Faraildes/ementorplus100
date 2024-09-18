package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TeacherFormContoller implements Initializable{

	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtCpf;
	
	@FXML
	private TextField txtPhone;
	
	@FXML
	private DatePicker dpAdmissionDate;
	
	@FXML
	private TextField txtSalary;
	
	@FXML
	private TextField txtChief;
	
	@FXML
	private TextField txtCoordinator;
	
	
	@FXML 
	private Label labelName;
	
	@FXML 
	private Label labelCpf;
	
	@FXML 
	private Label labelPhone;
	
	@FXML 
	private Label labelAdmissionDate;
	
	@FXML 
	private Label labelSalary;
	
	@FXML 
	private Label labelChief;
	
	@FXML 
	private Label labelCoordinator;
	
	@FXML
	private Button btSabe;
	
	@FXML
	private Button btCancel;
	
	
	@FXML
	public void onBtSaveAction() {
		System.out.println("onBtSaveAction");
	}
	
	@FXML
	public void onBtCancelAction() {
		System.out.println("onBtCancelAction");
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 60);
		Constraints.setTextFieldMaxLength(txtCpf, 12);
		Constraints.setTextFieldDouble(txtSalary);
		Constraints.setTextFieldMaxLength(txtChief, 3);
		Constraints.setTextFieldMaxLength(txtCoordinator, 3);	
	}

}
