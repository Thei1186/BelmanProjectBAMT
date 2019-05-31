/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.DAL;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.ProductionOrder;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Theis
 */
public class Facade implements IFacade
{

    ConfigReader cReader;
    DatabaseDAO dDAO;
    LoggerDAO lDAO;
    
    public Facade() throws IOException
    {
        dDAO = new DatabaseDAO();
        cReader = new ConfigReader();
        lDAO = new LoggerDAO();
    }
    
    /**
     * Retrieves a list of production orders
     * @return
     * @throws Exception 
     */
    @Override
    public List<ProductionOrder> getProductionOrder() throws Exception
    {
        return dDAO.getProductionOrders();
    }
    
    /**
     * Sets a department task as done
     * @param task 
     */
    @Override
    public void setTaskAsDone(DepartmentTask task)
    {
        dDAO.setTaskAsDone(task);
    }
    
    /**
     * Gets the department name
     * @return 
     */
    @Override
    public String getDepartmentName()
    {
        return cReader.getDepartmentName();
    }
    
    /**
     * Gets the time offset 
     * @return 
     */
    @Override
    public int getOffSet()
    {
        return cReader.getOffSet();
    }
    
    /**
     * Tries to update the log in the database
     * @param pOrder
     * @param dTask
     * @param logMessage 
     */
    @Override
    public void updateLog(ProductionOrder pOrder, DepartmentTask dTask, String logMessage)
    {
        lDAO.updateLog(pOrder, dTask, logMessage);
    }
    
}
