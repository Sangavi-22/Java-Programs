package View.Shop;

import Inputs.InputsFromUser;

public class ShopView implements ShopViewService{
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public boolean confirmAddProduct(){
        System.out.println("*******************************************************");
        System.out.println(" Do you want to add this product to shop?  1.Yes  2.No ");
        System.out.println("*******************************************************");
        System.out.println();
        int userChoice=inputsFromUser.inputChoice();
        if(userChoice==1) {
            return true;
        }
        else {
            return false;
        }
    }
    public void printOrders(int orderId){
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("Order Id: "+orderId);
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println();
    }
}
