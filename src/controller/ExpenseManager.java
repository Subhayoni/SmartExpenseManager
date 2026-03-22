package controller;

import exception.InvalidTransactionException;
import model.Transaction;
import persistence.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    private List<Transaction> txnList = new ArrayList<>();

    public ExpenseManager() {
        txnList = FileHandler.loadData();
        System.out.println("DEBUG: manager ready, " + txnList.size() + " txns loaded");
    }

    public void addTransaction(Transaction t) throws InvalidTransactionException {
        // Need to check if amount is negative here
        if (t.getAmount() <= 0) {
            throw new InvalidTransactionException("Amount must be > 0, got: " + t.getAmount());
        }
        if (t.getCategory() == null || t.getCategory().trim().isEmpty()) {
            throw new InvalidTransactionException("Category can't be blank.");
        }
        txnList.add(t);
        System.out.println("DEBUG: txn added, total now = " + txnList.size());
    }

    public void removeTransaction(int idx) {
        if (idx < 0 || idx >= txnList.size()) {
            System.out.println("DEBUG: bad index " + idx + ", size=" + txnList.size());
            System.out.println("Invalid index. No changes.");
            return;
        }
        Transaction removed = txnList.remove(idx);
        System.out.println("DEBUG: removed -> " + removed);
        System.out.println("Removed successfully.");
    }

    public List<Transaction> getAllTransactions() {
        return txnList;
    }

    public double getBalance() {
        double bal = 0;
        for (Transaction t : txnList) {
            if (t.getType().equals("INCOME")) bal += t.getAmount();
            else bal -= t.getAmount();
        }
        // just printing this to verify during testing
        System.out.println("DEBUG: balance = " + bal);
        return bal;
    }

    public List<Transaction> filterByCategory(String cat) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : txnList) {
            if (t.getCategory().equalsIgnoreCase(cat)) result.add(t);
        }
        System.out.println("DEBUG: filterByCategory(" + cat + ") -> " + result.size() + " results");
        return result;
    }

    public List<Transaction> filterByType(String type) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : txnList) {
            if (t.getType().equalsIgnoreCase(type)) result.add(t);
        }
        return result;
    }

    public double totalByCategory(String cat) {
        double total = 0;
        for (Transaction t : filterByCategory(cat)) total += t.getAmount();
        return total;
    }

    public void saveAll() {
        FileHandler.saveData(txnList);
        System.out.println("DEBUG: saveAll() called");
    }
}