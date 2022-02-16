/**
 * Contractor Class
 * <br>
 * <br>
 * This class extends the abstract class Contact.
 *
 * @see Contact
 * @author Denzel Ramsbottom
 * @version 1.0, 9 February 2022
 */
public class Contractor extends Contact {

    /**
     *
     * Constructor Method with an Empty Signature
     * <br>
     * <br>
     * Used to instantiate objects without hassle
     * in supporting classes & methods.
     *
     * @see Branch
     * @see Contact
     * @since version 1.0
     */
    public Contractor() {}

    /**
     *
     * Overridden String Method
     * <br>
     * <br>
     * Modifies abstract method in Contact.
     * One of the methods to distinguish Contact types
     * String representation of the Contractor class.
     *
     * @return String value indicating the Contact type an object represents
     * @see Contact
     * @since version 1.0
     */
    @Override
    public String getContactType() {
        return "Contractor";
    }

    /**
     *
     * Overridden String Method
     * <br>
     * <br>
     * Modifies abstract method in Contact.
     * One of the methods to distinguish Contact types
     * String indicating route to 'contractors.txt' file.
     *
     * @return String value of route to 'contractors.txt' file
     * @see Contact
     * @since version 1.0
     */
    @Override
    public String getContactPath() {
        return "/Task 24/capstone_3/src/main/resources/contractors.txt";
    }
}