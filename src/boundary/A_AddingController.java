package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class A_AddingController implements Initializable {
	@FXML
	private TabPane tabpane;
	@FXML
	private Tab ShipRoomTab;
	@FXML
	private BorderPane bpADDING;
	@FXML
	private Button personOrder;
	@FXML
	private Button sailingCruise;
	@FXML
	private Button shipRoom;
	@FXML
	private Tab SailingCruiseTab;
	@FXML
	private BorderPane bp1;

	@Override
	public void initialize(URL arg, ResourceBundle arg1) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("A_PersonOrder.fxml"));
			bpADDING.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void onClickH(ActionEvent event){

		if (event.getSource() == personOrder) {
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("A_PersonOrder.fxml"));
				bpADDING.setCenter(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (event.getSource() == sailingCruise) {
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("A_SailingXCruise.fxml"));
				bpADDING.setCenter(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (event.getSource() == shipRoom) {
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("A_AddingShipRoom.fxml"));
				bpADDING.setCenter(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}



	}

}
