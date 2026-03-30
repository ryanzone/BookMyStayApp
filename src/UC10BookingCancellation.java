/**
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * This program demonstrates safe cancellation of bookings by reversing
 * system state changes using a stack-based rollback mechanism.
 * It ensures inventory consistency and controlled recovery behavior.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
import java.util.*;

/**
 * Custom exception to represent invalid cancellation scenarios.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
class CancellationException extends Exception {
    /**
     * Constructs a CancellationException with a specific message.
     *
     * @param message the error message
     */
    public CancellationException(String message) {
        super(message);
    }
}

/**
 * Represents a booking entity.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
class Booking {
    String bookingId;
    String roomType;
    String roomId;
    boolean isActive;

    /**
     * Initializes a booking with given details.
     *
     * @param bookingId unique booking identifier
     * @param roomType type of room
     * @param roomId allocated room ID
     */
    public Booking(String bookingId, String roomType, String roomId) {
        this.bookingId = bookingId;
        this.roomType = roomType;
        this.roomId = roomId;
        this.isActive = true;
    }
}

/**
 * Manages hotel bookings and inventory.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
class BookingManager {

    private Map<String, Integer> inventory;
    private Map<String, Booking> bookings;
    private Stack<String> rollbackStack;

    /**
     * Initializes inventory, booking records, and rollback stack.
     */
    public BookingManager() {
        inventory = new HashMap<>();
        bookings = new HashMap<>();
        rollbackStack = new Stack<>();

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    /**
     * Creates a booking if inventory is available.
     *
     * @param bookingId booking identifier
     * @param roomType room type
     */
    public void createBooking(String bookingId, String roomType) {
        if (!inventory.containsKey(roomType) || inventory.get(roomType) <= 0) {
            System.out.println("Booking failed: No availability for " + roomType);
            return;
        }

        String roomId = roomType.substring(0, 1) + (inventory.get(roomType));
        Booking booking = new Booking(bookingId, roomType, roomId);

        bookings.put(bookingId, booking);
        inventory.put(roomType, inventory.get(roomType) - 1);

        System.out.println("Booking confirmed: " + bookingId + " -> " + roomId);
    }

    /**
     * Cancels a booking and performs rollback.
     *
     * @param bookingId booking identifier
     * @throws CancellationException if booking is invalid or already cancelled
     */
    public void cancelBooking(String bookingId) throws CancellationException {
        if (!bookings.containsKey(bookingId)) {
            throw new CancellationException("Error: Booking does not exist.");
        }

        Booking booking = bookings.get(bookingId);

        if (!booking.isActive) {
            throw new CancellationException("Error: Booking already cancelled.");
        }

        rollbackStack.push(booking.roomId);
        inventory.put(booking.roomType, inventory.get(booking.roomType) + 1);
        booking.isActive = false;

        System.out.println("Cancellation successful for Booking ID: " + bookingId);
        System.out.println("Released Room ID: " + rollbackStack.peek());
    }

    /**
     * Displays current inventory.
     */
    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }

    /**
     * Displays booking history.
     */
    public void displayBookings() {
        System.out.println("\nBooking Records:");
        for (Booking b : bookings.values()) {
            System.out.println(
                    b.bookingId + " | " + b.roomType + " | " + b.roomId + " | Active: " + b.isActive
            );
        }
    }
}

/**
 * Main class to demonstrate booking cancellation and rollback.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
public class UC10BookingCancellation {

    /**
     * Entry point of the program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        BookingManager manager = new BookingManager();

        manager.createBooking("B101", "Single");
        manager.createBooking("B102", "Double");

        try {
            manager.cancelBooking("B101");
        } catch (CancellationException e) {
            System.out.println(e.getMessage());
        }

        try {
            manager.cancelBooking("B101");
        } catch (CancellationException e) {
            System.out.println(e.getMessage());
        }

        try {
            manager.cancelBooking("B999");
        } catch (CancellationException e) {
            System.out.println(e.getMessage());
        }

        manager.displayInventory();
        manager.displayBookings();
    }
}