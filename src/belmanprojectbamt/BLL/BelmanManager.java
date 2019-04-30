/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.BLL;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.Order;
import belmanprojectbamt.DAL.IFacade;
import java.util.List;

/**
 *
 * @author Theis
 */
public class BelmanManager implements LogicInterface
{

    IFacade belDao;

    public BelmanManager(IFacade facade)
    {
        belDao = facade;
    }

    @Override
    public List<Order> getProductionOrder() throws Exception
    {
        return belDao.getProductionOrder();
    }

    @Override
    public List<DepartmentTask> getDepartmentTasks() throws Exception
    {
        return belDao.getDepartmentTasks();
    }

}
