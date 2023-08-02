package kadoufall.monopoly.location;

public enum Direction {
	cw, ccw; // clockwise,anticlockwise

	public static final String CLOCKWISE = "Clockwise";
	public static final String ANTICLOCKWISE = "Anticlockwise";

	public static String toDirection(Direction direction) {
		String re = "";
		switch (direction) {
		case cw:
			re = CLOCKWISE;
			break;
		case ccw:
			re = ANTICLOCKWISE;
			break;
		}
		return re;
	}

	public static Direction negative(Direction direction) {
		switch (direction) {
		case cw:
			return ccw;
		case ccw:
			return cw;
		default:
			return cw;// useless
		}
	}

}
