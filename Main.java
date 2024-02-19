import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Example usage
        Room room1 = new Room(101, RoomType.ECONOMY);
        Room room2 = new Room(102, RoomType.COMFORT);
        Room room3 = new Room(103, RoomType.LUXURY);

        List<Room> availableRooms = new ArrayList<>();
        availableRooms.add(room1);
        availableRooms.add(room2);
        availableRooms.add(room3);

        Guest guest1 = new Guest(1, "Omir Bayan", 500.0);
        guest1.setAvailableRooms(availableRooms);

        ReservationManager reservationManager = new ReservationManager();
        Room selectedRoom = guest1.selectRoom(RoomType.COMFORT);
        if (selectedRoom != null) {
            Reservation reservation1 = new Reservation(1, guest1, selectedRoom, new Date(), new Date());
            reservationManager.makeReservation(reservation1);
        } else {
            System.out.println("No available room of the specified type.");
        }
    }
}