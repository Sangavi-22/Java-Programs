package View.Customer;

import Controller.ControlCart.ShoppingCartController;
import Controller.ControlCart.ShoppingCartControllerService;
import Controller.ControlCustomer.CustomerAccountControllerService;
import Controller.ControlLogin.LoginController;
import Controller.ControlLogin.LoginControllerService;
import Controller.ControlShop.ShopController;
import Controller.ControlShop.ShopControllerService;
import Inputs.InputsFromUser;
import Model.LoginModel;
import Model.Shop.ShopModel;
import Model.ShoppingCart.ShoppingCartModel;
import Payment.Card;
import Payment.GooglePay;
import Payment.PaymentOperation;
import View.Login.LoginView;
import View.Login.LoginViewService;
import View.PaymentType;
import View.Shop.PrintShopInfoView;
import View.Shop.PrintShopInfoViewService;
import View.Shop.ShopView;
import View.ShoppingCart.ShoppingCartView;
import View.ShoppingCart.ShoppingCartViewService;

public class CustomerMenuView implements CustomerMenuViewService{
    private int totalAmount;
    private final ShopModel shopModel=new ShopModel();
    private final ShopView shopView=new ShopView();
    private final PrintShopInfoViewService printShopInfoView=new PrintShopInfoView();
    private final ShopControllerService shopController=new ShopController(shopModel,shopView,printShopInfoView);
    private final ShoppingCartModel shoppingCartModel=new ShoppingCartModel();
    private final ShoppingCartViewService shoppingCartView=new ShoppingCartView();

    private final ShoppingCartControllerService shoppingCartController=new ShoppingCartController(shoppingCartModel,shoppingCartView);
    private CustomerAccountControllerService customerAccountController;
    private InputsFromUser inputsFromUser=new InputsFromUser();

    public void setController(CustomerAccountControllerService customerAccountController){
        this.customerAccountController=customerAccountController;
    }
    public void customerMenu(){
        try {
            System.out.println();
            System.out.println("*********************************************************");
            System.out.println("!                     CUSTOMER MENU                     !");
            System.out.println("*********************************************************");
            System.out.println("!                Enter  1 to view Products              !");
            System.out.println("!                 Enter 2 to view cart                  !");
            System.out.println("!                Enter  3 to remove from cart           !");
            System.out.println("!                Enter  4 to update quantity            !");
            System.out.println("!                Enter  5 to search products            !");
            System.out.println("!                Enter  6 to view Orders                !");
            System.out.println("!                Enter  7 to view Profile               !");
            System.out.println("!                Enter  8 to view Shop Details          !");
            System.out.println("!                Enter  9 to Logout                     !");
            System.out.println("*********************************************************");
            System.out.println();
            int userChoice=inputsFromUser.inputChoice();
            switch (CustomerActions.values()[userChoice - 1]) {
                case VIEW_PRODUCTS:
                    boolean flag=true;
                    while(flag) {
                        System.out.println("*******************************************************");
                        System.out.println("!                     LIST OF PRODUCTS                !");
                        System.out.println("*******************************************************");
                        if(!shopController.displayPhones()){
                            System.out.println("No products available");
                            System.out.println("*******************************************************");
                            System.out.println();
                            flag=false;
                        }
                        else {
                            System.out.println("******************END OF PRODUCTS LIST*****************");
                            System.out.println();
                            System.out.println("Enter the Id of the product from the above list to know about them in detail");
                            int id = inputsFromUser.inputProductId();
                            shopController.displayPhone(id);
                            System.out.println("*******************************************************");
                            System.out.println(" Do you want to add this product to cart?  1.Yes  2.No ");
                            System.out.println("*******************************************************");
                            System.out.println();
                            switch(userChoice) {
                                case 1:
                                    int quantity=inputsFromUser.inputCount();
                                    if(shopController.phoneAvailable(id,quantity)) {
                                        shopController.addToShoppingCart(this.customerAccountController.getUserName(),id,quantity,shoppingCartController);
                                        System.out.println("Product added to cart successfully.......");
                                    }
                                    else{
                                        System.out.println("Sorry!!! Stock unavailable at this moment");
                                    }
                                case 2:
                                    System.out.println("*******************************************************");
                                    System.out.println("     Continue Shopping?     1.Yes     2.No             ");
                                    System.out.println("*******************************************************");
                                    userChoice=inputsFromUser.inputChoice();
                                    if(userChoice==1) {
                                        continue;
                                    }
                                    else{
                                        flag=false;
                                    }
                                    break;
                            }
                        }
                    }
                    customerMenu();
                    break;
                case VIEW_CART:
                    System.out.println("*******************************************************");
                    System.out.println("!                     VIEWING CART                    !");
                    System.out.println("*******************************************************");
                    if(!(shopController.displayCart(shoppingCartController))){
                        System.out.println("No products have been added till now");
                        System.out.println("*******************************************************");
                        System.out.println();
                    }
                    else {
                        System.out.println();
                        System.out.println("*******************************************************");
                        System.out.println("    Place order or Continue Shopping?   1.Yes    2.No  ");
                        System.out.println("*******************************************************");
                        System.out.println();
                        userChoice = inputsFromUser.inputChoice();
                        if (userChoice == 1) {
                            int amount = shopController.addToOrders(customerAccountController.getUserName(),shoppingCartController);
                            if (amount > 0) {
                                System.out.println("Please check the shipping address.");
                                System.out.println(customerAccountController.getShippingAddress());
                                System.out.println("*******************************************************");
                                System.out.println("Do you want to change the Shipping Address? 1.Yes 2.No");
                                System.out.println("*******************************************************");
                                userChoice = inputsFromUser.inputChoice();
                                if (userChoice == 1) {
                                    String shippingAddress = inputsFromUser.inputShippingAddress();
                                    customerAccountController.setShippingAddress(shippingAddress);
                                    System.out.println("Printing Confirmed Shipping Address. Please Check");
                                    System.out.println();
                                    System.out.println(customerAccountController.getShippingAddress());
                                }
                                else {
                                    System.out.println("Printing Confirmed Shipping Address. Please Check");
                                    System.out.println();
                                    System.out.println(customerAccountController.getShippingAddress());
                                }
                                System.out.println("*******************************************************");
                                System.out.println("    Processing... Taking you to the payment section    ");
                                System.out.println("*******************************************************");
                                System.out.println("1.Payment using card\n2.Payment using googlePay\n3.Cash on Delivery");
                                System.out.println("*******************************************************");
                                System.out.println();
                                PaymentOperation payment;
                                int choice = inputsFromUser.inputChoice();
                                switch (PaymentType.values()[choice - 1]) {
                                    case CARD:
                                        String cardNumber = inputsFromUser.inputAccountId();
                                        payment = new Card();
                                        customerAccountController.payFineThroughCard(payment, cardNumber);
                                        break;
                                    case GOOGLE_PAY:
                                        String googlePayId = inputsFromUser.inputGooglePayId();
                                        payment = new GooglePay();
                                        customerAccountController.payFineThroughGooglePay(payment, googlePayId);
                                        break;
                                    case CASH_ON_DELIVERY:
                                        System.out.println("You have chosen cash on delivery option");
                                }
                                System.out.println();
                                System.out.println("*******************************************************");
                                System.out.println("    Order placed successfully.Thank you for shopping.  ");
                                System.out.println("*******************************************************");
                                System.out.println();
                            }
                            else {
                                System.out.println("No products in cart");
                                System.out.println("*******************************************************");
                                System.out.println();
                            }
                        }
                        else {
                            System.out.println("*******************************************************");
                            System.out.println("     Please wait till we take you back to the menu     ");
                            System.out.println("*******************************************************");
                            System.out.println();
                        }
                    }
                    customerMenu();
                    break;
                case REMOVE_FROM_CART:
                    System.out.println("*******************************************************");
                    System.out.println("!                     VIEWING CART                    !");
                    System.out.println("*******************************************************");
                    System.out.println();
                    shopController.displayCart(shoppingCartController);
                    System.out.println("*******************************************************");
                    System.out.println();
                    System.out.println("*******************************************************");
                    System.out.println("Do you want to remove any product from cart? 1.Yes 2.No");
                    System.out.println("*******************************************************");
                    userChoice=inputsFromUser.inputChoice();
                    if(userChoice==1) {
                        int id=inputsFromUser.inputProductId();
                        shopController.removePhoneFromCart(id,shoppingCartController);
                    }
                    else {
                        System.out.println("*******************************************************");
                        System.out.println("     Please wait till we take you back to the menu     ");
                        System.out.println("*******************************************************");
                        System.out.println();
                    }
                    customerMenu();
                    break;
                case UPDATE_QUANTITY:
                    System.out.println();
                    System.out.println("*******************************************************");
                    System.out.println("!                     VIEWING CART                    !");
                    System.out.println("*******************************************************");
                    System.out.println();
                    shopController.displayCart(shoppingCartController);
                    System.out.println("*******************************************************");
                    System.out.println();
                    System.out.println("*******************************************************");
                    System.out.println("Do you want to update Quantity of any item? 1.Yes 2.No ");
                    System.out.println("*******************************************************");
                    userChoice=inputsFromUser.inputChoice();
                    if(userChoice==1) {
                        int id=inputsFromUser.inputProductId();
                        int count=inputsFromUser.inputCount();
                        if(shopController.phoneAvailable(id,count)) {
                            shopController.updateQuantityInCart(id,count,shoppingCartController);
                            System.out.println("Product quantity updated");
                            System.out.println("*******************************************************");
                            System.out.println();
                        }
                        else{
                            System.out.println("Sorry!!! Quantity cannot be updated");
                            System.out.println("*******************************************************");
                            System.out.println();
                        }
                    }
                    else {
                        System.out.println("*******************************************************");
                        System.out.println("     Please wait till we take you back to the menu     ");
                        System.out.println("*******************************************************");
                        System.out.println();
                    }
                    customerMenu();
                    break;
                case SEARCH_PRODUCTS:
                    System.out.println("*******************************************************");
                    System.out.println("!                    SEARCH PRODUCTS                  !");
                    System.out.println("*******************************************************");
                    System.out.println("Enter something to search");
                    String inputValue=inputsFromUser.inputValue();
                    flag=true;
                    while(flag) {
                        if (shopController.searchPhone(inputValue)) {
                            System.out.println("Enter the Id of the product from the above list to know about them in detail");
                            int id = inputsFromUser.inputProductId();
                            shopController.displayPhone(id);
                            System.out.println("*******************************************************");
                            System.out.println("  Do you want to add this product to cart? 1.Yes  2.No ");
                            System.out.println("*******************************************************");
                            if (userChoice == 1) {
                                System.out.println("Enter the quantity");
                                int quantity = inputsFromUser.inputCount();
                                shopController.addToShoppingCart(customerAccountController.getUserName(), id, quantity,shoppingCartController);
                                System.out.println("Product Added successfully");
                            }
                            else {
                                System.out.println("*******************************************************");
                                System.out.println("Do you want to go back to the search result? 1.Yes 2.No");
                                System.out.println("*******************************************************");
                                userChoice = inputsFromUser.inputChoice();
                                if (userChoice == 1) {
                                    continue;
                                }
                                else {
                                    flag = false;
                                }
                            }
                        }
                        else {
                            System.out.println("No records found");
                            flag=false;
                        }
                    }
                    System.out.println("****************END OF SEARCH PRODUCTS*****************");
                    System.out.println();
                    customerMenu();
                    break;
                case VIEW_ORDERS:
                    System.out.println("*******************************************************");
                    System.out.println("!                       ORDERS                        !");
                    System.out.println("*******************************************************");
                    if( shopController.viewOrdersPlaced(customerAccountController.getUserName())) {
                        System.out.println("********************END OF ORDERS LIST*****************");
                        System.out.println();
                    }
                    else {
                        System.out.println("********************END OF ORDERS LIST*****************");
                        System.out.println();
                    }
                    customerMenu();
                    break;
                case VIEW_PROFILE:
                    this.customerAccountController.updateView();
                    customerMenu();
                    break;
                case VIEW_SHOP_DETAILS:
                    shopController.setShopInfo();
                    shopController.updateView();
                    customerMenu();
                    break;
                case LOGOUT:
                    shopController.writeProductsFromCart(customerAccountController.getUserName(),shoppingCartController);
                    LoginViewService loginView = new LoginView();
                    LoginModel loginModel = new LoginModel();
                    LoginControllerService loginController = new LoginController(loginModel, loginView);
                    loginController.logOut();
                    break;
                case DEFAULT:
                    System.out.println(" Some problem occurred !!!");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("!   Please wait while we take you back to the menu     ");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    customerMenu();
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException | NumberFormatException exception) {
            System.out.println(" Some problem occurred !!!");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("! Please wait while we take you back to the main page !");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            customerMenu();
        }
    }
}
