package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entity.OfferObject;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class C_OfferObjectController implements Initializable {
	static ArrayList<OfferObject> data;
	protected static int counter = 0;
	@FXML
	private Text txtnights;
	@FXML
	private Text txtdeparture;
	@FXML
	private Text txtport;
	@FXML
	private Text txtfrom;
	@FXML
	private Text txtlast;
	@FXML
	private Text txtshipname;
	@FXML
	private Text txtid;
	@FXML
	private Label lbllast;
	@FXML
	private ImageView OfferImage;

	@Override
	public void initialize(URL arg, ResourceBundle arg1) {
		OfferObject.slideshowCount = 0;
		if (counter != data.size()) {
			OfferImage.setImage(data.get(counter).getImg());
			txtid.setText(data.get(counter).getSailingID());
			DoubleProperty mvw = OfferImage.fitWidthProperty();
			mvw.bind(Bindings.selectDouble(OfferImage.sceneProperty(), "width"));
			txtnights.setText(data.get(counter).getNights() + " Nights");
			txtdeparture.setText("Departs from " + data.get(counter).getDeparCountry() + ",");
			txtport.setText(data.get(counter).getDeparPort());
			txtfrom.setText(data.get(counter).getForm() + "$");
			txtshipname.setText(data.get(counter).getShipname());
			if (data.get(counter).getLast() < 10) {
				lbllast.setText("LAST " + data.get(counter).getLast() + " PLACES");

			} else
				lbllast.setVisible(false);
			counter++;
		}
	}

	public static void setCounter(int counter) {
		C_OfferObjectController.counter = counter;
	}

	public static void setData(ArrayList<OfferObject> data) {
		C_OfferObjectController.data = data;
		counter = 0;
	}

	@FXML
	private void handleOnMouseClicked(MouseEvent event) {



		System.out.println("mouse clicked");
		C_OffersController.SailingID = Integer.parseInt(txtid.getText());
		System.out.println("Sailing id is " + C_OffersController.SailingID );
		try {
			System.out.println("afkln");
			//Parent node = FXMLLoader.load(getClass().getResource("C_Book.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("C_Book.fxml"));
			Parent root = loader.load();
			Stage s = new Stage();
			s.initStyle(StageStyle.UNDECORATED);
			s.setScene(new Scene(root));
			s.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
