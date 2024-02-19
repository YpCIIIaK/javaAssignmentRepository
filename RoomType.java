enum RoomType {
    ECONOMY(50.0),
    COMFORT(100.0),
    LUXURY(200.0);

    private final double price;

    RoomType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
