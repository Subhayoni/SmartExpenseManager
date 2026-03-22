package model;

import java.io.Serializable;
import java.time.LocalDate;

// base class for both income and expense — need to keep this abstract
public abstract class Transaction implements Serializable {

    private static final long serialVersionUID = 2L;

    private double amount;
    private String category;
    private String note;
    private LocalDate date;

    public Transaction(double amount, String category, String note, LocalDate date) {
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    // subclasses must say what type they are
    public abstract String getType();

    public double getAmount()      { return amount; }
    public String getCategory()    { return category; }
    public String getNote()        { return note; }
    public LocalDate getDate()     { return date; }

    @Override
    public String toString() {
        return "[" + getType() + "] " + category + " | Rs." + amount + " | " + date + " | " + note;
    }
}