package View.ShoppingCart;

import Inputs.InputsFromUser;

public class ShoppingCartView implements ShoppingCartViewService {
    private InputsFromUser inputsFromUser=new InputsFromUser();
    public void displayBill(int amount,int orderId){
        if(amount==0) {
            System.out.print("");
        }
        else {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Bill Amount of orderId "+orderId+" is: Rs."+amount);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }

}
