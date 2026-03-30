/**
 * Use Case 12: Data Persistence & System Recovery
 *
 * This program demonstrates persistence and recovery of system state
 * using serialization and deserialization without redefining existing
 * Booking and BookingManager classes.
 *
 * @author Ryan John Mathew
 * @version 12.0
 */
import java.io.*;

/**
 * Represents a wrapper for persisting BookingManager state.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
class SystemState implements Serializable {

    private static final long serialVersionUID = 1L;

    BookingManager manager;

    /**
     * Initializes system state with BookingManager.
     *
     * @param manager booking manager instance
     */
    public SystemState(BookingManager manager) {
        this.manager = manager;
    }

    /**
     * Returns stored BookingManager.
     *
     * @return booking manager
     */
    public BookingManager getManager() {
        return manager;
    }
}

/**
 * Handles persistence operations.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    /**
     * Saves system state to file.
     *
     * @param state system state object
     */
    public void saveState(SystemState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    /**
     * Loads system state from file.
     *
     * @return restored system state or null if unavailable
     */
    public SystemState loadState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("System state loaded successfully.");
            return (SystemState) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous state found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading state. Starting with safe defaults.");
        }
        return null;
    }
}

/**
 * Main class to demonstrate persistence and recovery.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
public class UC12DataPersistenceRecover {

    /**
     * Entry point of the program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();
        BookingManager manager;

        SystemState loadedState = service.loadState();

        if (loadedState != null) {
            manager = loadedState.getManager();
            System.out.println("State restored successfully.");
        } else {
            manager = new BookingManager();
        }

        manager.createBooking("B301", "Single");
        manager.createBooking("B302", "Double");

        manager.displayInventory();
        manager.displayBookings();

        service.saveState(new SystemState(manager));
    }
}