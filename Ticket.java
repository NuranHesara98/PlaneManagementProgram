import java.io.FileWriter;          // Importing FileWriter
import java.io.IOException;         // Importing IOException

public class Ticket {           // Task 8 creating ticket class
    private char row;
    private int seat;
    private int price;
    private Person person;

    public Ticket(char row, int seat, int price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public char Seat_Row() {
        return row;
    }

    public int Seat_num() {
        return seat;
    }

    public int Seat_Price() {
        return price;
    }

    public Person Person() {
        return person;
    }

    public void printTicketInfo() {             //Printing ticket & person information
        System.out.print("Row: " + row);
        System.out.print(" Seat: " + seat);
        System.out.print(" Price: £" + price + "\n");
    }

    public void saveFile() {            //Task 12 saving txt file
        String fileName = row + Integer.toString(seat) + ".txt";            //Creating the file name
        try (FileWriter fileWriter = new FileWriter(fileName)) {            //Opening a FileWriter to write to the file
            fileWriter.write("Ticket information, \n");
            fileWriter.write("Row: " + row );                           //Writing the file information
            fileWriter.write(" Seat: " + seat );
            fileWriter.write(" Price: £" + price + "\n");
            fileWriter.write("\n");
            fileWriter.write("Person's information, \n");
            fileWriter.write("Name: " + person.Name() + "\n");
            fileWriter.write("Surname: " + person.Surname() + "\n");
            fileWriter.write("Email: " + person.Email() + "\n");
        } catch (IOException e) {
            System.out.println("Cannot save the file");         //Printing an error massage
            e.printStackTrace();            // Printing stack trace for debugging
        }
    }
}
