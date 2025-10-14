package expensefolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense {
    private final int ID;
    private String description;
    private String amount;
    private LocalDate date;
    private int nextId;
    private static final DateTimeFormatter dtf;

    static {
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public Expense(String category, String amount) {
        initializeNextIdFromFile();
        this.ID = nextId;
        this.description = category;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return ID + "," + date.format(dtf) + "," + description + "," + amount;
    }

    public void initializeNextIdFromFile() {
        File file = new File("expense.csv");
        if (!file.exists()) {
            nextId = 1;
            return;
        }
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            String last = null;
            while ((line = br.readLine()) != null) {
                last = line;
            }
            if (last != null && last.contains(",")) {
                String[] split = last.split(",");
                nextId = Integer.parseInt(split[0]);
                nextId++;
            } else {
                nextId = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
            nextId = 1;
        }
    }
}
