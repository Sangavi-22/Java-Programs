package View.Seller;

import Controller.ControlSeller.SellerAccountController;
import Controller.ControlSeller.SellerAccountControllerService;
import Inputs.InputsFromUser;

public class SellerAccountView implements SellerAccountViewService{
    private String name,email;
    private long mobileNum;
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void inputSellerDetails(SellerAccountControllerService sellerAccountController){
        int ctr=0;
        name = inputsFromUser.inputName();
        email = inputsFromUser.inputEmail();
        mobileNum = inputsFromUser.inputMobileNum();
        if(email.equals("-")) {
            ctr=ctr+1;
        }
        else {
            ctr=0;
        }
        switch (ctr) {
            case 1:
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("!               Profile Completion  66%               !");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            default:
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("!      Hurray!!! Profile creation completed 100%      !");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
        }
        System.out.println();
        passDetailsToController(sellerAccountController);
    }
    public  void inputSellerDetailsToUpdate(SellerAccountController sellerAccountController) {
        int userChoice;
        boolean flag=true;
        while(flag){
            System.out.println("1.name  2.email  3.mobileNum  4.save changes");
            userChoice=inputsFromUser.inputChoice();
            if(userChoice==1) {
                name = inputsFromUser.inputName();
                sellerAccountController.setName(name);
            }
            else if(userChoice==2) {
                email=inputsFromUser.inputEmail();
                sellerAccountController.setEmail(email);
            }
            else if(userChoice==3) {
                mobileNum=inputsFromUser.inputMobileNum();
                sellerAccountController.setMobileNum(mobileNum);
            }
            else {
                flag=false;
            }
        }
    }

    public void passDetailsToController(SellerAccountControllerService sellerAccountController){
        sellerAccountController.setName(name);
        sellerAccountController.setEmail(email);
        sellerAccountController.setMobileNum(mobileNum);
    }
}
