package kadoufall.monopoly.card;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kadoufall.monopoly.location.Location;
import kadoufall.monopoly.location.Player;

/**
 * GiveCard
 */
public class GiveCard extends Location {

	// card info final
	public static final String HERE_S_A_TURN_CARD_FOR_YOU = "Here's a turn card for you！";
	public static final String HERE_S_A_AVERAGE_RICH_CARD_FOR_YOU = "Here's a averageRichCard for you！";
	public static final String HERE_S_A_TAX_CARD_FOR_YOU = "Here's a tax card for you !";
	public static final String HERE_S_A_GRAB_CARD_FOR_YOU = "Here's a grab card for you !";
	public static final String HERE_S_A_BUY_CARD_FOR_YOU = "Here's a buy card for you !";
	public static final String HERE_S_A_CONTROL_DICE_FOR_YOU = "Here's a control dice for you！";
	public static final String HERE_S_A_ROADBLOCK_FOR_YOU = "Here's a roadblock for you !";
	public static final String WELCOME = "Welcome";

	public GiveCard(String name, Point point) {
		super(name, point);
	}


	@Override
	public void operation(Player player) {
		String str = "";

		int random = (int) (Math.random() * 7);
		switch (random) {
		case 0:
			player.addCard(new TurnCard());
			str = HERE_S_A_TURN_CARD_FOR_YOU;
			break;
		case 1:
			player.addCard(new AverageRichCard());
			str = HERE_S_A_AVERAGE_RICH_CARD_FOR_YOU;
			break;
		case 2:
			player.addCard(new TaxInspectionCard());
			str = HERE_S_A_TAX_CARD_FOR_YOU;
			break;
		case 3:
			player.addCard(new PlunderCard());
			str = HERE_S_A_GRAB_CARD_FOR_YOU;
			break;
		case 4:
			player.addCard(new BuyCard());
			str = HERE_S_A_BUY_CARD_FOR_YOU;
			break;
		case 5:
			player.addCard(new Dice());
			str = HERE_S_A_CONTROL_DICE_FOR_YOU;
			break;
		case 6:
			player.addCard(new Roadblock());
			str = HERE_S_A_ROADBLOCK_FOR_YOU;
			break;
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(WELCOME + name);
		alert.setHeaderText(null);
		alert.setContentText(str);
		alert.showAndWait();
	}

}
