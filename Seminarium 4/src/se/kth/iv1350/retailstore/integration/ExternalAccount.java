package se.kth.iv1350.retailstore.integration;

import se.kth.iv1350.retailstore.model.SaleInformation;

import java.util.ArrayList;

/**
 * Represent a log to store different sales
 */
public class ExternalAccount {
    private final ArrayList <SaleInformation> loggedSales;

    /**
     * Creates a new instance.
     */
    public ExternalAccount (){
        this.loggedSales = new ArrayList<SaleInformation>();
    }

    /**
     * Logs sale in list.
     * @param saleInformation The sale to be logged.
     */
    public void addSaleToAccount (SaleInformation saleInformation){
        this.loggedSales.add(saleInformation);
    }
}
