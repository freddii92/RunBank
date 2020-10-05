import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * JUnit Test class
 *
 * @author Alfredo Rodriguez
 * @version 1.0, 09/26/2020
 * @since September 26, 2020
 */
public class AccountTest {

    /**
     * First test to searchAccount method in Account class
     */
    @Test
    public void searchAccountTest1() {
        ArrayList<Account> testArray = new ArrayList<>();
        int identificationNumber = 28;
        int checkingAccountNumber = 3003;
        double checkingStartingBalance = 1999.99;
        Checking checkingInfo1 = new Checking(identificationNumber, checkingAccountNumber, checkingStartingBalance);
        testArray.add(checkingInfo1);
        identificationNumber = 29;
        checkingAccountNumber = 2001;
        checkingStartingBalance = 19.99;
        Checking checkingInfo2 = new Checking(identificationNumber, checkingAccountNumber, checkingStartingBalance);
        testArray.add(checkingInfo2);
        identificationNumber = 30;
        checkingAccountNumber = 2301;
        checkingStartingBalance = 194.90;
        Checking checkingInfo3 = new Checking(identificationNumber, checkingAccountNumber, checkingStartingBalance);
        testArray.add(checkingInfo3);
        assertEquals(1, checkingInfo1.searchAccount(testArray,2001));
    }

    /**
     * Second test to searchAccount method in Account class
     */
    @Test
    public void searchAccountTest2() {
        ArrayList<Account> testArray = new ArrayList<>();
        int identificationNumber = 28;
        int checkingAccountNumber = 3003;
        double checkingStartingBalance = 1999.99;
        Checking checkingInfo1 = new Checking(identificationNumber, checkingAccountNumber, checkingStartingBalance);
        testArray.add(checkingInfo1);
        identificationNumber = 29;
        checkingAccountNumber = 2001;
        checkingStartingBalance = 19.99;
        Checking checkingInfo2 = new Checking(identificationNumber, checkingAccountNumber, checkingStartingBalance);
        testArray.add(checkingInfo2);
        identificationNumber = 30;
        checkingAccountNumber = 2301;
        checkingStartingBalance = 194.90;
        Checking checkingInfo3 = new Checking(identificationNumber, checkingAccountNumber, checkingStartingBalance);
        testArray.add(checkingInfo3);
        assertTrue(checkingInfo1.searchAccount(testArray, 2301) == 2);
    }

    /**
     * First test to deposit method in Account class
     */
    @Test
    public void depositTest1() {
        ArrayList<Account> testArray = new ArrayList<>();
        int identificationNumber = 28;
        int checkingAccountNumber = 3003;
        double checkingStartingBalance = 1999.99;
        Checking checkingInfo1 = new Checking(identificationNumber, checkingAccountNumber, checkingStartingBalance);
        testArray.add(checkingInfo1);
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        String firstName = "Alfredo";
        String lastName = "Rodriguez";
        String dateOfBirth = "August 19, 1992";
        String address = "123 Unknown Way El Paso TX";
        long phoneNumber = 915999999;
        Customer customerInfo = new Customer(firstName, lastName, dateOfBirth, identificationNumber, address, phoneNumber);
        customerArrayList.add(customerInfo);
        assertSame("Incorrect value. Returning to main menu", checkingInfo1.deposit(customerArrayList, testArray, 0, -123.45, "checking"));
    }

    /**
     * Second test to deposit method in Account class
     */
    @Test
    public void depositTest2() {
        ArrayList<Account> testArray = new ArrayList<>();
        int identificationNumber = 28;
        int checkingAccountNumber = 3003;
        double checkingStartingBalance = 1999.99;
        Checking checkingInfo1 = new Checking(identificationNumber, checkingAccountNumber, checkingStartingBalance);
        testArray.add(checkingInfo1);
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        String firstName = "Alfredo";
        String lastName = "Rodriguez";
        String dateOfBirth = "August 19, 1992";
        String address = "123 Unknown Way El Paso TX";
        long phoneNumber = 915999999;
        Customer customerInfo = new Customer(firstName, lastName, dateOfBirth, identificationNumber, address, phoneNumber);
        customerArrayList.add(customerInfo);
        assertEquals("Deposit of $1000000.0 successful.", checkingInfo1.deposit(customerArrayList, testArray, 0, 1000000.00, "checking"));
    }

    /**
     * First test to transfer method in Account class
     */
    @Test
    public void transferTest1() {
        ArrayList<Account> testArray = new ArrayList<>();
        int identificationNumber = 28;
        int checkingAccountNumber = 3003;
        double checkingStartingBalance = 1999.99;
        Checking checkingInfo1 = new Checking(identificationNumber, checkingAccountNumber, checkingStartingBalance);
        testArray.add(checkingInfo1);
        ArrayList<Account> savingsTestArray = new ArrayList<>();
        identificationNumber = 29;
        int savingsAccountNumber = 2001;
        double savingsStartingBalance = 19.99;
        Savings savingsInfo = new Savings(identificationNumber, savingsAccountNumber, savingsStartingBalance);
        savingsTestArray.add(savingsInfo);
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        String firstName = "Alfredo";
        String lastName = "Rodriguez";
        String dateOfBirth = "August 19, 1992";
        String address = "123 Unknown Way El Paso TX";
        long phoneNumber = 915999999;
        Customer customerInfo = new Customer(firstName, lastName, dateOfBirth, identificationNumber, address, phoneNumber);
        customerArrayList.add(customerInfo);
        assertTrue(("Insufficient funds. Returning to main menu.").equals(checkingInfo1.transfer(customerArrayList, testArray, savingsTestArray, 0, 1, 200000.99)));
    }

    /**
     * Second test to transfer method in Account class
     */
    @Test
    public void transferTest2() {
        ArrayList<Account> testArray = new ArrayList<>();
        int identificationNumber = 28;
        int checkingAccountNumber = 3003;
        double checkingStartingBalance = 1999.99;
        Checking checkingInfo1 = new Checking(identificationNumber, checkingAccountNumber, checkingStartingBalance);
        testArray.add(checkingInfo1);
        ArrayList<Account> savingsTestArray = new ArrayList<>();
        identificationNumber = 29;
        int savingsAccountNumber = 2001;
        double savingsStartingBalance = 19.99;
        Savings savingsInfo = new Savings(identificationNumber, savingsAccountNumber, savingsStartingBalance);
        savingsTestArray.add(savingsInfo);
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        String firstName = "Alfredo";
        String lastName = "Rodriguez";
        String dateOfBirth = "August 19, 1992";
        String address = "123 Unknown Way El Paso TX";
        long phoneNumber = 915999999;
        Customer customerInfo = new Customer(firstName, lastName, dateOfBirth, identificationNumber, address, phoneNumber);
        customerArrayList.add(customerInfo);
        assertEquals("Transfer of $19.99 successful.", checkingInfo1.transfer(customerArrayList, savingsTestArray, testArray, 0, 1, 19.99));
    }
}