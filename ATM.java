package org.example;

import java.text.DecimalFormat;
import java.util.*;
public class ATM {
    public static void main(String[] args) {
        System.out.println("Welcome to the ATM!");
        inout.OptionMenu optionMenu = new inout.OptionMenu();
        optionMenu.mainMenu();
    }
    static class Account {
        private final int pinNumber;
        private double Balance = 0;
        Scanner input = new Scanner(System.in);
        DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
        public Account(int pinNumber) {
            this.pinNumber = pinNumber;
        }
        public Account(int pinNumber, int Balance) {
            this.pinNumber = pinNumber;
            this.Balance = Balance;
        }
        public int getPinNumber() {
            return pinNumber;
        }
        public double getBalance() {
            return Balance;
        }
        public void calcWithdraw(double amount) {
            Balance = (Balance - amount);
        }
        public void calcDeposit(double amount) {
            Balance = (Balance + amount);
        }
        void getWithdrawInput() {
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\nCurrent Account Balance: " + moneyFormat.format(Balance));
                    System.out.print("\nAmount you want to withdraw from Account: ");
                    double amount = input.nextDouble();
                    if ((Balance - amount) >= 0 && amount >= 0) {
                        calcWithdraw(amount);
                        System.out.println("\nCurrent Account Balance: " + moneyFormat.format(Balance));
                        end = true;
                    } else {
                        System.out.println("\nBalance and Amount Cannot be Negative.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid Choice.");
                    input.next();
                }
            }
        }
        void getDepositInput() {
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\nCurrent Account Balance: " + moneyFormat.format(Balance));
                    System.out.print("\nAmount you want to deposit from Account: ");
                    double amount = input.nextDouble();
                    if (amount >= 0) {
                        calcDeposit(amount);
                        System.out.println("\nCurrent Account Balance: " + moneyFormat.format(Balance));
                        end = true;
                    } else {
                        System.out.println("\nBalance and Amount Cannot Be Negative.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid Choice.");
                    input.next();
                }
            }
        }

    }
}
