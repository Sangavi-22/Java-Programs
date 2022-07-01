package Model.ShoppingCart;

import java.util.HashMap;
public class ShoppingCartModel {
    //private HashMap<String, PhoneDetailsModel>cart=new HashMap<>();
    /***public void addToCart(String userName,PhoneDetailsModel phoneDetailsModel){
        cart.put(userName,phoneDetailsModel);
    }
    public HashMap<String, PhoneDetailsModel> getProductsFromCart(){
        return cart;
    }***/
    private final HashMap<Integer,Integer>cart=new HashMap<>();
    public void addToCart(int productId,int orderedQuantity) {
        cart.put(productId,orderedQuantity);
    }
    public HashMap<Integer,Integer>getProductsFromCart() {
        return cart;
    }
}
