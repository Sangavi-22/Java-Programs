package Model.Shop;


import java.util.HashMap;

public class ShopModel {
    private HashMap<Integer,Integer> shop;
    public void addToShop(int productId,int quantity){
        shop=new HashMap<>();
        shop.put(productId,quantity);
    }
    public HashMap<Integer,Integer> getProductsFromShop(){
        return shop;
    }
}
