import java.io.*;           // Importing FileWriter and IOException
import java.util.*;         // Importing Scanner

public class PlaneManagement_program {     //Task 1 named project title
    private static final int ROWS_A_D = 14;         //Define number of seats per row for A and D
    private static final int ROWS_B_C = 12;         //Define number of seats per row for B and C
    private static final boolean[][] seats = {
            new boolean[ROWS_A_D],          // Array representing the seats in Row A
            new boolean[ROWS_B_C],          // Array representing the seats in Row B
            new boolean[ROWS_B_C],          // Array representing the seats in Row C
            new boolean[ROWS_A_D]           // Array representing the seats in Row D
    };

    private static Ticket[] ticketsold = new Ticket[54];           // Creating size of array
    private static int ticketcount = 0;         // Tracks the number of tickets sold

    public static void main(String[] args) {            //Task 2 printing the menu
        Scanner inputs = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {           // Continue the loop  until 0
            System.out.println("\n");           // Printing plane management menu
            System.out.println("   Welcome to the Plane management application    ");
            System.out.println("**************************************************");
            System.out.println("*                 MENU OPTION                    *");
            System.out.println("1) Buy a seat");
            System.out.println("2) Cancel a seat");
            System.out.println("3) Find first available seat");
            System.out.println("4) Show seating plan");
            System.out.println("5) Print ticket information and total sales");
            System.out.println("6) Search ticket");
            System.out.println("0) Quit");
            System.out.println("**************************************************");
            System.out.print("Please select an option: ");

            try {
                choice = inputs.nextInt();
                switch (choice) {
                    case 1:
                        buy_seat(inputs);           // Display buy seat option
                        break;
                    case 2:
                        cancel_Seat(inputs);            // Display cancel seat option
                        break;
                    case 3:
                        find_first_available();         //  Display first seat
                        break;
                    case 4:
                        show_seating_plan();            // Display seating plan
                        break;
                    case 5:
                        print_ticket_info();            // Display ticket information
                        break;
                    case 6:
                        search_ticket(inputs);          // Display searching ticket
                        break;
                    case 0:
                        System.out.println("Plane Management system is closing...");
                        break;
                    default:
                        System.out.println("Invalid choice! Select a valid option.");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid choice! Select a valid option.");
                inputs.next();         // Consume the invalid inputs
            }
        }
        inputs.close();        // Closing the inputs
    }

    public static void buy_seat(Scanner inputs) {          //Task 3 buying a seat
        while (true) {
            System.out.print("Enter row Letter (A/B/C/D): ");
            char rowletter = Character.toUpperCase(inputs.next().charAt(0));           // Getting user input for row letter

            if (rowletter < 'A' || rowletter > 'D') {
                System.out.println("Invalid row letter!");
                continue;           // Prompt user again for valid row letter
            }

            System.out.print("Enter Seat Number (1 - 14): ");
            int seatnumber = inputs.nextInt();             // Getting user input for seat number

            if (seatnumber < 1 || seatnumber > 14) {
                System.out.println("Invalid seat number!");
                continue;           // Prompt user again for valid seat number
            }

            int row = rowletter - 'A';              // Convert row letter to array index
            seatnumber--;           // Convert seat number to array index

            if (row < 0 || row >= seats.length || seatnumber < 0 || seatnumber >= seats[row].length) {          // Check if the given row or seat number is invalid
                System.out.println("Invalid row or seat number!");
                continue;           // Prompt user again for valid inputs
            }

            if (seats[row][seatnumber]) {                  // Checking the seat is booked or not
                System.out.println("Seat is already taken try another seat...");
            } else {
                System.out.print("Enter your name: ");          // Getting user information
                String name = inputs.next();
                System.out.print("Enter your surname: ");
                String surname = inputs.next();
                System.out.print("Enter your email: ");
                String email = inputs.next();
                Person person = new Person(name, surname, email);           // Creating a new person object with provide information

                int price = calculatePrice(row, seatnumber);            // Calculating the price based on ticket number
                Ticket ticket = new Ticket(rowletter, seatnumber + 1, price, person);           // Creating a new ticket object with seat information

                if (ticketcount == ticketsold.length) {         // Checking if number of sold ticket equals to sold ticket array length
                    Ticket[] TicketsArray = new Ticket[ticketsold.length * 2];          // Create a new array to store tickets, doubling the capacity of the current array.
                    System.arraycopy(ticketsold, 0, TicketsArray, 0, ticketcount);          // Copying existing tickets from old array to the new array
                    ticketsold = TicketsArray;          // Updating array
                }

                ticketsold[ticketcount++] = ticket;         // Add a purchased ticket to an array of sold ticket
                seats[row][seatnumber] = true;
                ticket.saveFile();          // Saved updated ticket info to a file
                System.out.println("\nSeat purchased successfully.");

                String fileName = rowletter + Integer.toString(seatnumber + 1) + ".txt";            // Build the file name based on the row letter and seat number
                System.out.println("Your ticket information has saved to file: " + fileName);
                break;                  // Exit the loop after successful purchase
            }
        }
    }

    public static void cancel_Seat(Scanner inputs) {           //Task 4 canceling a seat
        while (true) {
            System.out.print("Enter row Letter (A/B/C/D): ");
            char rowletter = Character.toUpperCase(inputs.next().charAt(0));          //Getting user input for row letter

            if (rowletter < 'A' || rowletter > 'D') {
                System.out.println("Invalid row letter!");
                continue;           // Prompt user again for valid row letter
            }

            System.out.print("Enter Seat Number (1 - 14): ");
            int seatnumber = inputs.nextInt();         // Getting user input for seat number

            if (seatnumber < 1 || seatnumber > 14) {
                System.out.println("Invalid seat number!");
                continue;           // Prompt user again for valid seat number
            }

            int row = rowletter - 'A';          // Convert row letter to array index
            seatnumber--;              // Convert seat number to array index

            if (row < 0 || row >= seats.length || seatnumber < 0 || seatnumber >= seats[row].length) {          //Check if the given row or seat number is invalid
                System.out.println("Invalid row or seat number!");
                return;
            }

            if (!seats[row][seatnumber]) {              // Checking the seat is booked or not
                System.out.println("This Seat is already available.");
                break;

            } else {
                for (int i = 0; i < ticketcount; i++) {
                    if (ticketsold[i].Seat_Row() == rowletter && ticketsold[i].Seat_num() == seatnumber + 1) {          // Check if the current ticket matches the given row letter and seat number.

                        String fileName = rowletter + Integer.toString(seatnumber + 1) + ".txt";            // When cancel a ticket Delete the saved file
                        File file = new File(fileName);
                        if (file.delete()) {            // When Cancel a seat it also deletes the specific file
                            System.out.println("\n");
                            System.out.println("File " + fileName + " deleted successfully.");
                        } else {
                            System.out.println("Failed to delete the file " + fileName);            //
                        }


                        ticketsold[i] = null;                       // When cancel a ticket delete it from array
                        for (int j = i; j < ticketcount - 1; j++) {         // Shift the remaining tickets one position forward in the array to fill the gap left by the canceled ticket.
                            ticketsold[j] = ticketsold[j + 1];
                        }
                        ticketcount--;          // Reduce the ticket count since one ticket has been canceled.

                        break;
                    }
                }
                seats[row][seatnumber] = false;         // Mark the specific seat as unoccupied
                System.out.println("Seat canceled successfully.");          // Set the seat status to available
                return;
            }
        }
    }

    public static void find_first_available() {         //Task 5 finding first available seat
        int row = 0;            // This used liner search algorithm
        int maxSeatNumber = ROWS_A_D;
        int seat = 0;

        while (row < seats.length) {
            while (seat < maxSeatNumber) {
                if (!seats[row][seat]) {            // Check if the seat at the current row and seat number is unoccupied
                    char rowletter = (char) ('A' + row);
                    int seatNumber = seat + 1;
                    System.out.println("First available seat: Row " + rowletter + ", Seat " + seatNumber);
                    return;
                }
                seat++;         // Move to the next seat with in the current row.
            }
            row++;              // Move to the next row within the seats.
            if (row == 0 || row == 3) {         // Update the maximum seat number based on the current row.
                maxSeatNumber = ROWS_A_D;
            } else {
                maxSeatNumber = ROWS_B_C;
            }
            seat = 0;           // Get the seat index to start from the first seat in the row
        }
        System.out.println("No available seats.");      // If all the seats are booked print no seats are available
    }

    public static void show_seating_plan() {            //Task 6 showing seating plan
        for (int row = 0; row < seats.length; row++) {
            if (row == 1 || row == 2) {         // Skip rows B and C
            }
            int maxSeatNumber;              // Control the maximum seat number based on the current row
            if (row == 0 || row == 3) {
                maxSeatNumber = ROWS_A_D;
            } else {
                maxSeatNumber = ROWS_B_C;
            }
            for (int seat = 0; seat < maxSeatNumber; seat++) {          // Repeat through each seat within the current row.

                if (seats[row][seat]) {         // Check if the seat at the current row and seat number is occupied.
                    System.out.print("X");          // Print "X"  if the seat is occupied
                } else {
                    System.out.print("O");          // Print "O" if the seat is unoccupied
                }
            }
            System.out.println();
        }
    }

    public static void print_ticket_info() {            //Task 10 Printing ticket information
        int totalSales = 0;
        for (int i = 0; i < ticketcount; i++) {         // Repeat through the sold tickets array.
            ticketsold[i].printTicketInfo();
            totalSales += ticketsold[i].Seat_Price();            // Calculate total sales
        }
        System.out.println("Total Sales: £" + totalSales);      // Printing total sales
    }

    private static int calculatePrice(int row, int seatNumber) {
        int price;              // Determine the price based on the row and seat number
        if (row == 0 || row == 3) {
            if (seatNumber >= 0 && seatNumber <= 4) {            // Calculating seat prices
                price = 200;
            } else if (seatNumber >= 5 && seatNumber <= 8) {
                price = 150;
            } else {
                price = 180;
            }
        } else {
            if (seatNumber >= 0 && seatNumber <= 4) {
                price = 200;
            } else if (seatNumber >= 5 && seatNumber <= 8) {
                price = 150;
            } else {
                price = 180;
            }
        }
        return price;
    }

    public static void search_ticket(Scanner inputs) {         //Task 11 search a  ticket
        while (true) {
            System.out.print("Enter row Letter (A/B/C/D): ");
            char rowletter = Character.toUpperCase(inputs.next().charAt(0)); // Getting user inputs for row letter

            if (rowletter < 'A' || rowletter > 'D') {
                System.out.println("Invalid row letter.");
                continue;           // Prompt user again for valid row letter
            }

            System.out.print("Enter Seat Number (1 - 14): ");
            int seatnumber = inputs.nextInt();         // Getting user inputs for seat number

            if (seatnumber < 1 || seatnumber > 14) {
                System.out.println("Invalid seat number!");
                continue;           // Prompt user again for valid seat number
            }

            int row = rowletter - 'A';         // Convert row letter to array index
            seatnumber--;              // Convert seat number to array index

            if (row < 0 || row >= seats.length || seatnumber < 0 || seatnumber >= seats[row].length) {          // Determine the maximum seat number based on the row.
                System.out.println("Invalid row or seat number!");
                return;

            }

            boolean found = false;          // Using boolean variable to track weather matching ticket is found
            for (int i = 0; i < ticketcount; i++) {
                if (ticketsold[i].Seat_Row() == rowletter && ticketsold[i].Seat_num() == seatnumber + 1) {          // Check if the seat row of the ticket at index i match the provided row letter
                    ticketsold[i].printTicketInfo();
                    ticketsold[i].Person().printTicketInfo();                 // print person info and ticket info
                    found = true;
                    return;             // return to main menu after finding the ticket
                }
            }
            if (!found) {           // Checking for a matching ticket
                System.out.println("This seat is available now.");          // If there a seat not booked print this
                return;             // return to main menu after confirming seat availability
            }
        }
    }
}

