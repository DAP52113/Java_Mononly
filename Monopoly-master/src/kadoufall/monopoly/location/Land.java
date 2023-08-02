package kadoufall.monopoly.location;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

import kadoufall.monopoly.application.Point;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Land extends Location {
	public static final String WELCOME = "welcome";
	public static final String Dollar = "dollar";
	public static final String CASH_SHORTAGE = "Cash Shortage！";
	public static final String NO_OWNER_LAND_LEVEL = "The current land has no owner, Land Level: ";
	public static final String LAND_PRICE = " ,Price: ";
	public static final String WHETHER_TO_BUY = "Whether you are willing to buy";
	public static final String BUY = "Buy";
	public static final String CANCEL = "Cancel";
	public static final String NOTICE = "Notice";
	public static final String BUY_SUCCESS = "Buy Success!";
	public static final String HOUSE_MAX_LEVEL = "The current house max level";
	public static final String UPGRADE_THE_HOUSE = "Upgrade the house ?";
	public static final String UPGRADE = "Upgrade";
	public static final double DOUBLE_TOLL = 0.3;
	public static final String ICONS_A_0_PNG = "icons/A0.png";
	public static final String ICONS_A_1_PNG = "icons/A1.png";
	public static final String ICONS_A_2_PNG = "icons/A2.png";
	public static final String ICONS_A_3_PNG = "icons/A3.png";
	public static final String ICONS_A_4_PNG = "icons/A4.png";
	public static final String ICONS_A_5_PNG = "icons/A5.png";
	public static final String ICONS_B_0_PNG = "icons/B0.png";
	public static final String ICONS_B_1_PNG = "icons/B1.png";
	public static final String ICONS_B_2_PNG = "icons/B2.png";
	public static final String ICONS_B_3_PNG = "icons/B3.png";
	public static final String ICONS_B_4_PNG = "icons/B4.png";
	public static final String ICONS_B_5_PNG = "icons/B5.png";
	public static final String ICONS_C_0_PNG = "icons/C0.png";
	public static final String ICONS_C_1_PNG = "icons/C1.png";
	public static final String ICONS_C_2_PNG = "icons/C2.png";
	public static final String ICONS_C_3_PNG = "icons/C3.png";
	public static final String ICONS_C_4_PNG = "icons/C4.png";
	public static final String ICONS_C_5_PNG = "icons/C5.png";
	public static final String ICONS_D_0_PNG = "icons/D0.png";
	public static final String ICONS_D_1_PNG = "icons/D1.png";
	public static final String ICONS_D_2_PNG = "icons/D2.png";
	public static final String ICONS_D_3_PNG = "icons/D3.png";
	public static final String ICONS_D_4_PNG = "icons/D4.png";
	public static final String ICONS_D_5_PNG = "icons/D5.png";
	public static final String ICONS_N_0_PNG = "icons/N0.png";
	public static final String ICONS_N_1_PNG = "icons/N1.png";
	public static final String ICONS_N_2_PNG = "icons/N2.png";
	public static final String ICONS_N_3_PNG = "icons/N3.png";
	public static final String ICONS_N_4_PNG = "icons/N4.png";
	public static final String ICONS_N_5_PNG = "icons/N5.png";

	private Player owner = null;
	private int originPrice = 500;
	private int level = 0;
	private int upPrice = 100 * level;
	private int price = 500;

	public Land(String name, Point point) {
		super(name, point);
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(int originPrice) {
		this.originPrice = originPrice;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUpPrice() {
		return upPrice;
	}

	public void setUpPrice(int upPrice) {
		this.upPrice = upPrice;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public void operation(Player player) {
		// do nothing
	}

	public void operation(Player player, ArrayList<ArrayList<Location>> streets) {
		if (this.getOwner() == null) {
			buyLand(player);
		} else if (this.getOwner() == player) {
			upgradeLand(player);
		} else {
			toll(player, streets);
		}
	}

	private void buyLand(Player player) {
		if (price > player.getCash()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(WELCOME + name);
			alert.setHeaderText(NO_OWNER_LAND_LEVEL + level + LAND_PRICE + price + Dollar);
			alert.setContentText(CASH_SHORTAGE);
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(WELCOME + name);
			alert.setHeaderText(NO_OWNER_LAND_LEVEL + level + LAND_PRICE + price + Dollar);
			alert.setContentText(WHETHER_TO_BUY);

			ButtonType buttonTypeOne = new ButtonType(BUY);
			ButtonType buttonTypeCancel = new ButtonType(CANCEL, ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne) {
				player.addCash(-price);
				owner = player;
				player.addHouseNum(1);
				player.addLands(this);
				Alert quit = new Alert(AlertType.INFORMATION);
				quit.setTitle(NOTICE);
				quit.setHeaderText(null);
				quit.setContentText(BUY_SUCCESS + name + WELCOME);
				quit.showAndWait();
				Image image = new Image(getClass().getResourceAsStream(getIconLoc(player.getPlayerId(), 0)));
				player.getPoint().getMark().setGraphic(new ImageView(image));
			} else {
				Alert quit = new Alert(AlertType.INFORMATION);
				quit.setTitle(NOTICE);
				quit.setHeaderText(null);
				quit.setContentText(name + WELCOME);
				quit.showAndWait();
			}
		}

	}

	private void upgradeLand(Player player) {
		if (level == 5) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(WELCOME + name);
			alert.setHeaderText(NO_OWNER_LAND_LEVEL + level + LAND_PRICE + price + Dollar);
			alert.setContentText(name + HOUSE_MAX_LEVEL);
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(WELCOME + name);
			alert.setHeaderText(NO_OWNER_LAND_LEVEL + level + LAND_PRICE + price + "Dollar ,Upgrade cost" + upPrice + Dollar);
			alert.setContentText(UPGRADE_THE_HOUSE);

			ButtonType buttonTypeOne = new ButtonType(UPGRADE);
			ButtonType buttonTypeCancel = new ButtonType(CANCEL, ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne) {
				if (upPrice > player.getCash()) {
					Alert quit = new Alert(AlertType.INFORMATION);
					quit.setTitle(NOTICE);
					quit.setHeaderText(null);
					quit.setContentText(CASH_SHORTAGE + name + WELCOME);
					quit.showAndWait();
				} else {
					level++;
					player.addCash(-upPrice);
					price += upPrice;
					Alert quit = new Alert(AlertType.INFORMATION);
					quit.setTitle(NOTICE);
					quit.setHeaderText(null);
					quit.setContentText("Upgrade successfully ！Current Level: " + level + "Level ," + name + WELCOME);
					quit.showAndWait();
					Image image = new Image(getClass().getResourceAsStream(getIconLoc(player.getPlayerId(), level)));
					player.getPoint().getMark().setGraphic(new ImageView(image));
				}
			} else {
				Alert quit = new Alert(AlertType.INFORMATION);
				quit.setTitle(NOTICE);
				quit.setHeaderText(null);
				quit.setContentText(name + "Welcome to your coming again！");
				quit.showAndWait();
			}
		}

	}

	// toll
	private void toll(Player player, ArrayList<ArrayList<Location>> streets) {
		String con = "";

		double toll = price * DOUBLE_TOLL;
		double addition = 0;
		int streetNum = point.getStreetID();
		ArrayList<Location> street = streets.get(streetNum);
		for (Location loc : street) {
			if (loc instanceof Land) {
				Land next = (Land) loc;
				if (next.getOwner() == owner && next != this) {
					addition += next.getPrice() * 0.1;
				}
			}
		}
		toll += addition;

		if (player.getCash() >= toll) {
			player.addCash(-toll);
			owner.addCash(toll);
			con += "Pay " + toll + "Dollar Toll(Where the street: " + addition + "Dollar)" + "\nYour cash balance is: " + player.getCash() + "\nThanks！";
		} else if (player.getCash() + player.getDeposit() >= toll) {
			player.addDeposit(-toll + player.getCash());
			player.addCash(-player.getCash());
			owner.addCash(toll);
			con += "Pay " + toll + "Dollar Toll(Where the street: " + addition + "Dollar)" + "\nThe cash balance is 0 and the deposit balance is: " + player.getDeposit()
					+ "\nThanks";
		} else {
			double toll1 = toll - player.getCash() - player.getDeposit();
			owner.addCash(toll - toll1);
			player.addCash(-player.getCash());
			player.addDeposit(-player.getDeposit());

			ArrayList<Location> lands = player.getLands();
			ArrayList<Location> tem = player.getLands();
			boolean give = false;
			for (int i = 0; i < lands.size(); i++) {
				player.addCash(((Land) lands.get(i)).getPrice());
				((Land) lands.get(i)).setOwner(null);
				tem.add(((Land) lands.get(i)));
				player.addHouseNum(-1);
				con += "You've sold: " + name + ",Get Cash" + ((Land) lands.get(i)).getPrice() + "\n";

				int temLevel = ((Land) lands.get(i)).getLevel();
				Image image = new Image(getClass().getResourceAsStream(getIconLoc(0, temLevel)));
				lands.get(i).getPoint().getMark().setGraphic(new ImageView(image));

				if (player.getCash() >= toll1) {
					give = true;
					player.addCash(-toll1);
					owner.addCash(toll);
					con += "Pay " + toll + "Dollar Toll(Where the street: " + addition + "Dollar)" + "\nThe cash balance is 0 and the deposit balance is: " + player.getCash()
							+ "\nThanks";
					break;
				}
			}
			Consumer<Location> del = e -> lands.remove(e);
			tem.forEach(del);
			if (!give) {
				owner.addCash(player.getCash());
				player.addCash(-player.getCash());
				player.setStepResult(StepResult.fail);
				con += "You can't pay the tolls. You're broke.";
			}
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(WELCOME + name);
		alert.setHeaderText(WELCOME + name + ",The house belongs to" + owner.getName() + "You need to pay the toll" + toll + Dollar);
		alert.setContentText(con);
		alert.showAndWait();

	}

	private String getIconLoc(int playerID, int level) {
		String re = "";
		if (playerID == 1) {
			switch (level) {
			case 0:
				re = ICONS_A_0_PNG;
				break;
			case 1:
				re = ICONS_A_1_PNG;
				break;
			case 2:
				re = ICONS_A_2_PNG;
				break;
			case 3:
				re = ICONS_A_3_PNG;
				break;
			case 4:
				re = ICONS_A_4_PNG;
				break;
			case 5:
				re = ICONS_A_5_PNG;
				break;
			}
		} else if (playerID == 2) {
			switch (level) {
			case 0:
				re = ICONS_B_0_PNG;
				break;
			case 1:
				re = ICONS_B_1_PNG;
				break;
			case 2:
				re = ICONS_B_2_PNG;
				break;
			case 3:
				re = ICONS_B_3_PNG;
				break;
			case 4:
				re = ICONS_B_4_PNG;
				break;
			case 5:
				re = ICONS_B_5_PNG;
				break;
			}
		} else if (playerID == 3) {
			switch (level) {
			case 0:
				re = ICONS_C_0_PNG;
				break;
			case 1:
				re = ICONS_C_1_PNG;
				break;
			case 2:
				re = ICONS_C_2_PNG;
				break;
			case 3:
				re = ICONS_C_3_PNG;
				break;
			case 4:
				re = ICONS_C_4_PNG;
				break;
			case 5:
				re = ICONS_C_5_PNG;
				break;
			}
		} else if (playerID == 4) {
			switch (level) {
			case 0:
				re = ICONS_D_0_PNG;
				break;
			case 1:
				re = ICONS_D_1_PNG;
				break;
			case 2:
				re = ICONS_D_2_PNG;
				break;
			case 3:
				re = ICONS_D_3_PNG;
				break;
			case 4:
				re = ICONS_D_4_PNG;
				break;
			case 5:
				re = ICONS_D_5_PNG;
				break;
			}
		} else if (playerID == 0) {
			switch (level) {
			case 0:
				re = ICONS_N_0_PNG;
				break;
			case 1:
				re = ICONS_N_1_PNG;
				break;
			case 2:
				re = ICONS_N_2_PNG;
				break;
			case 3:
				re = ICONS_N_3_PNG;
				break;
			case 4:
				re = ICONS_N_4_PNG;
				break;
			case 5:
				re = ICONS_N_5_PNG;
				break;
			}
		}
		return re;
	}
        
        public void changeIcon(){
            Image image = new Image(getClass().getResourceAsStream(getIconLoc(owner.getPlayerId(), 0)));
            owner.getPoint().getMark().setGraphic(new ImageView(image));
        }
        
}
