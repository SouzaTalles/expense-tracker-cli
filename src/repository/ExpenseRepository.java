package repository;

import expensefolder.Expense;

import java.io.*;

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

    public static void readExpense() {
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
