package expensefolder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense {
    private final int ID;
    private String description;
    private double amount;
    private LocalDate date;

    private static int nextId;
    private static final DateTimeFormatter dtf;

    static {
        nextId = 1;
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public Expense(String category, double amount) {
        this.ID = nextId++;
        this.description = category;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return "# ID DATE  DESCRIPTION  AMOUNT\n" +
            "# " + ID + date.format(dtf) + description + amount;
    }

    public void setDate() {
        this.date= LocalDate.now();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        if (amount < 0) amount = this.amount;
        this.amount = amount;
    }

    public int getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
