package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static it.unibo.bank.impl.SimpleBankAccount.MANAGEMENT_FEE;
import static it.unibo.bank.impl.StrictBankAccount.TRANSACTION_FEE;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {


    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;
    private static final double deposit = 100.0;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        mRossi = new AccountHolder("Mario", "Rossi", 1234);
        bankAccount = new StrictBankAccount(mRossi, 0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(0.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(mRossi, bankAccount.getAccountHolder());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        bankAccount.deposit(mRossi.getUserID(), deposit);
        assertEquals(100.0, bankAccount.getBalance());
        assertEquals(1,bankAccount.getTransactionsCount());
        bankAccount.chargeManagementFees(mRossi.getUserID());
        double expectBalance = 100.0 - TRANSACTION_FEE - MANAGEMENT_FEE;
        assertEquals(expectBalance, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
       try{
        bankAccount.withdraw(mRossi.getUserID(),-100.0);
        
       }catch(IllegalArgumentException e){
        System.out.println(e.getMessage());
       }
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        try{
            bankAccount.withdraw(mRossi.getUserID(), 100.0);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
