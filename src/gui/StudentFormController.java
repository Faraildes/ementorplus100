package gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Student;
import model.entities.Turma;
import model.exceptions.ValidationException;
import model.services.StudentService;
import model.services.TurmaService;

public class StudentFormController implements Initializable {

	private Student entity;

	private StudentService service;

	private TurmaService turmaService;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtName;

	@FXML
	private DatePicker dpBirthDate;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtPhone;

	@FXML
	private TextField txtPeriod;

	@FXML
	private ComboBox<Turma> comboBoxTurma;

	@FXML
	private Label labelErrorName;

	@FXML
	private Label labelErrorBirthDate;

	@FXML
	private Label labelErrorCpf;

	@FXML
	private Label labelErrorPhone;

	@FXML
	private Label labelErrorPeriod;

	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;

	private ObservableList<Turma> obsList;

	public void setStudent(Student entity) {
		this.entity = entity;
	}

	public void setServices(StudentService service, TurmaService turmaService) {
		this.service = service;
		this.turmaService = turmaService;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		} catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		} catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private Student getFormData() {
		Student obj = new Student();

		ValidationException exception = new ValidationException("Validation error");

		obj.setId(Utils.tryParseToInt(txtId.getText()));

		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
			exception.addError("name", "Field can't be empty");
		}
		obj.setName(txtName.getText());

		if (exception.getErrors().size() > 0) {
			throw exception;
		}

		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 60);
		Constraints.setTextFieldMaxLength(txtCpf, 12);
		Constraints.setTextFieldInteger(txtPeriod);
		Constraints.setTextFieldMaxLength(txtPhone, 15);
		Utils.formatDatePicker(dpBirthDate, "dd/MM/yyyy");
		
		initializeComboBoxTurma();
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
		if (entity.getBirthDate() != null)
			dpBirthDate.setValue(LocalDate.ofInstant(entity.getBirthDate().toInstant(), ZoneId.systemDefault()));
		txtCpf.setText(entity.getCpf());
		txtPhone.setText(entity.getPhone());
		txtPeriod.setText(String.format("%d", entity.getPeriod()));
		
		if(entity.getTurma() == null)
			comboBoxTurma.getSelectionModel().selectFirst();
		comboBoxTurma.setValue(entity.getTurma());
	}
	
	

	public void loadAssociatedObjects() {
		if (turmaService == null)
			throw new IllegalStateException("TurmaService was null");
		List<Turma> list = turmaService.findAll();
		obsList = FXCollections.observableArrayList(list);
		comboBoxTurma.setItems(obsList);
	}

	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();

		if (fields.contains("name")) {
			labelErrorName.setText(errors.get("name"));
		}
	}

	private void initializeComboBoxTurma() {
		Callback<ListView<Turma>, ListCell<Turma>> factory = lv -> new ListCell<Turma>() {
			@Override
			protected void updateItem(Turma item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};
		comboBoxTurma.setCellFactory(factory);
		comboBoxTurma.setButtonCell(factory.call(null));
	}
}