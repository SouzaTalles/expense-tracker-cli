import service.ExpenseService;

public class App {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
        }

        ExpenseService service = new ExpenseService();

        switch (args[1]) {
            case "help":
                System.out.println(help);
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

    private static final String help = """
             --------------------------------------------------------------------------------------------
            | Add expense:             expense-tracker add --description <description> --amount <value>  |
            | Delete expense:          expense-tracker delete --id <id>                                  |
            | List expenses:           expense-tracker list                                              | 
            | Total expenses:          expense-tracker summary                                           |
            | Total monthly expenses:  expense-tracker summary --month <number month>                    |
             --------------------------------------------------------------------------------------------
            """;
}