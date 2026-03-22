package view;

import controller.ExpenseManager;
import exception.InvalidTransactionException;
import model.Expense;
import model.Income;
import model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuView {

    private ExpenseManager manager;
    private Scanner sc;

    public MenuView(ExpenseManager manager) {
        this.manager = manager;
        this.sc = new Scanner(System.in);
    }

    public void showMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n===== Smart Expense Manager =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Transactions");
            System.out.println("4. View Balance");
            System.out.println("5. Filter by Category");
            System.out.println("6. Remove Transaction");
            System.out.println("0. Save & Exit");
            System.out.print("Choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1: handleAddIncome();   break;
                case 2: handleAddExpense();  break;
                case 3: handleViewAll();     break;
                case 4: handleBalance();     break;
                case 5: handleFilter();      break;
                case 6: handleRemove();      break;
                case 0:
                    manager.saveAll();
                    System.out.println("Data saved. Bye!");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    private void handleAddIncome() {
        try {
            System.out.print("Amount: ");
            double amt = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Category (e.g. Salary, Freelance): ");
            String cat = sc.nextLine().trim();
            System.out.print("Note: ");
            String note = sc.nextLine().trim();

            Income inc = new Income(amt, cat, note, LocalDate.now());
            manager.addTransaction(inc);
            System.out.println("Income added!");

        } catch (NumberFormatException e) {
            System.out.println("Bad amount entered.");
        } catch (InvalidTransactionException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }

    private void handleAddExpense() {
        try {
            System.out.print("Amount: ");
            double amt = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Category (e.g. Food, Travel, Bills): ");
            String cat = sc.nextLine().trim();
            System.out.print("Note: ");
            String note = sc.nextLine().trim();

            Expense exp = new Expense(amt, cat, note, LocalDate.now());
            manager.addTransaction(exp);
            System.out.println("Expense added!");

        } catch (NumberFormatException e) {
            System.out.println("Bad amount entered.");
        } catch (InvalidTransactionException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }

    private void handleViewAll() {
        List<Transaction> all = manager.getAllTransactions();
        if (all.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("\n--- All Transactions ---");
        for (int i = 0; i < all.size(); i++) {
            System.out.println(i + ". " + all.get(i));
        }
    }

    private void handleBalance() {
        System.out.printf("Current Balance: Rs. %.2f%n", manager.getBalance());
    }

    private void handleFilter() {
        System.out.print("Enter category to search: ");
        String cat = sc.nextLine().trim();
        List<Transaction> res = manager.filterByCategory(cat);
        if (res.isEmpty()) {
            System.out.println("Nothing found for: " + cat);
        } else {
            for (Transaction t : res) {
                System.out.println(t);
            }
            System.out.printf("Total: Rs. %.2f%n", manager.totalByCategory(cat));
        }
    }

    private void handleRemove() {
        handleViewAll();
        System.out.print("Enter index to remove: ");
        try {
            int idx = Integer.parseInt(sc.nextLine().trim());
            manager.removeTransaction(idx);
        } catch (NumberFormatException e) {
            System.out.println("Invalid index.");
        }
    }
}