/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankregister;

import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo Estrada
 */
public class ErrorMessages {

    public ErrorMessages() {
    }// end construct

    public void displayInsufficientFunds() {
        //custom title, warning icon
        JOptionPane.showMessageDialog(null,
                "Insufficient Funds!",
                "Applcation warning",
                JOptionPane.WARNING_MESSAGE);

    }// end displayInsufficientFunds

    public void displayAmountError() {
        //custom title, warning icon
        JOptionPane.showMessageDialog(null,
                "Amount Error!",
                "Applcation warning",
                JOptionPane.WARNING_MESSAGE);

    }// end displayAmountError
    
    public void displayBlankFieldError(String fieldName){
                //custom title, warning icon
        JOptionPane.showMessageDialog(null, fieldName +
                " Field cannot be blank Error!",
                "Applcation warning",
                JOptionPane.WARNING_MESSAGE);
        
    }// end displayBlankFieldError

}// end class ErrorMessages
