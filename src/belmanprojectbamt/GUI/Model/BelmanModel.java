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


    private BelmanModel() throws IOException
    {
        bModelInstance = null;
        IFacade bFacade = new Facade();
        bManager = new BelmanManager(bFacade);
        
        try {
            productionOrders = FXCollections.observableArrayList(bManager.getProductionOrder());

        } catch (Exception ex) {
            Logger.getLogger(BelmanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    public static BelmanModel getInstance() throws IOException
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
    public ObservableList<ProductionOrder> getProductionOrder()
    {
        try {
            return productionOrders;
        } catch (Exception ex) {
            Logger.getLogger(BelmanModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

}
