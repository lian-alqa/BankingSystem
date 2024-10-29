

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;

public class BankingSystem {
  static ArrayList<Customer> customers = new ArrayList<>();// we will record every customer created in the banking system
  static Scanner input = new Scanner (System.in);
  static PrintWriter write;
  static Scanner scanner;

    public static void main(String[] args) throws FileNotFoundException {
             Account account=null;// references the customer's account object

      while (true) {
          System.out.println("-----------------------------------------------");
          System.out.println("-- Welcome to the banking system! --");
          System.out.println("Here are your options:");
          System.out.println("1- Sign up and create an account");
          System.out.println("2- already have an existing");
          System.out.println("3- exit the program ");
          System.out.print("....");
          int choice = input.nextInt();


        if (choice ==1 || choice==2){

            System.out.print("Please enter your ID ");
            String ID =Customer.IDValidator(input.next());// we will get the id validated through this method

            if (choice==1)//sign up or create an account
                signUp(ID);

            else if(choice ==2){
                findExistingAccount(ID);
            }

        }


          else if(choice==3){
            System.out.println("---------------------------------");
            System.out.println("Thank you for using our bank :) ");
              System.exit(0);
          }

          else {
            System.out.println("---------------------------------");
            System.out.println("Please choose an option that's available...");
            System.out.println("---------------------------------");
        }

      }

    }



        public static int findCustomer(String ID){

            for (int i=0;i<customers.size();i++) {

                if (customers.get(i).getID().equals(ID))
                    return i;
            }
                return -1;
        }


        public static void signUp(String ID){// we will sign up the customer info in the system but that doesn't mean they have an account now they need to create an account

            System.out.println("----------------------------------");
            System.out.println("------------sign up page-----------");
            System.out.println("----------------------------------");
            int index = findCustomer(ID); // we will check if the customer data already exists in the system or not
            if(index!=-1){// this means the customer info exists in the system we just need to create an account
                customers.get(index).createAccount();
            }

            // in here we don't the customer's info so we won't be able to get personal info to make an account

            else {// this means the customer info doesn't exist in the system so to create a bank account for them we have to get extra info
                System.out.print("Enter your Name.. ");
                String name = input.next();
                System.out.print("Enter your age.. ");
                int age = input.nextInt();
                input.nextLine();// to consume the new line
                customers.add(new Customer(name, ID, age));
                index=findCustomer(ID);// we will find the new customer that we have created is at which index to create an account for them
                customers.get(index).createAccount();
            }


            scanner.close();
            write.flush();
        }




        public static void activity(Account account,Customer customer) throws FileNotFoundException {
        // this method will make users deposit and withdraw and ask for recipts
            System.out.println("HEllO "+customer.getName());


        while(true){

            System.out.println("--------------------------------------------");
            System.out.println("1-Withdraw an amount");
            System.out.println("2-Deposit money ");
            System.out.println("3-Print  receipt");
            System.out.println("4-account info and the balance");
            System.out.println("5-Go back to the main menu ...");
            System.out.println("--------------------------------------------");
            System.out.print("Choose an option.... ");
            int choice = input.nextInt();
            double amount=0;
            switch(choice){

                case 1:// withdraw
                    System.out.println("-------------------");
                    System.out.print("How much do you want to withdraw? ");
                    amount = input.nextDouble();
                    account.withdraw(amount);
                    break;

                case 2:// deposit
                    System.out.println("-------------------");
                    System.out.println("How much do you want to deposit? ");
                    amount =input.nextInt();
                     account.deposit(amount);
                    break;

                case 3:// print the receipt

                   printReceipt(customer.getAccount().getReceipt());
                    break;

                case 4:// this will be account info
                    System.out.println(customer.toString());
                    break;

               case 5:
                    return;//this will exist the loop and this method to go back to the method that it was invoked from which is find existing account


            }

            }


        }


        public static void findExistingAccount(String ID) throws FileNotFoundException {
            System.out.println("================================================================");
            System.out.println("Please wait a moment as we get everything ready...");
            System.out.println("================================================================");
            int index =findCustomer(ID);

            if(index !=-1){// this means the customer exists
                System.out.println("----------------");
                if(customers.get(index).getAccount()!=null){// this means this customer has an account
                    activity(customers.get(index).getAccount(),customers.get(index));// this means we will implement the needed operations on the account
                    return;// this will exist the method to go back to the main menu
                }

            }

            // if it didn't implement the first if statement that means the customer didn't sign up
            // if the second statement didn't implement that mean the customer doesn't have an account
                System.out.println("----------------------------------------------------");
                System.out.println("You don't have an account please sign up....");



        }

        private static void recordActivity(Customer customer,String info) throws FileNotFoundException {// this method will record info within the file
            Account account=customer.getAccount();

            write = new PrintWriter(customer.getAccount().getReceipt());// we have gotten the reference of the receipt object

            write.println("---------------------------------------------------");
            write.println(account.getDate());
            write.println(account.toString());
            write.println("---------------------------------------------------");

        }

        public static void printReceipt(File receipt) throws FileNotFoundException {

             scanner = new Scanner(receipt);

            while (scanner.hasNext()){
                System.out.println(scanner.nextLine());
            }




        }




        }

