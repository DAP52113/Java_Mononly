package kadoufall.monopoly.card;

import java.util.ArrayList;

import kadoufall.monopoly.application.Point;
import kadoufall.monopoly.location.Player;

/**
 * Card interface
 */
public abstract class Card {
	public String information;
	public String name;

	public Card() {
	}

	public abstract String getName();

	public abstract String getInformation();

	public abstract boolean useCard(ArrayList<Point> points, Player player, ArrayList<Player> players);
}
