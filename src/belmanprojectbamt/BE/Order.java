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
  

    public Order(String orderNumber, String customerName, Date deliveryDate)
    {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.deliveryDate = deliveryDate;
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
        return "Order{" + "orderNumber=" + orderNumber + ", customerName=" + customerName + ", deliveryDate=" + deliveryDate + '}';
    }

}
