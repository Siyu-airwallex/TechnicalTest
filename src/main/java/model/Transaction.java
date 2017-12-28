package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * Created by charlie on 23/12/17.
 */
public class Transaction {

    private String hashedCardNumber;
    private LocalDateTime timestamp;
    private BigDecimal paymentAmount;

    public Transaction(String hashedCardNumber, LocalDateTime timestamp, BigDecimal paymentAmount) {
        this.hashedCardNumber = hashedCardNumber;
        this.timestamp = timestamp;
        this.paymentAmount = paymentAmount;
    }

    public String getHashedCardNumber() {
        return hashedCardNumber;
    }

    public void setHashedCardNumber(String hashedCardNumber) {
        this.hashedCardNumber = hashedCardNumber;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "hashedCardNumber='" + hashedCardNumber + '\'' +
                ", timestamp=" + timestamp +
                ", paymentAmount=" + paymentAmount +
                '}';
    }

    @Override
    public boolean equals(Object other){
        if(other == null || !(other instanceof Transaction)) return false;
        Transaction that = (Transaction) other;
        return this.getHashedCardNumber().equals(that.getHashedCardNumber())
                        && this.getTimestamp().equals(that.getTimestamp())
                            && this.paymentAmount.equals(that.paymentAmount);
    }
}
