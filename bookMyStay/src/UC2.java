/**
 * UseCase2RoomInitialization
 *
 * Demonstrates Room abstraction, inheritance, and static availability
 * in the Book My Stay application.
 *
 * @version 2.0
 */
abstract class Room {
    protected String roomType;
    protected int beds;
    protected double price;

    // Constructor
    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    // Abstract method
    public abstract void displayDetails();
}

// Single Room
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 2000);
    }

    public void displayDetails() {
        System.out.println(roomType + " | Beds: " + beds + " | Price: ₹" + price);
    }
}

// Double Room
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 3500);
    }

    public void displayDetails() {
        System.out.println(roomType + " | Beds: " + beds + " | Price: ₹" + price);
    }
}

// Suite Room
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 6000);
    }

    public void displayDetails() {
        System.out.println(roomType + " | Beds: " + beds + " | Price: ₹" + price);
    }
}

// Main Class
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("=== Book My Stay App (Version 2.0) ===\n");

        // Creating room objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability variables
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display room details
        System.out.println("Room Details & Availability:\n");

        single.displayDetails();
        System.out.println("Available: " + singleAvailable + "\n");

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable + "\n");

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable + "\n");

        System.out.println("Application finished successfully!");
    }
}
