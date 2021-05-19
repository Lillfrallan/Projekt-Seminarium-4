package se.kth.iv1350.retailstore.controller;

import se.kth.iv1350.retailstore.model.*;
import se.kth.iv1350.retailstore.integration.*;
import se.kth.iv1350.retailstore.model.ItemNotFoundException;
import se.kth.iv1350.retailstore.model.SaleInformationObserver;
import se.kth.iv1350.retailstore.util.LogHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The program is controlled with Controller.
 * All calls is passed through here are from called from <Code>View</Code>
 */
public class Controller {
    private final ExternalAccount externalAccount;
    private final ExternalInventory externalInventory;
    private final CashRegister cashRegister;
    private final MemberCatalog memberCatalog;
    private final DiscountCatalogDTO discountCatalogDTO;
    private ReceiptPrinter receiptPrinter;
    private SaleInformation saleInformation;
    private final List<SaleInformationObserver> saleInformationObservers = new ArrayList<>();
    private LogHandler logHandler;
    /**
     * Creates new instance.
     */
    public Controller() throws IOException {
        this.externalAccount = new ExternalAccount();
        this.externalInventory = new ExternalInventory();
        this.cashRegister = new CashRegister();
        this.receiptPrinter = new ReceiptPrinter();
        this.memberCatalog = new MemberCatalog();
        this.discountCatalogDTO = new DiscountCatalogDTO();
        this.receiptPrinter = new ReceiptPrinter();
        this.logHandler = new LogHandler();
    }

    /**
     * Creates a new instance of a sale.
     */
    public void startNewSale(){
        this.saleInformation = new SaleInformation();
        saleInformation.addSaleObservers(saleInformationObservers);
    }

    /**
     * Searches the inventory for the scanned barcode and adds the item to sale.
     * @param identifier The <Code>String</Code> to identify an existing object with.
     * @param quantity The number of objects to be added.
     * @return  The updated <code>SaleInformation</code> of sale to print.
     * @throws ItemNotFoundException if the item identifier is unknown.
     * @throws ServerConnectionException if the connection to the server is down.
     */
    public SaleInformation addItem (String identifier, int quantity) throws ItemNotFoundException,
                                                                        ServerConnectionException {
        try{
            Item item = externalInventory.searchItem(identifier);
            saleInformation.addItem(item, quantity);
            return saleInformation;
        }catch (ServerConnectionException e){
            logHandler.logException(e);
            throw e;
        }
    }

    /**
     * A discount request is made and confirms membership in <code>MemberCatalog</code>
     * and forwards the result and a <code>DiscountCatalogDTO</code> the sale.
     * @param memberID The <code>String</code> to search membership with.
     * @return  The updated <code>SaleInformation</code> of sale to print.
     */
    public SaleInformation discountRequest (String memberID){
        boolean isMember = memberCatalog.searchMember(memberID);
        saleInformation.applyDiscount(discountCatalogDTO, isMember);
        return saleInformation;
    }

    /**
     * Adds the paid amount to <code>SaleInformation</code>
     * Sends the total cost of sale to <code>CashRegister</code>
     * Adds sale to <code>ExternalAccount</code>
     * Updates the goods left in stock.
     * @param amountPaid The amount paid from customer.
     * @return  The updated <code>SaleInformation</code> of sale to print.
     */
    public SaleInformation addPay (double amountPaid){
        saleInformation.updatePayment(amountPaid);
        cashRegister.updateRegister(saleInformation.getTotalCost());
        externalAccount.addSaleToAccount(saleInformation);
        externalInventory.updateInventory(saleInformation.getListOfItemsInSale());
        return saleInformation;
    }

    /**
     * Fetches in <code>SaleInformation</code> the receipt to send for printing.
     */
    public void printReceipt (){
         receiptPrinter.printReceipt(saleInformation.printReceipt());
    }

    /**
     * Adds an observer to the list.
     * @param saleInformationObserver The observer to be added.
     */
    public void addSaleObserver(SaleInformationObserver saleInformationObserver) {
        saleInformationObservers.add(saleInformationObserver);
    }
}
