package se.kth.iv1350.retailstore.startup;

import se.kth.iv1350.retailstore.controller.Controller;
import se.kth.iv1350.retailstore.view.View;

/**
 * Represents a startup of this application.
 */
public class Main {

    /**
     * Starts the program.
     * @param args The application does not take any command line parameters.
     */
    public static void main (String[] args) throws Exception{
        Controller controller = new Controller();
        View view = new View(controller);
        view.execution();
    }
}
