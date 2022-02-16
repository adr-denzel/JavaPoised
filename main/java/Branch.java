import java.util.Scanner;
import java.util.ArrayList;

/**
 * Branch Class
 * <br>
 * <br>
 * Where the high-level composition of Project
 * and Contact (Architect, Contactor, & Customer)
 * methods and objects are called and composed to fulfill
 * feature requirements for Project Management Application.
 * These methods and objects are structured as complex logic
 * trees here to ease complexity in Main.
 * <br>
 * <br>
 * Composition of the methods called in Branch are detailed
 * in Project, Contact (and its subclasses). These methods
 * bare empty signatures to allow for changes in Project,
 * Contact, etc. methods to make for no additional rework
 * in Main & Branch.
 * <br>
 * <br>
 * Major text prompt messaged are composed here as well.
 *
 * @see Contact
 * @see Architect
 * @see Contractor
 * @see Customer
 * @see Project
 * @see Main
 * @author Denzel Ramsbottom
 * @version 1.0, 9 February 2022
 */
public class Branch {
    /**
     * Project object.
     */
    Project activeProject = new Project();

    /**
     * Architect object typed in its superclass family Architect.
     */
    Contact activeArchitect = new Architect();

    /**
     * Architect object typed in its superclass family Contact.
     */
    Contact activeContractor = new Contractor();

    /**
     * Architect object typed in its superclass family Contact.
     */
    Contact activeCustomer = new Customer();

    /**
     * ArrayList object for Project types.
     */
    ArrayList<Project> allProjects = new ArrayList<>();

    /**
     * ArrayList object for Contact types, in this case Architect Contacts.
     */
    ArrayList<Contact> allArchitects = new ArrayList<>();

    /**
     * ArrayList object for Contact types in this case Contractor Contacts.
     */
    ArrayList<Contact> allContractors = new ArrayList<>();

    /**
     * ArrayList object for Contact types in this case Customer Contacts.
     */
    ArrayList<Contact> allCustomers = new ArrayList<>();

    /**
     *
     * mainPrompt() Method Called in Main
     * <br>
     * <br>
     * Indicates user input for respective feature selection.
     * <br>
     * Simple series of println() statements.
     *
     * @see Main
     * @since version 1.0
     */
    public void mainPrompt() {
        // main prompt message
        System.out.println("\nWelcome to Java-Poised. Please select:");
        System.out.println("'1' to access exiting projects");
        System.out.println("'2' to create an all new project");
        System.out.println("'3' to access existing contacts");
        System.out.println("'4' to create an all new contact");
        System.out.println("'0' to terminate Java-Poised\n");
    }

    /**
     *
     * viewProjectsPrompt() Method Called in Main
     * <br>
     * <br>
     * Indicates user input for respective project search & selection.
     * <br>
     * Simple series of println() statements.
     *
     * @see Main
     * @since version 1.0
     */
    public void viewProjectsPrompt() {
        // view projects methods
        System.out.println("\nWelcome to projects view. Please select:");
        System.out.println("'1' to view all projects");
        System.out.println("'2' to view all incomplete projects");
        System.out.println("'3' to view all overdue projects");
        System.out.println("'4' to search projects by name or project number");
        System.out.println("'0' to return to main\n");
    }

    /**
     *
     * editProjectsPrompt() Method Called in Branch
     * <br>
     * <br>
     * Provides initial instruction to user on accessing viewProjects branch.
     * <br>
     * Simple series of println() statements.
     *
     * @since version 1.0
     */
    public void editProjectsPrompt() {
        // view projects branch initial instructions
        System.out.println("\nTo edit a particular project's attributes, submit their 'project number', then follow the prompt");
        System.out.println("Otherwise submit '0' to return to menu.");
    }

    /**
     *
     * viewProjects() Method Called in Main
     * <br>
     * <br>
     * This logic tree is the primary means via user interaction to
     * collect a list of Project objects to view, edit, and save edits.
     * <br>
     * <br>
     * This method initially calls collectProjects() method to an ArrayList variable for
     * assignment.
     * <br>
     * <br>
     * Post list of Project objects being available for selection, viewing, or attribute
     * modification, user interaction allows for access to these Project objects through
     * a variety of search means.
     * <br>
     * <br>
     * Should a project be selected for selectEditSubmitProject() call to be made,
     * any changed to an existing project can then be saved to its respective text file.
     *
     * @see Main
     * @see Contact
     * @see Architect
     * @see Contractor
     * @see Customer
     * @since version 1.0
     */
    public void viewProjects() {
        Scanner inputMonitor = new Scanner(System.in);
        String userInput = inputMonitor.next();

        allProjects = activeProject.collectProjects();

        switch (userInput) {
            case "1" -> {
                activeProject.viewAllProjects(allProjects);
                if (!allProjects.isEmpty()) {
                    editProjectsPrompt();
                    try {
                        activeProject.selectEditSubmitProjects(allProjects);
                    }
                    catch (NullPointerException e) {
                        System.out.println("Edits aborted. No Project selected.");
                    }
                }
                else {
                    System.out.println("\nPlease create projects before attempting to access an empty database.");
                }
            }
            case "2" -> {
                activeProject.viewIncompleteProjects(allProjects);
                if (!allProjects.isEmpty()) {
                    editProjectsPrompt();
                    try {
                        activeProject.selectEditSubmitProjects(allProjects);
                    }
                    catch (NullPointerException e) {
                        System.out.println("Edits aborted. No Project selected.");
                    }
                }
                else {
                    System.out.println("\nPlease create projects before attempting to access an empty database.");
                }
            }
            case "3" -> {
                activeProject.viewOverdueProjects(allProjects);
                if (!allProjects.isEmpty()) {
                    editProjectsPrompt();
                    try {
                        activeProject.selectEditSubmitProjects(allProjects);
                    }
                    catch (NullPointerException e) {
                        System.out.println("Edits aborted. No Project selected.");
                    }
                }
                else {
                    System.out.println("\nPlease create projects before attempting to access an empty database.");
                }
            }
            case "4" -> {
                activeProject.searchProject(allProjects);
            }
            default -> System.out.println("\nProject view abandoned.");
        }
    }

    /**
     *
     * createProjectPrompt() Method Called in Main
     * <br>
     * <br>
     * Indicates user input for project creation.
     * <br>
     * Simple series of println() statements.
     *
     * @see Main
     * @since version 1.0
     */
    public void createProjectPrompt() {
        // create projects method
        System.out.println("\nPlease follow prompts to create an all new Project:");
        System.out.println("\nTo proceed either select a registered contact or create a new contact:");
    }

    /**
     *
     * createProjects() Method called in Main
     * <br>
     * <br>
     * Consolidates the creation of a fully attributed project object.
     * <br>
     * <br>
     * Firstly, Architect, Contractor & Customer objects are created
     * via assignContact() method. These states are assigned to the
     * active Contact objects instantiated for Branch.
     * <br>
     * <br>
     * Subsequently, those Contact objects are assigned as the
     * Architect, Contractor, and Customer for our active Project
     * object.
     * <br>
     * <br>
     * Next, we call setDefaultName() to confirm to Client request.
     * Then we call confirmAndAppend, to log our new project data
     * in our storage location.
     *
     * @see Main
     * @see Project
     * @see Contact
     * @see Architect
     * @see Contractor
     * @see Customer
     * @since version 1.0
     */
    public void createProject() {
        // try catch to account for NullPointerExceptions
        try {
            activeArchitect.assignContact();
            activeContractor.assignContact();
            activeCustomer.assignContact();

            activeProject = new Project();
            activeProject.setArchitect(activeArchitect);
            activeProject.setContractor(activeContractor);
            activeProject.setCustomer(activeCustomer);
            activeProject.setDefaultName();
            activeProject.createGeneralAttributes();
            activeProject.confirmAndAppend();
        }
        catch (NullPointerException e) {
            System.out.println("\nInput Error! Project creation failed due to invalid contact inputs.");
            System.out.println("Please follow the prompts and respond appropriately.");
        }
    }

    /**
     *
     * viewContactsPrompt() Method Called in Main
     * <br>
     * <br>
     * Indicates user input for contact view options.
     * <br>
     * Simple series of println() statements.
     *
     * @see Main
     * @since version 1.0
     */
    public void viewContactsPrompt() {
        // view contact prompt
        System.out.println("\nWelcome to contact view. Please select:");
        System.out.println("'1' to view Architect contacts");
        System.out.println("'2' to view Contactor contacts");
        System.out.println("'3' to view Customer contacts");
        System.out.println("'0' to return to main\n");
    }
    /**
     *
     * editContactsPrompt() Method Called in Branch
     * <br>
     * <br>
     * Provides initial instruction to user on accessing editContacts branch.
     * <br>
     * Simple series of println() statements.
     *
     * @since version 1.0
     */
    private void editContactsPrompt() {
        System.out.println("\nTo edit a particular contact's attributes, submit their 'index', then follow the prompt");
        System.out.println("Otherwise submit '0' to return to menu.");
    }

    /**
     *
     * viewContacts() Method Called in Main
     * <br>
     * <br>
     * Routine to guide a user through selecting a Contact class and their
     * respective stored objects to view in a text-based cli representation.
     * User selects the class to view. Program logic splits into one of three
     * directions to fulfil request.
     * <br>
     * <br>
     * user interaction leads to selectEditSubmitContact() call which initiates
     * routine to edit object attributes and then commit those attributes to
     * text file.
     *
     * @see Main
     * @see Contact
     * @see Architect
     * @see Contractor
     * @see Customer
     * @since version 1.0
     */
    public void viewContacts() {
        Scanner inputMonitor = new Scanner(System.in);
        String userInput = inputMonitor.next();

        switch (userInput) {
            case "1" -> {
                allArchitects = activeArchitect.collectContacts();
                activeArchitect.viewContacts(allArchitects);
                if (!allArchitects.isEmpty()) {
                    editContactsPrompt();
                    try {
                        activeArchitect.selectEditSubmitContact(allArchitects);
                    }
                    catch (NullPointerException e) {
                        System.out.println("Edits aborted. No Architect selected.");
                    }
                }
                else {
                    System.out.println("\nPlease create " + activeArchitect.getContactType() + "s before attempting to access an empty database.");
                }
            }
            case "2" -> {
                allContractors = activeContractor.collectContacts();
                activeContractor.viewContacts(allContractors);
                if (!allContractors.isEmpty()) {
                    editContactsPrompt();

                    try {
                        activeContractor.selectEditSubmitContact(allContractors);
                    }
                    catch (NullPointerException e) {
                        System.out.println("Edits aborted. No Contractor selected.");
                    }
                }
                else {
                    System.out.println("\nPlease create " + activeContractor.getContactType() + "s before attempting to access an empty database.");
                }
            }
            case "3" -> {
                allCustomers = activeCustomer.collectContacts();
                activeCustomer.viewContacts(allCustomers);
                if (!allCustomers.isEmpty()) {
                    editContactsPrompt();

                    try {
                        activeCustomer.selectEditSubmitContact(allCustomers);
                    }
                    catch (NullPointerException e) {
                        System.out.println("Edits aborted. No Customer selected.");
                    }
                }
                else {
                    System.out.println("\nPlease create " + activeCustomer.getContactType() + "s before attempting to access an empty database.");
                }
            }
        }
    }

    /**
     *
     * createNewContactPrompt() Method Called in Main
     * <br>
     * <br>
     * Indicates user input for new contact creation.
     * <br>
     * Simple series of println() statements.
     *
     * @see Main
     * @since version 1.0
     */
    public void createNewContactPrompt() {
        System.out.println("\nPlease select the type of contact to create:");
        System.out.println("'1' to create Architect contact");
        System.out.println("'2' to create Contactor contact");
        System.out.println("'3' to create Customer contact");
        System.out.println("'0' to return to main\n");
    }

    /**
     *
     * createNewContactSelection() Method Called in Branch
     * <br>
     * <br>
     * Parameterised println() statement for user information delivery.
     *
     * @param contact Used to call parameter's getContactType() method to complete text for appropriate Contact type
     * @since version 1.0
     */
    private void createNewContactSelection(Contact contact) {
        System.out.println("\nPlease follow the prompts to create your " + contact.getContactType() + " contact.");
    }

    /**
     *
     * createNewContact() Method Called in Main
     * <br>
     * <br>
     * Routine to lead user through process of providing attributes
     * that make up a contact object, be it Architect, Contractor, or
     * Customer.
     * <br>
     * <br>
     * Switch case is used to split logic on which Contact type creation routine
     * initiates; this will be up to the user to select.
     * <br>
     * <br>
     * Following that, createNewContactSelection(contact) prompt will
     * initiate and text for the respective Contact type substitutes the parameter variable.
     * createNewContact() method is called, and the subsequent confirmAndAppend()
     * method is called to commit changes to respective text file for Contact object
     * type.
     *
     * @see Main
     * @see Contact
     * @see Architect
     * @see Contractor
     * @see Customer
     * @since version 1.0
     */
    public void createNewContact() {
        Scanner inputMonitor = new Scanner(System.in);
        String userInput = inputMonitor.next();

        switch (userInput) {
            case "1" -> {
                createNewContactSelection(activeArchitect);
                activeArchitect.createContact();
                activeArchitect.confirmAndAppend();
            }
            case "2" -> {
                createNewContactSelection(activeContractor);
                activeContractor.createContact();
                activeContractor.confirmAndAppend();
            }
            case "3" -> {
                createNewContactSelection(activeCustomer);
                activeCustomer.createContact();
                activeCustomer.confirmAndAppend();
            }
            default -> System.out.println("\nContact creation abandoned.");
        }
    }
}