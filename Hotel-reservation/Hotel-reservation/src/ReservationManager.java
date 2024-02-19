import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ReservationManager {
    private List<Reservation> reservations;
    private DatabaseConnector databaseConnector;

    public ReservationManager() {
        this.reservations = new ArrayList<>();
        this.databaseConnector = new DatabaseConnector();
    }

    public void makeReservation(Reservation reservation) {
        reservations.add(reservation);
        // Save reservation to the database
        databaseConnector.saveReservation(reservation);
    }

    public void cancelReservation(Reservation reservation) {
        reservations.remove(reservation);
        // Delete reservation from the database
        databaseConnector.deleteReservation(reservation);
    }

    public List<Reservation> getReservationsForGuest(Guest guest) {
        List<Reservation> guestReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getGuest().equals(guest)) {
                guestReservations.add(reservation);
            }
        }
        return guestReservations;
    }

    public List<Reservation> getReservationsForRoom(Room room) {
        List<Reservation> roomReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room)) {
                roomReservations.add(reservation);
            }
        }
        return roomReservations;
    }

    public List<Reservation> getReservationsForDateRange(Date startDate, Date endDate) {
        List<Reservation> reservationsInRange = new ArrayList<>();
        for (Reservation reservation : reservations) {
            Date resStartDate = reservation.getStartDate();
            Date resEndDate = reservation.getEndDate();
            if ((resStartDate.equals(startDate) || resStartDate.after(startDate)) &&
                    (resEndDate.equals(endDate) || resEndDate.before(endDate))) {
                reservationsInRange.add(reservation);
            }
        }
        return reservationsInRange;
    }
}