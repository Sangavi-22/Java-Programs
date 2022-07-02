package View.Customer;

import Controller.ControlCustomer.CustomerAccountControllerService;
import Inputs.InputsFromUser;

public class CustomerAccountView implements CustomerAccountViewService{
    private String name,email,address;
    private long mobileNum;
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void inputCustomerDetails(CustomerAccountControllerService customerAccountController){
        System.out.println("*******************************************************");
        System.out.println("!                    User Details                     !");
        System.out.println("*******************************************************");
        name = inputsFromUser.inputName();
        email = inputsFromUser.inputEmail();
        mobileNum = inputsFromUser.inputMobileNum();
        address=inputsFromUser.inputShippingAddress();
        System.out.println("*******************************************************");
        System.out.println("!      Hurray!!! Profile creation completed 100%      !");
        System.out.println("*******************************************************");
        passDetailsToController(customerAccountController);
    }
    public void passDetailsToController(CustomerAccountControllerService customerAccountController){
        customerAccountController.setName(name);
        customerAccountController.setEmail(email);
        customerAccountController.setMobileNum(mobileNum);
        customerAccountController.setShippingAddress(address);
    }
}
