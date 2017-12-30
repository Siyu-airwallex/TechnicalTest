package util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import static org.junit.Assert.assertEquals;


/**
 * Created by charlie on 26/12/17.
 */

public class FormatParserTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void testPositiveParseToLocalDateTime(){
        Object[][] params = new Object[][]{{"1980-09-25T23:45:25", LocalDateTime.of(1980, Month.SEPTEMBER, 25, 23,45,25)},
                                           {"2008-12-01T01:00:30", LocalDateTime.of(2008, Month.DECEMBER, 1, 1,0,30)}};
        for(Object[] tmp : params){
            LocalDateTime res = FormatParser.parseToLocalDateTime(tmp[0].toString());
            assertEquals(res, tmp[1]);
        }
    }

    @Test
    public void testNegativeParseToLocalDateTime(){
        String[] params = new String[]{"1980-09-25F23:45:25", "2008-12-32T01:00:30","2018 12 31T01:00:30" };
        for(String tmp : params){
            exit.expectSystemExitWithStatus(-1);
            FormatParser.parseToLocalDateTime(tmp);
        }
    }

    @Test
    public void testPositiveParseToLocalDate(){
        Object[][] params = new Object[][]{{"1999-11-11", LocalDate.of(1999, Month.NOVEMBER, 11)},
                                          {"2020-05-31", LocalDate.of(2020, Month.MAY, 31)}};
        for(Object[] tmp : params){
            LocalDate res = FormatParser.parseToLocalDate(tmp[0].toString());
            assertEquals(res, tmp[1]);
        }
    }

    @Test
    public void testNegativeParseToLocalDate(){
        String[] params = new String[]{"1980-09-32", "2008 12 32","2018.12.31" };
        for(String tmp : params){
            exit.expectSystemExitWithStatus(-1);
            FormatParser.parseToLocalDate(tmp);
        }
    }

    @Test
    public void testPositiveParseToBigDecimal(){
        Object[][] params = new Object[][]{{"67.83", BigDecimal.valueOf(67.83)},
                                            {"20", BigDecimal.valueOf(20)},
                                            {"85.6", BigDecimal.valueOf(85.6)}};
        for(Object[] tmp : params){
            BigDecimal res = FormatParser.parseToBigDecimal(tmp[0].toString());
            assertEquals(res, tmp[1]);
        }
    }

    @Test
    public void testNegativeParseToBigDeciaml(){
        String[] params = new String[]{"67F", "sfsf", "  " };
        for(String tmp : params){
            exit.expectSystemExitWithStatus(-1);
            FormatParser.parseToBigDecimal(tmp);
        }
    }
}
