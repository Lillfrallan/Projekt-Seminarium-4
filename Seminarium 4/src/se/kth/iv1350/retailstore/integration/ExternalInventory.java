package se.kth.iv1350.retailstore.integration;

import se.kth.iv1350.retailstore.model.Item;
import se.kth.iv1350.retailstore.model.ItemNotFoundException;
import se.kth.iv1350.retailstore.util.ItemInformation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a storage of goods.
 */
public class ExternalInventory {
    private final ArrayList<ItemInformation> itemInInventory;

    /**
     * Creates and initializes an inventory.
     * Used mainly for simulation purposes.
     */
    public ExternalInventory(){
        this.itemInInventory = initializeStock();
    }

    /**
     * Searches for a matching item.
     * @param identifier The identifier that has been scanned.
     * @return Return an instance of <code>Item</code>. If not found null is returned.
     */
    public Item searchItem (String identifier) throws ItemNotFoundException, ServerConnectionException{
        if (identifier.equals("no connection")){
            throw new ServerConnectionException("Connection to server is down.");
        }

        for (ItemInformation itemInInventory : itemInInventory){
            if(identifier.equals(itemInInventory.getItemInList().getIdentifier())) {
                return new Item(itemInInventory.getItemInList());
            }
        }
        throw new ItemNotFoundException("item id " + identifier + "Not found in inventory");
    }

    /**
     * Updates inventory.
     * @param boughtItems The list that contains the value of quantity to update with in <code>ItemList</code>.
     */
    public void updateInventory (ArrayList<ItemInformation> boughtItems){
        for (ItemInformation boughtItem : boughtItems){
            for (ItemInformation itemInStock : itemInInventory){
                if (boughtItem.getItemInList().getIdentifier().equals(itemInStock.getItemInList().getIdentifier())){
                    itemInStock.updateQuantityInStock(boughtItem.getQuantity());
                }
            }
        }
    }

    /**
     * Creates an instance of <code>StringBuilder</code> and appends
     * the whole list of goods in stock.
     * @return The instance as <code>String</code>.
     */
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Items in stock\n");
        for (ItemInformation itemInSale : itemInInventory) {

            builder.append(itemInSale.toString());

        }

        return builder.toString();
    }

    /**
     * Gets the whole list of goods in stock.
     * @return The list of goods.
     */
    public ArrayList<ItemInformation> getListOfItemsInStock() {
        return this.itemInInventory;
    }

    private ArrayList<ItemInformation> initializeStock(){
        int quantity = 10;
        Item[] items = new Item[5];
        items[0] = new Item("banana", "100", 5, 1.12);
        items[1] = new Item("apple", "150", 4, 1.12);
        items[2] = new Item("pencil", "200", 15, 1.25);
        items[3] = new Item("eraser", "250", 10, 1.25);
        items[4] = new Item("lottery ticket", "300", 25, 1.25);

        ItemInformation[] ItemInformation = new ItemInformation[5];

        for (int index = 0; index < items.length; index++){
            ItemInformation[index] = new ItemInformation(items[index],quantity);
        }

        return new ArrayList<>(Arrays.asList(ItemInformation));
    }
}
