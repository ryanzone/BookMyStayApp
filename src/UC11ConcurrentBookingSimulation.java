/**
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 *
 * This program demonstrates how concurrent booking requests are handled
 * safely using synchronization to avoid race conditions and ensure
 * consistent inventory updates.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
import java.util.*;

/**
 * Represents a booking request.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
class BookingRequest {
    String guestName;
    String roomType;

    /**
     * Initializes a booking request.
     *
     * @param guestName name of the guest
     * @param roomType type of room requested
     */
    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

/**
 * Manages shared inventory with synchronized access.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
class ConcurrentInventory {

    private Map<String, Integer> inventory;

    /**
     * Initializes inventory with room types and counts.
     */
    public ConcurrentInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 3);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    /**
     * Allocates a room in a thread-safe manner.
     *
     * @param request booking request
     */
    public synchronized void allocateRoom(BookingRequest request) {
        String type = request.roomType;

        if (!inventory.containsKey(type)) {
            System.out.println("Invalid room type for " + request.guestName);
            return;
        }

        int available = inventory.get(type);

        if (available > 0) {
            inventory.put(type, available - 1);
            System.out.println(
                    Thread.currentThread().getName() + " allocated " + type +
                            " room to " + request.guestName
            );
        } else {
            System.out.println(
                    Thread.currentThread().getName() + " failed for " +
                            request.guestName + " (No " + type + " rooms left)"
            );
        }
    }

    /**
     * Displays current inventory.
     */
    public void displayInventory() {
        System.out.println("\nFinal Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

/**
 * Processes booking requests from a shared queue.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
class BookingProcessor implements Runnable {

    private Queue<BookingRequest> queue;
    private ConcurrentInventory inventory;

    /**
     * Initializes the processor with shared queue and inventory.
     *
     * @param queue shared booking queue
     * @param inventory shared inventory
     */
    public BookingProcessor(Queue<BookingRequest> queue, ConcurrentInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    /**
     * Runs the thread to process booking requests.
     */
    public void run() {
        while (true) {
            BookingRequest request;

            synchronized (queue) {
                if (queue.isEmpty()) {
                    break;
                }
                request = queue.poll();
            }

            if (request != null) {
                inventory.allocateRoom(request);
            }
        }
    }
}

/**
 * Main class to simulate concurrent booking requests.
 *
 * @author Ryan John Mathew
 * @version 1.0
 */
public class UC11ConcurrentBookingSimulation {

    /**
     * Entry point of the program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        Queue<BookingRequest> queue = new LinkedList<>();
        ConcurrentInventory inventory = new ConcurrentInventory();

        queue.add(new BookingRequest("Guest1", "Single"));
        queue.add(new BookingRequest("Guest2", "Single"));
        queue.add(new BookingRequest("Guest3", "Single"));
        queue.add(new BookingRequest("Guest4", "Double"));
        queue.add(new BookingRequest("Guest5", "Double"));
        queue.add(new BookingRequest("Guest6", "Suite"));
        queue.add(new BookingRequest("Guest7", "Suite"));

        Thread t1 = new Thread(new BookingProcessor(queue, inventory), "Thread-1");
        Thread t2 = new Thread(new BookingProcessor(queue, inventory), "Thread-2");
        Thread t3 = new Thread(new BookingProcessor(queue, inventory), "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        inventory.displayInventory();
    }
}