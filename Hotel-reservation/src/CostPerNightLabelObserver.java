import javax.swing.*;

class CostPerNightLabelObserver implements Observer {
    private JLabel costPerNightLabel;

    public CostPerNightLabelObserver(JLabel costPerNightLabel) {
        this.costPerNightLabel = costPerNightLabel;
    }

    @Override
    public void update(String message) {
        if (message.equals("Room Type Changed") || message.equals("Occupancy Changed")) {
            RoomType selectedRoomType = (RoomType) RoomType.values()[0]; // Get the first room type
            Occupancy selectedOccupancy = (Occupancy) Occupancy.values()[0]; // Get the first occupancy
            int costPerNight = selectedRoomType.getCost() * selectedOccupancy.getCapacity();
            costPerNightLabel.setText("Cost Per Night: $" + costPerNight);
        }
    }
}