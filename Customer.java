import java.util.Scanner;
public class Customer {
    private String name;
    private String ID;
    private int age;
    private Account account;


    Customer(String name, String ID, int age){
       this.name=name;
        this.age=age;
        this.ID=ID;
    }


    public void setName( String name){

        if (name.matches("\\w+") && !name.contains("_")){
            this.name=name;
        }

    }


    public void setAge(int age){
        this.age=age;
    }


    public void setID(String ID){
            this.ID=ID;

    }


    public String getName(){
        return this.name;
    }


    public String getID(){
        return this.ID;

    }


    public int getAge(){
        return age;
    }


    public void createAccount(){//setter method for object account

        if (eligibility()){
            System.out.println("your account has been made successfully ");
            account=new Account(0.0);
            System.out.println(toString());
        }

    }

        public Account getAccount(){
            return account;
        }

    private boolean eligibility(){// this method will test whether the customer is eligible to make an acc or not
        if (age>=18) {

               if (account==null){// means the customer has no account
                   return true;
               }

            System.out.println("You already have an account..");
               return false;
        }
         else {
            System.out.println("you can't make an account since you're under the age of 18");
            return false;
         }
         }


     @Override
    public String toString(){

        return "Name: "+name+"\nAge: "+age+"\nID: " +ID+" \n"+account.toString();
    }

    // will create a parental consent method here
    // if they have the same last name or wtv

    public static String IDValidator(String ID) {// this will check if each customer has an id of 10 characters or not
        Scanner input = new Scanner (System.in);

        while (true) {

            if (ID.length() == 10)
                return ID;

            System.out.println("This ID is invalid... please enter an ID that has 10 digits");
            ID=input.next();
        }
    }
}

