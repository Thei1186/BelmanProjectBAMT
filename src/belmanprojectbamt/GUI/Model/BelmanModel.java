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
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private BelmanModel() throws Exception
    {
        bModelInstance = null;
        IFacade bFacade = new Facade();
        bManager = new BelmanManager(bFacade);

        productionOrders = FXCollections.observableArrayList(bManager.getProductionOrder());
    }

    public static BelmanModel getInstance() throws Exception
    {
        if (bModelInstance == null)
        {
            bModelInstance = new BelmanModel();
        }
        return bModelInstance;
    }

    public void updateLog(ProductionOrder pOrder, DepartmentTask dTask, String logMessage)
    {
        bManager.updateLog(pOrder, dTask, logMessage);
    }

    /**
     *
     * @return
     */
    public ObservableList<ProductionOrder> getProductionOrder()
    {
        return productionOrders;
    }

    public void setTaskAsDone(DepartmentTask task)
    {
        bManager.setTaskAsDone(task);
    }

    public String getDepartmentName()
    {
        return bManager.getDepartmentName();
    }

    public int getOffSet()
    {
        return bManager.getOffSet();
    }

    public double getProgressBarData(Date startDate, Date endDate)
    {
        return bManager.getProgressBarData(startDate, endDate);
    }

}
