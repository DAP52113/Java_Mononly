package kadoufall.monopoly.location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import kadoufall.monopoly.application.Point;
import kadoufall.monopoly.card.AverageRichCard;
import kadoufall.monopoly.card.BuyCard;
import kadoufall.monopoly.card.Card;
import kadoufall.monopoly.card.Dice;
import kadoufall.monopoly.card.PlunderCard;
import kadoufall.monopoly.card.Roadblock;
import kadoufall.monopoly.card.TaxInspectionCard;
import kadoufall.monopoly.card.TurnCard;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;

public class Store extends Location {

	public static final int BUY_CARD_INIT_NUMBER = 7;
	public static final String WELCOME = "welcome";
	public static final String BUY = "Buy";

	public Store(String name, Point point) {
		super(name, point);
	}

	@Override
	public void operation(Player player) {
		Card[] buyCard = new Card[BUY_CARD_INIT_NUMBER];
		buyCard[0] = new TurnCard();
		buyCard[1] = new AverageRichCard();
		buyCard[2] = new Roadblock();
		buyCard[3] = new TaxInspectionCard();
		buyCard[4] = new BuyCard();
		buyCard[5] = new PlunderCard();
		buyCard[6] = new Dice();

		if (player.getCoupon() < 5) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(WELCOME + name);
			alert.setHeaderText(null);
			alert.setContentText("Your coupon is" + player.getCoupon() + "!\n" + "Each item requires 5 points. \n You are short of points！");
			alert.showAndWait();
		} else {
			String info = "";
			List<String> choices = new ArrayList<>();
			for (Card c : buyCard) {
				choices.add(c.getName());
				info += c.getName() + ":" + c.getInformation() + "\n";
			}
			ChoiceDialog<String> dialog = new ChoiceDialog<>(buyCard[0].getName(), choices);
			dialog.setTitle(WELCOME + name);
			dialog.setHeaderText("Your coupon is" + player.getCoupon() + "!\n" + "Each item requires 5 points.");
			dialog.setContentText(info + "Please select the item you want to purchase:");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				String choice = result.get();
				for (Card c : buyCard) {
					if (choice.equals(c.getName())) {
						player.addCard(c);
						player.addCoupon(-5);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle(WELCOME + name);
						alert.setHeaderText(null);
						alert.setContentText(BUY + c.getName() + "Success! Your voucher is left!" + player.getCoupon());
						alert.showAndWait();
						break;
					}
				}
				operation(player);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("welcome" + name);
				alert.setHeaderText(null);
				alert.setContentText(name + "Welcome to your coming again");
				alert.showAndWait();
			}

		}

	}

}
