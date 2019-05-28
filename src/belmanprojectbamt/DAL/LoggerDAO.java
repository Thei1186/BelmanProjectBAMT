/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.DAL;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.ProductionOrder;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Theis
 */
public class LoggerDAO
{
    DbConnectionProvider ds;

    public LoggerDAO() throws IOException
    {
        ds = new DbConnectionProvider();
    }
    
    public void updateLog(ProductionOrder pOrder, DepartmentTask dTask, String logMessage)
    {
        try (Connection con = ds.getConnection())
        {
            Calendar cal = Calendar.getInstance();
            java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());
            
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Log VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, pOrder.getId());
            pstmt.setString(2, pOrder.getOrderNumber());
            pstmt.setString(3, logMessage);
            pstmt.setString(4, dTask.getDepartmentName());
            pstmt.setDate(5, sqlDate);
            pstmt.execute();    
        } 
        catch (Exception e)
        {
            Logger.getLogger(LoggerDAO.class.getName()).log(Level.SEVERE, "Something went wrong when trying to update the log", e);
        }
    }
}
