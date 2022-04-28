import java.util.*; 
interface FixedDeposit{ 
    default void getRateOfInterest(){  
        System.out.println(" The rate of interest is 5.20%");
    } 
} 
interface RecurringDeposit{ 
    default void getRateOfInterest(){   
        System.out.println("The rate of interest is 4.40%"); 
    } 
} 
class DepositType implements FixedDeposit,RecurringDeposit{   
    String userName,savingsType; 
    private long existingaccNum;
    public void getRateOfInterest(){ 
        if(savingsType.equals("FixedDeposit")){ 
            FixedDeposit.super.getRateOfInterest(); 
        } 
        else{ 
            RecurringDeposit.super.getRateOfInterest(); 
        } 
    } 
    public void getUserDetails(String userName, String savingsType,long existingAccNum){ 
        this.userName=userName;  
        this.savingsType=savingsType; 
        this.existingAccNum=existingAccNum; 
    } 
} 
class Main{
    public static void main(String [] args){   
        String userName,savingsType; 
        long existingAccNum; 
        System.out.println("Enter your UserName, savingsType:Fixed or Recurring Deposit, existing account Number");
        Scanner sc=new Scanner(System.in); 
        userName=sc.nextLine(); 
        savingsType=sc.nextLine(); 
        existingAccNum=Long.parseLong(sc.nextLine());  
        DepositType depositType=new DepositType(); 
        depositType.getUserDetails(userName,savingsType,existingAccNum); 
        depositType.getRateOfInterest(); 
    }
}
   
