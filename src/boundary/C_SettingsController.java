package boundary;

import java.net.URL;
import java.util.ResourceBundle;

import control.Control;
import entity.Person;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class C_SettingsController implements Initializable {

	@FXML
	private PasswordField tf_old_password;
	@FXML
	private PasswordField tf_new_password;
	@FXML
	private PasswordField tf_confirm_password;
	@FXML
	private TextField tf_email;
	@FXML
	private TextField tf_phone;
	@FXML
	private Button btn_reset_password;
	@FXML
	private Button btn_save_password;
	@FXML
	private Button btn_save_phone_email;
	@FXML
	private Button btn_reset_email_phone;
	@FXML
	private RadioButton rd_phone;
	@FXML
	private RadioButton rd_email;
	@FXML
	private Text t_personID;
	@FXML
	private Text t_firstName;
	@FXML
	private Text t_surName;
	@FXML
	private Text t_dateOfBirth;
	@FXML
	private Text t_phone;
	@FXML
	private Text t_email;

	private Person user = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Control cp = Control.getInstance();

		for (Person p : cp.selectPerson()) {
			if (p.getPersonID().equals(U_ViewLogic.currentUserID))
				user = p;
		}
		tf_email.setPromptText(user.getEmail());
		tf_phone.setPromptText(user.getPhone());
		t_personID.setText(user.getPersonID());
		t_firstName.setText(user.getFirstName());
		t_surName.setText(user.getSurName());
		t_dateOfBirth.setText(user.getDateOfBirth().getDay()+"/"+user.getDateOfBirth().getMonth()+"/"+(user.getDateOfBirth().getYear()+1900));
		t_phone.setText(user.getPhone());
		t_email.setText(user.getEmail());

		btn_save_password.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert a = new Alert(AlertType.NONE);
				if (tf_old_password.getText().equals(user.getPassword())) {
					if (tf_new_password.getText().equals(tf_confirm_password.getText())) {
						if (!tf_new_password.getText().equals(null)) {
							try {
								if (true == cp.updatePassword(U_ViewLogic.currentUserID, tf_new_password.getText())) {

									a.setAlertType(AlertType.CONFIRMATION);
									a.setContentText("Success");
									a.show();
								} else {
									a.setAlertType(AlertType.CONFIRMATION);
									a.setContentText("Failed To Update Password !");
									a.show();
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				} else {
					a.setAlertType(AlertType.ERROR);
					a.setContentText("Wrong Password Typed !");
					a.show();
				}

			}

		});

		rd_email.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				clearClicks();
				rd_email.setSelected(true);
				tf_phone.setDisable(true);
				tf_email.setDisable(false);
			}

		});

		rd_phone.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				clearClicks();
				rd_phone.setSelected(true);
				tf_email.setDisable(true);
				tf_phone.setDisable(false);
			}

		});

		btn_reset_email_phone.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				clearClicks();

			}
		});
		btn_reset_password.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				tf_confirm_password.clear();
				tf_new_password.clear();
				tf_old_password.clear();

			}
		});

		btn_save_phone_email.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Alert a = new Alert(AlertType.NONE);
				if (!tf_phone.isDisabled()) {
					if (!tf_phone.getText().equals(null)) {
						if (true == cp.updatePhone(U_ViewLogic.currentUserID, tf_phone.getText())) {
							a.setAlertType(AlertType.CONFIRMATION);
							a.setContentText("Success");
							a.show();
						} else {
							a.setAlertType(AlertType.CONFIRMATION);
							a.setContentText("Failed To Update Phone !");
							a.show();
						}

					}

				} else {
					// Pattern pattern =
					// Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
					// Matcher matcher =
					// tf_email.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
					if (!tf_email.getText().equals(null)) {
						if (true == cp.updateEmail(U_ViewLogic.currentUserID, tf_email.getText())) {
							a.setAlertType(AlertType.CONFIRMATION);
							a.setContentText("Success");
							a.show();
						} else {
							a.setAlertType(AlertType.CONFIRMATION);
							a.setContentText("Failed To Update Email !");
							a.show();
						}

					}

				}
			}


		});
	}

	protected void clearClicks() {
		rd_email.setSelected(false);
		rd_phone.setSelected(false);
		tf_email.clear();
		tf_email.setDisable(true);
		tf_phone.clear();
		tf_phone.setDisable(true	);
	}

}
