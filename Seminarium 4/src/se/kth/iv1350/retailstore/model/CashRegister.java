package se.kth.iv1350.retailstore.model;

/**
 * Represents a cash register.
 */
public class CashRegister {
    private double balance;

    /**
     * Initializes an instance of a cash register
     * Used mainly for simulation purposes.
     */
    public CashRegister () {
        initializeBalance();
    }

    /**
     * Updates the register.
     * @param totalCost the cost of sale to be updated.
     */
    public void updateRegister (double totalCost){
        this.balance += totalCost;
    }

    /**
     * Gets the balance of the cash register
     * @return The balance of cash register
     */
    public double getBalance(){
        return this.balance;
    }

    private void initializeBalance(){
        this.balance = 500;
    }
}
