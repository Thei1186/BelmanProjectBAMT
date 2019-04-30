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

/**
 *
 * @author Theis
 */
public class BelmanModel
{

    private static BelmanModel bModelInstance;
    private LogicInterface bManager;

    private BelmanModel()
    {
        bModelInstance = null;
        IFacade bFacade = new Facade();
        bManager = new BelmanManager(bFacade);
    }

    public static BelmanModel getInstance()
    {
        if (bModelInstance == null)
        {
            bModelInstance = new BelmanModel();
        }
        return bModelInstance;
    }
//
//    /**
//     *
//     * @return
//     */
//    public List<Order> getProductionOrder()
//    {
//        
//    }
//
//    /**
//     *
//     * @return
//     */
//    public List<DepartmentTask> getDepartmentTasks()
//    {
//
//    }
}
