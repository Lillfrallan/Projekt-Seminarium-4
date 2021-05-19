package se.kth.iv1350.retailstore.model;

import se.kth.iv1350.retailstore.integration.DiscountCatalogDTO;
import se.kth.iv1350.retailstore.util.ItemInformation;
import java.util.ArrayList;

class Discount {
    private DiscountCatalogDTO discountCatalogDTO;
    private SaleInformation saleInformation;


    Discount(DiscountCatalogDTO discountCatalogDTO, SaleInformation saleInformation){
        this.discountCatalogDTO = discountCatalogDTO;
        this.saleInformation = saleInformation;
    }

    void applyDiscount (){
        ArrayList <ItemInformation> items = saleInformation.getListOfItemsInSale();
        for (ItemInformation itemInSale : items){
            findItemToDiscount(itemInSale.getItemInList());
        }
    }

    private void findItemToDiscount(Item itemToDiscount){
        ArrayList <ItemInformation> itemsWithDiscount = discountCatalogDTO.getItemsWithDiscount();
        for(ItemInformation discountedItem : itemsWithDiscount){
            if (itemToDiscount.getIdentifier().equals(discountedItem.getItemInList().getIdentifier())){
                setDiscountedPrice(itemToDiscount, discountedItem.getDiscountMultiplier());
            }
        }
    }

    private void setDiscountedPrice(Item itemToDiscount, double multiplier){
        itemToDiscount.setDiscountedItemPrice(multiplier);
    }
}
