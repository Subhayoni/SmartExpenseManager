package model;

import java.time.LocalDate;

public class Expense extends Transaction {

    private static final long serialVersionUID = 4L;

    // expense needs a sub-category maybe later, keeping it simple for now
    public Expense(double amount, String category, String note, LocalDate date) {
        super(amount, category, note, date);
    }

    @Override
    public String getType() {
        return "EXPENSE";
    }
}