package View.Shop;

import Inputs.InputsFromUser;

public class ShopView implements ShopViewService{
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public boolean confirmAddProduct(){
        System.out.println("Do you want to add this product to cart?\n1.Yes 2.No");
        int userChoice=inputsFromUser.inputChoice();
        if(userChoice==1) {
            return true;
        }
        else {
            return false;
        }
    }
    public void printShoppingCartProducts(int iterator){
        System.out.println("Item "+iterator+" :");
    }
}
