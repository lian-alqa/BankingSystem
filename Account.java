
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Date;

public class Account  {
    private File records=new File("C:\\Users\\layan\\OneDrive\\Desktop\\BankingSystem\\History.txt");// we will print every activity done within the account on here
    private Scanner scanner;
    private double balance;
    private Date date = new Date();// will be used to record dates of when everything was withdrawn or deposited

    // a try and catch block for file not found

    {
        try {
            scanner = new Scanner(records);
        } catch (FileNotFoundException e) {
            System.out.println("This file doesn't exist");
        }
    }


    Account(double balance) {
        this.balance = balance;
    }


    public double getBalance(){
        return balance;
    }

    public File getReceipt(){
        return records;
    }

    public Date getDate(){
        return date;
    }



    public void printReceipt(){// this will print the receipt on screen

        while(scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }

    }

    // later on we might allow people to take money however it would be included in their debts and then there is a maximum debit amount

    public String withdraw(double withdrawnMoney){

        if (balance-withdrawnMoney>0){
            balance -= withdrawnMoney;
            System.out.println("amount has been withdrawn..");
            return "withdrawn amount: " + withdrawnMoney;// we will print out the info of thw withdrawn money
        }

        System.out.println("insufficient fund");
        return "";
    }

    public String deposit(double balance){
        this.balance=balance;
        System.out.println("amount has been deposited..");
        return "Deposited amount: "+balance;

    }

    @Override
    public String toString(){
        return "----------------\nAccount info..\nAccount Balance: "+balance;
    }




}
