package FileOperations;

import java.io.*;
import java.util.ArrayList;
public class StockNotAvailableFileHandler implements StockNotAvailableFileHandlerService{
    File StockNotAvailableFile=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/StockNotAvailable.txt");
    File StockNotAvailableTempFile=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/StockNotAvailableTemp.txt");
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
                if(!products.contains(Integer.parseInt(words[0].split(":")[1])) && Integer.parseInt(words[0].split(":")[1])!=0){
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
    public void removePhoneId(int phoneId){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(StockNotAvailableFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(StockNotAvailableTempFile, true));
            String lineToRemove ="id: "+phoneId;
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.contains(lineToRemove))
                    continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.flush();
            writer.close();
            reader.close();
            StockNotAvailableTempFile.renameTo(StockNotAvailableFile);
            PrintWriter wr = new PrintWriter(StockNotAvailableFile);
            wr.print("");
            wr.flush();
            wr.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
