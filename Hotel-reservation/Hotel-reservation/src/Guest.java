import java.util.ArrayList;
import java.util.List;

class Guest implements GuestInterface, RoomSelectionInterface {
    private int serialNumber;
    private String fullName;
    private List<Reservation> reservations;
    private double bankAccountBalance;
    private List<Room> availableRooms;

    public Guest(int serialNumber, String fullName, double bankAccountBalance) {
        this.serialNumber = serialNumber;
        this.fullName = fullName;
        this.bankAccountBalance = bankAccountBalance;
        this.reservations = new ArrayList<>();
        this.availableRooms = new ArrayList<>();
    }

    @Override
    public void makeReservation(Reservation reservation) {
        reservations.add(reservation);
        // Set room as unavailable after reservation
        reservation.getRoom().setAvailable(false);
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        reservations.remove(reservation);
        // Set room as available after cancellation
        reservation.getRoom().setAvailable(true);
    }

    @Override
    public Room selectRoom(RoomType type) {
        // Logic to select a room based on the type
        // For example, iterate over available rooms and return the first available room of the specified type
        for (Room room : availableRooms) {
            if (room.getType() == type && room.isAvailable()) {
                return room;
            }
        }
        return null; // Return null if no available room of the specified type is found
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getBankAccountBalance() {
        return bankAccountBalance;
    }

    public void setBankAccountBalance(double bankAccountBalance) {
        this.bankAccountBalance = bankAccountBalance;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Room> getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(List<Room> availableRooms) {
        this.availableRooms = availableRooms;
    }
}