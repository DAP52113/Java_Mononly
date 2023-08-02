package kadoufall.monopoly.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import kadoufall.monopoly.location.*;

//location
public class Point extends VBox {
	//point icon
	public static final String ICONS_BARRAGE_PNG = "icons/barrage.png";
	// The label of the Location, the label of the location, the location ID
	// the street ID of the location, the location array within the location
	@FXML private Label label;
	private Label mark;
	private int pointID;
	private int streetID;
	private String address;
	private ArrayList<Location> locations = new ArrayList<Location>();

	public Point() {
		super();
		// setting fx xml file
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Point.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		setPointID(0);
		setStreetID(0);
		setAddress("");
		setMark(null);
	}

	public Label getLabel() {
		return label;
	}

	public int getPointID() {
		return pointID;
	}

	public void setPointID(int pointID) {
		this.pointID = pointID;
	}

	public int getStreetID() {
		return streetID;
	}

	public void setStreetID(int streetID) {
		this.streetID = streetID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Label getMark() {
		return mark;
	}

	public void setMark(Label mark) {
		this.mark = mark;
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public void addLocation(Location location) {
		this.locations.add(location);
	}

	// Gets the position for the specified direction and number of steps
	public Point getPointAt(ArrayList<Point> points, Point point, Direction direction, int step) {
		Point re = null;
		int toId = 0;
		int pointNum = points.size();
		switch (direction) {
		case cw:
			toId = pointID + step;
			if (toId >= pointNum) {
				toId -= pointNum;
			}
			break;
		case ccw:
			toId = pointID - step;
			if (toId < 0) {
				toId += pointNum;
			}
			break;
		}
		for (Point p : points) {
			if (p.getPointID() == toId) {
				re = p;
				break;
			}
		}
		return re;
	}

	// Determine if there are roadblocks at the current location
	// and return if there are
	public Location hasBarricade() {
		Location re = null;
		for (Location loc : locations) {
			if (loc instanceof Barricade) {
				re = loc;
				break;
			}
		}
		return re;
	}

	// removeLocation
	public void removeLocation(Location loc) {
		locations.remove(loc);
	}

	// setting icon ui
	public void setIcon() {
		Predicate<Location> filter = (p) -> (p instanceof Player || p instanceof Barricade);
		Location tem = locations.stream().filter(filter).findFirst().orElse(null);
		if(tem == null){
			label.setGraphic(null);
		}else{
			if(tem instanceof Player){
				Player tem1 = (Player)tem;
				Image image = new Image(getClass().getResourceAsStream(tem1.getIcon()));
				label.setGraphic(new ImageView(image));
			}else{
				Image image = new Image(getClass().getResourceAsStream(ICONS_BARRAGE_PNG));
				label.setGraphic(new ImageView(image));
			}
		}
	}


}
