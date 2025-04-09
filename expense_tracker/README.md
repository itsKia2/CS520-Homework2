# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTracker
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.

## Functionality
This program can do the following things:
- Adding transactions to the tracker which has 2 fields that can be entered (amount and category)
- Stores the serial number of the transaction sequentially (first transaction has lowest serial number)
- Records the date and time of the transaction that was just entered
- Ensures that amount is a valid number input (not zero, not negative, and below 1000)
- Ensures that category is one of the options from food, travel, bills, entertainment, or other.
