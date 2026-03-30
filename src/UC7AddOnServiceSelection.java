import java.util.*;

/**
 * Use Case 7: Add-On Service Selection
 *
 * This program demonstrates how optional services can be added to an existing
 * reservation without modifying core booking or inventory logic.
 *
 * A one-to-many relationship is maintained between reservation IDs and selected services
 * using a Map and List combination. The system supports extensibility by allowing
 * new services to be added dynamically without affecting core functionality.
 *
 * Features:
 * - Attach multiple services to a single reservation
 * - Maintain mapping using Map<String, List<AddOnService>>
 * - Calculate total additional cost separately
 * - Preserve core booking and inventory integrity
 *
 * Concepts Used:
 * - Business Extensibility
 * - One-to-Many Relationship
 * - Composition over Inheritance
 * - Separation of Concerns
 * - Cost Aggregation
 *
 * Developer: Ryan John Mathew
 * Version: 7.1
 */

public class UC7AddOnServiceSelection {

    static class AddOnService {
        private String serviceName;
        private double cost;

        public AddOnService(String serviceName, double cost) {
            this.serviceName = serviceName;
            this.cost = cost;
        }

        public String getServiceName() {
            return serviceName;
        }

        public double getCost() {
            return cost;
        }

        public String toString() {
            return serviceName + " (₹" + cost + ")";
        }
    }

    static class AddOnServiceManager {

        private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

        public void addService(String reservationId, AddOnService service) {
            serviceMap.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
        }

        public List<AddOnService> getServices(String reservationId) {
            return serviceMap.getOrDefault(reservationId, new ArrayList<>());
        }

        public double calculateTotalCost(String reservationId) {
            double total = 0;
            List<AddOnService> services = getServices(reservationId);

            for (AddOnService service : services) {
                total += service.getCost();
            }

            return total;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AddOnServiceManager manager = new AddOnServiceManager();

        System.out.print("Enter Reservation ID: ");
        String reservationId = scanner.nextLine();

        System.out.print("Enter number of add-on services: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        List<AddOnService> selectedServices = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter service name: ");
            String name = scanner.nextLine();

            System.out.print("Enter service cost: ");
            double cost = scanner.nextDouble();
            scanner.nextLine();

            AddOnService service = new AddOnService(name, cost);
            selectedServices.add(service);
        }

        for (AddOnService service : selectedServices) {
            manager.addService(reservationId, service);
        }

        System.out.println("\nSelected Services for Reservation " + reservationId + ":");
        for (AddOnService service : manager.getServices(reservationId)) {
            System.out.println("- " + service);
        }

        double totalCost = manager.calculateTotalCost(reservationId);
        System.out.println("Total Add-On Cost: ₹" + totalCost);

        scanner.close();
    }
}
