import java.util.Scanner; 
class User{   
    String userName,password,nameOfNewUser,newUserName,newPassword; 
    long mobileNumber;
    void displayAccountStatus(){ 
       System.out.println("Welcome to ABC Website"); 
    }
} 
class ExistingUser extends User{ 
    
    ExistingUser(String email, String id){  //constructor
        userName=email; 
        password=id; 
    } 
    @Override
    void displayAccountStatus(){ 
        System.out.println("Verification completed"); 
    }
} 
class NewUser extends User{ 
    NewUser(String name,String email,String newId,long mobile){ //constructor
        nameOfNewUser=name; 
        newUserName=email; 
        newPassword=newId;  
        mobileNumber=mobile;
    }  
    @Override
    void displayAccountStatus(){  
        super.displayAccountStatus();//super keyword is used to invoke the super class overriden method
        System.out.println("Your account has been created"); 
    }
}  
class Main{ 
    public static void main(String[] args){  
        int choice; 
        long mobile; 
        String name,email,passwordId;
        Scanner sc=new Scanner(System.in); 
        System.out.println("Please enter your choice:\n1.New User\n2.Existinguser"); 
        choice=sc.nextInt(); 
        switch(choice){ 
            case 1:   
                System.out.println("Enter your name");  
                sc.nextLine();
                name=sc.nextLine(); 
                System.out.println("Enter your email"); 
                email=sc.nextLine();  
                System.out.println("Enter your password. Note:Password should be alphanumeric and alteast 8 characters long");  
                passwordId=sc.nextLine(); 
                System.out.println("Enter your mobile number"); 
                mobile=Long.parseLong(sc.nextLine()); 
                NewUser newUserAccount=new NewUser(name,email,passwordId,mobile);  
                newUserAccount.displayAccountStatus(); 
                break;
            case 2: 
                System.out.println("Enter your email");
                email=sc.nextLine(); 
                sc.nextLine();
                System.out.println("Enter your password"); 
                passwordId=sc.nextLine(); 
                ExistingUser existingUserAccount=new ExistingUser(email,passwordId);   
                existingUserAccount.displayAccountStatus();
                break; 
        } 
     } 
}
