import java.util.*;

/**
 * UseCase4RoomSearch
 *
 * Demonstrates read-only room search using inventory without modifying state.
 *
 * @version 4.0
 */

// Abstract Room class
abstract class Room {
    protected String type;
    protected double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public abstract void display();
}

// Concrete Room classes
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 2000);
    }

    public void display() {
        System.out.println(type + " | Price: ₹" + price);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 3500);
    }

    public void display() {
        System.out.println(type + " | Price: ₹" + price);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 6000);
    }

    public void display() {
        System.out.println(type + " | Price: ₹" + price);
    }
}

// Inventory class (read-only access here)
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0); // unavailable
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

// Search Service
class RoomSearchService {

    public void searchRooms(RoomInventory inventory, List<Room> rooms) {
        System.out.println("\n--- Available Rooms ---");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getType());

            // Only show available rooms
            if (available > 0) {
                room.display();
                System.out.println("Available: " + available + "\n");
            }
        }
    }
}

// Main Class
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("=== Book My Stay App (Version 4.0) ===");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Create room objects
        List<Room> rooms = new ArrayList<>();
        rooms.add(new SingleRoom());
        rooms.add(new DoubleRoom());
        rooms.add(new SuiteRoom());

        // Search service
        RoomSearchService searchService = new RoomSearchService();

        // Perform search (read-only)
        searchService.searchRooms(inventory, rooms);

        System.out.println("Search completed successfully!");
    }
}