/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.DAL;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.ProductionOrder;
import java.util.List;

/**
 *
 * @author Theis
 */
public interface IFacade
{

    /**
     * Checks if task is set to done in the database
     * @param task
     * @return
     */
    public boolean checkIfDone(DepartmentTask task);
    
    /**
     * Retrieves a list of production orders
     * @return @throws java.lang.Exception
     */
    public List<ProductionOrder> getProductionOrder() throws Exception;

    /**
     * Sets a department task as done
     * @param task
     */
    public void setTaskAsDone(DepartmentTask task);

    /**
     * Gets the department name
     * @return
     */
    public String getDepartmentName();

    /**
     * Gets the time offset 
     * @return
     */
    public int getOffSet();

    /**
     * Tries to update the log in the database
     * @param pOrder
     * @param dTask
     * @param logMessage
     */
    public void updateLog(ProductionOrder pOrder, DepartmentTask dTask, String logMessage);
}
