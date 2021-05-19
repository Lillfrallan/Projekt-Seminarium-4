package se.kth.iv1350.retailstore.model;

/**
 * Exception related to items.
 */
public class ItemNotFoundException extends Exception{
    /**
     * Is thrown when an identifier is unknown.
     * @param message Information about exception.
     */
    public ItemNotFoundException(String message){
        super(message);
    }
}
