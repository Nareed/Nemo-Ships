package boundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import control.Control;
import entity.CruiseShip;
import entity.popularDest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class U_Main extends Application {

	public static Stage window;
	Button btn;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;

		System.out.println("FIRST" + Control.getInstance().roomCongestion());

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("U_Login.fxml"));
			primaryStage.setMaximized(true);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}