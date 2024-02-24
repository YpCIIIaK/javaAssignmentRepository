import java.util.List;

public class Hotel {
    private String name; // Hotel name
    private List<Room> rooms; // List of rooms in the hotel
    private ReservationManager reservationManager; // Reservation manager for the hotel

    public Hotel(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
        this.reservationManager = new ReservationManager();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public ReservationManager getReservationManager() {
        return reservationManager;
    }

    public void setReservationManager(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    // Method to display hotel information
    public void displayHotelInfo() {
        System.out.println("Welcome to " + name);
    }
}