import java.util.HashMap;
import java.util.Map;

/**
 * ================================================================
 * MAIN CLASS – UseCase4RoomSearch
 * ================================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates read-only search functionality.
 * 1. Initialize inventory
 * 2. Create room details (domain model)
 * 3. Perform search (READ ONLY)
 * 4. Filter unavailable rooms
 * @author Ryan John Mathew
 * @version 4.1
 */

public class UC4RoomSearch {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        Map<String, Room> rooms = new HashMap<>();
        rooms.put("Single", new Room("Single", 2000, "1 Bed, WiFi"));
        rooms.put("Double", new Room("Double", 3500, "2 Beds, AC, WiFi"));
        rooms.put("Suite", new Room("Suite", 6000, "Luxury Suite, AC, WiFi"));

        System.out.println("Available Rooms:\n");

        for (String type : rooms.keySet()) {

            int available = inventory.getAvailability(type);


            if (available > 0) {
                Room room = rooms.get(type);

                System.out.println("Room Type: " + room.getType());
                System.out.println("Price: ₹" + room.getPrice());
                System.out.println("Amenities: " + room.getAmenities());
                System.out.println("Available: " + available);
                System.out.println("------------------------");
            }
        }
    }
}