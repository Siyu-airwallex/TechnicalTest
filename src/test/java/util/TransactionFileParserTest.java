package util;

import model.Transaction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;
/**
 * Created by charlie on 27/12/17.
 */

public class TransactionFileParserTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void testPositveParseSingleLine(){
        Object[][] params = new Object[][]{{"10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-21T13:15:54, 15.20",
                                            new Transaction("10d7ce2f43e35fa57d1bbf8b1e3", LocalDateTime.parse("2014-04-21T13:15:54"),  new BigDecimal("15.20"))},
                                           {"10d7ce2f43e35fa57d1bbf8b1e7, 2014-04-30T13:15:54, 190.50",
                                            new Transaction("10d7ce2f43e35fa57d1bbf8b1e7", LocalDateTime.parse("2014-04-30T13:15:54"),  new BigDecimal("190.50"))},
                                          };
        for(Object[] tmp : params){
            Transaction res = TransactionFileParser.parseSingleLine(tmp[0].toString());
            assertEquals(res, tmp[1]);
        }
    }

    @Test
    public void testNegativeParseSingleLine(){
        String[] params = new String[]{"10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-21T13:15:54",
                                       "10d7ce2f43e35fa57d1bbf8b1e7, 2014-04-30T13:15:54, 190.50, extra "};
        for(String tmp : params){
            exit.expectSystemExitWithStatus(-1);
            TransactionFileParser.parseSingleLine(tmp);
        }
    }

}
