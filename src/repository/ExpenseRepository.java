package repository;

import expensefolder.Expense;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {

    private static final File FILE = new File("expense.csv");

    public static boolean addExpense(Expense expense) throws IOException {
        boolean fileExists = FILE.exists();
        boolean added;
        try (FileWriter fw = new FileWriter(FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            if (!fileExists) {
                bw.write("ID,DATE,DESCRIPTION,AMOUNT");
                bw.newLine();
                bw.flush();
            }
            bw.write(expense.toString());
            bw.newLine();
            bw.flush();
        }
        return true;
    }

    public static boolean deleteExpense(int id) throws IOException { // Adicione 'throws IOException'
        boolean delete = false;
        if (!FILE.exists()) {
            return false;
        }
        List<String> dados = readAll();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (int i = 0; i < dados.size(); i++) {
                String[] split = dados.get(i).split(",");
                if (split.length < 4) continue;
                if (split[0].equals("ID") || Integer.parseInt(split[0]) != id) {
                    bw.write(dados.get(i));
                    bw.newLine();
                }
            }
            delete = true;
        }
        return delete;
    }

    public static List<String> listExpense() throws IOException {
        return readAll();
    }

    public static void updateExpense(int id, String description, double amount) throws IOException {
        List<String> dados = readAll();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));) {
            for (String dado : dados) {
                String[] split = dado.split(",");
                if (split.length < 4) continue;
                if (split[0].equals("ID") || Integer.parseInt(split[0]) != id) {
                    bw.write(dado);
                    bw.newLine();
                } else {
                    bw.write(id + "," + split[1] + "," + description + "," + amount);
                    bw.newLine();
                }
            }
        }
    }

    public static double totalExpense() throws IOException {
        double valor;
        double somatotal = 0;
        List<String> dados = readAll();
        for (String dado : dados) {
            String[] split = dado.split(",");
            if (split.length < 4) continue;
            if (split[3].equals("AMOUNT")) continue;
            valor = Double.parseDouble(split[3]);
            somatotal += valor;
        }
        return somatotal;
    }

    public static double totalMonthExpense(int month) throws IOException {
        double valor;
        double somatotal = 0;
        List<String> dados = readAll();
        for (String dado : dados) {
            String[] split = dado.split(",");
            if (split.length < 4) continue;
            if (!split[1].equals("DATE")) {
                String[] date = split[1].split("/");
                if (Integer.parseInt(date[1]) == month) {
                    valor = Double.parseDouble(split[3]);
                    somatotal += valor;
                }
            }
        }
        return somatotal;
    }

    private static List<String> readAll() throws IOException {
        List<String> dados = new ArrayList<>();
        if (!FILE.exists()) return dados;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                dados.add(line);
            }
        }
        return dados;
    }


}
