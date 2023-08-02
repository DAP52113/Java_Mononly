package kadoufall.monopoly.location;

import kadoufall.monopoly.application.Point;

/**
 * Barricade
 */
public class Barricade extends Location {

	public Barricade(String name, Point point) {
		super(name, point);
	}

	@Override
	public void operation(Player player) {
		// do nothing
	}

}
