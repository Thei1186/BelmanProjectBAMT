/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Model;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.Order;
import belmanprojectbamt.BLL.BelmanManager;
import belmanprojectbamt.BLL.LogicInterface;
import belmanprojectbamt.DAL.Facade;
import belmanprojectbamt.DAL.IFacade;
import java.util.List;
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
    
    private ObservableList<Order> productionOrders;
    private ObservableList<DepartmentTask> departmentTasks;

    private BelmanModel()
    {
        bModelInstance = null;
        IFacade bFacade = new Facade();
        bManager = new BelmanManager(bFacade);
        
        try {
            productionOrders = FXCollections.observableArrayList(bManager.getProductionOrder());
            departmentTasks = FXCollections.observableArrayList(bManager.getDepartmentTasks());

        } catch (Exception ex) {
            Logger.getLogger(BelmanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    public static BelmanModel getInstance()
    {
        if (bModelInstance == null)
        {
            bModelInstance = new BelmanModel();
        }
        return bModelInstance;
    }

    /**
     *
     * @return
     */
    public ObservableList<Order> getProductionOrder()
    {
        try {
            return productionOrders;
        } catch (Exception ex) {
            Logger.getLogger(BelmanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return
     */
    public ObservableList<DepartmentTask> getDepartmentTasks()
    {
        try {
            return departmentTasks;
        } catch (Exception ex) {
            Logger.getLogger(BelmanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
