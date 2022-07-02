package Controller.ControlShop;

import Controller.ControlCart.ShoppingCartController;
import Controller.ControlCart.ShoppingCartControllerService;
import Controller.ControlMobilePhone.PhoneController;
import Controller.ControlMobilePhone.PhoneControllerService;
import DataStorage.DataSource;
import DataStorage.DataSourceService;
import Model.MobilePhone.*;
import Model.Shop.ShopModel;
import Model.ShoppingCart.ShoppingCartModel;
import View.MobilePhone.PhoneView;
import View.MobilePhone.PhoneViewService;
import View.MobilePhone.PrintPhoneInfoView;
import View.MobilePhone.PrintPhoneInfoViewService;
import View.Shop.PrintShopInfoViewService;
import View.Shop.ShopViewService;
import View.ShoppingCart.ShoppingCartView;
import View.ShoppingCart.ShoppingCartViewService;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopController implements ShopControllerService {
    private int totalAmount;
    private static int id=0;
    private final ShopModel shopModel;
    private final ShopViewService shopView;
    private final PrintShopInfoViewService printShopInfoView;
    private final DataSourceService dataSource=DataSource.getInstance();
    public ShopController(ShopModel shopModel, ShopViewService shopView,PrintShopInfoViewService printShopInfoView){
        this.shopModel=shopModel;
        this.shopView=shopView;
        this.printShopInfoView=printShopInfoView;
    }
    public void setShopInfo(){
        ShopModel.setShopName();
        ShopModel.setShopAddress();
        ShopModel.setShopContactNum();
    }
    public void updateView(){
        printShopInfoView.printShopDetails(shopModel.getShopName(),shopModel.getShopAddress(),shopModel.getContactNo());
    }
    public boolean displayPhones(){
        boolean flag=false;
        HashMap<Integer,Integer>phones=dataSource.getPhones();
        if(phones.size()!=0) {
            for(int key:phones.keySet()) {
                if(phones.get(key)==0){
                    continue;
                }
                else {
                    PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(key);
                    PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(key);
                    PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(key);
                    PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(key);
                    PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(key);
                    PhoneViewService phoneView = new PhoneView();
                    PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                    PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel, phoneView, printPhoneInfoView);
                    phoneController.printBasicInfo();
                    phoneController.printQuantity(phones.get(key));
                    flag=true;
                }
            }
        }
        else {
            flag=false;
        }
        return flag;
    }
    public void displayPhone(int id){
        HashMap<Integer,Integer>phones=dataSource.getPhones();
        PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(id);
        PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(id);
        PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(id);
        PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(id);
        PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(id);
        PhoneViewService phoneView = new PhoneView();
        PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
        PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel, phoneView, printPhoneInfoView);
        phoneController.updateView();
        phoneController.printQuantity(phones.get(id));
    }
    public int addPhone(PhoneDetailsModel phoneDetailsModel, PhoneCameraModel phoneCameraModel, PhoneDimensionsModel phoneDimensionsModel, PhoneManufacturerModel phoneManufacturerModel, PhoneProcessorModel phoneProcessorModel, PhoneViewService phoneView, PrintPhoneInfoViewService printPhoneInfoView) {
        int added=-1;
        PhoneControllerService phoneController=new PhoneController(phoneDetailsModel,phoneCameraModel,phoneDimensionsModel,phoneManufacturerModel,phoneProcessorModel,phoneView,printPhoneInfoView);
        phoneController.setInputs();
        int quantity=phoneController.getQuantityOfPhone();
        HashMap<Integer,PhoneDetailsModel>phoneDetails=dataSource.getPhoneDetails();
        if(shopView.confirmAddProduct()){
            for(int phoneId: phoneDetails.keySet()) {
                if (phoneDetails.get(phoneId).getModelName().equals(phoneController.getModelName()) && phoneDetails.get(phoneId).getPrice() == phoneController.getPrice()
                        && phoneDetails.get(phoneId).getDisplaySize() == phoneController.getDisplaySize() && phoneDetails.get(phoneId).getBatteryCapacity().equals(phoneController.getBatteryCapacity())) {
                    dataSource.updatePhoneQuantity(phoneId,quantity);
                    phoneController.updateView();
                    phoneController.printQuantity(dataSource.getPhones().get(phoneId));
                    added = 2;
                    if(dataSource.readStockNilProducts().contains(phoneId)) {
                        dataSource.removeFromStockNilList(phoneId);
                    }
                    else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            if(added!=2) {
                phoneController.setProductId(getProductIdForPhone() + 1);
                new ShopModel().addToShop(phoneController.getProductId(),quantity);
                dataSource.addPhoneToShop(phoneController.getProductId(),this.shopModel.getProductsFromShop().get(phoneController.getProductId()));
                dataSource.addPhoneDetails(phoneController.getProductId(), phoneDetailsModel);
                dataSource.addPhoneCamera(phoneController.getProductId(), phoneCameraModel);
                dataSource.addPhoneDimensions(phoneController.getProductId(), phoneDimensionsModel);
                dataSource.addPhoneManufacturer(phoneController.getProductId(), phoneManufacturerModel);
                dataSource.addPhoneProcessor(phoneController.getProductId(), phoneProcessorModel);
                phoneController.updateView();
                phoneController.printQuantity(dataSource.getPhones().get(phoneController.getProductId()));
                added=1;
            }
            else{
                added=0;
            }
        }
        else {
            added=-2;
        }
        return added;
    }
    public int getProductIdForPhone(){
        if(dataSource.getLastPhoneId()!=0) {
            return dataSource.getLastPhoneId();
        }
        else {
            return id++;
        }
    }
    public boolean searchPhone(String input) {
        ArrayList<Integer> productIds = dataSource.searchPhoneFromFile(input);
        HashMap<Integer, Integer> products = dataSource.getPhones();
        if (productIds.size() == 0) {
            return false;
        }
        else {
            boolean flag=false;
            for (int iterator : productIds) {
                if(products.containsKey(iterator)) {
                    PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(productIds.get(iterator));
                    PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(productIds.get(iterator));
                    PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(productIds.get(iterator));
                    PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(productIds.get(iterator));
                    PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(productIds.get(iterator));
                    PhoneViewService phoneView = new PhoneView();
                    PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                    PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel, phoneView, printPhoneInfoView);
                    phoneController.updateView();
                    phoneController.printQuantity(products.get(iterator));
                    flag=true;
                }
                else {
                    continue;
                }
            }
            return flag;
        }
    }
    public boolean removePhoneWithId(int id){
        boolean removed;
        if(dataSource.containsPhone(id)) {
            dataSource.removePhone(id);
            removed=true;
        }
        else {
            removed = false;
        }
        return removed;
    }
    public boolean removePhoneWithName(String modelName){
        return removePhoneWithId(dataSource.getPhoneId(modelName));
    }
    public boolean checkStock(){
        boolean flag;
        ArrayList<Integer> productIds =dataSource.readStockNilProducts();
        if(productIds.size()==0) {
            flag=false;
        }
        else {
            for (int iterator : productIds) {
                PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(productIds.get(iterator));
                PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(productIds.get(iterator));
                PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(productIds.get(iterator));
                PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(productIds.get(iterator));
                PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(productIds.get(iterator));
                PhoneViewService phoneView = new PhoneView();
                PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel, phoneView, printPhoneInfoView);
                phoneController.printBasicInfo();
            }
            flag=true;
        }
        return flag;
    }
    public boolean phoneAvailable(int id,int orderedQuantity){
        HashMap<Integer,Integer>products=dataSource.getPhones();
        if(products.get(id)==0){
            dataSource.addToStockNillList(id);
            return false;
        }
        else if(products.get(id)>orderedQuantity) {
            return true;
        }
        else{
            return false;
        }
    }
    public void addToShoppingCart(String userName, int productId, int orderedQuantity,ShoppingCartControllerService shoppingCartController){
        if(dataSource.containsCart(userName)) {
            HashMap<Integer, Integer> cartProducts = dataSource.getCart(userName);
            for (int key : cartProducts.keySet()) {
                shoppingCartController.addToCart(key, cartProducts.get(key));
            }
            shoppingCartController.addToCart(productId,orderedQuantity);
        }
        else {
            shoppingCartController.addToCart(productId,orderedQuantity);
        }
    }
    public void updateQuantityInCart(int productId,int count,ShoppingCartControllerService shoppingCartController){
        shoppingCartController.addToCart(productId,count);
    }
    public void removePhoneFromCart(int productId,ShoppingCartControllerService shoppingCartController){
        shoppingCartController.addToCart(productId,0);
    }
    public boolean displayCart(ShoppingCartControllerService shoppingCartController){
        HashMap<Integer,Integer>productsInCart=shoppingCartController.getProductsFromCart();
        boolean flag=false;
        for(int productIds: productsInCart.keySet()) {
            if(productsInCart.get(productIds)!=0) {
                PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(productIds);
                PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(productIds);
                PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(productIds);
                PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(productIds);
                PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(productIds);
                PhoneViewService phoneView = new PhoneView();
                PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel, phoneView, printPhoneInfoView);
                phoneController.printBasicInfo();
                phoneController.printOrderedQuantity(productsInCart.get(productsInCart));
                flag=true;
            }
            else{
                 continue;
            }
        }
        return flag;
    }
    public void calculateBill(int orderedQuantity,int price){
        totalAmount+=orderedQuantity*price;
    }
    public int getTotalAmount(){
        return totalAmount;
    }
    public int addToOrders(String userName,ShoppingCartControllerService shoppingCartController){
        int orderId;
        if(dataSource.getLastOrderNum(userName)!=0) {
            orderId=dataSource.getLastOrderNum(userName)+1;
        }
        else {
            orderId = 1;
        }
        HashMap<Integer,Integer>products=shoppingCartController.getProductsFromCart();
        boolean flag=false;
        int amount;
        for(int productId:products.keySet()) {
            if(products.get(productId)!=0) {
                dataSource.writeToOrdersList(userName, orderId, productId, products.get(productId));
                dataSource.updatePhoneQuantity(productId,products.get(productId)*-1);
                PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(products.get(productId));
                calculateBill(products.get(productId),phoneDetailsModel.getPrice());
                shoppingCartController.addToCart(productId,0);
                flag=true;
            }
            else {
                continue;
            }
        }
        if(flag) {
            dataSource.freeCartForUser(userName);
            dataSource.writeToBillFile(orderId,this.getTotalAmount());
            shoppingCartController.printBill(getTotalAmount(),orderId);
            amount=this.getTotalAmount();
            totalAmount=0;
        }
        else{
            amount=0;
        }
        return amount;
    }
    public boolean viewOrdersPlaced(String userName) {
        boolean flag=false;
        ArrayList<Integer>orders=dataSource.readParticularOrder(userName);
        for(int orderId:orders) {
            int ctr=1;
            ShoppingCartModel shoppingCartModel=dataSource.getProductsOfThatOrder(orderId);
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            ShoppingCartControllerService shoppingCartController=new ShoppingCartController(shoppingCartModel,shoppingCartView);
            HashMap<Integer,Integer>productsInCart=shoppingCartController.getProductsFromCart();
            for(int productIds: productsInCart.keySet()) {
                if(productsInCart.get(productIds)!=0) {
                    if (ctr == 1) {
                        shopView.printOrders(orderId);
                        ctr++;
                    } else {
                        continue;
                    }
                    PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(productIds);
                    PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(productIds);
                    PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(productIds);
                    PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(productIds);
                    PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(productIds);
                    PhoneViewService phoneView = new PhoneView();
                    PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                    PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel, phoneView, printPhoneInfoView);
                    phoneController.printBasicInfo();
                    phoneController.printOrderedQuantity(productIds);
                    calculateBill(productsInCart.get(productIds),phoneController.getPrice());
                    flag = true;
                }
                else{
                    continue;
                }
            }
            shoppingCartController.printBill(getTotalAmount(), orderId);
            totalAmount=0;
        }
        return flag;
    }
    public boolean viewAllOrdersPlaced(){
        boolean flag=false;
        ArrayList<Integer>orders=dataSource.readAllOrders();
        for(int orderId:orders) {
            int ctr=1;
            ShoppingCartModel shoppingCartModel=dataSource.getProductsOfThatOrder(orderId);
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            ShoppingCartControllerService shoppingCartController=new ShoppingCartController(shoppingCartModel,shoppingCartView);
            HashMap<Integer,Integer>productsInCart=shoppingCartController.getProductsFromCart();
            for(int productIds: productsInCart.keySet()) {
                if (productsInCart.get(productIds) != 0) {
                    if (ctr == 1) {
                        shopView.printOrders(orderId);
                        ctr++;
                    } else {
                        continue;
                    }
                    PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(productIds);
                    PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(productIds);
                    PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(productIds);
                    PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(productIds);
                    PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(productIds);
                    PhoneViewService phoneView = new PhoneView();
                    PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                    PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel, phoneView, printPhoneInfoView);
                    phoneController.printBasicInfo();
                    phoneController.printOrderedQuantity(productIds);
                    calculateBill(productsInCart.get(productIds), phoneController.getPrice());
                    flag = true;
                } else {
                    continue;
                }
            }
            shoppingCartController.printBill(getTotalAmount(),orderId);
            totalAmount=0;
        }
        return flag;
    }
    public void writeProductsFromCart(String userName,ShoppingCartControllerService shoppingCartController){
        dataSource.writeToCartFile(userName,shoppingCartController.getProductsFromCart());
    }














}
