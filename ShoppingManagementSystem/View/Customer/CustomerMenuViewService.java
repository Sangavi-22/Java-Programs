package View.Customer;

import Controller.ControlCustomer.CustomerAccountControllerService;

public interface CustomerMenuViewService {
    void setController(CustomerAccountControllerService customerAccountController);
    void passShoppingCartModel();
    void customerMenu();


}
