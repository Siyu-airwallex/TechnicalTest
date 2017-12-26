package test.java.util;

import main.java.util.FormatParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by charlie on 26/12/17.
 */

public class FormatParserTest {

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
            Executable parseExep = () -> FormatParser.parseToLocalDateTime(tmp);
            Throwable excepton = assertThrows(DateTimeParseException.class, parseExep);
            System.out.println(excepton.getMessage());
        }
    }


}