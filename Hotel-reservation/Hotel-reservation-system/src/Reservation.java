public class Reservation {
    private Customer customer; // Customer making the reservation
    private Room room; // Room being reserved
    private int durationInDays; // Duration of stay in days

    public Reservation(Customer customer, Room room, int durationInDays) {
        this.customer = customer;
        this.room = room;
        this.durationInDays = durationInDays;
    }

    // Getters and setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }
}