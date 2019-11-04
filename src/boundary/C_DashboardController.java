package boundary;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import control.Control;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class C_DashboardController implements Initializable {

	@FXML
	private Label totalOrders;
	@FXML
	private Label countriesVisited;
	@FXML
	private Label portsLanded;
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
	@FXML
	private PieChart mostVisitedCities;
	@FXML
	private Button btnExit;
	@FXML
	private PieChart mostPopularShips;
	@FXML
	private GridPane dashboardGridPane;
	@FXML
	private BorderPane bp;
	@FXML
	private BorderPane bp1;
	@FXML
	private BorderPane bp2;


	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		Control cp = Control.getInstance();
		totalOrders.setText("" + cp.getNumOfPastOrders(U_ViewLogic.currentUserID));
		countriesVisited.setText("" + cp.getNumOfPastOrders(U_ViewLogic.currentUserID));
		portsLanded.setText("" + cp.getNumOfPortsLanded(U_ViewLogic.currentUserID));
		HashMap<String, Double> hm = cp.getMostVisitedCountries();

		for (String key : hm.keySet()) {
			Double value = hm.get(key);
			details.add(new PieChart.Data(key, value));
		}

		mostVisitedCities = new PieChart();
		mostVisitedCities.setTitle("Most Visited Countries");
		mostVisitedCities.getStylesheets().add("boundary/U_pieChart.css");
		mostVisitedCities.setData(details);
		mostVisitedCities.setLegendSide(Side.BOTTOM);
		mostVisitedCities.setLabelsVisible(true);
		bp.setCenter(mostVisitedCities);
		HashMap<String, Integer> hm2 = cp.getMostPopularShips();

		for (String key : hm2.keySet()) {
			Integer value = hm2.get(key);
			details2.add(new PieChart.Data(key, value));
		}

		mostPopularShips = new PieChart();
		mostPopularShips.setTitle("Most Popular Ships");
		mostPopularShips.getStylesheets().add("boundary/U_pieChart.css");
		mostPopularShips.setData(details2);
		mostPopularShips.setLegendSide(Side.BOTTOM);
		mostPopularShips.setLabelsVisible(true);
		bp1.setCenter(mostPopularShips);

		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Month");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Amount");

		BarChart barChart = new BarChart(xAxis, yAxis);
		XYChart.Series dataSeries1 = new XYChart.Series();
		dataSeries1.setName((new Date().getYear() + 1900) + "");
		HashMap<String, Double> hm3 = cp.getAmountPayed(U_ViewLogic.currentUserID);

		for (String key : hm3.keySet()) {
			Double value = hm3.get(key);
			dataSeries1.getData().add(new XYChart.Data(key, value));
		}

		barChart.getData().add(dataSeries1);
		barChart.setTitle("Amount You Payed By Month");
		barChart.getStylesheets().add("boundary/U_barChart.css");
		bp2.setCenter(barChart);

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

}
