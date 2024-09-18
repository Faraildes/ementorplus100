package gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Teacher;

public class TeacherFormController implements Initializable{

	private Teacher entity;
	
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
	
	public void setTeacher(Teacher entity)  {
		this.entity = entity;
	}
	
	
	
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
	
	public void updateFormData() {
		if (entity ==null)
			throw new IllegalStateException("Entity was null!");
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
		txtCpf.setText(entity.getCpf());
		txtPhone.setText(entity.getPhone());
		if(entity.getAdmissionDate() != null)
			dpAdmissionDate.setValue(LocalDate.ofInstant(entity.getAdmissionDate().toInstant(), ZoneId.systemDefault()));
		Locale.setDefault(Locale.US);
		txtSalary.setText(String.valueOf(entity.getSalary()));
		txtChief.setText(entity.getChief());
		txtCoordinator.setText(entity.getCoordinator());
		
	}

}
