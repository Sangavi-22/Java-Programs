package FileOperations;

import Model.MobilePhone.PhoneManufacturerModel;
import java.io.*;
import java.util.HashMap;

public class PhoneManufacturerFileHandler implements PhoneManufacturerFileHandlerService{
    File PhoneManufacturer=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/PhoneManufacturer.txt");
    File PhoneManufacturerTemp=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/PhoneManufacturerTemp.txt");
    public  void writePhoneManufacturerDetails(int productId, String manufacturerName){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PhoneManufacturer,true));
            bw.write("productId:"+productId+", manufacturerName:"+manufacturerName+"\n");
            bw.flush();
            bw.close();
        }
        catch(IOException e){
        }
    }
    public HashMap<Integer, PhoneManufacturerModel> readPhoneManufacturerDetails(){
        HashMap<Integer, PhoneManufacturerModel>phoneManufacturers=new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PhoneManufacturer));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                HashMap<String,String>details=new HashMap<>();
                String[] words = sentence.split(", ");
                for(String part:words) {
                    details.put(part.split(":")[0],part.split(":")[1]);
                }
                PhoneManufacturerModel phoneManufacturerModel=new PhoneManufacturerModel();
                phoneManufacturerModel.setManufacturerName(details.get("manufacturerName"));
                phoneManufacturers.put(Integer.parseInt(details.get("productId")),phoneManufacturerModel);
            }
            br.close();

        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            return phoneManufacturers;
        }
    }
    public void removePhoneManufacturer(int productId){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PhoneManufacturer));
            BufferedWriter writer = new BufferedWriter(new FileWriter(PhoneManufacturerTemp,true));
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
            PhoneManufacturerTemp.renameTo(PhoneManufacturer);
            PrintWriter wr = new PrintWriter(PhoneManufacturerTemp);
            wr.print("");
            wr.flush();
            wr.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
