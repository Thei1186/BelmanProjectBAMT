/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.BE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Theis
 */
public class ProductionOrder
{
  private int id;
  private String orderNumber;
  private String customerName;
  private Date deliveryDate;
  private List<DepartmentTask> deptTasks;
  

    public ProductionOrder(String orderNumber, String customerName, Date deliveryDate)
    {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.deliveryDate = deliveryDate;
        this.deptTasks = new ArrayList<>();
    }

    public ProductionOrder(int id, String orderNumber, String customerName, Date deliveryDate, List<DepartmentTask> deptTasks)
    {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.deliveryDate = deliveryDate;
        this.deptTasks = deptTasks;
    }
    

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString()
    {
        return "Order{" + "orderNumber=" + orderNumber + ", customerName=" + customerName + ", deliveryDate=" + deliveryDate + ", deptTasks=" + deptTasks + '}';
    }

    public List<DepartmentTask> getDeptTasks()
    {
        return deptTasks;
    }

    public void setDeptTasks(List<DepartmentTask> deptTasks)
    {
        this.deptTasks = deptTasks;
    }

    public int getId()
    {
        return id;
    }
}
