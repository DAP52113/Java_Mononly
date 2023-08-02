package kadoufall.monopoly.application;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import kadoufall.monopoly.stock.*;
import kadoufall.monopoly.card.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import kadoufall.monopoly.location.*;

/**
 * Map control class
 */
public class Controller implements Initializable {

    // current player
    private Player currentPlayer = null;
    // player list
    private ArrayList<Player> players = new ArrayList<Player>();
    //street list
    private ArrayList<ArrayList<Location>> streets = new ArrayList<ArrayList<Location>>();
    // give up players
    private ArrayList<Player> giveUp = new ArrayList<Player>();
    //is hurt players
    private ArrayList<Integer> roundForHurt = new ArrayList<Integer>();
    // player info name
    private static final String NAME_PLAYER_INFO_A = "A";
    private static final String NAME_PLAYER_INFO_B = "B";
    private static final String NAME_PLAYER_INFO_C = "C";
    private static final String NAME_PLAYER_INFO_D = "D";
    //player id
    private static final int PLAYER_ID_A = 1;
    private static final int PLAYER_ID_B = 2;
    private static final int PLAYER_ID_C = 3;
    private static final int PLAYER_ID_D = 4;
    //player icon photo
    private static final String PLAYER_ICON_PHOTO_A = "icons/p1.jpg";
    private static final String PLAYER_ICON_PHOTO_B = "icons/p2.jpg";
    private static final String PLAYER_ICON_PHOTO_C = "icons/p3.jpg";
    private static final String PLAYER_ICON_PHOTO_D = "icons/p4.jpg";
    //player icon large info
    private static final String PLAYER_LARGE_ICON_PHOTO_A = "icons/p11.jpg";
    private static final String PLAYER_LARGE_ICON_PHOTO_B = "icons/p22.jpg";
    private static final String PLAYER_LARGE_ICON_PHOTO_C = "icons/p33.jpg";
    private static final String PLAYER_LARGE_ICON_PHOTO_D = "icons/p44.jpg";
    //init dice icon
    public static final String ICONS_DICE_1_PNG = "icons/dice1.png";
    public static final String ICONS_DICE_2_PNG = "icons/dice2.png";
    public static final String ICONS_DICE_3_PNG = "icons/dice3.png";
    public static final String ICONS_DICE_4_PNG = "icons/dice4.png";
    public static final String ICONS_DICE_5_PNG = "icons/dice5.png";
    public static final String ICONS_DICE_6_PNG = "icons/dice6.png";

    //stock price
    public static final double PRICE_SHELL_OIL = 40;
    public static final double PRICE_NOBEL = 150;
    public static final double PRICE_POLAROID = 25;
    public static final double PRICE_UNIEVER = 20;
    public static final double PRICE_HEINZ = 100;
    public static final double PRICE_HARLEYDAV = 55;
    public static final double PRICE_IBM = 90;
    public static final double PRICE_JUNIPER = 35;
    public static final double PRICE_JPMORANGA = 30;
    public static final double PRICE_MEADJONSON = 120;

    // zero item
    private static int ZERO_ITEM = 0;

    // current date
    private LocalDate date = LocalDate.now();

    // stock data lists
    private ArrayList<Stock> stocks = new ArrayList<Stock>();

    // dice
    @FXML Label dice;

    // state info
    @FXML Label state;

    // info
    @FXML Label gameInfo;
    @FXML Label playerInfo_icon;
    @FXML Label playerInfo_name;
    @FXML Label playerInfo_cash;
    @FXML Label playerInfo_cash_num;
    @FXML Label playerInfo_deposit;
    @FXML Label playerInfo_deposit_num;
    @FXML Label playerInfo_coupon;
    @FXML Label playerInfo_coupon_num;
    @FXML Label playerInfo_stock;
    @FXML Label playerInfo_stock_num;
    @FXML Label playerInfo_property;
    @FXML Label playerInfo_property_num;

    // point map setting
    private ArrayList<Point> points = new ArrayList<Point>();// list points
    @FXML Point Cell_110; @FXML Point Cell_108; @FXML Point Cell_106; @FXML Point Cell_104;
    @FXML Point Cell_63; @FXML Point Cell_78; @FXML Point Cell_77; @FXML Point Cell_75;
    @FXML Point Cell_74; @FXML Point Cell_70; @FXML Point Cell_71; @FXML Point Cell_69;
    @FXML Point Cell_68; @FXML Point Cell_32; @FXML Point Cell_31; @FXML Point Cell_30;
    @FXML Point Cell_29; @FXML Point Cell_28; @FXML Point Cell_27; @FXML Point Cell_25;
    @FXML Point Cell_24; @FXML Point Cell_22; @FXML Point Cell_52; @FXML Point Cell_57;
    @FXML Point Cell_56; @FXML Point Cell_55; @FXML Point Cell_54; @FXML Point Cell_53;
    @FXML Point Cell_33; @FXML Point Cell_34; @FXML Point Cell_35; @FXML Point Cell_36;
    @FXML Point Cell_37; @FXML Point Cell_38; @FXML Point Cell_39; @FXML Point Cell_40;
    @FXML Point Cell_41; @FXML Point Cell_42; @FXML Point Cell_43; @FXML Point Cell_44;
    @FXML Point Cell_45; @FXML Point Cell_46; @FXML Point Cell_47; @FXML Point Cell_48;
    @FXML Point Cell_49; @FXML Point Cell_50; @FXML Point Cell_51; @FXML Point Cell_81;
    @FXML Point Cell_83; @FXML Point Cell_85; @FXML Point Cell_84; @FXML Point Cell_88;
    @FXML Point Cell_90; @FXML Point Cell_89; @FXML Point Cell_87; @FXML Point Cell_94;
    @FXML Point Cell_95; @FXML Point Cell_97; @FXML Point Cell_96; @FXML Point Cell_98;
    @FXML Point Cell_100; @FXML Point Cell_102;
    // flag images
    @FXML Label Cell_109; @FXML Label Cell_107; @FXML Label Cell_103; @FXML Label Cell_73;
    @FXML Label Cell_149; @FXML Label Cell_159; @FXML Label Cell_76; @FXML Label Cell_86;
    @FXML Label Cell_82; @FXML Label Cell_80; @FXML Label Cell_79; @FXML Label Cell_169;
    @FXML Label Cell_21; @FXML Label Cell_179; @FXML Label Cell_189; @FXML Label Cell_18;
    @FXML Label Cell_17; @FXML Label Cell_199; @FXML Label Cell_66; @FXML Label Cell_58;
    @FXML Label Cell_16; @FXML Label Cell_26; @FXML Label Cell_120; @FXML Label Cell_60;
    @FXML Label Cell_9; @FXML Label Cell_121; @FXML Label Cell_23; @FXML Label Cell_62;
    @FXML Label Cell_15; @FXML Label Cell_14; @FXML Label Cell_13; @FXML Label Cell_12;
    @FXML Label Cell_122; @FXML Label Cell_10; @FXML Label Cell_123; @FXML Label Cell_8;
    @FXML Label Cell_7; @FXML Label Cell_67; @FXML Label Cell_124; @FXML Label Cell_65;
    @FXML Label Cell_64; @FXML Label Cell_59; @FXML Label Cell_118; @FXML Label Cell_61;
    @FXML Label Cell_125; @FXML Label Cell_126; @FXML Label Cell_127; @FXML Label Cell_1;
    @FXML Label Cell_128; @FXML Label Cell_3; @FXML Label Cell_4; @FXML Label Cell_139;
    @FXML Label Cell_6; @FXML Label Cell_91; @FXML Label Cell_130; @FXML Label Cell_129;
    @FXML Label Cell_92; @FXML Label Cell_166; @FXML Label Cell_93; @FXML Label Cell_119;
    @FXML Label Cell_99; @FXML Label Cell_101;
    @FXML
    private Font x3;

    // init
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init location add mark
        points.add(Cell_110);Cell_110.setMark(Cell_109);Cell_110.setStreetID(0);Cell_110.addLocation(new Land("HD Road Number 1",Cell_110));
        points.add(Cell_108);Cell_108.setMark(Cell_107);Cell_108.setStreetID(0);Cell_108.addLocation(new Land("HD Road Number 22",Cell_108));
        points.add(Cell_106);Cell_106.setMark(Cell_103);Cell_106.setStreetID(0);Cell_106.addLocation(new Land("HD Road Number 220",Cell_106));
        points.add(Cell_104);Cell_104.setMark(Cell_73);Cell_104.setStreetID(0);Cell_104.addLocation(new Land("HD Road Number 440",Cell_104));
        points.add(Cell_63);Cell_63.setMark(Cell_149);Cell_63.setStreetID(0);Cell_63.addLocation(new Land("HD Road Number 550",Cell_63));
        points.add(Cell_78);Cell_78.setMark(Cell_159);Cell_78.setStreetID(1);Cell_78.addLocation(new Bank("People Bank",Cell_78));
        points.add(Cell_77);Cell_77.setMark(Cell_76);Cell_77.setStreetID(1);Cell_77.addLocation(new Land("GuoQ Road Number 120",Cell_77));
        points.add(Cell_75);Cell_75.setMark(Cell_86);Cell_75.setStreetID(1);Cell_75.addLocation(new Land("GuoQ Road Number 150",Cell_75));
        points.add(Cell_74);Cell_74.setMark(Cell_82);Cell_74.setStreetID(1);Cell_74.addLocation(new Land("GuoQ Road Number 660",Cell_74));
        points.add(Cell_70);Cell_70.setMark(Cell_80);Cell_70.setStreetID(1);Cell_70.addLocation(new Land("GuoQ Road Number 1222",Cell_70));
        points.add(Cell_71);Cell_71.setMark(Cell_79);Cell_71.setStreetID(2);Cell_71.addLocation(new Land("Qi Road Number 2",Cell_71));
        points.add(Cell_69);Cell_69.setMark(Cell_169);Cell_69.setStreetID(2);Cell_69.addLocation(new Land("Qi Road Number 50",Cell_69));
        points.add(Cell_68);Cell_68.setMark(Cell_21);Cell_68.setStreetID(2);Cell_68.addLocation(new Land("Qi Road Number 510",Cell_68));
        points.add(Cell_32);Cell_32.setMark(Cell_179);Cell_32.setStreetID(2);Cell_32.addLocation(new Hospital("Total Hospital",Cell_32));
        points.add(Cell_31);Cell_31.setMark(Cell_189);Cell_31.setStreetID(2);Cell_31.addLocation(new Store("Market(Qi Road)",Cell_31));
        points.add(Cell_30);Cell_30.setMark(Cell_18);Cell_30.setStreetID(2);Cell_30.addLocation(new Land("Qi Road Number 250",Cell_30));
        points.add(Cell_29);Cell_29.setMark(Cell_17);Cell_29.setStreetID(2);Cell_29.addLocation(new Land("Qi Road Number 2250",Cell_29));
        points.add(Cell_28);Cell_28.setMark(Cell_199);Cell_28.setStreetID(2);Cell_28.addLocation(new GiveCard("Get Card",Cell_28));
        points.add(Cell_27);Cell_27.setMark(Cell_66);Cell_27.setStreetID(3);Cell_27.addLocation(new Land("JG Number 100",Cell_37));
        points.add(Cell_25);Cell_25.setMark(Cell_58);Cell_25.setStreetID(3);Cell_25.addLocation(new Land("JG Number 111",Cell_25));
        points.add(Cell_24);Cell_24.setMark(Cell_16);Cell_24.setStreetID(3);Cell_24.addLocation(new Land("JG Number 122",Cell_24));
        points.add(Cell_22);Cell_22.setMark(Cell_26);Cell_22.setStreetID(3);Cell_22.addLocation(new Land("JG Number 133",Cell_22));
        points.add(Cell_52);Cell_52.setMark(Cell_120);Cell_52.setStreetID(3);Cell_52.addLocation(new Bank("People bank(JiGu)",Cell_52));
        points.add(Cell_57);Cell_57.setMark(Cell_60);Cell_57.setStreetID(4);Cell_57.addLocation(new Land("People Road Number 550",Cell_57));
        points.add(Cell_56);Cell_56.setMark(Cell_9);Cell_56.setStreetID(4);Cell_56.addLocation(new Land("People Road Number 120",Cell_56));
        points.add(Cell_55);Cell_55.setMark(Cell_121);Cell_55.setStreetID(4);Cell_55.addLocation(new GiveCoupon("Get tickets(People Road)",Cell_55));
        points.add(Cell_54);Cell_54.setMark(Cell_23);Cell_54.setStreetID(4);Cell_54.addLocation(new Land("People Road Number 250",Cell_54));
        points.add(Cell_53);Cell_53.setMark(Cell_62);Cell_53.setStreetID(4);Cell_53.addLocation(new Land("People Road Number 350",Cell_53));
        points.add(Cell_33);Cell_33.setMark(Cell_15);Cell_33.setStreetID(5);Cell_33.addLocation(new Land("CD Road Number 0",Cell_33));
        points.add(Cell_34);Cell_34.setMark(Cell_14);Cell_34.setStreetID(5);Cell_34.addLocation(new Land("CD Road Number 230",Cell_34));
        points.add(Cell_35);Cell_35.setMark(Cell_13);Cell_35.setStreetID(5);Cell_35.addLocation(new Land("CD Road Number 530",Cell_35));
        points.add(Cell_36);Cell_36.setMark(Cell_12);Cell_36.setStreetID(5);Cell_36.addLocation(new Land("CD Road Number 1110",Cell_36));
        points.add(Cell_37);Cell_37.setMark(Cell_122);Cell_37.setStreetID(5);Cell_37.addLocation(new GiveCard("Get Card(CD Road)",Cell_37));
        points.add(Cell_38);Cell_38.setMark(Cell_10);Cell_38.setStreetID(5);Cell_38.addLocation(new Land("CD Road Number 1220",Cell_38));
        points.add(Cell_39);Cell_39.setMark(Cell_123);Cell_39.setStreetID(5);Cell_39.addLocation(new Bank("People Bank(CD Road)",Cell_39));
        points.add(Cell_40);Cell_40.setMark(Cell_8);Cell_40.setStreetID(5);Cell_40.addLocation(new Land("CD Road Number 1550",Cell_40));
        points.add(Cell_41);Cell_41.setMark(Cell_7);Cell_41.setStreetID(5);Cell_41.addLocation(new Land("CD Road Number 2770",Cell_41));
        points.add(Cell_42);Cell_42.setMark(Cell_67);Cell_42.setStreetID(6);Cell_42.addLocation(new Land("BJ Road Number 0",Cell_42));
        points.add(Cell_43);Cell_43.setMark(Cell_124);Cell_43.setStreetID(6);Cell_43.addLocation(new Lottery("Stocks(BJ Road)",Cell_43));
        points.add(Cell_44);Cell_44.setMark(Cell_65);Cell_44.setStreetID(6);Cell_44.addLocation(new Land("BJ Road Number 10",Cell_44));
        points.add(Cell_45);Cell_45.setMark(Cell_64);Cell_45.setStreetID(6);Cell_45.addLocation(new Land("BJ Road Number 20",Cell_45));
        points.add(Cell_46);Cell_46.setMark(Cell_59);Cell_46.setStreetID(6);Cell_46.addLocation(new Land("BJ Road Number 30",Cell_46));
        points.add(Cell_47);Cell_47.setMark(Cell_118);Cell_47.setStreetID(6);Cell_47.addLocation(new Space(Cell_47));
        points.add(Cell_48);Cell_48.setMark(Cell_61);Cell_48.setStreetID(6);Cell_48.addLocation(new Land("BJ Road Number 40",Cell_48));
        points.add(Cell_49);Cell_49.setMark(Cell_125);Cell_49.setStreetID(6);Cell_49.addLocation(new Store("Market(BJ Road)",Cell_49));
        points.add(Cell_50);Cell_50.setMark(Cell_126);Cell_50.setStreetID(6);Cell_50.addLocation(new News("News(BJ Road)",Cell_50));
        points.add(Cell_51);Cell_51.setMark(Cell_127);Cell_51.setStreetID(6);Cell_51.addLocation(new Space(Cell_51));
        points.add(Cell_81);Cell_81.setMark(Cell_1);Cell_81.setStreetID(6);Cell_81.addLocation(new Land("BJ Road Number 50",Cell_81));
        points.add(Cell_83);Cell_83.setMark(Cell_128);Cell_83.setStreetID(6);Cell_83.addLocation(new GiveCoupon("Get Tickets(BJ Road)",Cell_83));
        points.add(Cell_85);Cell_85.setMark(Cell_3);Cell_85.setStreetID(6);Cell_85.addLocation(new Land("BJ Road Number 60",Cell_85));
        points.add(Cell_84);Cell_84.setMark(Cell_4);Cell_84.setStreetID(6);Cell_84.addLocation(new Land("BJ Road Number 70",Cell_84));
        points.add(Cell_88);Cell_88.setMark(Cell_139);Cell_88.setStreetID(6);Cell_88.addLocation(new Land("BJ Road Number 80",Cell_88));
        points.add(Cell_90);Cell_90.setMark(Cell_6);Cell_90.setStreetID(6);Cell_90.addLocation(new Land("BJ Road Number 90",Cell_90));
        points.add(Cell_89);Cell_89.setMark(Cell_91);Cell_89.setStreetID(6);Cell_89.addLocation(new Land("BJ Road Number 100",Cell_89));
        points.add(Cell_87);Cell_87.setMark(Cell_130);Cell_87.setStreetID(7);Cell_87.addLocation(new Land("HZ Road Number 1",Cell_87));
        points.add(Cell_94);Cell_94.setMark(Cell_129);Cell_94.setStreetID(7);Cell_94.addLocation(new Land("HZ Road Number 11",Cell_94));
        points.add(Cell_95);Cell_95.setMark(Cell_92);Cell_95.setStreetID(7);Cell_95.addLocation(new Land("HZ Road Number 22",Cell_95));
        points.add(Cell_97);Cell_97.setMark(Cell_166);Cell_97.setStreetID(7);Cell_97.addLocation(new Bank("People bank(HZ Road)",Cell_97));
        points.add(Cell_96);Cell_96.setMark(Cell_93);Cell_96.setStreetID(7);Cell_96.addLocation(new Land("HZ Road Number 33",Cell_96));
        points.add(Cell_98);Cell_98.setMark(Cell_119);Cell_98.setStreetID(7);Cell_98.addLocation(new Lottery("Stocks(HZ Road)",Cell_98));
        points.add(Cell_100);Cell_100.setMark(Cell_99);Cell_100.setStreetID(7);Cell_100.addLocation(new Land("HZ Road Number 110",Cell_100));
        points.add(Cell_102);Cell_102.setMark(Cell_101);Cell_102.setStreetID(7);Cell_102.addLocation(new News("News(HZ Road)",Cell_102));

        // setting id
        for (int i = 0; i < points.size(); i++) {
            points.get(i).setPointID(i);
        }

        // setting street element
        for (int i = 0; i < 8; i++) {
            streets.add(new ArrayList<Location>());
        }
        for (Point p_item : points) {
            streets.get(p_item.getStreetID()).add(p_item.getLocations().get(0));
        }

        // init player list
        int playerNum = Monopoly.playerNumbers;
        for (int i = 0; i < playerNum; i++) {
            switch (i) {
                case 0:
                    players.add(new Player(NAME_PLAYER_INFO_A, PLAYER_ID_A, Cell_110, PLAYER_ICON_PHOTO_A, PLAYER_LARGE_ICON_PHOTO_A));
                    break;
                case 1:
                    players.add(new Player(NAME_PLAYER_INFO_B, PLAYER_ID_B, Cell_110, PLAYER_ICON_PHOTO_B, PLAYER_LARGE_ICON_PHOTO_B));
                    break;
                case 2:
                    players.add(new Player(NAME_PLAYER_INFO_C, PLAYER_ID_C, Cell_110, PLAYER_ICON_PHOTO_C, PLAYER_LARGE_ICON_PHOTO_C));
                    break;
                case 3:
                    players.add(new Player(NAME_PLAYER_INFO_D, PLAYER_ID_D, Cell_110, PLAYER_ICON_PHOTO_D, PLAYER_LARGE_ICON_PHOTO_D));
                    break;
            }

            roundForHurt.add(ZERO_ITEM);
        }

        // init current player
        currentPlayer = players.get(ZERO_ITEM);

        currentPlayer.getPoint().setIcon();

        changeInfo(currentPlayer);
        changeDate();
        warn();

        // init stock
        stocks.add(new Stock("Shell Oil", PRICE_SHELL_OIL, 0));
        stocks.add(new Stock("Nobel", PRICE_NOBEL, 1));
        stocks.add(new Stock("Polaroid", PRICE_POLAROID, 2));
        stocks.add(new Stock("Unilever", PRICE_UNIEVER, 3));
        stocks.add(new Stock("Heinz", PRICE_HEINZ, 4));
        stocks.add(new Stock("Harley-Davidson", PRICE_HARLEYDAV, 5));
        stocks.add(new Stock("IBM Company", PRICE_IBM, 6));
        stocks.add(new Stock("Juniper Networks", PRICE_JUNIPER, 7));
        stocks.add(new Stock("Jpmorgan Chase", PRICE_JPMORANGA, 8));
        stocks.add(new Stock("Meadjohnson", PRICE_MEADJONSON, 9));
        for (Stock s : stocks) {
            Random random_item = new Random();
            double rate_item_double = random_item.nextDouble() * (-11 - 10) + 11;
            s.setRate(rate_item_double);
            s.changePrice();
            s.changeRatePrint();
            s.addTrend(date.toString(), s.getPrice());
        }

        // init Dice graph
        int i = (int) (Math.random() * 6);
        String url = "";
        switch (i) {
            case 0:
                url = ICONS_DICE_1_PNG;
                break;
            case 1:
                url = ICONS_DICE_2_PNG;
                break;
            case 2:
                url = ICONS_DICE_3_PNG;
                break;
            case 3:
                url = ICONS_DICE_4_PNG;
                break;
            case 4:
                url = ICONS_DICE_5_PNG;
                break;
            case 5:
                url = ICONS_DICE_6_PNG;
                break;
        }
        Image image = new Image(getClass().getResourceAsStream(url));
        dice.setGraphic(new ImageView(image));

    }

    // player info
    @FXML
    private void playerInfo(ActionEvent e) {
        PlayerInfo playerInfoItem = new PlayerInfo(players);
        Stage stage_info = new Stage();
        playerInfoItem.start(stage_info);
    }

    // position info
    @FXML
    private void locInfo(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("View location information");
        dialog.setHeaderText(null);
        dialog.setContentText("Which step before and after you want to see specific information(the rear is represented by a negative number)");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                int step = Integer.parseInt(result.get());
                Point see;
                if (step > points.size() || step < -points.size()) {
                    Alert warn = new Alert(AlertType.WARNING);
                    warn.setTitle("Error");
                    warn.setHeaderText(null);
                    warn.setContentText("Your input is incorrect");
                    warn.showAndWait();
                    locInfo(e);
                } else {
                    String con = "";
                    if (step >= 0) {
                        see = currentPlayer.getPoint().getPointAt(points, currentPlayer.getPoint(),
                                currentPlayer.getDirection(), step);
                    } else {
                        see = currentPlayer.getPoint().getPointAt(points, currentPlayer.getPoint(),
                                Direction.negative(currentPlayer.getDirection()), -step);
                    }
                    boolean hasBarricade = false;
                    ArrayList<Location> cellLoc = see.getLocations();
                    for (int i = 0; i < cellLoc.size(); i++) {
                        if (cellLoc.get(i) instanceof Barricade) {
                            hasBarricade = true;
                        } else if (!(cellLoc.get(i) instanceof Player)) {
                            con += "   name：" + cellLoc.get(i).getName() + "\n";
                            if (cellLoc.get(i) instanceof Land) {
                                if (((Land) cellLoc.get(i)).getOwner() == null) {
                                    con += "   possessor：dereliction\n";
                                } else {
                                    con += "   possessor：" + ((Land) cellLoc.get(i)).getOwner().getName() + "\n";
                                }
                                con += "   Level：" + ((Land) cellLoc.get(i)).getLevel();
                                con += "   Price：" + ((Land) cellLoc.get(i)).getPrice();
                            }
                        }
                    }
                    if (hasBarricade) {
                        con += "   Palisade：Yes";
                    }
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Notice");
                    alert.setHeaderText(null);
                    alert.setContentText(con);
                    alert.showAndWait();
                    locInfo(e);
                }

            } catch (Exception exception) {
                Alert warn = new Alert(AlertType.WARNING);
                warn.setTitle("Error");
                warn.setHeaderText(null);
                warn.setContentText("Input Error");
                warn.showAndWait();
                locInfo(e);
            }

        }

    }

    // Menu:use card
    @FXML
    private void useCard(ActionEvent e) {
        ArrayList<Card> cards = currentPlayer.getCardsList();
        List<String> choices = new ArrayList<>();
        for (Card c : cards) {
            choices.add(c.getName());
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>(cards.get(0).getName(), choices);
        dialog.setTitle("Choice");
        dialog.setHeaderText(null);
        dialog.setContentText("Please select the card to use");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            for (Card c : cards) {
                if (choice.equals(c.getName())) {
                    boolean use = c.useCard(points, currentPlayer, players);
                    if (!use) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Notice");
                        alert.setHeaderText(null);
                        alert.setContentText("Use Failed");
                        alert.showAndWait();
                    } else {
                        currentPlayer.removeCard(c);
                    }
                    break;
                }
            }
            hasCard(e);
        }
    }

    // Menu：give up card
    @FXML
    private void discard(ActionEvent e) {
        ArrayList<Card> cards = currentPlayer.getCardsList();
        List<String> choices = new ArrayList<>();
        for (Card c : cards) {
            choices.add(c.getName());
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>(cards.get(0).getName(), choices);
        dialog.setTitle("Choose");
        dialog.setHeaderText(null);
        dialog.setContentText("Please give up card");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            for (Card c : cards) {
                if (choice.equals(c.getName())) {
                    currentPlayer.removeCard(c);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Notice");
                    alert.setHeaderText(null);
                    alert.setContentText("Give Success");
                    alert.showAndWait();
                    break;
                }
            }
            hasCard(e);
        }
    }

    // Menu：View the cards you have
    @FXML
    private void hasCard(ActionEvent e) {
        String info = "";
        ArrayList<Card> cards = currentPlayer.getCardsList();
        for (int i = 0; i < cards.size(); i++) {
            info += i + "." + cards.get(i).getName() + "\n";
        }
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notice");
        alert.setHeaderText("Have cards");
        alert.setContentText(info);
        alert.showAndWait();
    }

    // menu：view card info
    @FXML
    private void seeCard(ActionEvent e) {
        String info = "";
        Card[] buyCard = new Card[7];
        buyCard[0] = new TurnCard();
        buyCard[1] = new AverageRichCard();
        buyCard[2] = new Roadblock();
        buyCard[3] = new TaxInspectionCard();
        buyCard[4] = new BuyCard();
        buyCard[5] = new PlunderCard();
        buyCard[6] = new Dice();

        for (Card c : buyCard) {
            info += c.getName() + ":" + c.getInformation() + "\n\n";
        }
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notice");
        alert.setHeaderText("Card info");
        alert.setContentText(info);
        alert.showAndWait();
    }

    // Stock
    @FXML
    private void stock(ActionEvent e) {
        if (date.getDayOfWeek() == DayOfWeek.MONDAY || date.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notice");
            alert.setHeaderText(null);
            alert.setContentText("The stock market is closed for the weekend！");
            alert.showAndWait();
        } else {
            StartStock tem = new StartStock(currentPlayer, players, date, stocks);
            Stage stage = new Stage();
            try {
                tem.start(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            stage.setOnCloseRequest((ee) -> {
                changeInfo(currentPlayer);
            });
        }
    }

    /**
     * calculateGiniCoefficient
     * @param list_values
     * @return
     */
    private double calculateGiniCoefficient(List<Double> list_values) {
        double[] values = list_values.stream().mapToDouble(Double::new).toArray();
        Arrays.sort(values);
        int n = values.length;
        int sum = 0;
        int cumulativeSum = 0;

        // Computes the sum of array elements and the cumulative sum
        for (int i = 0; i < n; i++) {
            sum += values[i];
            cumulativeSum += sum;
        }

        double giniCoefficient = 0;

        if (sum != 0) {
            // aver
            double mean = (double) cumulativeSum / (double) (n * sum);
            //compute giniCoefficient
            giniCoefficient = 1 - (2 * mean - 1) / n;
        }

        return giniCoefficient;
    }


    // Menu: given in
    @FXML
    private void givein(ActionEvent e) {
        List<Double> player_property = new ArrayList<>();
        for (int item_player=0;item_player<players.size();item_player++){
            player_property.add(players.get(item_player).getProperty());
        }
        // compute Gini Coefficient data
        double result_gini = calculateGiniCoefficient(player_property);

        Alert alert_player = new Alert(AlertType.INFORMATION, currentPlayer.getName() + "failed !", ButtonType.YES);
        alert_player.setHeaderText("!");
        alert_player.setContentText("The player status is failed !");
        alert_player.showAndWait();

        players.remove(currentPlayer);
        currentPlayer.setStepResult(StepResult.fail);
        giveUp.add(currentPlayer);
        for (int k = 0; k < currentPlayer.getLands().size(); k++) {
            ((Land) currentPlayer.getLands().get(k)).setOwner(null);
        }
        currentPlayer.getPoint().removeLocation(currentPlayer);
        currentPlayer.getLands().clear();

        currentPlayer = nextPlayer();
        changeInfo(currentPlayer);
        warn();

        if (players.size() == 1) {
            //print Gini data
            Alert alert = new Alert(AlertType.INFORMATION, players.get(0).getName() + "Win!", ButtonType.YES);
            alert.setHeaderText("GAME OVER!");
            alert.setContentText("The Gini coefficient of the game is " + result_gini);
            alert.showAndWait();
            System.exit(0);
        }

    }

    // Dice
    @FXML
    private void Dice(MouseEvent event) {
        Object o = event.getSource();
        Label tem = (Label) o;
        System.out.println("currentPlayer isHurt():"+currentPlayer.isHurt());
        if (currentPlayer.isHurt()) {
            int temInt = roundForHurt.get(currentPlayer.getPlayerId() - 1);
            temInt++;
            roundForHurt.set(currentPlayer.getPlayerId() - 1, temInt);
            Alert alert = new Alert(AlertType.INFORMATION, "Not play", ButtonType.YES);
            alert.setHeaderText(null);
            alert.showAndWait();
            if (temInt == 2) {
                currentPlayer.setHurt(false);
                roundForHurt.set(currentPlayer.getPlayerId() - 1, 0);
            }
        } else {
            if (!currentPlayer.isUseDice()) {
                int random = (int) (Math.random() * 6) + 1;
                Alert alert = new Alert(AlertType.INFORMATION, "The number of points is " + random, ButtonType.YES);
                alert.setHeaderText(null);
                alert.showAndWait();
                currentPlayer.setStep(random);
            } else {
                Alert alert = new Alert(AlertType.INFORMATION, "remote control dice", ButtonType.YES);
                alert.setHeaderText(null);
                alert.showAndWait();
                currentPlayer.setUseDice(false);
            }

            int step = currentPlayer.getStep();
            String url = "";
            switch (step - 1) {
                case 0:
                    url = ICONS_DICE_1_PNG;
                    break;
                case 1:
                    url = ICONS_DICE_2_PNG;
                    break;
                case 2:
                    url = ICONS_DICE_3_PNG;
                    break;
                case 3:
                    url = ICONS_DICE_4_PNG;
                    break;
                case 4:
                    url = ICONS_DICE_5_PNG;
                    break;
                case 5:
                    url = ICONS_DICE_6_PNG;
                    break;
            }
            Image image = new Image(getClass().getResourceAsStream(url));
            tem.setGraphic(new ImageView(image));
            currentPlayer.step(points, streets, players);
        }
        currentPlayer = nextPlayer();
        changeInfo(currentPlayer);
        warn();
    }

    // next player
    private Player nextPlayer() {
        Player re = null;
        for (int i = 0; i < players.size(); i++) {
            if (currentPlayer == players.get(i)) {
                if (i + 1 < players.size()) {
                    re = players.get(i + 1);
                } else {
                    re = players.get(0);
                    changeDate();
                    if (date.getDayOfMonth() == 1) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Notice");
                        alert.setHeaderText(null);
                        alert.setContentText("You get 10% of your deposit!");
                        alert.showAndWait();
                        for (Player p : players) {
                            p.addDeposit(players.get(i).getDeposit() * 0.1);
                        }
                    }
                }
                break;
            }else{
                re = players.get(i);
                break;
            }
        }
        return re;
    }

    // change
    private void changeInfo(Player player) {
        Image image = new Image(getClass().getResourceAsStream(player.getLargeIcon()));
        playerInfo_icon.setGraphic(new ImageView(image));
        playerInfo_name.setText("  " + player.getName());
        playerInfo_cash_num.setText(String.valueOf(player.getCash()));
        playerInfo_deposit_num.setText(String.valueOf(player.getDeposit()));
        playerInfo_coupon_num.setText(String.valueOf(player.getCoupon()));
        playerInfo_stock_num.setText(String.valueOf(player.getStockProperty()));
        playerInfo_property_num.setText(String.valueOf(player.getProperty()));
    }

    // change date
    private void changeDate() {
        date = date.plusDays(1);
        gameInfo.setText("\n " + date.toString() + "\n\n " + date.getDayOfWeek().toString());
        for (Stock s : stocks) {
            Random ran = new Random();
            double rate = ran.nextDouble() * (-11 - 10) + 11;
            s.setRate(rate);
            s.changePrice();
            s.changeRatePrint();
            s.addTrend(date.toString(), s.getPrice());
            s.resetInventory();
        }
    }

    // warn notice
    private void warn() {
        String con = "";

        boolean hasBarricade = false;
        for (int i = 1; i < 11; i++) {
            Point cell = currentPlayer.getPoint().getPointAt(points, currentPlayer.getPoint(),
                    currentPlayer.getDirection(), i);
            for (int j = 0; j < cell.getLocations().size(); j++) {
                if (cell.getLocations().get(j) instanceof Barricade) {
                    con += "Front " + i + " roadblock！";
                    hasBarricade = true;
                    break;
                } else if (cell.getLocations().get(j) instanceof Land) {
                    Land tem = (Land) cell.getLocations().get(j);
                    if (tem.getOwner() != null && tem.getOwner() != currentPlayer) {
                        con += "Front " + i + " need to pay a toll！";
                        hasBarricade = true;
                        break;
                    }
                }
            }
            if (hasBarricade) {
                break;
            }
        }
        if (!hasBarricade) {
            con += "There's a smooth road ten paces ahead!";
        }
        state.setText(con);

    }

}
