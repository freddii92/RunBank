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
        ArrayList<Customer> customerArrayList;
//        ArrayList<Account> checkingArrayList = new ArrayList<>();
//        ArrayList<Account> savingsArrayList = new ArrayList<>();
//        ArrayList<Account> creditArrayList = new ArrayList<>();
        Scanner scanner = null;
        Scanner userInput = new Scanner(System.in);
        int userID;
        int openingOption;
        Customer user = new Customer();
        Checking userChecking = new Checking();
        Savings userSavings = new Savings();
        Credit userCredit = new Credit();
        Checking managerChecking;
        Savings managerSavings;
        Credit managerCredit;
        Customer managerCustomerObject = new Customer();
        Checking managerCheckingObject = new Checking();
        Savings managerSavingsObject = new Savings();
        Credit managerCreditObject = new Credit();
        boolean exit = false;

        customerArrayList = managerCustomerObject.getBankListArray();

        System.out.println("Welcome!");
        System.out.println("1. New user");
        System.out.println("2. Existing user");
        int userHistory = userInput.nextInt();

        if (userHistory == 1) {
            user.createAccount(customerArrayList);
        }

        // greeting customer and asking if they are a customer or bank manager
        System.out.println("Welcome!");
        System.out.println("Are you a customer or bank manager?");
        System.out.println("1. Customer");
        System.out.println("2. Bank Manager");
        System.out.println("3. Transaction Reader");
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
                        System.out.println("Checking: $" + user.getCheckingStartingBalance());
                        System.out.println("Savings:  $" + user.getSavingsStartingBalance());
                        System.out.println("Credit:   $" + user.getCreditStartingBalance());
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
                            System.out.println(userChecking.deposit(customerArrayList, i, depositAmount, "checking"));
                        }
                        if (depositInput == 2) {
                            System.out.print("Please enter amount: ");
                            double depositAmount = userInput.nextDouble();
                            System.out.println(userSavings.deposit(customerArrayList, i, depositAmount, "savings"));
                        }
                        if (depositInput == 3) {
                            System.out.print("Please enter amount: ");
                            double depositAmount = userInput.nextDouble();
                            System.out.println(userCredit.deposit(customerArrayList, i, depositAmount, "credit"));
                        }
                    }
                    if (mainInput == 3) {
                        System.out.println("Please select account.");
                        System.out.println("1. Checking");
                        System.out.println("2. Savings");
                        int withdrawInput = userInput.nextInt();
                        if (withdrawInput == 1) {
                            userChecking.withdraw(customerArrayList, i, "checking");
                        }
                        if (withdrawInput == 2) {
                            userSavings.withdraw(customerArrayList, i, "savings");
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
                            System.out.println(userChecking.transfer(customerArrayList, "checking", "savings", i, transferAmount));
                        }
                        if (transferInput == 2) {
                            System.out.print("Please select amount: ");
                            double transferAmount = userInput.nextDouble();
                            System.out.println(userChecking.transfer(customerArrayList, "savings", "checking", i, transferAmount));
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
                                userChecking.paySomeone(customerArrayList, "checking", "checking", i, otherAccountIndex);
                            }
                            if (otherAccountInput == 2) {
                                userChecking.paySomeone(customerArrayList, "checking", "savings", i, otherAccountIndex);
                            }
                        }
                        if (paySomeoneInput == 2) {
                            if (otherAccountInput == 1) {
                                userSavings.paySomeone(customerArrayList, "savings", "checking", i, otherAccountIndex);
                            }
                            if (otherAccountInput == 2) {
                                userSavings.paySomeone(customerArrayList, "savings", "savings", i, otherAccountIndex);
                            }
                        }
                    }
                    if (mainInput == 6) {
                        userChecking.newBalanceSheet(customerArrayList);
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
                        Scanner lastNameScanner = new Scanner(System.in);
                        System.out.print("Please enter first name: ");
                        String managerFirstNameInput = userInput.next();
                        System.out.print("Please enter last name: ");
                        String managerLastNameInput = lastNameScanner.nextLine();
                        int userAccountIndex = managerCustomerObject.searchAccount(customerArrayList, managerFirstNameInput, managerLastNameInput);
                        if (userAccountIndex == -1) continue;
                        System.out.println("Account Summary");
                        System.out.println("Checking: $" + customerArrayList.get(userAccountIndex).getCheckingStartingBalance());
                        System.out.println("Savings:  $" + customerArrayList.get(userAccountIndex).getSavingsStartingBalance());
                        System.out.println("Credit:   $" + customerArrayList.get(userAccountIndex).getCreditStartingBalance());
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
                            int userAccountIndex = managerCheckingObject.searchAccount(customerArrayList, accountNumberInput);
                            try {
                                managerCheckingObject.inquireBalance(customerArrayList, userAccountIndex, "checking");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Account does not exist. Returning to menu.");
                            }
                        }
                        if (accountTypeInput == 2) {
                            int userAccountIndex = managerSavingsObject.searchAccount(customerArrayList, accountNumberInput);
                            try {
                                managerCheckingObject.inquireBalance(customerArrayList, userAccountIndex, "savings");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Account does not exist. Returning to menu.");
                            }
                        }
                        if (accountTypeInput == 3) {
                            int userAccountIndex = managerCreditObject.searchAccount(customerArrayList, accountNumberInput);
                            try {
                                managerCheckingObject.inquireBalance(customerArrayList, userAccountIndex, "credit");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Account does not exist. Returning to menu.");
                            }
                        }
                    }
                    if (managerInquireInput == 3) {
                        for (int i = 0; i < customerArrayList.size(); i++) {
                            System.out.println(customerArrayList.get(i).getFirstName() + " " + customerArrayList.get(i).getLastName());
                            System.out.println("Checking: $" + customerArrayList.get(i).getCheckingStartingBalance());
                            System.out.println("Savings:  $" + customerArrayList.get(i).getSavingsStartingBalance());
                            System.out.println("Credit:   $" + customerArrayList.get(i).getCreditStartingBalance());
                        }
                    }
                    if (managerInquireInput == 4) {
                        exit = true;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect Value");
            }
        }
        if (openingOption == 3) {
            managerCheckingObject.transactionReader("");
        }
    }
}
