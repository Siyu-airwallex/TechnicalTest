package test.java.util;

import main.java.model.Transaction;
import main.java.util.FormatParser;
import main.java.util.TransactionFileParser;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by charlie on 27/12/17.
 */

public class TransactionFileParserTest {

    @Test
    public void testParseSingleLine(){
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
}
