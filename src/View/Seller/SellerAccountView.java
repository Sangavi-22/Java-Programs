package View.Seller;

import Controller.ControlSeller.SellerAccountControllerService;
import Inputs.InputsFromUser;

public class SellerAccountView implements SellerAccountViewService{
    private String name,email;
    private long mobileNum;
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void inputSellerDetails(SellerAccountControllerService sellerAccountController){
        System.out.println("*******************************************************");
        System.out.println("!                    User Details                     !");
        System.out.println("*******************************************************");
        name = inputsFromUser.inputName();
        email = inputsFromUser.inputEmail();
        mobileNum = inputsFromUser.inputMobileNum();
        System.out.println("*******************************************************");
        System.out.println("!      Hurray!!! Profile creation completed 100%      !");
        System.out.println("*******************************************************");
        System.out.println();
        passDetailsToController(sellerAccountController);
    }
    public void passDetailsToController(SellerAccountControllerService sellerAccountController){
        sellerAccountController.setName(name);
        sellerAccountController.setEmail(email);
        sellerAccountController.setMobileNum(mobileNum);
    }
}
