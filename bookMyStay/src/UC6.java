import java.util.*;

/**
 * UseCase6RoomAllocationService
 *
 * Demonstrates room allocation with uniqueness and inventory update.
 *
 * @version 6.0
 */

// Reservation class
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Inventory Service
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrement(String roomType) {
        int current = inventory.get(roomType);
        inventory.put(roomType, current - 1);
    }

    public void displayInventory() {
        System.out.println("\n--- Inventory Status ---");
        for (String key : inventory.keySet()) {
            System.out.println(key + " → " + inventory.get(key));
        }
    }
}

// Booking Service
class BookingService {

    private Queue<Reservation> queue;
    private RoomInventory inventory;

    // Track allocated room IDs (global uniqueness)
    private Set<String> allocatedRooms = new HashSet<>();

    // Map room type → assigned room IDs
    private Map<String, Set<String>> roomAllocations = new HashMap<>();

    public BookingService(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    // Generate unique room ID
    private String generateRoomId(String roomType) {
        return roomType.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 5);
    }

    // Process bookings
    public void processBookings() {
        System.out.println("\n--- Processing Booking Requests ---");

        while (!queue.isEmpty()) {
            Reservation req = queue.poll(); // FIFO

            String type = req.getRoomType();

            if (inventory.getAvailability(type) > 0) {

                String roomId;

                // Ensure unique room ID
                do {
                    roomId = generateRoomId(type);
                } while (allocatedRooms.contains(roomId));

                allocatedRooms.add(roomId);

                // Map room type → room IDs
                roomAllocations.putIfAbsent(type, new HashSet<>());
                roomAllocations.get(type).add(roomId);

                // Update inventory
                inventory.decrement(type);

                System.out.println("Booking CONFIRMED for " + req.getGuestName()
                        + " | Room: " + type
                        + " | Room ID: " + roomId);

            } else {
                System.out.println("Booking FAILED for " + req.getGuestName()
                        + " | No rooms available for " + type);
            }
        }
    }

    public void displayAllocations() {
        System.out.println("\n--- Room Allocations ---");
        for (String type : roomAllocations.keySet()) {
            System.out.println(type + " → " + roomAllocations.get(type));
        }
    }
}

// Main Class
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("=== Book My Stay App (Version 6.0) ===");

        // Initialize queue (FIFO)
        Queue<Reservation> queue = new LinkedList<>();
        queue.offer(new Reservation("Alice", "Single Room"));
        queue.offer(new Reservation("Bob", "Single Room"));
        queue.offer(new Reservation("Charlie", "Single Room")); // should fail
        queue.offer(new Reservation("David", "Suite Room"));

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Booking service
        BookingService service = new BookingService(queue, inventory);

        // Process bookings
        service.processBookings();

        // Show results
        service.displayAllocations();
        inventory.displayInventory();

        System.out.println("\nAll bookings processed.");
    }
}