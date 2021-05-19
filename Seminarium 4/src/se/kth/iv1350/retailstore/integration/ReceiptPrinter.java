package se.kth.iv1350.retailstore.integration;

/**
 * Represents a printer.
 */
public class ReceiptPrinter {
    /**
     * Creates an instance, represented as a printer.
     */
    public ReceiptPrinter(){}

    /**
     * Prints the specified receipt.
     * It prints to <code>System.out</code>.
     *
     * @param receiptDTO The instance of <code>ReceiptDTO</code> to be printed.
     */
    public void printReceipt(String receiptDTO){
        System.out.println(receiptDTO.toString());
    }
}
