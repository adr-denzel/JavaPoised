/**
 * Customer Class
 * <br>
 * <br>
 * This class extends the abstract class Contact.
 * <br>
 * <br>
 * @see Contact
 * @author Denzel Ramsbottom
 * @version 1.0, 9 February 2022
*/
public class Customer extends Contact {

    /**
     *
     * Constructor method with an Empty Signature
     * <br>
     * <br>
     * Used to instantiate objects without hassle
     * in supporting classes & methods.
     *
     * @see Branch
     * @see Contact
     * @since version 1.0
     */
    public Customer() {}

    /**
     *
     * Overridden String Method
     * <br>
     * <br>
     * Modifies abstract method in Contact.
     * One of the methods to distinguish Contact types
     * String representation of the Customer class.
     *
     * @return String value indicating the Contact type an object represents
     * @see Contact
     * @since version 1.0
     */
    @Override
    public String getContactType() {
        return "Customer";
    }

    /**
     *
     * Overridden String Method
     * <br>
     * <br>
     * Modifies abstract method in Contact.
     * One of the methods to distinguish Contact types
     * String indicating route to 'customers.txt' file.
     *
     * @return String value of route to 'customers.txt' file
     * @see Contact
     * @since version 1.0
     */
    @Override
    public String getContactPath() {
        return "/Task 24/capstone_3/src/main/resources/customers.txt";
    }
}