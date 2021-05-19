package se.kth.iv1350.retailstore.view;

import se.kth.iv1350.retailstore.model.SaleInformationObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Logs the stores total revenue in a text file.
 */
public class TotalRevenueFileOutput extends IOException implements SaleInformationObserver {
    private final PrintWriter revenueWriter;
    private double totalRevenue = 0;

    /**
     * Creates a new text file to log total revenue on.
     * @throws IOException is thrown if something goes wrong creating the file.
     */
    public TotalRevenueFileOutput() throws IOException {
        FileWriter file = new FileWriter("total_revenue.txt");
        this.revenueWriter = new PrintWriter(file, true);
    }

    /**
     * Prints to the file the total revenue.
     * @param totalRevenue The amount of revenue of completed sale.
     */
    @Override
    public void newSale(double totalRevenue) {
        this.totalRevenue += totalRevenue;
        revenueWriter.println("Total revenue for store: " + totalRevenue + " kr");
    }
}
