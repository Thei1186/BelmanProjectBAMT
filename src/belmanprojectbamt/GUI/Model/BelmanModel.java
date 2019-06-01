/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Model;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.ProductionOrder;
import belmanprojectbamt.BLL.BelmanManager;
import belmanprojectbamt.BLL.LogicInterface;
import belmanprojectbamt.DAL.Facade;
import belmanprojectbamt.DAL.IFacade;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Theis
 */
public class BelmanModel
{
    
    private static BelmanModel bModelInstance;
    private LogicInterface bManager;
    
    private ObservableList<ProductionOrder> productionOrders;

    /**
     * Singleton constructor
     *
     * @throws Exception
     */
    private BelmanModel() throws Exception
    {
        bModelInstance = null;
        IFacade bFacade = new Facade();
        bManager = new BelmanManager(bFacade);
        
    }

    /**
     * The model's getInstance method returns an instance of the model
     *
     * @return
     * @throws Exception
     */
    public static BelmanModel getInstance() throws Exception
    {
        if (bModelInstance == null)
        {
            bModelInstance = new BelmanModel();
        }
        return bModelInstance;
    }

    /**
     * Updates the log
     *
     * @param pOrder
     * @param dTask
     * @param logMessage
     */
    public void updateLog(ProductionOrder pOrder, DepartmentTask dTask, String logMessage)
    {
        bManager.updateLog(pOrder, dTask, logMessage);
    }

    /**
     * Takes an Array list of production orders from the Manager and makes it
     * into an observable array list.
     *
     * @return
     * @throws java.lang.Exception
     */
    public ObservableList<ProductionOrder> getProductionOrders() throws Exception
    {
        productionOrders = FXCollections.observableArrayList(bManager.getProductionOrder());
        return productionOrders;
    }

    /**
     * Calls the setTaskAsDone method on the bManager instance
     *
     * @param task
     */
    public void setTaskAsDone(DepartmentTask task)
    {
        bManager.setTaskAsDone(task);
    }

    /**
     * Gets the department name from the Manager
     *
     * @return
     */
    public String getDepartmentName()
    {
        return bManager.getDepartmentName();
    }

    /**
     * Gets the time offset from the Manager
     *
     * @return
     */
    public int getOffSet()
    {
        return bManager.getOffSet();
    }

    /**
     * Gets the progress bar data calcuted in the manager
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public double getProgressBarData(Date startDate, Date endDate)
    {
        return bManager.getProgressBarData(startDate, endDate);
    }

    /**
     * Checks if the current date is past the end date for a department task
     *
     * @param task
     * @return
     */
    public boolean isEndDateReached(DepartmentTask task)
    {
        return bManager.isEndDateReached(task);
    }

    /**
     * Checks if the current date is past the start date for a department task
     *
     * @param task
     * @return
     */
    public boolean isStartDateReached(DepartmentTask task)
    {
        return bManager.isStartDateReached(task);
    }
}
