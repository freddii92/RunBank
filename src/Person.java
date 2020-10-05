/**
 * Person class is an abstract class that has a child class called Customer
 *
 * @author Alfredo Rodriguez
 * @version 1.0 09/25/2020
 * @since September 25, 2020
 *
 */
public abstract class Person {

    // initializing attributes
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int identificationNumber;
    private String address;
    private long phoneNumber;

    /**
     * This is the defaulted constructor
     */
    public Person() {
    }

    /**
     * Constructor for Person
     *
     * @param firstNameIn Receives first name.
     * @param lastNameIn Receives last name.
     * @param dateOfBirthIn Receives date of birth.
     * @param identificationNumberIn Receives ID number.
     * @param addressIn Receives address.
     * @param phoneNumberIn Receives phone number.
     */
    public Person(String firstNameIn, String lastNameIn, String dateOfBirthIn, int identificationNumberIn, String addressIn, long phoneNumberIn) {
        this.firstName = firstNameIn;
        this.lastName = lastNameIn;
        this.dateOfBirth = dateOfBirthIn;
        this.identificationNumber = identificationNumberIn;
        this.address = addressIn;
        this.phoneNumber = phoneNumberIn;
    }

    /**
     * This method gets the first name.
     *
     * @return first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * This method sets the first name.
     *
     * @param firstName Receives first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method gets the last name.
     *
     * @return last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * This method sets the last name.
     *
     * @param lastName Receives last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method gets the date of birth.
     *
     * @return date of birth
     */
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * This method sets the date of birth.
     *
     * @param dateOfBirth Receives date of birth.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * This method gets the ID number.
     *
     * @return ID number
     */
    public int getIdentificationNumber() {
        return this.identificationNumber;
    }

    /**
     * This method sets the ID number.
     *
     * @param identificationNumber Receives ID number
     */
    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    /**
     * This method gets the address
     *
     * @return address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * This method sets the address.
     *
     * @param address Receives address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method gets the phone number.
     *
     * @return phone number
     */
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * This method sets the phone number
     *
     * @param phoneNumber Receives phone number
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
