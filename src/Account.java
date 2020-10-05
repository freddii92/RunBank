import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
     * @return
     */
    public double inquireBalance(ArrayList<Account> accountList, int i) {
        return accountList.get(i).getStartingBalance();
    }

    /**
     * This method is to search the index of the ArrayList based on the account number given.
     *
     * @param accountList Receives the Array List of the given account
     * @param accountNumber Received the account number
     * @return array list index
     */
    public int searchAccount(ArrayList<Account> accountList, int accountNumber) {

        int i = 0;

        for (i = 0; i < accountList.size(); i++) {
            if (accountNumber == accountList.get(i).getAccountNumber()) {
                break;
            }
        }

        return i;
    }

    /**
     * This method is used to deposit money into the account given.
     *
     * @param customerArrayList Receives the customer array list.
     * @param accountList Receives the account array list.
     * @param i Receives the index from the array lists.
     * @param depositAmount Receives amount
     * @param account Receives the type of account.
     * @return String of either success or failure
     */
    public String deposit(ArrayList<Customer> customerArrayList, ArrayList<Account> accountList, int i, double depositAmount, String account) {

        Scanner userInput = new Scanner(System.in);

        // asking user for amount to be deposited
        //System.out.print("Please enter amount: ");
        //double depositAmount = userInput.nextDouble();

        if (account.equals("credit") && (depositAmount + accountList.get(i).getStartingBalance() > 0)) {
            return "Unable to deposit more than your current balance of $" + accountList.get(i).getStartingBalance() + "\n" + "Returning to main menu.";
        }

        // if deposit amount is negative, it will print an error exit out of method
        if (depositAmount < 0) {
            return "Incorrect value. Returning to main menu";
        }

        // adding deposited amount to his balance
        double newBalance = accountList.get(i).getStartingBalance() + depositAmount;
        accountList.get(i).setStartingBalance(newBalance);

        // adding transaction to the transaction log text file by calling the transactionLog method
        String userFullName = customerArrayList.get(i).getFirstName() + " " + customerArrayList.get(i).getLastName();
        transactionLog("deposit", userFullName, "", depositAmount, account, "");

        // success message
        return "Deposit of $" + depositAmount + " successful.";
    }

    /**
     * This method is used to withdraw money from the account give.
     *
     * @param customerArrayList Receives customer array list.
     * @param accountList Receives account array list.
     * @param i Receives index from array lists.
     * @param account Receives the type of account.
     */
    public void withdraw(ArrayList<Customer> customerArrayList, ArrayList<Account> accountList, int i, String account) {

        Scanner userInput = new Scanner(System.in);

        // asking withdraw amount
        System.out.print("Please enter amount: ");
        double withdrawAmount = userInput.nextDouble();

        // if withdraw amount is negative, error will print out and user will be
        // returned to main menu
        if (withdrawAmount < 0) {
            System.out.println("Incorrect value. Returning to main menu.");
            return;
        }

        // if withdraw amount is more than what the user has, an error
        // will be printed and user will be returned to main menu
        if (accountList.get(i).getStartingBalance() - withdrawAmount < 0) {
            System.out.println("Insufficient funds. Returning to main menu.");
            return;
        }

        // subtracting withdraw amount from user's balance
        double newAccountBalance = accountList.get(i).getStartingBalance() - withdrawAmount;
        accountList.get(i).setStartingBalance(newAccountBalance);

        // printing success including new balance
        System.out.println("Withdraw of $" + withdrawAmount + " successful.");

        // adding transaction to the transaction log text file by calling the transactionLog method
        String userFullName = customerArrayList.get(i).getFirstName() + " " + customerArrayList.get(i).getLastName();
        transactionLog("withdraw", userFullName, "", withdrawAmount, account, "");
    }

    /**
     * This method is used to transfer money from a given account to a given account.
     *
     * @param customerArrayList Receives customer array list.
     * @param fromAccountList Receives from account.
     * @param toAccountList Receives to account.
     * @param i Receives index from array list.
     * @param transferInput Receives the input of either Checking or Customer from the user.
     * @param transferAmount Receives amount
     * @return String of either success or failure
     */
    public String transfer(ArrayList<Customer> customerArrayList, ArrayList<Account> fromAccountList, ArrayList<Account> toAccountList, int i, int transferInput, double transferAmount) {

        Scanner userInput = new Scanner(System.in);
        String fromAccount;
        String toAccount;

        if (transferInput == 1) {
            System.out.println("You selected Checking.");
            System.out.println("Money will be transferred to Savings account.");
            fromAccount = "checking";
            toAccount = "savings";
        }
        else {
            System.out.println("You selected Savings.");
            System.out.println("Money will be transferred to Checking account.");
            fromAccount = "savings";
            toAccount = "checking";
        }

        if (transferAmount < 0) {
            return "Incorrect value. Returning to main menu.";
        }

        if (fromAccountList.get(i).getStartingBalance() - transferAmount < 0) {
            return "Insufficient funds. Returning to main menu.";
        }

        fromAccountList.get(i).setStartingBalance(fromAccountList.get(i).getStartingBalance() - transferAmount);
        toAccountList.get(i).setStartingBalance(toAccountList.get(i).getStartingBalance() + transferAmount);

        // adding transaction to the transaction log text file by calling the transactionLog method
        String userFullName = customerArrayList.get(i).getFirstName() + " " + customerArrayList.get(i).getLastName();
        transactionLog("transfer", userFullName, "", transferAmount, fromAccount, toAccount);

        return "Transfer of $" + transferAmount + " successful.";

    }

    /**
     * This method is used to transfer money in between users.
     *
     * @param customerArrayList Receives customer array list.
     * @param fromAccountList Receives from account.
     * @param toAccountList Receives to account.
     * @param i Receives index from the current user from the array list.
     * @param j Receives index from the other user from the array list.
     */
    public void paySomeone(ArrayList<Customer> customerArrayList, ArrayList<Account> fromAccountList, ArrayList<Account> toAccountList, int i, int j) {

        Scanner userInput = new Scanner(System.in);

        // asking user for transfer amount
        System.out.print("Please enter amount: ");
        double paymentAmount = userInput.nextDouble();

        // if amount is is negative, an error will be printed and user will return to main menu
        if (paymentAmount < 0) {
            System.out.println("Incorrect value. Returning to main menu");
            return;
        }

        // if transfer amount is more than what the user has, it will print an error
        // and return user to the main menu
        if (fromAccountList.get(i).getStartingBalance() - paymentAmount < 0) {
            System.out.println("Insufficient funds. Returning to main menu.");
            return;
        }

        // subtracting transfer amount from current user's balance
        fromAccountList.get(i).setStartingBalance(fromAccountList.get(i).getStartingBalance() - paymentAmount);

        // adding transfer amount to other user's balance
        toAccountList.get(j).setStartingBalance(toAccountList.get(j).getStartingBalance() + paymentAmount);

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
                transLogWriter.write(userName + " sent $" + amount + " to " + otherUserName + ".\n");
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
     * @param checkingList Receives checking array list.
     * @param savingsList Receives savings array list.
     * @param creditList Receives credit array list.
     */
    public void newBalanceSheet(ArrayList<Customer> customerArrayList, ArrayList<Account> checkingList, ArrayList<Account> savingsList, ArrayList<Account> creditList) {

        try (PrintWriter writer = new PrintWriter("CS 3331 - Bank Users 2.csv")) {
            writer.println("First Name,Last Name,Date of Birth,IdentificationNumber,Address,Phone Number,Checking Account Number,Savings Account Number,Credit Account Number,Checking Starting Balance,Savings Starting Balance,Credit Starting Balance");
            for (int i = 0; i < customerArrayList.size(); i++) {
                writer.print(customerArrayList.get(i).getFirstName() + ",");
                writer.print(customerArrayList.get(i).getLastName() + ",");
                writer.print(customerArrayList.get(i).getDateOfBirth() + ",");
                writer.print(customerArrayList.get(i).getIdentificationNumber() + ",");
                writer.print(customerArrayList.get(i).getAddress() + ",");
                writer.print(customerArrayList.get(i).getPhoneNumber() + ",");
                writer.print(checkingList.get(i).getAccountNumber() + ",");
                writer.print(savingsList.get(i).getAccountNumber() + ",");
                writer.print(creditList.get(i).getAccountNumber() + ",");
                writer.print(checkingList.get(i).getStartingBalance() + ",");
                writer.print(savingsList.get(i).getStartingBalance() + ",");
                writer.println(creditList.get(i).getStartingBalance());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error");
        }

    }
}
