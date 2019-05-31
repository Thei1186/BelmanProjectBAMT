/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.DAL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Theis
 */
public class ConfigReader
{

    private static final String PROP_FILE = "Config.txt"; //defines the file to read
    private Properties configProp; 

    public ConfigReader() throws FileNotFoundException, IOException
    {
        configProp = new Properties();
        configProp.load(new FileInputStream(PROP_FILE));
    }
    
    /**
     * Gets department name from the config file
     * @return 
     */
    public String getDepartmentName()
    {
        return configProp.getProperty("Department");
    }
    
    /**
     * Gets time offset from the config file.
     * Defaults as 0 in case no offset is set within the file
     * @return 
     */
    public int getOffSet()
    {
        if (configProp.getProperty("TimeOffset").equals(""))
        {
            return 0;
        } 
        else
        {
            return Integer.parseInt(configProp.getProperty("TimeOffset"));
        }
    }
}
