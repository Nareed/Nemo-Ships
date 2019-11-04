package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Utils.E_RoomType;
import control.Control;
import entity.CruiseShip;
import entity.Room;
import entity.TableItem;
import entity.Toast;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class A_AddingShipRoomController implements Initializable {
	private ArrayList<CruiseShip> csarr = new ArrayList<>();
	private ArrayList<Room> roomsarr = new ArrayList<>();
	private int largestRoomNum = 0;
	private boolean addingmode = false;
	private CruiseShip selectedShip = null;
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
	private Button btnBackToView;
	@FXML
	private TableView<TableItem> roomstbl;
	@FXML
	private TableColumn<TableItem, Integer> roomNum;
	@FXML
	private TableColumn<TableItem, String> numOfBeds;
	@FXML
	private TableColumn<TableItem, E_RoomType> type;
	@FXML
	private TableColumn<TableItem, Integer> price;

	private ObservableList<TableItem> data = FXCollections.observableArrayList();
	private ObservableList<String> typeoptions = FXCollections.observableArrayList("standard", "suite");
	private ComboBox typecomboBox = new ComboBox(typeoptions);

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg, ResourceBundle arg1) {

		iniarr();
		initializeShipID();
		iniView();

		roomNum = new TableColumn<>("roomNum");
		numOfBeds = new TableColumn<>("numOfBeds");
		type = new TableColumn<>("type");
		price = new TableColumn<>("price");
		roomNum = new TableColumn<>("roomNum");

		roomstbl.getColumns().addAll(roomNum, numOfBeds, type, price);

		roomNum.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
		numOfBeds.setCellValueFactory(new PropertyValueFactory<>("numOfBeds"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));

		for (Room room : roomsarr) {
			if (room.getRoomNumber() > largestRoomNum)
				largestRoomNum = room.getRoomNumber();
			data.add(new TableItem(room.getRoomNumber(), room.getRoomType().toString(), room.getBedsAmount(),
					room.getPrice(), room.getCruiseShipID()));
		}

		roomstbl.getItems().setAll(data);
		roomstbl.setEditable(true);
	}

	public void iniView() {
		txtShipID.setVisible(false);

		txtNumPpl.setDisable(true);
		txtShipName.setDisable(true);
		txtManDate.setDisable(true);
		txtMaxCap.setDisable(true);
		roomstbl.setEditable(false);
		// ini buttons
		btnSave.setDisable(true);
		btnReset.setDisable(true);
		btnSave.setDisable(true);
	}

	@SuppressWarnings("deprecation")
	public void handleResetClick(MouseEvent mouEvent) throws IOException {
		int id = 0;
		System.out.println("in reset");
		if (addingmode) {
			txtNumPpl.setText("");
			txtShipName.setText("");
			txtMaxCap.setText("");
			txtShipID.setText("");
			txtManDate.setValue(null);
		} else {
			id = ComboShipID.getValue();
			for (CruiseShip cs : csarr)
				if (id == cs.getCruiseShipID()) {
					selectedShip = cs;
					break;
				}
			System.out.println("in here");
			txtMaxCap.setText(selectedShip.getMaxCapacity() + "");
			txtShipName.setText(selectedShip.getShipName());
			txtNumPpl.setText(selectedShip.getMaxNumberOfPeople() + "");
			Date d = selectedShip.getManufacturingDate();
			d.setYear(d.getYear() - 1999);
			txtManDate.setValue(d.toLocalDate());
			// add the relevant rooms
		}
	}

	public void handleDeleteClick(MouseEvent mouEvent) throws IOException {
		int selectedid = ComboShipID.getValue();
		// delete this ship from database&all its rooms
		initializeShipID();
	}

	public void handleBackToViewClick(MouseEvent mouEvent) throws IOException {
		iniView();
		addingmode = false;
	}

	public void handleAddClick(MouseEvent mouEvent) throws IOException {

		addingmode = true;

		ComboShipID.setVisible(false);
		txtShipID.setVisible(true);
		txtManDate.setDisable(false);
		txtMaxCap.setDisable(false);
		txtNumPpl.setDisable(false);
		txtShipName.setDisable(false);

		txtNumPpl.setText("");
		txtShipName.setText("");
		txtMaxCap.setText("");
		txtShipID.setText("");
		txtManDate.setValue(null);
		largestRoomNum = 0;

		btnAdd.setDisable(true);
		btnSave.setDisable(false);
		btnEdit.setDisable(true);
		btnReset.setDisable(false);

	}

	public void handleEditClick(MouseEvent mouEvent) throws IOException {
		System.out.println("in edit");
		txtNumPpl.setDisable(false);
		txtShipName.setDisable(false);
		txtManDate.setDisable(false);
		txtMaxCap.setDisable(false);
		roomstbl.setEditable(true);

		btnSave.setDisable(false);
		btnEdit.setDisable(true);
		btnReset.setDisable(false);
	}

	public void handleSaveClick(MouseEvent mouEvent) throws IOException {
		int shipid;
		String newshipid = null;
		String maxppl = txtNumPpl.getText();
		String maxCap = txtMaxCap.getText();
		String shipname = txtShipName.getText();
		LocalDate mandate = txtManDate.getValue();
		System.out.println("saving");
		if (emptyValidation("Max Num of ppl", maxppl.isEmpty()) && emptyValidation("Max Capacity", maxCap.isEmpty())
				&& emptyValidation("Ship name", shipname.isEmpty()) && emptyValidation("Man Date", mandate == null)) {
			if (addingmode) {
				// update relevant cruise ship
				newshipid = txtShipID.getText();
				if (validate("ShipID", newshipid, "[0-9]+")) {
					shipid = Integer.parseInt(newshipid);
					System.out.println("new ship id " + newshipid);
					if (validate("Max Capacity", maxCap, "[0-9]+") && validate("Max Num of People", maxppl, "[0-9]+")) {
						if (validate("Max Capacity", maxCap, "[0-9]+")
								&& validate("Max Num of People", maxppl, "[0-9]+")) {
							int cruiseShipID = Integer.parseInt(txtShipID.getText().toString());
							String shipName = (txtShipName.getText().toString());
							Date manufacturingDate = java.sql.Date.valueOf(txtManDate.getValue());
							int maxCapacity = Integer.parseInt(txtMaxCap.getText().toString());
							int maxNumberOfPeople = Integer.parseInt(txtNumPpl.getText().toString());

							Boolean boolean1 = Control.getInstance().insertCruiseShip(new CruiseShip(cruiseShipID,
									shipName, manufacturingDate, maxCapacity, maxNumberOfPeople));

							if (boolean1 == true)
								Toast.makeText(U_Main.window,
										"Cruise Ship Number : " + cruiseShipID + " Added Successfuly :)", 1500, 500,
										500);
							else {
								Toast.makeText(U_Main.window, "Failed To Add Cruise Ship :(", 1500, 500, 500);
							}

						}
					}
				}
				if (!addingmode) {
					// make new ship and save it
					int cruiseShipID = Integer.parseInt(txtShipID.getText().toString());
					String shipName = (txtShipName.getText().toString());
					Date manufacturingDate = java.sql.Date.valueOf(txtManDate.getValue());
					int maxCapacity = Integer.parseInt(txtMaxCap.getText().toString());
					int maxNumberOfPeople = Integer.parseInt(txtNumPpl.getText().toString());

					Boolean boolean1 = Control.getInstance().updateCruiseShip(
							new CruiseShip(cruiseShipID, shipName, manufacturingDate, maxCapacity, maxNumberOfPeople));

					if (boolean1 == true)
						Toast.makeText(U_Main.window,
								"Cruise Ship Number : " + cruiseShipID + " Updated Successfuly :)", 1500, 500, 500);
					else {
						Toast.makeText(U_Main.window, "Failed To Update Cruise Ship :(", 1500, 500, 500);
					}
				}
			}
		}
	}

	private void initializeShipID() {
		for (CruiseShip cs : csarr)
			ComboShipID.getItems().add(cs.getCruiseShipID());

		ComboShipID.valueProperty().addListener(new ChangeListener<Integer>() {
			@SuppressWarnings("deprecation")
			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				System.out.println(oldValue + " " + newValue);
				for (CruiseShip cs : csarr) {
					if (cs.getCruiseShipID() == newValue)
						selectedShip = cs;
				}
				txtMaxCap.setText(selectedShip.getMaxCapacity() + "");
				txtShipName.setText(selectedShip.getShipName());
				txtNumPpl.setText(selectedShip.getMaxNumberOfPeople() + "");
				Date d = selectedShip.getManufacturingDate();
				d.setYear(d.getYear() - 1999);
				txtManDate.setValue(d.toLocalDate());
				// add the relevant rooms

			}
		});
	}

	private boolean validateID(int shipid) {
		for (CruiseShip cs : csarr) {
			if (cs.getCruiseShipID() == shipid) {
				// show message that id is taken and should try another
				return false;
			}
		}
		return true;
	}

	private boolean emptyValidation(String field, boolean empty) {
		if (!empty) {
			return true;
		} else {
			validationAlert(field, true);
			return false;
		}
	}

	private boolean validate(String field, String value, String pattern) {
		if (!value.isEmpty()) {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(value);
			if (m.find() && m.group().equals(value)) {
				return true;
			} else {
				validationAlert(field, false);
				return false;
			}
		} else {
			validationAlert(field, true);
			return false;
		}
	}

	private void validationAlert(String field, boolean empty) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Validation Error");
		alert.setHeaderText(null);
		String s;
		if (empty)
			s = ("Please Enter " + field);
		else
			s = ("Please Enter Valid " + field);
		Toast.makeText(U_Main.window, s, 1500, 500, 500);
		alert.showAndWait();
	}

	private void iniarr() {

		ArrayList<CruiseShip> sc = Control.getInstance().selectCruiseShip();
		this.csarr = sc;
		ArrayList<Room> r = Control.getInstance().selectRoom();
		this.roomsarr = r;

	}
}
