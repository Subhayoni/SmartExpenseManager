package model;

import java.time.LocalDate;

// Income is just a Transaction that says its type is "INCOME"
public class Income extends Transaction {

    private static final long serialVersionUID = 3L;

    public Income(double amount, String category, String note, LocalDate date) {
        super(amount, category, note, date);
    }

    @Override
    public String getType() {
        return "INCOME";
    }
}