package poised;

/**
 * Contact Class
 * <br>
 * <br>
 * Data model for db contacts table; getters and setters included.
 *
 * @see DBHandler
 * @see Main
 * @author Denzel Ramsbottom
 * @version 1.0, 9 Feb 2022
 */
public class Contact {

    /**
     * int value for contactID.
     */
    private int contactID;

    /**
     * String value for first name.
     */
    private String firstName;

    /**
     * String value for last name.
     */
    private String lastName;

    /**
     * String value for email address.
     */
    private String email;

    /**
     * String value for phone number.
     */
    private String phoneNumber;

    /**
     * String value for physical address.
     */
    private String physicalAddress;

    /**
     * Boolean value if poised Employee.
     */
    private boolean poisedEmployee;

    /**
     * Constructor with empty signature.
     *
     * @since version 1.0
     */
    public Contact() {}

    /**
     * Setter method for contactID attribute.
     *
     * @param contactID Contact's DB ID.
     * @since version 1.0
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Setter method for first name attribute.
     *
     * @param firstName First name.
     * @since version 1.0
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter method for last name attribute.
     *
     * @param lastName Last name.
     * @since version 1.0
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Setter method for email address attribute.
     *
     * @param email Email address.
     * @since version 1.0
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter method for phone number attribute.
     *
     * @param phoneNumber Phone number
     * @since version 1.0
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Setter method for physical address attribute.
     *
     * @param physicalAddress Physical address
     * @since version 1.0
     */
    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    /**
     * Setter method for poised employee attribute.
     *
     * @param poisedEmployee if poised employee
     * @since version 2.0
     */
    public void setPoisedEmployee(boolean poisedEmployee) {
        this.poisedEmployee = poisedEmployee;
    }

    /**
     * Getter method for contactID attribute.
     *
     * @return Contact's DB ID
     * @since version 1.0
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Getter method for first name attribute.
     *
     * @return Contact's first name
     * @since version 1.0
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter method for last name attribute.
     *
     * @return Contact's last name
     * @since version 1.0
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter method for email address attribute.
     *
     * @return Contact's email address.
     * @since version 1.0
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter method for phone number attribute.
     *
     * @return Contact's phone number
     * @since version 1.0
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Getter method for physical address attribute.
     *
     * @return Contact's physical address
     * @since version 1.0
     */
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    /**
     * Getter method for poised employee attribute.
     *
     * @return Poised employee boolean.
     * @since version 2.0
     */
    public boolean getPoisedEmployee() {
        return poisedEmployee;
    }

    /**
     * Method to print string representation of contact to terminal
     *
     * @since version 2.0
     */
    public void displayContact() {
        // terminal message for string representation of contact
        String message = "\nName: " + this.firstName;
        message += "\nSurname: " + this.lastName;
        message += "\nEmail: " + this.email;
        message += "\nPhone number: " + this.phoneNumber;
        message += "\nPhysical address: " + this.physicalAddress;
        message += "\nPoised employee: " + this.poisedEmployee;

        System.out.println(message);
    }

    /**
     * Method to concatenate first and last names as a single string.
     *
     * @since version 1.0
     */
    public String fullName() {
        return firstName + " " + lastName;
    }
}