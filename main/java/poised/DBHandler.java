package poised;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * DBHandler Class
 * <br>
 * <br>
 * Features for Poised PMS application are defined here.
 * Contact and Project objects are instantiated and manipulated here.
 *
 * @see Main
 * @see Project
 * @see Contact
 * @author Denzel Rambsottom
 * @version 2.0, 14 March 2022
 */
public class DBHandler {

    /**
     * Connection object for DB access/credentials authorization.
     */
    private Connection connection;

    /**
     * Statement object to access DB via update and query statements.
     */
    private Statement statement;

    /**
     * ResultSet object to process query returns.
     */
    private ResultSet results;

    /**
     * String object to write SQL code statements to be passed through statements.
     */
    private String sqlCode;

    /**
     * DBHandler constructor on application instantiation to initiate connection to DB.
     * <br>
     * Establish states for connection and statement objects.
     *
     * @see Main
     * @since version 2.0
     */
    public DBHandler() {
        try {
            // DB environment variables
            String URL = "jdbc:mysql://localhost:3306/poised_pms_db?useSSL=false";
            String USER = "denzel";
            String PASSWORD = "chelseafc";

            // establish connection via driver
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // assign statement the connection object
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            if (connection != null) {
                System.out.println("\nConnected to DB.");
            }
        } catch (SQLException e) {
            System.out.println("\nConnection to DB not established.");
        }
    }

    /**
     * closeConnection terminates program connections to DB.
     * Called before program ends.
     *
     * @see Main
     * @since version 2.0
     */
    public void closeConnection() {
        try {
            // closed connection variables
            connection.close();
            statement.close();
        } catch (NullPointerException | SQLException e) {
            System.out.println("\nConnection to DB was never established.");
        }
    }

    /**
     * Major feature branch called in Main to access DB
     * to view existing project records, edit, delete, or
     * add entries to those records.
     *
     * @see Main
     * @since version 2.0
     */
    public void viewProjects() {
        // register user inputs
        Scanner inputMonitor = new Scanner(System.in);

        // terminal message
        System.out.println("""
        \nWelcome to Contact Viewer:
        '1' to view all projects
        '2' to view incomplete projects
        '3' to view overdue projects
        '4' to search for projects
        '0' to return to main menu
        """);
        String userInput = inputMonitor.nextLine();

        switch (userInput) {
            // view all projects
            case "1" -> {
                try {
                    // project preview string template
                    StringBuilder projectDB = new StringBuilder("""
                        ID | NAME | BUILDING TYPE
                        """);

                    // code to query all projects
                    sqlCode = "SELECT * FROM projects";
                    results = statement.executeQuery(sqlCode);

                    // write query results to string
                    while (results.next()) {
                        projectDB.append("\n").append(results.getInt("project_id"));
                        projectDB.append(", ").append(results.getString("project_name"));
                        projectDB.append(", ").append(results.getString("building_type"));
                    }
                    results.beforeFirst(); // roll back iterator to beginning

                    // if query returned results
                    if (results.next()) {
                        System.out.println(projectDB); // print projects preview
                        System.out.println("""
                            \nPlease submit an 'id' from the list of projects:
                            '0' will return you to main menu.
                            """);

                        String idParameter = inputMonitor.nextLine();
                        accessEditDeleteProject(idParameter); // call method to allow editing/deleting for selected project_id
                    } else {
                        System.out.println("\nNo records in projects table.");
                    }
                } catch (SQLException e) {
                    System.out.println("\nSQL Error! Try again.");
                }
            }
            case "2" -> {
                try {
                    // project preview string template
                    StringBuilder projectDB = new StringBuilder("""
                        ID | NAME | BUILDING TYPE
                        """);

                    // code to query all projects where project finalised = false
                    sqlCode = "SELECT * FROM projects WHERE project_finalised = false";
                    results = statement.executeQuery(sqlCode);

                    // write query results to string
                    while (results.next()) {
                        projectDB.append("\n").append(results.getInt("project_id"));
                        projectDB.append(", ").append(results.getString("project_name"));
                        projectDB.append(", ").append(results.getString("building_type"));
                    }
                    results.beforeFirst(); // roll back iterator to beginning

                    // if query returned results
                    if (results.next()) {
                        System.out.println(projectDB); // print projects preview
                        System.out.println("""
                            \nPlease submit an 'id' from the list of unfinished projects:
                            '0' will return you to main menu.
                            """);

                        String idParameter = inputMonitor.nextLine();
                        accessEditDeleteProject(idParameter); // call method to allow editing/deleting for selected project_id
                    } else {
                        System.out.println("\nNo records of unfinished projects available in projects table.");
                    }
                } catch (SQLException e) {
                    System.out.println("\nSQL Error! Try again.");
                }
            }
            case "3" -> {
                try {
                    // project preview string template
                    StringBuilder projectDB = new StringBuilder("""
                        ID | NAME | BUILDING TYPE
                        """);

                    // code to query all projects where projects finalised = false and project overdue
                    sqlCode = "SELECT * FROM projects WHERE project_finalised = false AND project_deadline " +
                            "<= '%s'".formatted(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    results = statement.executeQuery(sqlCode);

                    // write query results to string
                    while (results.next()) {
                        projectDB.append("\n").append(results.getInt("project_id"));
                        projectDB.append(", ").append(results.getString("project_name"));
                        projectDB.append(", ").append(results.getString("building_type"));
                    }
                    results.beforeFirst(); // roll back iterator to beginning

                    // if query returned results
                    if (results.next()) {
                        System.out.println(projectDB); // print projects preview
                        System.out.println("""
                            \nPlease submit an 'id' from the list of unfinished and overdue projects:
                            '0' will return you to main menu.
                            """);

                        String idParameter = inputMonitor.nextLine();
                        accessEditDeleteProject(idParameter); // call method to allow editing/deleting for selected project_id
                    } else {
                        System.out.println("\nNo records of unfinished and overdue projects available in projects table.");
                    }
                } catch (SQLException e) {
                    System.out.println("\nSQL Error! Try again.");
                    e.printStackTrace();
                }
            }
            case "4" -> {
                // terminal message
                System.out.println("""
                \nPlease select your project SEARCH PARAMETER:
                '1' search by project number
                '2' search by project name
                '0' abort project search
                """);
                String searchSubmit = inputMonitor.nextLine();

                switch (searchSubmit) {
                    // branch to initiate project search by project_id
                    case "1" -> {
                        System.out.println("\nPlease submit the 'project number' to search:");
                        String searchParameter = inputMonitor.nextLine();
                        try {
                            sqlCode = "SELECT * FROM projects WHERE project_id = %s".formatted(searchParameter);
                            results = statement.executeQuery(sqlCode);

                            if (results.next()) {
                                accessEditDeleteProject(searchParameter);
                            } else {
                                System.out.println("Search number: " + searchParameter + " had no matching record.");
                            }
                        } catch (SQLException e) {
                            System.out.println("\nSQL Error. Please ensure you submitted an integer.");
                        }
                    }
                    // branch to initiate project search by project_name
                    case "2" -> {
                        System.out.println("\nPlease submit the 'project name' to search:");
                        String searchParameter = inputMonitor.nextLine();
                        try {
                            sqlCode = "SELECT * FROM projects WHERE project_name = '%s'".formatted(searchParameter);
                            results = statement.executeQuery(sqlCode);

                            if (results.next()) {
                                accessEditDeleteProject("%d".formatted(results.getInt("project_id")));
                            } else {
                                System.out.println("\nSearch name: " + searchParameter + " had no matching record.");
                            }
                        } catch (SQLException e) {
                            System.out.println("\nSQL Error. Please ensure you submitted an integer.");
                        }
                    }
                    default -> System.out.println("Project search abandoned.");
                }
            }
            default -> System.out.println("\nYou aborted project viewer.");
        }
    }

    /**
     * Create project feature branch. Called in Main
     * to create new records in DB for projects.
     *
     * @see Main
     * @since version 2.0
     */
    public void createProject() {
        // register user inputs
        Scanner inputMonitor = new Scanner(System.in);

        // terminal message
        System.out.println("""
        \nWelcome to Project Creator:
        '1' to create an all-new Project
        '0' to return to main menu
        """);
        String userInput = inputMonitor.nextLine();

        if (userInput.equals("1")) {
            System.out.println("""
                    All projects need a registered customer.
                    
                    If customer's contact record does not exist,
                    please create a contact record for the customer
                    first.
                    """);

            // string representation preview
            StringBuilder contactDB = new StringBuilder("""
                        ID | NAME | SURNAME | EMAIL | PHONE NUMBER | ADDRESS | POISED EMPLOYEE
                        """);
            try {
                sqlCode = "SELECT * FROM contacts";
                results = statement.executeQuery(sqlCode);

                // append query results preview string
                while (results.next()) {
                    contactDB.append("\n").append(results.getInt("contact_id"));
                    contactDB.append(", ").append(results.getString("f_name"));
                    contactDB.append(", ").append(results.getString("s_name"));
                    contactDB.append(", ").append(results.getString("e_mail"));
                    contactDB.append(", ").append(results.getString("ph_num"));
                    contactDB.append(", ").append(results.getString("address"));
                    contactDB.append(", ").append(results.getBoolean("poised_employee"));
                }
                System.out.println(contactDB);
            } catch (SQLException e) {
                System.out.println("\nSQL Error!");
            }
            System.out.println("""
                    \n'1' to proceed with project creation
                    '0' to return to main menu (create contact)
                    """);
            String createProject = inputMonitor.nextLine();

            // initiate new project creation
            if (createProject.equals("1")) {
                Project newProject = new Project();

                System.out.println("\nPlease follow prompts and submit the requested information:");

                System.out.println("\nPlease submit the project's building type (e.g.House/Apartment/Office):");
                String buildingType = inputMonitor.nextLine();

                System.out.println(contactDB);
                System.out.println("\nPlease submit your chosen customer's Contact ID:");
                String customerID = inputMonitor.nextLine();

                try {
                    sqlCode = ("SELECT * FROM contacts WHERE contact_id = %s").formatted(customerID);
                    results = statement.executeQuery(sqlCode);

                    if (results.next()) {
                        Contact customerContact = new Contact();

                        // assign required contact attributes
                        customerContact.setLastName(results.getString("s_name"));

                        // assign required project attributes
                        newProject.setBuildingType(buildingType);
                        newProject.setCustomerID(Integer.parseInt(customerID));

                        setDefaultProjectName(newProject, customerContact);

                        // code to insert new record into projects
                        sqlCode = ("INSERT INTO projects VALUES (null, '%s', '%s', null, null, null, null, null," +
                                " false, null, null, null, null, %d)").formatted(newProject.getProjectName(),
                                newProject.getBuildingType(), newProject.getCustomer());
                        statement.executeUpdate(sqlCode);
                        System.out.println("\nProject " + newProject.getProjectName() + " created!");
                    } else {
                        System.out.println("\nProject creation failed! No recorded customer record for id "
                                + customerID + ".");
                    }
                } catch (SQLException e) {
                    System.out.println("\nSQL Error! Project creation failed.");
                }
            } else {
                System.out.println("\nYou aborted project creation.");
            }
        } else {
            System.out.println("\nYou aborted project creation.");
        }
    }

    /**
     * Major feature method called in Main to
     * edit, delete, and modify existing contacts records.
     *
     * @see Main
     * @since version 2.0
     */
    public void viewEditDeleteContacts() {
        // register user inputs
        Scanner inputMonitor = new Scanner(System.in);

        // terminal message
        System.out.println("""
        \nWelcome to Contact Viewer:
        '1' to view all Contacts
        '0' to return to main menu
        """);

        String userInput = inputMonitor.nextLine();

        if (userInput.equals("1")) {
            try {
                // preview template for string representation of contacts
                StringBuilder contactDB = new StringBuilder("""
                        ID | NAME | SURNAME | EMAIL | PHONE NUMBER | ADDRESS | POISED EMPLOYEE
                        """);

                sqlCode = "SELECT * FROM contacts";
                results = statement.executeQuery(sqlCode);

                while (results.next()) {
                    contactDB.append("\n").append(results.getInt("contact_id"));
                    contactDB.append(", ").append(results.getString("f_name"));
                    contactDB.append(", ").append(results.getString("s_name"));
                    contactDB.append(", ").append(results.getString("e_mail"));
                    contactDB.append(", ").append(results.getString("ph_num"));
                    contactDB.append(", ").append(results.getString("address"));
                    contactDB.append(", ").append(results.getBoolean("poised_employee"));
                }
                results.beforeFirst();
                if (results.next()) {
                    System.out.println(contactDB);

                    System.out.println("""
                    \nPlease submit an 'id' from the list of contacts:
                    '0' will return you to main menu.
                    """);
                    String idParameter = inputMonitor.nextLine();

                    sqlCode = String.format("SELECT * FROM contacts WHERE contact_id = %s", idParameter);
                    results = statement.executeQuery(sqlCode);

                    if (results.next()) {
                        Contact contact = new Contact();
                        contact.setContactID(results.getInt("contact_id"));
                        contact.setFirstName(results.getString("f_name"));
                        contact.setLastName(results.getString("s_name"));
                        contact.setEmail(results.getString("e_mail"));
                        contact.setPhoneNumber(results.getString("ph_num"));
                        contact.setPhysicalAddress(results.getString("address"));
                        contact.setPoisedEmployee(results.getBoolean("poised_employee"));

                        System.out.println("See your selected contact:");
                        contact.displayContact();

                        System.out.println("""
                        \n'1' to edit contact
                        '2' to delete contact
                        '0' to return to main menu
                        """);

                        String editInput = inputMonitor.nextLine();

                        switch (editInput) {
                            // edit contact branch
                            case "1" -> {
                                System.out.println("\nWhat field would you like to update?");
                                contact.displayContact();
                                System.out.println("""
                                        \n'1' Email
                                        '2' Phone Number
                                        '3' Address
                                        '4' Poised Employee
                                        '0' Abort Edits
                                        """);

                                String userEditInput = inputMonitor.nextLine();

                                // selecting contact attribute to edit
                                switch (userEditInput) {
                                    case "1" -> {
                                        System.out.println("\nPlease submit UPDATED EMAIL:");
                                        String updatedEmail = inputMonitor.nextLine();

                                        try {
                                            sqlCode = ("UPDATE contacts SET e_mail = '%s' " +
                                                    "WHERE contact_id = %s").formatted(updatedEmail,
                                                    contact.getContactID());
                                            statement.executeUpdate(sqlCode);
                                            System.out.println("\nRecord updated.");
                                        } catch (SQLException e) {
                                            System.out.println("\nRecord update error!");
                                        }
                                    }
                                    case "2" -> {
                                        System.out.println("\nPlease submit UPDATED PHONE NUMBER:");
                                        String updatedPhoneNumber = inputMonitor.nextLine();

                                        try {
                                            sqlCode = ("UPDATE contacts SET ph_num = '%s' " +
                                                    "WHERE contact_id = %s").formatted(updatedPhoneNumber,
                                                    contact.getContactID());
                                            statement.executeUpdate(sqlCode);
                                            System.out.println("\nRecord updated.");
                                        } catch (SQLException e) {
                                            System.out.println("\nRecord update error!");
                                        }
                                    }
                                    case "3" -> {
                                        System.out.println("\nPlease submit UPDATED ADDRESS:");
                                        String updatedAddress = inputMonitor.nextLine();

                                        try {
                                            sqlCode = ("UPDATE contacts SET address = '%s' " +
                                                    "WHERE contact_id = %s").formatted(updatedAddress,
                                                    contact.getContactID());
                                            statement.executeUpdate(sqlCode);
                                            System.out.println("\nRecord updated.");
                                        } catch (SQLException e) {
                                            System.out.println("\nRecord update error!");
                                        }
                                    }
                                    case "4" -> {
                                        System.out.println("""
                                            \nPlease Update:
                                            '1' IF poised employee
                                            '0' IF NOT poised employee
                                            """);
                                        String updatedEmployee = inputMonitor.nextLine();
                                        boolean employeeBool = updatedEmployee.equals("1");

                                        try {
                                            sqlCode = ("UPDATE contacts SET poised_employee = %b " +
                                                    "WHERE contact_id = %s").formatted(employeeBool,
                                                    contact.getContactID());
                                            statement.executeUpdate(sqlCode);
                                            System.out.println("\nRecord updated.");
                                        } catch (SQLException e) {
                                            System.out.println("\nRecord update error!");
                                        }
                                    }
                                    default -> System.out.println("\nEdits abandoned.");
                                }
                            }
                            // delete contacts branch
                            case "2" -> {
                                System.out.println("\nPlease confirm if you want to delete the following record:");
                                contact.displayContact();
                                System.out.println("""
                                \n'1' to delete
                                '0' to abort deletion
                                """);

                                // deletion request confirmation
                                String confirmDelete = inputMonitor.nextLine();
                                if (confirmDelete.equals("1")) {
                                    try {
                                        sqlCode = "DELETE FROM contacts WHERE contact_id = " + idParameter;
                                        statement.executeUpdate(sqlCode);

                                        sqlCode = "SELECT * FROM contacts WHERE contact_id = %s".formatted(idParameter);
                                        results = statement.executeQuery(sqlCode);

                                        // confirmation that record no longer in DB
                                        if (!results.next()) {
                                            System.out.println("\nRecord deleted.");
                                        } else {
                                            System.out.println("\nRecord was not deleted. Try again.");
                                        }
                                    } catch (SQLIntegrityConstraintViolationException ex) {
                                        System.out.println("""
                                            \nContact deletion failed.
                                            Cannot delete a contact as long as they're related to a project.
                                            """);
                                    } catch (SQLException e) {
                                        System.out.println("\nSystem Error! Record was not deleted.");
                                    }
                                } else {
                                    System.out.println("\nContact deletion was aborted.");
                                }
                            }
                            default -> System.out.println("\nContact edit/delete aborted.");
                        }
                    } else {
                        System.out.println("\nNo such record in contacts with 'id' " + idParameter);
                    }
                } else {
                    System.out.println("\nNo records in contact table."); // terminal message
                }
            } catch (SQLException e) {
                System.out.println("\nSQL error!");
            }
        } else {
            System.out.println("\nYou aborted Contact Viewer."); // terminal message
        }
    }

    /**
     * Major feature method called in Main for the creation
     * of new contact records.
     *
     * @see Main
     * @since version 2.0
     */
    public void createContact() {
        // register user inputs
        Scanner inputMonitor = new Scanner(System.in);

        // terminal message
        System.out.println("""
        \nWelcome to Contact Creator:
        '1' to create an all-new Contact
        '0' to return to main menu
        """);

        String userInput = inputMonitor.nextLine();

        if (userInput.equals("1")) {
            // new object to assign user inputs as attributes
            Contact newContact = new Contact();

            // terminal message
            System.out.println("\nPlease follow prompts and submit the requester information:");

            System.out.println("\nPlease submit contact's first name:");
            String firstName = inputMonitor.nextLine();

            System.out.println("\nPlease submit contact's last name:");
            String lastName = inputMonitor.nextLine();

            System.out.println("\nPlease submit contact's email address:");
            String email = inputMonitor.nextLine();

            System.out.println("\nPlease submit contact's phone number:");
            String phoneNumber = inputMonitor.nextLine();

            System.out.println("\nPlease submit contact's physical address:");
            String physicalAddress = inputMonitor.nextLine();

            System.out.println("""
            \nPlease submit:
            '1' if poised employee
            '0' if not poised employee
            """);
            String poisedEmployee = inputMonitor.nextLine();

            newContact.setFirstName(firstName);
            newContact.setLastName(lastName);
            newContact.setEmail(email);
            newContact.setPhoneNumber(phoneNumber);
            newContact.setPhysicalAddress(physicalAddress);
            newContact.setPoisedEmployee(poisedEmployee.equals("1"));

            // confirm contact creation
            System.out.println("\nIf you are pleased with the contact preview:");
            newContact.displayContact();
            System.out.println("""
                    \n'1' to finalise contact creation
                    '0' to abort contact creation
                    """);

            String createContact = inputMonitor.nextLine();

            // if-else block writing to DB
            if (createContact.equals("1")) {
                try {
                    sqlCode = "INSERT INTO contacts VALUES (null, '%s', '%s', '%s', '%s', '%s', %b)".formatted(
                            newContact.getFirstName(), newContact.getLastName(), newContact.getEmail(),
                            newContact.getPhoneNumber(), newContact.getPhysicalAddress(), newContact.getPoisedEmployee());

                    statement.executeUpdate(sqlCode);
                    System.out.println("\nRecord created!");
                } catch (SQLException e) {
                    System.out.println("\nDB error! Contact creation aborted."); // terminal message
                }
            } else {
                System.out.println("\nYou aborted contact creation."); // terminal message
            }
        } else {
            System.out.println("\nYou aborted contact creation."); // terminal message
        }
    }

    /**
     * Simple method to provide default project name, should one not be specified by user.
     *
     * @param project Object to be modified.
     * @param customer Object providing attributes.
     * @since version 2.0
     */
    private void setDefaultProjectName(Project project, Contact customer) {
        project.setProjectName(project.getBuildingType() + " " + customer.getLastName());
    }

    /**
     * Utility method to drill down into any project entry in projects table.
     *
     * @param projectID User selected string entry of project_id to query.
     * @since version 2.0
     */
    private void accessEditDeleteProject(String projectID) {
        // query project and assign all variables to a project object
        Scanner inputMonitor = new Scanner(System.in);
        try {
            sqlCode = "SELECT * FROM projects WHERE project_id = %s".formatted(projectID);
            results = statement.executeQuery(sqlCode);

            if (results.next()) {
                Project currentProject = new Project();
                currentProject.setProjectNumber(results.getInt("project_id"));
                currentProject.setProjectName(results.getString("project_name"));
                currentProject.setBuildingType(results.getString("building_type"));
                currentProject.setBuildingAddress(results.getString("building_address"));
                currentProject.setErfNumber(results.getInt("erf_number"));
                currentProject.setExpectedFee(results.getDouble("expected_fee"));
                currentProject.setFeePaidToDate(results.getDouble("fee_paid"));
                currentProject.setProjectDeadline(results.getString("project_deadline"));
                currentProject.setProjectFinalised(results.getBoolean("project_finalised"));
                currentProject.setDateFinalised(results.getString("finalisation_date"));
                currentProject.setEngineerID(results.getInt("engineer_id"));
                currentProject.setArchitectID(results.getInt("architect_id"));
                currentProject.setContractorID(results.getInt("contractor_id"));
                currentProject.setCustomerID(results.getInt("customer_id"));

                System.out.println("\nPlease see your selected project:");
                currentProject.displayProject();

                System.out.println("""
                        \n'1' to edit project
                        '2' to view project contacts
                        '3' to delete project
                        '0' to return to main menu
                        """);

                String editInput = inputMonitor.nextLine();
                switch (editInput) {
                    // edit project attributes branch
                    case "1" -> {
                        System.out.println("\nYour project:");
                        currentProject.displayProject();
                        System.out.println("""
                            \nWhat project field would you like to update:
                            '1' Project Name
                            '2' Building Type
                            '3' Building Address
                            '4' Erf Number
                            '5' Expected Fee
                            '6' Fee Paid to Date
                            '7' Project Deadline
                            '8' Project Finalised?
                            '9' Engineer Contact
                            '10' Architect Contact
                            '11' Contractor Contact
                            '12' Customer Contact
                            '0' Abort Edits
                            """);

                        String userEditInput = inputMonitor.nextLine();
                        switch (userEditInput) {
                            // edit project name
                            case "1" -> {
                                System.out.println("\nPlease submit UPDATED PROJECT NAME:");
                                String updateProjectName = inputMonitor.nextLine();

                                try {
                                    sqlCode = ("UPDATE projects SET project_name = '%s' " +
                                            "WHERE project_id = %s").formatted(updateProjectName,
                                            currentProject.getProjectNumber());
                                    statement.executeUpdate(sqlCode);
                                    System.out.println("\nRecord updated.");
                                } catch (SQLException e) {
                                    System.out.println("\nRecord update error!");
                                }
                            }
                            // edit building type
                            case "2" -> {
                                System.out.println("\nPlease submit UPDATED BUILDING TYPE (e.g. House/Apartment/Office):");
                                String updateBuildingType = inputMonitor.nextLine();

                                try {
                                    sqlCode = ("UPDATE projects SET building_type = '%s' " +
                                            "WHERE project_id = %s").formatted(updateBuildingType,
                                            currentProject.getProjectNumber());
                                    statement.executeUpdate(sqlCode);
                                    System.out.println("\nRecord updated.");
                                } catch (SQLException e) {
                                    System.out.println("\nRecord update error!");
                                }
                            }
                            // edit building address
                            case "3" -> {
                                System.out.println("\nPlease submit UPDATED BUILDING ADDRESS:");
                                String updateBuildingAddress = inputMonitor.nextLine();

                                try {
                                    sqlCode = ("UPDATE projects SET building_address = '%s' " +
                                            "WHERE project_id = %s").formatted(updateBuildingAddress,
                                            currentProject.getProjectNumber());
                                    statement.executeUpdate(sqlCode);
                                    System.out.println("\nRecord updated.");
                                } catch (SQLException e) {
                                    System.out.println("\nRecord update error!");
                                }
                            }
                            // edit erf number
                            case "4" -> {
                                System.out.println("\nPlease submit UPDATED ERF NUMBER (e.g. 10101):");
                                String updateErfNumber = inputMonitor.nextLine();

                                try {
                                    sqlCode = ("UPDATE projects SET erf_number = %s " +
                                            "WHERE project_id = %s").formatted(updateErfNumber,
                                            currentProject.getProjectNumber());
                                    statement.executeUpdate(sqlCode);
                                    System.out.println("\nRecord updated.");
                                } catch (SQLException e) {
                                    System.out.println("\nRecord update error! Please ensure number format is correct.");
                                }
                            }
                            // edit project fee
                            case "5" -> {
                                System.out.println("\nPlease submit UPDATED PROJECT FEE (e.g. 10000000.00):");
                                String updateProjectFee = inputMonitor.nextLine();

                                try {
                                    sqlCode = ("UPDATE projects SET expected_fee = %s " +
                                            "WHERE project_id = %s").formatted(updateProjectFee,
                                            currentProject.getProjectNumber());
                                    statement.executeUpdate(sqlCode);
                                    System.out.println("\nRecord updated.");
                                } catch (SQLException e) {
                                    System.out.println("\nRecord update error! Please ensure number format is correct.");
                                }
                            }
                            // edit fee paid to date
                            case "6" -> {
                                System.out.println("\nPlease submit UPDATED FEE PAID TO DATE (e.g. 5000000.00):");
                                String updateFeePaid = inputMonitor.nextLine();

                                try {
                                    sqlCode = ("UPDATE projects SET fee_paid = %s " +
                                            "WHERE project_id = %s").formatted(updateFeePaid,
                                            currentProject.getProjectNumber());
                                    statement.executeUpdate(sqlCode);
                                    System.out.println("\nRecord updated.");
                                } catch (SQLException e) {
                                    System.out.println("\nRecord update error! Please ensure number format is correct.");
                                }
                            }
                            // edit project deadline
                            case "7" -> {
                                System.out.println("\nPlease submit UPDATED PROJECT DEADLINE (yyyy-MM-dd):");
                                String updateDeadline = inputMonitor.nextLine();

                                try {
                                    sqlCode = ("UPDATE projects SET project_deadline = '%s' " +
                                            "WHERE project_id = %s").formatted(updateDeadline,
                                            currentProject.getProjectNumber());
                                    statement.executeUpdate(sqlCode);
                                    System.out.println("\nRecord updated.");
                                } catch (SQLException e) {
                                    System.out.println("\nRecord update error! Please ensure date format is correct.");
                                }
                            }
                            // finalise project
                            case "8" -> {
                                System.out.println("""
                                    \nIs this project READY TO BE FINALISED:
                                    '1' to finalise project
                                    '0' to abort finalisation
                                    """);
                                String updateFinalised = inputMonitor.nextLine();

                                // finalisation confirmed
                                if (updateFinalised.equals("1")) {
                                    // current date set as project finalisation date in object
                                    currentProject.setDateFinalised(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                                    currentProject.setProjectFinalised(true); // finalisation state changed
                                    try {
                                        sqlCode = ("UPDATE projects SET project_finalised = true, finalisation_date = '%s' " +
                                                "WHERE project_id = %s").formatted(currentProject.getDateFinalised() ,
                                                currentProject.getProjectNumber());
                                        statement.executeUpdate(sqlCode);
                                        System.out.println("\nRecord updated.");
                                    } catch (SQLException e) {
                                        System.out.println("\nRecord update error!");
                                    }
                                }
                                // block for project finalisation if account is not yet settled, invoice printed to terminal
                                if (currentProject.getExpectedFee() - currentProject.getFeePaidToDate() > 0) {
                                    Contact currentCustomer = new Contact();
                                    try {
                                        sqlCode = ("SELECT * FROM contacts WHERE contact_id = " +
                                                "%s").formatted(currentProject.getCustomer());
                                        results = statement.executeQuery(sqlCode);

                                        // assign attributes to contact object
                                        if (results.next()) {
                                            currentCustomer.setFirstName(results.getString("f_name"));
                                            currentCustomer.setLastName(results.getString("s_name"));
                                            currentCustomer.setPhoneNumber(results.getString("ph_num"));
                                            currentCustomer.setEmail(results.getString("e_mail"));

                                            // invoice template printed to terminal
                                            String output = "\nProject Number: " + currentProject.getProjectNumber();
                                            output += "\nCustomer: " + currentCustomer.fullName();
                                            output += "\nPhone Number: " + currentCustomer.getPhoneNumber();
                                            output += "\nEmail Address: " + currentCustomer.getEmail();
                                            output += "\nAmount Outstanding: R" + String.format("%.2f", currentProject.getExpectedFee()
                                                    - currentProject.getFeePaidToDate());

                                            System.out.println(output);
                                        } else {
                                            System.out.println("\nPlease ensure customer contact has been assigned to project.");
                                        }
                                    } catch (SQLException e) {
                                        System.out.println("SQL Error! Invoice could not be calculated.");
                                        e.printStackTrace();
                                    }
                                }
                            }
                            // assigning contact as engineer contact
                            case "9" -> {
                                try {
                                    System.out.println("\nPlease submit UPDATED ENGINEER CONTACT ID from list below:");

                                    StringBuilder contactDB = new StringBuilder("""
                                        ID | NAME | SURNAME | EMAIL | PHONE NUMBER | ADDRESS | POISED EMPLOYEE
                                        """);

                                    sqlCode = "SELECT * FROM contacts WHERE poised_employee = true";
                                    results = statement.executeQuery(sqlCode);

                                    while (results.next()) {
                                        contactDB.append("\n").append(results.getInt("contact_id"));
                                        contactDB.append(", ").append(results.getString("f_name"));
                                        contactDB.append(", ").append(results.getString("s_name"));
                                        contactDB.append(", ").append(results.getString("e_mail"));
                                        contactDB.append(", ").append(results.getString("ph_num"));
                                        contactDB.append(", ").append(results.getString("address"));
                                        contactDB.append(", ").append(results.getBoolean("poised_employee"));
                                    }
                                    System.out.println(contactDB);
                                    results.beforeFirst();

                                    // assigning contact
                                    if (results.next()) {
                                        String updateEngineerID = inputMonitor.nextLine();
                                        sqlCode = ("UPDATE projects SET engineer_id = %s " +
                                                "WHERE project_id = %s").formatted(updateEngineerID,
                                                currentProject.getProjectNumber());
                                        statement.executeUpdate(sqlCode);
                                        System.out.println("\nRecord updated.");
                                    }
                                } catch (SQLException e) {
                                    System.out.println("\nRecord update error! Please ensure Poised employee Contacts " +
                                            "are available for assignment to projects.");
                                }
                            }
                            // assigning contact as architect contact
                            case "10" -> {
                                try {
                                    System.out.println("\nPlease submit UPDATED Architect CONTACT ID from list below:");

                                    StringBuilder contactDB = new StringBuilder("""
                                        ID | NAME | SURNAME | EMAIL | PHONE NUMBER | ADDRESS | POISED EMPLOYEE
                                        """);

                                    sqlCode = "SELECT * FROM contacts WHERE poised_employee = false";
                                    results = statement.executeQuery(sqlCode);

                                    while (results.next()) {
                                        contactDB.append("\n").append(results.getInt("contact_id"));
                                        contactDB.append(", ").append(results.getString("f_name"));
                                        contactDB.append(", ").append(results.getString("s_name"));
                                        contactDB.append(", ").append(results.getString("e_mail"));
                                        contactDB.append(", ").append(results.getString("ph_num"));
                                        contactDB.append(", ").append(results.getString("address"));
                                        contactDB.append(", ").append(results.getBoolean("poised_employee"));
                                    }
                                    System.out.println(contactDB);
                                    results.beforeFirst();

                                    if (results.next()) {
                                        String updateArchitectID = inputMonitor.nextLine();
                                        sqlCode = ("UPDATE projects SET architect_id = %s " +
                                                "WHERE project_id = %s").formatted(updateArchitectID,
                                                currentProject.getProjectNumber());
                                        statement.executeUpdate(sqlCode);
                                        System.out.println("\nRecord updated.");
                                    }
                                } catch (SQLException e) {
                                    System.out.println("\nRecord update error! Please ensure Contacts " +
                                            "are available for assignment to projects.");
                                }
                            }
                            // assigning contact as contractor contact
                            case "11" -> {
                                try {
                                    System.out.println("\nPlease submit UPDATED Contractor CONTACT ID from list below:");

                                    StringBuilder contactDB = new StringBuilder("""
                                        ID | NAME | SURNAME | EMAIL | PHONE NUMBER | ADDRESS | POISED EMPLOYEE
                                        """);

                                    sqlCode = "SELECT * FROM contacts WHERE poised_employee = false";
                                    results = statement.executeQuery(sqlCode);

                                    while (results.next()) {
                                        contactDB.append("\n").append(results.getInt("contact_id"));
                                        contactDB.append(", ").append(results.getString("f_name"));
                                        contactDB.append(", ").append(results.getString("s_name"));
                                        contactDB.append(", ").append(results.getString("e_mail"));
                                        contactDB.append(", ").append(results.getString("ph_num"));
                                        contactDB.append(", ").append(results.getString("address"));
                                        contactDB.append(", ").append(results.getBoolean("poised_employee"));
                                    }
                                    System.out.println(contactDB);
                                    results.beforeFirst();

                                    if (results.next()) {
                                        String updateArchitectID = inputMonitor.nextLine();
                                        sqlCode = ("UPDATE projects SET contractor_id = %s " +
                                                "WHERE project_id = %s").formatted(updateArchitectID,
                                                currentProject.getProjectNumber());
                                        statement.executeUpdate(sqlCode);
                                        System.out.println("\nRecord updated.");
                                    }
                                } catch (SQLException e) {
                                    System.out.println("\nRecord update error! Please ensure Contacts " +
                                            "are available for assignment to projects.");
                                }
                            }
                            // assigning contact as customer contact
                            case "12" -> {
                                try {
                                    System.out.println("\nPlease submit UPDATED Customer CONTACT ID from list below:");

                                    StringBuilder contactDB = new StringBuilder("""
                                        ID | NAME | SURNAME | EMAIL | PHONE NUMBER | ADDRESS | POISED EMPLOYEE
                                        """);

                                    sqlCode = "SELECT * FROM contacts WHERE poised_employee = false";
                                    results = statement.executeQuery(sqlCode);

                                    while (results.next()) {
                                        contactDB.append("\n").append(results.getInt("contact_id"));
                                        contactDB.append(", ").append(results.getString("f_name"));
                                        contactDB.append(", ").append(results.getString("s_name"));
                                        contactDB.append(", ").append(results.getString("e_mail"));
                                        contactDB.append(", ").append(results.getString("ph_num"));
                                        contactDB.append(", ").append(results.getString("address"));
                                        contactDB.append(", ").append(results.getBoolean("poised_employee"));
                                    }
                                    System.out.println(contactDB);
                                    results.beforeFirst();

                                    if (results.next()) {
                                        String updateCustomerID = inputMonitor.nextLine();
                                        sqlCode = ("UPDATE projects SET customer_id = %s " +
                                                "WHERE project_id = %s").formatted(updateCustomerID,
                                                currentProject.getProjectNumber());
                                        statement.executeUpdate(sqlCode);
                                        System.out.println("\nRecord updated.");
                                    }
                                } catch (SQLException e) {
                                    System.out.println("\nRecord update error! Please ensure Contacts " +
                                            "are available for assignment to projects.");
                                }
                            }
                            default -> System.out.println("\nEdits abandoned.");
                        }
                    }
                    // view contacts assigned to project roles
                    case "2" -> {
                        System.out.println("""
                            \nSelect the contact you want to view:
                            '1' to view poised engineer contact
                            '2' to view architect contact
                            '3' to view contractor contact
                            '4' to view customer contact
                            '0' to abort contact view
                            """);

                        String contactViewSelect = inputMonitor.nextLine();

                        switch (contactViewSelect) {
                            case "1" -> singleContactView(currentProject.getEngineer());
                            case "2" -> singleContactView(currentProject.getArchitect());
                            case "3" -> singleContactView(currentProject.getContractor());
                            case "4" -> singleContactView(currentProject.getCustomer());
                            default -> System.out.println("\nProject contact view aborted.");
                        }
                    }
                    // delete project from db
                    case "3" -> {
                        System.out.println("\nPlease confirm if you want to delete the following record:");
                        currentProject.displayProject();

                        System.out.println("""
                            \n'1' to delete
                            '0' to abort deletion
                            """);

                        String confirmDelete = inputMonitor.nextLine();
                        if (confirmDelete.equals("1")) {
                            try {
                                sqlCode = "DELETE FROM projects WHERE project_id = " + projectID;
                                statement.executeUpdate(sqlCode);

                                sqlCode = "SELECT * FROM projects WHERE project_id = %s".formatted(projectID);
                                results = statement.executeQuery(sqlCode);
                                if (!results.next()) {
                                    System.out.println("\nRecord deleted.");
                                } else {
                                    System.out.println("\nRecord was not deleted. Try again.");
                                }
                            } catch (SQLException e) {
                                System.out.println("\nSystem Error! Record was not deleted.");
                            }
                        } else {
                            System.out.println("\nProject deletion was aborted.");
                        }
                    }
                    default -> System.out.println("\nProject edit/delete aborted.");
                }
            }
        } catch (SQLException e) {
            System.out.println("\nNo such record in projects table.");
        }
    }

    /**
     * Utility method to view a single overview of a contact,
     * printed to terminal, for any given contact_id.
     *
     * @param contactId for DB entry in contacts Table
     * @since version 2.0
     */
    private void singleContactView(int contactId) {
        try {
            // set preview template for display in terminal
            StringBuilder db = new StringBuilder("\nID | NAME | SURNAME | EMAIL | PHONE NUMBER | ADDRESS | POISED EMPLOYEE\n");
            results = statement.executeQuery("SELECT * FROM contacts WHERE contact_id = %d".formatted(contactId));

            // write query result to string for display
            if (results.next()) {
                db.append(results.getInt("contact_id"));
                db.append(", ").append(results.getString("f_name"));
                db.append(", ").append(results.getString("s_name"));
                db.append(", ").append(results.getString("e_mail"));
                db.append(", ").append(results.getString("ph_num"));
                db.append(", ").append(results.getString("address"));
                System.out.println("\nSee selected contact below:" + db);
            } else {
                System.out.println("\nNo contact assigned to role. Please edit the project to assign a contact.");
            }
        } catch (SQLException e) {
            System.out.println("\nSQL Error!");
            e.printStackTrace();
        }
    }
}