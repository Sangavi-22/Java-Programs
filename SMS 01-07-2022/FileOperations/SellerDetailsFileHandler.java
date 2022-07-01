package FileOperations;

import Model.Seller.SellerAccountModel;
import java.io.*;
import java.util.HashMap;

public class SellerDetailsFileHandler implements SellerDetailsFileHandlerService {
    File SellerDetails = new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/SellerDetails.txt");
    File SellerDetailsTemp = new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/SellerDetailsTemp.txt");
    public void writeSellerDetails(int userId,String userName,String password,String name,String email,long mobileNum){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(SellerDetails,true));
            bw.write("SellerId:"+userId+", userName:"+userName+", password:"+password+" ,name:"+name+", email:"+email+", mobileNum:"+mobileNum+"\n");
            bw.flush();
            bw.close();
        }
        catch(IOException e){
        }
    }
    public HashMap<String,SellerAccountModel>readSellers(){
        HashMap<String, SellerAccountModel>sellers= new HashMap<>();
        String sentence;
        try{
            String[] words;
            BufferedReader br=new BufferedReader(new FileReader(SellerDetails));
            while(br.ready()) {
                sentence=br.readLine();
                words=sentence.split(", ");
                SellerAccountModel sellerAccountModel=new SellerAccountModel();
                sellerAccountModel.setUserId(Integer.parseInt(words[0].split(":")[1]));
                sellerAccountModel.setUserName(words[1].split(":")[1]);
                sellerAccountModel.setPassword(words[2].split(":")[1]);
                sellerAccountModel.setName(words[3].split(":")[1]);
                sellerAccountModel.setEmail(words[4].split(":")[1]);
                sellerAccountModel.setMobileNum(Long.parseLong(words[5].split(":")[1]));
                sellers.put(sellerAccountModel.getUserName(),sellerAccountModel);
            }
            br.close();
        }
        catch(IOException e) {
        }
        finally {
            return sellers;
        }
    }
    public int readLastSellerId(){
        String sentence;
        int id=0;
        try{
            String[] words={};
            BufferedReader br=new BufferedReader(new FileReader(SellerDetails));
            while(br.ready()) {
                sentence=br.readLine();
                words=sentence.split(", ");
            }
            id=Integer.parseInt(words[0].split(":")[1]);
            br.close();
        }
        catch(IOException e) {
        }
        finally {
            return id;
        }
    }
    public void removeSellerDetailsFromFile(String userName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SellerDetails));
            BufferedWriter writer = new BufferedWriter(new FileWriter(SellerDetailsTemp,true));
            String lineToRemove=", userName:"+userName;
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
            SellerDetailsTemp.renameTo(SellerDetails);
            PrintWriter wr = new PrintWriter(SellerDetailsTemp);
            wr.print("");
            wr.flush();
            wr.close();
        }
        catch(IOException e) {
        }
    }


}
