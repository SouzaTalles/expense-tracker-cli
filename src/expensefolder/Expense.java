package expensefolder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense {
    private final int ID;
    private String description;
    private String amount;
    private LocalDate date;

    private static int nextId;
    private static final DateTimeFormatter dtf;

    static {
        nextId = 1;
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public Expense(String category, String amount) {
        this.ID = nextId;
        nextId++;
        this.description = category;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return  ID + "," + date.format(dtf) + "," + description + "," +amount;
    }

    public void setDate() {
        this.date= LocalDate.now();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(String amount) {
        double doubleAmount = Double.parseDouble(amount);
        if (doubleAmount < 0) amount = this.amount;
        this.amount = amount;
    }

    public int getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
