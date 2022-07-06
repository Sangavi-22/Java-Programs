package Controller.ControlShop;

import Controller.ControlCart.ShoppingCartController;
import Controller.ControlCart.ShoppingCartControllerService;
import Controller.ControlMobilePhone.PhoneController;
import Controller.ControlMobilePhone.PhoneControllerService;
import DataStorage.DataSource;
import DataStorage.DataSourceService;
import Date.DateUtility;
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
    private ShoppingCartModel shoppingCartModel;
    private final ShopModel shopModel;
    private final ShopViewService shopView;
    private final PrintShopInfoViewService printShopInfoView;
    private DateUtility dates=new DateUtility(); 
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
    public void addToShop(int productId, int quantity){
        this.shopModel.addToShop(productId,quantity);
    }
    public HashMap<Integer, Integer> getProductsFromShop(){
        return this.shopModel.getProductsFromShop();
    }
    public void printUser(String userName){
        this.shopView.printUserName(userName);
    }
    public void updateView(){
        printShopInfoView.printShopDetails(shopModel.getShopName(),shopModel.getShopAddress(),shopModel.getContactNo());
    }
    public void setShoppingCartModel(ShoppingCartModel shoppingCartModel){
        this.shoppingCartModel=shoppingCartModel;
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
                    PhoneStorageCapacityModel phoneStorageCapacityModel=dataSource.getParticularPhoneWithStorageCapacity(key);
                    PhoneViewService phoneView = new PhoneView();
                    PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                    PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel,phoneStorageCapacityModel, phoneView, printPhoneInfoView);
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
        PhoneStorageCapacityModel phoneStorageCapacityModel=dataSource.getParticularPhoneWithStorageCapacity(id);
        PhoneViewService phoneView = new PhoneView();
        PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
        PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel, phoneStorageCapacityModel, phoneView, printPhoneInfoView);
        phoneController.updateView();
        phoneController.printQuantity(phones.get(id));
    }
    public int addPhone(PhoneDetailsModel phoneDetailsModel, PhoneCameraModel phoneCameraModel, PhoneDimensionsModel phoneDimensionsModel, PhoneManufacturerModel phoneManufacturerModel, PhoneProcessorModel phoneProcessorModel,PhoneStorageCapacityModel phoneStorageCapacityModel, PhoneViewService phoneView, PrintPhoneInfoViewService printPhoneInfoView) {
        int added=0;
        PhoneControllerService phoneController=new PhoneController(phoneDetailsModel,phoneCameraModel,phoneDimensionsModel,phoneManufacturerModel,phoneProcessorModel,phoneStorageCapacityModel,phoneView,printPhoneInfoView);
        phoneController.setInputs();
        int quantity=phoneController.getQuantityOfPhone();
        HashMap<Integer,PhoneDetailsModel>phoneDetails=dataSource.getPhoneDetails();
        HashMap<Integer,PhoneStorageCapacityModel>phoneStorageDetails=dataSource.getPhoneStorageCapacityDetails();
        if(shopView.confirmAddProduct()) {
            for (int phoneId : phoneDetails.keySet()) {
                if (phoneDetails.get(phoneId).getModelName().equalsIgnoreCase(phoneController.getModelName()) && phoneStorageDetails.get(phoneId).getStorageCapacity()==phoneController.getStorageCapacity()) {
                    int count=dataSource.getPhones().get(phoneId)+quantity;
                    dataSource.updatePhoneQuantity(phoneId,count);
                    phoneController.setProductId(phoneId);
                    phoneController.updateView();
                    phoneController.printQuantity(dataSource.getPhones().get(phoneId));
                    if(dataSource.readStockNilProducts().contains(phoneController.getProductId()) && quantity>=1) {
                        dataSource.removeFromStockNilList(phoneController.getProductId());
                    }
                    else if(quantity==0) {
                        dataSource.addToStockNillList(phoneController.getProductId());
                    }

                    return 2;
                } else {
                    continue;
                }
            }
            phoneController.setProductId(getProductIdForPhone() + 1);
            this.addToShop(phoneController.getProductId(), quantity);
            dataSource.addPhoneToShop(phoneController.getProductId(), this.getProductsFromShop().get(phoneController.getProductId()));
            dataSource.addPhoneDetails(phoneController.getProductId(), phoneDetailsModel);
            dataSource.addPhoneCamera(phoneController.getProductId(), phoneCameraModel);
            dataSource.addPhoneDimensions(phoneController.getProductId(), phoneDimensionsModel);
            dataSource.addPhoneManufacturer(phoneController.getProductId(), phoneManufacturerModel);
            dataSource.addPhoneProcessor(phoneController.getProductId(), phoneProcessorModel);
            dataSource.addPhoneStorageCapacity(phoneController.getProductId(), phoneStorageCapacityModel);
            phoneController.updateView();
            phoneController.printQuantity(dataSource.getPhones().get(phoneController.getProductId()));
            if(dataSource.readStockNilProducts().contains(phoneController.getProductId()) && quantity>=1) {
                dataSource.removeFromStockNilList(phoneController.getProductId() );
            }
            else if(quantity==0){
                dataSource.addToStockNillList(phoneController.getProductId());
            }
            return 1;
        }
        else {
            return 3;
        }
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
        boolean flag=false;
        ArrayList<Integer> productIds = dataSource.searchPhoneFromFile(input);
        HashMap<Integer,Integer>phones=dataSource.getPhones();
        for(int key:phones.keySet()){
            if(productIds.contains(key)){
                PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(key);
                PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(key);
                PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(key);
                PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(key);
                PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(key);
                PhoneStorageCapacityModel phoneStorageCapacityModel=dataSource.getParticularPhoneWithStorageCapacity(key);
                PhoneViewService phoneView = new PhoneView();
                PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel, phoneStorageCapacityModel, phoneView, printPhoneInfoView);
                phoneController.printBasicInfo();
                phoneController.printQuantity(phones.get(key));
                flag=true;
            }
            else {
                continue;
            }
        }
        return flag;
    }
    public boolean containsPhone(int id){
        return  dataSource.getPhones().containsKey(id);
    }
    public boolean removePhoneWithId(int id){
        boolean removed;
        if(dataSource.containsPhone(id)) {
            PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(id);
            PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(id);
            PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(id);
            PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(id);
            PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(id);
            PhoneStorageCapacityModel phoneStorageCapacityModel=dataSource.getParticularPhoneWithStorageCapacity(id);
            PhoneViewService phoneView = new PhoneView();
            PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
            PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel,phoneStorageCapacityModel, phoneView, printPhoneInfoView);
            phoneController.printBasicInfo();
            if(shopView.confirmDeleteProduct()) {
                dataSource.removePhone(id);
                removed=true;
            }
            else {
                removed = false;
            }
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
            for (int iterator=0;iterator<productIds.size();iterator++) {
                PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(productIds.get(iterator));
                PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(productIds.get(iterator));
                PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(productIds.get(iterator));
                PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(productIds.get(iterator));
                PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(productIds.get(iterator));
                PhoneStorageCapacityModel phoneStorageCapacityModel=dataSource.getParticularPhoneWithStorageCapacity(productIds.get(iterator));
                PhoneViewService phoneView = new PhoneView();
                PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel,phoneStorageCapacityModel, phoneView, printPhoneInfoView);
                phoneController.printBasicInfo();
            }
            flag=true;
        }
        return flag;
    }
    public boolean updateStock(int productId,int count) {
        dataSource.updatePhoneQuantity(productId,count);
        dataSource.removeFromStockNilList(productId);
        return true;
    }
    public boolean phoneAvailable(int id,int orderedQuantity){
        HashMap<Integer,Integer>products=dataSource.getPhones();
        if(products.get(id)==0){
            dataSource.addToStockNillList(id);
            return false;
        }
        else if(products.get(id)>=orderedQuantity) {
            return true;
        }
        else{
            return false;
        }
    }
    public void addToShoppingCart(String userName, int productId, int orderedQuantity){
        ShoppingCartControllerService shoppingCartController;
        if(this.shoppingCartModel.getProductsFromCart().size()==0 && dataSource.containsCart(userName)) {
            this.shoppingCartModel=dataSource.getCart(userName);
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        }
        else {
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        }
        shoppingCartController.addToCart(productId,orderedQuantity);
    }
    public boolean phoneInCartAlready(String userName,int id){
        ShoppingCartControllerService shoppingCartController;
        if(this.shoppingCartModel.getProductsFromCart().size()==0 && dataSource.containsCart(userName)) {
            this.shoppingCartModel=dataSource.getCart(userName);
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        }
        else {
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        }
        return shoppingCartController.getProductsFromCart().containsKey(id) && shoppingCartController.getProductsFromCart().get(id)!=0;
    }

    public void updateQuantityInCart(String userName,int productId,int count){
        ShoppingCartControllerService shoppingCartController;
        if(this.shoppingCartModel.getProductsFromCart().size()==0 && dataSource.containsCart(userName)) {
            this.shoppingCartModel=dataSource.getCart(userName);
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        }
        else {
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        }
        shoppingCartController.addToCart(productId,count);
    }
    public void removePhoneFromCart(String userName,int productId){
        ShoppingCartControllerService shoppingCartController;
        if(this.shoppingCartModel.getProductsFromCart().size()==0 && dataSource.containsCart(userName)) {
            this.shoppingCartModel=dataSource.getCart(userName);
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        }
        else {
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        }
        shoppingCartController.addToCart(productId,0);
    }
    public boolean displayCart(String userName){
        boolean flag=false;
        ShoppingCartControllerService shoppingCartController;
        if(this.shoppingCartModel.getProductsFromCart().size()==0 && dataSource.containsCart(userName)) {
            this.shoppingCartModel=dataSource.getCart(userName);
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        }
        else {
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        }
        HashMap<Integer,Integer>productsInCart=shoppingCartController.getProductsFromCart();
        for(int productIds: productsInCart.keySet()) {
            if(productsInCart.get(productIds)!=0) {
                PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(productIds);
                PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(productIds);
                PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(productIds);
                PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(productIds);
                PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(productIds);
                PhoneStorageCapacityModel phoneStorageCapacityModel=dataSource.getParticularPhoneWithStorageCapacity(productIds);
                PhoneViewService phoneView = new PhoneView();
                PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel,phoneStorageCapacityModel, phoneView, printPhoneInfoView);
                phoneController.printBasicInfo();
                phoneController.printOrderedQuantity(productsInCart.get(productIds));
                flag=true;
            }
            else{
                continue;
            }
        }
        return flag;
    }
    public void calculateBill(int orderedQuantity,int price){
        this.totalAmount+=orderedQuantity*price;
    }
    public int getTotalAmount(){
        return totalAmount;
    }
    public int addToOrders(String userName){
        int orderId,amount;
        boolean flag=false;
        ShoppingCartControllerService shoppingCartController;
        if(this.shoppingCartModel.getProductsFromCart().size()==0 && dataSource.containsCart(userName)) {
            this.shoppingCartModel=dataSource.getCart(userName);
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        }
        else {
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        }
        if(dataSource.getLastOrderNum()!=0) {
            orderId=dataSource.getLastOrderNum()+1;
        }
        else {
            orderId = 1;
        }
        HashMap<Integer,Integer>products=shoppingCartController.getProductsFromCart();
        for(int productId:products.keySet()) {
            if(products.get(productId)!=0) {
                dataSource.writeToOrdersList(userName, orderId, productId, products.get(productId),"not Delivered");
                int quantity=dataSource.getPhones().get(productId)-products.get(productId);
                dataSource.updatePhoneQuantity(productId,quantity);
                PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(productId);
                this.calculateBill(products.get(productId),phoneDetailsModel.getPrice());
                shoppingCartController.addToCart(productId,0);
                flag=true;
            }
            else {
                continue;
            }
        }
        if(flag) {
            if(dataSource.containsCart(userName)) {
                dataSource.freeCartForUser(userName);
                dataSource.writeToBillFile(orderId,this.getTotalAmount());
                shoppingCartController.printBill(dataSource.readFromBillFile(orderId),orderId);
                amount=this.getTotalAmount();
                this.totalAmount=0;
            }
            else {
                dataSource.writeToBillFile(orderId,this.getTotalAmount());
                shoppingCartController.printBill(dataSource.readFromBillFile(orderId),orderId);
                amount=this.getTotalAmount();
                this.totalAmount=0;
            }
        }
        else{
            amount=0;
        }
        return amount;
    }
    public void removeOrder(int orderId,String userName){
        dataSource.removeOrder(orderId,userName);
    }
    public boolean viewOrdersPlaced(String userName) {
        boolean flag=false;
        int ctr=1;
        ArrayList<Integer>orders=dataSource.readParticularOrder(userName);
        HashMap<Integer,String>orderStatus=dataSource.readOrderStatus(userName);
        for(int orderId:orders) {
            if(orderStatus.get(orderId).equals("Delivered")) {
                continue;
            }
            else {
                shopView.printOrders(orderId);
                ctr++;
                ShoppingCartModel shoppingCartModel = dataSource.getProductsOfThatOrder(orderId);
                ShoppingCartViewService shoppingCartView = new ShoppingCartView();
                ShoppingCartControllerService shoppingCartController = new ShoppingCartController(shoppingCartModel, shoppingCartView);
                for (int products : shoppingCartController.getProductsFromCart().keySet()) {
                    PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(products);
                    PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(products);
                    PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(products);
                    PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(products);
                    PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(products);
                    PhoneStorageCapacityModel phoneStorageCapacityModel = dataSource.getParticularPhoneWithStorageCapacity(products);
                    PhoneViewService phoneView = new PhoneView();
                    PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                    PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel, phoneStorageCapacityModel, phoneView, printPhoneInfoView);
                    phoneController.printBasicInfo();
                    phoneController.printOrderedQuantity(shoppingCartController.getProductsFromCart().get(products)); 
                    dates.setDeliveryDate(orderId); 
                    phoneController.printDeliveryDateOfPhone(dates.getDeliveryDate(), orderStatus.get(orderId));
                    calculateBill(shoppingCartController.getProductsFromCart().get(products), phoneDetailsModel.getPrice());
                }
                shoppingCartController.printBill(this.getTotalAmount(), orderId);
                this.totalAmount = 0;
                flag = true;
            }
        }
        return flag;
    }
    public boolean viewPastOrdersPlaced(String userName) {
        boolean flag=false;
        ArrayList<Integer>orders=dataSource.readParticularOrder(userName);
        HashMap<Integer,String>orderStatus=dataSource.readOrderStatus(userName);
        for(int orderId:orders) {
            int ctr=1;
            ShoppingCartModel shoppingCartModel=dataSource.getProductsOfThatOrder(orderId);
            ShoppingCartViewService shoppingCartView=new ShoppingCartView();
            ShoppingCartControllerService shoppingCartController=new ShoppingCartController(shoppingCartModel,shoppingCartView);
            if(orderStatus.get(orderId).equals("Delivered")) {
                for(int productIds: shoppingCartController.getProductsFromCart().keySet()) {
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
                    PhoneStorageCapacityModel phoneStorageCapacityModel=dataSource.getParticularPhoneWithStorageCapacity(productIds);
                    PhoneViewService phoneView = new PhoneView();
                    PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                    PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel,phoneStorageCapacityModel, phoneView, printPhoneInfoView);
                    phoneController.printBasicInfo();
                    phoneController.printOrderedQuantity(shoppingCartController.getProductsFromCart().get(productIds));
                    dates.setDeliveryDate(orderId);
                    phoneController.printDeliveryDateOfPhone(dates.getDeliveryDate(), orderStatus.get(orderId));
                    calculateBill(shoppingCartController.getProductsFromCart().get(productIds),phoneDetailsModel.getPrice());
                    flag=true;
                }
                shoppingCartController.printBill(this.getTotalAmount(),orderId);
                this.totalAmount=0;
            }
            else {
                continue;
            }
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
            HashMap<Integer,String>orderStatus=dataSource.readOrderStatus(dataSource.getUserFromOrderedUsers(orderId));
            for(int productIds: shoppingCartController.getProductsFromCart().keySet()) {
                if (ctr == 1) {
                    shopView.printOrders(orderId);
                    this.printUser(dataSource.getUserFromOrderedUsers(orderId));
                    ctr++;
                } else {
                    continue;
                }

                PhoneDetailsModel phoneDetailsModel = dataSource.getParticularPhone(productIds);
                PhoneManufacturerModel phoneManufacturerModel = dataSource.getParticularPhoneWithManufacturer(productIds);
                PhoneDimensionsModel phoneDimensionsModel = dataSource.getParticularPhoneWithDimensions(productIds);
                PhoneCameraModel phoneCameraModel = dataSource.getParticularPhoneWithCamera(productIds);
                PhoneProcessorModel phoneProcessorModel = dataSource.getParticularPhoneWithProcessor(productIds);
                PhoneStorageCapacityModel phoneStorageCapacityModel=dataSource.getParticularPhoneWithStorageCapacity(productIds);
                PhoneViewService phoneView = new PhoneView();
                PrintPhoneInfoViewService printPhoneInfoView = new PrintPhoneInfoView();
                PhoneControllerService phoneController = new PhoneController(phoneDetailsModel, phoneCameraModel, phoneDimensionsModel, phoneManufacturerModel, phoneProcessorModel,phoneStorageCapacityModel, phoneView, printPhoneInfoView);
                phoneController.printBasicInfo();
                phoneController.printOrderedQuantity(shoppingCartController.getProductsFromCart().get(productIds));
                dates.setDeliveryDate(orderId);
                phoneController.printDeliveryDateOfPhone(dates.getDeliveryDate(),orderStatus.get(orderId));
                calculateBill(shoppingCartController.getProductsFromCart().get(productIds),phoneDetailsModel.getPrice());
                flag=true;
            }
            shoppingCartController.printBill(this.getTotalAmount(),orderId);
            this.totalAmount=0;
        }
        return flag;
    }

    public void dispatchOrder(int orderId) {
        dataSource.updateOrderStatus(dataSource.getUserFromOrderedUsers(orderId),orderId);
    }

    public void writeProductsFromCart(String userName){
        ShoppingCartViewService shoppingCartView=new ShoppingCartView();
        ShoppingCartControllerService shoppingCartController=new ShoppingCartController(this.shoppingCartModel,shoppingCartView);
        dataSource.writeToCartFile(userName,shoppingCartController.getProductsFromCart());
    }














}
