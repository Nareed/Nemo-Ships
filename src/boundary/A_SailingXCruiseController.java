package boundary;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import control.Control;
import entity.Cruise;
import entity.Person;
import entity.PersonTableItem;
import entity.Sailing;
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

public class A_SailingXCruiseController implements Initializable {
	private ArrayList<Sailing> sailingArr = new ArrayList<>();
	private ArrayList<Cruise> crusieArr = new ArrayList<>();

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
	private TableView<Sailing> tblSailing;
	@FXML
	private TableColumn<Sailing, String> sailingID;
	@FXML
	private TableColumn<Sailing, String> leavingTime;
	@FXML
	private TableColumn<Sailing, String> returnTime;
	@FXML
	private TableColumn<Sailing, String> tcbirth;
	@FXML
	private TableColumn<Sailing, String> tcphone;
	@FXML
	private TableColumn<Sailing, String> tcemail;
	@FXML
	private TableColumn<Sailing, String> tcpass;
	@FXML
	private TableView<Cruise> tblCruise;
	@FXML
	private TableColumn<Cruise, Integer> cruiseID;
	@FXML
	private TableColumn<Cruise, Integer> cruiseShipID;
	@FXML
	private TableColumn<Cruise, Integer> roomNumber;
	@FXML
	private TableColumn<Cruise, String> personID;
	@FXML
	TextField a;
	@FXML
	DatePicker b;
	@FXML
	DatePicker c;
	@FXML
	TextField d;
	@FXML
	TextField e;

	private ObservableList<Sailing> SailingData = FXCollections.observableArrayList();
	private ObservableList<Cruise> CruiseOrderdata = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg, ResourceBundle arg1) {
		System.out.println("in this ini");
		Date d = new Date(0);

		ArrayList<Sailing> p = Control.getInstance().selectSailing();

		for (Sailing sailing : p) {
			SailingData.add(sailing);
		}

		sailingID = new TableColumn<>("sailingID");
		sailingID.setCellValueFactory(new PropertyValueFactory<>("sailingID"));

		leavingTime = new TableColumn<>("leavingTime");
		leavingTime.setCellValueFactory(new PropertyValueFactory<>("leavingTime"));

		returnTime = new TableColumn<>("returnTime");
		returnTime.setCellValueFactory(new PropertyValueFactory<>("returnTime"));

		tblSailing.getColumns().addAll(sailingID, leavingTime, returnTime);

		tblSailing.getItems().setAll(SailingData);

		ArrayList<Cruise> co = Control.getInstance().selectCruise();

		for (Cruise cruise : co) {
			CruiseOrderdata.add(cruise);
		}

		cruiseID = new TableColumn<>("cruiseID");
		cruiseID.setCellValueFactory(new PropertyValueFactory<>("cruiseID"));

		cruiseShipID = new TableColumn<>("cruiseShipID");
		cruiseShipID.setCellValueFactory(new PropertyValueFactory<>("cruiseShipID"));

		tblCruise.getColumns().addAll(cruiseID, cruiseShipID);

		tblCruise.getItems().setAll(CruiseOrderdata);

	}

	public void addSailing(ActionEvent e) throws ParseException {

		if (a.getText().isEmpty() || Control.getInstance().selectSailingIDS().contains(Integer.parseInt(a.getText()))) {
			Toast.makeText(U_Main.window, "Please Enter Valid ID", 1500, 500, 500);

		}

		else {

			Sailing s = new Sailing(Integer.parseInt(a.getText()), java.sql.Date.valueOf(b.getValue()),
					java.sql.Date.valueOf(c.getValue()));

			Boolean b = Control.getInstance().insertSailing(s);

			if (b) {
				Toast.makeText(U_Main.window, "Added Sailing Successfully", 1500, 500, 500);
				tblSailing.getItems().add(s);
			} else {
				Toast.makeText(U_Main.window, "Failed To Add Sailing !", 1500, 500, 500);
			}
		}

	}

	public void addCruise(ActionEvent e2) throws ParseException {

		Cruise s = new Cruise(Integer.parseInt(d.getText()), (Integer.parseInt(e.getText())));

		Boolean b = Control.getInstance().insertCruise(s);

		if (b) {
			Toast.makeText(U_Main.window, " addCruise Successfully", 1500, 500, 500);
			tblCruise.getItems().add(s);
		} else {
			Toast.makeText(U_Main.window, "Failed To Add Sailing !", 1500, 500, 500);
		}

	}

	public void updateSailing(ActionEvent e) throws ParseException {
		/*
		 * PersonTableItem selected =
		 * tblSailing.getSelectionModel().getSelectedItem(); if (selected ==
		 * null) { Toast.makeText(U_Main.window, "Please Choose A Person", 1500,
		 * 500, 500);
		 *
		 * } else { DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("d/MM/yyyy"); String date =
		 * dob.getValue().toString(); LocalDate localDate =
		 * LocalDate.parse(date, formatter); Person p = new Person(id.getText(),
		 * name.getText(), sur.getText(), java.sql.Date.valueOf(localDate),
		 * phone.getText(), email.getText(), password.getText());
		 *
		 * Boolean b = Control.getInstance().updatePerson(p);
		 *
		 * if (b) { Toast.makeText(U_Main.window, "Updated " + p.getFirstName()
		 * + " Successfully", 1500, 500, 500); tblSailing.getItems().add(new
		 * PersonTableItem(p.getPersonID(), p.getFirstName(), p.getSurName(),
		 * date, p.getPhone(), p.getEmail(), p.getPassword())); } else {
		 * Toast.makeText(U_Main.window, "Failed To Update Person !", 1500, 500,
		 * 500); }
		 *
		 * }
		 */

	}

	public void deleteSailing(ActionEvent e) {
		Sailing selected = tblSailing.getSelectionModel().getSelectedItem();
		if (selected == null) {
			Toast.makeText(U_Main.window, "Please Choose A Sailing", 1500, 500, 500);

		} else {

			Boolean b = Control.getInstance().deleteSailing(Integer.parseInt(a.getText()));

			if (b) {
				Toast.makeText(U_Main.window, "Deleted Successfully", 1500, 500, 500);
				tblSailing.getItems().remove(selected);
			} else {
				Toast.makeText(U_Main.window, "Failed To Delete !", 1500, 500, 500);
			}

		}
	}

	public void deleteCruise(ActionEvent ee) {
		Cruise selected = tblCruise.getSelectionModel().getSelectedItem();
		if (selected == null) {
			Toast.makeText(U_Main.window, "Please Choose A Crusie", 1500, 500, 500);

		} else {

			Boolean b = Control.getInstance().deleteCruise(Integer.parseInt(d.getText()),
					Integer.parseInt(e.getText()));

			if (b) {
				Toast.makeText(U_Main.window, "Deleted Successfully", 1500, 500, 500);
				tblSailing.getItems().remove(selected);
			} else {
				Toast.makeText(U_Main.window, "Failed To Delete !", 1500, 500, 500);
			}

		}
	}

}
