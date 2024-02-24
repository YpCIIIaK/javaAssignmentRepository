import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize hotel and rooms
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, RoomType.SINGLE, 100.0));
        rooms.add(new Room(2, RoomType.DOUBLE, 150.0));
        rooms.add(new Room(3, RoomType.SUITE, 200.0));
        Hotel hotel = new Hotel("My Hotel", rooms);

        // Display welcome message
        hotel.displayHotelInfo();

        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu options
            System.out.println("\nMenu:");
            System.out.println("1. Make Reservation");
            System.out.println("2. View Available Rooms");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Get user choice
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    makeReservation(scanner, hotel);
                    break;
                case 2:
                    hotel.getReservationManager().displayAvailableRooms(hotel.getRooms());
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to make a reservation
    private static void makeReservation(Scanner scanner, Hotel hotel) {
        System.out.println("\nMake Reservation:");

        // Get customer information
        System.out.print("Enter customer serial number: ");
        int serialNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer bank account: ");
        String bankAccount = scanner.nextLine();
        Customer customer = new Customer(serialNumber, name, bankAccount);

        // Display available rooms
        hotel.getReservationManager().displayAvailableRooms(hotel.getRooms());

        // Get room choice
        System.out.print("Enter room number to reserve: ");
        int roomNumber = scanner.nextInt();
        Room selectedRoom = null;
        for (Room room : hotel.getRooms()) {
            if (room.getNumber() == roomNumber) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Invalid room number.");
            return;
        }

        // Get duration of stay
        System.out.print("Enter duration of stay (in days): ");
        int duration = scanner.nextInt();

        // Make reservation
        try {
            hotel.getReservationManager().makeReservation(customer, selectedRoom, duration);
            System.out.println("Reservation successful!");
        } catch (ReservationException e) {
            System.out.println("Reservation failed: " + e.getMessage());
        }
    }
}