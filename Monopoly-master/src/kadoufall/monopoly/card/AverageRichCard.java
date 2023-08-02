package kadoufall.monopoly.card;

import java.util.ArrayList;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kadoufall.monopoly.location.Player;

public class AverageRichCard extends Card { // AverageRichCard

	public AverageRichCard() {
	}

	public String getInformation() {
		return "Divide everyone's cash equally";
	}

	public String getName() {
		return "AverageRichCard";
	}

	@Override
	public boolean useCard(ArrayList<Point> points, Player player, ArrayList<Player> players) {
		double averageCash = 0;
		for (int i = 0; i < players.size(); i++) {
			averageCash += players.get(i).getCash();
		}
		averageCash /= players.size();
		for (int i = 0; i < players.size(); i++) {
			players.get(i).addCash(-players.get(i).getCash() + averageCash);
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notice");
		alert.setHeaderText(null);
		alert.setContentText("Everyone's cash has been distributed equally！");
		alert.showAndWait();
		return true;
	}

}
