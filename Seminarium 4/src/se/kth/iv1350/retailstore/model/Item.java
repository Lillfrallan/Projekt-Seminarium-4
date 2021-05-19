package se.kth.iv1350.retailstore.model;

/**
 * Represents an item.
 */
public class Item {
    private final String itemName;
    private final String identifier;
    private double itemPrice;
    private final double vatRate;

    public Item(String itemName, String identifier, double itemPrice, double vatRate){
        this.itemName = itemName;
        this.identifier = identifier;
        this.itemPrice = itemPrice;
        this.vatRate = vatRate;
    }
    /**
     * Creates an instance of an item.
     * @param itemInInventory Contains the specifics of an item.
     */
    public Item (Item itemInInventory){
        this.itemName = itemInInventory.getItemName();
        this.identifier = itemInInventory.getIdentifier();
        this.itemPrice = itemInInventory.getItemPrice();
        this.vatRate = itemInInventory.getVatRate();
    }

    /**
     * Gets the name of item.
     * @return The name of item.
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * Gets the identifier of item.
     * @return The identifier of item.
     */
    public String getIdentifier(){
        return this.identifier;
    }

    /**
     * Gets the price of item.
     * @return The price of item.
     */
    public double getItemPrice(){
        return this.itemPrice;
    }

    /**
     * Gets the VAT rate of item.
     * @return The VAT rate of item.
     */
    public double getVatRate(){
        return this.vatRate;
    }

    void setDiscountedItemPrice(double itemPriceMultiplier) {
        this.itemPrice *= itemPriceMultiplier;
    }

    /**
     * Creates an instance of <code>String</code>
     * @return The <code>String</code> to be printed.
     */
    public String toString() {
        return (itemName + "\t\t" + itemPrice + "kr ");
    }
}
