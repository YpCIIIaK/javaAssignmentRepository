# Hotel Reservation System

### Creators: Vladimirov Vladimir, Tinalin Arsen, Sabyraly Gaukharbek, Abishev Beibarys, Seitkadyr Alisher, Sabyraly Sanzhar
### Group: SE - 2329

# 1. Guest Management:

  Guest class: Represents a guest with attributes like serial number, full name, bank account balance, a list of reservations, and a list of available rooms.
  Methods:
   - makeReservation(reservation): Adds a reservation to the guest's list and marks the room as unavailable.
   - cancelReservation(reservation): Removes a reservation from the guest's list and marks the room as available.
   - selectRoom(type): Searches for an available room of the specified type.
   - Getter and setter methods for guest information.
# 2. Room Management:

  Room class: Represents a room with attributes like room number, type (e.g., economy, comfort, luxury), and availability status.
  Methods:
   - Getter and setter methods for room information.
   - setAvailable(boolean available): Updates the room's availability status.
# 3. Reservation Management:

  Reservation class: Represents a reservation with details like reservation ID, guest, room, start date, and end date.
  ReservationManager class: Manages a list of reservations in memory and interacts with the database connector (simulated in this code).
  Methods:
   - makeReservation(reservation): Adds a reservation to the internal list and calls the DatabaseConnector to save it (not implemented).
   - cancelReservation(reservation): Removes a reservation from the internal list and calls the DatabaseConnector to delete it (not implemented).
   - getReservationsForGuest(guest): Retrieves all reservations for a specific guest.
   - getReservationsForRoom(room): Retrieves all reservations for a specific room.
   - getReservationsForDateRange(startDate, endDate): Retrieves all reservations within a date range.
# 4. Database Interaction (Simulated):

  DatabaseConnector class: This class is a placeholder for actual database interaction. It currently has methods for saving and deleting reservations, but their implementation is not provided.
  
# 5. Room Selection Interface:

  RoomSelectionInterface: Defines a single method selectRoom(type) that guests can use to find an available room of a specific type.
  
# 6. Room Type Enumeration:

  RoomType enum: Defines different room types (economy, comfort, luxury) with their corresponding prices.
  Overall Workflow:

   - Guest creation: Guests are created with their details.
   - Room creation: Rooms are created with their types and set as available.
   - Reservation making: Guests can make reservations by selecting an available room of their desired type. The selected room is marked as unavailable after reservation.
   - Reservation cancellation: Guests can cancel their reservations, making the room available again.
   - Reservation management: The ReservationManager keeps track of reservations in memory and provides methods to retrieve them based on various criteria.
  

