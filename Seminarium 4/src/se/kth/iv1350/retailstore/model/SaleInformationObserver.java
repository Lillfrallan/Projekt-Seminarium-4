package se.kth.iv1350.retailstore.model;

/**
 * An interface listening to notifications about completed sales. Implementing classes' methods are
 * invoked when a sale is paid.
 */
public interface SaleInformationObserver {
    /**
     * Invoked when a sale is paid.
     * @param totalRevenue The amount of revenue of completed sale.
     */
    void newSale (double totalRevenue);
}
