package repository;

import expensefolder.Expense;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {

    private static File file = new File("expense.csv");
    private static List<String> dados = new ArrayList<>();

    public static boolean addExpense(Expense expense) {
        boolean fileExists = file.exists();
        boolean added = false;
        try (FileWriter fw = new FileWriter(file, true);
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
        if (!file.exists()) {
            return false;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> dados = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                dados.add(line);
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < dados.size(); i++) {
                String[] split = dados.get(i).split(",");
                if (split[0].equals("ID") || Integer.parseInt(split[0]) != id) {
                    bw.write(dados.get(i));
                    bw.newLine();
                }
                bw.flush();
            }
            bw.close();
            delete = true;
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete expense in file.", e);
        }
        return delete;
    }

    public static List<String> listExpense() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                dados.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading expenses from file.", e);
        }
        return dados;
    }

    public static void updateExpense(int id, String description, String amount) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> dados = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                dados.add(line);
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < dados.size(); i++) {
                String[] split = dados.get(i).split(",");
                if (split[0].equals("ID") || Integer.parseInt(split[0]) != id) {
                    bw.write(dados.get(i));
                    bw.newLine();
                } else {
                    bw.write(id + "," + split[1] + "," + description + "," + amount);
                    bw.newLine();
                }
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException("Error updating expense in file.", e);
        }
    }

    public static double totalExpense() {
        double valor;
        double somatotal = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                dados.add(line);
            }
            for (String dado : dados) {
                String[] split = dado.split(",");
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
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                dados.add(line);
            }
            for (String dado : dados) {
                String[] split = dado.split(",");
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
}
