package kadoufall.monopoly.stock;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import kadoufall.monopoly.location.*;

public class StockController implements Initializable {
	public static final String SELECT_OPERATION = "Please select operation";
	public static final String BUY = "Buy";
	public static final String SELL = "Sell";
	public static final String ERROR = "Error";
	public static final String INPUT_IS_INCORRECT = "Your input is incorrect!";
	public static final String A_NAME = "A";
	public static final String B_NAME = "B";
	public static final String C_NAME = "C";
	public static final String D_NAME = "D";
	// Player array, date, stock array
	private ArrayList<Player> players = null;
	private LocalDate date = null;
	private ArrayList<Stock> stocks = null;
	private Player currentPlayer = null;

	// Tables, line charts
	@FXML
	private TableView<Stock> tableView;
	@FXML
	private LineChart lineChart;

	//
	@FXML
	private CategoryAxis x;
	@FXML
	private NumberAxis y;

	// INIT
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Initialize player array, date, stock array, table
		players = StartStock.players;
		date = StartStock.date;
		stocks = StartStock.stocks;
		currentPlayer = StartStock.currentPlayer;
		tableView.setItems(FXCollections.observableArrayList(stocks));

		ObservableList<Stock> stocks = tableView.getItems();

		Stock clickedRow1 = stocks.get(0);
		XYChart.Series series1 = new XYChart.Series();
		series1.setName(clickedRow1.getName());
		Map<String, Double> trend1 = clickedRow1.getTrend();
		for (Map.Entry<String, Double> entry : trend1.entrySet()) {
			series1.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
		}
		lineChart.getData().addAll(series1);

		// Click Update chart and double click to trade
		tableView.setRowFactory(tv -> {
			TableRow<Stock> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (row.getItem() != null) {
					if (event.getClickCount() == 1) {
						lineChart.getData().clear();
						Stock clickedRow = row.getItem();
						XYChart.Series series = new XYChart.Series();
						series.setName(clickedRow.getName());
						Map<String, Double> trend = clickedRow.getTrend();
						for (Map.Entry<String, Double> entry : trend.entrySet()) {
                                                    System.out.println(entry.getKey());
							series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
						}
						lineChart.getData().addAll(series);
					}

					else if (event.getClickCount() == 2) {
						Stock stock = row.getItem();
						if (stock.getRate() == 10) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle(stock.getName());
							alert.setHeaderText(stock.getName());
							alert.setContentText(stock.getName() + "In the limit state, can not be traded!");
							alert.showAndWait();
						} else if (stock.getRate() == -10) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle(stock.getName());
							alert.setHeaderText(stock.getName());
							alert.setContentText(stock.getName() + "In the drop limit state, can not be traded!");
							alert.showAndWait();
						} else {
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle(stock.getName());
							alert.setHeaderText(stock.getName());
							alert.setContentText(SELECT_OPERATION);

							ButtonType buttonTypeOne = new ButtonType(BUY);
							ButtonType buttonTypeTwo = new ButtonType(SELL);
							ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

							alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == buttonTypeOne) {
								double maxBuy1 = currentPlayer.getCash() + currentPlayer.getDeposit();
								maxBuy1 /= stock.getPrice();
								int maxBuy = (int) maxBuy1;
								maxBuy = Math.min(maxBuy, stock.getInventory());
								TextInputDialog dialog = new TextInputDialog();
								dialog.setTitle(stock.getName());
								dialog.setHeaderText(null);
								dialog.setContentText("Cash : " + currentPlayer.getCash() + "Dollar\n Account: "
										+ currentPlayer.getDeposit() + "Dollar\n Remaining Stock: " + stock.getInventory()
										+ "stocks \n  The number of shares that can be purchased ranges :(0-" + maxBuy + "),The number of shares to be purchased");
								Optional<String> result1 = dialog.showAndWait();
								if (result1.isPresent()) {
									String buyNum = result1.get();
									try {
										int num = Integer.parseInt(buyNum);
										double numPrice = num * stock.getPrice();
										if (num > maxBuy) {
											Alert warn = new Alert(AlertType.WARNING);
											warn.setTitle(ERROR);
											warn.setHeaderText(null);
											warn.setContentText(INPUT_IS_INCORRECT);
											warn.showAndWait();
										} else {
											// Reduce cash deposits, average costs, and reduce inventory
											stock.addInventory(-num);
											currentPlayer.addStockNum(stock.getId(), num);
											if (numPrice <= currentPlayer.getCash()) {
												currentPlayer.addCash(-numPrice);

											} else {
												currentPlayer.addDeposit(-numPrice + currentPlayer.getCash());
												currentPlayer.addCash(-currentPlayer.getCash());
											}
											stock.addCost(num, stock.getPrice() * num);
											switch (currentPlayer.getName()) {
											case A_NAME:
												stock.setA(stock.getA() + num);
												break;
											case B_NAME:
												stock.setB(stock.getB() + num);
												break;
											case C_NAME:
												stock.setC(stock.getC() + num);
												break;
											case D_NAME:
												stock.setD(stock.getD() + num);
												break;
											}
											stock.changeAveCost();
											currentPlayer.addStockProperty(stock.getPrice() * num);
											Alert success = new Alert(AlertType.INFORMATION);
											success.setTitle(stock.getName());
											success.setHeaderText(stock.getName());
											success.setContentText("Buy Success!");
											success.showAndWait();

											for (int i = 0; i < tableView.getColumns().size(); i++) {
												tableView.getColumns().get(i).setVisible(false);
												tableView.getColumns().get(i).setVisible(true);
											}
										}
									} catch (Exception e) {
										Alert warn = new Alert(AlertType.WARNING);
										warn.setTitle(ERROR);
										warn.setHeaderText(null);
										warn.setContentText(INPUT_IS_INCORRECT);
										warn.showAndWait();
									}
								}
							} else if (result.get() == buttonTypeTwo) {
								int has = currentPlayer.getStockNum().get(stock.getId());
								TextInputDialog dialog = new TextInputDialog();
								dialog.setTitle(stock.getName());
								dialog.setHeaderText(null);
								dialog.setContentText("Your currently hold: " + has + "\n Please enter you want to sell:");
								Optional<String> result1 = dialog.showAndWait();
								if (result1.isPresent()) {
									String sellNum = result1.get();
									try {
										int num = Integer.parseInt(sellNum);
										if (num > has) {
											Alert warn = new Alert(AlertType.WARNING);
											warn.setTitle(ERROR);
											warn.setHeaderText(null);
											warn.setContentText(INPUT_IS_INCORRECT);
											warn.showAndWait();
										} else {
											currentPlayer.addStockNum(stock.getId(), -num);
											currentPlayer.addCash(num * stock.getPrice());
											stock.addInventory(num);
											switch (currentPlayer.getName()) {
											case A_NAME:
												stock.setA(stock.getA() - num);
												break;
											case B_NAME:
												stock.setB(stock.getB() - num);
												break;
											case C_NAME:
												stock.setC(stock.getC() - num);
												break;
											case D_NAME:
												stock.setD(stock.getD() - num);
												break;
											}
											currentPlayer.addStockProperty(-stock.getPrice() * num);

											Alert success = new Alert(AlertType.INFORMATION);
											success.setTitle(stock.getName());
											success.setHeaderText(stock.getName());
											success.setContentText("Sell Success!");
											success.showAndWait();

											for (int i = 0; i < tableView.getColumns().size(); i++) {
												tableView.getColumns().get(i).setVisible(false);
												tableView.getColumns().get(i).setVisible(true);
											}

										}
									} catch (Exception e) {
										Alert warn = new Alert(AlertType.WARNING);
										warn.setTitle(ERROR);
										warn.setHeaderText(null);
										warn.setContentText(INPUT_IS_INCORRECT);
										warn.showAndWait();
									}
								}
							} else {
							}
						}

					}
				}

			});
			return row;
		});
	}

}
