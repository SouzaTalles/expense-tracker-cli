# Expense Tracker

Um sistema simples de gerenciamento de despesas via terminal, desenvolvido em Java puro, com foco em organização, boas práticas e manipulação de dados.
O projeto permite adicionar, listar e excluir despesas, oferecendo uma base sólida para quem deseja evoluir para versões mais complexas com persistência em banco de dados ou interface gráfica.

---

## Visão Geral

O **Expense Tracker** foi desenvolvido em **Java** como um projeto prático de gerenciamento de dados via **CLI e arquivos CSV**.  
Ele é ideal para praticar:
- Manipulação de arquivos e leitura/gravação em CSV;
- Uso de argumentos de linha de comando (`args`);
- Estruturação modular de código em Java;
- Boas práticas de organização e persistência de dados.

---

## Tecnologias utilizadas

- Java 17+
- Paradigma de Programação Orientada a Objetos (POO)
- Coleções (ArrayList)
- Scanner (entrada de dados via terminal)

--- 

## Funcionalidades Principais

| Funcionalidade | Descrição |
|----------------|------------|
| **Adicionar despesa** | Adiciona uma nova despesa com descrição e valor. |
| **Atualizar despesa** | Atualiza uma despesa existente pelo ID. |
| **Excluir despesa** | Remove uma despesa pelo seu ID. |
| **Listar despesas** | Exibe todas as despesas registradas. |
| **Resumo total** | Mostra o total de todas as despesas registradas. |
| **Resumo mensal** | Mostra o total das despesas de um mês específico (do ano atual). |

---

##  Como Executar

### 1. Pré-requisitos

- Java 17 ou superior instalado.
- Um terminal (bash, cmd, ou PowerShell).

### 2. Clonar o repositório
```bash
git clone https://github.com/SouzaTalles/expense-tracker-cli.git
cd expense-tracker-cli
```

### 3. Compilar o projeto
```bash
javac *.java
```

### 4. Executar a aplicação
```bash
java App <Commands>
```

---

## Comandos Disponíveis

### Help
```bash
expense-tracker help
```

### Adicionar despesa
```bash
expense-tracker add --description <description> --amount <value>
```

### Excluir despesa
```bash
expense-tracker delete --id <id>
```

### Atualizar despesa
```bash
expense-tracker update --id <id> --description <new description> --amount <new value> 
```

### Listar despesas
```bash
expense-tracker list
```

### Resumo total
```bash
expense-tracker summary
```

### Resumo mensal
```bash
expense-tracker summary --month <number month>
```

---

## Tratamento de Erros

O sistema inclui verificações para:
- Valores negativos no campo --amount;
-  IDs inexistentes em atualizações ou exclusões;
- Criação automática do arquivo expense.csv se ele não existir;
- Controle de IOException e formatação de entradas inválidas.

--- 

## Armazenamento dos Dados
As despesas são gravadas em expense.csv no diretório raiz.
Exemplo de conteúdo:
```aiignore
id,date,description,amoun
1,2024-08-06,Lunch,20.0
2,2024-08-06,Dinner,10.0
```

---

## Autor
Desenvolvido por Talles Souza.

---

## URL do Projeto
[Roadmap project](https://roadmap.sh/projects/expense-tracker) 