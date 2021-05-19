package test.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.retailstore.integration.ExternalInventory;
import se.kth.iv1350.retailstore.integration.ServerConnectionException;
import se.kth.iv1350.retailstore.model.Item;
import se.kth.iv1350.retailstore.model.ItemNotFoundException;
import se.kth.iv1350.retailstore.util.ItemInformation;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ExternalInventoryTest {
    private ExternalInventory externalInventory;

    @BeforeEach
    void setUp() {
        externalInventory = new ExternalInventory();
    }

    @AfterEach
    void tearDown() {
        externalInventory = null;
    }

    @Test
    void testSearchExistingItem() {
        try{
            String eraser = "150";
            Item existingItem = externalInventory.searchItem(eraser);
            boolean actualResult = existingItem.getIdentifier().equals(eraser);
            assertTrue(actualResult,"testSearchExistingItem failed");
        }catch (ItemNotFoundException e){
            e.getMessage();
        }catch (ServerConnectionException e){
            e.getMessage();
        }
    }

    @Test
    void testSearchNonExistingItem() {
        try{
            String unknownIdentifier = "255";
            externalInventory.searchItem(unknownIdentifier);
        fail("exception catch failed");
        }catch (ItemNotFoundException e){
            assertTrue(e.getMessage().contains("Not found"));
        }catch (ServerConnectionException e){
            assertFalse(e.getMessage().contains("Connection to server is down"));
        }
    }

    @Test
    void testNoConnectionException() {
        try{
            String lostConnection = "no connection";
            externalInventory.searchItem(lostConnection);
            fail("exception catch failed");
        }catch (ItemNotFoundException e){
            assertFalse(e.getMessage().contains("Not found"));
        }catch (ServerConnectionException e){
            assertTrue(e.getMessage().contains("Connection to server is down"));
        }
    }

    @Test
    void testUpdateInventory() {
        int indexOfItemInStock = 0;
        int noOfItemsToBuy = 3;
        ArrayList<ItemInformation> itemsInStock = externalInventory.getListOfItemsInStock();
        ArrayList<ItemInformation> itemToBuy = new ArrayList<>();
        itemToBuy.add(new ItemInformation(itemsInStock.get(indexOfItemInStock).getItemInList(),3));
        int quantityOfItemsInStock = itemsInStock.get(indexOfItemInStock).getQuantity();
        int expectedQuantityLeftInStock = quantityOfItemsInStock - noOfItemsToBuy;
        externalInventory.updateInventory(itemToBuy);
        int actualNoOfItemsInStock = externalInventory.getListOfItemsInStock().get(indexOfItemInStock).getQuantity();
        assertEquals(actualNoOfItemsInStock,expectedQuantityLeftInStock, "Inventory wasn't correctly updated");
    }
}