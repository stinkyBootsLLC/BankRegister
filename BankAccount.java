/*
 * File: BankAccount.java
 * Created: 5/16/2020
 * 
 */
package bankregister;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class will represent all the properties and actions of a bank account.
 * @author Eduardo Estrada
 */
public class BankAccount {

    private final String name;
    private final String actype;
    private float balance;
    private final ArrayList<String> transactions;
    private final DecimalFormat df;
    private final ErrorMessages error;
    /**
     * Creates a BankAccount instance
     * @param name - of the owner
     * @param actype - Account Type
     * @param bal - current balance
     */
    public BankAccount(String name, String actype, float bal) {
        transactions = new ArrayList<>();
        df = new DecimalFormat("#####.##");
        error = new ErrorMessages();
        this.name = name;
        this.actype = actype;
        this.balance = bal;
    }// end constructor
    /**
     * Returns a boolean if a successful deposit takes place.
     * @param amt - amount of deposit
     * @param transDate - date of deposit
     * @param transCategory - deposit category
     * @param transPayee - deposit payee
     * @param transCheckNum - deposit check number
     * @return boolean
     */
    public boolean deposit(float amt, String transDate, String transCategory,
            String transPayee, String transCheckNum) {

        if (amt < 0) {
            error.errorMessage("Amount Error!");
            return false;
        } else {
            balance = balance + amt;
            transactions.add("NO," + transDate + "," + transCheckNum + ","
                    + transPayee + "," + transCategory + ",   ," + df.format(amt)
                    + "," + df.format(balance) + "\n");
            return true;
        }
    }// end deposit()
    /**
     * Returns a boolean if a successful withdrawal takes place.
     * @param amt - amount of withdrawal
     * @param transDate - date of withdrawal
     * @param transCategory - withdrawal category
     * @param transPayee - withdrawal payee
     * @param transCheckNum - withdrawal check number
     * @return boolean
     */
    public boolean withdraw(float amt, String transDate, String transCategory,
            String transPayee, String transCheckNum) {
        if (balance < amt) {
            error.errorMessage("Insufficient Funds!");
            return false;
        } else if (amt < 0) {
            error.errorMessage("Amount Error!");
            return false;
        } else {
            balance = balance - amt;
            transactions.add("NO," + transDate + "," + transCheckNum + ","
                    + transPayee + "," + transCategory + "," + df.format(amt) + ", ,"
                    + df.format(balance) + "\n");
            return true;
        }
    }// end withdraw()
    /**
     * Will record the session transactions to external text file.
     * This method will also update the external text with account credentials.
     * A new balance.
     * @throws IOException 
     */
    public void recordTransActions() throws IOException {

        try (
            // will overwrite the initial account info file with new balance
            FileWriter accountInfoWriter = new FileWriter("accountInfo.txt")) {
            accountInfoWriter.write(name + "," + actype + "," + df.format(balance));
        }// end try
        File transActionsFile = new File("TransActions.txt");
        if (!transActionsFile.exists()) {
            transActionsFile.createNewFile();
        } else {
            FileWriter transActionFileWriter = new FileWriter(transActionsFile.getAbsoluteFile(), true);
            try (BufferedWriter transActionBufferedWriter = new BufferedWriter(transActionFileWriter)) {
                for (String transAction : transactions) {
                    transActionBufferedWriter.write(transAction);
                }// end for
            }// end try
        }// end if

    }// end recordTransActions()

    public String getActype() {
        return actype;
    }

    public float getBalance() {
        return balance;
    }

    public String getBalanceAsString() {
        return df.format(balance);
    }

}// end class BankAccount
