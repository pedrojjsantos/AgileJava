package sis.studentinfo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {
    private BigDecimal balance = new BigDecimal("0.00");
    private int transactionCount = 0;

    public BigDecimal getBalance() {
        return balance;
    }

    public void credit(BigDecimal amount) {
        balance = balance.add(amount);
        transactionCount++;
    }

    public BigDecimal transactionAverage() {
        BigDecimal count = new BigDecimal(transactionCount);
        return (transactionCount == 0) ?
                balance : balance.divide(count, RoundingMode.HALF_UP) ;
    }
}
