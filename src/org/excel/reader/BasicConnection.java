
package org.excel.reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.openide.util.Exceptions;


/**
 *
 * @author sqa
 */
public class BasicConnection
{
    Connection con;
    
    public void connectTo(){
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "password");
            System.out.println("Successfully Connected");
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    public void addSampleRow(){
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO EMPLOYEE " + "VALUES (123, 'amanda')");
            statement.executeUpdate("INSERT INTO EMPLOYEE " + "VALUES (321, 'lorene')");
            
            con.close();
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    public void addValidWebistes(){
        List<ExcelData> validatedWebsites = DataManager.getInstance().getExcelData();
        String query = "INSERT INTO PASSEDWEBSITES VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
           
            for (ExcelData sites : validatedWebsites){
                ps.setString(1, sites.getSalesMetro());
                ps.setString(2, sites.getCategory());
                ps.setString(3, sites.getCompanyName());
                ps.setString(4, sites.getFirstName());
                ps.setString(5, sites.getLastName());
                ps.setString(6, sites.getAddress());
                ps.setString(7, sites.getCity());
                ps.setString(8, sites.getState());
                ps.setString(9,sites.getPhoneNumber());
                ps.setString(10, sites.getEmailAddress());
                ps.setString(11, sites.getEmailSourceLink());
                ps.setString(12, sites.getWebsite());
                ps.setString(13, sites.getPro2URL());
                int valid;

                boolean v = sites.getValid();
                
                // convert valid to sql 0 or 1
                if (v){
                    valid = 1;
                }
                else{
                    valid = 0;
                }
                
                ps.setInt(14, valid);
                
                              
                ps.execute();
                
                
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        
    }
    
}
