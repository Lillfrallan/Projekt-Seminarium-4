package se.kth.iv1350.retailstore.integration;

import se.kth.iv1350.retailstore.model.Item;
import se.kth.iv1350.retailstore.util.ItemInformation;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represent what goods that are discounted.
 */
public class DiscountCatalogDTO {
    private final ArrayList <ItemInformation> itemsWithDiscount;

    /**
     * Creates a new instance and initializes list with discounted goods.
     */
    public DiscountCatalogDTO(){
        this.itemsWithDiscount = initializeDiscountedItems();
    }

    /**
     * Gets the instance of <code>ItemList</code> with discount.
     * @return The instance of discounted Item
     */
    public ArrayList <ItemInformation> getItemsWithDiscount(){
        return itemsWithDiscount;
    }

    private ArrayList<ItemInformation> initializeDiscountedItems(){
        double[] discountMultipliers = {0.8, 1, 1, 0.5, 1};
        Item[] items = new Item[5];
        items[0] = new Item("banana", "100", 5, 1.12);
        items[1] = new Item("apple", "150", 4, 1.12);
        items[2] = new Item("pencil", "200", 15, 1.25);
        items[3] = new Item("eraser", "250", 10, 1.25);
        items[4] = new Item("lottery ticket", "300", 25, 1.25);

        ItemInformation[] ItemInformation = new ItemInformation[5];
        for (int index = 0; index < items.length; index++){
            ItemInformation[index] = new ItemInformation(items[index],discountMultipliers[index]);
        }

        ArrayList<ItemInformation> itemsInStock = new ArrayList<>();
        Collections.addAll(itemsInStock, ItemInformation);
        return itemsInStock;
    }
}
