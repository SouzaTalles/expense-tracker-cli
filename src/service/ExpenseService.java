package service;

import expensefolder.Expense;
import repository.ExpenseRepository;

public class ExpenseService {
    public void Add(String[] ex) {
        Expense expense = new Expense(
                ex[3],
                ex[5]
        );
        ExpenseRepository.addExpense(expense);
    }

    public void Delete(String[] ex) {

    }
}
