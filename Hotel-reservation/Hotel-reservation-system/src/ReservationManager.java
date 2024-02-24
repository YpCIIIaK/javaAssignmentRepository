import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationManager {
    private List<Reservation> reservations; // List of reservations

    public ReservationManager() {
        this.reservations = new ArrayList<>();
    }

    // Method to make a reservation
    public void makeReservation(Customer customer, Room room, int durationInDays) throws ReservationException {
        // Check room availability, customer funds, etc.
        // Execute reservation within a transaction
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction
            // Insert reservation data into the database
            String query = "INSERT INTO reservations (customer_serial_number, room_number, duration_in_days) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, customer.getSerialNumber());
                pstmt.setInt(2, room.getNumber());
                pstmt.setInt(3, durationInDays);
                pstmt.executeUpdate();
            }
            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            throw new ReservationException("Failed to make reservation: " + e.getMessage());
        }

        // Create Reservation object and update room availability
        Reservation reservation = new Reservation(customer, room, durationInDays);
        reservations.add(reservation);
        room.setAvailable(false); // Mark the room as unavailable
    }

    // Other methods for reservation management

    // Method to display available rooms
    public void displayAvailableRooms(List<Room> rooms) {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room Number: " + room.getNumber() + ", Type: " + room.getType() + ", Price: $" + room.getPrice() + " per night");
            }
        }
    }
}