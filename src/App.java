import expensefolder.Expense;
import repository.ExpenseRepository;

import java.io.IOException;
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
                try {
                    if (args.length != 6)
                        throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
                    amount = parseDouble(args[5]);
                    if (amount <= 0)
                        throw new IllegalArgumentException("Amount must be a positive number. Type \"expense-tracker help\" for a list of commands.");
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
                } catch (IOException e) {
                    System.err.println("Error: Failed to save expense due to a file system problem. Please check permissions or disk space.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (RuntimeException e) {
                    System.err.println("An unexpected error occurred: " + e.getMessage());
                }
                break;

            case "delete":
                try {
                    if (args.length != 4)
                        throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
                    id = parseInt(args[3]);
                    boolean b1 = ExpenseRepository.deleteExpense(id);
                    if (b1) {
                        System.out.println("Expense deleted successfully.");
                    } else {
                        System.out.println("Expense could not be deleted.");
                    }
                } catch (IOException e) {
                    System.err.println("Error: Failed to delete expense due to a file system problem. Please check permissions.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
                break;

            case "update":
                try {
                    if (args.length != 8)
                        throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
                    id = parseInt(args[3]);
                    amount = parseDouble(args[7]);
                    if (amount <= 0)
                        throw new IllegalArgumentException("Amount must be a positive number. Type \"expense-tracker help\" for a list of commands.");
                    ExpenseRepository.updateExpense(id, args[5], amount);
                } catch (IOException e) {
                    System.err.println("Error: Failed to update expense due to a file system problem. Please check permissions.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
                break;

            case "list":
                try {
                    if (args.length != 2)
                        throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
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
                } catch (IOException e) {
                    System.err.println("Error: Failed to read expenses due to a file system problem. Please check permissions.");
                }
                break;

            case "summary":
                try {
                    if (args.length == 2) {
                        double v = ExpenseRepository.totalExpense();
                        System.out.println("Total expenses: " + v);
                    } else if (args.length == 4) {
                        int mes = parseInt(args[3]);
                        double v = ExpenseRepository.totalMonthExpense(mes);
                        System.out.println("Total expenses in month " + mes + ": " + v);
                    } else {
                        throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
                    }
                } catch (IOException e) {
                    System.err.println("Error: Failed to calculate summary due to a file reading problem. Please check file accessibility.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
                break;

            default:
                System.err.println("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
        }
    }

    public static double parseDouble(String s) throws IllegalArgumentException {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount. Please provide a valid number. Type \"expense-tracker help\" for a list of commands.");
        }
    }

    public static int parseInt(String s) throws IllegalArgumentException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount. Please provide a valid number. Type \"expense-tracker help\" for a list of commands.");
        }
    }
}