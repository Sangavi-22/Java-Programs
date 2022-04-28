import java.util.*; 
class createBankAccount{  
    private String userName,address,email,dateOfBirth,nomineeName; //data hiding 
    private long aadhaarNum,mobileNum;
    public void setUserDetails(String userName,String address,String email,String dateOfBirth,String nomineeName,long aadhaarNum,long mobileNum){  
        this.userName=userName;
        this.address=address;
        this.email=email;
        this.dateOfBirth=dateOfBirth; 
        this.nomineeName=nomineeName; 
        this.aadhaarNum=aadhaarNum; 
        this.mobileNum=mobileNum; 
    } 
    public void displayMessage(){  
        System.out.println("Your bank account have been created.Your credit card while arrive at your address given by you within 2 days");  
        System.out.println("Thankyou for using our service");
    }
} 
class Main{ 
    public static void main(String[] args){    
        String userName,address,email,dateOfBirth,nomineeName; 
        long aadhaarNum,mobileNum;
        Scanner sc=new Scanner(System.in);  
        System.out.println("Welcome to our banking services"); 
        System.out.println("Enter your name, address, email, date of birth, Nominee Name, aadhaarNumber, mobile Num");
        userName=sc.nextLine(); 
        address=sc.nextLine(); 
        email=sc.nextLine();   
        dateOfBirth=sc.nextLine();  
        nomineeName=sc.nextLine(); 
        aadhaarNum=Long.parseLong(sc.nextLine());
        mobileNum=Long.parseLong(sc.nextLine()); 
        createBankAccount newAccount=new createBankAccount(); 
        newAccount.setUserDetails(userName,address,email,dateOfBirth,nomineeName,aadhaarNum,mobileNum); 
        newAccount.displayMessage(); 
        //System.out.println(newAccount.userName);//This will generate an error since private members are accessible only in its own class
        
    }
}
