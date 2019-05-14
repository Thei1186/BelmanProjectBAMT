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
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Theis
 */
public class DatabaseDAO
{

    DbConnectionProvider ds;

    public DatabaseDAO() throws IOException
    {
        ds = new DbConnectionProvider();
    }

    public void updateDatabase(List<ProductionOrder> pOrders)
    {
        try (Connection con = ds.getConnection())
        {
            con.setAutoCommit(false);
            
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO ProductionOrder VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO ProductionOrder VALUES (?,?,?,?,?)");
            
            for (ProductionOrder pOrder : pOrders)
            {
                java.sql.Date sqlDate = new java.sql.Date(pOrder.getDeliveryDate().getTime());
                
                pstmt.setString(1, pOrder.getOrderNumber());
                pstmt.setString(2, pOrder.getCustomerName());
                pstmt.setDate(3, sqlDate);
                pstmt.addBatch();
                
                List<DepartmentTask> tasks = pOrder.getDeptTasks();
                for (DepartmentTask task : tasks)
                {
                    java.sql.Date sqlStartDate = new java.sql.Date(task.getStartDate().getTime());
                    java.sql.Date sqlEndDate = new java.sql.Date(task.getEndDate().getTime());
                    
                    pstmt1.setString(1, task.getDepartmentName());
                    pstmt1.setDate(2, sqlStartDate);
                    pstmt1.setDate(3, sqlEndDate);
                    pstmt1.setInt(4, task.getTimeOffset());
                    pstmt1.setBoolean(5, task.isFinishedTask());
                    pstmt1.addBatch();
                }
            }
                pstmt.executeBatch();
                pstmt1.executeBatch();
                con.commit();
        } catch (Exception e)
        {
        }

    }
}
