package boundary;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import control.Control;
import entity.CruiseOrder;
import entity.Person;
import entity.PersonTableItem;
import entity.Toast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class A_PersonOrderController implements Initializable {
	private ArrayList<PersonTableItem> personarr = new ArrayList<>();
	private ArrayList<CruiseOrder> ordersarr = new ArrayList<>();

	@FXML
	private ComboBox<Integer> ComboShipID;
	@FXML
	private TextField txtShipName;
	@FXML
	private DatePicker txtManDate;
	@FXML
	private TextField txtMaxCap;
	@FXML
	private TextField txtNumPpl;
	@FXML
	private TextField txtShipID;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnReset;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;
	@FXML
	private TableView<PersonTableItem> tblUsers;
	@FXML
	private TableColumn<PersonTableItem, String> tcid;
	@FXML
	private TableColumn<PersonTableItem, String> tcfirst;
	@FXML
	private TableColumn<PersonTableItem, String> tcsur;
	@FXML
	private TableColumn<PersonTableItem, String> tcbirth;
	@FXML
	private TableColumn<PersonTableItem, String> tcphone;
	@FXML
	private TableColumn<PersonTableItem, String> tcemail;
	@FXML
	private TableColumn<PersonTableItem, String> tcpass;
	@FXML
	private TableView<CruiseOrder> tblOrders;
	@FXML
	private TableColumn<CruiseOrder, Integer> cruiseID;
	@FXML
	private TableColumn<CruiseOrder, Integer> cruiseShipID;
	@FXML
	private TableColumn<CruiseOrder, Integer> roomNumber;
	@FXML
	private TableColumn<CruiseOrder, String> personID;

	private ObservableList<PersonTableItem> Persondata = FXCollections.observableArrayList();
	private ObservableList<CruiseOrder> CruiseOrderdata = FXCollections.observableArrayList();
	@FXML
	TextField id;
	@FXML

	TextField name;
	@FXML

	TextField sur;
	@FXML

	DatePicker dob;
	@FXML

	TextField phone;
	@FXML

	TextField email;
	@FXML

	TextField password;
	@FXML

	TextField TFcruiseID;
	@FXML

	TextField TFcruiseShipID;
	@FXML

	TextField TFroomNumber;
	@FXML

	TextField TFpersonID;

	@Override
	public void initialize(URL arg, ResourceBundle arg1) {
		System.out.println("in this ini");
		Date d = new Date(0);

		ArrayList<Person> p = Control.getInstance().selectPerson();

		for (Person person : p) {
			Persondata.add(new PersonTableItem(person.getPersonID(), person.getFirstName(), person.getSurName(),
					person.getDateOfBirth().toString(), person.getPhone(), person.getEmail(), person.getPassword()));
		}

		tcid = new TableColumn<>("tcid");
		tcid.setCellValueFactory(new PropertyValueFactory<>("tcid"));

		tcfirst = new TableColumn<>("tcfirst");
		tcfirst.setCellValueFactory(new PropertyValueFactory<>("tcfirst"));

		tcsur = new TableColumn<>("tcsur");
		tcsur.setCellValueFactory(new PropertyValueFactory<>("tcsur"));

		tcbirth = new TableColumn<>("tcbirth");
		tcbirth.setCellValueFactory(new PropertyValueFactory<>("tcbirth"));

		tcphone = new TableColumn<>("tcphone");
		tcphone.setCellValueFactory(new PropertyValueFactory<>("tcphone"));

		tcemail = new TableColumn<>("tcemail");
		tcemail.setCellValueFactory(new PropertyValueFactory<>("tcemail"));

		tcpass = new TableColumn<>("tcpass");
		tcpass.setCellValueFactory(new PropertyValueFactory<>("tcpass"));

		tblUsers.getColumns().addAll(tcid, tcfirst, tcsur, tcbirth, tcphone, tcemail, tcpass);

		tblUsers.getItems().setAll(Persondata);
		tblUsers.setEditable(true);

		ArrayList<CruiseOrder> co = Control.getInstance().selectCruiseOrder();

		for (CruiseOrder cruiseorder : co) {
			CruiseOrderdata.add(cruiseorder);
		}

		cruiseID = new TableColumn<>("cruiseID");
		cruiseID.setCellValueFactory(new PropertyValueFactory<>("cruiseID"));

		cruiseShipID = new TableColumn<>("cruiseShipID");
		cruiseShipID.setCellValueFactory(new PropertyValueFactory<>("cruiseShipID"));

		roomNumber = new TableColumn<>("roomNumber");
		roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

		personID = new TableColumn<>("personID");
		personID.setCellValueFactory(new PropertyValueFactory<>("personID"));

		tblOrders.getColumns().addAll(cruiseID, cruiseShipID, roomNumber, personID);

		tblOrders.getItems().setAll(CruiseOrderdata);
		tblOrders.setEditable(true);

	}

	public void addUser(ActionEvent e) throws ParseException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate localDate = dob.getValue();

		Person p = new Person(id.getText(), name.getText(), sur.getText(), java.sql.Date.valueOf(localDate),
				phone.getText(), email.getText(), password.getText());

		Boolean b = Control.getInstance().insertPerson(p);

		if (b) {
			Toast.makeText(U_Main.window, "Added " + p.getFirstName() + " Successfully", 1500, 500, 500);
			tblUsers.getItems().add(new PersonTableItem(p.getPersonID(), p.getFirstName(), p.getSurName(),
					localDate.toString(), p.getPhone(), p.getEmail(), p.getPassword()));
		} else {
			Toast.makeText(U_Main.window, "Failed To Add Person !", 1500, 500, 500);
		}

	}

	public void updateUser(ActionEvent e) throws ParseException {
		PersonTableItem selected = tblUsers.getSelectionModel().getSelectedItem();
		if (selected == null) {
			Toast.makeText(U_Main.window, "Please Choose A Person", 1500, 500, 500);

		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			String date = dob.getValue().toString();
			LocalDate localDate = LocalDate.parse(date, formatter);
			Person p = new Person(id.getText(), name.getText(), sur.getText(), java.sql.Date.valueOf(localDate),
					phone.getText(), email.getText(), password.getText());

			Boolean b = Control.getInstance().updatePerson(p);

			if (b) {
				Toast.makeText(U_Main.window, "Updated " + p.getFirstName() + " Successfully", 1500, 500, 500);
				tblUsers.getItems().add(new PersonTableItem(p.getPersonID(), p.getFirstName(), p.getSurName(), date,
						p.getPhone(), p.getEmail(), p.getPassword()));
			} else {
				Toast.makeText(U_Main.window, "Failed To Update Person !", 1500, 500, 500);
			}

		}

	}

	public void deleteUser(ActionEvent e) {
		PersonTableItem selected = tblUsers.getSelectionModel().getSelectedItem();
		if (selected == null) {
			Toast.makeText(U_Main.window, "Please Choose A Person", 1500, 500, 500);

		} else {

			Boolean b = Control.getInstance().deletePerson(id.getText());

			if (b) {
				Toast.makeText(U_Main.window, "Deleted " + selected.getTcfirst() + " Successfully", 1500, 500, 500);
				tblUsers.getItems().remove(selected);
			} else {
				Toast.makeText(U_Main.window, "Failed To Delete Person !", 1500, 500, 500);
			}

		}
	}

	public void addOrder(ActionEvent e) {

		if (id.getText().equals("")) {
			Toast.makeText(U_Main.window, "Please Choose A Room", 1500, 500, 500);

		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			String date = dob.getValue().toString();
			LocalDate localDate = LocalDate.parse(date, formatter);
			CruiseOrder p = new CruiseOrder(Integer.parseInt(TFcruiseID.getText()),
					Integer.parseInt(TFcruiseShipID.getText()), Integer.parseInt(TFroomNumber.getText()),
					TFpersonID.getText());

			Boolean b = Control.getInstance().insertCruiseOrder(p);

			if (b) {
				Toast.makeText(U_Main.window, "Added Successfully", 1500, 500, 500);
				tblOrders.getItems().add(p);
			} else {
				Toast.makeText(U_Main.window, "Failed To Add Person !", 1500, 500, 500);
			}

		}

	}

	public void updateOrder(ActionEvent e) {
		CruiseOrder selected = tblOrders.getSelectionModel().getSelectedItem();
		if (selected == null) {
			Toast.makeText(U_Main.window, "Please Choose A CruiseOrder", 1500, 500, 500);

		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

			CruiseOrder p = new CruiseOrder(Integer.parseInt(TFcruiseID.getText()),
					Integer.parseInt(TFcruiseShipID.getText()), Integer.parseInt(TFroomNumber.getText()),
					TFpersonID.getText());

			Boolean b = Control.getInstance().updateCruiseOrder(p);

			if (b) {
				Toast.makeText(U_Main.window, "Updated Successfully", 1500, 500, 500);

			} else {
				Toast.makeText(U_Main.window, "Failed To Update CruiseOrder !", 1500, 500, 500);
			}

		}

	}

	public void deleteOrder(ActionEvent e) {
		CruiseOrder selected = tblOrders.getSelectionModel().getSelectedItem();
		if (selected == null) {
			Toast.makeText(U_Main.window, "Please Choose An Order", 1500, 500, 500);

		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");


			Boolean b = Control.getInstance().deleteCruiseOrder(selected.getCruiseID(), selected.getCruiseShipID(),
					selected.getRoomNumber(), selected.getPersonID());

			if (b) {
				Toast.makeText(U_Main.window, "Deleted Successfully", 1500, 500, 500);
				tblOrders.getItems().remove(selected);
			} else {
				Toast.makeText(U_Main.window, "Failed To Delete Order !", 1500, 500, 500);
			}

		}
	}

}
