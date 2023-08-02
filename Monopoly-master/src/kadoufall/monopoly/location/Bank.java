package kadoufall.monopoly.location;

import java.util.Optional;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class Bank extends Location {

	public static final String WELCOME = "Welcome";
	public static final String SELECT_OPERATION = "Please select operation, exit Please select Cancel";
	public static final String SAVE = "Save";
	public static final String WITHDRAW = "Withdraw";
	public static final String CANCEL = "Cancel";
	public static final String NOTICE = "Notice";
	public static final String WELCOME_TO_YOUR_COMING_AGAIN = "welcome to your coming again！";
	public static final String CASH = "Cash: ";
	public static final String SAVE_SUCCESS = "Save Success";
	public static final String Dollar = "Dollar";
	public static final String YOUR_INPUT_IS_INCORRECT = "Your input is incorrect";
	public static final String ERROR = "Error";
	public static final String SUCCESSFUL_WITHDRAWAL = "Successful withdrawal";

	public Bank(String name, Point point) {
		super(name, point);
	}

	public void operation(Player player) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(WELCOME + name);
		alert.setHeaderText(null);
		alert.setContentText(SELECT_OPERATION);
		ButtonType buttonTypeOne = new ButtonType(SAVE);
		ButtonType buttonTypeTwo = new ButtonType(WITHDRAW);
		ButtonType buttonTypeCancel = new ButtonType(CANCEL, ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			saveMoney(player);
			operation(player);
		} else if (result.get() == buttonTypeTwo) {
			withdrawalMoney(player);
			operation(player);
		} else {
			Alert quit = new Alert(AlertType.INFORMATION);
			quit.setTitle(NOTICE);
			quit.setHeaderText(null);
			quit.setContentText(name + WELCOME_TO_YOUR_COMING_AGAIN);
			quit.showAndWait();
		}
	}

	private void saveMoney(Player player) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(WELCOME + name);
		dialog.setHeaderText(null);
		dialog.setContentText(CASH + player.getCash() + "\n Please enter the amount you wish to deposit(0-" + player.getCash() + "),Keep two decimal places");

		Optional<String> result = dialog.showAndWait();
		String save1 = "";
		if (result.isPresent()) {
			save1 = result.get();
			try {
				double save = Double.valueOf(save1);
				save1 = String.format("%.2f", save);
				save = Double.valueOf(save1);
				if (save <= player.getCash() && save >= 0) {
					player.addDeposit(save);
					player.addCash(-save);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(SAVE_SUCCESS);
					alert.setHeaderText(null);
					alert.setContentText("Account " + (player.getDeposit() - save) + "add to " + player.getDeposit() + "Dollar\n"
							+ "Cash " + (player.getCash() + save) + "reduce to " + player.getCash() + Dollar);
					alert.showAndWait();
				} else {
					Alert warn = new Alert(AlertType.WARNING);
					warn.setTitle(ERROR);
					warn.setHeaderText(null);
					warn.setContentText(YOUR_INPUT_IS_INCORRECT);
					warn.showAndWait();
					saveMoney(player);
				}
			} catch (Exception ex) {
				Alert warn = new Alert(AlertType.WARNING);
				warn.setTitle(ERROR);
				warn.setHeaderText(null);
				warn.setContentText(YOUR_INPUT_IS_INCORRECT);
				warn.showAndWait();
				saveMoney(player);
			}
		}

	}

	private void withdrawalMoney(Player player) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(WELCOME + name);
		dialog.setHeaderText(null);
		dialog.setContentText(
				"Account " + player.getDeposit() + "\nPlease enter the amount you wish to withdraw(0-" + player.getDeposit() + "),two decimal places");

		Optional<String> result = dialog.showAndWait();
		String withdrawal1 = "";
		if (result.isPresent()) {
			withdrawal1 = result.get();
			try {
				double withdrawal = Double.valueOf(withdrawal1);
				withdrawal1 = String.format("%.2f", withdrawal);
				withdrawal = Double.valueOf(withdrawal1);
				if (withdrawal <= player.getDeposit() && withdrawal >= 0) {
					player.addDeposit(-withdrawal);
					player.addCash(withdrawal);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(SUCCESSFUL_WITHDRAWAL);
					alert.setHeaderText(null);
					alert.setContentText("Account " + (player.getDeposit() + withdrawal) + "Reduce to " + player.getDeposit()
							+ "Dollar\n" + "Cash " + (player.getCash() - withdrawal) + "Add to" + player.getCash() + Dollar);
					alert.showAndWait();
				} else {
					Alert warn = new Alert(AlertType.WARNING);
					warn.setTitle(ERROR);
					warn.setHeaderText(null);
					warn.setContentText(YOUR_INPUT_IS_INCORRECT);
					warn.showAndWait();
					withdrawalMoney(player);
				}
			} catch (Exception exception) {
				Alert warn = new Alert(AlertType.WARNING);
				warn.setTitle(ERROR);
				warn.setHeaderText(null);
				warn.setContentText(YOUR_INPUT_IS_INCORRECT);
				warn.showAndWait();
				withdrawalMoney(player);
			}
		}
	}

}
