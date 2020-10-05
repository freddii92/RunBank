import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the RunBank method where main method is located
 *
 * @author Alfredo Rodriguez
 * @version 1.0,  09/25/2020
 * @since September 25, 2020
 */
public class RunBank {
    public static void main(String[] args) {

        // initializing all variables used
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        ArrayList<Account> checkingArrayList = new ArrayList<>();
        ArrayList<Account> savingsArrayList = new ArrayList<>();
        ArrayList<Account> creditArrayList = new ArrayList<>();
        Scanner scanner = null;
        Scanner userInput = new Scanner(System.in);
        int userID = 0;
        int openingOption = 0;
        Customer user;
        Account userChecking;
        Account userSavings;
        Account userCredit;
        Checking managerChecking;
        Savings managerSavings;
        Credit managerCredit;
        Customer managerCustomerObject = new Customer();
        Checking managerCheckingObject = new Checking();
        Savings managerSavingsObject = new Savings();
        Credit managerCreditObject = new Credit();
        boolean exit = false;

        // reading csv file
        File bankInfo = new File("CS 3331 - Bank Users 2.csv");

        // try and catch to prevent file not found exception
        try {
            scanner = new Scanner(bankInfo);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        // skipping first line from file
        assert scanner != null;
        scanner.nextLine();

        // while loop that will read file line by line
        // will split info in between commas
        // loop will also add each information from line into the array as objects
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            String[] newLine = nextLine.split(",");

            String firstName = newLine[0];
            String lastName = newLine[1];
            String dateOfBirth = newLine[2] + "," + newLine[3];
            String identificationNumberString = newLine[4];
            int identificationNumber = Integer.parseInt(identificationNumberString.replaceAll("[\\s\\-]", ""));
            String address = newLine[5] + "," + newLine[6] + "," + newLine[7];
            String phoneNumberString = newLine[8];
            long phoneNumber = Long.parseLong(phoneNumberString.replaceAll("\\D", ""));
            String checkingAccountNumberString = newLine[9];
            int checkingAccountNumber = Integer.parseInt(checkingAccountNumberString);
            String savingsAccountNumberString = newLine[10];
            int savingsAccountNumber = Integer.parseInt(savingsAccountNumberString);
            String creditAccountNumberString = newLine[11];
            int creditAccountNumber = Integer.parseInt(creditAccountNumberString);
            String checkingStartingBalanceString = newLine[12];
            double checkingStartingBalance = Double.parseDouble(checkingStartingBalanceString);
            String savingsStartingBalanceString = newLine[13];
            double savingsStartingBalance = Double.parseDouble(savingsStartingBalanceString);
            String creditStartingBalanceString = newLine[14];
            double creditStartingBalance = Double.parseDouble(creditStartingBalanceString);

            Customer customerInfo = new Customer(firstName, lastName, dateOfBirth, identificationNumber, address, phoneNumber);
            Checking checkingInfo = new Checking(identificationNumber, checkingAccountNumber, checkingStartingBalance);
            Savings savingsInfo = new Savings(identificationNumber, savingsAccountNumber, savingsStartingBalance);
            Credit creditInfo = new Credit(identificationNumber, creditAccountNumber, creditStartingBalance);

            customerArrayList.add(customerInfo);
            checkingArrayList.add(checkingInfo);
            savingsArrayList.add(savingsInfo);
            creditArrayList.add(creditInfo);
        }

        // greeting customer and asking if they are a customer or bank manager
        System.out.println("Welcome!");
        System.out.println("Are you a customer or bank manager?");
        System.out.println("1. Customer");
        System.out.println("2. Bank Manager");
        openingOption = userInput.nextInt();

        // if statement for individual
        if (openingOption == 1) {
            System.out.print("Please enter your identification number: ");
            userID = userInput.nextInt();

            int i = 0;

            // while loop to iterate through the array list that will assign the correct account to userAccount
            // if account is not found, it will ask the user to try again
            // infinite loop will only exit once account is found
            while (true) {
                if (userID == customerArrayList.get(i).getIdentificationNumber()) {
                    user = customerArrayList.get(i);
                    userChecking = checkingArrayList.get(i);
                    userSavings = savingsArrayList.get(i);
                    userCredit = creditArrayList.get(i);
                    break;
                }
                i++;
                if (i > customerArrayList.size() - 1) {
                    i = 0;
                    System.out.print("Identification number not found in database. Please try again: ");
                    userID = userInput.nextInt();
                }
            }

            System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + "!");

            // main menu when account is found
            try {
                while (!exit) {
                    System.out.println("1. Inquire Balance");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Transfer");
                    System.out.println("5. Pay someone");
                    System.out.println("6. Exit");
                    int mainInput = userInput.nextInt();

                    if (mainInput == 1) {
                        System.out.println("Account Summary");
                        System.out.println("Checking: $" + userChecking.getStartingBalance());
                        System.out.println("Savings:  $" + userSavings.getStartingBalance());
                        System.out.println("Credit:   $" + userCredit.getStartingBalance());
                    }
                    if (mainInput == 2) {
                        System.out.println("Please select account.");
                        System.out.println("1. Checking");
                        System.out.println("2. Savings");
                        System.out.println("3. Credit");
                        int depositInput = userInput.nextInt();
                        if (depositInput == 1) {
                            System.out.print("Please enter amount: ");
                            double depositAmount = userInput.nextDouble();
                            System.out.println(userChecking.deposit(customerArrayList, checkingArrayList, i, depositAmount, "checking"));
                        }
                        if (depositInput == 2) {
                            System.out.print("Please enter amount: ");
                            double depositAmount = userInput.nextDouble();
                            System.out.println(userSavings.deposit(customerArrayList, savingsArrayList, i, depositAmount, "savings"));
                        }
                        if (depositInput == 3) {
                            System.out.print("Please enter amount: ");
                            double depositAmount = userInput.nextDouble();
                            System.out.println(userCredit.deposit(customerArrayList, creditArrayList, i, depositAmount, "credit"));
                        }
                    }
                    if (mainInput == 3) {
                        System.out.println("Please select account.");
                        System.out.println("1. Checking");
                        System.out.println("2. Savings");
                        int withdrawInput = userInput.nextInt();
                        if (withdrawInput == 1) {
                            userChecking.withdraw(customerArrayList, checkingArrayList, i, "checking");
                        }
                        if (withdrawInput == 2) {
                            userSavings.withdraw(customerArrayList, savingsArrayList, i, "savings");
                        }
                    }
                    if (mainInput == 4) {
                        System.out.println("Select account would you like to transfer from.");
                        System.out.println("1. Checking");
                        System.out.println("2. Savings");
                        int transferInput = userInput.nextInt();
                        if (transferInput == 1) {
                            System.out.print("Please select amount: ");
                            double transferAmount = userInput.nextDouble();
                            System.out.println(userChecking.transfer(customerArrayList, checkingArrayList, savingsArrayList, i, transferInput, transferAmount));
                        }
                        if (transferInput == 2) {
                            System.out.print("Please select amount: ");
                            double transferAmount = userInput.nextDouble();
                            System.out.println(userChecking.transfer(customerArrayList, savingsArrayList, checkingArrayList, i, transferInput, transferAmount));
                        }
                    }
                    if (mainInput == 5) {
                        System.out.println("Please select account.");
                        System.out.println("1. Checking");
                        System.out.println("2. Savings");
                        int paySomeoneInput = userInput.nextInt();
                        System.out.print("Please enter the identification number of the person you're paying: ");
                        int otherIdentificationNumber = userInput.nextInt();
                        int otherAccountIndex = user.searchAccount(customerArrayList, otherIdentificationNumber);
                        if (i == otherAccountIndex) {
                            System.out.println("You cannot pay to yourself. Returning to main menu.");
                            continue;
                        }
                        System.out.println("Please select the other person's account.");
                        System.out.println("1. Checking");
                        System.out.println("2. Savings");
                        int otherAccountInput = userInput.nextInt();
                        if (paySomeoneInput == 1) {
                            if (otherAccountInput == 1) {
                                userChecking.paySomeone(customerArrayList, checkingArrayList, checkingArrayList, i, otherAccountIndex);
                            }
                            if (otherAccountInput == 2) {
                                userChecking.paySomeone(customerArrayList, checkingArrayList, savingsArrayList, i, otherAccountIndex);
                            }
                        }
                        if (paySomeoneInput == 2) {
                            if (otherAccountInput == 1) {
                                userSavings.paySomeone(customerArrayList, savingsArrayList, checkingArrayList, i, otherAccountIndex);
                            }
                            if (otherAccountInput == 2) {
                                userSavings.paySomeone(customerArrayList, savingsArrayList, savingsArrayList, i, otherAccountIndex);
                            }
                        }
                    }
                    if (mainInput == 6) {
                        userChecking.newBalanceSheet(customerArrayList, checkingArrayList, savingsArrayList, creditArrayList);
                        exit = true;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect Value");
            }
        }
        // if statement for manager
        if (openingOption == 2) {
            try {
                while (!exit) {
                    System.out.println("1. Inquire account by name.");
                    System.out.println("2. Inquire account by type/number");
                    System.out.println("3. Inquire all accounts");
                    System.out.println("4. Exit");
                    int managerInquireInput = userInput.nextInt();
                    if (managerInquireInput == 1) {
                        System.out.println("Who's account would you like to inquire about?");
                        String managerNameInput = userInput.next();
                        int userAccountIndex = managerCustomerObject.searchAccount(customerArrayList, managerNameInput);
                        userChecking = checkingArrayList.get(userAccountIndex);
                        userSavings = savingsArrayList.get(userAccountIndex);
                        userCredit = creditArrayList.get(userAccountIndex);
                        System.out.println("Account Summary");
                        System.out.println("Checking: $" + userChecking.getStartingBalance());
                        System.out.println("Savings:  $" + userSavings.getStartingBalance());
                        System.out.println("Credit:   $" + userCredit.getStartingBalance());
                    }
                    if (managerInquireInput == 2) {
                        System.out.println("What account type?");
                        System.out.println("1. Credit");
                        System.out.println("2. Savings");
                        System.out.println("3. Credit");
                        int accountTypeInput = userInput.nextInt();
                        System.out.println("What is the account number?");
                        int accountNumberInput = userInput.nextInt();
                        if (accountTypeInput == 1) {
                            int userAccountIndex = managerCheckingObject.searchAccount(checkingArrayList, accountNumberInput);
                            try {
                                managerChecking = (Checking) checkingArrayList.get(userAccountIndex);
                                managerChecking.inquireBalance(checkingArrayList, userAccountIndex, "Checking: $");
                            }
                            catch (IndexOutOfBoundsException e) {
                                System.out.println("Account does not exist. Returning to menu.");
                            }
                        }
                        if (accountTypeInput == 2) {
                            int userAccountIndex = managerSavingsObject.searchAccount(savingsArrayList, accountNumberInput);
                            try {
                                managerSavings = (Savings) savingsArrayList.get(userAccountIndex);
                                managerSavings.inquireBalance(savingsArrayList, userAccountIndex, "Savings: $");
                            }
                            catch (IndexOutOfBoundsException e) {
                                System.out.println("Account does not exist. Returning to menu.");
                            }
                        }
                        if (accountTypeInput == 3) {
                            int userAccountIndex = managerCreditObject.searchAccount(creditArrayList, accountNumberInput);
                            try {
                                managerCredit = (Credit) creditArrayList.get(userAccountIndex);
                                managerCredit.inquireBalance(creditArrayList, userAccountIndex, "Credit: $");
                            }
                            catch (IndexOutOfBoundsException e) {
                                System.out.println("Account does not exist. Returning to menu.");
                            }
                        }
                    }
                    if (managerInquireInput == 3) {
                        for (int i = 0; i < customerArrayList.size(); i ++) {
                            System.out.println(customerArrayList.get(i).getFirstName() + " " + customerArrayList.get(i).getLastName());
                            System.out.println("Checking: $" + checkingArrayList.get(i).getStartingBalance());
                            System.out.println("Savings:  $" + savingsArrayList.get(i).getStartingBalance());
                            System.out.println("Credit:   $" + creditArrayList.get(i).getStartingBalance());
                        }
                    }
                    if (managerInquireInput == 4) {
                        exit = true;
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Incorrect Value");
            }
        }
    }
}
