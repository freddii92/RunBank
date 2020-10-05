import java.util.ArrayList;

/**
 * Customer class is child class of Person
 *
 * @author Alfredo Rodriguez
 * @version 1.0, 09/25/2020
 * @since September 25, 2020
 */
public class Customer extends Person {

    /**
     * This is the default constructor
     */
    public Customer() {
        super();
    }

    /**
     * Constructor for Customer
     *
     * @param firstNameIn Receives first name @see Person
     * @param lastNameIn Receives last name @see Person
     * @param dateOfBirthIn Receives date of birth @see Person
     * @param identificationNumberIn Receives ID number @see Person
     * @param addressIn Receives address @see Person
     * @param phoneNumberIn Receives phone number @see Person
     */
    public Customer(String firstNameIn, String lastNameIn, String dateOfBirthIn, int identificationNumberIn, String addressIn, long phoneNumberIn) {
        super(firstNameIn, lastNameIn, dateOfBirthIn, identificationNumberIn, addressIn, phoneNumberIn);
    }

    /**
     * This method is used to search for the index of the customer array list using ID number.
     *
     * @param customerArrayList Receives customer array list.
     * @param identificationNumber Receives ID number
     * @return index from array list
     */
    public int searchAccount(ArrayList<Customer> customerArrayList, int identificationNumber) {

        int index = 0;

        // for loop that will iterate through the array list
        // once identification number is found, index is assigned
        for (int i = 0; i < customerArrayList.size(); i++) {
            if (identificationNumber == customerArrayList.get(i).getIdentificationNumber()) {
                index = i;
            }
        }
        return index;
    }

    /**
     * This method is used to search for the index of the customer array list using the first name
     * @param customerArrayList Receives customer array list.
     * @param name Receives first name
     * @return index from array list
     */
    public int searchAccount(ArrayList<Customer> customerArrayList, String name) {

        int i = 0;

        for (i = 0; i < customerArrayList.size(); i++) {
            if (name.equals(customerArrayList.get(i).getFirstName())) {
                break;
            }
        }

        return i;
    }
}
