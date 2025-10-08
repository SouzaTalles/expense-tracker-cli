import repository.ExpenseRepository;
import service.ExpenseService;

import java.util.List;

public class App {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
        }

        ExpenseService service = new ExpenseService();

        switch (args[1]) {
            case "help":
                System.out.println("""
                     --------------------------------------------------------------------------------------------
                    | Add expense:             expense-tracker add --description <description> --amount <value>  |
                    | Delete expense:          expense-tracker delete --id <id>                                  |
                    | List expenses:           expense-tracker list                                              | 
                    | Total expenses:          expense-tracker summary                                           |
                    | Total monthly expenses:  expense-tracker summary --month <number month>                    |
                     --------------------------------------------------------------------------------------------
                        """);
                break;

            case "add":
                if (args.length != 6) throw new IllegalArgumentException("Invalid arguments. Type \"help\" for a list of commands.");
                service.Add(args);
                break;

            case "delete":
                if (args.length != 4) throw new IllegalArgumentException("Invalid arguments. Type \"help\" for a list of commands.");
                service.delete(args);
                break;

            case "list":
                if (args.length != 2) throw new IllegalArgumentException("Invalid arguments. Type \"help\" for a list of commands.");
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

                } else if (args.length == 4) {
                    int mes = Integer.parseInt(args[3]);
                    // passar o mes como argumento
                } else {
                    throw new IllegalArgumentException("Invalid arguments. Type \"help\" for a list of commands.");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid arguments. Type \"help\" for a list of commands.");
        }


    }

}