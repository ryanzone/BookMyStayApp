import java.util.*;

/**
 * Version: 2.1
 *
 * Use Case 2: Basic Room Types & Static Availability
 * -------------------------------------------------
 * Demonstrates object initialization and basic data handling
 * using the Room class.
 *
 * Room details such as type, price, and amenities are reused
 * from the existing Room class.
 *
 * Availability is maintained using simple variables to highlight
 * limitations before introducing data structures in later use cases.
 *
 * Author: Ryan John
 */

public class UC2RoomInitialization {

    public static void main(String[] args) {

        Room single = new Room("Single Room", 2000, "1 Bed, WiFi");
        Room doubleRoom = new Room("Double Room", 3500, "2 Beds, AC, WiFi");
        Room suite = new Room("Suite Room", 6000, "3 Beds, AC, WiFi, TV");

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("===== Room Details & Availability =====\n");

        displayRoom(single, singleAvailable);
        displayRoom(doubleRoom, doubleAvailable);
        displayRoom(suite, suiteAvailable);

        System.out.println("\nApplication Terminated.");
    }

    public static void displayRoom(Room room, int availability) {
        System.out.println("Room Type: " + room.getType());
        System.out.println("Price: ₹" + room.getPrice());
        System.out.println("Amenities: " + room.getAmenities());
        System.out.println("Available: " + availability);
        System.out.println("----------------------------------");
    }
}