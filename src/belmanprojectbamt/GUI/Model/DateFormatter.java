/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Theis
 */
public class DateFormatter
{
    public DateFormatter()
    {
       
    }

    public String formatDate(Date dateToFormat)
    {
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        String formattedDate = df.format(dateToFormat);
        
        return formattedDate;
    }

}
