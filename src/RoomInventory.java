import java.util.HashMap;
import java.util.Map;

/**
 * ================================================================
 * CLASS – RoomInventory
 * ================================================================
 *
 * Manages centralized room availability using HashMap.
 *
 * @author Developer
 * @version 3.0
 */

public class RoomInventory {

    private Map<String, Integer> inventory;

    /**
     * Constructor – initializes inventory with default room types
     */
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initial room setup
        inventory.put("Single", 10);
        inventory.put("Double", 5);
        inventory.put("Suite", 2);
    }

    /**
     * Get availability of a specific room type
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Update room availability (controlled update)
     */
    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
        } else {
            System.out.println("Room type does not exist.");
        }
    }

    /**
     * Display full inventory
     */
    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms: " + entry.getValue());
        }
    }
}