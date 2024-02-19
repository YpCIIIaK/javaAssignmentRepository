class DatabaseConnector {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/hotel_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "omir1306";

    public void saveReservation(Reservation reservation) {
        // Database save logic
        System.out.println("Reservation saved to database.");
    }

    public void deleteReservation(Reservation reservation) {
        // Database delete logic
        System.out.println("Reservation deleted from database.");
    }

    // Other database methods
}