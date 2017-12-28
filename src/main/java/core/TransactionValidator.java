package core;

import model.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by charlie on 23/12/17.
 */
public class TransactionValidator {

    public static List<String> detectFraudCreditCards(List<Transaction> trans, LocalDate date, BigDecimal threshold){
        Map<String, BigDecimal> sumByCard = new HashMap<>();
        for(Transaction tran : trans){
            if(tran.getTimestamp().toLocalDate().equals(date)){
               String card = tran.getHashedCardNumber();
               BigDecimal currSum = sumByCard.getOrDefault(card, new BigDecimal("0"));
               sumByCard.put(card, currSum.add(tran.getPaymentAmount()));
            }
        }
        return sumByCard.entrySet().stream()
                .filter(record -> record.getValue().compareTo(threshold) == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
