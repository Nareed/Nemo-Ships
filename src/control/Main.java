package control;

import boundary.U_ViewLogic;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

	Stage window;
	Button btn;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		U_ViewLogic.initUI();

	}
}