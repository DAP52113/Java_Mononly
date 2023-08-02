package kadoufall.monopoly.location;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Hospital extends Location {

	public static final String WELCOME = "welcome";
	public static final String NOT_SICK = "I'm not sick";

	public Hospital(String name, Point point) {
		super(name, point);
	}

	@Override
	public void operation(Player player) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(WELCOME + name);
		alert.setHeaderText(null);
		alert.setContentText(NOT_SICK);
		alert.showAndWait();
	}

}
