package main.java;

import main.java.model.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import static main.java.core.TransactionValidator.detectFraudCreditCards;
import static main.java.util.FormatParser.parseToBigDecimal;
import static main.java.util.FormatParser.parseToLocalDate;
import static main.java.util.TransactionFileParser.parseFile;

public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the path of transaction file: ");
        List<Transaction> trans = parseFile(reader.nextLine());
        System.out.println("Enter the date: ");
        LocalDate date = parseToLocalDate(reader.nextLine());
        System.out.println("Enter the price threshold: ");
        BigDecimal priceThreshold = parseToBigDecimal(reader.nextLine());

        List<String> res = detectFraudCreditCards(trans,date,priceThreshold);
        System.out.println("Identified fraudulent hashed credit card numbers are as the following: ");
        res.forEach(System.out :: println);
    }

}
