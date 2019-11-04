package boundary;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import control.Control;
import entity.HistoryObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class C_ItemController implements Initializable {

	private ArrayList<HistoryObject> data;
	public static int counter = 0;
	@FXML
	private Text sDate;
	@FXML
	private Text rDate;
	@FXML
	private Text shipname;
	@FXML
	private Text price;
	@FXML
	private Text roomNum;

	@Override
	public void initialize(URL arg, ResourceBundle arg1) {
		Control cp = Control.getInstance();
		data = cp.getPastOrdersByPerson(U_ViewLogic.currentUserID);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String from = df.format(data.get(counter).getStaringDate()).toString();
		String to = df.format(data.get(counter).getReturnDate()).toString();
		sDate.setText(from);
		rDate.setText(to);
		shipname.setText(data.get(counter).getShipName());
		roomNum.setText(data.get(counter).getRoomNumber()+"");
		price.setText(data.get(counter).getPrice()+"");
		counter++;
	}

}
