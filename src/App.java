public class App {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid arguments. Type \"expense-tracker help\" for a list of commands.");
        }

        switch (args[1]) {
            case "help":
                System.out.println(help);
                break;
            case "add":
                break;
            case "delete":
                break;
            case "list":
                break;
            case "summary":
                if (args.length == 2) {

                } else if (args.length == 4) {
                    switch (args[3]) {
                        case "1" -> System.out.println("mes 1");
                        case "2" -> System.out.println("mes 2");
                        case "3" -> System.out.println("mes 3");
                        case "4" -> System.out.println("mes 4");
                        case "5" -> System.out.println("mes 5");
                        case "6" -> System.out.println("mes 6");
                        case "7" -> System.out.println("mes 7");
                        case "8" -> System.out.println("mes 8");
                        case "9" -> System.out.println("mes 9");
                        case "10" -> System.out.println("mes 10");
                        case "11" -> System.out.println("mes 11");
                        case "12" -> System.out.println("mes 12");
                    }
                } else {
                    throw new IllegalArgumentException("Invalid arguments. Type \"help\" for a list of commands.");
                }
                break;
        }


    }

    private static final String help = """
             -----------------------------------------------------------------------------------------
            | Add expense:             expense-tracker add --description <description> --amount <value>  |
            | Delete expense:          expense-tracker delete --id <id>                               |
            | List expenses:           expense-tracker list                                           | 
            | Total expenses:          expense-tracker summary                                        |
            | Total monthly expenses:  expense-tracker summary --month <number month>                 |
             -----------------------------------------------------------------------------------------
            """;
}