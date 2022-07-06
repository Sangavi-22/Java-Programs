package Model.Shop;

import java.util.HashMap;

public class ShopModel {
    private HashMap<Integer,Integer> shop=new HashMap<>();
    private static String shopName,shopAddress,contactNo;

    public static void setShopName(){
        shopName="Mobile Galaxy";
    }
    public String getShopName(){
        return shopName;
    }
    public static void setShopAddress(){
        shopAddress="No.10 Ramaswamy Street, Chennai-12";
    }
    public String getShopAddress(){
        return shopAddress;
    }
    public static void setShopContactNum(){
        contactNo="044-278920231";
    }
    public String getContactNo(){
        return contactNo;
    }
    public void addToShop(int productId,int quantity){
        this.shop.put(productId,quantity);
    }
    public HashMap<Integer,Integer> getProductsFromShop(){
        return this.shop;
    }
}
