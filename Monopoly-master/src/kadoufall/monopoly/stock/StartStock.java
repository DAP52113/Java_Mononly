package kadoufall.monopoly.stock;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kadoufall.monopoly.location.Player;

/**
 * StartStock
 */
public class StartStock extends Application {
	public static final String STOCK_MARKET = "Stock Market";
	public static final String STOCK_FXML = "Stock.fxml";
	static ArrayList<Player> players = null;
	static LocalDate date = null;
	static ArrayList<Stock> stocks = null;
	static Player currentPlayer = null;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(STOCK_MARKET);
		Pane myPane = (Pane) FXMLLoader.load(getClass().getResource(STOCK_FXML));
		Scene myScene = new Scene(myPane);
		primaryStage.setScene(myScene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

	public StartStock(Player currentPlayer, ArrayList<Player> players, LocalDate date, ArrayList<Stock> stocks) {
		this.players = players;
		this.date = date;
		this.stocks = stocks;
		this.currentPlayer = currentPlayer;
	}

}
