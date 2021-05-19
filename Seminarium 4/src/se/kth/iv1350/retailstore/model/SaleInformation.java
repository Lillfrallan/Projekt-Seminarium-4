package se.kth.iv1350.retailstore.model;

import se.kth.iv1350.retailstore.integration.DiscountCatalogDTO;
import se.kth.iv1350.retailstore.util.ItemInformation;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the information for a sale
 */
public class SaleInformation {
    private List<SaleInformationObserver> saleInformationObservers = new ArrayList<>();
    private final ArrayList <ItemInformation> saleList;
    private double runningTotal;
    private double totalVAT;
    private double amountPaid;
    private double change;

    /**
     * Creates an instance of a sale.
     */
    public SaleInformation (){
        this.saleList = new ArrayList<>();
        this.runningTotal = 0;
        this.totalVAT = 0;
        this.amountPaid = 0;
        this.change = 0;
    }

    /**
     * Adds the list of <code>SaleObservers</code> to the sale.
     * @param observers The list to be added.
     */
    public void addSaleObservers(List<SaleInformationObserver> observers){
        saleInformationObservers.addAll(observers);
    }

    /**
     * Adds an instance of <code>Item</code> to list of sale.
     * If item already exists in sale only the quantity is increased.
     * If item is received as null nothing happens.
     *
     * @param item The item to be added.
     * @param quantity The quantity of that item.
     */
    public void addItem (Item item, int quantity) {
        if (item != null) {
            if (isItemInSaleList(item)) {
                addQuantity(item, quantity);
                updateWholeSale();
            } else {
                this.saleList.add(new ItemInformation(item, quantity));
                updateSale(item, quantity);
            }
        }
    }

    private boolean isItemInSaleList(Item item){
        for (ItemInformation itemInSaleList : this.saleList){
            if (item.getIdentifier().equals(itemInSaleList.getItemInList().getIdentifier())) {
                return true;
            }
        }
        return false;
    }

    private void addQuantity (Item item, int quantity){
        for (ItemInformation itemInSale : this.saleList){
            if (item.getIdentifier().equals(itemInSale.getItemInList().getIdentifier())){
                itemInSale.addQuantityInSaleList(quantity);
            }
        }
    }

    private void updateSale (Item item, int quantity) {
        double priceWithoutVAT = item.getItemPrice();
        double priceWithVAT = priceWithoutVAT * item.getVatRate();
        this.runningTotal += priceWithVAT * quantity;
        this.totalVAT += (priceWithVAT - priceWithoutVAT) * quantity;
    }

    private void updateWholeSale(){
        this.runningTotal = 0;
        this.totalVAT = 0;
        for(ItemInformation itemsInSale : saleList)
            updateSale(itemsInSale.getItemInList(), itemsInSale.getQuantity());
    }

    /**
     * Confirms if customer is member and then will forward incoming information about discounted items
     * to <code>Discount</code>.
     * If not member. Nothing happens.
     * @param discountCatalogDTO The list of discounted items.
     * @param isMember the customer memberID to be evaluated.
     */
    public void applyDiscount(DiscountCatalogDTO discountCatalogDTO, boolean isMember) {
        if (isMember) {
            Discount discount = new Discount(discountCatalogDTO, this);
            discount.applyDiscount();
            updateWholeSale();
        }
    }

    /**
     * Updates the information about payment.
     * @param amountPaid What the customer paid.
     */
    public void updatePayment(double amountPaid){
        this.amountPaid = amountPaid;
        this.change = amountPaid - runningTotal;
        notifyObservers();
    }

    private void notifyObservers(){
        for(SaleInformationObserver saleInformationObserver : saleInformationObservers)
            saleInformationObserver.newSale(runningTotal);
    }

    /**
     * Creates an instance of <code>ReceiptDTO</code>
     * @return An instance of <code>String</code>
     */
    public String printReceipt(){
        ReceiptDTO receiptDTO = new ReceiptDTO(this);
        return receiptDTO.toString();
    }

    /**
     * Creates an instance of <code>String</code>
     * @return The <code>String</code> to be printed.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Qty:     Items:     Price: \n");
        for (ItemInformation itemInSale : saleList) {
            builder.append(itemInSale.toString());
        }
        builder.append("\nAmount paid: ").append(amountPaid).append("kr");
        builder.append("\nTotal change: ").append(change).append("kr");
        builder.append("\nTotal to pay: ").append(runningTotal).append("kr\n");
        return builder.toString();
    }

    /**
     * Gets the list of current items in sale.
     * @return The current items in sale.
     */
    public ArrayList<ItemInformation> getListOfItemsInSale() {
        return this.saleList;
    }

    /**
     * Gets the total cost of sale.
     * @return The total cost of sale.
     */
    public double getTotalCost () {
        return this.runningTotal;
    }

    /**
     * Gets the amount paid by customer.
     * @return The amount paid by customer.
     */
    public double getAmountPaid() {
        return this.amountPaid;
    }

    /**
     * Gets the amount of total VAT.
     * @return The mount of total VAT.
     */
    public double getTotalVAT(){
        return totalVAT;
    }

    /**
     * Gets the customers change.
     * @return The customers change.
     */
    public double getChange() {
        return this.change;
    }
}
