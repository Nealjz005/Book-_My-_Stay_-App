import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * Demonstrates centralized room inventory using HashMap.
 *
 * @version 3.0
 */

// Inventory Class
class RoomInventory {

    // HashMap to store room type → availability
    private Map<String, Integer> inventory;

    // Constructor - initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initial room availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability of a room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (e.g., booking or cancellation)
    public void updateAvailability(String roomType, int change) {
        int current = inventory.getOrDefault(roomType, 0);
        int updated = current + change;

        if (updated >= 0) {
            inventory.put(roomType, updated);
        } else {
            System.out.println("Not enough rooms available for " + roomType);
        }
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " → Available: " + entry.getValue());
        }
    }
}

// Main Class
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("=== Book My Stay App (Version 3.0) ===");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Simulate booking (reduce availability)
        System.out.println("\nBooking 1 Single Room...");
        inventory.updateAvailability("Single Room", -1);

        // Simulate cancellation (increase availability)
        System.out.println("Cancelling 1 Double Room...");
        inventory.updateAvailability("Double Room", +1);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("\nApplication finished successfully!");
    }
}