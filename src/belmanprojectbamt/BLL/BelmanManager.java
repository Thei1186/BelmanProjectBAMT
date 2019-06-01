/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.BLL;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.ProductionOrder;
import belmanprojectbamt.DAL.IFacade;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Manages logic and acts as a bridge between the DAL and GUI layers
 * @author Theis
 */
public class BelmanManager implements LogicInterface
{

    IFacade belDao;

    public BelmanManager(IFacade facade)
    {
        belDao = facade;
    }

    /**
     * Checks if the current date is past the start date for a department task
     *
     * @param task
     * @return
     */
    @Override
    public boolean isStartDateReached(DepartmentTask task)
    {
        Calendar cal = Calendar.getInstance();
        Long timeOffsetInMilli = TimeUnit.DAYS.toMillis(task.getTimeOffset());
        Long curDateMilli = cal.getTimeInMillis();

        return curDateMilli >= (task.getStartDate().getTime() - timeOffsetInMilli);
    }

    /**
     * Checks if the current date is past the end date for a department task
     *
     * @param task
     * @return
     */
    @Override
    public boolean isEndDateReached(DepartmentTask task)
    {
        Calendar cal = Calendar.getInstance();
        Long curDateMilli = cal.getTimeInMillis();

        return curDateMilli > task.getEndDate().getTime();
    }

    /**
     * Gets a list of production orders from the facade
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<ProductionOrder> getProductionOrder() throws Exception
    {
        return belDao.getProductionOrder();
    }

    /**
     * Sets task as done
     *
     * @param task
     */
    @Override
    public void setTaskAsDone(DepartmentTask task)
    {
        belDao.setTaskAsDone(task);
    }

    /**
     * Gets department name from config file through the facade
     *
     * @return
     */
    @Override
    public String getDepartmentName()
    {
        return belDao.getDepartmentName();
    }

    /**
     * Gets time offset from config file through the facade
     *
     * @return
     */
    @Override
    public int getOffSet()
    {
        return belDao.getOffSet();
    }

    /**
     * Takes a start and end date and calculates the time elapsed and returns
     * the elapsed time
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public double getProgressBarData(Date startDate, Date endDate)
    {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();

        double availableTime = (endTime - startTime) / (1000 * 60 * 60 * 24);

        double todayInMillis = System.currentTimeMillis() / (1000 * 60 * 60 * 24);

        double daysSpent = todayInMillis - (startTime / (1000 * 60 * 60 * 24));

        double elapsedTime = daysSpent / availableTime;

        return elapsedTime;
    }

    /**
     * Updates the log
     *
     * @param pOrder
     * @param dTask
     * @param logMessage
     */
    @Override
    public void updateLog(ProductionOrder pOrder, DepartmentTask dTask, String logMessage)
    {
        belDao.updateLog(pOrder, dTask, logMessage);
    }
}
