package boundary;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

import Utils.Utilities;
import control.Control;
import entity.popularDest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class A_DashboardController implements Initializable {

	@FXML
	private Text txtTotalProfit;
	@FXML
	private Text txtTotalOrders;
	@FXML
	private Text txtTotalUsers;
	@FXML
	private Button btnOverview;
	@FXML
	private Button btnOrders;
	@FXML
	private Button btnOffers;
	@FXML
	private Button btnSettings;
	@FXML
	private Button btnSignout;
	@FXML
	private final ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
	@FXML
	private final ObservableList<PieChart.Data> details2 = FXCollections.observableArrayList();
	private final CategoryAxis xAxis = new CategoryAxis();

	private final NumberAxis yAxis = new NumberAxis();

	private LineChart<String, Number> linechart;

	private HashMap<Integer, Double> hm1 = new HashMap<>();
	private HashMap<Integer, Double> hm2 = new HashMap<>();
	private HashMap<String, Double> hm3 = new HashMap<>();
	private HashMap<String, Integer> hm4 = new HashMap<>();
	@SuppressWarnings("deprecation")
	int year = new Date().getYear() + 1900;

	@FXML
	private GridPane dashboardGridPane;
	@FXML
	private BorderPane bplandscape1;
	@FXML
	private BorderPane bplandscape2;
	@FXML
	private BorderPane bplandscape3;
	@FXML
	private BorderPane bplandscape4;
	@FXML
	private BorderPane bplandscape5;
	@FXML
	private BorderPane bplandscape6;
	@FXML
	private BorderPane bplandscape7;
	@FXML
	private BorderPane bpUsers;
	@FXML
	private BorderPane bpOrders;

	XYChart.Series series1;
	XYChart.Series series2;

	@FXML
	private final ObservableList<PieChart.Data> details3 = FXCollections.observableArrayList();
	@FXML
	private final ObservableList<PieChart.Data> details4 = FXCollections.observableArrayList();
	@FXML
	private final ObservableList<PieChart.Data> details5 = FXCollections.observableArrayList();
	@FXML
	private final ObservableList<PieChart.Data> details6 = FXCollections.observableArrayList();

	private PieChart mostVisitedCities;
	@FXML
	private Button btnExit;

	private PieChart mostPopularShips;
	private HashMap<Integer, Double> hm5;
	private HashMap<Integer, Double> hm6;

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inihm();

		txtTotalProfit.setText(Control.getInstance().getIncome() + "");
		txtTotalUsers.setText(Control.getInstance().numOfUsers() + "");
		txtTotalOrders.setText(Control.getInstance().numberOfOrders() + "");

		// ---------------------------Popular Destinations In Last 5
		// Years-----------------------------------

		Control cp = Control.getInstance();
		HashMap<Integer, ArrayList<popularDest>> a = cp.popularDest();
		final CategoryAxis xAxisPopDest = new CategoryAxis();
		final NumberAxis yAxisPopDest = new NumberAxis();
		final BarChart<String, Number> bc = new BarChart<>(xAxisPopDest, yAxisPopDest);
		bc.setTitle("Popular Destinations In Last 5 Years");
		xAxisPopDest.setLabel("Destination");
		yAxisPopDest.setLabel("Number Of People");
		bc.getStylesheets().add("boundary/U_barChart.css");

		for (Integer popularDest : a.keySet()) {
			XYChart.Series populardestSeries = new XYChart.Series();
			populardestSeries.setName(popularDest + "");
			for (popularDest data : a.get(popularDest)) {
				populardestSeries.getData()
						.add(new XYChart.Data(data.getpName() + " , " + data.getcName(), data.getCount()));
			}
			bc.getData().add(populardestSeries);
		}

		bplandscape1.setCenter(bc);

		// -------------------------------Most Visited
		// Countries-------------------------------

		for (String key : hm3.keySet()) {
			Double value = hm3.get(key);
			details3.add(new PieChart.Data(key, value));
		}
		mostVisitedCities = new PieChart();
		mostVisitedCities.setTitle("Most Visited Countries");
		mostVisitedCities.getStylesheets().add("boundary/U_pieChart.css");
		mostVisitedCities.setData(details3);
		mostVisitedCities.setLegendSide(Side.BOTTOM);
		mostVisitedCities.setLabelsVisible(true);
		bplandscape2.setCenter(mostVisitedCities);

		// ----------------------------Popular
		// Ships----------------------------------

		for (String key : hm4.keySet()) {
			Integer value = hm4.get(key);
			details4.add(new PieChart.Data(key, value));
		}
		mostPopularShips = new PieChart();
		mostPopularShips.setTitle("Most Popular Ships");
		mostPopularShips.getStylesheets().add("boundary/U_pieChart.css");
		mostPopularShips.setData(details4);
		mostPopularShips.setLegendSide(Side.BOTTOM);
		mostPopularShips.setLabelsVisible(true);
		bplandscape3.setCenter(mostPopularShips);

		// ----------------------------Income Monitoring
		// 2019----------------------------------

		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Month");
		linechart = new LineChart<String, Number>(xAxis, yAxis);
		linechart.setTitle("Income Monitoring, " + year);
		series1 = new XYChart.Series();
		series1.setName("Profit from Standart rooms");
		// populating the series with data
		for (Integer key : hm1.keySet())
			series1.getData().add(new XYChart.Data(key + "", hm1.get(key)));
		linechart.getData().add(series1);
		series2 = new XYChart.Series();
		series2.setName("Profit from Suite rooms");
		// populating the series with data
		for (Integer key : hm2.keySet())
			series2.getData().add(new XYChart.Data(key + "", hm2.get(key)));
		linechart.getData().add(series2);
		bplandscape4.setCenter(linechart);
		linechart.getStylesheets().add("boundary/U_barChart.css");

		// ---------------------------Income Monitoring
		// 2018-----------------------------------

		xAxis = new CategoryAxis();
		yAxis = new NumberAxis();
		xAxis.setLabel("Month");
		linechart = new LineChart<String, Number>(xAxis, yAxis);
		linechart.setTitle("Income Monitoring, " + (year - 1));
		series1 = new XYChart.Series();
		series1.setName("Profit from Standart rooms");
		// populating the series with data
		for (Integer key : hm5.keySet())
			series1.getData().add(new XYChart.Data(key + "", hm5.get(key)));
		linechart.getData().add(series1);
		series2 = new XYChart.Series();
		series2.setName("Profit from Suite rooms");
		// populating the series with data
		for (Integer key : hm6.keySet())
			series2.getData().add(new XYChart.Data(key + "", hm6.get(key)));
		linechart.getData().add(series2);
		bplandscape5.setCenter(linechart);
		linechart.getStylesheets().add("boundary/U_barChart.css");

		// -------------------------VIP CUSTOMERS VS NON
		// CUSTOMERS-------------------------------------

		int vips = cp.VIPcustomer();
		int count = cp.selectPerson().size();
		int notVip = count - vips;
		Data pieItem1 = new PieChart.Data("NOT VIP's - " + ((double) notVip / count), notVip);
		Data pieItem2 = new PieChart.Data("VIP's - " + ((double) vips / count), vips);
		details5.add(pieItem1);
		details5.add(pieItem2);
		mostPopularShips = new PieChart();
		mostPopularShips.setTitle(" VIP's - Two Orders Above 5,000 ");
		mostPopularShips.getStylesheets().add("boundary/U_pieChart.css");
		mostPopularShips.setData(details5);
		mostPopularShips.setLegendSide(Side.BOTTOM);
		mostPopularShips.setLabelsVisible(true);
		bplandscape6.setCenter(mostPopularShips);

		// -----------------------area-chart---------------------------

		NumberAxis xAxiss = new NumberAxis(1, 12, 1);
		NumberAxis yAxiss = new NumberAxis();
		final AreaChart<Number, Number> ac = new AreaChart<Number, Number>(xAxiss, yAxiss);
		ac.setTitle("Rooms Congestion By Years");

		HashMap<Integer, HashMap<Integer, Double>> outerHM = cp.roomCongestion();
		for (Integer yearinmap : outerHM.keySet()) {
			XYChart.Series seriesInMAp = new XYChart.Series();
			seriesInMAp.setName(yearinmap + "");
			HashMap<Integer, Double> meshtane = outerHM.get(yearinmap);
			for (Integer monthinHM : meshtane.keySet()) {
				seriesInMAp.getData().add(new XYChart.Data(monthinHM, meshtane.get(monthinHM)));
			}
			ac.getData().add(seriesInMAp);
		}
		bplandscape7.setCenter(ac);

	}

	@FXML
	public void clearClick() {

		btnOverview.setStyle("-fx-background-color : #49BDEC");
		btnOrders.setStyle("-fx-background-color : #49BDEC");
		btnOffers.setStyle("-fx-background-color : #49BDEC");
		btnSettings.setStyle("-fx-background-color : #49BDEC");
		btnSignout.setStyle("-fx-background-color : #49BDEC");

		btnOverview.setDisable(false);
		btnOrders.setDisable(false);
		btnOffers.setDisable(false);
		btnSettings.setDisable(false);
		btnSignout.setDisable(false);
	}

	@FXML
	public void handleClicks(ActionEvent actionEvent) {

		clearClick();

		if (actionEvent.getSource() == btnOverview) {
			btnOverview.setStyle("-fx-background-color : #a7c853");
		}
		if (actionEvent.getSource() == btnOrders) {
			btnOrders.setStyle("-fx-background-color : #a7c853");
		}
		if (actionEvent.getSource() == btnOffers) {

			btnOffers.setStyle("-fx-background-color : #a7c853");
		}
		if (actionEvent.getSource() == btnSettings) {
			btnSettings.setStyle("-fx-background-color : #a7c853");
		}
		if (actionEvent.getSource() == btnSignout) {
			btnSignout.setStyle("-fx-background-color : #a7c853");

		}
	}

	class ItemChangeListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				Object item = event.getItem();
				// do something with object
			}
		}
	}

	@FXML
	public void handleOnMouseEntered(MouseEvent actionEvent) {
		if (actionEvent.getSource() == btnExit) {
			btnExit.setStyle("-fx-background-color : #a7c853");
		}
	}

	@FXML
	public void handleOnMouseExited(MouseEvent actionEvent) {
		btnExit.setStyle("-fx-background-color : #49BDEC");
	}

	@FXML
	public void handleExit(MouseEvent actionEvent) {
		((Stage) btnExit.getScene().getWindow()).close();
	}

	private void inihm() {
		hm1 = Control.getInstance().profitFromStandard(year);
		hm2 = Control.getInstance().profitFromSuite(year);
		hm3 = Control.getInstance().getMostVisitedCountries();
		hm4 = Control.getInstance().getMostPopularShips();
		hm5 = Control.getInstance().profitFromStandard(year - 1);
		hm6 = Control.getInstance().profitFromSuite(year - 1);

	}

	@FXML
	public void glow(MouseEvent mouse) {
		BorderPane b = (BorderPane) mouse.getSource();
		DropShadow borderGlow = new DropShadow();
		borderGlow.setColor(Color.rgb(26, 57, 76));
		borderGlow.setHeight(83.52);
		borderGlow.setRadius(43.6075);
		borderGlow.setSpread(0.6);
		borderGlow.setWidth(92.91);
		b.setEffect(borderGlow);

	}

	@FXML
	public void clearGlow(MouseEvent mouse) {
		BorderPane b = (BorderPane) mouse.getSource();
		b.setEffect(null);

	}

}
