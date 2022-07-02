package Controller.ControlCart;

import Model.ShoppingCart.ShoppingCartModel;
import View.ShoppingCart.ShoppingCartViewService;
import java.util.HashMap;

public class ShoppingCartController implements ShoppingCartControllerService{
    private final ShoppingCartModel model;
    private final ShoppingCartViewService view;
    public ShoppingCartController(ShoppingCartModel model, ShoppingCartViewService view){
        this.model=model;
        this.view=view;
    }
    public void addToCart(int productId,int quantity){
        this.model.addToCart(productId,quantity);
    }
    public HashMap<Integer,Integer> getProductsFromCart(){
        return this.model.getProductsFromCart();
    }
    public void printBill(int totalAmount,int orderId){
        view.displayBill(totalAmount,orderId);
    }
}
