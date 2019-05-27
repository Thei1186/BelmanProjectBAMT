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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

    public List<ProductionOrder> getProductionOrders()
    {
        List<ProductionOrder> productionOrders = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ProductionOrder ORDER BY DeliveryDate");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("Id");
                String orderNumber = rs.getString("OrderNr");
                String customerName = rs.getString("CustomerName");
                Date deliveryDate = rs.getDate("DeliveryDate");
                List<DepartmentTask> deptTasks = new ArrayList<>();
                ProductionOrder order = new ProductionOrder(id, orderNumber, customerName, deliveryDate, deptTasks);

                PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM DepartmentTask WHERE ProdId = (?)");
                pstmt1.setInt(1, id);
                ResultSet rs1 = pstmt1.executeQuery();

                while (rs1.next())
                {
                    int deptId = rs1.getInt("ProdId");
                    String DepartmentName = rs1.getString("DepartmentName");
                    Date startDate = rs1.getDate("StartDate");
                    Date endDate = rs1.getDate("EndDate");
                    int timeOffset = rs1.getInt("TimeOffset");
                    boolean finishedTask = rs1.getBoolean("FinishedTask");
                    DepartmentTask task = new DepartmentTask(deptId, DepartmentName, startDate, endDate, timeOffset, finishedTask);
                    order.getDeptTasks().add(task);
                }

                productionOrders.add(order);
            }
        } 
        catch (Exception e)
        {

        }

        return productionOrders;
    }

    public void setTaskAsDone(DepartmentTask task)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("UPDATE DepartmentTask Set FinishedTask = (?)"
                    + " WHERE ProdId = (?) AND DepartmentName = (?)");
            pstmt.setBoolean(1, true);
            pstmt.setInt(2, task.getProdId());
            pstmt.setString(3, task.getDepartmentName());

            pstmt.execute();
        }
        catch (Exception e)
        {
          
        }
    }
}
