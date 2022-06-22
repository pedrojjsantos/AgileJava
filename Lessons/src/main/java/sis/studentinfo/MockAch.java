package sis.studentinfo;

import com.jimbob.ach.*;
import org.junit.Assert;

import java.util.Date;

class MockAch implements Ach {
    static final String ABA = "102000012";
    static final String ACCOUNT_NUMBER = "194431518811";

    public AchResponse issueDebit(
            AchCredentials credentials, AchTransactionData data) {
        Assert.assertEquals(ACCOUNT_NUMBER, data.account);
        Assert.assertEquals(ABA, data.aba);
        AchResponse response = new AchResponse();
        response.timestamp = new Date();
        response.traceCode = "1";
        response.status = AchStatus.SUCCESS;
        return response;
    }
    public AchResponse markTransactionAsNSF(AchCredentials credentials,
                                            AchTransactionData data,
                                            String traceCode) {
        return null;
    }
    public AchResponse refundTransaction(AchCredentials credentials,
                                         AchTransactionData data,
                                         String traceCode) {
        return null;}
    public AchResponse issueCredit(AchCredentials credentials,
                                   AchTransactionData data) {
        return null;
    }
    public AchResponse voidSameDayTransaction(
            AchCredentials credentials,
            AchTransactionData data,
            String traceCode) {
        return null;
    }
    public AchResponse queryTransactionStatus(AchCredentials credentials,
                                              AchTransactionData data,
                                              String traceCode) {
        return null;
    }
}
