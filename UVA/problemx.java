/*
 * Account
 * By Yuning Zhou
 * Apr 2, 2017
 * Accompanied by the class Account.
 *
 */
import java.util.*;

public class problemx {
    public static void main(String[] args){
        Account newAccount = new Account(1122, 20000);
        Account.setIr(4.5 / 100);

        // output
        System.out.println("Your new balance is: " + newAccount.withdraw(2500));
        System.out.println("Your new balance is: " + newAccount.deposit(3000));
        System.out.println("The balance is: " + newAccount.getBalance());
        System.out.println("The monthly interst is: " + newAccount.getMonthlyInterest());
        System.out.println("The date on which this account is created is: " + newAccount.getDate());

    }


    static class Account {
        // fields
        private int id;
        private double balance;
        private static double annualInterestRate;
        private Date dateCreated;

        // constructors
        public Account(){

        }

        public Account(int id, double balance){
            this.id = id;
            this.balance = balance;
        }

        // methods
        public void setId(int i){
            id = i;
        }

        public int getId(){
            return id;
        }

        public void setBalance(double i){
            balance = i;
        }

        public double getBalance(){
            return balance;
        }

        public static void setIr(double i){
            annualInterestRate = i;
        }

        public static double getIr(){
            return annualInterestRate;
        }

        public Date getDate(){
            this.dateCreated = new Date();
            return dateCreated;
        }

        public static double getMonthlyInterestRate(){
            return (annualInterestRate / 12);
        }

        public double getMonthlyInterest(){
            return (balance * annualInterestRate / 12);
        }

        public double withdraw(double money){
            balance = balance - money;
            return (balance);
        }

        public double deposit(double money){
            balance = balance + money;
            return (balance);
        }
    }
}