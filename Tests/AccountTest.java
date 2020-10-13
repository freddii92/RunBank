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

    public ArrayList<Customer> setupList() {

        ArrayList<Customer> testArray = new ArrayList<>();
        String firstName = "Alfredo";
        String lastName = "Rodriguez";
        String dateOfBirth = "August 19, 1992";
        String address = "123 Unknown Way El Paso TX";
        long phoneNumber = 915999999;
        int identificationNumber = 28;
        int checkingAccountNumber = 3003;
        int savingsAccountNumber = 1234;
        int creditAccountNumber = 4321;
        double checkingStartingBalance = 1999.99;
        double savingsStartingBalance = 699.98;
        double creditStartingBalance = -999.99;
        Customer checkingInfo1 = new Customer(firstName,lastName, dateOfBirth, identificationNumber, address, phoneNumber, checkingAccountNumber, savingsAccountNumber, creditAccountNumber, checkingStartingBalance, savingsStartingBalance, creditStartingBalance);
        testArray.add(checkingInfo1);
        firstName = "David";
        lastName = "Rodriguez";
        dateOfBirth = "August 19, 1992";
        address = "123 Unknown Way El Paso TX";
        phoneNumber = 915999999;
        identificationNumber = 31;
        checkingAccountNumber = 7356;
        savingsAccountNumber = 1690;
        creditAccountNumber = 9987;
        checkingStartingBalance = 15679.09;
        savingsStartingBalance = 69.09;
        creditStartingBalance = -9.99;
        Customer checkingInfo2 = new Customer(firstName,lastName, dateOfBirth, identificationNumber, address, phoneNumber, checkingAccountNumber, savingsAccountNumber, creditAccountNumber, checkingStartingBalance, savingsStartingBalance, creditStartingBalance);
        testArray.add(checkingInfo2);
        firstName = "Ivan";
        lastName = "Rodriguez";
        dateOfBirth = "August 19, 1992";
        address = "123 Unknown Way El Paso TX";
        phoneNumber = 915999999;
        identificationNumber = 4;
        checkingAccountNumber = 7656;
        savingsAccountNumber = 5678;
        creditAccountNumber = 9999;
        checkingStartingBalance = 19.99;
        savingsStartingBalance = 6.98;
        creditStartingBalance = -9989.99;
        Customer checkingInfo3 = new Customer(firstName,lastName, dateOfBirth, identificationNumber, address, phoneNumber, checkingAccountNumber, savingsAccountNumber, creditAccountNumber, checkingStartingBalance, savingsStartingBalance, creditStartingBalance);
        testArray.add(checkingInfo3);
        return testArray;
    }    /**
     * First test to searchAccount method in Account class
     */
    @Test
    public void searchAccountTest1() {
        Checking testCustomer = new Checking();
        assertEquals(0, testCustomer.searchAccount(setupList(),3003));
    }

    /**
     * Second test to searchAccount method in Account class
     */
    @Test
    public void searchAccountTest2() {
        Checking testCustomer = new Checking();
        assertTrue(testCustomer.searchAccount(setupList(), 9999) == 2);
    }

    /**
     * First test to deposit method in Account class
     */
    @Test
    public void depositTest1() {
        Checking test = new Checking();
        assertSame("Incorrect value. Returning to main menu", test.deposit(setupList(), 0, -123.45, "checking"));
    }

    /**
     * Second test to deposit method in Account class
     */
    @Test
    public void depositTest2() {
        Checking test = new Checking();
        assertEquals("Deposit of $1000000.0 successful.", test.deposit(setupList(), 0, 1000000.00, "checking"));
    }

    /**
     * First test to transfer method in Account class
     */
    @Test
    public void transferTest1() {
        Checking test = new Checking();
        assertTrue(("Insufficient funds. Returning to main menu.").equals(test.transfer(setupList(), "checking", "savings", 0, 200000.99)));
    }

    /**
     * Second test to transfer method in Account class
     */
    @Test
    public void transferTest2() {
        Checking test = new Checking();
        assertEquals("Transfer of $19.99 successful.", test.transfer(setupList(), "savings", "checking", 0, 19.99));
    }
}