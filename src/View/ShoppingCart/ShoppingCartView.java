package View.ShoppingCart;

public class ShoppingCartView implements ShoppingCartViewService {
    public void displayBill(int amount,int orderId){
        if(amount==0) {
            System.out.print("");
        }
        else {
            System.out.println("Bill Amount of orderId: "+orderId+"is: Rs."+amount);
        }
    }

}
