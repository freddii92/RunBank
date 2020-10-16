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
    private double checkingCurrentBalance;
    private int savingsAccountNumber;
    private double savingsStartingBalance;
    private double savingsCurrentBalance;
    private int creditAccountNumber;
    private double creditStartingBalance;
    private double creditCurrentBalance;
    private int creditMax;
    private final Checking userChecking = new Checking();

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
     * This method gets the starting balance
     *
     * @return checking starting balance
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
     * This method gets the checking current balance
     *
     * @return checking current balance
     */
    public double getCheckingCurrentBalance() {
        return this.checkingCurrentBalance;
    }

    /**
     * This method sets the checking current balance
     *
     * @param checkingCurrentBalanceIn Receives checking current balance
     */
    public void setCheckingCurrentBalance(double checkingCurrentBalanceIn) {
        this.checkingCurrentBalance = checkingCurrentBalanceIn;
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
     * This method sets the savings starting balance
     *
     * @param savingsStartingBalanceIn Receives savings starting balance
     */
    public void setSavingsStartingBalance(double savingsStartingBalanceIn) {
        this.savingsStartingBalance = savingsStartingBalanceIn;
    }

    /**
     * This method gets the savings current balance
     *
     * @return savings current balance
     */
    public double getSavingsCurrentBalance() {
        return this.savingsCurrentBalance;
    }

    /**
     * This method sets savings current balance
     *
     * @param savingsCurrentBalanceIn Receives savings current balance
     */
    public void setSavingsCurrentBalance(double savingsCurrentBalanceIn) {
        this.savingsCurrentBalance = savingsCurrentBalanceIn;
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
     * This method sets the credit starting balance
     *
     * @param creditStartingBalanceIn Receives credit starting balance
     */
    public void setCreditStartingBalance(double creditStartingBalanceIn) {
        this.creditStartingBalance = creditStartingBalanceIn;
    }

    /**
     * This method gets the credit current balance
     *
     * @return credit current balance
     */
    public double getCreditCurrentBalance() {
        return this.creditCurrentBalance;
    }

    /**
     * This method sets credit current balance
     *
     * @param creditCurrentBalanceIn Receives credit current balance
     */
    public void setCreditCurrentBalance(double creditCurrentBalanceIn) {
        this.creditCurrentBalance = creditCurrentBalanceIn;
    }

    /**
     * This method gets credit max
     *
     * @return credit max
     */
    public int getCreditMax() {
        return this.creditMax;
    }

    /**
     * This method sets credit max
     *
     * @param creditMaxIn Receives credit max
     */
    public void setCreditMax(int creditMaxIn) {
        this.creditMax = creditMaxIn;
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
                    double checkingStartingBalanceIn, double savingsStartingBalanceIn, double creditStartingBalanceIn, double checkingCurrentBalanceIn, double savingsCurrentBalanceIn, double creditCurrentBalanceIn, int creditMaxIn) {
        super(firstNameIn, lastNameIn, dateOfBirthIn, identificationNumberIn, addressIn, phoneNumberIn);
        this.checkingAccountNumber = checkingAccountNumberIn;
        this.savingsAccountNumber = savingsAccountNumberIn;
        this.creditAccountNumber = creditAccountNumberIn;
        this.checkingStartingBalance = checkingStartingBalanceIn;
        this.savingsStartingBalance = savingsStartingBalanceIn;
        this.creditStartingBalance = creditStartingBalanceIn;
        this.checkingCurrentBalance = checkingCurrentBalanceIn;
        this.savingsCurrentBalance = savingsCurrentBalanceIn;
        this.creditCurrentBalance = creditCurrentBalanceIn;
        this.creditMax = creditMaxIn;
    }

    public ArrayList<Customer> getBankListArray() {

        // Initializing all index variables
        int firstNameIndex, lastNameIndex, dateOfBirthIndex, identificationNumberIndex, addressIndex,
        phoneNumberIndex, checkingAccountNumberIndex, savingsAccountNumberIndex, creditAccountNumberIndex,
        checkingStartingBalanceIndex, savingsStartingBalanceIndex, creditStatingBalanceIndex, creditMaxIndex;

        firstNameIndex = lastNameIndex = dateOfBirthIndex = identificationNumberIndex = addressIndex =
        phoneNumberIndex = checkingAccountNumberIndex = savingsAccountNumberIndex = creditAccountNumberIndex =
        checkingStartingBalanceIndex = savingsStartingBalanceIndex = creditStatingBalanceIndex = creditMaxIndex = 0;

        // reading csv file
        File bankUsers = new File("CS 3331 - Bank Users 3.csv");
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
            if (headerArray[i].equals("Date of Birth")) dateOfBirthIndex = j;
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
            if (headerArray[i].equals("Credit Max")) creditMaxIndex = j;
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
            String dateOfBirth = newLine[dateOfBirthIndex];
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
            double checkingCurrentBalance = Double.parseDouble(checkingStartingBalanceString);
            String savingsStartingBalanceString = newLine[savingsStartingBalanceIndex];
            double savingsStartingBalance = Double.parseDouble(savingsStartingBalanceString);
            double savingsCurrentBalance = Double.parseDouble(savingsStartingBalanceString);
            String creditStartingBalanceString = newLine[creditStatingBalanceIndex];
            double creditStartingBalance = Double.parseDouble(creditStartingBalanceString);
            double creditCurrentBalance = Double.parseDouble(creditStartingBalanceString);
            String creditMaxString = newLine[creditMaxIndex];
            int creditMax = Integer.parseInt(creditMaxString);

            Customer userInfo = new Customer(firstName, lastName, dateOfBirth, identificationNumber, address,
            phoneNumber, checkingAccountNumber, savingsAccountNumber, creditAccountNumber,
            checkingStartingBalance, savingsStartingBalance, creditStartingBalance, checkingCurrentBalance, savingsCurrentBalance, creditCurrentBalance, creditMax);
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
                return i;
            }
        }

        System.out.println("Account not found. Returning to main menu.");
        return -1;
    }

    /**
     * This method creates a new account
     *
     * @param customerArrayList Receives array list
     */
    public void createAccount(ArrayList<Customer> customerArrayList) {

        Scanner userInput = new Scanner(System.in);
        Scanner addressScanner = new Scanner(System.in);
        Scanner dobScanner = new Scanner(System.in);

        System.out.println("Please include all fields");
        System.out.print("First name: ");
        String firstName = userInput.next();
        System.out.print("Last name: ");
        String lastName = userInput.next();
        System.out.print("Date of birth (Month Day, Year): ");
        String dateOfBirth = "\"" + dobScanner.nextLine() + "\"";
        System.out.print("Address: ");
        String address = "\"" + addressScanner.nextLine() + "\"";
        System.out.print("10-digit phone number: ");
        long phoneNumber = userInput.nextLong();
        System.out.print("Savings account number: ");
        int savingsAccountNumber = userInput.nextInt();
        System.out.print("Amount to deposit into savings: ");
        double savingsStartingBalance = userInput.nextDouble();
        double savingsCurrentBalance = savingsStartingBalance;
        int identificationNumber = customerArrayList.size() + 1;

        System.out.println("Would you like to create a Checking account? (y/n)");
        String createAccount = userInput.next().toLowerCase();

        int checkingAccountNumber = 0;
        double checkingStartingBalance = 0;
        double checkingCurrentBalance = checkingStartingBalance;

        if (createAccount.equals("y")) {
            System.out.print("Enter checking account number: ");
            checkingAccountNumber = userInput.nextInt();
            System.out.print("Enter amount to deposit into checking: ");
            checkingStartingBalance = userInput.nextDouble();
            checkingCurrentBalance = checkingStartingBalance;
        }

        System.out.println("Would you like to create a Credit account? (y/n)");
        createAccount = userInput.next().toLowerCase();

        int creditAccountNumber = 0;
        double creditStartingBalance = 0;
        double creditCurrentBalance = 0;
        int creditMax = 5000;

        if (createAccount.equals("y")) {
            System.out.print("Enter credit account number: ");
            creditAccountNumber = userInput.nextInt();
            System.out.print("Congratulations! You were approved for $5000");
            creditStartingBalance = 0;
        }

        Customer customerInfo = new Customer(firstName, lastName, dateOfBirth, identificationNumber, address, phoneNumber, checkingAccountNumber, savingsAccountNumber, creditAccountNumber, checkingStartingBalance, savingsStartingBalance, creditStartingBalance, checkingCurrentBalance, savingsCurrentBalance, creditCurrentBalance, creditMax);

        customerArrayList.add(customerInfo);

        System.out.println("Redirecting you to main menu...");
    }
}
