/**
 * ================================================================
 * MAIN CLASS – UseCase3InventorySetup
 * ================================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Demonstrates usage of HashMap for managing room availability.
 *
 * @author Developer
 * @version 3.1
 */

public class HotelBookingApp {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        System.out.println("\nChecking availability of Double Room:");
        System.out.println("Available: " + inventory.getAvailability("Double"));

        // Update inventory
        System.out.println("\nUpdating Double Room count to 3...");
        inventory.updateAvailability("Double", 3);

        // Display updated inventory
        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();
    }
}