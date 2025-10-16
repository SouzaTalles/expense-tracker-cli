package repository;

import expensefolder.Expense;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {

    private static final File FILE = new File("expense.csv");

    public static boolean addExpense(Expense expense) {
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
            added = true;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save expense to file.", e);
        }
        return added;
    }

    public static boolean deleteExpense(int id) {
        boolean delete = false;
        if (!FILE.exists()) {
            return false;
        }
        try {
            List<String> dados = readAll();
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            for (int i = 0; i < dados.size(); i++) {
                String[] split = dados.get(i).split(",");
                if (split.length < 4) continue;
                if (split[0].equals("ID") || Integer.parseInt(split[0]) != id) {
                    bw.write(dados.get(i));
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();
            delete = true;
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete expense in file.", e);
        }
        return delete;
    }

    public static List<String> listExpense() {
        try {
            return readAll();
        } catch (IOException e) {
            throw new RuntimeException("Error reading expenses from file.", e);
        }
    }

    public static void updateExpense(int id, String description, double amount) {
        try {
            List<String> dados = readAll();
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));

            for (int i = 0; i < dados.size(); i++) {
                String[] split = dados.get(i).split(",");
                if (split.length < 4) continue;
                if (split[0].equals("ID") || Integer.parseInt(split[0]) != id) {
                    bw.write(dados.get(i));
                    bw.newLine();
                } else {
                    bw.write(id + "," + split[1] + "," + description + "," + amount);
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException("Error updating expense in file.", e);
        }
    }

    public static double totalExpense() {
        double valor;
        double somatotal = 0;
        try {
            List<String> dados = readAll();
            for (String dado : dados) {
                String[] split = dado.split(",");
                if (split.length < 4) continue;
                if (split[3].equals("AMOUNT")) continue;
                valor = Double.parseDouble(split[3]);
                somatotal += valor;
            }
            return somatotal;
        } catch (IOException e) {
            throw new RuntimeException("Error calculating total expenses.", e);
        }
    }

    public static double totalMonthExpense(int month) {
        double valor;
        double somatotal = 0;

        try {
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
        } catch (IOException e) {
            throw new RuntimeException("Error calculating total expenses.", e);
        }
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

    public static double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid amount. Please provide a valid number. Type \"expense-tracker help\" for a list of commands.");
        }
    }

    public static int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid amount. Please provide a valid number. Type \"expense-tracker help\" for a list of commands.");
        }
    }
}
