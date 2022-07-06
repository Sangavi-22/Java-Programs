package View.Customer;

import Controller.ControlCustomer.CustomerAccountControllerService;

public interface GuestMenuViewService {
    void setController(CustomerAccountControllerService customerAccountController);

    void passShoppingCartModel();
    void guestMenu();
}
