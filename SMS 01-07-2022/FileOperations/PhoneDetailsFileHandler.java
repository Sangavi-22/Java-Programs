package FileOperations;

import Model.MobilePhone.PhoneDetailsModel;
import java.io.*;
import java.util.HashMap;

public class PhoneDetailsFileHandler implements PhoneDetailsFileHandlerService{
    File PhoneDetails=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/PhoneDetails.txt");
    File PhoneDetailsTemp=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/PhoneDetailsTemp.txt");
    public void writePhoneDetails(int productId, String modelName, String batteryCapacity, double displaySize,int price){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PhoneDetails,true));
            bw.write("productId:"+productId+", modelName:"+modelName+", batteryCapacity:"+batteryCapacity+" ,displaySize:"+displaySize+" ,price:"+price+"\n");
            bw.flush();
            bw.close();
        }
        catch(IOException e){
        }
    }
    public int readLastPhoneId(){
        int id=0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(PhoneDetails));
            String sentence;
            while ((sentence =br.readLine()) != null) {
                String[] words=sentence.split(", ");
                id=Integer.parseInt(words[0].split(":")[1]);
            }
            br.close();
        }
        catch(IOException e) {
        }
        finally {
            return id;
        }
    }
    public void removePhoneDetails(int productId){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PhoneDetails));
            BufferedWriter writer = new BufferedWriter(new FileWriter(PhoneDetailsTemp,true));
            String lineToRemove="productId:"+productId;
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
            PhoneDetailsTemp.renameTo(PhoneDetails);
            PrintWriter wr = new PrintWriter(PhoneDetailsTemp);
            wr.print("");
            wr.flush();
            wr.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int readPhoneId(String modelName){
        int id=0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(PhoneDetails));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                String[] words = sentence.split(", ");
                if(words[1].split(":")[1].contains(modelName)) {
                    id=Integer.parseInt(words[0].split(":")[1]);
                }
            }
            br.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        finally{
            return id;
        }
    }
    public HashMap<Integer, PhoneDetailsModel> readPhoneDetails(){
        HashMap<Integer, PhoneDetailsModel>phoneDetails=new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PhoneDetails));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                HashMap<String,String>details=new HashMap<>();
                String[] words = sentence.split(", ");
                for(String part:words) {
                    details.put(part.split(":")[0],part.split(":")[1]);
                }
                PhoneDetailsModel phoneDetailsModel=new PhoneDetailsModel();
                phoneDetailsModel.setProductId(Integer.parseInt(details.get("productId")));
                phoneDetailsModel.setModelName(details.get("modelName"));
                phoneDetailsModel.setBatteryCapacity(details.get("batteryCapacity"));
                phoneDetailsModel.setDisplaySize(Double.parseDouble(details.get("displaySize")));
                phoneDetailsModel.setPrice(Integer.parseInt(details.get("price")));
                phoneDetails.put(Integer.parseInt(details.get("productId")),phoneDetailsModel);
            }
            br.close();

        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            return phoneDetails;
        }


    }
}
