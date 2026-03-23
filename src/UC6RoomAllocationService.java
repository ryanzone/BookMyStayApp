import java.util.*;

/**
 * ================================================================
 * MAIN CLASS – UseCase6RoomAllocationService
 * ================================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Handles booking confirmation with uniqueness and consistency.
 *
 * @author Ryan John Mathew
 * @version 6.1
 */

public class UC6RoomAllocationService {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Booking queue (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();
        bookingQueue.add(new Reservation("Alice", "Single"));
        bookingQueue.add(new Reservation("Bob", "Single"));
        bookingQueue.add(new Reservation("Charlie", "Single")); // should fail
        bookingQueue.add(new Reservation("David", "Double"));

        // Track allocated room IDs (GLOBAL uniqueness)
        Set<String> allocatedRoomIds = new HashSet<>();

        // Track room type → assigned IDs
        Map<String, Set<String>> roomAllocations = new HashMap<>();

        System.out.println("Processing Booking Requests...\n");

        while (!bookingQueue.isEmpty()) {

            Reservation request = bookingQueue.poll(); // FIFO
            String type = request.getRoomType();

            int available = inventory.getAvailability(type);

            if (available > 0) {

                // Generate unique room ID
                String roomId = generateRoomId(type, allocatedRoomIds);

                // Store in global set
                allocatedRoomIds.add(roomId);

                // Map room type → IDs
                roomAllocations
                        .computeIfAbsent(type, k -> new HashSet<>())
                        .add(roomId);

                // Decrement inventory immediately
                inventory.decrementRoom(type);

                // Confirm booking
                System.out.println("CONFIRMED → " + request.getGuestName()
                        + " | Room: " + roomId);

            } else {
                System.out.println("FAILED → " + request.getGuestName()
                        + " | No " + type + " rooms available");
            }
        }

        // Final state
        System.out.println("\nFinal Allocations:");
        System.out.println(roomAllocations);

        System.out.println("\nRemaining Inventory:");
        inventory.displayInventory();
    }

    /**
     * Generates a UNIQUE room ID
     */
    private static String generateRoomId(String type, Set<String> existingIds) {

        String roomId;
        do {
            roomId = type.substring(0, 1).toUpperCase()
                    + (int) (Math.random() * 1000);
        } while (existingIds.contains(roomId));

        return roomId;
    }
}