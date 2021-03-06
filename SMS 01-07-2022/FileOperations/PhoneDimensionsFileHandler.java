package FileOperations;

import Model.MobilePhone.PhoneDimensionsModel;
import java.io.*;
import java.util.HashMap;

public class PhoneDimensionsFileHandler implements PhoneDimensionsFileHandlerService{
    File PhoneDimensions=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/PhoneDimensions.txt");
    File PhoneDimensionsTemp=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/PhoneDimensionsTemp.txt");
    public void writePhoneDimensions(int productId, int width, int height, int weight){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PhoneDimensions,true));
            bw.write("productId:"+productId+", width:"+width+", height:"+height+", weight:"+weight+"\n");
            bw.flush();
            bw.close();
        }
        catch(IOException e){
        }
    }
    public void removePhoneDimensions(int productId) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PhoneDimensions));
            BufferedWriter writer = new BufferedWriter(new FileWriter(PhoneDimensionsTemp,true));
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
            PhoneDimensionsTemp.renameTo(PhoneDimensions);
            PrintWriter wr = new PrintWriter(PhoneDimensionsTemp);
            wr.print("");
            wr.flush();
            wr.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    public HashMap<Integer, PhoneDimensionsModel> readPhoneDimensionsDetails() {
        HashMap<Integer, PhoneDimensionsModel>phoneDimensionsDetails=new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PhoneDimensions));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                HashMap<String,String>details=new HashMap<>();
                String[] words = sentence.split(", ");
                for(String part:words) {
                    details.put(part.split(":")[0],part.split(":")[1]);
                }
                PhoneDimensionsModel phoneDimensionsModel=new PhoneDimensionsModel();
                phoneDimensionsModel.setWeight(Integer.parseInt(details.get("width")));
                phoneDimensionsModel.setWeight(Integer.parseInt(details.get("height")));
                phoneDimensionsModel.setWeight(Integer.parseInt(details.get("weight")));
                phoneDimensionsDetails.put(Integer.parseInt(details.get("productId")),phoneDimensionsModel);
            }
            br.close();

        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            return phoneDimensionsDetails;
        }

    }
}
