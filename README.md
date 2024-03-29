# Hotel Reservation System

### Creators:  Abishev Beibarys, Vladimirov Vladimir, Tinalin Arsen, Sabyraly Gaukharbek, Seitkadyr Alisher, Yesbossynov Sanzhar

### Group: SE - 2329

# Overall functionality:

  - ### Manages Room Data: It maintains a list of available rooms with details like type, cost, capacity, and availability status.
  - ### Tracks Reservations: It updates room availability based on reservations stored in a database (likely accessed through DatabaseConnection).
  - ### Notifies UI Updates: It utilizes the observer pattern to notify different UI components about changes in room availability or reservation status.
  - ### Calculates Cost: It calculates the cost per night based on the selected room type and occupancy.
  - ### Provides Reservation Management: It offers functionalities to add, remove, and cancel reservations, updating the system accordingly.


# Classes

## 1. Room

  ### Represents: 
  - A physical hotel room.
  ### Properties:
  - roomNumber: A unique identifier for the room.
  - type: The type of room (e.g., RoomType.LUXURY, RoomType.ECONOMY).
  - available: A boolean flag indicating if the room is currently available.
  - capacity: The maximum number of guests the room can accommodate.
  ###Purpose: Stores essential data about each room necessary for the reservation system.

## 2. RoomAvailabilitySubject

  ### Represents: 
  
  The central manager of room availability within the system.
  
   ### Properties:
  
  - observers: A list of Observer objects that are interested in room availability updates.
  - rooms: A list of Room objects that it manages.
    
  Methods:
  
  - registerObserver, removeObserver, notifyObservers: Functions to manage the list of subscribed observers.
  - updateRoomAvailability: Used to update the availability of a set of rooms, likely after querying a database.
  - addRoom, removeRoom: Methods to manipulate the list of managed rooms.
  - updateAvailabilityBasedOnReservations: Checks reservation status for each room and updates availability, notifying observers.
  - checkReservationStatusForRoom: A helper function to query the database to determine if a room is reserved.
  - cancelReservation: Updates availability for a canceled reservation and notifies observers.
    
  Purpose: The core class that keeps track of available rooms, updates availability based on reservations, and broadcasts those changes to the relevant parts of the system.

## 3. Observer Classes (CostPerNightLabelObserver, ResultTextAreaObserver, RoomAvailabilityObserver)

  Common Interface: They all implement the Observer interface, providing the update(String message) method.
  
  Specific Actions: 
    Each observer reacts differently to updates:

  - CostPerNightLabelObserver: Updates a label to display the cost per night based on selected room type and occupancy.
      
  - ResultTextAreaObserver: Updates a text area with a success message when a reservation is submitted.
      
  - RoomAvailabilityObserver: Updates a text area to display which rooms have become available or unavailable.
  
  Purpose: Respond to changes notified by the RoomAvailabilitySubject, updating the user interface dynamically.


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
  


# Error handling

  ## The program handles errors that may occur during operation, such as:
  
  - Insufficient funds.
  - Room not available.
  - Database connection error.
    
  ## In such cases, the user is presented with an appropriate error message.



# Database interaction:
  The code uses JDBC to connect to a PostgreSQL database.
  Reservation data is stored in tables in the database.


# Why observe and factory methods

## Observer Pattern:

### Core concept: 

  - This pattern defines a one-to-many relationship between objects, where one object (subject) maintains a list of dependent objects (observers) and notifies them about changes to its state.

### Implementation in the code:

  - RoomAvailabilitySubject acts as the subject, managing a list of observers and notifying them about changes in room availability or reservation status.

  - Classes like CostPerNightLabelObserver and RoomAvailabilityObserver act as observers, registering with the subject and updating the UI based on received notifications.

### Factory Method Pattern:

### Core concept:
  - This pattern focuses on object creation. It aims to centralize the logic for creating objects of a specific type without revealing the exact implementation details of the creation process.

### Not used in the provided code:
  - While the code uses various classes like Room and Observer extensively, it doesn't demonstrate the factory method pattern. There aren't any explicit factories responsible for creating these objects. They seem to be instantiated directly as needed.

### Potential Use Case for Factory Method Pattern:

  - Instead of directly creating Observer objects, the system could potentially benefit from a factory approach:

### ObserverFactory class:
 - This factory could be responsible for creating different types of observers based on their functionalities.

### Benefits:

  - Centralized creation logic: Makes it easier to manage different observer types and potentially introduce new ones without modifying other parts of the code.
  - Encapsulation: Hides the internal implementation details of how observers are created, promoting better separation of concerns.
  
