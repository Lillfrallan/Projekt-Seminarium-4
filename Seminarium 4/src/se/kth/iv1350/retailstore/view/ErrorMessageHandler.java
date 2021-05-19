package se.kth.iv1350.retailstore.view;

/**
 * Handler for showing error messages to user.
 */
public class ErrorMessageHandler {
    /**
     * Prints a <code>String</code> to user showing relevant error information to the user interface.
     * @param errorMessage The error message for user.
     */
    void displayErrorMessage(String errorMessage){
        System.out.println("\n*******ERROR*MESSAGE*******\n" + errorMessage +
                "\n*******END*OF*MESSAGE******\n");
    }
}
