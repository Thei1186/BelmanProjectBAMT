/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.BE;

import java.util.Date;

/**
 *
 * @author Theis
 */
public class DepartmentTask
{

    private String departmentName;
    private Date startDate;
    private Date endDate;
    private int timeOffset;
    private boolean finishedTask;

    public DepartmentTask(String departmentName, Date startDate, Date endDate, int timeOffset, boolean finishedTask)
    {
        this.departmentName = departmentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeOffset = timeOffset;
        this.finishedTask = finishedTask;
    }
    
    public String getDepartmentName()
    {
        return departmentName;
    }

    public void setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
    }

    public int getTimeOffset()
    {
        return timeOffset;
    }

    public void setTimeOffset(int timeOffset)
    {
        this.timeOffset = timeOffset;
    }

    @Override
    public String toString()
    {
        return "DepartmentTask{" + "departmentName=" + departmentName + ", startDate=" + startDate + ", endDate=" + endDate + ", timeOffset=" + timeOffset + ", finishedTask=" + finishedTask + '}';
    }

    
    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public boolean isFinishedTask()
    {
        return finishedTask;
    }

    public void setFinishedTask(boolean finishedTask)
    {
        this.finishedTask = finishedTask;
    }

}
