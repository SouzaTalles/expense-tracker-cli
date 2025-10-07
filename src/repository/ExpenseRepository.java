package repository;

import expensefolder.Expense;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExpenseRepository {

    public static void addExpense(Expense expense) {
        File file = new File("expense.csv");
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
}
