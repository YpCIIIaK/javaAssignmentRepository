enum Occupancy {
    SINGLE("Single", 1),
    DOUBLE("Double", 2),
    SUITE("Suite", 4);

    private final String type;
    private final int capacity;

    Occupancy(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }
}
