import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Bank Statement class to print out bank statements
 *
 * @author Alfredo Rodriguez
 * @version 1.0, 10/15/2020
 * @since October 15, 2020
 */
public class BankStatement {

    public void createBankStatement(ArrayList<Customer> customerArrayList, int index, double checkingStartingBalance,
                                     double savingsStartingBalance, double creditStartingBalance) {

        File transactionLog = new File("transactionLog.txt");
        Scanner scanner = null;
        // try and catch to prevent file not found exception
        try {
            scanner = new Scanner(transactionLog);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        // asserting scanner. this was suggested by IntelliJ
        // it would give me an exception without it sometimes
        assert scanner != null;

        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String today = df.format(new Date());

        try (PrintWriter writer = new PrintWriter("Bank Statement")) {
            int count = 0;
            writer.println("Miner Bank" + "                                                    Checking Account Number: " + customerArrayList.get(index).getCheckingAccountNumber());
            writer.println("                                                              Savings Account Number:  " + customerArrayList.get(index).getSavingsAccountNumber());
            writer.println("                                                              Credit Account Number:   " + customerArrayList.get(index).getCreditAccountNumber());
            writer.println("                                                              Statement Begin Date: " + today);
            writer.println("                                                              Statement End Date: " + today);
            writer.println(customerArrayList.get(index).getFirstName() + customerArrayList.get(index).getLastName());
            writer.println(customerArrayList.get(index).getAddress());
            writer.println();
            writer.println();
            writer.println("-------------------- Transactions --------------------");
            while (scanner.hasNextLine()) {
                if (scanner.next().equals(customerArrayList.get(index).getFirstName())) {
                    writer.println(scanner.nextLine());
                }
            }

            writer.println(customerArrayList.get(index).getFirstName() + " " + customerArrayList.get(index).getLastName());


        }
        catch (FileNotFoundException e) {
            System.out.println("Error");
        }

    }

}
