package se.kth.iv1350.retailstore.view;

import se.kth.iv1350.retailstore.model.SaleInformationObserver;

import java.io.IOException;

/**
 * Is used to show the stores total revenue to the user interface.
 */
public class TotalRevenueView extends IOException implements SaleInformationObserver {
    private double totalRevenue = 0;

    /**
     * Adds the revenue of a finished sale to the total revenue and prints to the user interface
     * when invoked.
     * @param totalRevenue The amount of revenue of completed sale.
     */
    @Override
    public void newSale(double totalRevenue) {
        this.totalRevenue += totalRevenue;
        displayTotalRevenue();
    }

    private void displayTotalRevenue(){
        System.out.println("\nTotal revenue for store: " + totalRevenue + "kr\n");
    }
}
