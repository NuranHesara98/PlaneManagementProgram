public class Person {          //Task 7 creating person class
    private String name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email) {          //Creating Person object with name, surname, and email
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    public String Name() {
        return name;
    }
    public String Surname() {
        return surname;
    }
    public String Email() {
        return email;
    }
    public void printTicketInfo() {             //Printing ticket & person information
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }
}