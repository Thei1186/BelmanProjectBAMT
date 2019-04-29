/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.DAL;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.Order;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Theis
 */
public class FileDAO
{

    private List<Order> productionOrders;
    private List<DepartmentTask> departmentTasks;

    public FileDAO()
    {
        productionOrders = new ArrayList<>();
        departmentTasks = new ArrayList<>();
    }

    public List<Order> getProductionOrders() throws FileNotFoundException, IOException, ParseException, java.text.ParseException
    {
        Object obj = new JSONParser().parse(new FileReader("Data.json"));

        JSONObject jObject = (JSONObject) obj;

        JSONArray jArray = (JSONArray) jObject.get("ProductionOrders");
        for (Object object : jArray)
        {
            JSONObject oObject = (JSONObject) object;

            JSONObject pOObject = (JSONObject) oObject.get("Customer");
            String customerName = (String) pOObject.get("Name");

            JSONObject orderObject = (JSONObject) oObject.get("Order");
            String orderNum = (String) orderObject.get("OrderNumber");

            JSONObject deliveryObject = (JSONObject) oObject.get("Delivery");
            String sDate = (String) deliveryObject.get("DeliveryTime");
            Date deliveryDate = formatDateString(sDate);

            Order order = new Order(orderNum, customerName, deliveryDate);
            productionOrders.add(order);

//            JSONObject deptTaskObject = (JSONObject) oObject.get("DepartmentTasks");
//            Date startDate = (Date) deptTaskObject.get("StartDate");
        }
//        for (Order productionOrder : productionOrders)
//        {
//            System.out.println(productionOrder.toString());
//        }
        return productionOrders;
    }

    public List<DepartmentTask> getDepartmentTasks() throws FileNotFoundException, IOException, ParseException
    {
        Object obj = new JSONParser().parse(new FileReader("Data.json"));

        JSONObject jObject = (JSONObject) obj;

        JSONArray jArray = (JSONArray) jObject.get("ProductionOrders");
        for (Object object : jArray)
        {
            JSONObject oObject = (JSONObject) object;
            JSONArray dTaskArray = (JSONArray) oObject.get("DepartmentTasks");
            for (Object object1 : dTaskArray)
            {
                JSONObject dTaskObject = (JSONObject) object1;
                JSONObject dObject = (JSONObject) dTaskObject.get("Department");
                String departmentName = (String) dObject.get("Name");

                String endDateString = (String) dTaskObject.get("EndDate");
                Date endDate = formatDateString(endDateString);

                String startDateString = (String) dTaskObject.get("StartDate");
                Date startDate = formatDateString(startDateString);

                boolean finishedTask = (boolean) dTaskObject.get("FinishedOrder");

                departmentTasks.add(new DepartmentTask(departmentName, startDate, endDate, 0, finishedTask));
            }
        }
        return departmentTasks;
    }

    public Date formatDateString(String dateString)
    {
        Long milli = Long.parseLong(dateString.substring(6, dateString.indexOf("+")));
        Date newDate = new Date(milli);
        return newDate;
    }
}
