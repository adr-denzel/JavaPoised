import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * Contact Class
 * <br>
 * <br>
 * This abstract class contains the major implementations of getters, setters,
 * and more complex methods to do with Contact (Architect, Contractor, Customer)
 * interactions, inputs, and outputs.
 * <br>
 * <br>
 * Methods are called and subclass objects are instantiated in Branch Class.
 *
 * @see Branch
 * @see Architect
 * @see Contractor
 * @see Customer
 * @see Project
 * @see Main
 * @author Denzel Ramsbottom
 * @version 1.0, 9 February 2022
 */
public abstract class Contact {

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
     *
     * Setter method for modifying first name attribute.
     *
     * @param firstName First name value.
     * @since version 1.0
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * Setter method for modifying last name attribute.
     *
     * @param lastName Last name value.
     * @since version 1.0
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * Setter method for modifying email address attribute.
     *
     * @param email Email address value.
     * @since version 1.0
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * Setter method for modifying phone number attribute.
     *
     * @param phoneNumber Phone number value.
     * @since version 1.0
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * Setter method for modifying physical address attribute.
     *
     * @param physicalAddress Physical address method.
     * @since version 1.0
     */
    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    /**
     *
     * Getter method to return contact's full name.
     *
     * @return Returns concatenated first and last name attributes with a space.
     * @since version 1.0
     */
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     *
     * Getter method to return email address attribute.
     *
     * @return Returns contact's email address.
     * @since version 1.0
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * Getter method to return last name attribute.
     *
     * @return Returns contact's last name.
     * @since version 1.0
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * Getter method to return first name attribute.
     *
     * @return Returns contact's first name.
     * @since version 1.0
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * Getter method to return phone number attribute.
     *
     * @return Returns contact's phone number.
     * @since version 1.0
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * Getter method to return physical address attribute.
     *
     * @return Returns contact's physical address.
     * @since version 1.0
     */
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    /**
     *
     * toString() Method
     * <br>
     * <br>
     * Provides an easy method of calling and getting text output
     * of Contact subclass objects. Useful in text based programmes
     * like this one.
     *
     * @return Returns a String representation of all object attributes
     * concatenated to an output String, with prefixes for each attribute type.
     * @since version 1.0
     */
    public String toString() {
        String output = "Name: " + firstName;
        output += "\nSurname: " + lastName;
        output += "\nEmail: " + email;
        output += "\nPhone Number: " + phoneNumber;
        output += "\nphysicalAddress: " + physicalAddress;

        return output;
    }

    /**
     *
     * getContactType() Abstract Method
     *<br>
     *<br>
     * Expanded upon in subclasses Architect, Contractor, and Customer.
     *
     * @return Returns String representation of the Contact object type.
     * @see Architect
     * @see Contractor
     * @see Customer
     * @since version 1.0
     */
    public abstract String getContactType();

    /**
     *
     * getContactPath() Abstract Method
     * <br>
     * <br>
     * Expanded upon in subclasses Architect, Contractor, and Customer
     *
     * @return Returns String value of relative path to file storing Class related
     * data.
     * @see Architect
     * @see Contractor
     * @see Customer
     * @since version 1.0
     */
    public abstract String getContactPath();

    /**
     *
     * createContact() Method Called in Branch
     * <br>
     * <br>
     * Simple series of text prompts for inputs that are assigned to a Contact subclass
     * object as attributes. This routine is useful in creation of Project object, or new contact
     * subclass object.
     *
     * @see Branch
     * @since version 1.0
     */
    public void createContact() {
        Scanner inputMonitor = new Scanner(System.in);

        System.out.println("\nCreate "+ this.getContactType() +" Contact");
        System.out.println("Please submit " + this.getContactType() + "'s first name:");
        String firstName = inputMonitor.nextLine();
        System.out.println("Please submit " + this.getContactType() + "'s last name:");
        String lastName = inputMonitor.nextLine();
        System.out.println("Please submit " + this.getContactType() + "'s email address:");
        String email = inputMonitor.nextLine();
        System.out.println("Please submit " + this.getContactType() + "'s phone number:");
        String phoneNumber = inputMonitor.nextLine();
        System.out.println("Please submit " + this.getContactType() + "'s physical address:");
        String physicalAddress = inputMonitor.nextLine();

        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setPhysicalAddress(physicalAddress);
    }

    /**
     *
     * assignContact() Method Called in Main
     * <br>
     * <br>
     * A routine in which an active contact subclass object in Branch is used to call assignContact()
     * which initiated the selection of contact details/objects from data stored in text file, or
     * prompts user to create new set of data for storage to respective contact text file storage
     * location. Assigned contact data is tied to object which then forms part of input for new
     * project object creation and data storage.
     *
     * @see Branch
     * @since version 1.0
     */
    public void assignContact() {
        Scanner inputMonitor = new Scanner(System.in);

        ArrayList<Contact> allContacts = this.collectContacts();
        if (!allContacts.isEmpty()) {
            System.out.println("\nAll registered " + this.getContactType() + "s");
            for (Contact i : allContacts) {
                System.out.println("__________________________________________");
                System.out.println("Index: " + (allContacts.indexOf(i) + 1) + "\n" + i.toString());
            }
            System.out.println("\nIf your contact is registered, submit their 'index' to assign as " + this.getContactType() + ".");
            System.out.println("Otherwise submit 'new', to create a new contact.");
        }
        else {
            System.out.println("\n" + this.getContactType() + " directory is empty.");
            System.out.println("Please proceed with 'new' " + this.getContactType() + " creation.");
        }

        String userInput = inputMonitor.nextLine();

        if (userInput.equalsIgnoreCase("new")) {
            this.createContact();
            this.confirmAndAppend();
            System.out.println("Contact Appended to Database.");
        }
        else {
            try {
                int index = Integer.parseInt(userInput) - 1;
                this.setFirstName(allContacts.get(index).getFirstName());
                this.setLastName(allContacts.get(index).getLastName());
                this.setEmail(allContacts.get(index).getEmail());
                this.setPhoneNumber(allContacts.get(index).getPhoneNumber());
                this.setPhysicalAddress(allContacts.get(index).getPhysicalAddress());

                System.out.println("\n" + allContacts.get(index).getName() + " assigned as " + this.getContactType() + ".");
            }
            catch (Exception e) {
                System.out.println("\nInput Error. Please ensure you submit an 'index' within range of what's available.");
            }
        }
    }

    /**
     *
     * collectContacts() Method Called in Branch
     * <br>
     * <br>
     * Nice routine that when called reads source files of respective contact type and
     * returns an ArrayList of contact subclass objects that bear attributes of the data
     * read from respective text file.
     *
     * @return ArrayList of all contacts typed like the object the method is called from.
     * Thus according relative to the object type, that text is reference to create and collect
     * contact subclass objects from.
     * @see Branch
     * @see Architect
     * @see Contractor
     * @see Customer
     * @since version 1.0
     */
    public ArrayList<Contact> collectContacts() {
        ArrayList<Contact> collectContacts = new ArrayList<>();

        // inspiration for these 2 lines: https://stackoverflow.com/questions/4032957/how-to-get-the-real-path-of-java-application-at-runtime
        File getPath = new File("");
        String pathAbsolute = getPath.getAbsolutePath();

        try {
            File inputFile = new File(pathAbsolute + this.getContactPath());
            Scanner lineInputs = new Scanner(inputFile);

            // new contact subclass object created based on the contactType of the
            // object instantiated in Branch, where this method is called from
            while (lineInputs.hasNextLine()) {
                Contact conduit = switch (this.getContactType()) {
                    case "Architect" -> new Architect();
                    case "Contractor" -> new Contractor();
                    case "Customer" -> new Customer();
                    default -> null;
                };
                String lineData = lineInputs.nextLine();

                // ref: https://www.geeksforgeeks.org/split-string-java-examples/
                String[] lineDataSplit = lineData.split("--");

                conduit.setFirstName(lineDataSplit[0]);
                conduit.setLastName(lineDataSplit[1]);
                conduit.setEmail(lineDataSplit[2]);
                conduit.setPhoneNumber(lineDataSplit[3]);
                conduit.setPhysicalAddress(lineDataSplit[4]);

                collectContacts.add(conduit);
            }
        }
        catch (IOException e) {
            System.out.println("File not found.");
        }
        return collectContacts;
    }

    /**
     *
     * viewContacts() Method Called in Branch
     * <br>
     * <br>
     * A simple looped viewing template to present the ArrayList of collected contact types
     * in text form in series. Good for giving user visual on what they'll be selecting, or
     * simply when inspecting contacts.
     *
     * @param allContacts ArrayList of all contact objects collected from respective text file.
     * @see Branch
     * @since version 1.0
     */
    public void viewContacts(ArrayList<Contact> allContacts) {
        if (!allContacts.isEmpty()) {
            System.out.println("\nAll registered " + allContacts.get(0).getContactType() + "s");
            for (Contact i : allContacts) {
                System.out.println("__________________________________________");
                System.out.println("Index: " + (allContacts.indexOf(i) + 1) + "\n" + i.toString());
            }
        }
        else {
            System.out.println("\nNo contacts to list from this Directory.");
        }
    }

    /**
     *
     * selectEditSubmitContact() Method Called in Branch
     * <br>
     * <br>
     * This is an editing and updating routine used to select a contact object, based
     * off of what's offered in selection from viewContacts() method called in Branch.
     * <br>
     * <br>
     * User inputs the index of the contact they want to edit, no matter where in ArrayList
     * it is located. Object of interest is accessed and the prompts to modify any particular
     * attributes is offered.
     * <br>
     * <br>
     * Following this the user is prompted to write their changes to respective Architect,
     * Contractor, Customer text file. These changes are saved for future access to data,
     * as entire file is overwritten with original and edited data.
     *
     * @param allContacts ArrayList of all contact objects collected from respective text file.
     * @see Branch
     * @since version 1.0
     */
    public void selectEditSubmitContact(ArrayList<Contact> allContacts) {
        Scanner inputMonitor = new Scanner(System.in);
        String indexInput = inputMonitor.nextLine();

        if (!indexInput.equals("0")) {
            try {
                Contact activeObject = allContacts.get(Integer.parseInt(indexInput) - 1);
                System.out.println("\nYour selected " + activeObject.getContactType());
                System.out.println("__________________________________________");
                System.out.println(activeObject.toString());

                System.out.println("\nPlease submit contact attributes at their respective prompts.");
                System.out.println("Submit empty strings for attributes you do not want changed.\n");

                System.out.println("Submit " + activeObject.getContactType() + " email:");
                String emailSubmit = inputMonitor.nextLine();
                System.out.println("Submit " + activeObject.getContactType() + " phone number:");
                String phoneSubmit = inputMonitor.nextLine();
                System.out.println("Submit " + activeObject.getContactType() + " physical address:");
                String addressSubmit = inputMonitor.nextLine();

                if (!emailSubmit.equals("")) {
                    activeObject.setEmail(emailSubmit);
                }
                if (!phoneSubmit.equals("")) {
                    activeObject.setPhoneNumber(phoneSubmit);
                }
                if (!addressSubmit.equals("")) {
                    activeObject.setPhysicalAddress(addressSubmit);
                }
                System.out.println("\nYour " + activeObject.getContactType() + " contact changes");
                System.out.println(activeObject.toString());

                System.out.println("To finalise you edits please enter 'submit'.");
                System.out.println("'0' to abandon edits.");

                String finalInput = inputMonitor.nextLine();

                // inspiration for these 2 lines: https://stackoverflow.com/questions/4032957/how-to-get-the-real-path-of-java-application-at-runtime
                File getPath = new File("");
                String pathAbsolute = getPath.getAbsolutePath();

                if (finalInput.equalsIgnoreCase("submit")) {
                    FileWriter inputFile = new FileWriter(pathAbsolute + activeObject.getContactPath());

                    String dataLine;

                    for (Contact i : allContacts) {
                        dataLine = i.getFirstName() + "--";
                        dataLine += i.getLastName() + "--";
                        dataLine += i.getEmail() + "--";
                        dataLine += i.getPhoneNumber() + "--";
                        dataLine += i.getPhysicalAddress() + "\n";

                        inputFile.write(dataLine);
                    }
                    inputFile.close();
                    System.out.println("Edits successfully written to file.");
                } else {
                    System.out.println("Edits abandoned.");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid input. Please ensure you submit an integer within bounds as 'index'");
            } catch (NumberFormatException e) {
                System.out.println("Edits aborted. Please ensure you submit an integer if you're interested in an 'index'");
            } catch (IOException e) {
                System.out.println("File not found.");
            }
        }
    }

    /**
     *
     * confirmAndAppend() Method Called in Branch
     * <br>
     * <br>
     * Essentially this method will be called on a contact object of interest and
     * append its attributes to the appropriate text file for storage among the
     * existing contact attributes for either Architects, Contractors, or Customers.
     *
     * @see Branch
     * @since version 1.0
     */
    public void confirmAndAppend() {
        Scanner inputMonitor = new Scanner(System.in);

        // user message prompt based on the active object's attributes
        System.out.println("\nPlease confirm whether to save the following contact to storage.");
        System.out.println("\n" + this.toString());
        System.out.println("\nPlease enter 'submit' to confirm, or 'anything else' to abort.");

        String userInput = inputMonitor.nextLine();

        // inspiration for these 2 lines: https://stackoverflow.com/questions/4032957/how-to-get-the-real-path-of-java-application-at-runtime
        File getPath = new File("");
        String pathAbsolute = getPath.getAbsolutePath();

        if (userInput.equalsIgnoreCase("submit")) {
            try {
                //ref: https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
                FileWriter input = new FileWriter(pathAbsolute + this.getContactPath(), true);

                String dataLine;

                dataLine = this.getFirstName() + "--";
                dataLine += this.getLastName() + "--";
                dataLine += this.getEmail() + "--";
                dataLine += this.getPhoneNumber() + "--";
                dataLine += this.getPhysicalAddress() + "\n";

                input.write(dataLine);
                input.close();
                System.out.println(this.getContactType() + " created!");
            }
            catch (IOException e) {
                System.out.println("File not found!");
            }
        }
        else {
            System.out.println("Edits aborted. New contact not saved to database.");
        }
    }
}