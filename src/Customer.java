import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Customer class is child class of Person
 *
 * @author Alfredo Rodriguez
 * @version 1.0, 09/25/2020
 * @since September 25, 2020
 */
public class Customer extends Person {

    private int checkingAccountNumber;
    private double checkingStartingBalance;
    private int savingsAccountNumber;
    private double savingsStartingBalance;
    private int creditAccountNumber;
    private double creditStartingBalance;
    private Checking userChecking = new Checking();

    /**
     * This method gets the checking account number
     *
     * @return checking account number
     */
    public int getCheckingAccountNumber() {
        return this.checkingAccountNumber;
    }

    /**
     * This method sets checking account number
     *
     * @param checkingAccountNumberIn Receives checking account number
     */
    public void setCheckingAccountNumber(int checkingAccountNumberIn) {
        this.checkingAccountNumber = checkingAccountNumberIn;
    }

    /**
     * This method gets the checking starting balance
     *
     * @return checking account number
     */
    public double getCheckingStartingBalance() {
        return this.checkingStartingBalance;
    }

    /**
     * This method sets the checking starting balance
     *
     * @param checkingStartingBalanceIn Receives checking starting balance
     */
    public void setCheckingStartingBalance(double checkingStartingBalanceIn) {
        this.checkingStartingBalance = checkingStartingBalanceIn;
    }

    /**
     * This method gets the savings account number
     *
     * @return savings account number
     */
    public int getSavingsAccountNumber() {
        return this.savingsAccountNumber;
    }

    /**
     * This method sets savings account number
     *
     * @param savingsAccountNumberIn receives savings account number
     */
    public void setSavingsAccountNumber(int savingsAccountNumberIn) {
        this.savingsAccountNumber = savingsAccountNumberIn;
    }

    /**
     * This method gets the savings starting balance
     *
     * @return savings starting balance
     */
    public double getSavingsStartingBalance() {
        return this.savingsStartingBalance;
    }

    /**
     * This method sets savings starting balance
     * @param savingsStartingBalanceIn Receives savings starting balance
     */
    public void setSavingsStartingBalance(double savingsStartingBalanceIn) {
        this.savingsStartingBalance = savingsStartingBalanceIn;
    }

    /**
     * This method gets the credit account number
     *
     * @return credit account number
     */
    public int getCreditAccountNumber() {
        return this.creditAccountNumber;
    }

    /**
     * This method sets credit account number
     *
     * @param creditAccountNumberIn Receives credit account number
     */
    public void setCreditAccountNumber(int creditAccountNumberIn) {
        this.creditAccountNumber = creditAccountNumberIn;
    }

    /**
     * This method gets the credit starting balance
     *
     * @return credit starting balance
     */
    public double getCreditStartingBalance() {
        return this.creditStartingBalance;
    }

    /**
     * This method sets credit starting balance
     *
     * @param creditStartingBalanceIn Receives credit starting balance
     */
    public void setCreditStartingBalance(double creditStartingBalanceIn) {
        this.creditStartingBalance = creditStartingBalanceIn;
    }

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
    public Customer(String firstNameIn, String lastNameIn, String dateOfBirthIn, int identificationNumberIn, String addressIn,
                    long phoneNumberIn, int checkingAccountNumberIn, int savingsAccountNumberIn, int creditAccountNumberIn,
                    double checkingStartingBalanceIn, double savingsStartingBalanceIn, double creditStartingBalanceIn) {
        super(firstNameIn, lastNameIn, dateOfBirthIn, identificationNumberIn, addressIn, phoneNumberIn);
        this.checkingAccountNumber = checkingAccountNumberIn;
        this.savingsAccountNumber = savingsAccountNumberIn;
        this.creditAccountNumber = creditAccountNumberIn;
        this.checkingStartingBalance = checkingStartingBalanceIn;
        this.savingsStartingBalance = savingsStartingBalanceIn;
        this.creditStartingBalance = creditStartingBalanceIn;
    }

    public ArrayList<Customer> getBankListArray() {

        // Initializing all index variables
        int firstNameIndex, lastNameIndex, dateOfBirthIndex, identificationNumberIndex, addressIndex,
        phoneNumberIndex, checkingAccountNumberIndex, savingsAccountNumberIndex, creditAccountNumberIndex,
        checkingStartingBalanceIndex, savingsStartingBalanceIndex, creditStatingBalanceIndex;

        firstNameIndex = lastNameIndex = dateOfBirthIndex = identificationNumberIndex = addressIndex =
        phoneNumberIndex = checkingAccountNumberIndex = savingsAccountNumberIndex = creditAccountNumberIndex =
        checkingStartingBalanceIndex = savingsStartingBalanceIndex = creditStatingBalanceIndex = 0;

        // reading csv file
        File bankUsers = new File("CS 3331 - Bank Users 2.csv");
        Scanner scanner = null;
        // try and catch to prevent file not found exception
        try {
            scanner = new Scanner(bankUsers);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        // asserting scanner. this was suggested by IntelliJ
        // it would give me an exception without it sometimes
        assert scanner != null;

        String header = scanner.nextLine();
        String[] headerArray = header.split(",");

        // creating array list
        ArrayList<Customer> bankList = new ArrayList<>();

        /* for loop will assign indices based on header to know correct place to grab information
           from the csv file */
        int j = 0;
        for (int i = 0; i < headerArray.length; i++) {

            if (headerArray[i].equals("First Name")) firstNameIndex = j;
            if (headerArray[i].equals("Last Name")) lastNameIndex = j;
            if (headerArray[i].equals("Date of Birth")) {
                dateOfBirthIndex = j;
                j += 1;
            }
            if (headerArray[i].equals("IdentificationNumber")) identificationNumberIndex = j;
            if (headerArray[i].equals("Address")) {
                addressIndex = j;
                j += 2;
            }
            if (headerArray[i].equals("Phone Number")) phoneNumberIndex = j;
            if (headerArray[i].equals("Checking Account Number")) checkingAccountNumberIndex = j;
            if (headerArray[i].equals("Savings Account Number")) savingsAccountNumberIndex = j;
            if (headerArray[i].equals("Credit Account Number")) creditAccountNumberIndex = j;
            if (headerArray[i].equals("Checking Starting Balance")) checkingStartingBalanceIndex = j;
            if (headerArray[i].equals("Savings Starting Balance")) savingsStartingBalanceIndex = j;
            if (headerArray[i].equals("Credit Starting Balance")) creditStatingBalanceIndex = j;
            j++;

        }

        // while loop that will read file line by line
        // will split info in between commas
        // loop will also add each information from line into the array as objects
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            String[] newLine = nextLine.split(",");

            String firstName = newLine[firstNameIndex];
            String lastName = newLine[lastNameIndex];
            String dateOfBirth = newLine[dateOfBirthIndex] + "," + newLine[dateOfBirthIndex + 1];
            String identificationNumberString = newLine[identificationNumberIndex];
            int identificationNumber = Integer.parseInt(identificationNumberString.replaceAll("[\\s\\-]", ""));
            String address = newLine[addressIndex] + "," + newLine[addressIndex + 1] + "," + newLine[addressIndex + 2];
            String phoneNumberString = newLine[phoneNumberIndex];
            long phoneNumber = Long.parseLong(phoneNumberString.replaceAll("\\D", ""));
            String checkingAccountNumberString = newLine[checkingAccountNumberIndex];
            int checkingAccountNumber = Integer.parseInt(checkingAccountNumberString);
            String savingsAccountNumberString = newLine[savingsAccountNumberIndex];
            int savingsAccountNumber = Integer.parseInt(savingsAccountNumberString);
            String creditAccountNumberString = newLine[creditAccountNumberIndex];
            int creditAccountNumber = Integer.parseInt(creditAccountNumberString);
            String checkingStartingBalanceString = newLine[checkingStartingBalanceIndex];
            double checkingStartingBalance = Double.parseDouble(checkingStartingBalanceString);
            String savingsStartingBalanceString = newLine[savingsStartingBalanceIndex];
            double savingsStartingBalance = Double.parseDouble(savingsStartingBalanceString);
            String creditStartingBalanceString = newLine[creditStatingBalanceIndex];
            double creditStartingBalance = Double.parseDouble(creditStartingBalanceString);

            Customer userInfo = new Customer(firstName, lastName, dateOfBirth, identificationNumber, address,
            phoneNumber, checkingAccountNumber, savingsAccountNumber, creditAccountNumber,
            checkingStartingBalance, savingsStartingBalance, creditStartingBalance);
            bankList.add(userInfo);
        }

        return bankList;
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
     * @param firstName Receives first name
     * @param lastName Receives last name
     * @return index from array list
     */
    public int searchAccount(ArrayList<Customer> customerArrayList, String firstName, String lastName) {

        int i;

        for (i = 0; i < customerArrayList.size(); i++) {
            if (firstName.equals(customerArrayList.get(i).getFirstName()) && lastName.equals(customerArrayList.get(i).getLastName())) {
                break;
            }
        }

        return i;
    }
}
