package Controller.ControlShop;

import Model.MobilePhone.*;
import View.MobilePhone.PhoneViewService;
import View.MobilePhone.PrintPhoneInfoViewService;
import java.util.HashMap;

public interface ShopControllerService {
    boolean displayPhones();
    void displayPhone(int id);
    boolean addPhone(PhoneDetailsModel phoneDetailsModel, PhoneCameraModel phoneCameraModel, PhoneDimensionsModel phoneDimensionsModel, PhoneManufacturerModel phoneManufacturerModel, PhoneProcessorModel phoneProcessorModel, PhoneViewService phoneView, PrintPhoneInfoViewService printPhoneInfoView, int quantity);
    boolean updatePhoneQuantity(int id, int count);
    boolean removePhoneWithId(int id);
    boolean removePhoneWithName(String modelName);
    boolean searchPhone(String inputValue);
    void checkStock();
    int getProductIdForPhone();
    void addToShop(int productId,int quantity);
    HashMap<Integer,Integer> getProductsFromShop();
    void updateView();
    void addToShoppingCart(String userName, int id, int quantity);
    boolean displayCart();
    int getTotalAmount();
    void addToOrders(String userName);
    void removeProductFromCart(int id);
    void updateQuantityInCart(int id, int count);
    boolean productAvailable(int id, int quantity);

    void viewOrdersPlaced(String userName);
    void viewAllOrdersPlaced();
    void addToWishList();
}
