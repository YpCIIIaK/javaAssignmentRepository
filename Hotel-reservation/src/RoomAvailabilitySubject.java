import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class RoomAvailabilitySubject implements Subject {
    private List<Observer> observers;
    private List<Room> rooms;

    public RoomAvailabilitySubject() {
        this.observers = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // Additional method to update room availability
    public void updateRoomAvailability(Room room) {
        room.setAvailable(true); // For example, mark room as available
        notifyObservers("Room " + room.getRoomNumber() + " is now available.");
    }

    // Additional method to add rooms
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Additional method to remove rooms
    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    // Method to update room availability based on reservation status
    public void updateAvailabilityBasedOnReservations() {
        // Iterate over all rooms
        for (Room room : rooms) {
            // Check if there is any active reservation for the room
            boolean isReserved = checkReservationStatusForRoom(room);

            // Update room availability based on reservation status
            room.setAvailable(!isReserved);

            // Notify observers about the updated availability
            if (isReserved) {
                notifyObservers("Room " + room.getRoomNumber() + " is now reserved.");
            } else {
                notifyObservers("Room " + room.getRoomNumber() + " is now available.");
            }
        }
    }

    // Method to check if a room is currently reserved
    private boolean checkReservationStatusForRoom(Room room) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM reservations WHERE room_number = ? AND start_date <= CURRENT_DATE AND end_date >= CURRENT_DATE";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, room.getRoomNumber());
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int reservationCount = rs.getInt(1);
                        return reservationCount > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Default to false if an error occurs or no reservations are found
    }

    // Additional method to retrieve all rooms
    public List<Room> getRooms() {
        return rooms;
    }
}
