public class Room {
    private int number; // Room number
    private RoomType type; // Type of room (e.g., Single, Double, Suite)
    private double price; // Price per night
    private boolean available; // Availability status

    public Room(int number, RoomType type, double price) throws IllegalArgumentException {
        if (number < 1 || number > 30) {
            throw new IllegalArgumentException("Room number must be between 1 and 30.");
        }
        this.number = number;
        this.type = type;
        this.price = price;
        this.available = true; // Room is initially available
    }

    // Getters and setters
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) throws IllegalArgumentException {
        if (number < 1 || number > 30) {
            throw new IllegalArgumentException("Room number must be between 1 and 30.");
        }
        this.number = number;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}