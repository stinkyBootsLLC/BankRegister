/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankregister;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Eduardo Estrada
 */
public class BankAccount {

    private final String name;
    private final String actype;
    private float  amount;
    private float balance;
    private String transActionDate;
    private String category;
    private final ArrayList<String> transactions = new ArrayList<String>();

    public BankAccount(String name, String actype, float bal) {
        this.name = name;
        this.actype = actype;
        this.balance = bal;
    }// end constructor

    public boolean deposit(float amt, String transDate, String transCategory) {
        this.amount = amt;
        this.transActionDate = transDate;
        this.category = transCategory;
        
        if (amount < 0) {
            System.out.println("Invalid Amount");
            return false;
        }
        balance = balance + amount;
        System.out.println("new bal = " + balance);
        
        transactions.add("Date: " + transActionDate + " Category: " + category + " Amount: $" + amount + " Balance: $" + balance + "\n");
        return true;
    }

    public boolean withdraw(float amt, String transDate, String transCategory) {

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

        transactions.add("Date: " + transActionDate + " Category: " + category + " Amount: -$" + amount + " Balance: $" + balance + "\n");
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

        File file = new File("TransActions.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String transAction : transactions) {
            bw.write(transAction);
        }
        
        // note:  I need to overwrite the original file with the new balance
       

        bw.close();

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
