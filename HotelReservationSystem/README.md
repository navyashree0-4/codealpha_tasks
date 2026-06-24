Hotel Reservation System Project
# Hotel Reservation System

## Description
The Hotel Reservation System is a GUI-based Java application developed using Java Swing. It allows users to search available rooms, make reservations, cancel bookings, view booking details, and simulate payments. The system uses Object-Oriented Programming (OOP) concepts and File Handling to store and manage booking information.

## Features
- Search available hotel rooms
- Room categorization:
  - Standard
  - Deluxe
  - Suite
- Make room reservations
- Cancel existing reservations
- Payment simulation before booking confirmation
- View all booking details
- Store booking records using File I/O
- User-friendly GUI using Java Swing

## Technologies Used
- Java
- Java Swing
- Object-Oriented Programming (OOP)
- ArrayList
- File Handling (FileWriter, FileReader, BufferedReader)

## Project Structure

HotelReservationSystem/
│
├── HotelReservationSystem.java
├── Room.java
├── Booking.java
├── bookings.txt
└── README.md

## Room Categories

| Room Type | Price |
|------------|--------|
| Standard | ₹1000 |
| Deluxe | ₹2000 |
| Suite | ₹3500 |

## Functionalities

### Search Rooms
Users can search available rooms based on room category.

### Book Room
Users can:
- Enter customer name
- Select room type
- Complete payment simulation
- Confirm reservation

### Cancel Reservation
Users can cancel a reservation using the Booking ID.

### View Bookings
Displays:
- Booking ID
- Customer Name
- Room Number
- Room Type
- Booking Amount

### File Storage
All booking records are stored in:

```text
bookings.txt
```

This ensures booking information is retained for future use.

## How to Run

### Compile

```bash
javac Room.java
javac Booking.java
javac HotelReservationSystem.java
```

### Run

```bash
java HotelReservationSystem
```

## Sample Booking Record

```text
1,Ram,101,Standard,1000
2,Priya,201,Deluxe,2000
```

## Learning Outcomes
- Java Swing GUI Development
- Event Handling
- File Handling
- Object-Oriented Programming
- ArrayList Data Management
- Hotel Reservation Workflow Design

## Future Enhancements
- Database Integration (MySQL)
- User Login System
- Online Payment Gateway
- Room Availability Dashboard
- Booking Receipt Generation

## Author
Navyashree

## Internship
CodeAlpha Java Programming Internship

## Task
Task 4 - Hotel Reservation System
