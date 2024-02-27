import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hotel Registration");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridBagLayout());

            // Components
            JLabel nameLabel = new JLabel("Guest Name:");
            JTextField nameTextField = new JTextField(15);

            JLabel telephoneLabel = new JLabel("Telephone Number:");
            JTextField telephoneTextField = new JTextField(15);

            JLabel roomTypeLabel = new JLabel("Room Type:");
            JComboBox<RoomType> roomTypeComboBox = new JComboBox<>(RoomType.values());

            JLabel occupancyLabel = new JLabel("Occupancy:");
            JComboBox<Occupancy> occupancyComboBox = new JComboBox<>(Occupancy.values());

            JLabel costPerNightLabel = new JLabel("Cost Per Night: $0");

            JLabel durationLabel = new JLabel("Duration (days):");
            JTextField durationTextField = new JTextField(10);

            JLabel roomSelectionLabel = new JLabel("Select Room:");
            JComboBox<String> roomComboBox = new JComboBox<>();
            roomComboBox.addItem("Choose Room");
            roomComboBox.setEnabled(false);

            JButton makeReservationButton = new JButton("Make Reservation");
            JButton cancelReservationButton = new JButton("Cancel Reservation");

            JTextArea resultTextArea = new JTextArea(5, 20);
            resultTextArea.setEditable(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);

            // Add components to panel
            panel.add(nameLabel, gbc);
            gbc.gridx = 1;
            panel.add(nameTextField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(telephoneLabel, gbc);
            gbc.gridx = 1;
            panel.add(telephoneTextField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(roomTypeLabel, gbc);
            gbc.gridx = 1;
            panel.add(roomTypeComboBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(occupancyLabel, gbc);
            gbc.gridx = 1;
            panel.add(occupancyComboBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            panel.add(costPerNightLabel, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            panel.add(durationLabel, gbc);
            gbc.gridx = 1;
            panel.add(durationTextField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            panel.add(roomSelectionLabel, gbc);
            gbc.gridx = 1;
            panel.add(roomComboBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.gridwidth = 2;
            panel.add(makeReservationButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.gridwidth = 2;
            panel.add(cancelReservationButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.gridwidth = 2;
            panel.add(resultTextArea, gbc);

            frame.add(panel, BorderLayout.NORTH);
            frame.setVisible(true);

            // Initialize rooms
            List<Room> rooms = initializeRooms();

            // Initialize room availability subject
            RoomAvailabilitySubject roomAvailabilitySubject = new RoomAvailabilitySubject();

            // Register observers
            roomAvailabilitySubject.registerObserver(new RoomAvailabilityObserver(resultTextArea));


// Event listener for room type combo box
            roomTypeComboBox.addActionListener(e -> {
                updateAvailableRooms(roomTypeComboBox, occupancyComboBox, rooms, roomComboBox);
                updateCostPerNightLabel((RoomType) Objects.requireNonNull(roomTypeComboBox.getSelectedItem()), (Occupancy) Objects.requireNonNull(occupancyComboBox.getSelectedItem()), costPerNightLabel);
            });

            // Event listener for occupancy combo box
            occupancyComboBox.addActionListener(e -> {
                updateAvailableRooms(roomTypeComboBox, occupancyComboBox, rooms, roomComboBox);
                updateCostPerNightLabel((RoomType) Objects.requireNonNull(roomTypeComboBox.getSelectedItem()), (Occupancy) Objects.requireNonNull(occupancyComboBox.getSelectedItem()), costPerNightLabel);
            });

            // Method to update the cost per night label based on selected room type and occupancy
            updateCostPerNightLabel((RoomType) Objects.requireNonNull(roomTypeComboBox.getSelectedItem()), (Occupancy) occupancyComboBox.getSelectedItem(), costPerNightLabel);

            // Logic for make reservation button
            makeReservationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Check if all fields are filled
                    if (isFormFilled(nameTextField, telephoneTextField, durationTextField) && roomComboBox.getSelectedIndex() != 0) {
                        try (Connection connection = DatabaseConnection.getConnection()) {
                            // Retrieve input values
                            String guestName = nameTextField.getText();
                            String telephoneNumber = telephoneTextField.getText();
                            RoomType selectedRoomType = (RoomType) roomTypeComboBox.getSelectedItem();
                            Occupancy selectedOccupancy = (Occupancy) occupancyComboBox.getSelectedItem();
                            int duration = Integer.parseInt(durationTextField.getText());
                            int roomNumber = Integer.parseInt(roomComboBox.getSelectedItem().toString().replaceAll("[^\\d.]", ""));

                            // Calculate total cost
                            int totalCost = calculateTotalCost(selectedRoomType, duration, selectedOccupancy);

                            // Insert reservation data into the database
                            String sql = "INSERT INTO reservation (guest_name, telephone_number, room_number, duration, total_cost) VALUES (?, ?, ?, ?, ?)";
                            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                                pstmt.setString(1, guestName);
                                pstmt.setString(2, telephoneNumber);
                                pstmt.setInt(3, roomNumber);
                                pstmt.setInt(4, duration);
                                pstmt.setInt(5, totalCost);
                                pstmt.executeUpdate();
                            }

                            // Display result
                            StringBuilder result = new StringBuilder();
                            result.append("Guest Name: ").append(guestName).append("\n");
                            result.append("Telephone Number: ").append(telephoneNumber).append("\n");
                            result.append("Room Type: ").append(selectedRoomType.getType()).append("\n");
                            result.append("Occupancy: ").append(selectedOccupancy.getType()).append("\n");
                            result.append("Duration: ").append(duration).append(" ").append((duration == 1) ? "day" : "days").append("\n");
                            result.append("Total Cost: $").append(totalCost).append("\n");
                            resultTextArea.setText(result.toString());


// Remove the reserved room from the list of available rooms
                            rooms.removeIf(room -> room.getRoomNumber() == roomNumber);

                            // Update room availability
                            roomAvailabilitySubject.updateRoomAvailability((Room) rooms);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame, "Failed to make reservation.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please fill in all fields and select a room.", "Incomplete Form", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            // Logic for cancel reservation button
            cancelReservationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Clear all fields and reset selection
                    nameTextField.setText("");
                    telephoneTextField.setText("");
                    roomTypeComboBox.setSelectedIndex(0);
                    occupancyComboBox.setSelectedIndex(0);
                    durationTextField.setText("");
                    roomComboBox.setSelectedIndex(0);
                    costPerNightLabel.setText("Cost Per Night: $0");
                    resultTextArea.setText("");

                    // Reset room availability
                    roomAvailabilitySubject.updateAvailabilityBasedOnReservations();
                }
            });
        });
    }

    // Method to update available rooms based on selected room type and occupancy
    private static void updateAvailableRooms(JComboBox<RoomType> roomTypeComboBox, JComboBox<Occupancy> occupancyComboBox, List<Room> rooms, JComboBox<String> roomComboBox) {
        RoomType selectedRoomType = (RoomType) Objects.requireNonNull(roomTypeComboBox.getSelectedItem());
        Occupancy selectedOccupancy = (Occupancy) Objects.requireNonNull(occupancyComboBox.getSelectedItem());

        int requiredCapacity = selectedOccupancy.getCapacity();
        int requiredRooms = 0;

        switch (selectedRoomType) {
            case ECONOMY:
                requiredRooms = selectedOccupancy == Occupancy.SINGLE ? 5 :
                        selectedOccupancy == Occupancy.DOUBLE ? 4 :
                                selectedOccupancy == Occupancy.SUITE ? 2 : 0;
                break;
            case COMFORT:
                requiredRooms = selectedOccupancy == Occupancy.SINGLE ? 5 :
                        selectedOccupancy == Occupancy.DOUBLE ? 5 :
                                selectedOccupancy == Occupancy.SUITE ? 3 : 0;
                break;
            case LUXURY:
                requiredRooms = selectedOccupancy == Occupancy.SINGLE ? 4 :
                        selectedOccupancy == Occupancy.DOUBLE ? 4 :
                                selectedOccupancy == Occupancy.SUITE ? 4 : 0;
                break;
        }

        int finalRequiredRooms = requiredRooms;

        List<Room> availableRooms = rooms.stream()
                .filter(Room::isAvailable)
                .filter(room -> room.getType() == selectedRoomType && room.getCapacity() == requiredCapacity)
                .limit(finalRequiredRooms)
                .collect(Collectors.toList());

        // Update room selection combo box with available rooms
        roomComboBox.removeAllItems();
        roomComboBox.addItem("Choose Room");
        if (!availableRooms.isEmpty()) {
            for (Room room : availableRooms) {
                roomComboBox.addItem("Room " + room.getRoomNumber());
            }
            roomComboBox.setEnabled(true);
        } else {
            roomComboBox.setEnabled(false);
        }
    }


    // Method to update the cost per night label based on selected room type and occupancy
    private static void updateCostPerNightLabel(RoomType roomType, Occupancy occupancy, JLabel costPerNightLabel) {
        int costPerNight = roomType.getCost() * occupancy.getCapacity();
        costPerNightLabel.setText("Cost Per Night: $" + costPerNight);
    }

    // Method to check if all fields are filled
    private static boolean isFormFilled(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Method to calculate total cost
    private static int calculateTotalCost(RoomType roomType, int duration, Occupancy occupancy) {
        int costPerNight = roomType.getCost() * occupancy.getCapacity();
        return costPerNight * duration;
    }

    // Method to initialize rooms
    private static List<Room> initializeRooms() {
        List<Room> rooms = new ArrayList<>();

        // Add economy rooms
        for (int i = 1; i <= 5; i++) {
            rooms.add(new Room(i, RoomType.ECONOMY, 1));
        }
        for (int i = 6; i <= 9; i++) {
            rooms.add(new Room(i, RoomType.ECONOMY, 2));
        }
        for (int i = 10; i <= 11; i++) {
            rooms.add(new Room(i, RoomType.ECONOMY, 4));
        }

        // Add comfort rooms
        for (int i = 12; i <= 16; i++) {
            rooms.add(new Room(i, RoomType.COMFORT, 1));
        }
        for (int i = 17; i <= 20; i++) {
            rooms.add(new Room(i, RoomType.COMFORT, 2));
        }
        for (int i = 21; i <= 22; i++) {
            rooms.add(new Room(i, RoomType.COMFORT, 4));
        }

        // Add luxury rooms
        for (int i = 23; i <= 27; i++) {
            rooms.add(new Room(i, RoomType.LUXURY, 1));
        }
        for (int i = 28; i <= 31; i++) {
            rooms.add(new Room(i, RoomType.LUXURY, 2));
        }
        for (int i = 32; i <= 33; i++) {
            rooms.add(new Room(i, RoomType.LUXURY, 4));
        }

        return rooms;
    }
}