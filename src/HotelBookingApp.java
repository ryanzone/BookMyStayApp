/**
 * Version: 2.1
 *
 * Use Case 2: Basic Room Types & Static Availability
 * -------------------------------------------------
 * Demonstrates object-oriented design using abstraction, inheritance,
 * polymorphism, and encapsulation in a hotel booking context.
 *
 * Room types are modeled using an abstract class and concrete subclasses.
 * Availability is stored using simple variables to highlight limitations
 * before introducing data structures in later use cases.
 *
 * Author: Ryan John
 */

abstract class Room {
    private String roomType;
    private int beds;
    private double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 2000);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 3500);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 6000);
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("===== Room Details & Availability =====\n");

        single.displayRoomDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println("----------------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println("----------------------------------");

        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvailable);
        System.out.println("----------------------------------");

        System.out.println("\nApplication Terminated.");
    }
}