package View.Seller;

import Controller.ControlLogin.LoginController;
import Controller.ControlLogin.LoginControllerService;
import Controller.ControlSeller.SellerAccountControllerService;
import Controller.ControlShop.ShopController;
import Controller.ControlShop.ShopControllerService;
import Inputs.InputsFromUser;
import Model.LoginModel;
import Model.MobilePhone.*;
import Model.Shop.ShopModel;
import View.Login.LoginView;
import View.Login.LoginViewService;
import View.MobilePhone.PhoneView;
import View.MobilePhone.PhoneViewService;
import View.MobilePhone.PrintPhoneInfoView;
import View.MobilePhone.PrintPhoneInfoViewService;
import View.Shop.PrintShopInfoView;
import View.Shop.PrintShopInfoViewService;
import View.Shop.ShopView;

public class SellerMenuView implements SellerMenuViewService{
    private final ShopModel shopModel=new ShopModel();
    private final ShopView shopView=new ShopView();
    private final PrintShopInfoViewService printShopInfoView=new PrintShopInfoView();
    private final ShopControllerService shopController=new ShopController(shopModel,shopView,printShopInfoView);
    private SellerAccountControllerService sellerAccountController;
    private final InputsFromUser inputsFromUser=new InputsFromUser();

    public void setController(SellerAccountControllerService sellerAccountController){
        this.sellerAccountController=sellerAccountController;
    }
    public void sellerMenu(){
        try {
            System.out.println();
            System.out.println("*******************************************************");
            System.out.println("!                      WELCOME                        !");
            System.out.println("*******************************************************");
            System.out.println("!                Enter 1 to view Products             !");
            System.out.println("!                Enter 2 to add Products              !");
            System.out.println("!                Enter 3 to remove Products           !");
            System.out.println("!                Enter 4 to search Products           !");
            System.out.println("!                Enter 5 to view Orders               !");
            System.out.println("!                Enter 6 to Check Stock               !");
            System.out.println("!                Enter 7 to view profile              !");
            System.out.println("!                Enter 8 to Remove User               !");
            System.out.println("!                Enter 9 to Logout                    !");
            System.out.println("*******************************************************");
            System.out.println();
            int userChoice=inputsFromUser.inputChoice();
            switch (SellerActions.values()[userChoice - 1]) {
                case VIEW_PRODUCTS:
                    boolean flag=true;
                    while(flag) {
                        System.out.println();
                        System.out.println("#######################################################");
                        System.out.println("!                     LIST OF PRODUCTS                !");
                        System.out.println("#######################################################");
                        boolean printed=shopController.displayPhones();
                        if(!printed){
                            System.out.println("No products available");
                            System.out.println("#######################################################");
                            flag=false;
                        }
                        else {
                            System.out.println("#######################################################");
                            System.out.println("Enter the Id of the product from the above list to know about them in detail");
                            int id = inputsFromUser.inputProductId();
                            shopController.displayPhone(id);
                            System.out.println("Do you want to go back to the list of products?\n1.Yes\n2.No");
                            userChoice = inputsFromUser.inputChoice();
                            if (userChoice == 1) {
                                continue;
                            }
                            else {
                                flag = false;
                            }
                        }
                    }

                    sellerMenu();
                    break;
                case ADD_PRODUCTS:
                    System.out.println();
                    System.out.println("=======================================================");
                    System.out.println("|                    ADD PRODUCTS                     |");
                    System.out.println("=======================================================");
                    int noOfProducts = inputsFromUser.inputNoOfProducts();
                    while (noOfProducts > 0) {
                        System.out.println("Are you going to add a new product: 1.Yes 2.No");
                        int userInput = inputsFromUser.inputChoice();
                        if (userInput == 1) {
                            PhoneDetailsModel phoneDetailsModel = new PhoneDetailsModel();
                            PhoneManufacturerModel phoneManufacturerModel = new PhoneManufacturerModel();
                            PhoneDimensionsModel phoneDimensionsModel = new PhoneDimensionsModel();
                            PhoneCameraModel phoneCameraModel = new PhoneCameraModel();
                            PhoneProcessorModel phoneProcessorModel = new PhoneProcessorModel();
                            PhoneViewService phoneView = new PhoneView();
                            PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                            System.out.println("Enter the quantity:");
                            int quantity=inputsFromUser.inputCount();
                            if(shopController.addPhone(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel, phoneView, printPhoneInfoView,quantity)) {
                                System.out.println("Product added successfully");
                            }
                            else{
                                System.out.println("Product cannot be added");
                            }
                        }
                        else if (userInput == 2) {
                            int id=inputsFromUser.inputProductId();
                            System.out.println("Enter the quantity you want to add to shop");
                            int count=inputsFromUser.inputCount();
                            if(shopController.updatePhoneQuantity(id,count)) {
                                System.out.println("Product quantity incremented successfully");
                            }
                            else{
                                System.out.println("No records found");
                            }
                        }
                        else {
                            System.out.println("Please provide a valid input");
                        }
                        noOfProducts--;
                    }
                    System.out.println("================= END OF ADD PRODUCTS =================");
                    System.out.println();
                    sellerMenu();
                    break;
                case REMOVE_PRODUCTS:
                    System.out.println();
                    System.out.println("#######################################################");
                    System.out.println("!                    REMOVE PRODUCTS                  !");
                    System.out.println("#######################################################");
                    System.out.println("1.Remove products by id\n2.Remove products by name");
                    int userInput = inputsFromUser.inputChoice();
                    if (userInput == 1) {
                        int id=inputsFromUser.inputProductId();
                        if(shopController.removePhoneWithId(id)) {
                            System.out.println("Phone has been removed");
                        }
                        else {
                            System.out.println("No records available");
                        }
                    }
                    else if (userInput == 2) {
                        String modelName=inputsFromUser.inputModelName();
                        if(shopController.removePhoneWithName(modelName)) {
                            System.out.println("Phone has been removed");
                        }
                        else {
                            System.out.println("No records available");
                        }
                    }
                    else {
                        System.out.println("Please provide a valid input");
                    }
                    sellerMenu();
                    break;
                case SEARCH_PRODUCTS:
                    System.out.println("Enter something to search");
                    String inputValue=inputsFromUser.inputValue();
                    if(shopController.searchPhone(inputValue)) {
                        System.out.println("Searching completed");
                    }
                    else {
                        System.out.println("No records found");
                    }
                    sellerMenu();
                    break;
                case VIEW_ORDERS:
                    shopController.viewAllOrdersPlaced();
                    sellerMenu();
                    break;
                case CHECK_STOCK:
                    shopController.checkStock();
                    sellerMenu();
                    break;
                case VIEW_PROFILE:
                    sellerAccountController.updateView();
                    sellerMenu();
                    break;
                case REMOVE_USER:
                    System.out.println("Enter the username and userId to remove");
                    String userName = inputsFromUser.inputUserName();
                    LoginViewService loginView = new LoginView();
                    LoginModel loginModel = new LoginModel();
                    LoginControllerService loginController = new LoginController(loginModel,loginView);
                    loginController.removeUser(userName);
                    sellerMenu();
                    break;
                case LOGOUT:
                    loginView = new LoginView();
                    loginModel = new LoginModel();
                    loginController = new LoginController(loginModel, loginView);
                    loginController.logOut();
                    sellerMenu();
                    break;
                case DEFAULT:
                    System.out.println("Invalid choice");
                    System.out.println("Taking you back to the menu......");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("!                 Please Wait!!!!!                    !");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    sellerMenu();
                    break;

            }
        }
        catch(ArrayIndexOutOfBoundsException | NumberFormatException exception) {
                System.out.println("Error.Please provide correct input");
                System.out.println("Taking you back to the menu......");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("!                 Please Wait!!!!!                    !");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                sellerMenu();
        }
    }
}
