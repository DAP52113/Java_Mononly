package kadoufall.monopoly.card;

import java.util.ArrayList;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kadoufall.monopoly.location.Direction;
import kadoufall.monopoly.location.Player;

public class TaxInspectionCard extends Card {

	public static final String FORCING_THEM_TO_PAY_TAXES = "Forcing them to pay taxes on 30% of their savings";
	public static final String TAX_INSPECTION_CARD = "TaxInspectionCard";
	public static final String NOTICE = "Notice";
	public static final String OPPONENT_WITHIN_FIVE_MOVES_USE_FAILURE = "You have no opponent within five moves, use failure!";
	public static final String PAY_TAX_ON_30_OF_THEIR_DEPOSITS = "You have forced your opponent to pay tax on 30% of their deposits";

	public TaxInspectionCard() {
	}

	public String getInformation() {
		return FORCING_THEM_TO_PAY_TAXES;
	}

	public String getName() {
		return TAX_INSPECTION_CARD;
	}

	public boolean useCard(ArrayList<Point> points, Player player, ArrayList<Player> players) {
		boolean re = true;
		ArrayList<Player> opponent = new ArrayList<Player>();
		Point cell = player.getPoint().getPointAt(points, player.getPoint(), player.getDirection(), 0);
		for (int i = 0; i < cell.getLocations().size(); i++) {
			if (cell.getLocations().get(i) instanceof Player && cell.getLocations().get(i) != player) {
				opponent.add((Player) cell.getLocations().get(i));
			}
		}
		for (int i = 1; i < 6; i++) {
			cell = player.getPoint().getPointAt(points, player.getPoint(), player.getDirection(), i);
			for (int j = 0; j < cell.getLocations().size(); j++) {
				if (cell.getLocations().get(j) instanceof Player) {
					opponent.add((Player) cell.getLocations().get(j));
				}
			}
			cell = player.getPoint().getPointAt(points, player.getPoint(), Direction.negative(player.getDirection()),
					i);
			for (int j = 0; j < cell.getLocations().size(); j++) {
				if (cell.getLocations().get(j) instanceof Player) {
					opponent.add((Player) cell.getLocations().get(j));
				}
			}
		}
		int oppNum = opponent.size();
		if (oppNum == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(NOTICE);
			alert.setHeaderText(null);
			alert.setContentText(OPPONENT_WITHIN_FIVE_MOVES_USE_FAILURE);
			alert.showAndWait();
			re = false;
		} else {
			for (int i = 0; i < oppNum; i++) {
				double tax = opponent.get(i).getCash() * 0.3;
				opponent.get(i).addCash(-tax);
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(NOTICE);
			alert.setHeaderText(null);
			alert.setContentText(PAY_TAX_ON_30_OF_THEIR_DEPOSITS);
			alert.showAndWait();
		}
		return re;
	}

}
