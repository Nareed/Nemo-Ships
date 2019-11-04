package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import control.Control;
import entity.OfferObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class C_OffersController implements Initializable {
	public static ArrayList<OfferObject> data = new ArrayList<>();

	private boolean[] monthsbtn = new boolean[5];
	private boolean[] nightsbtn = new boolean[4];
	private String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	@FXML
	private ToggleButton btn0;
	@FXML
	private ToggleButton btn1;
	@FXML
	private ToggleButton btn2;
	@FXML
	private ToggleButton btn3;
	@FXML
	private ToggleButton btn4;
	@FXML
	private ToggleButton nbtn0;
	@FXML
	private ToggleButton nbtn1;
	@FXML
	private ToggleButton nbtn2;
	@FXML
	private ToggleButton nbtn3;
	@FXML
	private Button btnGO;
	@FXML
	private TextField minPrice;
	@FXML
	private TextField maxPrice;
	@FXML
	private ListView<String> countrylist = new ListView<String>();
	ListView<String> selected = new ListView<>();
	@FXML
	private HBox offersbox;

	public static int SailingID;

	Node[] nodes;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		iniToggleButtons();
		C_OffersController.data = Control.getInstance().getOffers();
		C_OfferObjectController.setData(data);
		nodes = new Node[20];

		for (int i = 0; i < data.size() && i < nodes.length; i++) {
			try {
				nodes[i] = (FXMLLoader.load(getClass().getResource("C_OfferObject.fxml")));
				offersbox.getChildren().add(nodes[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("deprecation")
	private void iniToggleButtons() {
		Date d = new Date();
		int curr = d.getMonth();

		btn0.setText(months[curr]);
		btn1.setText(months[curr + 1]);
		btn2.setText(months[curr + 2]);
		btn3.setText(months[curr + 3]);
		btn4.setText(months[curr + 4]);

	}

	// ---------------- handle nights buttons ----------------//
	@FXML
	private void nightsMouseClicked(MouseEvent event) {
		if (!nightsIsPressed(((ToggleButton) event.getSource()).getId())) {
			((ToggleButton) event.getSource())
					.setStyle("-fx-background-color: red; -fx-background-radius: 30; textFill=WHITE");
		} else
			((ToggleButton) event.getSource())
					.setStyle("-fx-background-color:  #1a394c; -fx-background-radius: 30; textFill=WHITE");
		clickNights(((ToggleButton) event.getSource()).getId());
	}

	@FXML
	private void nightsMouseEntered(MouseEvent event) {
		if (!nightsIsPressed(((ToggleButton) event.getSource()).getId()))
			((ToggleButton) event.getSource())
					.setStyle("-fx-background-color: #265573; -fx-background-radius: 30; textFill=WHITE");
	}

	@FXML
	private void nightsMouseExited(MouseEvent event) {
		if (!nightsIsPressed(((ToggleButton) event.getSource()).getId()))
			((ToggleButton) event.getSource())
					.setStyle("-fx-background-color:  #1a394c; -fx-background-radius: 30; textFill=WHITE");
	}

	private boolean nightsIsPressed(String s) {
		int num = Character.getNumericValue(s.charAt(4));
		return nightsbtn[num];
	}

	private void clickNights(String s) {
		int num = Character.getNumericValue(s.charAt(4));
		nightsbtn[num] = !nightsbtn[num];
		return;
	}

	// ---------------- handle month buttons ----------------//
	@FXML
	private void monthMouseClicked(MouseEvent event) {
		if (!monthIsPressed(((ToggleButton) event.getSource()).getId()))
			((ToggleButton) event.getSource())
					.setStyle("-fx-background-color: red; -fx-background-radius: 30; textFill=WHITE");
		else
			((ToggleButton) event.getSource())
					.setStyle("-fx-background-color:  #4BB3A9; -fx-background-radius: 30; textFill=WHITE");
		clickMonth(((ToggleButton) event.getSource()).getId());
	}

	@FXML
	private void monthMouseEntered(MouseEvent event) {
		if (!monthIsPressed(((ToggleButton) event.getSource()).getId()))
			((ToggleButton) event.getSource())
					.setStyle("-fx-background-color: #265573; -fx-background-radius: 30; textFill=WHITE");
	}

	@FXML
	private void monthMouseExited(MouseEvent event) {
		if (!monthIsPressed(((ToggleButton) event.getSource()).getId()))
			((ToggleButton) event.getSource())
					.setStyle("-fx-background-color:  #4BB3A9; -fx-background-radius: 30; textFill=WHITE");
	}

	private boolean monthIsPressed(String s) {
		int num = Character.getNumericValue(s.charAt(3));
		return monthsbtn[num];
	}

	private void clickMonth(String s) {
		int num = Character.getNumericValue(s.charAt(3));
		monthsbtn[num] = !monthsbtn[num];
		return;
	}

	@FXML
	private void goMouseClicked(MouseEvent event) {
		ArrayList<Integer> nightsarr = new ArrayList<>();
		offersbox.getChildren().clear();
		if (nightsbtn[0]) {
			nightsarr.add(2);
			nightsarr.add(3);
			nightsarr.add(4);
			nightsarr.add(5);
		}
		if (nightsbtn[1]) {
			nightsarr.add(6);
			nightsarr.add(7);
			nightsarr.add(8);
		}
		if (nightsbtn[2]) {
			nightsarr.add(9);
			nightsarr.add(10);
			nightsarr.add(11);
		}
		if (nightsbtn[3]) {
			nightsarr.add(12);
			nightsarr.add(13);
			nightsarr.add(14);
		}
		ArrayList<String> montharr = new ArrayList<>();

		if (monthsbtn[0])
			montharr.add(btn0.getText());
		if (monthsbtn[1])
			montharr.add(btn1.getText());
		if (monthsbtn[2])
			montharr.add(btn2.getText());
		if (monthsbtn[3])
			montharr.add(btn3.getText());
		if (monthsbtn[4])
			montharr.add(btn4.getText());

		if (nightsarr.size() == 0 && montharr.size() == 0) {

			C_OffersController.data = Control.getInstance().getOffers();
			C_OfferObjectController.setData(data);

			for (int i = 0; i < data.size() && i < nodes.length; i++) {
				try {
					nodes[i] = (FXMLLoader.load(getClass().getResource("C_OfferObject.fxml")));
					offersbox.getChildren().add(nodes[i]);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		if (nightsarr.size() == 0 && montharr.size() != 0) {

			C_OffersController.data = Control.getInstance().refineOffersByMonth(montharr);
			C_OfferObjectController.setData(data);

			for (int i = 0; i < data.size() && i < nodes.length; i++) {
				try {
					nodes[i] = (FXMLLoader.load(getClass().getResource("C_OfferObject.fxml")));
					offersbox.getChildren().add(nodes[i]);

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}
		if (nightsarr.size() != 0 && montharr.size() == 0) {

			C_OffersController.data = Control.getInstance().refineOffersByNights(nightsarr);
			C_OfferObjectController.setData(data);

			for (int i = 0; i < data.size() && i < nodes.length; i++) {
				try {
					nodes[i] = (FXMLLoader.load(getClass().getResource("C_OfferObject.fxml")));
					offersbox.getChildren().add(nodes[i]);

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		if (nightsarr.size() != 0 && montharr.size() != 0) {

			Set<OfferObject> set = new HashSet<>();
			set.addAll(Control.getInstance().refineOffersByNights(nightsarr));
			set.addAll(Control.getInstance().refineOffersByMonth(montharr));
			ArrayList<OfferObject> array = new ArrayList<>(set);
			C_OffersController.data = array;
			C_OfferObjectController.setData(data);

			for (int i = 0; i < data.size() && i < nodes.length; i++) {
				try {
					nodes[i] = (FXMLLoader.load(getClass().getResource("C_OfferObject.fxml")));
					offersbox.getChildren().add(nodes[i]);

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

}
