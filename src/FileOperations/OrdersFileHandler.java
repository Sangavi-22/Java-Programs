package FileOperations;

import Model.ShoppingCart.ShoppingCartModel;
import java.io.*;
import java.util.ArrayList;

public class OrdersFileHandler implements OrdersFileHandlerService{
    File OrdersFile=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/OrdersFile.txt");
    File OrderedUsers=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/OrderedUsers.txt");
    public void writeOrders(int orderNum,int productId,int quantity){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OrdersFile,true));
            bw.write("orderId:"+orderNum+", productId:"+productId+", quantity"+quantity+"\n");
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    public  void writeOrderedUsers(int orderNum, String userName){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OrderedUsers,true));
            bw.write("userName:"+userName+", orderNum:"+orderNum+"\n");
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Integer> readUserOrderId(String userName){
        ArrayList<Integer>orders=new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(OrderedUsers));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                String[] words = sentence.split(", ");
                if(words[1].split(":")[1].equals(userName)) {
                    orders.add(Integer.parseInt(words[0].split(":")[1]));
                }
            }
            br.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            return orders;
        }
    }
   public ShoppingCartModel readProducts(int orderId){
        ShoppingCartModel shoppingCartModel=new ShoppingCartModel();
       try {
           BufferedReader br = new BufferedReader(new FileReader(OrdersFile));
           String sentence;
           while ((sentence = br.readLine()) != null) {
               String[] words = sentence.split(", ");
               if(Integer.parseInt(words[0].split(":")[1])==orderId) {
                   int productId=Integer.parseInt(words[1].split(":")[1]);
                   int quantity=Integer.parseInt(words[2].split(":")[1]);
                   shoppingCartModel.addToCart(productId,quantity);
               }
               else{
                   continue;
               }
           }
           br.close();
       }
       catch(IOException e) {
           throw new RuntimeException(e);
       }
       finally {
           return shoppingCartModel;
       }
   }
   public ArrayList<Integer> readAllOrderFromFile(){
       ArrayList<Integer>orders=new ArrayList<>();
       try {
           BufferedReader br = new BufferedReader(new FileReader(OrdersFile));
           String sentence;
           while ((sentence = br.readLine()) != null) {
               String[] words = sentence.split(", ");
               if(!orders.contains(Integer.parseInt(words[0].split(":")[1]))) {
                   orders.add(Integer.parseInt(words[0].split(":")[1]));
               }
               else {
                   continue;
               }
           }
           br.close();
       }
       catch(IOException e) {
           throw new RuntimeException(e);
       }
       finally {
           return orders;
       }
   }
   public int readLastOrderNum(String userName){
        int id=0;
        try {
           BufferedReader br = new BufferedReader(new FileReader(OrderedUsers));
           String sentence;
           while ((sentence = br.readLine()) != null) {
               String[] words = sentence.split(", ");
               if(words[0].split(":")[1].equals(userName)) {
                   id = Integer.parseInt(words[1].split(":")[1]);
               }
               else {
                   continue;
               }
           }
           br.close();
       }
       catch(IOException e) {
           throw new RuntimeException(e);
       }
       finally {
           return id;
       }
   }
}
