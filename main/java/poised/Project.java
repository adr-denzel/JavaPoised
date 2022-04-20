package poised;

/**
 * Project Class
 * <br>
 * <br>
 * Data model for db projects table; getters and setters included.
 *
 * @see DBHandler
 * @see Main
 * @author Denzel Ramsbottom
 * @version 1.0, 9 February 2022
 */
public class Project {

    /**
     * int value for project number.
     */
    private int projectNumber;

    /**
     * String value for the project's name.
     */
    private String projectName;

    /**
     * String value for building type description.
     */
    private String buildingType;

    /**
     * String value for building physical address.
     */
    private String buildingAddress;

    /**
     * int value for erf number.
     */
    private int erfNumber;

    /**
     * Double value for project fee.
     */
    private double expectedFee;

    /**
     * Double value for fee paid to date.
     */
    private double feePaidToDate;

    /**
     * String value for project deadline day.
     */
    private String projectDeadline;

    /**
     * Boolean value for project finalisation status.
     */
    private boolean projectFinalised;

    /**
     * String value for project finalisation date.
     */
    private String dateFinalised;

    /**
     * int value for engineer contact ID.
     */
    private int engineerID;

    /**
     * int value for architect contact ID.
     */
    private int architectID;

    /**
     * int value for contractor contact ID.
     */
    private int contractorID;

    /**
     * int value for customer contact ID
     */
    private int customerID;

    /**
     * Constructor with empty signature.
     *
     * @since version 1.0
     */
    public Project() {}

    /**
     * Setter method for project number attribute.
     *
     * @param projectNumber Project number/Project DB ID.
     * @since version 1.0
     */
    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }

    /**
     * Setter method for project name attribute.
     *
     * @param projectName Project name.
     * @since version 1.0
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Setter method for building type description attribute.
     *
     * @param buildingType Building type.
     * @since version 1.0
     */
    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    /**
     * Setter method for building address attribute.
     *
     * @param buildingAddress Building address.
     * @since version 1.0
     */
    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    /**
     * Setter method for erf number attribute.
     *
     * @param erfNumber Erf number.
     * @since version 1.0
     */
    public void setErfNumber(int erfNumber) {
        this.erfNumber = erfNumber;
    }

    /**
     * Setter method for expected fee attribute.
     *
     * @param expectedFee Expected fee.
     * @since version 1.0
     */
    public void setExpectedFee(double expectedFee) {
        this.expectedFee = expectedFee;
    }

    /**
     * Setter method for fee paid to date attribute.
     *
     * @param feePaidToDate Fee paid to date.
     * @since version 1.0
     */
    public void setFeePaidToDate(double feePaidToDate) {
        this.feePaidToDate = feePaidToDate;
    }

    /**
     * Setter method for project deadline date attribute.
     *
     * @param projectDeadline Project deadline date.
     * @since version 1.0
     */
    public void setProjectDeadline(String projectDeadline) {
        this.projectDeadline = projectDeadline;
    }

    /**
     * Setter method for project finalised status attribute.
     *
     * @param projectFinalised Project finalised status.
     * @since version 1.0
     */
    public void setProjectFinalised(boolean projectFinalised) {
        this.projectFinalised = projectFinalised;
    }

    /**
     * Setter method for project finalisation date attribute.
     *
     * @param dateFinalised Project finalisation date.
     * @since version 1.0
     */
    public void setDateFinalised(String dateFinalised) {
        this.dateFinalised = dateFinalised;
    }

    /**
     * Setter method for project engineer contact ID attribute.
     *
     * @param engineerID Engineer ID.
     * @since version 2.0
     */
    public void setEngineerID(int engineerID) {
        this.engineerID = engineerID;
    }

    /**
     * Setter method for project architect contact ID attribute.
     *
     * @param architectID Architect ID.
     * @since version 2.0
     */
    public void setArchitectID(int architectID) {
        this.architectID = architectID;
    }

    /**
     * Setter method for project contractor contact ID attribute.
     *
     * @param contractorID Contractor ID.
     * @since version 2.0
     */
    public void setContractorID(int contractorID) {
        this.contractorID = contractorID;
    }

    /**
     * Setter method for project customer contact ID attribute.
     *
     * @param customerID Customer value.
     * @since version 2.0
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Getter method for project number attribute.
     *
     * @return Project number.
     * @since version 1.0
     */
    public int getProjectNumber() {
        return projectNumber;
    }

    /**
     * Getter method for project name attribute.
     *
     * @return Project name value
     * @since version 1.0
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Getter method for project building type description attribute.
     *
     * @return Building type description.
     * @since version 1.0
     */
    public String getBuildingType() {
        return buildingType;
    }

    /**
     * Getter method for project physical address attribute.
     *
     * @return Project physical address.
     * @since version 1.0
     */
    public String getBuildingAddress() {
        return buildingAddress;
    }

    /**
     * Getter method for project efr number attribute.
     *
     * @return Erf number.
     * @since version 1.0
     */
    public int getErfNumber() {
        return erfNumber;
    }

    /**
     * Getter method for project expected fee attribute.
     *
     * @return Expected project fee.
     * @since version 1.0
     */
    public double getExpectedFee() {
        return expectedFee;
    }

    /**
     * Getter method for project fees paid to date.
     *
     * @return Fees paid to date.
     * @since version 1.0
     */
    public double getFeePaidToDate() {
        return feePaidToDate;
    }

    /**
     * Getter method for project deadline attribute.
     *
     * @return Project deadline date.
     * @since version 1.0
     */
    public String getProjectDeadline() {
        return projectDeadline;
    }

    /**
     * Getter method for project finalisation status attribute.
     *
     * @return Project finalisation status.
     * @since version 1.0
     */
    public boolean getProjectFinalised() {
        return projectFinalised;
    }

    /**
     * Getter method for project finalisation date attribute.
     *
     * @return Date finalised.
     * @since version 1.0
     */
    public String getDateFinalised() {
        return dateFinalised;
    }

    /**
     * Getter method for project engineer's contact ID attribute.
     *
     * @return Engineer's contact ID.
     * @since version 2.0
     */
    public int getEngineer() {
        return engineerID;
    }

    /**
     * Getter method for project architect's contact ID attribute.
     *
     * @return Architect's contact ID.
     * @since version 2.0
     */
    public int getArchitect() {
        return architectID;
    }

    /**
     * Getter method for project contractor's contact ID attribute.
     *
     * @return Contractor's contact ID.
     * @since version 2.0
     */
    public int getContractor() {
        return contractorID;
    }

    /**
     * Getter method for project customer's contact ID attribute.
     *
     * @return Customer's contact ID.
     * @since version 2.0
     */
    public int getCustomer() {
        return customerID;
    }

    // string representation of a project sans contact details
    public void displayProject() {
        // terminal message for string representation for project
        String message = "\nProject Number: " + this.projectNumber;
        message += "\nProject Name: " + this.projectName;
        message += "\nBuilding Type: " + this.buildingType;
        message += "\nBuilding Address: " + this.buildingAddress;
        message += "\nErf Number: " + this.erfNumber;
        message += "\nExpected Fee: %,.2f".formatted(this.expectedFee);
        message += "\nFee Paid-to-Date: %,.2f".formatted(this.feePaidToDate);
        message += "\nProject Deadline: " + this.projectDeadline;
        message += "\nProject Finalised: " + this.projectFinalised;
        // only printed if project has been finalised
        if (projectFinalised) {
            message += "\nFinalisation Date: " + this.dateFinalised;
        }
        System.out.println(message);
    }
}