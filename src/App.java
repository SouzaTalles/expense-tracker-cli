import expensefolder.Expense;
import repository.ExpenseRepository;

import java.util.List;

public class App {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
        }
        int id;
        double amount;

        switch (args[1]) {
            case "help":
                System.out.println("""
                         ----------------------------------------------------------------------------------------------------------------
                        | Add expense:             expense-tracker add --description <description> --amount <value>                      |
                        | Delete expense:          expense-tracker delete --id <id>                                                      |
                        | List expenses:           expense-tracker list                                                                  | 
                        | Total expenses:          expense-tracker summary                                                               |
                        | Total monthly expenses:  expense-tracker summary --month <number month>                                        |
                        | Update expense:          expense-tracker update --id <id> --description <new description> --amount <new value> |
                         ----------------------------------------------------------------------------------------------------------------
                        """);
                break;

            case "add":
                if (args.length != 6) throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
                amount = ExpenseRepository.parseDouble(args[5]);
                if (amount <= 0) throw new IllegalArgumentException("Amount must be a positive number. Type \"expense-tracker help\" for a list of commands.");
                Expense expense = new Expense(
                        args[3],
                        amount
                );
                boolean b = ExpenseRepository.addExpense(expense);
                if (b) {
                    System.out.println("Expense added successfully.");
                } else {
                    System.out.println("Expense could not be added.");
                }
                break;

            case "delete":
                if (args.length != 4) throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
                id = ExpenseRepository.parseInt(args[3]);
                boolean b1 = ExpenseRepository.deleteExpense(id);
                if (b1) {
                    System.out.println("Expense deleted successfully.");
                } else {
                    System.out.println("Expense could not be deleted.");
                }
                break;

            case "update":
                if (args.length != 8) throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
                id = ExpenseRepository.parseInt(args[3]);
                amount = ExpenseRepository.parseDouble(args[7]);
                if (amount <= 0) throw new IllegalArgumentException("Amount must be a positive number. Type \"expense-tracker help\" for a list of commands.");
                ExpenseRepository.updateExpense(id, args[5], amount);
                break;

            case "list":
                if (args.length != 2) throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
                List<String> strings = ExpenseRepository.listExpense();
                System.out.println("-------------------------------------------------------------");
                for (String string : strings) {
                    if (string != null) {
                        String[] split = string.split(",");
                        System.out.print("| ");
                        for (String s : split) {
                            System.out.printf("%-12s | ", s);
                        }
                        System.out.println();
                        System.out.println("-------------------------------------------------------------");
                    }
                }
                break;

            case "summary":
                if (args.length == 2) {
                    double v = ExpenseRepository.totalExpense();
                    System.out.println("Total expenses: " + v);
                } else if (args.length == 4) {
                    int mes = ExpenseRepository.parseInt(args[3]);
                    double v = ExpenseRepository.totalMonthExpense(mes);
                    System.out.println("Total expenses in month " + mes + ": " + v);
                } else {
                    throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
        }
    }
}