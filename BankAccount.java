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
    private String payee;
    private String checkNum;
    private float  amount;
    private float balance;
    private String transActionDate;
    private String category;
    private final ArrayList<String> transactions = new ArrayList<String>();
    DecimalFormat df = new DecimalFormat("#####.##");

  
    
    public BankAccount(String name, String actype, float bal) {
        this.name = name;
        this.actype = actype;
        this.balance = bal;
    }// end constructor

    public boolean deposit(float amt, String transDate, String transCategory, String transPayee, String transCheckNum) {
        this.checkNum = transCheckNum;
        this.payee = transPayee;
        this.amount = amt;
        this.transActionDate = transDate;
        this.category = transCategory;
        
        if (amount < 0) {
            System.out.println("Invalid Amount");
            return false;
        }
        balance = balance + amount;
        System.out.println("new bal = " + balance);
        // NO	5/12/20	1516	Lucio Lopez	Home:Lawn & Garden	$50.00		$11,087.14

        transactions.add("NO," + transActionDate + "," + payee +","+ category 
                + ",   ," + df.format(amount) + "," + df.format(balance) + "\n");
        return true;
    }

    public boolean withdraw(float amt, String transDate, String transCategory, String transPayee,String transCheckNum) {
        this.checkNum = transCheckNum;
        this.payee = transPayee;
        this.amount = amt;
        this.transActionDate = transDate;
        this.category = transCategory;
        if (balance < amount) {
            System.out.println("Not sufficient balance.");
            return false;
        }
        if (amount < 0) {
            System.out.println("Invalid Amount");
            return false;
        }

        balance = balance - amount;
        System.out.println("new bal = " + balance);

        transactions.add("NO," + transActionDate + "," + payee +","+ category 
                + "," + df.format(amount) + ",   ," + df.format(balance) + "\n");
        return true;
    }

    public void displayInfo() {
        System.out.println("Name:" + name);
        System.out.println("Balance: $" + balance);

    }

    public void dbal() {
        System.out.println("Balance: $" + balance);
    }

    public void recordTransActions() throws IOException {
        
        // will overwrite the account info with new balance
        FileWriter accountInfoWriter = new FileWriter("accountInfo.txt");
        accountInfoWriter.write(name + ","+actype + "," + df.format(balance));
        accountInfoWriter.close();
        
        
        
        
        File transActionsFile = new File("TransActions.txt");
        if (!transActionsFile.exists()) {
            transActionsFile.createNewFile();
        }

        FileWriter transActionFileWriter = new FileWriter(transActionsFile.getAbsoluteFile(),true);
        BufferedWriter transActionBufferedWriter = new BufferedWriter(transActionFileWriter);
        for (String transAction : transactions) {
            transActionBufferedWriter.write(transAction);
        }
        
     
       

        transActionBufferedWriter.close();

    }// end recordTransActions()

//    public void mainDisplay() {
//
//        int menu;
//
//        System.out.println("Menu");
//        System.out.println("1. Deposit Amount");
//        System.out.println("2. Withdraw Amount");
//        System.out.println("3. Display Information");
//        System.out.println("4. Exit");
//
//        boolean quit = false;
//        do {
//            System.out.print("Please enter your choice: ");
//            menu = userInput.nextInt();
//            switch (menu) {
//                case 1:
//                    deposit();
//                    break;
//
//                case 2:
//                    withdraw();
//                    break;
//
//                case 3:
//                    displayInfo();
//                    break;
//
//                case 4:
//
//                    try {
//                        recordTransActions();
//                    } catch (IOException ex) {
//                        Logger.getLogger(BankAccount.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    System.out.println(transactions);
//                    quit = true;
//                    break;
//            }
//        } while (!quit);
//
//    }// end mainDisplay()

    public String getName() {
        return name;
    }

    public String getActype() {
        return actype;
    }

    public float getAmount() {
        return amount;
    }

    public float getBalance() {
        return balance;
    }

    public String getTransActionDate() {
        return transActionDate;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

}// end class BankAccount
