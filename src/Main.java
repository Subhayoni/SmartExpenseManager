import controller.ExpenseManager;
import view.MenuView;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Expense Manager...");
        ExpenseManager manager = new ExpenseManager();
        MenuView view = new MenuView(manager);
        view.showMenu();
    }
}