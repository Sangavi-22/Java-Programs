package Controller.ControlShop;

import Controller.ControlCart.ShoppingCartControllerService;
import Model.MobilePhone.*;
import View.MobilePhone.PhoneViewService;
import View.MobilePhone.PrintPhoneInfoViewService;

public interface ShopControllerService {
    void setShopInfo();
    void updateView();
    boolean displayPhones();
    void displayPhone(int id);
    int addPhone(PhoneDetailsModel phoneDetailsModel, PhoneCameraModel phoneCameraModel, PhoneDimensionsModel phoneDimensionsModel, PhoneManufacturerModel phoneManufacturerModel, PhoneProcessorModel phoneProcessorModel, PhoneViewService phoneView, PrintPhoneInfoViewService printPhoneInfoView);
    int getProductIdForPhone();
    boolean searchPhone(String inputValue);
    boolean removePhoneWithId(int id);
    boolean removePhoneWithName(String modelName);
    boolean checkStock();
    boolean phoneAvailable(int id, int quantity);
    void addToShoppingCart(String userName, int id, int quantity, ShoppingCartControllerService shoppingCartController);
    void updateQuantityInCart(int id, int count,ShoppingCartControllerService shoppingCartController);
    void removePhoneFromCart(int id,ShoppingCartControllerService shoppingCartController);
    boolean displayCart(ShoppingCartControllerService shoppingCartController);
    void calculateBill(int orderedQuantity,int price);
    int getTotalAmount();
    int addToOrders(String userName,ShoppingCartControllerService shoppingCartController);
    boolean viewOrdersPlaced(String userName);
    boolean viewAllOrdersPlaced();
    void writeProductsFromCart(String userName,ShoppingCartControllerService shoppingCartController);

}
