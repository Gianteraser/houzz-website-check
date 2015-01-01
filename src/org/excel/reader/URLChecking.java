/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.excel.reader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.SwingUtilities;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openide.util.Exceptions;
import org.excel.reader.readerwindowTopComponent;
import org.jsoup.HttpStatusException;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
/**
 *
 * @author sqa
 */
public class URLChecking
{
    private URL url;
    private DataManager data = DataManager.getInstance();
    
    public URLChecking(){
        DataManager.getInstance().addButtonListener(new ButtonListener()
        {

            @Override
            public void buttonPressed()
            {
                System.out.println("Validated");
            }
        });
    }
    public List<ExcelData> LoadExcelData(){
        ExcelReader excelReader = new ExcelReader();
        
        try {
            excelReader.ReadExcel(data.getCSV_location());
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return data.getExcelData();
    }
    
    public boolean BasicTermChecker(String url, String term) {
        try {
            System.out.println(url);
            Document doc = Jsoup.connect(url.trim()).get();

            if (doc.toString().toLowerCase().contains(term)) {
                return true;
            }

        } catch (Exception ex) {
            // not sure if this should be here
            return false;
        }
        return false;
    }
        
    public boolean connectURL(String url, String term)
    {
        try {
            Document doc = Jsoup.connect(url.trim()).get();
            if (doc.toString().toLowerCase().contains(term)) {
                return true;
            } else {
                List<String> websites = new ArrayList<>();
                String[] document = doc.toString().split("\"");

                for (int i = 1; i < document.length; i++) {
                    if (document[i - 1].contains("href") && (document[i].contains(".com") || document[i].contains(".net") || document[i].contains(".org"))) {
                        if (!websites.contains(document[i])) {
                            websites.add(document[i]);

                        }
                    }

                }
                // to see if there is http or https
                for (int i = 0; i < websites.size(); i++) {
                    if (!websites.get(i).contains("http://") && !websites.get(i).contains("https://")) {

                        websites.set(i, ("https://" + websites.get(i)).replace("////", "//"));
                    }
                }

                for (String website : websites) {
                    if (BasicTermChecker(website, term)) {
                        return true;
                    }
                }
            }
        

        } catch (IOException ex) {
            return false;
        }

        return false;
    }

    public void StandAloneConnectHouzz() {

        try {
            

            for (ExcelData currentWebsite : data.getExcelData()) {
                //example url http://www.houzz.com/professionals/s/D.r.c.-Building-Contractors,-Llc/c/Stamford%2C-CT
                String website = "http://www.houzz.com/professionals/s/" + currentWebsite.getCompanyName().replace(" ", "-") + "/c/"
                        + currentWebsite.getCity().replace(" ", "-") + "%2C" + currentWebsite.getState();
                Document doc = Jsoup.connect(website).get();
                if (doc.toString().contains(currentWebsite.getPhoneNumber())) {
                    currentWebsite.setValid(false);
                    currentWebsite.setNotes("Not on Houzz");
                    connectPro2URL(currentWebsite);
                } else {
                    currentWebsite.setValid(true);
                }

            }

        } catch (HttpStatusException ex) {

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                DataManager.getInstance().firesEvent();
            }
        });
    }
    /**
     * returns false if listing is on houzz, returns true if it doesn't exist on houzz.
     * 
     * @param data
     * @return 
     */
    public boolean connectToHouzz(ExcelData data){
        try {
            //example url http://www.houzz.com/professionals/s/D.r.c.-Building-Contractors,-Llc/c/Stamford%2C-CT
            String website = "http://www.houzz.com/professionals/s/" + data.getCompanyName().replace(" ", "-") + "/c/"  + data.getCity().replace(" ", "-") + "%2C" + data.getState();
            Document doc = Jsoup.connect(website).get();
            if (doc.toString().contains(data.getPhoneNumber())){
                

                data.setValid(false);
                data.setNotes("On Houzz");
                return false;
            }
            

        } catch (HttpStatusException ex) {
           
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        //returns true if phonenumber doesnt exist in houzz
        return true;
    }
    /**
     * returns true if there pro2url exists
     * returns false if pro2url does not exist
     * @param data
     * @return 
     * @
     */
    public boolean connectPro2URL(ExcelData data){

        DataManager mainData = DataManager.getInstance();
        String[] pro2url;
        
        try {
            String website = "http://www.houzz.com/professionals/s/" + data.getCompanyName().replace(" ", "-") + "/c/"  + data.getCity().replace(" ", "-") + "%2C" + data.getState();
            Document houzz = Jsoup.connect(website).get();
            
            pro2url = houzz.toString().split("\"");
            
            for (int i = 0; i < pro2url.length; i++){
                if (pro2url[i].contains(data.getPhoneNumber())){
                    if (pro2url[i - 9].contains("/pro2/")){
                    data.setPro2URL(pro2url[i - 9]);
                    data.setValid(true);
                    data.setNotes("Has Pro2URL");
                    return true;
                    }
                }
            }    
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
            return false;
    }
    
    public void TermSearcher(ExcelData currentWebsite){
        String keyterm = (String) data.getKeyTerms().get(0);
            currentWebsite.setValid(this.Iterate2LayerSearch(currentWebsite.getWebsite()));
            
            if (currentWebsite.getValid() == true){
                currentWebsite.setNotes("Term:" + keyterm + "found" );
            }
            
            

         // not sure if this is necessary if debugger never hits her remove statement
//        else {
//            currentWebsite.setValid(false);
//            currentWebsite.setNotes("Layer 2 Search: False");
//        }
    }
    
    public void FullCheck() {
        ExcelReader excelReader = new ExcelReader();
        this.LoadExcelData();
        excelReader.formatHttpString();
        ProgressHandle prog = ProgressHandleFactory.createHandle("Validating");
        int count = 0;
        prog.start(data.getExcelData().size());

        for (ExcelData currentWebsite : data.getExcelData()) {
            connectPro2URL(currentWebsite);
            if ((currentWebsite.getValid() == false) && currentWebsite.getNotes() == null){
                connectToHouzz(currentWebsite);
            }
            if ((currentWebsite.getNotes() == null)) {
                TermSearcher(currentWebsite);
               
            }
            
            prog.progress(count++);
        }

            

            

        
        prog.finish();
        SwingUtilities.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                DataManager.getInstance().firesEvent();
            }
        });
    }
    
    public void FormatHttpString(){
        
    }
    
    public boolean IterateKeyTerms(String url){
        boolean hasKeyTerm = false;
        for (Iterator it = data.getKeyTerms().iterator(); it.hasNext();){
            String i = (String) it.next();
            if(BasicTermChecker(url, i)){
                hasKeyTerm = true;
            }
        }
        return hasKeyTerm;
    }
    
    public boolean Iterate2LayerSearch(String url){
        boolean hasKeyTerm = false;
        for (Iterator it = data.getKeyTerms().iterator(); it.hasNext();){
            String i = (String) it.next();
            if (connectURL(url, i)){
                hasKeyTerm = true;
            }
            
        }
        return hasKeyTerm;
    }
    
}
        
    

