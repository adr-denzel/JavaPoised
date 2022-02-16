import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Project Class
 * <br>
 * <br>
 * The nexus of where all data in this program eventually intersects. Project object is the primary
 * architecture of how information stored in the 4 text files actually relates to each other.
 * <br>
 * <br>
 * In numerous instances Contact class methods are utilised in Project methods, due to the
 * relationship of project to contact.
 * <br>
 * <br>
 * Methods are called and Project objects are instantiated in Branch Class.
 *
 * @see Branch
 * @see Architect
 * @see Contractor
 * @see Customer
 * @see Contact
 * @see Main
 * @author Denzel Ramsbottom
 * @version 1.0, 9 February 2022
 */
public class Project {

    /**
     * String value for the characteristic type of the building.
     */
    private String buildingType;

    /**
     * Contact value for the assigned Architect of the project.
     */
    private Contact architect = new Architect();

    /**
     * Contact value for the assigned Contractor of the project.
     */
    private Contact contractor = new Contractor();

    /**
     * Contact value for the assigned Customer of the project.
     */
    private Contact customer =  new Customer();

    /**
     * Integer value of the project number.
     */
    private int projectNumber;

    /**
     * Integer value of the project's erf number.
     */
    private int erfNumber;

    /**
     * Double value of the fee charged for completing the project.
     */
    private double expectedFee;

    /**
     * Double value of the fees paid to date before completing the project.
     */
    private double feePaidToDate;

    /**
     * String value for the project's name.
     */
    private String projectName;

    /**
     * Boolean value for the completion status od the project.
     */
    private boolean projectFinalised = false;

    /**
     * String value for the physical address of the project. Generic String set as default.
     */
    private String buildingAddress = "Please set Project Address";

    /**
     * String value for the agreed due date for completion of the project. Generic String set as default.
     */
    private String projectDeadline = "Please set Project Deadline";

    /**
     * String value for the date upon which the project is finalised. Generic String set as default.
     */
    private String dateFinalised = "Project In-progress";

    /**
     *
     * Constructor Method with an Empty Signature
     * <br>
     * <br>
     * Used to instantiate objects hassle-free and when required.
     *
     * @see Branch
     * @since version 1.0
     */
    public Project() {}

    /**
     *
     * setDefaultName() Method called in Branch
     * <br>
     * <br>
     * This nifty little routine is called in the createProject()
     * branch in Branch to fulfil requirement that unless modified,
     * a project name will simply be the concatenation of Customer
     * last name and the building type.
     *
     * @see Branch
     * @since version 1.0
     */
    public void setDefaultName() {
        Scanner inputMonitor = new Scanner(System.in);

        System.out.println("\nWhat type of building is this project ('Apartment'/'Office'/'House'/etc:");

        this.projectName = inputMonitor.nextLine() + " " + customer.getLastName();
    }

    /**
     *
     * setNewProjectNumber() Method is Called in Branch
     * <br>
     * <br>
     * This method is special as it is only called for newly created projects and the for the
     * objects that make them up. This sets up a default project number based on the number
     * of existing lines of project data in 'projects.txt'
     * <br>
     * <br>
     * Thus project numbers are never duplicated, nor overwritten. A method to keep indexes and
     * identities of projects in good order between themselves for the users ease of organisation.
     * <br>
     * <br>
     * Plus what kinda of a project management application mixes up project data...
     * A bad one!
     *
     * @see Branch
     * @since version 1.0
     */
    public void setNewProjectNumber() {
        // inspiration for these 2 lines: https://stackoverflow.com/questions/4032957/how-to-get-the-real-path-of-java-application-at-runtime
        File getPath = new File("");
        String pathAbsolute = getPath.getAbsolutePath();

        try {
            File inputFile = new File(pathAbsolute + this.getProjectPath());
            Scanner lineInputs = new Scanner(inputFile);
            int counter = 1;

            while (lineInputs.hasNextLine()) {
                lineInputs.nextLine();
                counter ++;
            }

            this.projectNumber = counter;
        }
        catch (IOException e) {
            System.out.println("IO Error! No existing projects.");
        }
    }

    /**
     *
     * Setter method for modifying project number attribute.
     *
     * @param projectNumber Project number value
     * @since version 1.0
     */
    public void setProjectNumber(String projectNumber) {
        this.projectNumber = Integer.parseInt(projectNumber);
    }

    /**
     *
     * Setter method for modifying project name attribute.
     *
     * @param projectName Project name value.
     * @since version 1.0
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     *
     * Setter method for modifying the project building type attribute.
     *
     * @param buildingType Building type value.
     * @since version 1.0
     */
    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    /**
     *
     * Setter method for modifying the project building address attribute.
     *
     * @param buildingAddress Building address value
     * @since version 1.0
     */
    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    /**
     *
     * Setter method for modifying the project erf number attribute.
     *
     * @param erfNumber Erf number value
     * @since version 1.0
     */
    public void setErfNumber(String erfNumber) {
        this.erfNumber = Integer.parseInt(erfNumber);
    }

    /**
     *
     * Setter method for modifying the project expected fee attribute.
     *
     * @param expectedFee Expected fee value.
     * @since version 1.0
     */
    public void setExpectedFee(String expectedFee) {
        this.expectedFee = Double.parseDouble(expectedFee);
    }

    /**
     *
     * Setter method for modifying the project fee paid to date attribute.
     *
     * @param feePaidToDate Fee paid to date value.
     * @since version 1.0
     */
    public void setFeePaidToDate(String feePaidToDate) {
        this.feePaidToDate = Double.parseDouble(feePaidToDate);
    }

    /**
     *
     * Setter method for modifying the project deadline attribute.
     *
     * @param projectDeadline Project deadline value.
     * @since version 1.0
     */
    public void setProjectDeadline(String projectDeadline) {
        this.projectDeadline = projectDeadline;
    }

    /**
     *
     * Setter method for modifying the project finalised attribute.
     *
     * @param projectFinalised Project finalised value.
     * @since version 1.0
     */
    public void setProjectFinalised(String projectFinalised) {
        this.projectFinalised = Boolean.parseBoolean(projectFinalised);
    }

    /**
     *
     * Setter method for modifying the project finalisation date attribute.
     *
     * @param dateFinalised Date finalised value.
     * @since version 1.0
     */
    public void setDateFinalised(String dateFinalised) {
        this.dateFinalised = dateFinalised;
    }

    /**
     *
     * Setter method for modifying the project architect attribute.
     *
     * @param architect Architect value.
     * @since version 1.0
     */
    public void setArchitect(Contact architect) {
        this.architect = architect;
    }

    /**
     *
     * Setter method for modifying the project contractor attribute.
     *
     * @param contractor Contractor value.
     * @since version 1.0
     */
    public void setContractor(Contact contractor) {
        this.contractor = contractor;
    }

    /**
     *
     * Setter method for modifying the project customer attribute.
     *
     * @param customer Customer value.
     * @since version 1.0
     */
    public void setCustomer(Contact customer) {
        this.customer = customer;
    }

    /**
     * finaliseProject() Method
     * <br>
     * <br>
     * This method is called in the selectEditSubmitProject() method.
     * This is used to change state of projectFinalise as well as record the
     * date on which that occurs. As per client request.
     *
     * @see Branch
     * @since version 1.0
     */
    public void finaliseProject() {
        this.projectFinalised = true;
        this.dateFinalised = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     *
     * Getter method to return project number attribute.
     *
     * @return Project number value.
     * @since version 1.0
     */
    public int getProjectNumber() {
        return projectNumber;
    }

    /**
     *
     * Getter method to return project name attribute.
     *
     * @return Project name value
     * @since version 1.0
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     *
     * Getter method to return project building type attribute.
     *
     * @return Building type value.
     * @since version 1.0
     */
    public String getBuildingType() {
        return buildingType;
    }

    /**
     *
     * Getter method to return project physical address attribute.
     *
     * @return Project physical address value.
     * @since version 1.0
     */
    public String getBuildingAddress() {
        return buildingAddress;
    }

    /**
     *
     * Getter method to return project efr number attribute.
     *
     * @return Erf number value.
     * @since version 1.0
     */
    public int getErfNumber() {
        return erfNumber;
    }

    /**
     *
     * Getter method to return project expected fee attribute.
     *
     * @return Project fee value.
     * @since version 1.0
     */
    public double getExpectedFee() {
        return expectedFee;
    }

    /**
     *
     * Getter method to return project fees paid to date.
     *
     * @return Fees paid to date value.
     * @since version 1.0
     */
    public double getFeePaidToDate() {
        return feePaidToDate;
    }

    /**
     *
     * Getter method to return project deadline attribute.
     *
     * @return Project deadline value.
     * @since version 1.0
     */
    public String getProjectDeadline() {
        return projectDeadline;
    }

    /**
     *
     * Getter method to return project date finalised attribute.
     *
     * @return Date finalised value.
     * @since version 1.0
     */
    public String getDateFinalised() {
        return dateFinalised;
    }

    /**
     *
     * Getter method to return project finalised attribute.
     *
     * @return Project finalised value.
     * @since version 1.0
     */
    public boolean getProjectFinalised() {
        return projectFinalised;
    }

    /**
     *
     * Getter method to return project architect attribute.
     *
     * @return Architect value.
     * @since version 1.0
     */
    public Contact getArchitect() {
        return architect;
    }

    /**
     *
     * Getter method to return project contractor attribute.
     *
     * @return Contractor value.
     * @since version 1.0
     */
    public Contact getContractor() {
        return contractor;
    }

    /**
     *
     * Getter method to return project customer attribute.
     *
     * @return Customer value.
     * @since version 1.0
     */
    public Contact getCustomer() {
        return customer;
    }

    /**
     *
     * getInvoice() Method
     * <br>
     * <br>
     * This is somewhat of a toString() type situation, although it only fires
     * once it's been called from selectEditSubmitProject() in Branch. When this
     * fires the output is made to terminal. User only sees prompt if the account is
     * in arrears. Should accounts be settled. Method does not fire.
     *
     * @return String of the final state of accounts and critical project
     * details.
     * @see Branch
     * @since version 1.0
     */
    public String getInvoice() {
        String output = "Project Number: " + projectNumber;
        output += "\nCustomer: " + customer.getName();
        output += "\nPhone Number: " + customer.getPhoneNumber();
        output += "\nEmail Address: " + customer.getEmail();
        output += "\nAmount Outstanding: R" + (expectedFee - feePaidToDate);

        return output;
    }

    /**
     *
     * String method indicating route to 'projects.txt'
     *
     * @return 'projects.txt.' project file path.
     * @since version 1.0
     */
    public String getProjectPath() {
        return "/Task 24/capstone_3/src/main/resources/projects.txt";
    }

    /**
     *
     * toString() Method
     * <br>
     * <br>
     * Used all over the place to get text output of any object we're handling.
     * Useful for viewing details for the user.
     *
     * @return String representation of all project attributes.
     * @since version 1.0
     */
    public String toString() {
        String output = "Project Number: " + projectNumber;
        output += "\nProject Name: " + projectName;
        output += "\nAddress: " + buildingAddress;
        output += "\nErf: " + erfNumber;
        output += "\nExpected Fee: " + String.format("%.2f", expectedFee);
        output += "\nFee Paid to Date: " + String.format("%.2f", feePaidToDate);
        output += "\nProject Deadline: " + projectDeadline;
        output += "\nProject Complete: " + projectFinalised;
        if (projectFinalised) {
            output += "\nCompleted Date: " + dateFinalised;
        }
        output += "\nProject Architect: " + architect.getName();
        output += "\nProject Contractor: " + contractor.getName();
        output += "\nProject Customer: " + customer.getName();
        return output;
    }

    /**
     * createGeneralAttributes() Method
     * <br>
     * <br>
     * This is called in Branch on a newly instantiated project object to provide
     * additional input to flesh out project attributes before append to project text file.
     * Straightforward series of Q&A before committing to new project, the modified attributes.
     *
     * @see Branch
     * @since version 1.0
     */
    public void createGeneralAttributes() {
        Scanner inputMonitor = new Scanner(System.in);
        try {
            setNewProjectNumber();
            System.out.println("Please enter the erf number:");
            String submitErfNumber = inputMonitor.nextLine();
            System.out.println("Please enter the expected fee for this project:");
            String submitExpectedFee = inputMonitor.nextLine();
            System.out.println("Please enter the fees paid to date for this project:");
            String submitFeesPaidToDate = inputMonitor.nextLine();
            System.out.println("Please enter the project name:");
            String submitProjectName = inputMonitor.nextLine();
            System.out.println("Please enter the project address:");
            String submitBuildingAddress = inputMonitor.nextLine();
            System.out.println("Please enter the project deadline (e.g. 17/03/1968):");
            String submitProjectDeadline = inputMonitor.nextLine();

            if (!submitErfNumber.equals("")) {
                this.setErfNumber(submitErfNumber);
            }
            if (!submitExpectedFee.equals("")) {
                this.setExpectedFee(submitExpectedFee);
            }
            if (!submitFeesPaidToDate.equals("")) {
                this.setFeePaidToDate(submitFeesPaidToDate);
            }
            if (!submitProjectName.equals("")) {
                this.setProjectName(submitProjectName);
            }
            if (!submitBuildingAddress.equals("")) {
                this.setBuildingAddress(submitBuildingAddress);
            }
            if (!submitProjectDeadline.equals("")) {
                this.setProjectDeadline(submitProjectDeadline);
            }
        }
        catch (NumberFormatException e) {
            System.out.println("\nError! Please ensure you submit numbers where required.");
        }
    }

    /**
     *
     * selectEditSubmitProjects() Method
     * <br>
     * <br>
     * Complete project selection, and based on that selection, editing, and saving
     * edits to 'projects.txt'.
     *
     * @param allProjects Updated ArrayList of all projects, containing data
     *                    that was modified in method call.
     * @see Branch
     * @since version 1.0
     */
    public void selectEditSubmitProjects(ArrayList<Project> allProjects) {
        Scanner inputMonitor = new Scanner(System.in);
        String indexInput = inputMonitor.nextLine();
        Project activeProject = null;

        if (!indexInput.equals("0")) {
            for (Project i : allProjects) {
                if (Integer.parseInt(indexInput) == i.getProjectNumber()) {
                    activeProject = i;
                }
            }
            if (activeProject != null) {
                System.out.println("\nYour selected Project");
                System.out.println("__________________________________________");
                System.out.println(activeProject.toString());

                System.out.println("\nPlease submit project attributes at their respective prompts.");
                System.out.println("Submit empty strings for attributes you do not want changed.\n");

                System.out.println("Submit project name:");
                String nameSubmit = inputMonitor.nextLine();
                System.out.println("Submit building type (Apartment, office, flat, house, etc.):");
                String buildingSubmit = inputMonitor.nextLine();
                System.out.println("Submit building address:");
                String addressSubmit = inputMonitor.nextLine();
                System.out.println("Submit erf number:");
                String erfSubmit = inputMonitor.nextLine();
                System.out.println("Submit project fee:");
                String feeSubmit = inputMonitor.nextLine();
                System.out.println("Submit fee paid to date:");
                String feePaidSubmit = inputMonitor.nextLine();
                System.out.println("Submit deadline date (dd/mm/yyyy):");
                String deadlineSubmit = inputMonitor.nextLine();

                System.out.println("Would you like to reassign your current architect 'yes' or 'no'?");
                String architectReassign = inputMonitor.nextLine();
                if (architectReassign.equalsIgnoreCase("yes")) {
                    activeProject.getArchitect().assignContact();
                }
                System.out.println("Would you like to reassign your current contractor 'yes' or 'no'?");
                String contractorReassign = inputMonitor.nextLine();
                if (contractorReassign.equalsIgnoreCase("yes")) {
                    activeProject.getContractor().assignContact();
                }
                System.out.println("Would you like to reassign your current customer 'yes' or 'no'?");
                String customerReassign = inputMonitor.nextLine();
                if (customerReassign.equalsIgnoreCase("yes")) {
                    activeProject.getCustomer().assignContact();
                }

                if (!nameSubmit.equals("")) {
                    activeProject.setProjectName(nameSubmit);
                }
                if (!buildingSubmit.equals("")) {
                    activeProject.setBuildingType(buildingSubmit);
                }
                if (!addressSubmit.equals("")) {
                    activeProject.setBuildingAddress(addressSubmit);
                }
                if (!erfSubmit.equals("")) {
                    activeProject.setErfNumber(erfSubmit);
                }
                if (!feeSubmit.equals("")) {
                    activeProject.setExpectedFee(feeSubmit);
                }
                if (!feePaidSubmit.equals("")) {
                    activeProject.setFeePaidToDate(feePaidSubmit);
                }
                if (!deadlineSubmit.equals("")) {
                    activeProject.setProjectDeadline(deadlineSubmit);
                }
                System.out.println("Would you like to finalise and close-out this project, 'yes' or 'no'?");
                String projectFinalise = inputMonitor.nextLine();
                if (projectFinalise.equalsIgnoreCase("yes")) {
                    activeProject.finaliseProject();
                }

                System.out.println("\nYour edited project");
                System.out.println(activeProject.toString());

                System.out.println("\nTo write all your changes please enter 'submit'.");
                System.out.println("'0' to abandon changes.");

                String finalInput = inputMonitor.nextLine();
                try {
                    // inspiration for these 2 lines: https://stackoverflow.com/questions/4032957/how-to-get-the-real-path-of-java-application-at-runtime
                    File getPath = new File("");
                    String pathAbsolute = getPath.getAbsolutePath();

                    if (finalInput.equalsIgnoreCase("submit")) {
                        FileWriter inputFile = new FileWriter(pathAbsolute + activeProject.getProjectPath());

                        String dataLine;

                        for (Project j : allProjects) {
                            dataLine = j.getProjectNumber() + "--";
                            dataLine += j.getProjectName() + "--";
                            dataLine += j.getBuildingType() + "--";
                            dataLine += j.getBuildingAddress() + "--";
                            dataLine += j.getErfNumber() + "--";
                            dataLine += j.getExpectedFee() + "--";
                            dataLine += j.getFeePaidToDate() + "--";
                            dataLine += j.getProjectDeadline() + "--";
                            dataLine += j.getProjectFinalised() + "--";
                            dataLine += j.getDateFinalised() + "--";
                            dataLine += j.getArchitect().getFirstName() + "--";
                            dataLine += j.getArchitect().getLastName() + "--";
                            dataLine += j.getArchitect().getEmail() + "--";
                            dataLine += j.getArchitect().getPhoneNumber() + "--";
                            dataLine += j.getArchitect().getPhysicalAddress() + "--";
                            dataLine += j.getContractor().getFirstName() + "--";
                            dataLine += j.getContractor().getLastName() + "--";
                            dataLine += j.getContractor().getEmail() + "--";
                            dataLine += j.getContractor().getPhoneNumber() + "--";
                            dataLine += j.getContractor().getPhysicalAddress() + "--";
                            dataLine += j.getCustomer().getFirstName() + "--";
                            dataLine += j.getCustomer().getLastName() + "--";
                            dataLine += j.getCustomer().getEmail() + "--";
                            dataLine += j.getCustomer().getPhoneNumber() + "--";
                            dataLine += j.getCustomer().getPhysicalAddress() + "\n";

                            inputFile.write(dataLine);
                        }
                        inputFile.close();
                        if (activeProject.getProjectFinalised() && activeProject.getFeePaidToDate() < activeProject.getExpectedFee()) {
                            System.out.println("\nProject closed. Please see invoice below\n");
                            System.out.println(activeProject.getInvoice());
                        }
                        System.out.println("\nEdits successfully written to file.");
                    } else {
                        System.out.println("Edits abandoned.");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid input. Please ensure you submit a valid project number.");
                } catch (NumberFormatException e) {
                    System.out.println("Edits aborted. Please ensure you submit an integer if you're interested in an 'index'");
                } catch (IOException e) {
                    System.out.println("File not found.");
                }
            } else {
                System.out.println("You entered a non-existent project number.");
            }
        }
    }

    /**
     *
     * confirmAndAppend() Method
     * <br>
     * <br>
     * Used post creation of a new project, this appends the newly created
     * project data to 'projects.txt'.
     *
     * @since version 1.0
     * @see Branch
     */
    public void confirmAndAppend() {
        Scanner inputMonitor = new Scanner(System.in);

        System.out.println("\nPlease confirm whether to save the following project to storage.");
        System.out.println("\n" + this.toString());
        System.out.println("\nPlease enter 'submit' to confirm, or 'anything else' to abort.");

        String userInput = inputMonitor.nextLine();

        // inspiration for these 2 lines: https://stackoverflow.com/questions/4032957/how-to-get-the-real-path-of-java-application-at-runtime
        File getPath = new File("");
        String pathAbsolute = getPath.getAbsolutePath();

        if (userInput.equalsIgnoreCase("submit")) {
            try {
                //ref: https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
                FileWriter input = new FileWriter(pathAbsolute + this.getProjectPath(), true);

                String dataLine;

                dataLine = this.getProjectNumber() + "--";
                dataLine += this.getProjectName() + "--";
                dataLine += this.getBuildingType() + "--";
                dataLine += this.getBuildingAddress() + "--";
                dataLine += this.getErfNumber() + "--";
                dataLine += this.getExpectedFee() + "--";
                dataLine += this.getFeePaidToDate() + "--";
                dataLine += this.getProjectDeadline() + "--";
                dataLine += this.getProjectFinalised() + "--";
                dataLine += this.getDateFinalised() + "--";
                dataLine += this.getArchitect().getFirstName()+ "--";
                dataLine += this.getArchitect().getLastName() + "--";
                dataLine += this.getArchitect().getEmail() + "--";
                dataLine += this.getArchitect().getPhoneNumber() + "--";
                dataLine += this.getArchitect().getPhysicalAddress() + "--";
                dataLine += this.getContractor().getFirstName()+ "--";
                dataLine += this.getContractor().getLastName() + "--";
                dataLine += this.getContractor().getEmail() + "--";
                dataLine += this.getContractor().getPhoneNumber() + "--";
                dataLine += this.getContractor().getPhysicalAddress() + "--";
                dataLine += this.getCustomer().getFirstName()+ "--";
                dataLine += this.getCustomer().getLastName() + "--";
                dataLine += this.getCustomer().getEmail() + "--";
                dataLine += this.getCustomer().getPhoneNumber() + "--";
                dataLine += this.getCustomer().getPhysicalAddress() + "\n";

                input.write(dataLine);
                input.close();
                System.out.println("\n" + this.getProjectName() + " project created.");
            }
            catch (IOException e) {
                System.out.println("File not found!");
            }
        }
        else {
            System.out.println("Project aborted. New project not saved to database.");
        }
    }

    /**
     *
     * collectProjects() Method
     * <br>
     * <br>
     * Reads 'projects.txt' line by line and translates data splits into Project objects,
     * with supporting Architect, Contractor, and Customer objects. All ready to be read
     * and used in programme.
     *
     * @return ArrayList of all projects made from data in 'projects.txt'.
     * @see Branch
     * @since version 1.0
     */
    public ArrayList<Project> collectProjects() {
        ArrayList<Project> allProjects = new ArrayList<>();

        // inspiration for these 2 lines: https://stackoverflow.com/questions/4032957/how-to-get-the-real-path-of-java-application-at-runtime
        File getPath = new File("");
        String pathAbsolute = getPath.getAbsolutePath();

        try {
            File inputFile = new File(pathAbsolute + this.getProjectPath());
            Scanner lineInputs = new Scanner(inputFile);

            while (lineInputs.hasNextLine()) {
                Project conduitProject = new Project();
                Contact conduitArchitect = new Architect();
                Contact conduitContractor = new Contractor();
                Contact conduitCustomer = new Customer();

                conduitProject.setArchitect(conduitArchitect);
                conduitProject.setContractor(conduitContractor);
                conduitProject.setCustomer(conduitCustomer);

                String lineData = lineInputs.nextLine();

                // ref: https://www.geeksforgeeks.org/split-string-java-examples/
                String[] lineDataSplit = lineData.split("--");

                conduitProject.setProjectNumber(lineDataSplit[0]);
                conduitProject.setProjectName(lineDataSplit[1]);
                conduitProject.setBuildingType(lineDataSplit[2]);
                conduitProject.setBuildingAddress(lineDataSplit[3]);
                conduitProject.setErfNumber(lineDataSplit[4]);
                conduitProject.setExpectedFee(lineDataSplit[5]);
                conduitProject.setFeePaidToDate(lineDataSplit[6]);
                conduitProject.setProjectDeadline(lineDataSplit[7]);
                conduitProject.setProjectFinalised(lineDataSplit[8]);
                conduitProject.setDateFinalised(lineDataSplit[9]);
                conduitProject.getArchitect().setFirstName(lineDataSplit[10]);
                conduitProject.getArchitect().setLastName(lineDataSplit[11]);
                conduitProject.getArchitect().setEmail(lineDataSplit[12]);
                conduitProject.getArchitect().setPhoneNumber(lineDataSplit[13]);
                conduitProject.getArchitect().setPhysicalAddress(lineDataSplit[14]);
                conduitProject.getContractor().setFirstName(lineDataSplit[15]);
                conduitProject.getContractor().setLastName(lineDataSplit[16]);
                conduitProject.getContractor().setEmail(lineDataSplit[17]);
                conduitProject.getContractor().setPhoneNumber(lineDataSplit[18]);
                conduitProject.getContractor().setPhysicalAddress(lineDataSplit[19]);
                conduitProject.getCustomer().setFirstName(lineDataSplit[20]);
                conduitProject.getCustomer().setLastName(lineDataSplit[21]);
                conduitProject.getCustomer().setEmail(lineDataSplit[22]);
                conduitProject.getCustomer().setPhoneNumber(lineDataSplit[23]);
                conduitProject.getCustomer().setPhysicalAddress(lineDataSplit[24]);

                allProjects.add(conduitProject);
            }
        }
        catch (IOException e) {
            System.out.println("File not found.");
        }
        return allProjects;
    }

    /**
     * viewAllProjects() Method
     * <br>
     * <br>
     * Simple looping text output template to view all projects collected in ArrayList.
     *
     * @param allProjects ArrayList of all projects made from data in 'projects.txt'.
     * @see Branch
     * @since version 1.0
     */
    public void viewAllProjects(ArrayList<Project> allProjects) {
        if (!allProjects.isEmpty()) {
            System.out.println("\nAll registered projects");
            for (Project i : allProjects) {
                System.out.println("__________________________________________");
                System.out.println(i.toString());
            }
        }
        else {
            System.out.println("\nNo Projects to list from this Directory.");
        }
    }

    /**
     * viewIncompleteProjects() Method
     * <br>
     * <br>
     * Simple looping text output template with some conditions to act as filter for what
     * is displayed on command.
     *
     * @param allProjects ArrayList of all projects made from data in 'projects.txt'.
     * @see Branch
     * @since version 1.0
     */
    public void viewIncompleteProjects(ArrayList<Project> allProjects) {
        if (!allProjects.isEmpty()) {
            System.out.println("\nAll incomplete projects");
            for (Project i : allProjects) {
                if (!i.getProjectFinalised()) {

                    System.out.println("__________________________________________");
                    System.out.println(i.toString());
                }
                else {
                    System.out.print("\nThere are no incomplete projects.");
                }
            }
        }
        else {
            System.out.println("\nNo Projects to list from this Directory.");
        }
    }

    /**
     *
     * viewOverdueProjects() Method
     * <br>
     * <br>
     * Simple looping text output template with some conditions to act as filter for what
     * is displayed on command.
     *
     * @param allProjects ArrayList of all projects made from data in 'projects.txt'.
     * @see Branch
     * @since version 1.0
     */
    public void viewOverdueProjects(ArrayList<Project> allProjects) {
        if (!allProjects.isEmpty()) {
            System.out.println("\nAll overdue projects");
            int count = 0;
            for (Project i : allProjects) {
                if (!i.getProjectFinalised() && i.projectOverdue()) {
                    count++;
                    System.out.println("__________________________________________");
                    System.out.println(i.toString());
                }
            }
            if (count == 0) {
                System.out.println("\nThere are no overdue projects.");
            }
        }
        else {
            System.out.println("\nNo Projects to list from this Directory.");
        }
    }

    /**
     * projectOverdue() Method in Branch
     * <br>
     * <br>
     * Based on the current date and the deadline date (and that all has been formatted as necessary
     * on user input) this little method assesses if a project object has data from an
     * overdue project.
     * <br>
     * <br>
     * This method is called in Branch by virtue of it being embedded in viewOverdueProjects() method
     * from Project.
     *
     * @return Boolean whether project is overdue.
     * @see Branch
     * @since version 1.0
     */
    public boolean projectOverdue(){
        boolean output = false;

        // ref: https://mkyong.com/java8/java-8-how-to-convert-string-to-localdate/
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = LocalDate.parse(this.getProjectDeadline(), formatter);
        try {
            // ref: https://stackoverflow.com/questions/55704166/how-can-i-check-if-a-date-for-a-rent-is-overdue-the-rent-is-overdue-after-30-da
            if (dueDate.isBefore(currentDate)) {
                output = true;
            }
        }
        catch (InputMismatchException e) {
            System.out.println("\nPlease ensure date format of project 'DateFinalised' field is invalid.");
        }
        return output;
    }

    /**
     *
     * searchProject() Method
     * <br>
     * <br>
     * Very similar to any of the viewing methods utilised throughout this project
     * to give user visibility to their inputs. Only difference is this display
     * method will not automatically give a view of all projects.
     * User prompted for 'number' or 'name' to branch into correct routine
     * to search for project via number input or name input.
     *
     * @param allProjects ArrayList of all projects made from data in 'projects.txt'.
     * @see Branch
     * @since version 1.0
     */
    public void searchProject(ArrayList<Project> allProjects) {
        if (!allProjects.isEmpty()) {
            Scanner inputMonitor = new Scanner(System.in);
            System.out.println("\nPlease submit 'name' or 'number' to select project search method.");
            System.out.println("Please submit '0' to return to main menu.");
            String userInput = inputMonitor.nextLine();

            if (userInput.equalsIgnoreCase("name")) {
                System.out.println("\nPlease submit the desired project 'name'");
                String projectSearch = inputMonitor.nextLine();

                for (Project i : allProjects) {
                    if (i.getProjectName().equalsIgnoreCase(projectSearch)) {
                        System.out.println("\nProject Found!");
                        System.out.println("__________________________________________");
                        System.out.println(i.toString());
                    }
                }
            }
            else if (userInput.equalsIgnoreCase("number")) {
                System.out.println("\nPlease submit the desired project 'number'");
                String projectSearch = inputMonitor.nextLine();

                for (Project i : allProjects) {
                    if (i.getProjectNumber() == Integer.parseInt(projectSearch)) {
                        System.out.println("\nProject Found!");
                        System.out.println("__________________________________________");
                        System.out.println(i.toString());
                    }
                }
            }
            else {
                ;
            }
        }
        else {
            System.out.println("\nNo Projects to list from this Directory.");
        }
    }
}