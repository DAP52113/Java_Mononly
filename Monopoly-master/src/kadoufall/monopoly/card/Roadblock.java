package kadoufall.monopoly.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;
import kadoufall.monopoly.location.Barricade;
import kadoufall.monopoly.location.Direction;
import kadoufall.monopoly.location.Player;

public class Roadblock extends Card {

	public static final String ROADBLOCK_WILL_STOP_IS_AND = "Any player who passes a roadblock will stop is and cannot move forward";
	public static final String ROADBLOCK = "Roadblock";
	public static final String CHOICE = "Choice";
	public static final String PLACE_THE_ROADBLOCK = "place the Roadblock";
	public static final String NOTICE = "Notice";
	public static final String ADD_ROAD_LOCK_SUCCESS = "Add RoadLock Success！";

	public Roadblock() {
	}

	public String getInformation() {
		return ROADBLOCK_WILL_STOP_IS_AND;
	}

	public String getName() {
		return ROADBLOCK;
	}

	public boolean useCard(ArrayList<Point> points, Player player, ArrayList<Player> players) {
		List<String> choices = new ArrayList<>();
		for (int i = -8; i < 9; i++) {
			choices.add(String.valueOf(i));
		}
		ChoiceDialog<String> dialog = new ChoiceDialog<>(String.valueOf(1), choices);
		dialog.setTitle(CHOICE);
		dialog.setHeaderText(null);
		dialog.setContentText(PLACE_THE_ROADBLOCK);

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			int choice = Integer.parseInt(result.get());
			Point cell = player.getPoint().getPointAt(points, player.getPoint(), player.getDirection(), choice);
			if (choice < 0) {
				cell = player.getPoint().getPointAt(points, player.getPoint(),
						Direction.negative(player.getDirection()), -choice);
			}
			Barricade ba = new Barricade("", cell);
			cell.addLocation(ba);
			cell.setIcon();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(NOTICE);
			alert.setHeaderText(null);
			alert.setContentText(ADD_ROAD_LOCK_SUCCESS);
		}
		return true;
	}

}
