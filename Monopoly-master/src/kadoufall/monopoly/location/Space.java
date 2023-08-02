package kadoufall.monopoly.location;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Space extends Location {

	public static final String EMPTY = "empty";
	public static final String WELCOME = "welcome";

	public Space(Point point) {
		super(EMPTY, point);
	}

	@Override
	public void operation(Player player) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(WELCOME + name);
		alert.setHeaderText(null);
		alert.setContentText("Coming to the clearing! = _ =");
		alert.showAndWait();
	}

}
