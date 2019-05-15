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
import java.sql.Statement;
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

    public List<ProductionOrder> getProductionOrders()
    {
        List<ProductionOrder> productionOrders = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ProductionOrder");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("Id");
                String orderNumber = rs.getString("OrderNr");
                String customerName = rs.getString("CustomerName");
                Date deliveryDate = rs.getDate("DeliveryDate");
                List<DepartmentTask> deptTasks = new ArrayList<>();
                ProductionOrder order = new ProductionOrder(id, orderNumber, customerName, deliveryDate, deptTasks);

                PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM DepartmentTask WHERE Id = (?)");
                pstmt1.setInt(1, id);
                ResultSet rs1 = pstmt1.executeQuery();

                while (rs1.next())
                {
                    int deptId = rs1.getInt("Id");
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
            PreparedStatement pstmt = con.prepareStatement("UPDATE DepartmentTask Set FinishedTask = (?) WHERE Id = (?) AND DepartmentName = (?)");
            pstmt.setBoolean(1, true);
            pstmt.setInt(2, task.getId());
            pstmt.setString(3, task.getDepartmentName());

            pstmt.execute();
        }
        catch (Exception e)
        {

        }
    }
}
