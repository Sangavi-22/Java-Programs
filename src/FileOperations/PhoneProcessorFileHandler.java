package FileOperations;

import Model.MobilePhone.PhoneProcessorModel;
import java.io.*;
import java.util.HashMap;

public class PhoneProcessorFileHandler implements  PhoneProcessorFileHandlerService{
    File PhoneProcessor= new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/PhoneProcessor.txt");
    File PhoneProcessorTemp= new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/PhoneProcessorTemp.txt");
    public  void writePhoneProcessorDetails(int productId, String processorType, String operatingSystem){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PhoneProcessor,true));
            bw.write("productId:"+productId+", processorType:"+processorType+", operatingSystem:"+operatingSystem+"\n");
            bw.flush();
            bw.close();
        }
        catch(IOException e){
        }
    }
    public HashMap<Integer, PhoneProcessorModel> readPhoneProcessorDetails(){
        HashMap<Integer, PhoneProcessorModel>phoneProcessorDetails=new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PhoneProcessor));
            String sentence;
            while ((sentence = br.readLine()) != null) {
                HashMap<String,String>details=new HashMap<>();
                String[] words = sentence.split(", ");
                for(String part:words) {
                    details.put(part.split(":")[0],part.split(":")[1]);
                }
                PhoneProcessorModel phoneProcessorModel=new PhoneProcessorModel();
                phoneProcessorModel.setProcessorType(details.get("processorType"));
                phoneProcessorModel.setProcessorType(details.get("operatingSystem"));
                phoneProcessorDetails.put(Integer.parseInt(details.get("productId")),phoneProcessorModel);
            }
            br.close();

        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            return phoneProcessorDetails;
        }
    }
    public void removePhoneProcessor(int productId){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PhoneProcessor));
            BufferedWriter writer = new BufferedWriter(new FileWriter(PhoneProcessorTemp,true));
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
            PhoneProcessorTemp.renameTo(PhoneProcessor);
            PrintWriter wr = new PrintWriter(PhoneProcessorTemp);
            wr.print("");
            wr.flush();
            wr.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
