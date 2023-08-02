package kadoufall.monopoly.card;

import java.util.ArrayList;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kadoufall.monopoly.location.Direction;
import kadoufall.monopoly.location.Player;

public class PlunderCard extends Card { // PlunderCard

	public PlunderCard() { }

	public String getInformation() {
		return "Randomly claim one of your opponent's cards in five steps";
	}

	public String getName() {
		return "PlunderCard";
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
			alert.setTitle("Notice");
			alert.setHeaderText(null);
			alert.setContentText("You have no opponent within five steps, Use Failed！");
			alert.showAndWait();
			re = false;
		} else {
			String con = "";
			for (int i = 0; i < oppNum; i++) {
				int cardNum = opponent.get(i).getCardsList().size();
				if (cardNum > 0) {
					int choice = (int) (Math.random() * cardNum);
					Card card = opponent.get(i).getCardsList().get(choice);
					player.addCard(card);
					opponent.get(i).getCardsList().remove(card);
					con += "Get " + card.getName() + "\n";
				}
			}
			con += "You have randomly appropriated one of your opponent's cards as your own";
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notice");
			alert.setHeaderText(null);
			alert.setContentText(con);
			alert.showAndWait();
		}
		return re;
	}

}
