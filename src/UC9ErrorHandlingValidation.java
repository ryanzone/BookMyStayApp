/**
 * Use Case 9: Error Handling & Validation
 *
 * This program demonstrates validation and structured error handling
 * in a Hotel Booking Management System using Core Java.
 * It ensures invalid inputs are detected early and handled gracefully.
 */
import java.util.HashMap;
import java.util.Map;

/**
 * Custom exception to represent invalid booking scenarios.
 */
class InvalidBookingException extends Exception {
    /**
     * Constructs an InvalidBookingException with a specific message.
     *
     * @param message the error message describing the issue
     */
    public InvalidBookingException(String message) {
        super(message);
    }
}

/**
 * Manages hotel room inventory and booking operations.
 */
class HotelInventory {

    private Map<String, Integer> rooms;

    /**
     * Initializes the hotel inventory with predefined room types and counts.
     */
    public HotelInventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 5);
        rooms.put("Double", 3);
        rooms.put("Suite", 2);
    }

    /**
     * Validates whether the given room type exists.
     *
     * @param type the room type to validate
     * @throws InvalidBookingException if the room type is invalid
     */
    public void validateRoomType(String type) throws InvalidBookingException {
        if (!rooms.containsKey(type)) {
            throw new InvalidBookingException("Error: Invalid room type '" + type + "'");
        }
    }

    /**
     * Checks whether the requested number of rooms is valid and available.
     *
     * @param type the room type
     * @param count the number of rooms requested
     * @throws InvalidBookingException if count is invalid or exceeds availability
     */
    public void checkAvailability(String type, int count) throws InvalidBookingException {
        int available = rooms.get(type);
        if (count <= 0) {
            throw new InvalidBookingException("Error: Booking count must be greater than 0");
        }
        if (count > available) {
            throw new InvalidBookingException(
                    "Error: Not enough rooms available. Requested: " + count + ", Available: " + available
            );
        }
    }

    /**
     * Books rooms after validating inputs and availability.
     *
     * @param type the room type
     * @param count the number of rooms to book
     * @throws InvalidBookingException if validation fails
     */
    public void bookRoom(String type, int count) throws InvalidBookingException {
        validateRoomType(type);
        checkAvailability(type, count);
        rooms.put(type, rooms.get(type) - count);
        System.out.println("Booking successful for " + count + " " + type + " room(s).");
    }

    /**
     * Displays the current room inventory.
     */
    public void displayInventory() {
        System.out.println("\nCurrent Room Availability:");
        for (String type : rooms.keySet()) {
            System.out.println(type + " Rooms: " + rooms.get(type));
        }
    }
}

/**
 * Main class to execute the booking system with validation and error handling.
 */
public class UC9ErrorHandlingValidation {

    /**
     * Entry point of the program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        HotelInventory hotel = new HotelInventory();

        try {
            hotel.bookRoom("Single", 2);
            hotel.bookRoom("Deluxe", 1);
        } catch (InvalidBookingException e) {
            System.out.println(e.getMessage());
        }

        try {
            hotel.bookRoom("Double", 0);
        } catch (InvalidBookingException e) {
            System.out.println(e.getMessage());
        }

        try {
            hotel.bookRoom("Suite", 5);
        } catch (InvalidBookingException e) {
            System.out.println(e.getMessage());
        }

        hotel.displayInventory();
    }
}