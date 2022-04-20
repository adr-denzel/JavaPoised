package poised;

import java.util.Scanner;

/**
 * Major feature method calls made here.
 *
 * @author Denzel Ramsbottom
 * @version 1.0, 9 Feb 2022
 */
public class Main {

    /**
     * Call various DBHandler methods to execute application
     * features.
     *
     * @see DBHandler
     * @since version 1.0
     */
    public static void main(String[] args) {
        boolean running = true; // flag to change state for end of loop

        // instantiate DB access
        DBHandler db = new DBHandler();

        // loop to keep going until 'termination' branch is selected
        while (running) {
            // main menu terminal message
            System.out.println("""
            \nWelcome to JavaPoised. Please submit:
            '1' to access exiting projects
            '2' to create an all new project
            '3' to access existing contacts
            '4' to create an all new contact
            '0' to terminate Java-Poised
            """);
            Scanner inputMonitor = new Scanner(System.in); // monitor user input
            String userInput = inputMonitor.nextLine(); // variable to assess for switch cases is the very next user input

            // enhanced switch case, cases call Branch object methods
            switch (userInput) {
                // terminate Java-Poised
                case "0" -> {
                    db.closeConnection(); // security; close DB connection and statement objects
                    running = false; // flag state change, loop ends
                    System.out.println("Session terminated"); // terminal message
                }
                // view projects branch
                case "1" -> db.viewProjects();
                // create new project branch
                case "2" -> db.createProject();
                // view contacts branch
                case "3" -> db.viewEditDeleteContacts();
                // create new contact branch
                case "4" -> db.createContact();
                // for all other input scenarios, terminal prints message, loop continues
                default -> System.out.println("Invalid input! Please submit a valid integer from the list.");
            }
        }
    }
}