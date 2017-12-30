package util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by charlie on 23/12/17.
 */
public class FormatParser {

    private static final DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static LocalDateTime parseToLocalDateTime(String timestamp){
        LocalDateTime dateTime = null;
        try{
            dateTime =  LocalDateTime.parse(timestamp,timestampFormatter);
        }catch (DateTimeParseException e){
            System.out.printf("timestamp '%s' format error, make sure follow the pattern yyyy-MM-dd'T'HH:mm:ss\n", timestamp);
            System.exit(-1);
        }
        return dateTime;
    }

    public static LocalDate parseToLocalDate(String date){
        LocalDate localDate = null;
        try{
            localDate =  LocalDate.parse(date);
        }catch (DateTimeParseException e){
            System.out.printf("given date '%s' format error, make sure follow the pattern 'yyyy-MM-dd'\n", date);
            System.exit(-1);
        }
        return localDate;
    }

    public static BigDecimal parseToBigDecimal(String amount){
        BigDecimal paymentAmount = null;
        try{
            paymentAmount = new BigDecimal(amount);
        }catch (NumberFormatException e){
            System.out.printf("price '%s' format error, make sure follow the pattern 'dollars.cents'\n", amount);
            System.exit(-1);
        }
        return paymentAmount;
    }
}
