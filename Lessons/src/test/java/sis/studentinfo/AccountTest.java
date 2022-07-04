package sis.studentinfo;

import com.jimbob.ach.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

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
        account.setAch(createMockAch(AchStatus.SUCCESS));
        final BigDecimal amount = new BigDecimal("50.00");
        account.transferFromBank(amount);
        assertEquals(amount, account.getBalance());
    }

    @Test
    public void testFailedTransferFromBank() {
        account.setAch(createMockAch(AchStatus.FAILURE));
        final BigDecimal amount = new BigDecimal("50.00");
        account.transferFromBank(amount);
        assertEquals(new BigDecimal("0.00"), account.getBalance());
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

    private Ach createMockAch(AchStatus status) {
        return new MockAch() {
            @Override
            public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data) {
                assertEquals(AccountTest.ACCOUNT_NUMBER, data.account);
                assertEquals(AccountTest.ABA, data.aba);
                AchResponse response = new AchResponse();
                response.timestamp = new Date();
                response.traceCode = "1";
                response.status = status;
                return response;
            }
        };
    }


    @Test
    public void testWithdraw() throws Exception {
        account.credit(new BigDecimal("100.00"));account.withdraw(new BigDecimal("40.00"));
        assertEquals(new BigDecimal("60.00"), account.getBalance());
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        account.credit(new BigDecimal("100.00"));
        account.withdraw(new BigDecimal("140.00"));
        assertEquals(new BigDecimal("100.00"), account.getBalance());
    }
}
