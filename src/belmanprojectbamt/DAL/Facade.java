/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.DAL;

import belmanprojectbamt.BE.ProductionOrder;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Theis
 */
public class Facade implements IFacade
{
    FileDAO fDAO;
    DatabaseDAO dDAO;

    public Facade() throws IOException
    {
        fDAO = new FileDAO();
        dDAO = new DatabaseDAO();
    }

    @Override
    public List<ProductionOrder> getProductionOrder() throws Exception
    {
        return dDAO.getProductionOrders();
//        return fDAO.getProductionOrders();
    }

}
