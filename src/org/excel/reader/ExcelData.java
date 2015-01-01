
package org.excel.reader;


public class ExcelData
{
    private String salesMetro;
    private String category;
    private String companyName;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;
    private String emailAddress;
    private String emailSourceLink;
    private String website;
    private String pro2URL;
    private boolean valid;
    private String notes;

    public ExcelData(String salesMetro, String category, String companyName, String firstName,
                     String lastName, String address, String city, String state, String phoneNumber, 
                     String emailAddress, String emailSourceLink, String website, String pro2URL, boolean valid)
    {
        this.salesMetro = salesMetro;
        this.category = category;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.emailSourceLink = emailSourceLink;
        this.website = website;
        this.pro2URL = pro2URL;
        this.valid = valid;

    }

    ExcelData()
    {
        
    }
    

    /**
     * @return the salesMetro
     */
    public String getSalesMetro()
    {
        return salesMetro;
    }

    /**
     * @param salesMetro the salesMetro to set
     */
    public void setSalesMetro(String salesMetro)
    {
        this.salesMetro = salesMetro;
    }

    /**
     * @return the category
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName()
    {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the emailSourceLink
     */
    public String getEmailSourceLink()
    {
        return emailSourceLink;
    }

    /**
     * @param emailSourceLink the emailSourceLink to set
     */
    public void setEmailSourceLink(String emailSourceLink)
    {
        this.emailSourceLink = emailSourceLink;
    }

    /**
     * @return the pro2URL
     */
    public String getPro2URL()
    {
        return pro2URL;
    }

    /**
     * @param pro2URL the pro2URL to set
     */
    public void setPro2URL(String pro2URL)
    {
        this.pro2URL = pro2URL;
    }

    /**
     * @return the valid
     */
    public boolean getValid()
    {
        return valid;
    }

    /**
     * @param valid the valid to set
     */
    public void setValid(boolean valid)
    {
        this.valid = valid;
    }

    /**
     * @return the website
     */
    public String getWebsite()
    {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website)
    {
        this.website = website;
    }

    /**
     * @return the notes
     */
    public String getNotes()
    {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes)
    {
        this.notes = notes;
    }
    
    
    
    
    
}
