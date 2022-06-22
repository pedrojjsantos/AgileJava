package sis.studentinfo;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AccountTest {
    static final String ABA = "102000012";
    static final String ACCOUNT_NUMBER = "194431518811";
    private Account account;

    @Before
    public void setUp() {
        account = new Account();
        account.setBankAba(ABA);
        account.setBankAccountNumber(ACCOUNT_NUMBER);
        account.setBankAccountType(Account.BankAccountType.CHECKING);
    }

    @Test
    public void testTransferFromBank() {
        account.setAch(new com.jimbob.ach.JimBobAch()); // uh-oh
        final BigDecimal amount = new BigDecimal("50.00");
        account.transferFromBank(amount);
        assertEquals(amount, account.getBalance());
    }

    @Test
    public void testTransactions() {
        account.credit(new BigDecimal("0.10"));
        account.credit(new BigDecimal("11.00"));
        assertEquals(new BigDecimal("11.10"), account.getBalance());
    }

    @Test
    public void testTransactionAverage() {
        account.credit(new BigDecimal("0.10"));
        account.credit(new BigDecimal("11.00"));
        account.credit(new BigDecimal("2.99"));
        assertEquals(new BigDecimal("4.70"), account.transactionAverage());
    }
}
