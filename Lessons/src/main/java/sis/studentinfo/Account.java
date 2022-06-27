package sis.studentinfo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.jimbob.ach.*;

public class Account implements Accountable {
    private BigDecimal balance = new BigDecimal("0.00");
    private int transactionCount = 0;
    private String bankAba;
    private String bankAccountNumber;
    private BankAccountType bankAccountType;
    private Ach ach;

    public enum BankAccountType {
        CHECKING("ck"),
        SAVINGS("sv");

        private String value;

        BankAccountType(String value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return value;
        }
    }

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

    public void setBankAba(String bankAba) {
        this.bankAba = bankAba;
    }
    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
    public void setBankAccountType(
            Account.BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }
    public void transferFromBank(BigDecimal amount) {
        AchResponse achResponse =
                getAch().issueDebit(createCredentials(), createData(amount));
        if (achResponse.status == AchStatus.SUCCESS)
            credit(amount);
    }

    private AchCredentials createCredentials() {
        AchCredentials credentials = new AchCredentials();
        credentials.merchantId = "12355";
        credentials.userName = "sismerc1920";
        credentials.password = "pitseleh411";
        return credentials;
    }
    private AchTransactionData createData(BigDecimal amount) {
        AchTransactionData data = new AchTransactionData();
        data.description = "transfer from bank";
        data.amount = amount;
        data.aba = bankAba;
        data.account = bankAccountNumber;
        data.accountType = bankAccountType.toString();
        return data;
    }
    private Ach getAch() {
        return ach;
    }void setAch(Ach ach) {
        this.ach = ach;
    }
}
