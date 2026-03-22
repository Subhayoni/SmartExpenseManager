package controller;

import exception.InvalidTransactionException;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    // main list that stores everything
    private List<Transaction> txnList = new ArrayList<>();

    public void addTransaction(Transaction t) throws InvalidTransactionException {
        // Need to check if amount is negative here
        if (t.getAmount() <= 0) {
            throw new InvalidTransactionException("Amount must be greater than zero.");
        }
        if (t.getCategory() == null || t.getCategory().trim().isEmpty()) {
            throw new InvalidTransactionException("Category can't be empty.");
        }

        txnList.add(t);
        System.out.println("DEBUG: added txn -> " + t); // debug
    }

    public void removeTransaction(int idx) {
        if (idx < 0 || idx >= txnList.size()) {
            System.out.println("Invalid index, nothing removed.");
            return;
        }
        Transaction removed = txnList.remove(idx);
        System.out.println("DEBUG: removed -> " + removed);
    }

    public List<Transaction> getAllTransactions() {
        return txnList;
    }

    // total balance = income - expenses
    public double getBalance() {
        double bal = 0;
        for (Transaction t : txnList) {
            if (t.getType().equals("INCOME")) {
                bal += t.getAmount();
            } else {
                bal -= t.getAmount();
            }
        }
        return bal;
    }
    // filter by category — case insensitive because users never type it right
    public List<Transaction> filterByCategory(String cat) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : txnList) {
            if (t.getCategory().equalsIgnoreCase(cat)) {
                result.add(t);
            }
        }
        System.out.println("DEBUG: found " + result.size() + " for category: " + cat);
        return result;
    }

    // show only expenses or only income
    public List<Transaction> filterByType(String type) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : txnList) {
            if (t.getType().equalsIgnoreCase(type)) {
                result.add(t);
            }
        }
        return result;
    }

    // total spending in a given category
    public double totalByCategory(String cat) {
        double total = 0;
        for (Transaction t : filterByCategory(cat)) {
            total += t.getAmount();
        }
        return total;
    }
}