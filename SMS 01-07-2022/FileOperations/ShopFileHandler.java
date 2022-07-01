package FileOperations;

import java.io.*;
import java.util.HashMap;

public class ShopFileHandler implements ShopFileHandlerService{
    File ShopProducts=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/ShopProducts.txt");
    File ShopProductsTemp=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/ShopProductsTemp.txt");
    public void writeProductToShop(int productId, int quantity) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ShopProducts,true));
            bw.write("ProductId:"+productId+", quantity:"+quantity+"\n");
            bw.flush();
            bw.close();
        }
        catch(IOException e){
        }
    }
    public HashMap<Integer,Integer> readProducts(){
        HashMap<Integer,Integer>products=new HashMap<>();
        String sentence;
        try{
            String[] words;
            BufferedReader br=new BufferedReader(new FileReader(ShopProducts));
            while(br.ready()) {
                sentence=br.readLine();
                words=sentence.split(", ");
                products.put(Integer.parseInt(words[0].split(":")[1]),Integer.parseInt(words[1].split(":")[1]));
            }
            br.close();
        }
        catch(IOException e) {
        }
        finally {
            return products;
        }
    }
    public void updateProductInShop(int productId,int quantity){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ShopProducts));
            BufferedWriter writer = new BufferedWriter(new FileWriter(ShopProductsTemp,true));
            String lineToRemove="ProductId:"+productId;
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.contains(lineToRemove))
                    writer.write("ProductId:"+productId+", quantity:"+quantity+"\n");
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.flush();
            writer.close();
            reader.close();
            ShopProductsTemp.renameTo(ShopProducts);
            PrintWriter wr = new PrintWriter(ShopProductsTemp);
            wr.print("");
            wr.flush();
            wr.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void removePhoneFromShop(int productId){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ShopProducts));
            BufferedWriter writer = new BufferedWriter(new FileWriter(ShopProductsTemp,true));
            String lineToRemove="ProductId:"+productId;
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.contains(lineToRemove))
                    continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.flush();
            writer.close();
            reader.close();
            ShopProductsTemp.renameTo(ShopProducts);
            PrintWriter wr = new PrintWriter(ShopProductsTemp);
            wr.print("");
            wr.flush();
            wr.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
