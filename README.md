# Hotel Reservation System

### Creators: Vladimirov Vladimir, Tinalin Arsen, Sabyraly Gaukharbek, Abishev Beibarys, Seitkadyr Alisher, Yesbossynov Sanzhar

### Group: SE - 2329

# Overall functionality:

  - The program initializes a hotel with rooms and a reservation manager.
  - Users can interact with the system through a menu-driven interface.
  - They can make reservations by providing their information and choosing a room and duration.
  - The reservation manager checks room availability, customer funds (not implemented in this example), and performs other validations.
  - If everything is valid, a reservation is created and stored in the database.
  - Users can also view available rooms and exit the program.


# Classes

## Customer:

  - Represents a customer with attributes like serial number, name, and bank account.
  - Used to validate customer information during reservation.

## Room:

  - Represents a room in the hotel with attributes like number, type (e.g., single, double), price, and availability status.
  - Used to display available rooms and check their availability during reservation.

## Reservation:

  - Represents a reservation made by a customer for a specific room and duration.
  - Used to manage the list of reservations.

## ReservationManager:

  - Manages reservations by adding, removing, and editing them.
  - Checks room availability and processes payments (not implemented in this example).
  - Interacts with the database to store reservation information.

## Hotel:

  - Represents the hotel, its name, and list of rooms.
  - Used to display hotel information.

## Main:

  - Launches the program and provides an interface for user interaction.
  - Allows users to make reservations, view available rooms, and exit the system.

## DatabaseConnection:

  - Provides a connection to the database where reservation information is stored.


# What happens after code initialization?

  ## The user is presented with a menu:
  
  ## Make a reservation:
  
  - Enter information about yourself and the desired room.
  - The system will check availability, validity, and payment capability.
  - If everything is ok, it will create a reservation and save it to the database.
    
  ## View available rooms:
  
  - The system will show a list of available rooms.
    
  ## Exit:
  
  - Stop the program.
  
