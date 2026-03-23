import java.util.HashMap;
import java.util.Map;

/**
 * ================================================================
 * CLASS – RoomInventory
 * ================================================================
 *
 * Manages centralized room availability using a HashMap.
 *
 * Provides controlled operations for booking and cancellation
 * along with optional direct updates for administrative purposes.
 *
 * Author: Ryan John Mathew
 * Version: 3.2
 */

public class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single", 10);
        inventory.put("Double", 5);
        inventory.put("Suite", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public boolean decrementRoom(String roomType) {
        int current = inventory.getOrDefault(roomType, 0);

        if (current > 0) {
            inventory.put(roomType, current - 1);
            return true;
        } else {
            System.out.println("No rooms available for type: " + roomType);
            return false;
        }
    }

    public void incrementRoom(String roomType) {
        int current = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, current + 1);
    }

    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public void displayInventory() {
        System.out.println("\n===== Current Room Inventory =====");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms: " + entry.getValue());
        }

        System.out.println("==================================");
    }
}