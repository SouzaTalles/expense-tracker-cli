# Expense Tracker

A simple expense management system via terminal, developed in pure Java, focused on organization, clean code, and data handling.
This project allows adding, listing, updating, and deleting expenses — serving as a solid foundation for more advanced versions with database persistence or graphical interfaces.

---

## Overview
Expense Tracker was built in Java as a practical project for managing data through the CLI and CSV files.
It’s ideal for practicing:
- File manipulation and CSV read/write operations;
- Using command-line arguments (args);
- Modular and organized Java project structure;
- Data persistence and clean code principles.

---

## Technologies Used
- Java 17+
- Object-Oriented Programming (OOP)
- Collections (ArrayList)
- Scanner (CLI input handling)

---

## Main Features
| Feature | Description |
|----------------|------------|
| **Add expense** | Adds a new expense with description and amount. |
| **Update expense** | Updates an existing expense by ID. |
| **Delete expense** | Removes an expense by its ID. |
| **List expenses** | Displays all registered expenses. |
| **Total summary** | Shows the total of all recorded expenses. |
| **Monthly summary** | Displays the total expenses for a specific month (of the current year). |

---

## How to Run

### 1. Prerequisites

- Java 17 or higher installed.
- A terminal (Bash, CMD, or PowerShell).

### 2. Clone the repository
```bash
git clone https://github.com/SouzaTalles/expense-tracker-cli.git
cd expense-tracker-cli
```


### 3. Compile the project
```bash
javac *.java
```

### 4. Run the application
```bash
java App <Commands>
```

---

## Available Commands
### Help
```bash
expense-tracker help
```

### Add expense
```bash
expense-tracker add --description <description> --amount <value>
```

### Delete expense
```bash
expense-tracker delete --id <id>
```

### Update expense
```bash
expense-tracker update --id <id> --description <new description> --amount <new value>
```

### List expenses
```bash
expense-tracker list
```

### Total summary
```bash
expense-tracker summary
```

### Monthly summary
```bash
expense-tracker summary --month <number month>
```

---

## Error Handling

The system includes validations for:

- Negative values in the --amount field;
- Nonexistent IDs during update or delete operations;
- Automatic creation of the expense.csv file if missing;
- Handling of IOException and invalid input formatting.

---

## Data Storage

Expenses are stored in a expense.csv file in the project’s root directory.
Example:
```aiignore
id,date,description,amount
1,2024-08-06,Lunch,20.0
2,2024-08-06,Dinner,10.0
```

---

## Author

Developed by Talles Souza.

---

## Project URL
[Roadmap project](https://roadmap.sh/projects/expense-tracker) 