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
public class Order
{
  private String orderNumber;
  private String customerName;
  private Date deliveryDate;
  private Date startDate;
  private Date endDate;

    public Order(String orderNumber, String customerName, Date deliveryDate, Date startDate, Date endDate)
    {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.deliveryDate = deliveryDate;
        this.startDate = startDate;
        this.endDate = endDate;
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
  
}
