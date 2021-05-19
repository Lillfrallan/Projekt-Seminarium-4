package se.kth.iv1350.retailstore.util;

import se.kth.iv1350.retailstore.model.Item;

/**
 * Is used for combining different values with <code>Item</code>
 */
public class ItemInformation {
    private Item item;
    private int quantity;
    private double discountMultiplier;

    /**
     * Creates an instance of <code>Item</code> and how many of it.
     * @param item The item to be combined.
     * @param quantity How of that <code>Item</code>
     */
    public ItemInformation (Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Creates an instance of <code>Item</code> and it's discount.
     * Used mainly for simulation purposes.
     * @param item The item to be combined.
     * @param discountMultiplier The multiplier to be combined.
     */
    public ItemInformation (Item item, double discountMultiplier){
        this.item = item;
        this.discountMultiplier = discountMultiplier;
    }

    /**
     * Get the instance of <code>ItemList</code>.
     * @return The instance of <code>ItemList</code>.
     */
    public Item getItemInList(){
        return this.item;
    }

    /**
     * Get the multiplier to discount with.
     * @return The value of the multiplier.
     */
    public double getDiscountMultiplier(){return discountMultiplier;}

    /**
     * Get the quantity of instance.
     * @return The quantity of <code>Item</code>.
     */
    public int getQuantity(){return quantity;}

    /**
     * Updates the quantity left of <code>Item</code> in stock.
     * @param quantity The number of quantity to update with.
     */
    public void updateQuantityInStock (int quantity){
        this.quantity -= quantity;
    }

    /**
     * Increases the quantity of <code>ItemList</code>.
     * @param quantity The quantity of items to add.
     */
    public void addQuantityInSaleList(int quantity){
        this.quantity += quantity;
    }

    /**
     * Appends an instance of item and turns instance into a <code>String</code>.
     * @return The instance as a <code>String</code>
     */
    public String toString(){

        return quantity + "\t\t" + item.toString() + "\n";
    }
}
