package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entity.CustomRipple;
import entity.Toast;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class A_MainPageController implements Initializable {
	@FXML
	private Button btnDashboard;
	@FXML
	private Button btnManageItems;
	@FXML
	private Button btnSignout;
	@FXML
	private Button btnMinus;
	@FXML
	private Button btnExit;
	@FXML
	private SplitPane splitPane;
	@FXML
	private BorderPane borderPane;
	@FXML
	private HBox titleBar;

	private List<Button> buttonList = new ArrayList<Button>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Toast.makeText(U_Main.window, "Logged In Successfully", 1500, 500, 500);

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("A_Dashboard.fxml"));
			borderPane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

		btnMinus.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
				// is stage minimizable into task bar. (true | false)
				stage.setIconified(true);
			}
		});

		buttonList.add(btnDashboard);
		buttonList.add(btnManageItems);
		buttonList.add(btnSignout);

		for (Button b : buttonList) {
			b.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					clearClick();
					CustomRipple customRipple = new CustomRipple();
					customRipple.createRipple(event.getSceneX(), event.getSceneY(), borderPane, b);
					if (event.getSource() == btnDashboard) {
						btnDashboard.setStyle("-fx-background-color :  #265573");
						Parent root;
						try {
							root = FXMLLoader.load(getClass().getResource("A_Dashboard.fxml"));
							borderPane.setCenter(root);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (event.getSource() == btnManageItems) {
						btnManageItems.setStyle("-fx-background-color : #265573");
						Parent root;
						try {
							root = FXMLLoader.load(getClass().getResource("A_ManageItems.fxml"));
							borderPane.setCenter(root);

						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					if (event.getSource() == btnSignout) {
						((Stage) btnExit.getScene().getWindow()).close();
						Stage s = ((Stage) btnExit.getScene().getWindow());
						Parent root;
						try {
							root = FXMLLoader.load(getClass().getResource("U_Login.fxml"));
							s.setMaximized(true);
							s.setScene(new Scene(root));
							s.show();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
			});
		}
	}

	@FXML
	public void clearClick() {

		btnDashboard.setStyle("-fx-background-color :   #1A394C");
		btnManageItems.setStyle("-fx-background-color :   #1A394C");
		btnSignout.setStyle("-fx-background-color :  #1A394C");

	}

	@FXML
	public void handleExit(ActionEvent actionEvent) {
		((Stage) btnExit.getScene().getWindow()).close();
	}

}
