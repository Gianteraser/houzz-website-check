/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.excel.reader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.event.EventListenerList;

/**
 *
 * @author sqa
 */
public class DataManager
{
    private List<ExcelData> excelData;
    private final EventListenerList buttonListeners = new EventListenerList();
    private String CSV_location;
    private final ArrayList keyTerms = new ArrayList();

    
    //private List<ExcelData> validatedExcelData;

    
    private DataManager()
    {
    }
    
    public static DataManager getInstance()
    {
        return DataManagerHolder.INSTANCE;
    }

    /**
     * @return the excelData
     */
    public List<ExcelData> getExcelData()
    {
        return excelData;
    }

    /**
     * @param excelData the excelData to set
     */
    public void setExcelData(List<ExcelData> excelData)
    {
        this.excelData = excelData;
    }

    /**
     * @return the CSV_location
     */
    public String getCSV_location() {
        return CSV_location;
    }

    /**
     * @param CSV_location the CSV_location to set
     */
    public void setCSV_location(String CSV_location) {
        this.CSV_location = CSV_location;
    }

    /**
     * @return the keyTerms
     */
    public ArrayList getKeyTerms() {
        return keyTerms;
    }


    
    private static class DataManagerHolder
    {

        private static final DataManager INSTANCE = new DataManager();
    }
    
    
    public void addButtonListener(ButtonListener listener){
        buttonListeners.add(ButtonListener.class, listener);
    }
    
    public void firesEvent(){
        ButtonListener[] listeners = buttonListeners.getListeners(ButtonListener.class);
        for (ButtonListener listener : listeners){
            listener.buttonPressed();
        }
    }
    
    public int getNumberOfValids(){
        int count = 0;
        for (ExcelData data : excelData){
            if (data.getValid() == true){
                count++;
            }
            
        }
        return count;
    }

    public String KeyTermsToString() {
        String currentWords = "";    
            
        for (Iterator it = this.getKeyTerms().iterator(); it.hasNext();) {
            String words = (String) it.next();
            currentWords += words + ", ";
        }
        return currentWords;
    }
    

    
}
