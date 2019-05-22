/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.BLL;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.ProductionOrder;
import belmanprojectbamt.DAL.IFacade;
import java.util.Date;
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
    public List<ProductionOrder> getProductionOrder() throws Exception
    {
        return belDao.getProductionOrder();
    }

    @Override
    public void setTaskAsDone(DepartmentTask task)
    {
        belDao.setTaskAsDone(task);
    }

    @Override
    public String getDepartmentName()
    {
        return belDao.getDepartmentName();
    }
    
    @Override
    public int getOffSet()
    {
        return belDao.getOffSet();
    }

    @Override
    public double getProgressBarData(Date startDate, Date endDate)
    {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        
        double availableTime = (endTime - startTime)/ (1000 * 60 * 60 * 24);
        
        double todayInMillis = System.currentTimeMillis() / (1000 * 60 * 60 * 24);

        double daysSpent = todayInMillis - (startTime / (1000 * 60 * 60 * 24));

        double elapsedTime = daysSpent / availableTime;  

        return elapsedTime;
    }
}
