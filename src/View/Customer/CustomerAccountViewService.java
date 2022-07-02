package View.Customer;

import Controller.ControlCustomer.CustomerAccountControllerService;

public interface CustomerAccountViewService {

    void inputCustomerDetails(CustomerAccountControllerService customerAccountController);
    void passDetailsToController(CustomerAccountControllerService customerAccountController);
}
