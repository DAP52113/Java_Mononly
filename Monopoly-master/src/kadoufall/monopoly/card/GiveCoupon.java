package kadoufall.monopoly.card;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kadoufall.monopoly.location.Location;
import kadoufall.monopoly.location.Player;

public class GiveCoupon extends Location {

	//info
	public static final String POINTS_FOR_YOU = "25 points for you！";
	public static final String WELCOME = "Welcome";

	public GiveCoupon(String name, Point point) {
		super(name, point);
	}

	@Override
	public void operation(Player player) {
		player.addCoupon(25);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(WELCOME + name);
		alert.setHeaderText(null);
		alert.setContentText(POINTS_FOR_YOU);
		alert.showAndWait();
	}

}
