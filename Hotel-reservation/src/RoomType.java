enum RoomType {
    LUXURY("Luxury", 200, 2),
    ECONOMY("Economy", 100, 1),
    COMFORT("Comfort", 150, 2);

    private final String type;
    private final int cost;
    private final int capacity;

    RoomType(String type, int cost, int capacity) {
        this.type = type;
        this.cost = cost;
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }
}