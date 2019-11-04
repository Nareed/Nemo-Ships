package boundary;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import control.Control;
import entity.BookObject;
import entity.CruiseOrder;
import entity.Room;
import entity.TableItem;
import entity.Toast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class C_BookController implements Initializable {

	private BookObject bo;

	@FXML
	private Text txtnights;

	@FXML
	private Text txtship;

	@FXML
	private Text txttraveling;

	@FXML
	private Button btnExit;
	@FXML
	private Button bookbtn;

	@FXML
	private TableView<TableItem> tblRooms;
	@FXML
	private TableColumn<TableItem, Integer> roomNum;
	@FXML
	private TableColumn<TableItem, String> type;
	@FXML
	private TableColumn<TableItem, String> numOfBeds;
	@FXML
	private TableColumn<TableItem, Integer> price;

	private ObservableList<TableItem> data = FXCollections.observableArrayList();
	int sailingid;
	@FXML
	private CheckBox cbsuite;
	@FXML
	private CheckBox cbstandard;
	@FXML
	private CheckBox cb2;
	@FXML
	private CheckBox cb3;
	@FXML
	private CheckBox cb4;
	@FXML
	DialogPane dp;

	private HashMap<CheckBox, Boolean> hm = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg, ResourceBundle arg1) {

		hm.put(cb2, true);
		hm.put(cb3, true);
		hm.put(cb4, true);
		hm.put(cbstandard, true);
		hm.put(cbsuite, true);

		for (CheckBox key : hm.keySet())
			key.setSelected(true);

		sailingid = C_OffersController.SailingID;
		bo = Control.getInstance().getBook(sailingid);
		// get booking object for the sailing id
		bo.setCountry(Control.getInstance().getCountriesInSailing(sailingid));
		txtnights.setText(bo.getNights() + " Nights");
		txtship.setText(bo.getShipName());

		String traveling = "Traveling to ";

		if (bo.getCountry() != null) {
			for (int i = 0; i < bo.getCountry().length; i++) {
				if (bo.getCountry()[i] != null) {
					traveling = traveling + bo.getCountry()[i] + " , ";
				}

			}
		}
		traveling = traveling.substring(0, traveling.length() - 3);
		txttraveling.setText(traveling);

		System.out.println("in with ");

		ArrayList<Room> array = Control.getInstance().getBookTable(sailingid);
		for (Room room : array) {

			data.add(new TableItem(room.getRoomNumber(), room.getRoomType().toString(), room.getBedsAmount(),
					room.getPrice(), room.getCruiseShipID()));
		}

		roomNum.setCellValueFactory(new PropertyValueFactory<>("roomNum"));

		type.setCellValueFactory(new PropertyValueFactory<>("type"));

		numOfBeds.setCellValueFactory(new PropertyValueFactory<>("numOfBeds"));

		price.setCellValueFactory(new PropertyValueFactory<>("price"));

		tblRooms.getColumns().addAll(roomNum, type, numOfBeds, price);

		tblRooms.getItems().setAll(data);

		tblRooms.setEditable(true);
	}

	@FXML
	public void handleExit(ActionEvent actionEvent) {
		System.out.println("Exit Pressed");
		((Stage) btnExit.getScene().getWindow()).close();

	}

	@FXML
	public void onBooking(ActionEvent me) {

		TableItem selected = tblRooms.getSelectionModel().getSelectedItem();
		if (selected == null) {
			Toast.makeText(U_Main.window, "Please Choose A Room", 1500, 500, 500);

		} else {
			Boolean b = Control.getInstance().insertCruiseOrder(new CruiseOrder(sailingid, selected.getCriuseShipID(),
					selected.getRoomNum(), U_ViewLogic.currentUserID));
			if (b) {
				Toast.makeText(U_Main.window, "Booked Room " + selected.getRoomNum() + " Successfully", 1500, 500, 500);
				tblRooms.getItems().remove(selected);
			} else {
				Toast.makeText(U_Main.window, "Failed To Book Room !", 1500, 500, 500);
			}
		}

	}

	@FXML
	public void handleChecked(ActionEvent actionEvent) {

		CheckBox c = (CheckBox) actionEvent.getSource();

		if (c.getText().equals(cbstandard.getText()))
			hm.put(cbstandard, !hm.get(cbstandard));

		if (c.getText().equals(cbsuite.getText()))
			hm.put(cbsuite, !hm.get(cbsuite));

		if (c.getText().equals(cb2.getText()))
			hm.put(cb2, !hm.get(cb2));

		if (c.getText().equals(cb3.getText()))
			hm.put(cb3, !hm.get(cb3));

		if (c.getText().equals(cb4.getText()))
			hm.put(cb4, !hm.get(cb4));

		handleFilter();

	}

	public void handleFilter() {

		Set<String> roomTypeArr = new HashSet<>();
		Set<String> bedAmountArr = new HashSet<>();

		if (hm.get(cbstandard))
			roomTypeArr.add("standard");
		if (hm.get(cbsuite))
			roomTypeArr.add("suite");
		if (hm.get(cb2))
			bedAmountArr.add("2");
		if (hm.get(cb3))
			bedAmountArr.add("3");
		if (hm.get(cb4))
			bedAmountArr.add("4");

		tblRooms.getItems().clear();
		ObservableList<TableItem> tempData = FXCollections.observableArrayList();

		ArrayList<Room> array = Control.getInstance().getBookTable(sailingid);
		for (Room room : array)
			if (roomTypeArr.contains(room.getRoomType().toString()) && bedAmountArr.contains(room.getBedsAmount()))
				tempData.add(new TableItem(room.getRoomNumber(), room.getRoomType().toString(), room.getBedsAmount(),
						room.getPrice(), room.getCruiseShipID()));

		tblRooms.getItems().setAll(tempData);
	}

}
