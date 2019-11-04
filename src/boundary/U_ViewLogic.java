package boundary;

import java.io.IOException;
import java.net.URL;

import control.Control;
import entity.Person;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Class U_ViewLogic ~ manages the windows in the system
 */
public class U_ViewLogic {
	// ------------------------------ Variables ------------------------------
	protected static final Rectangle2D FULL_SCREEN = Screen.getPrimary().getBounds();
	protected static final Rectangle2D VISIBLE_SCREEN = Screen.getPrimary().getVisualBounds();

	// protected static SysData sysData;
	protected static String currentUserID;

	protected static U_LoginController sampleController;
	protected static C_MainPageController sampleController2;
	/*
	 * protected static AdminPlayerController adminPlayerController; protected
	 * static AdminCoachController adminCoachController; protected static
	 * AdminCustomerController adminCustomerController; protected static
	 * AdminReceptionistController adminReceptionistController; protected static
	 * AdminStadiumController adminStadiumController; protected static
	 * AdminTrophyController adminTrophyController; protected static
	 * AdminTeamController adminTeamController; protected static
	 * AdminMatchController adminMatchController;
	 *
	 * protected static RecepMainController recepMainController; protected
	 * static CoachMainController coachMainController; protected static
	 * CustomerMainController customerMainController;
	 */

	// ------------------------------ Methods ------------------------------
	/**
	 * this method starts the windows in the system
	 */
	public static void initUI() {
		newUserWindow();
	}
	public static void initUIAdmin() {
		newAdminWindow();
	}

	/**
	 * this method manages the window properties
	 *
	 * @param fxmlLocation
	 * @param stage
	 * @param prefWidth
	 * @param prefHeight
	 * @param minWidth
	 * @param minHeight
	 * @param maxWidth
	 * @param maxHeight
	 * @param resizable
	 * @param title
	 * @param waitFor
	 */
	protected static void newWindow(URL fxmlLocation, Stage stage, Double prefWidth, Double prefHeight, Double minWidth,
			Double minHeight, Double maxWidth, Double maxHeight, boolean resizable, String title, boolean waitFor) {
		//
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(fxmlLocation);
					Parent root = loader.load();
					Scene scene;

					if (prefWidth == null || prefHeight == null)
						scene = new Scene(root);

					else
						scene = new Scene(root, prefWidth, prefHeight);
					stage.setScene(scene);

					if (minWidth != null)
						stage.setMinWidth(minWidth);
					if (minHeight != null)
						stage.setMinHeight(minHeight);
					if (maxWidth != null)
						stage.setMaxWidth(maxWidth);
					if (maxHeight != null)
						stage.setMaxHeight(maxHeight);

					stage.setResizable(resizable);

					if (waitFor)
						stage.initModality(Modality.APPLICATION_MODAL);

					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ------------------------------ Login ------------------------------
	/**
	 * Open Login Window
	 */
	protected static void newUserWindow() {
		Stage stage = new Stage();
		stage.setMaximized(true);
		stage.initStyle(StageStyle.UNDECORATED);
		newWindow(U_ViewLogic.class.getResource("C_MainPage.fxml"), stage, null, null, null, null, null, null, false,
				"UserWindow", false);
	}

	protected static void newAdminWindow() {
		Stage stage = new Stage();
		stage.setMaximized(true);
		stage.initStyle(StageStyle.UNDECORATED);
		newWindow(U_ViewLogic.class.getResource("A_MainPage.fxml"), stage, null, null, null, null, null, null, false,
				"UserWindow", false);

	}

	protected static void onSignin(String email) {
		if (email.equals("admin"))
			currentUserID = "admin";
		else
			for (Person p : Control.getInstance().selectPerson()) {
				if (p.getEmail() != null && p.getEmail().equals(email))
					currentUserID = p.getPersonID();
			}
	}

	protected static void onSignout() {
		currentUserID = null;
	}

	protected static void newSignUpWindow() {
		Stage stage = new Stage();

		newWindow(U_ViewLogic.class.getResource("signUp.fxml"), stage, null, null, null, null, null, null, false,
				"sign Up", false);
	}

}
