package kadoufall.monopoly.location;

import java.util.Optional;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

/**
 * Lottery
 */
public class Lottery extends Location {

	public static final int MONEY_NUMBER = 1000;
	public static final String WELCOME = "Welcome";
	public static final String NOTICE = "Notice";
	public static final String ERROR = "Error";
	public static final String YOUR_INPUT_IS_INCORRECT = "Your input is incorrect";

	public Lottery(String name, Point point) {
		super(name, point);
	}

	@Override
	public void operation(Player player) {
		int money = MONEY_NUMBER;
		String header = "";
		header = "A lottery ticket fixed price of 10 Dollar, the winning amount" + money + "Dollar\n";
		header += "Today the following lottery tickets are open for purchase, each lottery number is as follows:\n";
		String[] lotArray = new String[10];
		for (int i = 0; i < 10; i++) {
			String lotNum = String.valueOf((int) (Math.random() * 10000) + 1)
					+ String.valueOf((int) (Math.random() * 10)) + String.valueOf((int) (Math.random() * 10000));
			lotArray[i] = lotNum;
			header += "The  " + i + "  Note： " + lotNum + "\n";
		}

		int win = (int) (Math.random() * 10);

		core(header, win, money, lotArray, player);
	}

	private void core(String header, int win, int money, String[] lotArray, Player player) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(WELCOME +" "+ name);
		dialog.setHeaderText(header);
		dialog.setContentText("Please select the number of notes you want to buy, enter (0-9)");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			String choice = result.get();
			try {
				int chose = Integer.parseInt(choice);
				if (chose >= 0 && chose <= 9) {
					player.addCash(-10);
					if (chose == win) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle(NOTICE);
						alert.setHeaderText(null);
						alert.setContentText("Congratulations! Get " + money + "Dollar!");
						alert.showAndWait();
						player.addCash(money);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle(NOTICE);
						alert.setHeaderText(null);
						alert.setContentText("The winning numbers are " + lotArray[win] + "\n I'm sorry you didn't win. Thank you!");
						alert.showAndWait();
					}
				} else {
					Alert warn = new Alert(AlertType.WARNING);
					warn.setTitle(ERROR);
					warn.setHeaderText(null);
					warn.setContentText(YOUR_INPUT_IS_INCORRECT);
					warn.showAndWait();
					core(header, win, money, lotArray, player);
				}
			} catch (Exception ex) {
				Alert warn = new Alert(AlertType.WARNING);
				warn.setTitle(ERROR);
				warn.setHeaderText(null);
				warn.setContentText(YOUR_INPUT_IS_INCORRECT);
				warn.showAndWait();
				core(header, win, money, lotArray, player);
			}
		}
	}

}
