package kadoufall.monopoly.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import kadoufall.monopoly.location.Direction;
import kadoufall.monopoly.location.Player;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;

public class TurnCard extends Card {

	public static final String TURN_YOURSELF_OR_AN_OPPONENT_FIVE_STEPS = "Turn yourself or an opponent within five steps of you";
	public static final String TURN_CARD = "TurnCard";
	public static final String CHOICE = "Choice";
	public static final String TO_WHOM_TO_USE_THE_TURN_CARD = "To whom to use the TurnCard";
	public static final String MY_SELF = "MySelf";
	public static final String OPPONENT = "Opponent";
	public static final String CANCEL = "Cancel";
	public static final String NOTICE = "Notice";
	public static final String TURN_AROUND = "Turn around -";
	public static final String NO_OPPONENT_WITHIN_FIVE_MOVES_USE_FAILURE = "You have no opponent within five moves, use failure!";
	public static final String PLEASE_SELECT_AN_OPPONENT_BELOW = "Please select an opponent below";

	public TurnCard() {
	}

	public String getInformation() {
		return TURN_YOURSELF_OR_AN_OPPONENT_FIVE_STEPS;
	}

	public String getName() {
		return TURN_CARD;
	}

	public boolean useCard(ArrayList<Point> points, Player player, ArrayList<Player> players) {
		boolean re = true;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(CHOICE);
		alert.setHeaderText(null);
		alert.setContentText(TO_WHOM_TO_USE_THE_TURN_CARD);

		ButtonType buttonTypeOne = new ButtonType(MY_SELF);
		ButtonType buttonTypeTwo = new ButtonType(OPPONENT);
		ButtonType buttonTypeCancel = new ButtonType(CANCEL, ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			player.setDirection(Direction.negative(player.getDirection()));
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setTitle(NOTICE);
			alert1.setHeaderText(null);
			alert1.setContentText(TURN_AROUND + Direction.toDirection(player.getDirection()));
			alert1.showAndWait();
		} else if (result.get() == buttonTypeTwo) {
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
				cell = player.getPoint().getPointAt(points, player.getPoint(),
						Direction.negative(player.getDirection()), i);
				for (int j = 0; j < cell.getLocations().size(); j++) {
					if (cell.getLocations().get(j) instanceof Player) {
						opponent.add((Player) cell.getLocations().get(j));
					}
				}
			}
			int oppNum = opponent.size();
			if (oppNum == 0) {
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setTitle(NOTICE);
				alert1.setHeaderText(null);
				alert1.setContentText(NO_OPPONENT_WITHIN_FIVE_MOVES_USE_FAILURE);
				alert1.showAndWait();
				re = false;
			} else {
				List<String> choices = new ArrayList<>();
				for (Player p : opponent) {
					choices.add(p.getName());
				}
				ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
				dialog.setTitle(CHOICE);
				dialog.setHeaderText(null);
				dialog.setContentText(PLEASE_SELECT_AN_OPPONENT_BELOW);

				Optional<String> result1 = dialog.showAndWait();
				if (result1.isPresent()) {
					String choice = result1.get();

					for (Player p : opponent) {
						if (choice.equals(p.getName())) {
							p.setDirection(Direction.negative(p.getDirection()));
							Alert alert1 = new Alert(AlertType.INFORMATION);
							alert1.setTitle(NOTICE);
							alert1.setHeaderText(null);
							alert1.setContentText(
									"Success " + p.getName() + " Turn around for " + Direction.toDirection(p.getDirection()));
							alert1.showAndWait();
							break;
						}
					}
				}else{
					re = false;
				}
			}

		} else {
			re = false;
		}

		return re;
	}

}
