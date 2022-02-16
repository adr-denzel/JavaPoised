import java.util.Scanner;

/**
 * Main Class
 * <br>
 * <br>
 * The place from which application logic is initiated.
 * Major feature branch calls are made here. These
 * are in the form of a Branch object.
 *
 * @see Branch
 * @author Denzel Ramsbottom
 * @version 1.0, 9 February 2022
 */
public class Main {

    /**
     *
     * Main Method
     * <br>
     * <br>
     * All Branch calls are made here via Branch object.
     *
     * @see Branch
     * @since version 1.0
     */
    public static void main(String[] args) {
        boolean running = true; // flag to indicate end of loop

        // loop to keep program going until 'termination' is selected
        while (running) {
            Branch mainBranch = new Branch(); // instantiate object
            mainBranch.mainPrompt(); // call prompt message

            Scanner inputMonitor = new Scanner(System.in); // monitor user input
            String userInput = inputMonitor.next(); // variable to assess for switch cases is the very next user input

            // enhanced switch case, cases call Branch object methods
            switch (userInput) {
                // branch to terminate program, simply assigns new value to flag
                case "0" -> {
                    System.out.println("Session terminated");
                    running = false;
                }
                // project view branch, see applicable methods in Branch.java
                case "1" -> {
                    mainBranch.viewProjectsPrompt();
                    mainBranch.viewProjects();
                }
                // create project branch, see applicable methods in Branch.java
                case "2" -> {
                    mainBranch.createProjectPrompt();
                    mainBranch.createProject();
                }
                // contact view branch, see applicable methods in Branch.java
                case "3" -> {
                    mainBranch.viewContactsPrompt();
                    mainBranch.viewContacts();
                }
                // create contact branch, see applicable methods in Branch.java
                case "4" -> {
                    mainBranch.createNewContactPrompt();
                    mainBranch.createNewContact();
                }
                // for all other input scenarios, message prints, loop continues
                default -> System.out.println("Invalid input! Please 'submit' an integer from the list.");
            }
        }
    }
}