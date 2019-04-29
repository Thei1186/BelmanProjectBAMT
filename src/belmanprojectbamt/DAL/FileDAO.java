/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.DAL;

import belmanprojectbamt.BE.Order;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public FileDAO()
    {
        productionOrders = new ArrayList<>();
    }

    public void getProductionOrder() throws FileNotFoundException, IOException, ParseException
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

//            JSONObject deptTaskObject = (JSONObject) oObject.get("DepartmentTasks");
//            Date startDate = (Date) deptTaskObject.get("StartDate");
        }

    }

}
