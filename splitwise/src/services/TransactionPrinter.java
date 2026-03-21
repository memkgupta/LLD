package services;

import entities.Transaction;

public class TransactionPrinter {

    public static void printTransactions(String groupName, String type, Iterable<Transaction> txns) {
        for (Transaction t : txns) {
            System.out.println(
                    "[" + groupName + "][" + type + "] " + t.toString()
            );
        }
    }
}