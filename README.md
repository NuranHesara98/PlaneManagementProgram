# Plane Management Program

## Overview
The Plane Management Program is a Java application designed to manage seat bookings on a plane. It allows users to buy, cancel, and search for tickets, as well as view the seating plan and ticket information. This program demonstrates file handling, array manipulation, and basic user input handling in Java.

## Features
1. **Buy a Seat**: Allows users to purchase a seat by providing row and seat numbers along with personal information.
2. **Cancel a Seat**: Users can cancel their booked seats.
3. **Find First Available Seat**: The program finds and displays the first available seat.
4. **Show Seating Plan**: Displays the current seating plan showing occupied and available seats.
5. **Print Ticket Information and Total Sales**: Displays all sold tickets' information and the total sales amount.
6. **Search Ticket**: Users can search for a ticket using row and seat numbers.

## How to Use
1. **Run the Program**: Compile and run the `PlaneManagement_program` class.
2. **Menu Options**: Follow the on-screen menu to select an option by entering the corresponding number.
3. **Input Details**: Provide necessary details as prompted by the program, such as row letters (A-D) and seat numbers (1-14).

## Code Structure
- **Main Class**: `PlaneManagement_program`
  - Contains the main method and menu options handling.
  - Manages the seat booking, cancellation, and search functionalities.
- **Ticket Class**: Handles ticket information, including seat details and personal information.
- **Person Class**: Stores personal information of the ticket holder.

## File Handling
- Each ticket is saved as a text file named with the row letter and seat number (e.g., `A1.txt`).
- Canceling a ticket deletes the corresponding file.

## Dependencies
- Java Standard Library
