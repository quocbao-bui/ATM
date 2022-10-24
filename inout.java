package org.example;

import java.text.DecimalFormat;
import java.util.*;

public class inout {
    static class OptionMenu {
        Scanner menuInput = new Scanner(System.in);
        DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
        HashMap<Integer, ATM.Account> data = new HashMap<>();
        private void getLogin() {
            boolean end = false;
            int customerNumber;
            int pinNumber;
            while (!end) {
                try {
                    System.out.print("\n(Press 0 to exit)");
                    System.out.print("\nEnter your customer number: ");
                    customerNumber = menuInput.nextInt();
                    if (customerNumber == 0) {
                        break;
                    }
                    System.out.print("\nEnter your PIN number: ");
                    pinNumber = menuInput.nextInt();
                    for (Map.Entry<Integer, ATM.Account> integerAccountEntry : data.entrySet()) {
                        ATM.Account acc = (ATM.Account) ((Map.Entry<?, ?>) integerAccountEntry).getValue();
                        if (data.containsKey(customerNumber) && pinNumber == acc.getPinNumber()) {
                            getChecking(acc);
                            end = true;
                            break;
                        }
                    }
                    if (!end){
                        System.out.println("\nWrong Customer Number or Pin Number");
                    }
                }
                catch (InputMismatchException x) {
                    System.out.println("\nInvalid Character(s). Only Numbers.");
                    menuInput.next();
                }
            }
        }
        private void getChecking(ATM.Account acc) {
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\nAccount: ");
                    System.out.println(" Type 1 - View Balance");
                    System.out.println(" Type 2 - Withdraw Money");
                    System.out.println(" Type 3 - Deposit Money");
                    System.out.println(" Type 4 - Log out");
                    System.out.print("\nChoice: ");
                    int selection = menuInput.nextInt();
                    switch (selection) {
                        case 1:
                            System.out.println("\nAccount Balance: " + moneyFormat.format(acc.getBalance()));
                            break;
                        case 2:
                            acc.getWithdrawInput();
                            break;
                        case 3:
                            acc.getDepositInput();
                            break;
                        case 4:
                            end = true;
                            break;
                        default:
                            System.out.println("\nInvalid Choice.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid Choice.");
                    menuInput.next();
                }
            }
        }
        private void createAccount() {
            int cst_no = 0;
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\nEnter your customer number ");
                    cst_no = menuInput.nextInt();
                    if (cst_no == 0) {
                        System.out.println("Please enter another number: ");
                        continue;
                    }
                    for (Map.Entry<Integer, ATM.Account> ignored : data.entrySet()) {
                        if (!data.containsKey(cst_no)) {
                            end = true;
                            break;
                        }
                    }
                    if (!end) {
                        System.out.println("\nThis customer number is already registered");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid Choice.");
                    menuInput.next();
                }
            }
            System.out.println("\nEnter PIN to be registered");
            int pin = menuInput.nextInt();
            data.put(cst_no, new ATM.Account(pin));
            System.out.println("\nYour new account has been successfully registered!");
            System.out.println("\nRedirecting to login.............");
            getLogin();
        }
        void mainMenu() {
            data.put(321, new ATM.Account(321, 1000));
            data.put(123, new ATM.Account(123, 200));
            boolean end = false;
            while (!end) {
                try {
                    System.out.println("\n Type 1 - Login");
                    System.out.println(" Type 2 - Create Account");
                    System.out.println(" Type 3 - Exit");
                    System.out.print("\nChoice: ");
                    int choice = menuInput.nextInt();
                    switch (choice) {
                        case 1:
                            getLogin();
                            break;
                        case 2:
                            createAccount();
                            break;
                        case 3:
                            end = true;
                            break;
                        default:
                            System.out.println("\nInvalid Choice.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nAnother Invalid Choice.");
                    menuInput.next();
                }
            }
            System.out.println("\nThank You for using the ATM.\n");
        }
    }
}
