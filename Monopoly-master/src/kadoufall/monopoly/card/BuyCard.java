package kadoufall.monopoly.card;

import java.util.ArrayList;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kadoufall.monopoly.location.Land;
import kadoufall.monopoly.location.Location;
import kadoufall.monopoly.location.Player;

public class BuyCard extends Card { // BuyCard

	public BuyCard() {

	}

	public String getName() {
		return "BuyCard";
	}

	public String getInformation() {
		return "Forcibly buying land in their current location at the current price!";
	}

	public boolean useCard(ArrayList<Point> points, Player player, ArrayList<Player> players) {
		boolean re = true;
		boolean hasLand = false;
		int num = -1;

		ArrayList<Location> loc = player.getPoint().getLocations();

		for (int i = 0; i < loc.size(); i++) {
			if (loc.get(i) instanceof Land) {
				hasLand = true;
				num = i;
			}
		}
		if (hasLand) {
			Land land = (Land) loc.get(num);
			if (land.getOwner() == player) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Notice");
				alert.setHeaderText(null);
				alert.setContentText("Use Failed！The present house is owns！");
				alert.showAndWait();
				re = false;
			} else {
				if (land.getPrice() > player.getCash()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Notice");
					alert.setHeaderText(null);
					alert.setContentText("Use Failed！Cash is not enough to buy a house！");
					alert.showAndWait();
					re = false;
				} else {
					if (land.getOwner() == null) {
						land.setLevel(1);
						land.setOwner(player);
						player.addHouseNum(1);
						player.addLands(land);
						player.addCash(-land.getPrice());
					} else {
						land.getOwner().getLands().remove(land);
						land.getOwner().addHouseNum(-1);
						land.setOwner(player);
						player.addHouseNum(1);
						player.addCash(-land.getPrice());
						player.addLands(land);
					}
					land.changeIcon();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Notice");
					alert.setHeaderText(null);
					alert.setContentText("Use Success，You get " + land.getName());
					alert.showAndWait();
				}
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notice");
			alert.setHeaderText(null);
			alert.setContentText("The current location is not a house,not buy！");
			alert.showAndWait();
			re = false;
		}
		return re;
	}

}
