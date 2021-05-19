package se.kth.iv1350.retailstore.view;

import se.kth.iv1350.retailstore.controller.Controller;
import se.kth.iv1350.retailstore.integration.ServerConnectionException;
import se.kth.iv1350.retailstore.model.ItemNotFoundException;
import se.kth.iv1350.retailstore.model.SaleInformation;

import java.io.IOException;

/**
 * Represent a view for a user.
 */
public class View {
    private final Controller controller;
    private ErrorMessageHandler errorMessage = new ErrorMessageHandler();

    /**
     * Creates new instance of View.
     * @param controller The controller that is used for all operations.
     */
    public View (Controller controller) throws IOException {
        this.controller = controller;
        controller.addSaleObserver(new TotalRevenueFileOutput());
        controller.addSaleObserver(new TotalRevenueView());
    }

    /**
     * Represents how the interaction is made with the program could be.
     */
    public void execution (){
        controller.startNewSale();
        try{
            updateView(controller.addItem("100", 1));
        } catch (ItemNotFoundException e) {
            errorMessage.displayErrorMessage("Item identifier is not Recognized.");
        } catch (ServerConnectionException e) {
            errorMessage.displayErrorMessage("Could not perform task. Please try again.");
        }
        try {
            updateView(controller.addItem("220", 3));
        } catch (ItemNotFoundException e) {
            errorMessage.displayErrorMessage("Item identifier is not Recognized.");
        } catch (ServerConnectionException e) {
            errorMessage.displayErrorMessage("Could not perform task. Please try again.");
        }
        try {
            updateView(controller.addItem("150", 4));
        } catch (ItemNotFoundException e) {
            errorMessage.displayErrorMessage("Item identifier is not Recognized.");
        } catch (ServerConnectionException e) {
            errorMessage.displayErrorMessage("Could not perform task. Please try again.");
        }
        try {
            updateView(controller.addItem("no connection", 1));
        } catch (ItemNotFoundException e) {
            errorMessage.displayErrorMessage("Item identifier is not Recognized.");
        } catch (ServerConnectionException e) {
            errorMessage.displayErrorMessage("Could not perform task. Please try again.");
        }
        updateView(controller.discountRequest("9007182743"));
        updateView(controller.addPay(500));
        controller.printReceipt();
    }

    private void updateView(SaleInformation saleInformation) {
        System.out.println(saleInformation.toString());
    }
}
