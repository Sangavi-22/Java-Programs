package FileOperations;

import java.io.*;
import java.util.ArrayList;
public class StockNotAvailableFileHandler implements StockNotAvailableFileHandlerService{
    File StockNotAvailableFile=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/StockNotAvailable.txt");
    public void writeToFile(int id){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(StockNotAvailableFile,true));
            bw.write("id: "+id+"\n");
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
        }
    }
    public ArrayList<Integer> readStockNotAvailableProducts(){
        ArrayList<Integer>products=new ArrayList<>();
        String sentence;
        try{
            String[] words;
            BufferedReader br=new BufferedReader(new FileReader(StockNotAvailableFile));
            while(br.ready()) {
                sentence=br.readLine();
                words=sentence.split(", ");
                if(!products.contains(Integer.parseInt(words[0].split(":")[1]))){
                    products.add(Integer.parseInt(words[0].split(":")[1]));
                }
            }
            br.close();
        }
        catch(IOException e) {
        }
        finally {
            return products;
        }
    }
}
