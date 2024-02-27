class Room {
    private int roomNumber;
    private RoomType type;
    private boolean available;
    private int capacity;

    public Room(int roomNumber, RoomType type, int capacity) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.available = true; // Room is initially available
        this.capacity = capacity;
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCapacity() {
        return capacity;
    }
}