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

    private static final String PROP_FILE = "Config.txt";
    private Properties configProp;

    public ConfigReader() throws FileNotFoundException, IOException
    {
        configProp = new Properties();
        configProp.load(new FileInputStream(PROP_FILE));
    }

    public String getDepartmentName()
    {
        return configProp.getProperty("Department");
    }

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
