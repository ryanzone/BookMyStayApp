/**
 * ================================================================
 * CLASS – Room
 * ================================================================
 *
 * Represents room details such as price and amenities.
 *
 * @author Ryan John Mathew
 * @version 4.0
 */

public class Room {

    private String type;
    private double price;
    private String amenities;

    public Room(String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }
}