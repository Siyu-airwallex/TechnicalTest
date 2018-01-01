package core;

import junitx.framework.ListAssert;
import model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static core.TransactionValidator.detectFraudCreditCards;
/**
 * Created by charlie on 28/12/17.
 */

@RunWith(Parameterized.class)
public class TransactionValidatorTest {

    private List<Transaction> transactions;
    private LocalDate date;
    private BigDecimal threshold;
    private List<String> expectedFraudCards;

    public TransactionValidatorTest(List<Transaction> transactions, LocalDate date,
                                    BigDecimal threshold, List<String> expectedFraudCards){
        this.transactions = transactions;
        this.date = date;
        this.threshold = threshold;
        this.expectedFraudCards = expectedFraudCards;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Transaction trans1 = new Transaction("001", LocalDateTime.parse("2008-04-20T13:15:54"), new BigDecimal("44.9"));
        Transaction trans2 = new Transaction("002", LocalDateTime.parse("2008-04-21T11:34:54"), new BigDecimal("68.42"));
        Transaction trans3 = new Transaction("003", LocalDateTime.parse("2008-04-20T21:18:24"), new BigDecimal("78"));
        Transaction trans4 = new Transaction("002", LocalDateTime.parse("2008-04-20T05:45:54"), new BigDecimal("90.2"));
        Transaction trans5 = new Transaction("002", LocalDateTime.parse("2008-04-21T09:25:12"), new BigDecimal("39.84"));
        Transaction trans6 = new Transaction("005", LocalDateTime.parse("2008-04-20T16:35:54"), new BigDecimal("54.56"));
        Transaction trans7 = new Transaction("001", LocalDateTime.parse("2008-04-21T21:43:34"), new BigDecimal("98.23"));
        Transaction trans8 = new Transaction("006", LocalDateTime.parse("2008-04-21T13:15:54"), new BigDecimal("54.48"));
        Transaction trans9 = new Transaction("002", LocalDateTime.parse("2008-04-21T10:15:10"), new BigDecimal("14.5"));
        Transaction trans10 = new Transaction("001", LocalDateTime.parse("2008-04-20T23:25:54"), new BigDecimal("74.0"));
        Transaction trans11 = new Transaction("008", LocalDateTime.parse("2008-04-21T22:15:21"), new BigDecimal("24.46"));
        Transaction trans12 = new Transaction("007", LocalDateTime.parse("2008-04-20T09:23:32"), new BigDecimal("84.48"));
        Transaction trans13 = new Transaction("003", LocalDateTime.parse("2008-04-20T11:24:32"), new BigDecimal("40.5"));
        Transaction trans14 = new Transaction("008", LocalDateTime.parse("2008-04-21T11:34:32"), new BigDecimal("90.80"));
        Transaction trans15 = new Transaction("005", LocalDateTime.parse("2008-04-20T08:01:22"), new BigDecimal("67.99"));

        List<Transaction> transactions = Arrays.asList(trans1, trans2, trans3, trans4, trans5, trans6, trans7,
                                                       trans8, trans9, trans10, trans11, trans12, trans13, trans14, trans15);
        List<String> expected1 = Arrays.asList("005","001", "003");
        List<String> expected2 = Arrays.asList("002", "008");

        return Arrays.asList(new Object[][]{
            {transactions, LocalDate.parse("2008-04-20"), new BigDecimal(100), expected1},
            {transactions, LocalDate.parse("2008-04-21"), new BigDecimal(100), expected2}}
        );
    }

    @Test
    public void testDetectFraudCreditCards(){

        ListAssert.assertEquals(expectedFraudCards, detectFraudCreditCards(transactions,date,threshold));
    }

}
