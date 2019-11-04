package boundary;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

public class trysomethingController implements Initializable {
	@FXML
	private BorderPane bp;
	@FXML
	private LineChart<String, Number> linechart;
	@FXML
	private ComboBox<Integer> comboyears = new ComboBox<>();

	private HashMap<String, Integer> hm1 = new HashMap<>();
	private HashMap<String, Integer> hm2 = new HashMap<>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		inihm();
		inicb();

		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Month");

		linechart = new LineChart<String, Number>(xAxis, yAxis);

		linechart.setTitle("Income Monitoring, 2019");

		XYChart.Series series1 = new XYChart.Series();

		series1.setName("Profit from Standart rooms");
		// populating the series with data
		for (String key : hm1.keySet())
			series1.getData().add(new XYChart.Data(key, hm1.get(key)));

		linechart.getData().add(series1);

		XYChart.Series series2 = new XYChart.Series();

		series2.setName("Profit from Suite rooms");
		// populating the series with data
		for (String key : hm2.keySet())
			series2.getData().add(new XYChart.Data(key, hm2.get(key)));

		linechart.getData().add(series2);
		bp.setCenter(linechart);

	}

	private void inicb() {
		Date d = new Date();
		int curryear = d.getYear();

		comboyears.getItems().addAll(curryear, curryear + 1, curryear + 2, curryear + 3, curryear + 4);
	}

	private void inihm() {
		hm1.put("Jan", 23);
		hm1.put("Feb", 14);
		hm1.put("Mar", 15);
		hm1.put("Apr", 24);
		hm1.put("May", 34);
		hm1.put("Jun", 36);
		hm1.put("Jul", 22);
		hm1.put("Aug", 45);
		hm1.put("Sep", 43);
		hm1.put("Oct", 17);
		hm1.put("Nov", 29);
		hm1.put("Dec", 25);

		hm2.put("Jan", 19);
		hm2.put("Feb", 24);
		hm2.put("Mar", 25);
		hm2.put("Apr", 30);
		hm2.put("May", 30);
		hm2.put("Jun", 45);
		hm2.put("Jul", 37);
		hm2.put("Aug", 50);
		hm2.put("Sep", 55);
		hm2.put("Oct", 23);
		hm2.put("Nov", 32);
		hm2.put("Dec", 30);
	}
}
