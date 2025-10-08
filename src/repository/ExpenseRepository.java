package repository;

import expensefolder.Expense;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {

    private static File file = new File("expense.csv");

    public static void addExpense(Expense expense) {
        boolean fileExists = file.exists();
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteExpense(int id) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> listExpense() {
        List<String> dados = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                dados.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dados;
    }
}
