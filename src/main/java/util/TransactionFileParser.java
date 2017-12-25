package main.java.util;

import main.java.model.Transaction;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import static main.java.util.FormatParser.parseToBigDecimal;
import static main.java.util.FormatParser.parseToLocalDateTime;

/**
 * Created by charlie on 23/12/17.
 */
public class TransactionFileParser {

    public static Transaction parseSingleLine(String line) {
        String[] elements = line.split(",");
        if(elements.length != 3) {
            System.out.printf("line '%s' in error format, should be a comma separated string of three elements\n", line);
            System.exit(-1);
        }
        String hashedCardNumber = elements[0].trim();
        LocalDateTime timestamp = parseToLocalDateTime(elements[1].trim());
        BigDecimal paymentAmount = parseToBigDecimal(elements[2].trim());
        return new Transaction(hashedCardNumber, timestamp, paymentAmount);
    }

    public static List<Transaction> parseFile(String fileName) {
        List<Transaction> transactions = null;
        try{
            transactions = Files.lines(Paths.get(fileName))
                            .map(TransactionFileParser:: parseSingleLine)
                            .collect(Collectors.toList());
        }catch (IOException e){
            System.out.printf("file '%s' can not be found or access\n", fileName);
            System.exit(-1);
        }
        return transactions;
    }
}
