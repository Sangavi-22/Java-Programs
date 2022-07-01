package View.Customer;

import Controller.ControlCustomer.CustomerAccountControllerService;
import Inputs.InputsFromUser;

public class CustomerAccountView implements CustomerAccountViewService{
    private String name,email,address;
    private long mobileNum;
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void inputCustomerDetails(CustomerAccountControllerService customerAccountController){
        name = inputsFromUser.inputName();
        email = inputsFromUser.inputEmail();
        mobileNum = inputsFromUser.inputMobileNum();
        address=inputsFromUser.inputShippingAddress();
        passDetailsToController(customerAccountController);
    }
    public void passDetailsToController(CustomerAccountControllerService customerAccountController){
        customerAccountController.setName(name);
        customerAccountController.setEmail(email);
        customerAccountController.setMobileNum(mobileNum);
        customerAccountController.setShippingAddress(address);
    }
}
