import java.util.ArrayList;

/**
 * Checking class is the child class of Account
 *
 * @author Alfredo Rodriguez
 * @version 1.0, 09/25/2020
 * @since September 25, 2020
 */
public class Checking extends Account {

    private int identificationNumber;

    /**
     * This is the default constructor
     */
    public Checking() {
        super();
    }

    /**
     * Constructor for Checking
     *
     * @param identificationNumberIn Receives ID number
     * @param accountNumberIn Receives account number @see Account
     * @param startingBalanceIn Receives starting balance @see Account
     */
    public Checking(int identificationNumberIn, int accountNumberIn, double startingBalanceIn) {
        super(accountNumberIn, startingBalanceIn);
        this.identificationNumber = identificationNumberIn;
    }

    /**
     * This method gets ID number
     * @return ID number
     */
    public int getIdentificationNumber() {
        return this.identificationNumber;
    }

    /**
     * This method sets ID number
     *
     * @param identificationNumberIn Receives ID number
     */
    public void setIdentificationNumber(int identificationNumberIn) {
        this.identificationNumber = identificationNumberIn;
    }

    /**
     * This method is used to inquire balance.
     *
     * @param accountList Receives account list.
     * @param i Receives index from array list.
     * @param account Receives account type
     */
    public void inquireBalance(ArrayList<Account> accountList, int i, String account) {
        System.out.println(account + accountList.get(i).getStartingBalance());
    }
}
