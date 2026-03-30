import java.util.*;

/**
 * Use Case 8: Booking History & Reporting
 *
 * This program introduces historical tracking of confirmed bookings to enable
 * operational visibility, auditing, and reporting without using external storage.
 *
 * Confirmed reservations are stored in insertion order using a List, forming
 * a chronological record of bookings. A separate reporting service processes
 * stored data to generate summaries without modifying the original records.
 *
 * Features:
 * - Store confirmed reservations in booking history
 * - Maintain insertion order for chronological tracking
 * - Retrieve booking history for review
 * - Generate summary reports
 * - Preserve data integrity during reporting
 *
 * Concepts Used:
 * - Operational Visibility
 * - List Data Structure
 * - Ordered Storage
 * - Historical Tracking
 * - Reporting Readiness
 * - Separation of Concerns
 * - Persistence Mindset (In-Memory)
 *
 * Developer: Ryan John Mathew
 * Version: 8.1
 */

public class UC8BookingHistoryReport {

    static class Reservation {
        private String reservationId;
        private String guestName;
        private String roomType;

        public Reservation(String reservationId, String guestName, String roomType) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getReservationId() {
            return reservationId;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }

        public String toString() {
            return "Reservation ID: " + reservationId +
                    ", Guest: " + guestName +
                    ", Room Type: " + roomType;
        }
    }

    static class BookingHistory {

        private List<Reservation> history = new ArrayList<>();

        public void addReservation(Reservation reservation) {
            history.add(reservation);
        }

        public List<Reservation> getAllReservations() {
            return history;
        }
    }

    static class BookingReportService {

        public void generateReport(List<Reservation> reservations) {
            System.out.println("\n--- Booking Report ---");
            System.out.println("Total Reservations: " + reservations.size());

            Map<String, Integer> roomTypeCount = new HashMap<>();

            for (Reservation r : reservations) {
                roomTypeCount.put(
                        r.getRoomType(),
                        roomTypeCount.getOrDefault(r.getRoomType(), 0) + 1
                );
            }

            System.out.println("\nRoom Type Summary:");
            for (String type : roomTypeCount.keySet()) {
                System.out.println(type + ": " + roomTypeCount.get(type));
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        System.out.print("Enter number of confirmed bookings: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Reservation ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter Guest Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Room Type: ");
            String room = scanner.nextLine();

            Reservation reservation = new Reservation(id, name, room);
            history.addReservation(reservation);
        }

        System.out.println("\n--- Booking History ---");
        for (Reservation r : history.getAllReservations()) {
            System.out.println(r);
        }

        reportService.generateReport(history.getAllReservations());

        scanner.close();
    }
}
