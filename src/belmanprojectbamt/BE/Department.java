/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.BE;

/**
 *
 * @author Theis
 */
public class Department
{
 private String departmentName;
 private int timeOffset;

    public Department(String departmentName, int timeOffset)
    {
        this.departmentName = departmentName;
        this.timeOffset = timeOffset;
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
 
}
