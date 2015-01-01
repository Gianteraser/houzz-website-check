
package org.excel.reader;

import com.opencsv.CSVReader;
import java.awt.Dialog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import org.openide.util.Exceptions;


public class ExcelReader
{
    DataManager data = DataManager.getInstance();
    
    /**
     * This function reads all the data from the csv file skipping the header.
     * @return 
     */
    public void ReadExcel(String CSV_location) throws FileNotFoundException, IOException      
    {
       // new instance of arraylist to hold excel data 
        List<ExcelData> fileData = new ArrayList<>();
        // instance of buffered reader to open file from this location
        CSVReader reader = new CSVReader(new FileReader(new File(CSV_location)));
        
        reader.readNext();  // skips intial row
        System.out.println("First Line Skipped");
        List<String[]> lines = reader.readAll();
        
        for(String[] lineParts : lines) { // loop to read line by line through document
                // splits each line into indexes
                ExcelData data = new ExcelData(lineParts[0], lineParts[1], lineParts[2], lineParts[3], lineParts[4], lineParts[5],
                        lineParts[6], lineParts[7], lineParts[8], lineParts[9], lineParts[10], lineParts[11], lineParts[12], false);
                fileData.add(data);
            }
             
        
        DataManager.getInstance().setExcelData(fileData);
        this.formatPhoneNumber();
    }
    
    public String[] getTableColumns(){
        String[] s = null;
        try (BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Zelph\\Documents\\NetBeansProjects\\weirdos.csv")))) {
            String split = br.readLine();
            s = split.split(",");
     
        }
        
        catch (Exception ex) {
        Exceptions.printStackTrace(ex);
        }
        
        return s;
}
    
    public static void getCSV() throws FileNotFoundException{
        JFileChooser jFile = new JFileChooser();
        int returnVal =jFile.showOpenDialog(jFile);
        String selectedFile = null;
        if (returnVal == JFileChooser.APPROVE_OPTION){
            selectedFile = jFile.getSelectedFile().getAbsolutePath();
        
            List<ExcelData> fileData = DataManager.getInstance().getExcelData();    
            OutputStreamWriter outWriter = new OutputStreamWriter(new FileOutputStream(selectedFile));          


            try(BufferedWriter bWriter = new BufferedWriter(outWriter)) {
                for (ExcelData file : fileData){
                   bWriter.write(file.getSalesMetro());
                   bWriter.write(",");
                   bWriter.write(file.getCategory());
                   bWriter.write(",");
                   bWriter.write(file.getCompanyName());
                   bWriter.write(",");
                   bWriter.write(file.getFirstName());
                   bWriter.write(",");
                   bWriter.write(file.getLastName());
                   bWriter.write(",");
                   bWriter.write(file.getAddress());
                   bWriter.write(",");
                   bWriter.write(file.getCity());
                   bWriter.write(",");
                   bWriter.write(file.getState());
                   bWriter.write(",");
                   bWriter.write(file.getPhoneNumber());
                   bWriter.write(",");
                   bWriter.write(file.getEmailAddress());
                   bWriter.write(",");
                   bWriter.write(file.getEmailSourceLink());
                   bWriter.write(",");
                   bWriter.write(file.getPro2URL());
                   bWriter.write(",");
                   bWriter.write(String.valueOf(file.getValid()));
                   bWriter.newLine();


                }


            } catch (FileNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }

        
        }
    }
    
    public void formatPhoneNumber(){
        // alter phone number format
        List<ExcelData> excelData = DataManager.getInstance().getExcelData();
        
        for (ExcelData data : excelData) {
            String getNumber = data.getPhoneNumber();
            String formattedNumber = "(" + getNumber.substring(0, 3) + ") " + getNumber.substring(4);
            data.setPhoneNumber(formattedNumber);
        }
    }
    
    public void formatHttpString(){

        // correct any websites that do not have http or https
        
        for (ExcelData currentWebsite : data.getExcelData()) {
            boolean stop = false;
            // correct any websites that do not have http or https
            if (currentWebsite.getWebsite() != null || !currentWebsite.getWebsite().trim().equals("")) {
                if (!currentWebsite.getWebsite().contains("http://") && !currentWebsite.getWebsite().contains("https://")) {
                    String website = "http://" + currentWebsite.getWebsite();
                    currentWebsite.setWebsite(website);
                }
            }
        }
    }
}