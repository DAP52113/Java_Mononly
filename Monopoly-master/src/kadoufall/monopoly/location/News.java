package kadoufall.monopoly.location;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

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

public class News extends Location {

	public static final String WELCOME = "WELCOME";
	public static final String BIG_NEWS = "Big News！！！";

	public News(String name, Point point) {
		super(name, point);
	}

	@Override
	public void operation(Player player) {
		// do nothing

	}

	public void operation(Player player, ArrayList<Player> players) {
		String con = "";

		int chance = (int) (Math.random() * 6);
		switch (chance) {
		case 0:
			double money = Double.valueOf(Math.floor(new Random().nextDouble() * 1000));
			Player max = players.stream().max((p, p1) -> (p.getHouseNum() - p1.getHouseNum())).get();
			max.addCash(money);
			con += max.getName() + "has the most number of properties" + money + " Cash！";
			break;
		case 1:
			double money1 = Double.valueOf(Math.floor(new Random().nextDouble() * 1000));
			Player min = players.stream().min((p, p1) -> (p.getHouseNum() - p1.getHouseNum())).get();
			con += min.getName() + "has the fewest properties" + money1 + " Cash！";
			min.addCash(money1);
			break;
		case 2:
			con += "Everyone gets 10% of the deposit";
			Consumer<Player> giveMoney = e -> e.addDeposit(e.getDeposit() * 0.1);
			players.forEach(giveMoney);
			break;
		case 3:
			con += "Tax actively, everyone pays 10% property tax";
			Consumer<Player> giveMoney1 = e -> e.addDeposit(-e.getDeposit() * 0.1);
			players.forEach(giveMoney1);
			break;
		case 4:
			Card card = new TurnCard();
			String str = "Everyone gets a steering piece";
			int random = (int) (Math.random() * 7);
			switch (random) {
			case 0:
				break;
			case 1:
				card = new AverageRichCard();
				str = "Everyone gets a Grant Thornton card";
				break;
			case 2:
				card = new TaxInspectionCard();
				str = "Everyone gets a Tax card";
				break;
			case 3:
				card = new PlunderCard();
				str = "Everyone gets a Get card";
				break;
			case 4:
				card = new BuyCard();
				str = "Everyone gets a Buy Land card";
				break;
			case 5:
				card = new Dice();
				str = "Everyone gets a remote control die";
				break;
			case 6:
				card = new Roadblock();
				str = "Everyone gets a roadblock";
				break;
			}
			con += str;
			for (int i = 0; i < players.size(); i++) {
				players.get(i).addCard(card);
			}
			break;
		case 5:
			con += "Hurt.....\nForced to stay in the hospital for two rounds \n continue the game counterclockwise after two rounds";
			player.setHurt(true);
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(WELCOME + name);
		alert.setHeaderText(BIG_NEWS);
		alert.setContentText(con);
		alert.showAndWait();
	}
}
