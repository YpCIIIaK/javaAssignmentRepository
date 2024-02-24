enum RoomType {
    SINGLE(100.0),
    DOUBLE(150.0),
    SUITE(200.0);

    private final double price;

    RoomType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}