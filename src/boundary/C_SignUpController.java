package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class C_SignUpController implements Initializable {

	@FXML
	private TextField tfemail;
	@FXML
	private TextField tfname;
	@FXML
	private TextField tflast;
	@FXML
	private TextField tfphone;
	@FXML
	private TextField tfid;
	@FXML
	private DatePicker tfdate;
	@FXML
	private Button signupbtn;
	@FXML
	private Button btnMinus;
	@FXML
	private Button btnExit;
	@FXML
	private BorderPane bp;
	@FXML
	private PasswordField pswrdtxt;
	@FXML
	private ImageView slideShow;
	int slideshowCount;
	ArrayList<Image> imageArrayList;
	@Override
	public void initialize(URL arg, ResourceBundle arg1) {
		btnMinus.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
				// is stage minimizable into task bar. (true | false)
				stage.setIconified(true);
			}
		});

		imageArrayList = new ArrayList<>();
		imageArrayList.add(new Image("Media/1.png"));
		imageArrayList.add(new Image("Media/2.png"));
		imageArrayList.add(new Image("Media/3.png"));
		imageArrayList.add(new Image("Media/4.png"));
		slideShow.setImage(imageArrayList.get(slideshowCount));
		DoubleProperty mvw = slideShow.fitWidthProperty();
		mvw.bind(Bindings.selectDouble(slideShow.sceneProperty(), "width"));
		slideshowCount++;

		tfemail.setStyle(tfemail.getStyle() + "-fx-text-fill:white;");
		pswrdtxt.setStyle(pswrdtxt.getStyle() + "-fx-text-fill:white;");
		tfname.setStyle(tfname.getStyle() + "-fx-text-fill:white;");
		tflast.setStyle(tflast.getStyle() + "-fx-text-fill:white;");
		tfphone.setStyle(tfphone.getStyle() + "-fx-text-fill:white;");
		tfid.setStyle(tfid.getStyle() + "-fx-text-fill:white;");
		tfdate.setStyle(tfdate.getStyle() + "-fx-text-fill:white;");

	}

	public void handleClicks(ActionEvent actionEvent) throws IOException, ParseException {
		if (actionEvent.getSource() == signupbtn) {
			System.out.println("sign up button pressed");
			String email = tfemail.getText();
			String name = tfname.getText();
			String last = tflast.getText();
			String phone = tfphone.getText();
			String id = tfid.getText();
			LocalDate date = tfdate.getValue();
			String password = pswrdtxt.getText();

			String regex = "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+";

			if (email.isEmpty())
				fieldIsEmpty(tfemail);
			else if (name.isEmpty())
				fieldIsEmpty(tfname);
			else if (last.isEmpty())
				fieldIsEmpty(tflast);
			else if (phone.isEmpty())
				fieldIsEmpty(tfphone);
			else if (id.isEmpty())
				fieldIsEmpty(tfid);
			else if (date == null)
				JOptionPane.showMessageDialog(null, "date field is empty");
			else if (!email.matches(regex))
				JOptionPane.showMessageDialog(null, "email aint valid");
			else if (!name.matches("[a-zA-Z]+") || name.length() > 30)
				JOptionPane.showMessageDialog(null, "first name aint valid");
			else if (!last.matches("[a-zA-Z]+") || last.length() > 30)
				JOptionPane.showMessageDialog(null, "last Fname aint valid");
			else if (!phone.matches("[0-9]+") || phone.length() != 10)
				JOptionPane.showMessageDialog(null, "phone aint valid");
			else if (!id.matches("[0-9]+") || id.length() != 9)
				JOptionPane.showMessageDialog(null, "id aint valid");
			else {

				Date d = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(date.toString());
				Boolean b = Control.getInstance().insertPerson(new Person(id,name,last,d,phone,email,password));
				if(b==true){
					System.out.println("Added Successfully");
				}else{
					System.out.println("all fields are valid");

				}
				// make new person
				// log into his acc

			}
		}
	}

	public void fieldIsEmpty(TextField tf) {
		tf.setStyle("-fx-background-color: #FF9B9B");
		JOptionPane.showMessageDialog(null, tf + "is empty");
	}

	@FXML
	public void handleExit(ActionEvent actionEvent) {
		((Stage) btnExit.getScene().getWindow()).close();

	}
	@FXML
	public void OnMouseEnteredImage() {
		slideShow.setImage(imageArrayList.get(slideshowCount));
		DoubleProperty mvw = slideShow.fitWidthProperty();
		mvw.bind(Bindings.selectDouble(slideShow.sceneProperty(), "width"));
		slideshowCount++;
		if (slideshowCount >= imageArrayList.size()) {
			slideshowCount = 0;
		}

	}
	@FXML
	public void handleBack(ActionEvent actionEvent) {
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