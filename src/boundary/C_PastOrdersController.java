package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import control.Control;
import entity.HistoryObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class C_PastOrdersController implements Initializable {
	ArrayList<HistoryObject> ho;
	@FXML
	private VBox pnItems;
	@FXML
	private Label totalOrders;
	@FXML
	private Label countriesVisited;
	@FXML
	private Label portsLanded;
	@FXML
	private Button btnOverview;
	@FXML
	private Button btnOrders;
	@FXML
	private Button btnMenus;
	@FXML
	private Button btnSettings;
	@FXML
	private Button btnSignout;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		C_ItemController.counter = 0;
		Control cp = Control.getInstance();
		ho = cp.getPastOrdersByPerson(U_ViewLogic.currentUserID);

		Node[] nodes = new Node[ho.size()];
		for (int i = 0; i < ho.size(); i++) {
			try {
				nodes[i] = FXMLLoader.load(getClass().getResource("C_PastItems.fxml"));
				pnItems.getChildren().add(nodes[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
