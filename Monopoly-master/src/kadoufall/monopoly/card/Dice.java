package kadoufall.monopoly.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;
import kadoufall.monopoly.location.Player;

/**
 * Dice
 */
public class Dice extends Card { // Dice
	public Dice() {
	}

	public String getName() {
		return "Remote Dice";
	}

	public String getInformation() {
		return "Control the result of the Dice(1-6)";
	}

	public boolean useCard(ArrayList<Point> points, Player player, ArrayList<Player> players) {
		List<String> choices = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			choices.add(String.valueOf(i));
		}
		ChoiceDialog<String> dialog = new ChoiceDialog<>(String.valueOf(1), choices);
		dialog.setTitle("Notice");
		dialog.setHeaderText(null);
		dialog.setContentText("Which step would you like to roll");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			String choice1 = result.get();
			int choice = Integer.parseInt(choice1);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notice");
			alert.setHeaderText(null);
			alert.setContentText("The next thing I'm gonna shake is:" + choice);
			alert.showAndWait();
			player.setStep(choice);
			player.setUseDice(true);
		}
		return true;
	}

}
