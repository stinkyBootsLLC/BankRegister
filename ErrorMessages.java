/*
 * File: ErrorMessages.java
 * Created: 5/16/2020
 * 
 */
package bankregister;

import javax.swing.JOptionPane;

/**
 * This class will handle displaying error messages to the user.
 * @author Eduardo Estrada
 */
public class ErrorMessages {

    public ErrorMessages() {
    }// end constructor
    /**
     * Displays an JOptionPane with specific error.
     * @param error - specific error.
     */
    public void errorMessage(String error){
        JOptionPane.showMessageDialog(null,
        error, "Applcation warning",
        JOptionPane.WARNING_MESSAGE);
    }// end errorMessage()
    
}// end class ErrorMessages
