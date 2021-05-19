package se.kth.iv1350.retailstore.model;

import java.time.LocalDateTime;

class ReceiptDTO {
    private final SaleInformation saleInformation;
    private final LocalDateTime timeOfSale;
    private final String storeName = "KomåköP";
    private final String address = "Köpmannagatan 1, Köpköping";

    ReceiptDTO (SaleInformation saleInformation){
        this.saleInformation = saleInformation;
        this.timeOfSale = LocalDateTime.now();
    }

    /**
     * Creates an instance of <code>String</code>
     * @return The <code>String</code> to be printed.
     */
     public String toString() {
        String receipt = "----------RECEIPT----------\n" +
                "Time of sale: " + timeOfSale + "\n" +
                "Store: " + storeName + "\n" +
                "Adress: " + address + "\n" +
                saleInformation.toString() +
                "Total VAT: " + saleInformation.getTotalVAT() + "kr\n" +
                "------END-OF-RECEIPT------\n";
        return receipt;
    }
}
