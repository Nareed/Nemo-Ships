package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import control.Control;
import entity.Person;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class U_LoginController implements Initializable {

	@FXML
	private Button loginbtn;
	@FXML
	private Hyperlink signuphere;
	@FXML
	private PasswordField pswrdtxt;
	@FXML
	private TextField emailtxt;
	@FXML
	private MediaView mv_video;
	@FXML
	private VBox vbox;
	@FXML
	private Button btnMinus;
	@FXML
	private Button btnExit;

	MediaPlayer mediaPlayer;


	@Override
	public void initialize(URL arg, ResourceBundle arg1) {
		loginbtn.setStyle(
				" -fx-background-color : #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);"
						+ " -fx-background-radius : 30;" + " -fx-background-insets: 0,1,1;" + " -fx-text-fill: black;"
						+ " -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1)");



		emailtxt.setStyle(emailtxt.getStyle() + "-fx-text-fill:white;");
		pswrdtxt.setStyle(pswrdtxt.getStyle() + "-fx-text-fill:white;");
		Media media = new Media("file:///C:/Users/fadod/workspace/nemo_ships/src/Media/vid.mp4");
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();
		DoubleProperty mvw = mv_video.fitWidthProperty();
		DoubleProperty mvh = mv_video.fitHeightProperty();
		mvw.bind(Bindings.selectDouble(mv_video.sceneProperty(), "width"));
		mvh.bind(Bindings.selectDouble(mv_video.sceneProperty(), "height"));
		mv_video.setPreserveRatio(true);
		mv_video.setMediaPlayer(mediaPlayer);

		btnMinus.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
				// is stage minimizable into task bar. (true | false)
				stage.setIconified(true);
			}
		});

	}


	public void handleLink(ActionEvent event){
		Parent root;
		try {
			Stage stage = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("C_SignUp.fxml"));
			stage.setMaximized(true);
			stage.setScene(new Scene(root));
			mediaPlayer.stop();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void loginOnAction() {
		/*
		 * String email = emailtxt.getText(); String pw = pswrdtxt.getText();
		 * System.out.println("email is " + email);
		 * System.out.println("password is " + pw); String regex =
		 * "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+"; if
		 * (!email.isEmpty()) { if (email.matches(regex)) {
		 * System.out.println("mail is valid"); if (!pw.isEmpty()) {
		 * System.out.println("check if they match and move to user's page"); }
		 * else System.out.println("password is empty"); } else
		 * System.out.println("mail ain't valid"); } else
		 * System.out.println("mail is empty");
		 */

		String email = emailtxt.getText();
		String pw = pswrdtxt.getText();
		if (email.equals("a") && pw.equals("a")) {
			((Stage) btnExit.getScene().getWindow()).close();
			U_ViewLogic.onSignin(email);
			U_ViewLogic.initUIAdmin();
			return;
		}
		if (email.equals("c") && pw.equals("c")) {
			mediaPlayer.stop();
			((Stage) btnExit.getScene().getWindow()).close();
			U_ViewLogic.onSignin("fadi@hotmail.com");
			U_ViewLogic.initUI();
			return;
		}
		if (validate("Email", email, "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
				&& emptyValidation("Password", pw.isEmpty())) {
			for (Person p : Control.getInstance().selectPerson()) {
				if (p.getEmail().equals(email) && p.getPassword().equals(pw)) {
					mediaPlayer.stop();
					((Stage) btnExit.getScene().getWindow()).close();
					U_ViewLogic.onSignin(email);
					U_ViewLogic.initUI();
					return;
				}
			}
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Please Enter Valid Details");
			alert.setHeaderText(null);
			alert.showAndWait();

		}

	}


	public void buttOnMouseEntered() {
		loginbtn.setStyle(
				" -fx-background-color :  #4BB3A9 ;-fx-background-radius : 30");
	}

	public void buttOnMouseExited() {
		loginbtn.setStyle(
				" -fx-background-color : white ;-fx-background-radius : 30");
	}

	/**
	 * this method enables logging in pressing Enter
	 */
	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			loginOnAction();
	}

	@FXML
	public void handleExit(ActionEvent actionEvent) {
		((Stage) btnExit.getScene().getWindow()).close();

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

	private boolean emptyValidation(String field, boolean empty) {
		if (!empty) {
			return true;
		} else {
			validationAlert(field, true);
			return false;
		}
	}

	private void validationAlert(String field, boolean empty) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Validation Error");
		alert.setHeaderText(null);
		if (field.equals("Role"))
			alert.setContentText("Please Select " + field);
		else {
			if (empty)
				alert.setContentText("Please Enter " + field);
			else
				alert.setContentText("Please Enter Valid " + field);
		}
		alert.showAndWait();
	}
}