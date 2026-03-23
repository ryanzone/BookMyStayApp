import java.util.LinkedList;
import java.util.Queue;

/**
 * ================================================================
 * MAIN CLASS – UseCase5BookingRequestQueue
 * ================================================================
 *
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Demonstrates FIFO booking request handling using Queue.
 * 1. Create booking request queue
 * 2. Guest submits booking requests
 * 3. Display queue (without removing)
 * 4. IMPORTANT: No removal, no allocation, no inventory update
 * @author Ryan John Mathew
 * @version 5.1
 */

public class UC5BookingRequestQueue {

    public static void main(String[] args) {


        Queue<Reservation> bookingQueue = new LinkedList<>();


        bookingQueue.add(new Reservation("Alice", "Single"));
        bookingQueue.add(new Reservation("Bob", "Double"));
        bookingQueue.add(new Reservation("Charlie", "Suite"));
        bookingQueue.add(new Reservation("David", "Single"));

        System.out.println("Booking Requests Received (FIFO Order):\n");

        for (Reservation r : bookingQueue) {
            System.out.println(r);
        }

        System.out.println("\nNext request to be processed:");
        System.out.println(bookingQueue.peek()); // FIFO check

    }
}