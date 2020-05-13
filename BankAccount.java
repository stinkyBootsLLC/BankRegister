/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template transActionsFile, choose Tools | Templates
 * and open the template in the editor.
 */
package bankregister;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 *
 * @author Eduardo Estrada
 */
public class BankAccount {

    private final String name;
    private final String actype;
    private float balance;
    private final ArrayList<String> transactions = new ArrayList<>();
    private final DecimalFormat df = new DecimalFormat("#####.##");
    private final ErrorMessages error = new ErrorMessages();

  
    /**
     * Creates a BankAccount instance
     * @param name - of the owner
     * @param actype - AccountType
     * @param bal - current balance
     */
    public BankAccount(String name, String actype, float bal) {
        this.name = name;
        this.actype = actype;
        this.balance = bal;
    }// end constructor

    public boolean deposit(float amt, String transDate, String transCategory, 
                                     String transPayee, String transCheckNum){

        if (amt < 0) {
            error.displayAmountError();
            return false;
        } else {
            balance = balance + amt;
            transactions.add("NO," + transDate + "," + transCheckNum + ","             
                    + transPayee +","+ transCategory + ",   ," + df.format(amt) + 
                    "," + df.format(balance) + "\n");
            return true; 
        }
    }// end deposit()

    public boolean withdraw(float amt, String transDate, String transCategory, 
                                String transPayee,String transCheckNum){
        if (balance < amt) {
            error.displayInsufficientFunds();
            return false;
        } else if (amt < 0) {
            error.displayAmountError();
            return false;
        } else {
            balance = balance - amt;
            transactions.add("NO," + transDate + "," + transCheckNum + "," + 
                    transPayee +","+ transCategory + "," + df.format(amt) + ", ," 
                    + df.format(balance) + "\n");
            return true;
        }
    }// end withdraw()


    public void recordTransActions() throws IOException {
        
        try ( 
            // will overwrite the account info file with new balance
            FileWriter accountInfoWriter = new FileWriter("accountInfo.txt")){
            accountInfoWriter.write(name + ","+actype + "," + df.format(balance));
        }// end try
        File transActionsFile = new File("TransActions.txt");
        if (!transActionsFile.exists()) {
            transActionsFile.createNewFile();
        } else {
            FileWriter transActionFileWriter = new FileWriter(transActionsFile.getAbsoluteFile(),true);
            try (BufferedWriter transActionBufferedWriter = new BufferedWriter(transActionFileWriter)){
                for (String transAction : transactions) {
                    transActionBufferedWriter.write(transAction);
                }// end for
            }// end try
        }// end if

    }// end recordTransActions()


    public String getName() {
        return name;
    }

    public String getActype() {
        return actype;
    }

    public float getBalance() {
        return balance;
    }
    
    public String getBalanceAsString(){
        return df.format(balance);
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

}// end class BankAccount
