import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Account class is an abstract class that is the parent of Checking, Savings and Credit classes.
 * It includes getters and setters as well as multiple methods that will work with the main RunBank class and its child classes.
 *
 * @author Alfredo Rodriguez
 * @version 1.0, 09/25/2020
 * @since September 25, 2020
 *
 */
public abstract class Account {

    // initializing attributes
    private int accountNumber;
    private double startingBalance;

    /**
     * This is the default constructor
     */
    public Account() {
    }

    /**
     * Constructor for Account
     *
     * @param accountNumberIn Either Checking, Savings or Credit account number.
     * @param startingBalanceIn Either Checking, Savings or Credit starting balance.
     */
    public Account(int accountNumberIn, double startingBalanceIn) {
        this.accountNumber = accountNumberIn;
        this.startingBalance = startingBalanceIn;
    }

    /**
     * This method gets the account number.
     *
     * @return account number
     */
    public int getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * This method sets the account number.
     *
     * @param accountNumberIn Received account number.
     */
    public void setAccountNumber(int accountNumberIn) {
        this.accountNumber = accountNumberIn;
    }

    /**
     * This method gets the balance of the account.
     *
     * @return balance
     */
    public double getStartingBalance() {
        return this.startingBalance;
    }

    /**
     * This method sets the balance.
     *
     * @param startingBalanceIn Receives balance.
     */
    public void setStartingBalance(double startingBalanceIn) {
        this.startingBalance = startingBalanceIn;
    }

    /**
     * This method is used to inquire the balance of the account received.
     *
     * @param accountList Receives account array list.
     * @param i Receives array list index
     */
    public void inquireBalance(ArrayList<Customer> accountList, int i) {
        System.out.println("Checking $: " + accountList.get(i).getCheckingStartingBalance());
        System.out.println("Savings  $: " + accountList.get(i).getSavingsStartingBalance());
        System.out.println("Credit   $:" + accountList.get(i).getCreditStartingBalance());
    }

    /**
     * This method is to search the index of the ArrayList based on the account number given.
     *
     * @param customerArrayList Receives the Array List of the given account
     * @param accountNumber Received the account number
     * @return array list index
     */
    public int searchAccount(ArrayList<Customer> customerArrayList, int accountNumber) {

        int i;

        for (i = 0; i < customerArrayList.size(); i++) {
            if (accountNumber == customerArrayList.get(i).getCheckingAccountNumber() || accountNumber == customerArrayList.get(i).getSavingsAccountNumber() || accountNumber == customerArrayList.get(i).getCreditAccountNumber()) {
                break;
            }
        }

        return i;
    }

    /**
     * This method is used to deposit money into the account given.
     *
     * @param customerArrayList Receives the customer array list.
     * @param i Receives the index from the array lists.
     * @param depositAmount Receives amount
     * @param accountType Receives the type of account.
     * @return String of either success or failure
     */
    public String deposit(ArrayList<Customer> customerArrayList, int i, double depositAmount, String accountType) {

        Scanner userInput = new Scanner(System.in);

        if (accountType.equals("credit") && (depositAmount + customerArrayList.get(i).getCreditStartingBalance() > 0)) {
            return "Unable to deposit more than your current balance of $" + customerArrayList.get(i).getCreditStartingBalance() + "\n" + "Returning to main menu.";
        }

        // if deposit amount is negative, it will print an error exit out of method
        if (depositAmount < 0) {
            return "Incorrect value. Returning to main menu";
        }

        // adding deposited amount to his balance
        if (accountType.equals("checking")) {
            double newBalance = customerArrayList.get(i).getCheckingStartingBalance() + depositAmount;
            customerArrayList.get(i).setCheckingStartingBalance(newBalance);
        }
        if (accountType.equals("savings")) {
            double newBalance = customerArrayList.get(i).getSavingsStartingBalance() + depositAmount;
            customerArrayList.get(i).setSavingsStartingBalance(newBalance);
        }
        if (accountType.equals("credit")) {
            double newBalance = customerArrayList.get(i).getCreditStartingBalance() + depositAmount;
            customerArrayList.get(i).setCreditStartingBalance(newBalance);
        }

        // adding transaction to the transaction log text file by calling the transactionLog method
        String userFullName = customerArrayList.get(i).getFirstName() + " " + customerArrayList.get(i).getLastName();
        transactionLog("deposit", userFullName, "", depositAmount, accountType, "");

        // success message
        return "Deposit of $" + depositAmount + " successful.";
    }

    /**
     * This method is used to withdraw money from the account give.
     *
     * @param customerArrayList Receives customer array list.
     * @param i Receives index from array lists.
     * @param accountType Receives the type of account.
     */
    public void withdraw(ArrayList<Customer> customerArrayList, int i, String accountType, double amount) {

        Scanner userInput = new Scanner(System.in);

        // asking withdraw amount
        double withdrawAmount;

        if (amount == 0) {
            System.out.print("Please enter amount: ");
            withdrawAmount = userInput.nextDouble();
        }
        else {
            withdrawAmount = amount;
        }

        // if withdraw amount is negative, error will print out and user will be
        // returned to main menu
        if (withdrawAmount < 0) {
            System.out.println("Incorrect value. Returning to main menu.");
            return;
        }

        // if withdraw amount is more than what the user has, an error
        // will be printed and user will be returned to main menu
        if (accountType.equals("checking") && customerArrayList.get(i).getCheckingStartingBalance() - withdrawAmount < 0) {
            System.out.println("Insufficient funds in checking account. Returning to main menu.");
            return;
        }
        if (accountType.equals("savings") && customerArrayList.get(i).getSavingsStartingBalance() - withdrawAmount < 0) {
            System.out.println("Insufficient funds in savings account. Returning to main menu.");
            return;
        }

        // subtracting withdraw amount from user's balance
        if (accountType.equals("checking")) {
            double newAccountBalance = customerArrayList.get(i).getCheckingStartingBalance() - withdrawAmount;
            customerArrayList.get(i).setCheckingStartingBalance(newAccountBalance);
        }
        if (accountType.equals("savings")) {
            double newAccountBalance = customerArrayList.get(i).getSavingsStartingBalance() - withdrawAmount;
            customerArrayList.get(i).setSavingsStartingBalance(newAccountBalance);
        }

        // printing success including new balance
        System.out.println("Withdraw of $" + withdrawAmount + " successful.");

        // adding transaction to the transaction log text file by calling the transactionLog method
        String userFullName = customerArrayList.get(i).getFirstName() + " " + customerArrayList.get(i).getLastName();
        transactionLog("withdraw", userFullName, "", withdrawAmount, accountType, "");
    }

    /**
     * This method is used to transfer money from a given account to a given account.
     *
     * @param customerArrayList Receives customer array list.
     * @param fromAccount Receives from account.
     * @param toAccount Receives to account.
     * @param i Receives index from array list.
     * @param transferAmount Receives amount
     * @return String of either success or failure
     */
    public String transfer(ArrayList<Customer> customerArrayList, String fromAccount, String toAccount, int i, double transferAmount) {

        Scanner userInput = new Scanner(System.in);

        if (fromAccount.equals("checking")) {
            System.out.println("You selected Checking.");
            System.out.println("Money will be transferred to Savings account.");
        }
        else {
            System.out.println("You selected Savings.");
            System.out.println("Money will be transferred to Checking account.");
        }

        if (transferAmount < 0) {
            return "Incorrect value. Returning to main menu.";
        }

        if (fromAccount.equals("checking") && customerArrayList.get(i).getCheckingStartingBalance() - transferAmount < 0) {
            return "Insufficient funds. Returning to main menu.";
        }
        if (fromAccount.equals("savings") && customerArrayList.get(i).getSavingsStartingBalance() - transferAmount < 0) {
            return "Insufficient funds. Returning to main menu.";
        }

        if (fromAccount.equals("checking")) {
            customerArrayList.get(i).setCheckingStartingBalance(customerArrayList.get(i).getCheckingStartingBalance() - transferAmount);
            customerArrayList.get(i).setSavingsStartingBalance(customerArrayList.get(i).getSavingsStartingBalance() + transferAmount);
        }
        if (fromAccount.equals("savings")) {
            customerArrayList.get(i).setSavingsStartingBalance(customerArrayList.get(i).getSavingsStartingBalance() - transferAmount);
            customerArrayList.get(i).setCheckingStartingBalance(customerArrayList.get(i).getCheckingStartingBalance() + transferAmount);
        }

        // adding transaction to the transaction log text file by calling the transactionLog method
        String userFullName = customerArrayList.get(i).getFirstName() + " " + customerArrayList.get(i).getLastName();
        transactionLog("transfer", userFullName, "", transferAmount, fromAccount, toAccount);

        return "Transfer of $" + transferAmount + " successful.";

    }

    /**
     * This method is used to transfer money in between users.
     *
     * @param customerArrayList Receives customer array list.
     * @param fromAccount Receives from account.
     * @param toAccount Receives to account.
     * @param i Receives index from the current user from the array list.
     * @param j Receives index from the other user from the array list.
     */
    public void paySomeone(ArrayList<Customer> customerArrayList, String fromAccount, String toAccount, int i, int j, double amount) {

        Scanner userInput = new Scanner(System.in);

        // asking user for transfer amount
        double paymentAmount;
        if (amount == 0) {
            System.out.print("Please enter amount: ");
            paymentAmount = userInput.nextDouble();
        }
        else {
            paymentAmount = amount;
        }

        // if amount is is negative, an error will be printed and user will return to main menu
        if (paymentAmount < 0) {
            System.out.println("Incorrect value. Returning to main menu");
            return;
        }

        // if transfer amount is more than what the user has, it will print an error
        // and return user to the main menu
        if (fromAccount.equals("checking") && customerArrayList.get(i).getCheckingStartingBalance() - paymentAmount < 0) {
            System.out.println("Insufficient funds in checking account. Returning to main menu.");
            return;
        }
        if (fromAccount.equals("savings") && customerArrayList.get(i).getSavingsStartingBalance() - paymentAmount < 0) {
            System.out.println("Insufficient funds in savings account. Returning to main menu.");
            return;
        }

        // subtracting transfer amount from current user's balance
        if (fromAccount.equals("checking")) {
            customerArrayList.get(i).setCheckingStartingBalance(customerArrayList.get(i).getCheckingStartingBalance() - paymentAmount);
        }
        if (fromAccount.equals("savings")) {
            customerArrayList.get(i).setSavingsStartingBalance(customerArrayList.get(i).getSavingsStartingBalance() - paymentAmount);
        }

        // adding transfer amount to other user's balance
        if (toAccount.equals("checking")) {
            customerArrayList.get(j).setCheckingStartingBalance(customerArrayList.get(j).getCheckingStartingBalance() + paymentAmount);
        }
        if (toAccount.equals("savings")) {
            customerArrayList.get(j).setSavingsStartingBalance(customerArrayList.get(j).getSavingsStartingBalance() + paymentAmount);
        }

        // success message including new balance
        System.out.println("Your payment of $" + paymentAmount + " to " + customerArrayList.get(j).getFirstName() + " was successful.");

        // adding transaction to the transaction log text file by calling the transactionLog method
        String userFullName = customerArrayList.get(i).getFirstName() + " " + customerArrayList.get(i).getLastName();
        String otherUserFullName = customerArrayList.get(j).getFirstName() + " " + customerArrayList.get(j).getLastName();
        transactionLog("paySomeone", userFullName, otherUserFullName, paymentAmount, "", "");
    }

    /**
     * This method is used to keep track of the transactions on a txt file called transactionLog.txt
     *
     * @param transactionType Receives transaction type.
     * @param userName Receives user's full name.
     * @param otherUserName Receives the other user's full name (in case of transferring money to another user)
     * @param amount Receives amount from transaction.
     * @param fromAccount Receives from account.
     * @param toAccount Receives to account.
     */
    public void transactionLog(String transactionType, String userName, String otherUserName, double amount, String fromAccount, String toAccount) {

        // using try and catch to prevent IOException error
        // file will be created if it does not exist, otherwise it will append information
        // adding if statements that will write different info depending on transaction types
        try {
            FileWriter transLogWriter = new FileWriter("transactionLog.txt", true);
            if (transactionType.equals("paySomeone")) {
                transLogWriter.write(userName + " payed $" + amount + " to " + otherUserName + ".\n");
            }
            if (transactionType.equals("deposit")) {
                transLogWriter.write(userName + " deposited $" + amount + " into their " + fromAccount + " account.\n");
            }
            if (transactionType.equals("transfer")) {
                transLogWriter.write(userName + " transferred $" + amount + " from their " + fromAccount + " account to their " + toAccount + ".\n");
            }
            if (transactionType.equals("withdraw")) {
                transLogWriter.write(userName + " withdrew $" + amount + " from their " + fromAccount + " account.\n");
            }
            transLogWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error");
        }
    }

    /**
     * This method is used to keep track of the new balances on a csv file called newBalanceSheet.csv
     *
     * @param customerArrayList Receives customer array list.
     */
    public void newBalanceSheet(ArrayList<Customer> customerArrayList) {

        try (PrintWriter writer = new PrintWriter("New Balance Sheet.csv")) {
            writer.println("First Name,Last Name,Date of Birth,IdentificationNumber,Address,Phone Number,Checking Account Number,Savings Account Number,Credit Account Number,Checking Starting Balance,Savings Starting Balance,Credit Starting Balance,Credit Max");
            for (int i = 0; i < customerArrayList.size(); i++) {
                writer.print(customerArrayList.get(i).getFirstName() + ",");
                writer.print(customerArrayList.get(i).getLastName() + ",");
                writer.print(customerArrayList.get(i).getDateOfBirth() + ",");
                writer.print(customerArrayList.get(i).getIdentificationNumber() + ",");
                writer.print(customerArrayList.get(i).getAddress() + ",");
                writer.print(customerArrayList.get(i).getPhoneNumber() + ",");
                writer.print(customerArrayList.get(i).getCheckingAccountNumber() + ",");
                writer.print(customerArrayList.get(i).getSavingsAccountNumber() + ",");
                writer.print(customerArrayList.get(i).getCreditAccountNumber() + ",");
                writer.print(customerArrayList.get(i).getCheckingStartingBalance() + ",");
                writer.print(customerArrayList.get(i).getSavingsStartingBalance() + ",");
                writer.print(customerArrayList.get(i).getCreditStartingBalance() + ",");
                writer.println(customerArrayList.get(i).getCreditMax());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error");
        }

    }

    /**
     * This method reads a transaction action file and performs given operations
     *
     * @param fileName receives file name
     */
    public void transactionReader(ArrayList<Customer> customerArrayList, String fileName) {

        int fromFirstNameIndex, fromLastNameIndex, fromWhereIndex, actionIndex, toFirstNameIndex, toLastNameIndex,
                toWhereIndex, actionAmountIndex;
        fromFirstNameIndex = fromLastNameIndex = fromWhereIndex = actionIndex = toFirstNameIndex = toLastNameIndex =
                toWhereIndex = actionAmountIndex = 0;

        // reading csv file
        File transactionActions = new File(fileName);
        Scanner scanner = null;
        Scanner scannerCopy = null;
        // try and catch to prevent file not found exception
        try {
            scanner = new Scanner(transactionActions);
            scannerCopy = new Scanner(transactionActions);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        // asserting scanner. this was suggested by IntelliJ
        // it would give me an exception without it sometimes
        assert scanner != null;
        assert scannerCopy != null;

        String header = scanner.nextLine();
        String[] headerArray = header.split(",");

        int i;
        for (i = 0; i < headerArray.length; i++) {

            if (headerArray[i].equals("From First Name")) fromFirstNameIndex = i;
            if (headerArray[i].equals("From Last Name")) fromLastNameIndex = i;
            if (headerArray[i].equals("From Where")) fromWhereIndex = i;
            if (headerArray[i].equals("Action")) actionIndex = i;
            if (headerArray[i].equals("To First Name")) toFirstNameIndex = i;
            if (headerArray[i].equals("To Last Name")) toLastNameIndex = i;
            if (headerArray[i].equals("To Where")) toWhereIndex = i;
            if (headerArray[i].equals("Action Amount")) actionAmountIndex = i;

        }

//        scannerCopy.nextLine();
//
//        int count = 0;
//        while (scanner.hasNextLine()) {
//            scanner.nextLine();
//            count++;
//        }
//
//        String[][] newArray = new String[count][];
//
//        int column = 0;
//        for (int row = 0; scannerCopy.hasNextLine() && row < count; row++) {
//            newArray[row][0] = scannerCopy.nextLine();
//        }

//        for (int k = 0; k < newArray.length; k++) {
//            System.out.println(Arrays.toString(newArray[k]));
//        }

        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            String[] newLine = nextLine.split(",");

            String fromFirstName = null;
            String fromLastName = null;
            String fromWhere = null;
            String toFirstName = null;
            String toLastName = null;
            String toWhere = null;
            double actionAmount = 0;

            String action = newLine[actionIndex];
            if (!action.equals("deposits")) {
                fromFirstName = newLine[fromFirstNameIndex];
                fromLastName = newLine[fromLastNameIndex];
                fromWhere = newLine[fromWhereIndex];
            }
            if (!action.equals("inquires")) {
                if (!action.equals("withdraws")) {
                    toFirstName = newLine[toFirstNameIndex];
                    toLastName = newLine[toLastNameIndex];
                    toWhere = newLine[toWhereIndex];
                }
                String actionAmountString = newLine[actionAmountIndex];
                actionAmount = Double.parseDouble(actionAmountString);
            }

            Customer fromUser = new Customer();
            Customer toUser = new Customer();
            Checking checkingObject = new Checking();

            int fromUserIndex = 0;
            int toUserIndex = 0;

            if (fromFirstName != null) {
                fromUserIndex = fromUser.searchAccount(customerArrayList, fromFirstName, fromLastName);
            }
            if (toFirstName != null) {
                toUserIndex = toUser.searchAccount(customerArrayList, toFirstName, toLastName);
            }

            if (action.equals("pays")) {
                paySomeone(customerArrayList, fromWhere, toWhere, fromUserIndex, toUserIndex, actionAmount);
            }
            if (action.equals("transfers")) {
                transfer(customerArrayList, fromWhere, toWhere, fromUserIndex, actionAmount);
            }
            if (action.equals("inquires")) {
                checkingObject.inquireBalance(customerArrayList, fromUserIndex, fromWhere);
            }
            if (action.equals("withdraws")) {
                withdraw(customerArrayList, fromUserIndex, fromWhere, actionAmount);
            }
            if (action.equals("deposits")) {
                deposit(customerArrayList, fromUserIndex, actionAmount, toWhere);
            }

        }
    }
}
