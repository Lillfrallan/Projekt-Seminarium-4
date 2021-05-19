package test.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.retailstore.model.CashRegister;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {
    private CashRegister cashRegister = new CashRegister();

    @Test
    void testUpdatePositiveAmount() {
        double amountToPay = 200;
        double expectedResult = cashRegister.getBalance() + amountToPay;
        cashRegister.updateRegister(amountToPay);
        double actualResult = cashRegister.getBalance();

        assertEquals(expectedResult, actualResult, "Wrong update of balance");
    }

    @Test
    void testUpdateNegativeAmount() {
        double amountToPay = -20;
        double expectedResult = cashRegister.getBalance() + amountToPay;
        cashRegister.updateRegister(amountToPay);
        double actualResult = cashRegister.getBalance();

        assertEquals(expectedResult, actualResult, "Wrong update of balance");
    }
}