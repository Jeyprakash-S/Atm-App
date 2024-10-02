import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalTime;
class ATM2 {
    static long accNumber = 171201000022010L;
    static String accHolderName = "JeyPrakash S";
    static double bankBalance = 105004.45;
    static String defaultpin = "1111";
    static String accTransferPin = "0011";

    static long frndAccNumber = 171201000022001L;
    static String frndAccHolderName = "Akil";
    static double frndBankBalance = 1044.4;

    static Scanner input = new Scanner(System.in); 
    static String option;
    static ArrayList<ArrayList<Object>> miniStatement = new ArrayList<>();
  

    public static void main(String[] args) {
        boolean verify;
        do{
           verify= vefiryPin();
            if (verify){
                mainMenu();
                verify = false;
            }
            else {
                System.out.println("Entered pin is Wrong\nTry again ");
            }
        }
        while(!verify);
    }

    public static void mainMenu(){
        boolean check = true;
        do{
            System.out.println("\nSelect your option");
            System.out.println("\na.Cash Withdrawal  b.Balance Enquiry  c.Deposit Cash");
            System.out.println("d.PIN Change  e.Transfer Amount  f.Mini Statement  g. Exit");
            option = input.next();
            if (option.equals("a")){
                withdrawal();
            }else if (option.equals("b")){
                printingBalance();
            }else if (option.equals("c")){
                depositCash();
            }else if (option.equals("d")){
                pinChange();
                check = false;
            }else if (option.equals("e")){
                transferAmount();
            }else if (option.equals("f")){
                miniStatement();
            }else if (option.equals("g")){
                check = false;
                
            }else{
                System.out.println("Invalid Entry\nEnter an alphabet from a to g");
                
            }
        }while(check);
        

    }
    public static boolean vefiryPin(){
        boolean samePin = false;
        System.out.println("Enter your PIN number:");
        String pin = input.next();
        if (pin.equals(defaultpin)){
            samePin = true;
        }
        return samePin;
    }
    
    public static void printingBalance(){
        System.out.println("Your Account Balance is " + bankBalance);
    }
    public static void transferAmount(){
        boolean check;
        do{
            check = true;
            System.out.println("Enter your 15 digit account number");
            String enteredAccNumber = input.next();
            try{
                long accountNumber = Long.parseLong(enteredAccNumber);
                if (enteredAccNumber.length() ==15){
                    if(accountNumber == accNumber){
                        boolean check1;
                        System.out.println("Account Number is "+ accNumber);
                        System.out.println("Accountholder Name is "+ accHolderName);
                        do{
                            check1 = true;    
                            System.out.println("Enter the 15 digit recipient Account Number");
                            String enteredRecipientNumber = input.next();
                            
                            long rAccountNumber = Long.parseLong(enteredRecipientNumber);
                            if(enteredRecipientNumber.length() == 15){
                                if(rAccountNumber == frndAccNumber){
                                    System.out.println("Recipient Account Number : " + frndAccNumber);
                                    System.out.println("Recipient Name : "+ frndAccHolderName);
                                    System.out.println("Enter the amount to be Transfered");
                                    String amount = input.next();
                                    if(isNumbers(amount)){
                                        double transAmount = Double.parseDouble(amount);
                                        bankBalance = subtraction(bankBalance, transAmount);
                                        frndBankBalance = adddition(frndBankBalance, transAmount);
                                        System.out.println("Transfer Successful");
                                        ArrayList<Object> miniStatementRows = new ArrayList<>();
                                        miniStatementRows.add(LocalTime.now());
                                        miniStatementRows.add("Transfered Amount");
                                        miniStatementRows.add(transAmount);
                                        miniStatement.add(miniStatementRows);
                                        check1 = false;
                                        check = false;
                                    }
                                    else
                                        System.out.println("Invalid Recipient Account Number");
                                }
                                else
                                    System.out.println("Invalid Recipient Account Number");
                            }
                            else
                                System.out.println("Invalid Recipient Account Number");

                        }while(check1);

                        
                    }
                    else
                        System.out.println("Invalid Account Number");
                }
                else
                    System.out.println("Invalid Account Number");
            }
            catch (Exception E){
                System.out.println("Recipient's or Your Account Number is invalid");
            }
        }while(check);
    }

    public static void depositCash(){
        System.out.println("Enter your Mobile number :");
        long mobileNumber = input.nextLong();
        boolean check;
        do{
            check = true;
            System.out.println("Enter your 15 digit account number");
            String enteredAccNumber = input.next();
            try{
                long accountNumber = Long.parseLong(enteredAccNumber);
                int totalDigit = 0;
                for(int i : enteredAccNumber.toCharArray()){
                    totalDigit += 1;
                }
                if (totalDigit == 15){
                    if (accountNumber == accNumber){
                        System.out.println("Account Number is "+ accNumber);
                        System.out.println("Accountholder Name is "+ accHolderName);
                        System.out.println("Enter the amount to be deposited: ");
                        double depositAmount = input.nextDouble();
                        bankBalance = adddition(bankBalance, depositAmount) ;
                        System.out.println("New Balance is " + bankBalance);
                        check = false;
                        ArrayList<Object> miniStatementRows = new ArrayList<>();
                        miniStatementRows.add(LocalTime.now());
                        miniStatementRows.add("Deposited Cash");
                        miniStatementRows.add(depositAmount);
                        miniStatement.add(miniStatementRows);
                    }
                    else
                        System.out.println("Invalid Account Number,Try again");
                }
                else
                    System.out.println("Invalid Account Number,Try again");
            }
            catch (Exception E) {
                System.out.println("Invalid Account Number,Try again");
            }
            
        }while(check);
    }
    public static void pinChange(){
        System.out.println("Enter your new PIN : ");
        String newPin = input.next();
        int noOfDigits = newPin.length();
        if (isNumbers(newPin)){
            if (noOfDigits==4){
                if (!newPin.equals(defaultpin)){
                    defaultpin = newPin;
                    System.out.println("New Pin changed Successfully");           
                }
                else{
                    System.out.println("New Pin should not be Old Pin");
                    pinChange();
                }
            }
            else{
                System.out.println("Please enter a PIN of 4 Digits");
                pinChange();
            }
        }
        else{
            System.out.println("Please enter Numbers to set the New Pin");
            pinChange();
        }

    }
    public static void withdrawal(){
        System.out.println("Enter the amount : ");
        String amountstr = input.next();
        if (isNumbers(amountstr)) {
            double amount = Double.parseDouble(amountstr);
            if (amount<bankBalance){
                if (amount<0){
                    System.out.println("Enter a positive amount");
                    withdrawal();
                }
                else if (amount%100==0 && amount!=0){
                    System.out.println("Please Collect your cash");
                    bankBalance = subtraction(bankBalance, amount);
                    ArrayList<Object> miniStatementRows = new ArrayList<>();
                    miniStatementRows.add(LocalTime.now());
                    miniStatementRows.add("Withdrawal Amount");
                    miniStatementRows.add(amount);
                    miniStatement.add(miniStatementRows);
                }
                else {
                    System.out.println("Enter amount in Hundreds");
                    withdrawal();
                }                
            }
            else {
                System.out.println("Amount exceeds your balance");
                withdrawal();
            }   
        }else{
            System.out.println("Please enter a number to withdraw");
            withdrawal();
        }
        System.out.println("Do you want to show Balance on the screen");
        System.out.println("a.Yes      b.No");
        option = input.next();
        if (option.equals("a")){
            printingBalance();
        }
    }
    public static double adddition(double a, double b){
        double total = a+b;
        return total;
    }
    public static double subtraction(double a, double b){
        double total = a-b;
        return total;
    }
    public static boolean isNumbers(String str){
        boolean status = false;
        for(char c : str.toCharArray()){
            if(Character.isDigit(c)){
                status = true;
            }
            else{
                status = false;
                break;
            }
        }
        return status;
    }
    public static void miniStatement(){
        if(miniStatement.size()>3){
            miniStatement.remove(0);
        }
        for(int i = 0 ; miniStatement.size()>i ; i++){
            System.out.println(miniStatement.get(i).get(0) +"\t"+ miniStatement.get(i).get(1) + "\t" + miniStatement.get(i).get(2));
        }
        System.out.println("Available Balance is "+ bankBalance);
    }
}