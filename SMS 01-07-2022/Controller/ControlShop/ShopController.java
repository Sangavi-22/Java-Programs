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
    private final ShoppingCartModel shoppingCartModel=new ShoppingCartModel();
    private final ShoppingCartViewService shoppingCartView=new ShoppingCartView();
    private final ShoppingCartControllerService shoppingCartController=new ShoppingCartController(shoppingCartModel,shoppingCartView);
    private final DataSourceService dataSource=DataSource.getInstance();
    public ShopController(ShopModel shopModel, ShopViewService shopView,PrintShopInfoViewService printShopInfoView){
        this.shopModel=shopModel;
        this.shopView=shopView;
        this.printShopInfoView=printShopInfoView;
    }
    public boolean displayPhones(){
        HashMap<Integer,Integer>products=dataSource.getProducts();
        if(products.size()!=0) {
            for(int key:products.keySet()) {
                if(products.get(key)==0){
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
                    phoneController.printQuantity(products.get(key));
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
    public void displayPhone(int id){
        HashMap<Integer,Integer>products=dataSource.getProducts();
        PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(id);
        PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(id);
        PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(id);
        PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(id);
        PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(id);
        PhoneViewService phoneView = new PhoneView();
        PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
        PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel, phoneView, printPhoneInfoView);
        phoneController.updateView();
        phoneController.printQuantity(products.get(id));
    }
    public boolean addPhone(PhoneDetailsModel phoneDetailsModel, PhoneCameraModel phoneCameraModel, PhoneDimensionsModel phoneDimensionsModel, PhoneManufacturerModel phoneManufacturerModel, PhoneProcessorModel phoneProcessorModel, PhoneViewService phoneView, PrintPhoneInfoViewService printPhoneInfoView, int quantity) {
        boolean added;
        PhoneControllerService phoneController=new PhoneController(phoneDetailsModel,phoneCameraModel,phoneDimensionsModel,phoneManufacturerModel,phoneProcessorModel,phoneView,printPhoneInfoView);
        phoneController.setInputs();
        phoneController.setProductId(getProductIdForPhone()+1);
        phoneController.updateView();
        if(shopView.confirmAddProduct()) {
            shopModel.addToShop(phoneController.getProductId(),quantity);
            dataSource.addPhoneToShop((int)this.getProductsFromShop().keySet().toArray()[0],quantity);
            dataSource.addPhoneDetails(phoneController.getProductId(),phoneDetailsModel);
            dataSource.addPhoneCamera(phoneController.getProductId(),phoneCameraModel);
            dataSource.addPhoneDimensions(phoneController.getProductId(),phoneDimensionsModel);
            dataSource.addPhoneManufacturer(phoneController.getProductId(),phoneManufacturerModel);
            dataSource.addPhoneProcessor(phoneController.getProductId(),phoneProcessorModel);
            added=true;
        }
        else {
            added=false;
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
    public HashMap<Integer,Integer> getProductsFromShop(){
        return shopModel.getProductsFromShop();
    }
    public boolean updatePhoneQuantity(int id,int count){
        boolean incremented;
        if(dataSource.containsPhone(id)) {
            dataSource.updatePhoneQuantity(id,count);
            incremented=true;
        }
        else {
            incremented = false;
        }
        return incremented;
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
    public boolean searchPhone(String input) {
        ArrayList<Integer> productIds = dataSource.searchPhoneFromFile(input);
        HashMap<Integer,Integer>products=dataSource.getProducts();
        if (productIds.size() == 0) {
            return false;
        }
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
            phoneController.printQuantity(products.get(iterator));
        }
        return true;
    }
    public void viewAllOrdersPlaced(){
        ArrayList<Integer>orders=dataSource.readAllOrders();
        for(int orderId:orders) {
            ShoppingCartModel shoppingCartModel=dataSource.getProductsOfThatOrder(orderId);
            HashMap<Integer,Integer>productsInCart=shoppingCartController.getProductsFromCart();
            int iterator=0;
            for(int productIds: productsInCart.keySet()) {
                if(productsInCart.get(productIds)!=0) {
                    this.shopView.printShoppingCartProducts(iterator++);
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
                }
                else{
                    continue;
                }
                shoppingCartController.printBill( getTotalAmount());
                totalAmount=0;
            }
        }

    }
    public void addToWishList() {

    }

    public void checkStock(){
        ArrayList<Integer> productIds =dataSource.readStockNilProducts();
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
    }
    public boolean productAvailable(int id,int orderedQuantity){
        HashMap<Integer,Integer>products=dataSource.getProducts();
        if(products.get(id)!=0) {
            return true;
        }
        else{
            dataSource.addToStockNillList(id);
            return false;
        }
    }
    public void addToShoppingCart(String userName,int productId,int orderedQuantity){
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
    public void addToOrders(String userName){
        int orderNum=0;
        //getLastOrderNum();
        HashMap<Integer,Integer>products=shoppingCartController.getProductsFromCart();
        for(int productId:products.keySet()) {
            dataSource.writeToOrdersList(userName,orderNum,productId,products.get(productId));
            PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(products.get(productId));
            calculateBill(products.get(productId),phoneDetailsModel.getPrice());
        }
        for(int productId:products.keySet()) {
            shoppingCartController.addToCart(productId,0);
            dataSource.removeOrderFromCart(userName);
        }
        dataSource.writeToBillFile(orderNum,this.getTotalAmount());

        totalAmount=0;
    }
    public boolean displayCart(){
        HashMap<Integer,Integer>productsInCart=shoppingCartController.getProductsFromCart();
        int iterator=0;
        boolean flag;
        for(int productIds: productsInCart.keySet()) {
            if(productsInCart.get(productIds)!=0) {
                this.shopView.printShoppingCartProducts(iterator++);
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
                shoppingCartController.printBill( getTotalAmount());
                totalAmount=0;
            }
        }
        if(getTotalAmount()!=0) {
            shoppingCartController.printBill(getTotalAmount());
            totalAmount=0;
            flag=true;
        }
        else {
           flag=false;
        }
        return flag;
    }
    public void removeProductFromCart(int productId){
        shoppingCartController.addToCart(productId,0);
    }
    public void updateQuantityInCart(int productId,int count){
        shoppingCartController.addToCart(productId,count);
    }
    public void viewOrdersPlaced(String userName) {
        ArrayList<Integer>orders=dataSource.readParticularOrder(userName);
        for(int orderId:orders) {
            ShoppingCartModel shoppingCartModel=dataSource.getProductsOfThatOrder(orderId);
            HashMap<Integer,Integer>productsInCart=shoppingCartController.getProductsFromCart();
            int iterator=0;
            for(int productIds: productsInCart.keySet()) {
                if(productsInCart.get(productIds)!=0) {
                    this.shopView.printShoppingCartProducts(iterator++);
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
                }
                else{
                    continue;
                }
                shoppingCartController.printBill( getTotalAmount());
                totalAmount=0;
            }
        }
    }
    public void addToWishList(String userName){
        dataSource.writeToWishList(userName);
    }
    public void calculateBill(int orderedQuantity,int price){
        totalAmount+=orderedQuantity*price;
    }
    public int getTotalAmount(){
        return totalAmount;
    }
    public void addToShop(int quantity,int productId){
        shopModel.addToShop(quantity,productId);
    }
    public void updateView(){

    }













}
