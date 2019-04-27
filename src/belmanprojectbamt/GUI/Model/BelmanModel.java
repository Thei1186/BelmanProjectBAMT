/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Model;

import belmanprojectbamt.BLL.BelmanManager;
import belmanprojectbamt.BLL.LogicInterface;
import belmanprojectbamt.DAL.Facade;
import belmanprojectbamt.DAL.IFacade;

/**
 *
 * @author Theis
 */
public class BelmanModel
{

    private static BelmanModel bModel;
    private LogicInterface bManager;

    private BelmanModel()
    {
        bModel = null;
        IFacade bFacade = new Facade();
        bManager = new BelmanManager(bFacade);
    }

    public static BelmanModel getInstance()
    {
        if (bModel == null)
        {
            bModel = new BelmanModel();
        }
        return bModel;
    }

}
