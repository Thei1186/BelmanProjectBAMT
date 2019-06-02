/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.BLL;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.ProductionOrder;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Theis
 */
public interface LogicInterface
{

    /**
     * Gets a list of production orders from the facade
     *
     * @return
     * @throws Exception
     */
    public List<ProductionOrder> getProductionOrder() throws Exception;

    /**
     *
     * @param pOrder
     * @param dTask
     * @param logMessage
     */
    public void updateLog(ProductionOrder pOrder, DepartmentTask dTask, String logMessage);

    /**
     * Sets task as done
     *
     * @param task
     */
    public void setTaskAsDone(DepartmentTask task);

    /**
     * Gets time offset from config file through the facade
     *
     * @return
     */
    public int getOffSet();

    /**
     * Gets department name from config file through the facade
     *
     * @return
     */
    public String getDepartmentName();

    /**
     * Takes a start and end date and calculates the time elapsed and returns
     * the elapsed time
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public double getProgressBarData(Date startDate, Date endDate);

    /**
     * Checks if the current date is past the start date for a department task
     *
     * @param task
     * @return
     */
    public boolean isStartDateReached(DepartmentTask task);

    /**
     * Checks if the current date is past the end date for a department task
     *
     * @param task
     * @return
     */
    public boolean isEndDateReached(DepartmentTask task);
    
    /**
     * Checks if task is done in the database
     * @param task
     * @return 
     */
    public boolean checkIfDone(DepartmentTask task);
}
