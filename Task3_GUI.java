import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATMGUI extends JFrame implements ActionListener {
    private BankAccount account;
    private JTextField balanceField;
    private JTextField amountField;
    private JTextArea outputArea;

    public ATMGUI(BankAccount account) {
        this.account = account;

        setTitle("ATM Machine");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,2));

        JLabel balanceLabe1 = new JLabel("Balance:");
        balanceField = new JTextField(Double.toString(account.getBalance()));
        balanceField.setEditable(false);

        JLabel amountLabel = new JLabel("Amount");
        amountField = new JTextField();

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(this);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        panel.add(balanceLabe1);
        panel.add(balanceField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(checkBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(outputArea);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Check Balance")) {
            outputArea.setText("Your balance is: Rs. " + account.getBalance());
        } else if (e.getActionCommand().equals("Deposit")) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    account.deposit(amount);
                    balanceField.setText(Double.toString(account.getBalance()));
                    outputArea.setText("Deposit successful.");
                } else {
                    outputArea.setText("Invalid deposit amount.");
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input.Please enter a valid amount.");
            }
        } else if (e.getActionCommand().equals("Withdraw")) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0 && account.withdraw(amount)) {
                    balanceField.setText(Double.toString(account.getBalance()));
                    outputArea.setText("Withdrawl successful.");
                } else {
                    outputArea.setText("Invalid withdraw amount or insufficient balance.");
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input.please a valid amount.");
            }
        }
    }
}

public class Task3_GUI {

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0);
        ATMGUI atmGUI = new ATMGUI(userAccount);
        atmGUI.setVisible(true);
    }   
}